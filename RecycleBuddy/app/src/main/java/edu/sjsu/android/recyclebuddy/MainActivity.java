package edu.sjsu.android.recyclebuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("test", "MainActivity started");
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    private void openInfoActivity(){
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);
    }
}