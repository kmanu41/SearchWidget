package com.example.doctor.searchwidget;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class StudentRepo {
    private DBHelper dbHelper;


    public StudentRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public Cursor  getStudentListByKeyword(String search) {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //String selectQuery =  "SELECT  rowid as " +
          //      Student.KEY_ROWID + "," +
            //    Student.KEY_ID + "," +
              //  Student.KEY_name + "," +
         //       Student.KEY_email + "," +
           //     Student.KEY_age +
             //   " FROM " + Student.TABLE +
         //       " WHERE " +  Student.KEY_name + "  LIKE  '%" +search + "%' "
           //     ;
        Cursor cursor=db.query("dictionary",new String []{"_id","Vocab","Favorite"},"Vocab LIKE ?", new String[]{"%"+search+"%"},null,null,null);

        //Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor == null) {
            return null;
        } else if (!cursor.moveToFirst()) {
            cursor.close();
            return null;
        }
        return cursor;


    }
}