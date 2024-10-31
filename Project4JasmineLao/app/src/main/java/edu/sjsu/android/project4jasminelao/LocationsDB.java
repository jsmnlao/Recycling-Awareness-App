package edu.sjsu.android.project4jasminelao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocationsDB extends SQLiteOpenHelper {

    protected final static String DATABASE_NAME = "LocationsDB";
    protected final static String TABLE_NAME = "Locations";
    protected final static String ID = "_id";
    protected final static String LAT = "latitude";
    protected final static String LONG = "longitude";
    protected final static String ZOOM = "zoom_level";
    // protected: other classes under same package can access them

    private static final String CREATE_TABLE =
            String.format("CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s DOUBLE NOT NULL," +
            "%s DOUBLE NOT NULL," +
            "%s FLOAT NOT NULL);", TABLE_NAME, ID, LAT, LONG, ZOOM);

    public LocationsDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE); // call method to execute create_table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insert(ContentValues contentValues){
        SQLiteDatabase database = getWritableDatabase(); // open the database and return it to you
        return database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllLocations() { //cursor reads data table row by row
        SQLiteDatabase database = getWritableDatabase();
        return database.query(TABLE_NAME, // which table you want to query from
                new String[]{ID, LAT, LONG, ZOOM}, // columns you want to query
                null, null, null, null, null); // want everything, no selection/argument
    }


    public int deleteAllAllocations(){
        SQLiteDatabase database = getWritableDatabase();
        return database.delete(TABLE_NAME, null, null); // deleting everything
    }
}
