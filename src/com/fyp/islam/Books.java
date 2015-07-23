package com.fyp.islam;

import java.util.ArrayList;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Books extends Activity {
	   

	private static final String DB_NAME = "iedatabase.db";
    private static final String TABLE_NAME = "books";
	private static final String BOOK_ID = "_id";
	private static final String BOOK_NAME = "b_name";
	private static final String BOOK_DETAIL = "book";
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> friends;
	DataBaseHelper dbOpenHelper;
	String Detail;
	EditText et;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dua_list);
        
        ActionBar ab = getActionBar();
        ab.setTitle("Boosks");
        ab.setDisplayHomeAsUpEnabled(true);
          dbOpenHelper = new DataBaseHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
               fillFreinds();
        setUpList();      
    }
    @Override
   	protected void onDestroy() {
   		// TODO Auto-generated method stub
   		super.onDestroy();
   		dbOpenHelper.close();
   	}
    
	private void setUpList() {
		listView = (ListView) findViewById(R.id.list);
		
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Books.this, R.layout.dua_list_item, R.id.dualist, friends);
				listView.setAdapter(adapter);
				
				et = (EditText) findViewById(R.id.d_search);
				et.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence c, int arg1, int arg2, int arg3) {
						// TODO Auto-generated method stub
						adapter.getFilter().filter(c);
					}
					
					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
							int arg3) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
								int position,long id) {
				String item = listView.getItemAtPosition(position).toString();
				
				Intent i = new Intent(Books.this, Dua_Detail.class);
				 Cursor ourCursor= database.query(TABLE_NAME,
						 					new String[] 
						 							{BOOK_DETAIL},
						 							null,null,null,null,
						 							BOOK_ID);
				ourCursor.moveToPosition(position);
				Detail=ourCursor.getString(0);
				ourCursor.close();
				i.putExtra("name", item);
				i.putExtra("detail", Detail);
				
				startActivity(i);
			}
		});
	}
	
		private void fillFreinds() {
		
		friends = new ArrayList<String>();
		Cursor friendCursor = database.query(TABLE_NAME,
											 new String[] 
											 {BOOK_ID, BOOK_NAME, BOOK_DETAIL},
											 null, null, null, null
											 , BOOK_ID);
		friendCursor.moveToFirst();
		if(!friendCursor.isAfterLast()) {
			do {
				String name = friendCursor.getString(1);
				
				friends.add(name);
				
			} while (friendCursor.moveToNext());
		}
		friendCursor.close();
	}
}