package edu.sjsu.android.newrecyclebuddy;

import android.annotation.SuppressLint;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

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
//        TextView logoutText = view.findViewById(R.id.logout_text);
//        logoutText.setOnClickListener(this::logout);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("users").document("User1");
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
        return view;
    }

    private void logout(View view) {
        Log.d("test", "clicked logout button in home fragment");
        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_openingFragment);
    }
}