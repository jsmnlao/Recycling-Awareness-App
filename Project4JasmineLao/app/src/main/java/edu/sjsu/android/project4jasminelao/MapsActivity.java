package edu.sjsu.android.project4jasminelao;

import androidx.fragment.app.FragmentActivity;

import android.content.ContentValues;
import android.net.Uri;
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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

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
        getContentResolver().insert(CONTENT_URI, values);
    }

    private void deleteAllLocations(){
        mMap.clear();
        getContentResolver().delete(CONTENT_URI, null, null);
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