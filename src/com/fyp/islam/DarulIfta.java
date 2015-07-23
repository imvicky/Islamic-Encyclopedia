package com.fyp.islam;

import java.util.ArrayList;



import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DarulIfta extends Activity {
	   

	private static final String DB_NAME = "iedatabase.db";
   
	private static final String TABLE_NAME = "darulifta";
	private static final String IFTA_ID = "_id";
	private static final String IFTA_NAME = "ifta_name";
	private static final String IFTA_DETAIL = "ifta_info";
	private static final String IFTA_MAIL = "ifta_email";
	private SQLiteDatabase database;
	private ListView listView;
	private ArrayList<String> mylist;
	
	  DataBaseHelper dbOpenHelper;
		  String info,mail;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.darulifta);
        ActionBar ab = getActionBar();
        ab.setTitle("Darul Ifta");
        ab.setDisplayHomeAsUpEnabled(true);
          dbOpenHelper = new DataBaseHelper(this, DB_NAME);
        database = dbOpenHelper.openDataBase();
          fillList();
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
		      R.layout.listview_item, R.id.topiclist, mylist));
		
				
		 listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
								int position,long id) {
				String item = listView.getItemAtPosition(position).toString();
				
				
				 Cursor ourCursor= database.query(TABLE_NAME,
						 					new String[] 
						 							{IFTA_DETAIL,IFTA_MAIL},
						 							null,null,null,null,
						 							IFTA_ID);
				ourCursor.moveToPosition(position);
				info=ourCursor.getString(0);
				mail= ourCursor.getString(1);
				ourCursor.close();
				AlertDialog.Builder ad= new AlertDialog.Builder(DarulIfta.this);
				
				ad.setTitle(item);
				ad.setMessage(info + "\n" + "E-Mail: "+ mail);
				ad.setPositiveButton("OK", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
					
						arg0.dismiss();
					}
				});
				ad.show();
				
			}
		});
	}
	
		private void fillList() {
			
		mylist = new ArrayList<String>();
		Cursor myCursor = database.query(TABLE_NAME,
											 new String[] 
											 {IFTA_ID, IFTA_NAME},
											 null, null, null, null
											 , IFTA_ID);
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