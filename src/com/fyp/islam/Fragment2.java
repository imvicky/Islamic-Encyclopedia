package com.fyp.islam;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class Fragment2 extends Fragment {

	
	
public Fragment2() {
		// Required empty public constructor
	}

	SeekBar seekBar;
	TextView topic, detail;
	String name, det;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Intent a = getActivity().getIntent();
		name= a.getStringExtra("title");
		det = a.getStringExtra("senglish");
		topic= (TextView)getActivity().findViewById(R.id.e_name);
		detail= (TextView) getActivity().findViewById(R.id.e_details);
		
		topic.setText(name);

		detail.setText(det);
		seekBar= (SeekBar) getActivity().findViewById(R.id.seekBar2);
		seekBar.setProgress((int) detail.getTextSize());
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
				detail.setTextSize(progress);
			}
		});
	
	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		
		return inflater.inflate(R.layout.fragment2, container, false);
	}

}
