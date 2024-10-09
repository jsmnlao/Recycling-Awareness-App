package edu.sjsu.android.exercise3jasminelao;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainFragment extends Fragment {
    private String data;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            data = getArguments().getString("myText");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
        
    }

    public void onClick(View view){
        // let data be Amazon's homepage
        Uri website = Uri.parse("http://www.amazon.com");
        // create an implicit Intent with a specific action (view) and the data
        Intent implicit =  new Intent(Intent.ACTION_VIEW, website);
        // create a chooser for the implicit Intent so that user can choose the app to perform action
        Intent choose = Intent.createChooser(implicit, "Load Amazon.com with: ");
        // use resultLauncher from MainActivity to start the Intent to get result back
        MainActivity.resultLauncher.launch(choose); // demonstrates how to explicitly set an app chooser
    }
}