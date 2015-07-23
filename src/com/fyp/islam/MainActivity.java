package com.fyp.islam;



import android.os.Bundle;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	
	
	
	TextView quran,stories,forum,darulifta,elaj;
	
	TextView abt; 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar ab = getActionBar();
        ab.hide();
        
        String fontPath = "fonts/ITCEDSCR.TTF";
         quran= (TextView)findViewById(R.id.btn_quran);
         stories = (TextView)findViewById(R.id.List_topics);
         darulifta= (TextView)findViewById(R.id.btn_ifta);
        abt = (TextView) findViewById(R.id.b_about);
         elaj= (TextView) findViewById(R.id.btn_ilaj);
        quran.setOnClickListener(this);
        stories.setOnClickListener(this);
       
        elaj.setOnClickListener(this);
        darulifta.setOnClickListener(this);
        abt.setOnClickListener(this);
        
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        quran.setTypeface(tf);
        stories.setTypeface(tf);
        darulifta.setTypeface(tf);
        elaj.setTypeface(tf);
  }

    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()){
		case R.id.List_topics:
				Intent i_list = new Intent(MainActivity.this, Sections_List.class);
				startActivity(i_list);
				break;
		case R.id.btn_quran:
			Intent i_q = new Intent(MainActivity.this, Quran_text.class);
			startActivity(i_q);
			break;
		
		case R.id.btn_ilaj:
			Intent il = new Intent(MainActivity.this, RohaniElaj.class);
			startActivity(il);
			break;
			
		case R.id.btn_ifta:
			Intent ift = new Intent(MainActivity.this, DarulIfta.class);
			startActivity(ift);
			break;
		case R.id.b_about:
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
			
			ad.setTitle("About Developer");
			ad.setMessage("Made by Muhammad Waqas ." +
					" \n Email: gre8vicky@gmail.com " +
					"\n For any queries and suggestions donot hesitate to contact me." +
					" \n Please appreciate and remember me in your prayers." +
					" \n Jazak Allah");
			ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					});
			ad.show();
			break;
		}
		
		
	}
	
	
	
	
}
