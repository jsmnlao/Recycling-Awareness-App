package edu.sjsu.android.newrecyclebuddy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CommunityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CommunityFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CommunityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommunityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommunityFragment newInstance(String param1, String param2) {
        CommunityFragment fragment = new CommunityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //fetchAndDisplayTopUsers();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        fetchAndDisplayTopUsers(view);
        return view;

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
}