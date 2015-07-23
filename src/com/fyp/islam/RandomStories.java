package com.fyp.islam;



import java.util.ArrayList;

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

public class RandomStories extends Activity {
	   

	private static final String DB_NAME = "iedatabase.db";
    private static final String TABLE_NAME = "Stories";
	private static final String STORY_ID = "_id";
	private static final String STORY_NAME = "title";
	private static final String STORY_DETAIL = "description";
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> friends;
	DataBaseHelper dbOpenHelper;
	String Detail;
	EditText ed;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dua_list);
        
        android.app.ActionBar ab = getActionBar();
        ab.setTitle("Random Stories");
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
			
		final ArrayAdapter<String> adapter = new ArrayAdapter<String>(RandomStories.this, R.layout.dua_list_item, R.id.dualist, friends);
		listView.setAdapter(adapter);
		
		ed = (EditText) findViewById(R.id.d_search);
		 

        ed.addTextChangedListener(new TextWatcher() {
 
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
				
				Intent i = new Intent(RandomStories.this, Dua_Detail.class);
				 Cursor ourCursor= database.query(TABLE_NAME,
						 					new String[] 
						 							{STORY_DETAIL},
						 							null,null,null,null,
						 							STORY_ID);
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
		friends = new ArrayList<String>();
		Cursor friendCursor = database.query(TABLE_NAME,
											 new String[] 
											 {STORY_ID, STORY_NAME, STORY_DETAIL},
											 null, null, null, null
											 , STORY_ID);
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