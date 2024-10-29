package edu.sjsu.android.project4jasminelao;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class TempActivity extends AppCompatActivity {

    Uri uri = Uri.parse("content://edu.sjsu.android.project4JasmineLao");


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        findViewById(R.id.deleteBtn).setOnClickListener(this::delete);
        findViewById(R.id.insertBtn).setOnClickListener(this::insert);
        findViewById(R.id.locationBtn).setOnClickListener(v -> {new GPSTracker(this).getLocation();});
    }

    void delete(View v){
        getContentResolver().delete(uri, null, null);
    }

    void insert(View v){
        ContentValues values = new ContentValues();
        values.put(LocationsDB.LAT, 1.0);
        values.put(LocationsDB.LONG, 2.0);
        values.put(LocationsDB.ZOOM, 3.0f);

        getContentResolver().insert(uri, values);
    }
}