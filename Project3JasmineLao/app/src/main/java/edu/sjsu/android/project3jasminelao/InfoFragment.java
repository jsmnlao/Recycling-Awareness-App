package edu.sjsu.android.project3jasminelao;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class InfoFragment extends Fragment {

    public InfoFragment() {
        // Required empty public constructor
    }
    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        view.findViewById(R.id.tel_btn).setOnClickListener(this::onClick);
        return view;
    }

    private void onClick(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse(getString(R.string.tel_415_123_4567));
        intent.setData(data);  // set the intent data to the phone number
        startActivity(intent); // start the intent
    }

}