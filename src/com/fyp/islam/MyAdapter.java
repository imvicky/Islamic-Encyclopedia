package com.fyp.islam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter{

	int PAGE_COUNT = 3;
	public MyAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Fragment frag=null;
		if(arg0==0){
			frag= new Fragment1();
		}
		if(arg0==1){
			frag= new Fragment2();
		}
		if(arg0==2){
			frag= new Fragment3();
		}
		
		
		return frag;
	}

	@Override
	public int getCount() {
		
		// TODO Auto-generated method stub
		return PAGE_COUNT;
	}
}
