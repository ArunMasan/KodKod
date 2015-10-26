package com.example.bookmytravelres;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity1 extends Activity {
	// Declare Tab Variable
	ActionBar.Tab Tab1, Tab2, Tab3;
	Fragment fragmentTab1 = new FragmentTab1();
	Fragment fragmentTab2 = new FragmentTab2();
	Fragment fragmentTab3 = new FragmentTab3();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();

		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		// Tab1 = actionBar.newTab().setIcon(R.drawable.tab1);
		Tab1 = actionBar.newTab().setText("Air");
		Tab2 = actionBar.newTab().setText("Hotel");
		Tab3 = actionBar.newTab().setText("Air+Hotel");

		// Set Tab Listeners
		Tab1.setTabListener(new TabListener(fragmentTab1));
		Tab2.setTabListener(new TabListener(fragmentTab2));
		Tab3.setTabListener(new TabListener(fragmentTab3));

		// Add tabs to actionbar
		actionBar.addTab(Tab1);
		actionBar.addTab(Tab2);
		actionBar.addTab(Tab3);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.Check_Flight_Status:
			// Starting a new Intent
			Intent checkStatus = new Intent(getApplicationContext(),
					checkStatus.class);

			// starting new activity
			startActivity(checkStatus);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		case R.id.Finding_Deals:
			// Starting a new Intent
			Intent Finding_Deals = new Intent(getApplicationContext(),
					FindingDeals.class);

			// starting new activity
			startActivity(Finding_Deals);
			return true;
		case R.id.Feedback:
			// Starting a new Intent
			Intent Feedback = new Intent(getApplicationContext(),
					FeedbackMain.class);

			// starting new activity
			startActivity(Feedback);
			return true;
		case R.id.Logout:
			finish();
			return true;
		}

	}

}
