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


public class RohaniElaj extends Activity {
	// Declare Variables

	private static final String DB_NAME = "iedatabase.db";
	   
	private static final String TABLE_NAME = "elaj";
	private static final String RE_ID = "_id";
	private static final String RE_NAME = "re_name";
	private static final String RE_DETAIL = "re_detail";
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> mylist;
	
	  DataBaseHelper dbOpenHelper;
	  String detail;
	EditText editsearch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.listview_main);
		
		 ActionBar ab = getActionBar();
	        ab.setTitle("Rohani Elaj");
	        ab.setDisplayHomeAsUpEnabled(true);
	             dbOpenHelper = new DataBaseHelper(this, DB_NAME);
	        database = dbOpenHelper.openDataBase();
	          fillTheList();
	        setUpList();    
	        
	       
	      	        
	       
}
	        
	

	 @Override
	   	protected void onDestroy() {
	   		// TODO Auto-generated method stub
	   		super.onDestroy();
	   		dbOpenHelper.close();
	   	}
	    
		private void setUpList() {
			listView = (ListView) findViewById(R.id.i_listview);
		
				final ArrayAdapter<String> adapter = new ArrayAdapter<String>(RohaniElaj.this, R.layout.listview_item, R.id.topiclist, mylist);
					listView.setAdapter(adapter);
					
					editsearch = (EditText) findViewById(R.id.search);
					 
			       
			        editsearch.addTextChangedListener(new TextWatcher() {
			 
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
					
					Intent i = new Intent(RohaniElaj.this, SingleItemView.class);
					 Cursor ourCursor= database.query(TABLE_NAME,
							 					new String[] 
							 							{RE_DETAIL},
							 							null,null,null,null,
							 							RE_ID);
					ourCursor.moveToPosition(position);
					detail=ourCursor.getString(0);
					ourCursor.close();
					i.putExtra("name", item);
					i.putExtra("Details", detail);
					
					startActivity(i);
				}
				
				
			});
					
				
		
					
					
		}
		
			private void fillTheList() {
				//mylist= new List<String>();
			mylist = new ArrayList<String>();
			Cursor myCursor = database.query(TABLE_NAME,
												 new String[] 
												 {RE_ID, RE_NAME},
												 null, null, null, null
												 , RE_ID);
			myCursor.moveToFirst();
			if(!myCursor.isAfterLast()) {
				do {
					String Name = myCursor.getString(1);
					
					mylist.add(Name);
					
				} while (myCursor.moveToNext());
			}
			myCursor.close();
		}
			
			
			

			

}
	

