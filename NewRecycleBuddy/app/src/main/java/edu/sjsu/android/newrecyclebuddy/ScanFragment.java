package edu.sjsu.android.newrecyclebuddy;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ScanFragment extends Fragment {

    private ImageView imagePreview;
    private Button uploadImageButton;
    private Button analyzeButton;
    private final int GALLERY_REQUEST_CODE = 1;


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
        imagePreview = view.findViewById(R.id.preview_image);
        uploadImageButton = view.findViewById(R.id.upload_image_button);
        analyzeButton = view.findViewById(R.id.analyze_button);

        uploadImageButton.setOnClickListener(v -> openGallery()); // when upload button is clicked, gallery is opened
        return view;
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
                imagePreview.setImageURI(data.getData());  // gets the image from data and display in imagePreview
            }
        }
    }
}