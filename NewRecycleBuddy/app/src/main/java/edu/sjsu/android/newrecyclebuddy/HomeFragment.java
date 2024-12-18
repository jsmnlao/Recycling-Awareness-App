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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeFragment extends Fragment {
    private TextView logoutButton;

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
        logoutButton = view.findViewById(R.id.logout_text);
        logoutButton.setOnClickListener(this::logout);

        // Fetch the current user's name
        // Access SharedPreferences to retrieve user details
        fetchAndDisplayUserName();
        fetchAndDisplayUserStats(view);
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

    public void fetchAndDisplayUserStats(View view) {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("userEmail", null);
        if (email != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(email);
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    UserStats userStats = documentSnapshot.toObject(UserStats.class);
                    TextView plasticCount = view.findViewById(R.id.plastic_count);
                    TextView metalCount = view.findViewById(R.id.metal_count);
                    TextView glassCount = view.findViewById(R.id.glass_count);
                    TextView cardboardCount = view.findViewById(R.id.cardboard_count);
                    TextView paperCount = view.findViewById(R.id.paper_count);
                    plasticCount.setText(String.valueOf(userStats.getPlastic_count()));
                    metalCount.setText(String.valueOf(userStats.getMetal_count()));
                    glassCount.setText(String.valueOf(userStats.getGlass_count()));
                    cardboardCount.setText(String.valueOf(userStats.getCardboard_count()));
                    paperCount.setText(String.valueOf(userStats.getPaper_count()));
                }
            });
        }

    }

    private void logout(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_loginFragment);
    }
}