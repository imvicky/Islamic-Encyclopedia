package com.fyp.islam;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		 
		ActionBar actionbar = getActionBar();
		actionbar.hide();
		Thread timer = new Thread(){
		public void run(){
		try {
			sleep(4000);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			Intent i = new Intent(splash.this, MainActivity.class);
			startActivity(i);
		}
			
			
		}
		
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

}
