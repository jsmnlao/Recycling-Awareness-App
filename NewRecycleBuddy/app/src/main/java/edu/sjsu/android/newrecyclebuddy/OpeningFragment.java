package edu.sjsu.android.newrecyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OpeningFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpeningFragment extends Fragment {


    public OpeningFragment() {
        // Required empty public constructor
    }

    public static OpeningFragment newInstance(String param1, String param2) {
        OpeningFragment fragment = new OpeningFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_opening, container, false);
        Button getStartedButton = view.findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(this::onGetStartedClicked);
        return view;
    }

    public void onGetStartedClicked(View view) {
        Log.d("test", "clicked opening fragment");
        Navigation.findNavController(view).navigate(R.id.action_openingFragment_to_signupFragment);
    }
}