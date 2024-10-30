package edu.sjsu.android.dataprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;

import java.net.URI;
import java.util.Objects;

public class StudentsProvider extends ContentProvider {

    private StudentsDB database;

    @Override
    /**
     * used to insert a new row of data
     * implement by calling the insert method in StudentsDB to insert data
     * URI returned is for newly inserted row
     * need to call notifyChange using new URI
     */
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = database.insert(values);
        // if row is added successfully
        if(rowID > 0){
            Uri _uri = ContentUris.withAppendedId(uri, rowID);
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public boolean onCreate() {
        database = new StudentsDB(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
       // if not specified, sort by ID
        sortOrder = sortOrder == null ? "_id" : sortOrder;
        return database.getAllStudents(sortOrder);
    }


    //***** don't need to implement methods below *****//

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}