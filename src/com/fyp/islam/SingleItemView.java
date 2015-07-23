package com.fyp.islam;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SingleItemView extends Activity {
	// Declare Variables
	TextView txtname, txtdetails;
	String name,Details;
	SeekBar seekBar;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.singleitemview);
		ActionBar ab = getActionBar();
		ab.hide();
		
		Intent i = getIntent();
		
		
		name = i.getStringExtra("name");
		
	
		txtname = (TextView) findViewById(R.id.name);
		

		txtname.setText(name);
		Details = i.getStringExtra("Details");
		txtdetails = (TextView) findViewById(R.id.Details);
		
		txtdetails.setText(Details);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		seekBar.setProgress((int) this.txtdetails.getTextSize());
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				txtdetails.setTextSize(progress);
			}
		});
	}
}