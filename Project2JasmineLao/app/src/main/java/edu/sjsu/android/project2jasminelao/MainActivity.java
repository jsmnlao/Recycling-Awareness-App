package edu.sjsu.android.project2jasminelao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Sensor sensor;
    private SensorManager manager;
    private MyView view;
    // TODO: set MyView

    @SuppressLint("ServiceCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        manager = (SensorManager) getSystemService(Context.SEARCH_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        // If accelerator does not exist, toast message and stop the method
        if(sensor != null){
            Toast.makeText(this, "No sensor found", Toast.LENGTH_LONG).show();
            return;
        }

        // TODO: set content view to a myView object (9/24)
        view = new MyView();
        setContentView(R.layout.activity_main);
        /*
        • Implement onCreate
        o Initialize the SensorManager object and the Sensor object (type is accelerometer). If the
        accelerometer doesn’t exist, toast a message and stop the method.
        o Initialize the MyView object by passing in “this” as Activity is a subclass of Context.
        o Set the content view to the MyView object instead of activity_main.
        • Register and unregister the listener (get the MyListener object from the MyView object) in
        appropriate lifecycle methods
         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        // non-static method --> listener, sensor object, delay time - static variable
        manager.registerListener(view.getListener(), sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onStop(){
        super.onStop();
        manager.unregisterListener(view.getListener());
    }
}
