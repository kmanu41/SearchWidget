package com.example.doctor.searchwidget;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResult extends AppCompatActivity {
    public static final String EXTRA_NO = "idNumber";
    private SQLiteDatabase db;

    StudentRepo studentRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        int idNumber = (Integer) getIntent().getExtras().get(EXTRA_NO);


        try {
            SQLiteOpenHelper dbHelper = new DBHelper(this);
            db = dbHelper.getReadableDatabase();
            String selectQuery = "SELECT " +
                    Student.KEY_ID + "," +
                    Student.KEY_name + "," +
                    Student.KEY_email + "," +
                    Student.KEY_age +
                    " FROM " + Student.TABLE
                    + " WHERE " +
                    Student.KEY_ID + "=?";
            Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idNumber)});
            Student student = new Student();
            if (cursor.moveToFirst()) {
                student.student_ID = cursor.getInt(cursor.getColumnIndex(Student.KEY_ID));
                student.name = cursor.getString(cursor.getColumnIndex(Student.KEY_name));
                student.email = cursor.getString(cursor.getColumnIndex(Student.KEY_email));
                student.age = cursor.getInt(cursor.getColumnIndex(Student.KEY_age));
                TextView nam = (TextView) findViewById(R.id.sName);
                nam.setText(student.name);
                TextView age = (TextView) findViewById(R.id.sAge);
                age.setText(String.valueOf(student.age));
                TextView emailid = (TextView) findViewById(R.id.sEmailid);
                emailid.setText(student.email);


            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavail", Toast.LENGTH_SHORT);
            toast.show();
        }


    }



}
