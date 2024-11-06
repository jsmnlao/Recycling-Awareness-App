package edu.sjsu.android.gradebook;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.sjsu.android.gradebook.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final String AUTHORITY = "dataprovider.jasminelao";
    private final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // attach methods implemented in class to the corresponding button in onCreate
        binding.add.setOnClickListener(this::addStudent);
        binding.get.setOnClickListener(this::getAllStudents);
    }

    /**
     * Adds student to the database
     * @param view
     */
    public void addStudent(View view){
        // wrap student's info in ContentValues object
        ContentValues values = new ContentValues();
        // put: col name as key, user input as value
        values.put("name", binding.studentName.getText().toString());
        values.put("grade", binding.studentGrade.getText().toString());
        // getContentResolver in Activity class to get the obj for communicating
        //with accessible content provider -- StudentsProvider
        if(getContentResolver().insert(CONTENT_URI, values) != null){
            // toast a success message
            Toast.makeText(this, "Student information added!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getAllStudents(View view){
        // sort by student name
        try (Cursor c = getContentResolver().query(CONTENT_URI, null, null, null, "name")){
            if(c.moveToFirst()){
                String result = "Jasmine Lao's Gradebook: \n";
                do{
                    for(int i = 0; i < c.getColumnCount(); i++){
                        result = result.concat(c.getString(i) + "\t");
                    }
                    result = result.concat("\n");
                }
                while(c.moveToNext());
                binding.result.setText(result);
            }
        }
    }

}