package edu.sjsu.android.project2jasminelao;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.view.Display;

public class MyListener implements SensorEventListener {

    private Display display;
    private float x, y;
    private long timestamp;

    public MyListener(Context context){
        // TODO: initialize display object using context object passed in
        x = 0f;
        y = 0f;
        timestamp = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // TODO: implement on 9/19
        /*
        Set x-axis, y-axis, and timestamp value based on the sensor data. Remember to interpret the
        data based on different orientations (0, 90, 180, 270) of the screen. Lesson 8 page 17 gives the
        pseudocode for rotation 0 and 90 as examples.
         */
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
