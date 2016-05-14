package com.example.doctor.searchwidget;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Bookmarks extends AppCompatActivity {

    private SQLiteDatabase db;
    private DBHelper SQL;
    private Cursor favoritesCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        ListView listView=(ListView)findViewById(R.id.list_favorite);
        try{
            SQLiteOpenHelper SQL = new DBHelper(this);
            db = SQL.getWritableDatabase();
            favoritesCursor=db.query("dictionary", new String[]{"_id", "Vocab","Favorite"}, "Favorite=1", null, null, null, null);
            CursorAdapter favoriteAdapter=new SimpleCursorAdapter(Bookmarks.this,android.R.layout.simple_list_item_1,favoritesCursor
            ,new String[]{"Vocab"},new int[]{android.R.id.text1},0);
            listView.setAdapter(favoriteAdapter);
        }catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {@Override
        public void onItemClick(AdapterView<?>listView,View v,int position,long id ){
                                                Intent intent=new Intent(Bookmarks.this,SearchResult.class);
                                               intent.putExtra(SearchResult.EXTRA_NO,(int)id);
                                               startActivity(intent);
                                            }
                                        }
        );


    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }

}
