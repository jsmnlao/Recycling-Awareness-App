package edu.sjsu.android.project2jasminelao;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.Surface;

public class MyListener implements SensorEventListener {

    private final Display display;
    private float x, y;
    private long timestamp;

    public MyListener(Context context){
        // TODO: initialize display object using context object passed in
        display = ((DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE)).getDisplay(Display.DEFAULT_DISPLAY);
        x = 0f;
        y = 0f;
        timestamp = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        timestamp = sensorEvent.timestamp;

        int rotation = display.getRotation();
        if(rotation == Surface.ROTATION_0){
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
        }
        else if(rotation == Surface.ROTATION_90){
            x = -sensorEvent.values[1];
            y = sensorEvent.values[0];
        }
        else if(rotation == Surface.ROTATION_180){
            x = -sensorEvent.values[0];
            y = -sensorEvent.values[1];
        }
        else if(rotation == Surface.ROTATION_270){
            x = sensorEvent.values[1];
            y = -sensorEvent.values[0];
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public float getX(){
        return x;
    }

    public float getY() {
        return y;
    }

    public long getTimestamp(){
        return timestamp;
    }
}
