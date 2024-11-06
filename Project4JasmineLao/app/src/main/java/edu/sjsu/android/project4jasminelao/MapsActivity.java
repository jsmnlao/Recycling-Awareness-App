package edu.sjsu.android.project4jasminelao;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Camera;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.sjsu.android.project4jasminelao.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LoaderManager.LoaderCallbacks<Cursor>{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private final LatLng LOCATION_UNIV = new LatLng(37.335371, -121.881050);
    private final LatLng LOCATION_CS = new LatLng(37.333714, -121.881860);

    private final Uri CONTENT_URI = Uri.parse("content://edu.sjsu.android.project4JasmineLao");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LoaderManager.getInstance(this).restartLoader(0,null, this);

        binding.city.setOnClickListener(this::switchView);
        binding.univ.setOnClickListener(this::switchView);
        binding.cs.setOnClickListener(this::switchView);
//        binding.location.setOnClickListener(this::getLocation);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this::addLocation); // short click
        mMap.setOnMapLongClickListener(p -> deleteAllLocations()); // long click
    }

    private void addLocation(LatLng point){
        mMap.addMarker(new MarkerOptions().position(point));
        ContentValues values = new ContentValues();
        values.put(LocationsDB.LAT, point.latitude);   // from the point
        values.put(LocationsDB.LONG, point.longitude); // from the point
        values.put(LocationsDB.ZOOM, mMap.getCameraPosition().zoom); // zoom level is from the camera position, from map object
        new MyTask().execute(values);
//        getContentResolver().insert(CONTENT_URI, values);
    }

    private void deleteAllLocations(){
        mMap.clear();
//        getContentResolver().delete(CONTENT_URI, null, null);
        new MyTask().execute();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        // runs in the background to load the cursor
        return new CursorLoader(this, CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        // takes cursor as parameter and returned from oncreate
        // runs on the main thread
        if(cursor != null && cursor.moveToFirst()){ // move to first row
            // retrieve data row by row
            float zoom;
            LatLng point;
            do{
                // takes index of col based on the column name
                double lat = cursor.getDouble(cursor.getColumnIndexOrThrow(LocationsDB.LAT));
                double lng = cursor.getDouble(cursor.getColumnIndexOrThrow(LocationsDB.LONG));
                zoom = cursor.getFloat(cursor.getColumnIndexOrThrow(LocationsDB.ZOOM));
                point = new LatLng(lat, lng);
                // based on the values you get, you add a marker to it
                mMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)));

            } while(cursor.moveToNext());
            // camera will focus on the last point being added
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(point, zoom);
            mMap.moveCamera(update);
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        // can keep this method empty
    }

    class MyTask extends AsyncTask<ContentValues, Void, Void> {
        @Override
        protected Void doInBackground(ContentValues... values) {
            // if there is a values object passed in, insert
            if(values != null && values.length > 0 && values[0] != null) {
                getContentResolver().insert(CONTENT_URI, values[0]);
            }
            else{
                getContentResolver().delete(CONTENT_URI, null, null);
            }
            // else, delete
            return null;
        }


    }

    private void switchView(View view) {
        CameraUpdate update = null;
        if (view.getId() == R.id.city) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            update = CameraUpdateFactory.newLatLngZoom(LOCATION_UNIV, 10f);
        } else if (view.getId() == R.id.univ) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            update = CameraUpdateFactory.newLatLngZoom(LOCATION_UNIV, 14f);
        } else {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            update = CameraUpdateFactory.newLatLngZoom(LOCATION_CS, 18f);
        }
        mMap.animateCamera(update);
    }
}