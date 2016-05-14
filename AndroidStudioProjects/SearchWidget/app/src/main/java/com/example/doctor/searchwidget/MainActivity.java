package com.example.doctor.searchwidget;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CustomAdapter customAdapter;
    ListView listView;
    public Cursor cursor;
    public StudentRepo studentRepo;
    private final static String TAG = MainActivity.class.getName().toString();

    private static final String TAG_BOOKMARKS="bookmarks";
    private static final String TAG_ABOUT_US="about";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SearchResult.class);
                intent.putExtra(SearchResult.EXTRA_NO, (int) id);
                startActivity(intent);
            }

        };
        //ListView listView=(ListView)findViewById(R.id.lstStudent);
        //listView.setOnItemClickListener(itemClickListener);
        //studentRepo = new StudentRepo(this);
        //cursor = studentRepo.getStudentList();
        //customAdapter = new CustomAdapter(MainActivity.this, cursor, 0);
       // listView = (ListView) findViewById(R.id.lstStudent);
        // listView.setAdapter(customAdapter);

        //Testing

        listView = (ListView) findViewById(R.id.lstStudent);
        listView.setOnItemClickListener(itemClickListener);
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        List<String> quotes = databaseAccess.getQuotes();
        databaseAccess.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quotes);
        this.listView.setAdapter(adapter);
        //Done Testing


        ImageView imageView=new ImageView(this);
        imageView.setImageResource(R.drawable.button_action);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(imageView)
                .build();
        ImageView Bookmarks=new ImageView(this);
        Bookmarks.setImageResource(R.drawable.button_action);
        ImageView Aboutus=new ImageView(this);
        Aboutus.setImageResource(R.drawable.button_action);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        SubActionButton buttonbookmark = itemBuilder.setContentView(Bookmarks).build();
        SubActionButton buttonaboutus = itemBuilder.setContentView(Aboutus).build();
        buttonbookmark.setOnClickListener(this);
        buttonaboutus.setOnClickListener(this);

        buttonbookmark.setTag(TAG_BOOKMARKS);
        buttonaboutus.setTag(TAG_ABOUT_US);


        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonbookmark)
                .addSubActionView(buttonaboutus)
                .attachTo(actionButton)
                .build();



    }



    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    ///@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);


      // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                    cursor = studentRepo.getStudentListByKeyword(s);
                    if (cursor == null) {
                        Toast.makeText(MainActivity.this, "No records found!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, cursor.getCount() + " records found!", Toast.LENGTH_LONG).show();
                    }
                    customAdapter.swapCursor(cursor);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    cursor = studentRepo.getStudentListByKeyword(s);
                    if (cursor != null) {
                        customAdapter.swapCursor(cursor);
                    }
                    return false;
                }

            });



        return true;

    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals(TAG_BOOKMARKS)){
            startActivity(new Intent(this,Bookmarks.class));
        }
        if (v.getTag().equals(TAG_ABOUT_US)){

        }

    }
}




