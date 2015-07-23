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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class Dua extends Activity {
	   

	private static final String DB_NAME = "iedatabase.db";
   
	private static final String TABLE_NAME = "dua";
	private static final String DUA_ID = "_id";
	private static final String DUA_NAME = "dua_name";
	private static final String DUA_DETAIL = "dua";
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> mylist;
	
	EditText et;
	  DataBaseHelper dbOpenHelper;
		  String Detail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dua_list);
        ActionBar ab = getActionBar();
        ab.setTitle("Dua");
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
		
			final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Dua.this, R.layout.dua_list_item, R.id.dualist, mylist);
			listView.setAdapter(adapter);
			
			et = (EditText) findViewById(R.id.d_search);
			 

	        et.addTextChangedListener(new TextWatcher() {
	 
	            public void afterTextChanged(Editable arg0) {
	             
	            }
	 
	            @Override
	            public void beforeTextChanged(CharSequence arg0, int arg1,
	                    int arg2, int arg3) {
	                // TODO Auto-generated method stub
	            }
	 
	            @Override
	            public void onTextChanged(CharSequence cs, int arg1, int arg2,
	                    int arg3) {
	                // TODO Auto-generated method stub
	            	 adapter.getFilter().filter(cs); 
	            }

				
	        });
			
				listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
								int position,long id) {
				String item = listView.getItemAtPosition(position).toString();
				
				Intent i = new Intent(Dua.this, Dua_Detail.class);
				 Cursor ourCursor= database.query(TABLE_NAME,
						 					new String[] 
						 							{DUA_DETAIL},
						 							null,null,null,null,
						 							DUA_ID);
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
			
		mylist = new ArrayList<String>();
		Cursor friendCursor = database.query(TABLE_NAME,
											 new String[] 
											 {DUA_ID, DUA_NAME},
											 null, null, null, null
											 , DUA_ID);
		friendCursor.moveToFirst();
		if(!friendCursor.isAfterLast()) {
			do {
				String name = friendCursor.getString(1);
				
				mylist.add(name);
				
			} while (friendCursor.moveToNext());
		}
		friendCursor.close();
	}
}