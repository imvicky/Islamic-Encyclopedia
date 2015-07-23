package com.fyp.islam;




import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Sections_List extends Activity{

	ListView listview;
	ArrayAdapter<String> adapter;
	String[] list =new String[]{"Islam","Books","Dua","SixKalmahs","RandomStories"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.section_list);
		
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
	listview = (ListView)findViewById(R.id.listView1);
	adapter = new ArrayAdapter<String>(Sections_List.this, R.layout.listview_item,R.id.topiclist, list);
	listview.setAdapter(adapter);
	listview.setOnItemClickListener(new OnItemClickListener() {

	    @Override
        public void onItemClick(AdapterView<?> parent, View view,
           int position, long id) {
          
	    	String cls= list[position];
	    	Class myclass;
	    	try{
	    		myclass = Class.forName("com.fyp.islam." + cls);
	    			Intent i = new Intent(Sections_List.this, myclass);
	    			startActivity(i);
	    		} catch (ClassNotFoundException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		
	    	}
		
	
	
	});
	
}
}
