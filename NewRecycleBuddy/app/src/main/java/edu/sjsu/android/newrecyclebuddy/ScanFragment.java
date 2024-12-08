package edu.sjsu.android.newrecyclebuddy;

import static android.app.Activity.RESULT_OK;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ScanFragment extends Fragment {

    private ImageView image;
    private Button uploadImageButton;
    private Button analyzeButton;
    private final int GALLERY_REQUEST_CODE = 1;
    private TextView analysisResult;
    private Uri imageUri;
    private static final int REQUEST_CODE_PERMISSION = 100;

    public ScanFragment() {
        // Required empty public constructor
    }

    public static ScanFragment newInstance(String param1, String param2) {
        ScanFragment fragment = new ScanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scan, container, false);
        image = view.findViewById(R.id.preview_image);
        uploadImageButton = view.findViewById(R.id.upload_image_button);
        analyzeButton = view.findViewById(R.id.analyze_button);
        analysisResult = view.findViewById(R.id.analysis_result);

        uploadImageButton.setOnClickListener(v -> {
            Log.d("test", "Button clicked");
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Log.d("test", "Permission not granted, requesting...");
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_PERMISSION);
            }
        });

        analyzeButton.setOnClickListener(v -> analyzeImage());
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("test", "In onRequestPermissionsResult() method to request for permission");

        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, open the gallery
                openGallery();
            } else {
                // Permission denied, show a message to the user
                Toast.makeText(getContext(), "Permission denied. Please enable it in app settings.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", requireActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }
    }

    private void analyzeImage() {
        Log.d("test", "analyze button is clicked");

        // Make sure image is inputted by user
        if(image.getDrawable() == null){
            Toast.makeText(getActivity(), "Please upload an image first!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Prepare image to send to backend Flask API model
        if(imageUri == null){
            Toast.makeText(getActivity(), "Image URI is invalid. Try again!", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.d("test", "Image URI is valid: " + imageUri.toString());

        File imageFile = new File(getRealPathFromURI(imageUri));

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", imageFile.getName(),
                        RequestBody.create(imageFile, MediaType.parse("image/jpeg")))
                .build();

        // My comp IP address: 192.168.56.1
        // My phone IP address: 192.168.0.125
        // My local host: 127.0.0.1
        // Special alias: 10.0.2.2 -- predefined IP that maps to host machine
        Request request = new Request.Builder().url("http://192.168.56.1:10000/predict").post(requestBody).build();
        Log.d("test", "Sending request to Flask server...");


        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("test", "Network request failed", e);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getActivity(), "server down", Toast.LENGTH_SHORT).show();
                        analysisResult.setText("error connecting to the server!!");
                    });
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d("test", "Response received");
//                analysisResult.setText(response.body().string());
                if (response.isSuccessful() && response.body() != null) {
                    final String result = response.body().string();
                    Log.d("test", "Response: " + result);
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> analysisResult.setText(result));
                    }
                } else {
                    Log.e("test", "Invalid response");
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(() -> {
                            Toast.makeText(getActivity(), "Error in response!", Toast.LENGTH_SHORT).show();
                            analysisResult.setText("Invalid response from the server");
                        });
                    }
                }
            }
        });
    }

    private String getRealPathFromURI(Uri uri){
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity(), uri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int col_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(col_index);
        cursor.close();
        return result;
    }

//    public void openGallery(){
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, GALLERY_REQUEST_CODE);
//    }

//    @SuppressLint("IntentReset")
//    private void openGallery() {
//        @SuppressLint("IntentReset") Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image/*");
//        startActivityForResult(intent, REQUEST_CODE_PERMISSION);
//    }

        private void openGallery() {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 0);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQUEST_CODE && data != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    if(Environment.isExternalStorageManager()){
                        imageUri = data.getData(); // save the image URI
                        image.setImageURI(data.getData());
                    }
                    else{
                        Toast.makeText(getActivity(), "Permission denied in onActivityResult", Toast.LENGTH_SHORT).show();
                        Log.e("test", "In else loop of onActivityResult where permission is denied");
                        Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                        startActivityForResult(intent, REQUEST_CODE_PERMISSION);
                    }
                }
//                imageUri = data.getData(); // save the image URI
//                image.setImageURI(data.getData());  // gets the image from data and display in imagePreview
            }
        }
    }
}