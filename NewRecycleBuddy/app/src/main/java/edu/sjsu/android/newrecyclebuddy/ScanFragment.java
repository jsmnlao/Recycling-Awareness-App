package edu.sjsu.android.newrecyclebuddy;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
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

        uploadImageButton.setOnClickListener(v -> openGallery()); // when upload button is clicked, gallery is opened
        analyzeButton.setOnClickListener(v -> analyzeImage());
        return view;
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

    public void openGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQUEST_CODE){
                assert data != null;
                imageUri = data.getData(); // save the image URI
                image.setImageURI(data.getData());  // gets the image from data and display in imagePreview
            }
        }
    }
}