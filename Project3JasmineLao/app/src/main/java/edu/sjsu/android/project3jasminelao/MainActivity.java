package edu.sjsu.android.project3jasminelao;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu); // Resource menu: R.menu.menu
        return super.onCreateOptionsMenu(menu); // Parameter menu
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.unin){
            // TODO: implement uninstall
            Log.d("hello", "Uninstall clicked");
            return true;
        }
        else if (item.getItemId() == R.id.info){
            // TODO: go to info page
            Log.d("hello", "Information clicked");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}