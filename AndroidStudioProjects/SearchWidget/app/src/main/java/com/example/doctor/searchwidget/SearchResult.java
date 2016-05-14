package com.example.doctor.searchwidget;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResult extends AppCompatActivity {
    public static final String EXTRA_NO = "idNumber";
    private SQLiteDatabase db;
    private DBHelper SQL;

    StudentRepo studentRepo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        int idNumber = (Integer) getIntent().getExtras().get(EXTRA_NO);


        try {
            SQLiteOpenHelper SQL = new DBHelper(this);
            db = SQL.getWritableDatabase();
            //Testing
            Cursor cursor = db.query("dictionary", new String[]{"Vocab", "Meaning", "Favorite"}, "_id=?",
                    new String[]{String.valueOf(idNumber)},null,null,null,null);


            //Testing Done


            //String selectQuery = "SELECT " +
              //      Student.KEY_ID + "," +
                //    Student.KEY_name + "," +
                  //  Student.KEY_email + "," +
             //       Student.KEY_age +
               //     " FROM " + Student.TABLE
                 //   + " WHERE " +
                   // Student.KEY_ID + "=?";
            //Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(idNumber)});
            //Student student = new Student();
            dictionarysample Dictionarysample=new dictionarysample();
            if (cursor.moveToFirst()) {
                Dictionarysample.vocab= cursor.getString(0);
                Dictionarysample.meaning= cursor.getString(1);
                //Dictionarysample.favorite= cursor.getString(2);
                Boolean isFavorite=(cursor.getInt(2)==1);
                //student.name = cursor.getString(cursor.getColumnIndex(Student.KEY_name));
                //student.email = cursor.getString(cursor.getColumnIndex(Student.KEY_email));
                //student.age = cursor.getInt(cursor.getColumnIndex(Student.KEY_age));
                TextView nam = (TextView) findViewById(R.id.sName);
                nam.setText(Dictionarysample.vocab);
                //nam.setText(student.name);
                TextView age = (TextView) findViewById(R.id.sAge);
                age.setText(Dictionarysample.meaning);
                //age.setText(String.valueOf(student.age));
                TextView emailid = (TextView) findViewById(R.id.sEmailid);
                //emailid.setText(String.valueOf(Dictionarysample.favorite));
                //emailid.setText(student.email);
                CheckBox favorite=(CheckBox)findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);

            }
            cursor.close();
            db.close();

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavail", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    public void onFavoriteClicked(View view){
        int idNumber = (Integer) getIntent().getExtras().get(EXTRA_NO);
CheckBox favorite=(CheckBox)findViewById(R.id.favorite);
        ContentValues contentValues=new ContentValues();
        contentValues.put("Favorite",favorite.isChecked());
        SQLiteOpenHelper SQL = new DBHelper(SearchResult.this);

        try {
            SQLiteDatabase db = SQL.getWritableDatabase();
            db.update("dictionary", contentValues, "_id=?", new String[]{String.valueOf(idNumber)});
            db.close();

        }catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



}
