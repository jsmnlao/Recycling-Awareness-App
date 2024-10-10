package edu.sjsu.android.exercise3jasminelao;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TextFragment extends Fragment {
    private NavController controller;

    public TextFragment() {
        // Required empty public constructor
    }


    public static TextFragment newInstance(String param1, String param2) {
        TextFragment fragment = new TextFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        // set NavController
        controller = NavHostFragment.findNavController(this);
        // attach onClickListener
        view.findViewById(R.id.confirm).setOnClickListener(this::onClick);
        view.findViewById(R.id.cancel).setOnClickListener(this::onClick);
        return view;
    }

    public void onClick(View view){
        Bundle bundle = new Bundle();
        // set data based on what Button is being clicked
        if(view.getId() == R.id.confirm){
            bundle.putString("myText", "Jasmine Lao");
        }
        else if(view.getId() == R.id.cancel){
            bundle.putString("myText", "Cancelled");
        }
        // navigate to MainFragment with data bundle
        controller.navigate(R.id.action_textFragment_to_mainFragment, bundle);
    }
}