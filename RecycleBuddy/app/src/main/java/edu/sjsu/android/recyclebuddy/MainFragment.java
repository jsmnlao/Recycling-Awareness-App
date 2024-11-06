package edu.sjsu.android.recyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends Fragment {


    public MainFragment() {
        // Required empty public constructor
    }


    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        Button getStartedButton = view.findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(this::onGetStartedClicked);
        return view;

    }

    public void onClick(View v){
        NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.action_openingFragment_to_signupFragment);
    }

    public void onGetStartedClicked(View view) {
        Log.d("test", "clicked recyclebuddy/mainFragment");
        Navigation.findNavController(view).navigate(R.id.action_openingFragment_to_signupFragment);

    }
}