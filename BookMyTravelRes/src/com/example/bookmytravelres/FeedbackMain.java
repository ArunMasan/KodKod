/**
 * 
 */
package com.example.bookmytravelres;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class FeedbackMain extends Activity {

	// Declare Tab Variable
	ActionBar.Tab Tab1, Tab2;
	Fragment Appfeedbackfragment = new AppFeedbackFragment();
	Fragment Hotelfeedbackfragment = new HotelFeedbackFragment();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_main);

		DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());

		ActionBar actionBar = getActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		// Tab1 = actionBar.newTab().setIcon(R.drawable.tab1);
		Tab1 = actionBar.newTab().setText("App Feedback");
		Tab2 = actionBar.newTab().setText("Hotel Feedback");

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(Appfeedbackfragment));
		Tab2.setTabListener(new TabListener(Hotelfeedbackfragment));

		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}
}
