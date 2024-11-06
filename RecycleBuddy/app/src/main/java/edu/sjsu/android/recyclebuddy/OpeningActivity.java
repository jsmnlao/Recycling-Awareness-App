package edu.sjsu.android.recyclebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OpeningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("test", "On Create in OpeningActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        Button getStartedButton = findViewById(R.id.get_started_button);
        getStartedButton.setOnClickListener(v -> {
            Log.d("test", "get started button clicked");
            Intent intent = new Intent(OpeningActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        Log.d("test", "clicked OpeningActivity");
    }
}