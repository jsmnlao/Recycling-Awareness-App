package edu.sjsu.android.project4jasminelao;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import java.util.Objects;


public class LocationsProvider extends ContentProvider {
    private LocationsDB locationsDB;

    public LocationsProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return locationsDB.deleteAllAllocations();
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = locationsDB.insert(values);
        // if record is added successfully
        if(rowID > 0){
            Uri _uri = ContentUris.withAppendedId(uri, rowID);
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record info " + uri);
    } 

    @Override
    public boolean onCreate() {
        locationsDB = new LocationsDB(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return locationsDB.getAllLocations();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}