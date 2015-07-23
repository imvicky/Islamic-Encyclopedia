package com.fyp.islam;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SixKalmahs extends Activity {
	   

	private static final String DB_NAME = "iedatabase.db";
    private static final String TABLE_NAME = "kalmah";
	private static final String KALMAH_ID = "_id";
	private static final String KALMAH_NAME = "kalmas_nane";
	private static final String KALMAH_DETAIL = "kalmah_detail";
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> kalimahlist;
	DataBaseHelper dbOpenHelper;
	String Detail;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darulifta);
        
        android.app.ActionBar ab = getActionBar();
        ab.setTitle("Six Kalmah");
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
		listView = (ListView) findViewById(R.id.d_listview);
		listView.setAdapter(new ArrayAdapter<String>(this,
				R.layout.dua_list_item, R.id.dualist, kalimahlist));
				
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
								int position,long id) {
				String item = listView.getItemAtPosition(position).toString();
				
				Intent i = new Intent(SixKalmahs.this, Dua_Detail.class);
				 Cursor ourCursor= database.query(TABLE_NAME,
						 					new String[] 
						 							{KALMAH_DETAIL},
						 							null,null,null,null,
						 							KALMAH_ID);
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
			//friends= new List<String>();
			kalimahlist = new ArrayList<String>();
		Cursor friendCursor = database.query(TABLE_NAME,
											 new String[] 
											 {KALMAH_ID, KALMAH_NAME, KALMAH_DETAIL},
											 null, null, null, null
											 , KALMAH_ID);
		friendCursor.moveToFirst();
		if(!friendCursor.isAfterLast()) {
			do {
				String name = friendCursor.getString(1);
				
				kalimahlist.add(name);
				
			} while (friendCursor.moveToNext());
		}
		friendCursor.close();
	}
}