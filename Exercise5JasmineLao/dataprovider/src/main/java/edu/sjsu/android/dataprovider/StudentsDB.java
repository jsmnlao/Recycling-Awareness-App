package edu.sjsu.android.dataprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class manages a SQL database, makes it easy for StudentsProvider implementations,
 * especially for creating/upgrading the database so the application startup will not
 * be blocked with long-running database upgrades.
 */
public class StudentsDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentsDatabase";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "students";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String GRADE = "grade";

    static final String CREATE_TABLE =
            String.format("CREATE TABLE %s (" +
            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "%s TEXT NOT NULL, " +
            "%S TEXT NOT NULL);", TABLE_NAME, ID, NAME, GRADE);

    public StudentsDB(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    /**
     * called when database is first created, just create an empty table
     * by calling the method to executing the create table command
     */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    /**
     * called when database needs to be upgraded/version changed
     * don't need to implement because won't upgrade for this exercise
     * instead, it deletes old table and creates a new one
     */
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //****** implement 2 helper methods: insert and getAllStudents() *******//
    // implement them in this class instead of StudentsProvider because we need to use the table name
    // when getWritableDatabase is first called, database will open and cached
    // Call getWritableDatabase everytime you want to write to database

    public long insert(ContentValues contentValues){
        SQLiteDatabase database = getWritableDatabase();
        return database.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllStudents(String orderBy){
        SQLiteDatabase database = getWritableDatabase();
        return database.query(TABLE_NAME,
                new String[] {ID, NAME, GRADE},
                null, null, null, null, orderBy);

    }

}
