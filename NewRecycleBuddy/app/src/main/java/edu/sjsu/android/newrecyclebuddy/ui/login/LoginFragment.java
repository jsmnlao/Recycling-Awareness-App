package edu.sjsu.android.newrecyclebuddy.ui.login;

import static android.content.ContentValues.TAG;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

import edu.sjsu.android.newrecyclebuddy.databinding.FragmentLoginBinding;

import edu.sjsu.android.newrecyclebuddy.R;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);
        TextView dont_have_account = view.findViewById(R.id.dont_have_account_text);
        dont_have_account.setOnClickListener(this::navigateToSignup);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
//        final ProgressBar loadingProgressBar = binding.loading;

        loginViewModel.getLoginFormState().observe(getViewLifecycleOwner(), new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
//                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
//                    updateUiWithUser(loginResult.getSuccess());
                    onClickCustom(view);
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) { // if checkmark clicked on keyboard, still do validation
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("userbase")
                            .whereEqualTo("Email", usernameEditText.getText().toString())
                            .whereEqualTo("Password", passwordEditText.getText().toString())
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        if (task.getResult().isEmpty()) {
                                            Toast.makeText(requireContext(), "Email or password incorrect, please try again.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                Log.d("login", document.getId() + " => " + document.getData());
                                                loginViewModel.login(usernameEditText.getText().toString(),
                                                        passwordEditText.getText().toString());
                                            }
                                        }
                                    } else {
                                        Log.d("login", "Error getting documents: ", task.getException());
                                    }
                                }
                            });
                    return true;
                }
                return false;
            }
        });

        // login button user validation
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("userbase")
                        .whereEqualTo("Email", usernameEditText.getText().toString())
                        .whereEqualTo("Password", passwordEditText.getText().toString())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    // if user doesn't exist in database
                                    if (task.getResult().isEmpty()){
                                        Toast.makeText(requireContext(), "Email or password incorrect, please try again.", Toast.LENGTH_SHORT).show();
                                    }
                                    // if user exists, log in
                                    else{
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d("login", document.getId() + " => " + document.getData());
                                            // log in
                                            loginViewModel.login(usernameEditText.getText().toString(),
                                                    passwordEditText.getText().toString());
                                        }
                                    }
                                } else {
                                    Log.d("login", "Error getting documents: ", task.getException());
                                }
                            }
                        });
            }
        });
    }

    public void onClickCustom(View v) {
        Log.d("test", "Navigating to home fragment");
        try {
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_loginFragment_to_homeFragment);
        } catch (Exception e) {
            Log.e("test", "Failed to navigate: " + e.getMessage());
        }
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        }
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(
                    getContext().getApplicationContext(),
                    errorString,
                    Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToSignup(View view) {
        NavController controller = Navigation.findNavController(view);
        controller.navigate(R.id.action_loginFragment_to_signupFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}