package edu.sjsu.android.mybrowser;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Intent and set TextView
        String url = getIntent().getDataString();
        ((TextView) findViewById(R.id.website)).setText(url);

        // Return result if user clicks Return button, just returning result, not sending data
        findViewById(R.id.back).setOnClickListener(view -> {
            // set activity's result to RESULT_OK
            setResult(RESULT_OK);
            // finish and close current activity
            finish();
        });

    }
}