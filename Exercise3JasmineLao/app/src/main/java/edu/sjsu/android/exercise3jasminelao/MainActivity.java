package edu.sjsu.android.exercise3jasminelao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    static ActivityResultLauncher<Intent> resultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Implement the result launcher
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), // launch an activity
                result -> {
                    if(result.getResultCode() == RESULT_OK)
                        // updates TextView in mainFragment to display "Website visited" text
                        ((TextView) findViewById(R.id.result)).setText("Website visited");
                });
    }
}