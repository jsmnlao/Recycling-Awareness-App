package edu.sjsu.android.newrecyclebuddy;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommunityFragment extends Fragment {
    private TextView logoutButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CommunityFragment() {
        // Required empty public constructor
    }

    public static CommunityFragment newInstance(String param1, String param2) {
        CommunityFragment fragment = new CommunityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //fetchAndDisplayTopUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        logoutButton = view.findViewById(R.id.logout_text);
        logoutButton.setOnClickListener(this::logout);

        fetchAndDisplayUserName();
        fetchAndDisplayTopUsers(view);
        return view;

    }

    private void logout(View view) {
        Navigation.findNavController(view).navigate(R.id.action_nav_community_to_loginFragment);
    }

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

                    db.collection("users").document(email)
                    .get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            UserStats userStats = documentSnapshot.toObject(UserStats.class);
                            if (userStats != null) {
                                // Use the userStats object as needed
                                int total = userStats.getPlastic_count() +
                                        userStats.getPaper_count() +
                                        userStats.getMetal_count() +
                                        userStats.getGlass_count() +
                                        userStats.getCardboard_count();
                                // Proceed with using the 'total' variable
                                TextView userScoreTextView = getView().findViewById(R.id.user_score);
                                userScoreTextView.setText(String.valueOf(total));
                            }
                        }
                    })
                    .addOnFailureListener(e -> Log.e("Firestore", "Error fetching user data: " + e.getMessage()));
        }
    }

    private void fetchAndDisplayTopUsers(View rootView) {
        CollectionReference usersRef = db.collection("users");
        usersRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<UserStats> userList = new ArrayList<>();

                // Create User objects from Firestore data
                for (QueryDocumentSnapshot document : task.getResult()) {
                    UserStats user = document.toObject(UserStats.class);
                    String name = document.getString("name");
                    user.setTotal();
                    int total = user.getTotal();

                    if (name != null) {
                        userList.add(new UserStats(name, total));
                    }
                }

                // Sort users by total score (descending)
                Collections.sort(userList, (u1, u2) -> Integer.compare(u2.getTotal(), u1.getTotal()));

                // Display top 5 users
                displayTopUsers(rootView, userList);
            }
        });
    }

    private void displayTopUsers(View rootView, List<UserStats> userList) {
        int maxUsersToShow = 5;
        int[] nameIds = {
                R.id.first_place_name, R.id.second_place_name, R.id.third_place_name,
                R.id.fourth_place_name, R.id.fifth_place_name
        };
        int[] scoreIds = {
                R.id.first_place_score, R.id.second_place_score, R.id.third_place_score,
                R.id.fourth_place_score, R.id.fifth_place_score
        };

        for (int i = 0; i < maxUsersToShow && i < userList.size(); i++) {
            UserStats user = userList.get(i);

            // Set name
            TextView nameTextView = rootView.findViewById(nameIds[i]);
            nameTextView.setText(user.getName());

            // Set total score
            TextView scoreTextView = rootView.findViewById(scoreIds[i]);
            scoreTextView.setText(String.valueOf(user.getTotal()));
        }
    }

    /*
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

     */
}