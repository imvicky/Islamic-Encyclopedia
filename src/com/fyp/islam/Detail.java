package com.fyp.islam;


import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;

public class Detail extends FragmentActivity implements TabListener {

	ViewPager mPager;
	ActionBar ab;
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quran_detail);
		
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		mPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				ab.setSelectedNavigationItem(arg0);
			}			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle("Qur'an Kareem");
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ab.setBackgroundDrawable(new ColorDrawable(0xffb1ced2));
		
		ab.setDisplayShowTitleEnabled(true);
		
		ActionBar.Tab tab1 = ab.newTab();
		tab1.setText("Arabic");
		tab1.setTabListener(this);
		
		ActionBar.Tab tab2 = ab.newTab();
		tab2.setText("English");
		tab2.setTabListener(this);
		
		ActionBar.Tab tab3 = ab.newTab();
		tab3.setText("Urdu");
		tab3.setTabListener(this);
		
		
		ab.addTab(tab1);
		ab.addTab(tab2);
		ab.addTab(tab3);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}


}

