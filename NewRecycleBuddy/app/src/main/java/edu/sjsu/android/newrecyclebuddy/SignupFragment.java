package edu.sjsu.android.newrecyclebuddy;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SignupFragment extends Fragment {

    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        Button signupButton = view.findViewById(R.id.signup_button);
        TextView already_have_account = view.findViewById(R.id.already_have_account_text);
        already_have_account.setOnClickListener(this::navigateToLogin);
//        Button loginButton = view.findViewById(R.id.login_button);

        EditText editTextNameVar = view.findViewById(R.id.editTextName);
        EditText editTextEmailVar = view.findViewById(R.id.editTextEmail);
        EditText editTextPasswordVar = view.findViewById(R.id.editTextPassword);


        signupButton.setOnClickListener(v -> {
            // Retrieve the data from the text fields when the signup button is clicked
            String name = editTextNameVar.getText().toString();
            String email = editTextEmailVar.getText().toString();
            String password = editTextPasswordVar.getText().toString();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required.", Toast.LENGTH_SHORT).show();
                return;
            }

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Check if the email already exists
            db.collection("userbase")
                    .document(email)
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // Email already exists
                                Toast.makeText(requireContext(), "Email already in use. Please log in or use a different email.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Proceed with signup
                                Map<String, Object> user = new HashMap<>();
                                user.put("Name", name);
                                user.put("Email", email);
                                user.put("Password", password); // Consider hashing passwords in production
                                user.put("Registration", OffsetDateTime.now());

                                db.collection("userbase")
                                        .document(email)
                                        .set(user)
                                        .addOnSuccessListener(aVoid -> {
                                            Log.d(TAG, "DocumentSnapshot successfully written!");
                                            Toast.makeText(requireContext(), "Sign up successful! Hi, " + user.get("Name") + ".", Toast.LENGTH_SHORT).show();
                                            NavController navController = Navigation.findNavController(v);
                                            navController.navigate(R.id.action_signupFragment_to_loginFragment);
                                        })
                                        .addOnFailureListener(e -> Log.w(TAG, "Error writing document", e));
                            }
                        } else {
                            Log.e(TAG, "Error checking email existence: ", task.getException());
                            Toast.makeText(requireContext(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
                        }
                    });
        });

        return view;
    }

    private void navigateToLogin(View view) {
        NavController controller = Navigation.findNavController(view);
        controller.navigate(R.id.action_signupFragment_to_loginFragment);
    }

    public void onClick(View v) {
        Log.d("test", "clicked recyclebuddy/signUpFragment");
        NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.action_signupFragment_to_loginFragment);
    }
}