package edu.sjsu.android.newrecyclebuddy;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.OffsetDateTime;
import java.util.Objects;

import edu.sjsu.android.newrecyclebuddy.retrofit.AppUserApi;
import edu.sjsu.android.newrecyclebuddy.retrofit.RetrofitService;
import edu.sjsu.android.newrecyclebuddy.springmodels.AppUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        Button loginButton = view.findViewById(R.id.login_button);

        // TODO: Rename those components to be more readable
        EditText editTextName = view.findViewById(R.id.editTextText);
        EditText editTextEmail = view.findViewById(R.id.editTextTextEmailAddress);
        EditText editTextPassword = view.findViewById(R.id.editTextTextPassword);

        // For sending user information inputted in the signup form
        RetrofitService retrofitService = new RetrofitService();
        AppUserApi appUserApi = retrofitService.getRetrofit().create(AppUserApi.class);



        signupButton.setOnClickListener(v -> {
            // Retrieve the data from the text fields when the signup button is clicked
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();

            AppUser appUser = new AppUser();
            appUser.setName(name);
            appUser.setEmail(email);
            appUser.setPassword(password);
            OffsetDateTime now = OffsetDateTime.now();
            appUser.setRegistration(now);

            // enqueue the post request to prevent unresponsiveness while data is being sent thru the server
            appUserApi.save(appUser).enqueue(new Callback<AppUser>() {
                @Override
                public void onResponse(Call<AppUser> call, Response<AppUser> response) {
                    Toast.makeText(requireContext(), "Sign up successful! Hi, " + appUser.getName() + ".", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<AppUser> call, Throwable throwable) {
                    Toast.makeText(requireContext(), "Sign up failed, please try again.", Toast.LENGTH_SHORT).show();
                    Log.d("test", Objects.requireNonNull(throwable.getMessage()));
                }
            });

            // Log the values (for testing purposes)
            Log.d("test", "Name: " + name + ", Email: " + email + ", Password: " + password + ", Time: " + appUser.getRegistration());
        });

        loginButton.setOnClickListener(this::onClick);
        return view;
    }

    public void onClick(View v) {
        Log.d("test", "clicked recyclebuddy/signUpFragment");
        NavController navController = Navigation.findNavController(v);
        navController.navigate(R.id.action_signupFragment_to_loginFragment);
    }
}