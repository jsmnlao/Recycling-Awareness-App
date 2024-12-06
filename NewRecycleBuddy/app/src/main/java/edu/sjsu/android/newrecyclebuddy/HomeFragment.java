package edu.sjsu.android.newrecyclebuddy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.firestore.FirebaseFirestore;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        Log.d("test", "onCreateView in HomeFragment");
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Fetch the current user's name
        // Access SharedPreferences to retrieve user details
        fetchAndDisplayUserName();

//        logoutText.setOnClickListener(this::logout);
        return view;
    }

    // update home page name to current user's name
    public void fetchAndDisplayUserName() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("userEmail", null);

        if (email != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("userbase").document(email)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String name = documentSnapshot.getString("Name");
                            TextView userNameTextView = getView().findViewById(R.id.user_name);
                            if (userNameTextView != null) {
                                userNameTextView.setText(name); // Update the UI with the latest data
                            }
                        }
                    })
                    .addOnFailureListener(e -> Log.e("Firestore", "Error fetching user data: " + e.getMessage()));
        }
    }

    private void logout(View view) {
        Log.d("test", "clicked logout button in home fragment");
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_openingFragment);
    }
}