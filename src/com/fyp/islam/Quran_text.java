package com.fyp.islam;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Quran_text extends Activity {
	  
	
	private static final String DB_NAME = "iedatabase.db";
    private static final String TABLE_NAME = "quran";
	private static final String SURAH_ID = "_id";
	private static final String SURAH_NAME = "surah_name";
	private static final String SURAH_DETAIL = "surah";
	private static final String SURAH_ENG = "english";
	private static final String SURAH_URDU = "urdu";
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> friends;
	
	  DataBaseHelper dbOpenHelper;
		  String Detail,eng,urd;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surahlist);
       ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Surah List");
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
		listView= (ListView) findViewById(R.id.list);
		listView.setAdapter(new ArrayAdapter<String>(this,
		        R.layout.listview_item, R.id.topiclist, friends));
				
				listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
								int position,long id) {
				String item = listView.getItemAtPosition(position).toString();
				
				Intent i = new Intent(Quran_text.this, Detail.class);
				 Cursor ourCursor= database.query(TABLE_NAME,
						 					new String[] 
						 							{SURAH_DETAIL,SURAH_ENG, SURAH_URDU},
						 							null,null,null,null,
						 							SURAH_ID);
				ourCursor.moveToPosition(position);
				Detail=ourCursor.getString(0);
				eng= ourCursor.getString(1);
				urd = ourCursor.getString(2);
			
				ourCursor.close();
				i.putExtra("title", item);
				i.putExtra("detail", Detail);
				i.putExtra("senglish", eng);
				i.putExtra("surdu", urd);
				startActivity(i);
			}
		});
	}
	
		private void fillFreinds() {
			//friends= new List<String>();
		friends = new ArrayList<String>();
		Cursor friendCursor = database.query(TABLE_NAME,
											 new String[] 
											 {SURAH_ID, SURAH_NAME},
											 null, null, null, null
											 , SURAH_ID);
		friendCursor.moveToFirst();
		if(!friendCursor.isAfterLast()) {
			do {
				String name = friendCursor.getString(1);
				
				friends.add(name);
				
			} while (friendCursor.moveToNext());
		}
		friendCursor.close();
	}
		
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// TODO Auto-generated method stub
			return super.onCreateOptionsMenu(menu);
		}
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
		
			return super.onOptionsItemSelected(item);
		}
		
}