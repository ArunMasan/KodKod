package com.techblogon.loginexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btnDb;
	Button show;

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.lvExp);

		// preparing list data
		prepareListData();

		listAdapter = new ExpandableListAdapter(this, listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);

		// Listview Group click listener
		expListView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
					int groupPosition, long id) {
				// Toast.makeText(getApplicationContext(),
				// "Group Clicked " + listDataHeader.get(groupPosition),
				// Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

			@Override
			public void onGroupExpand(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Expanded",
						Toast.LENGTH_SHORT).show();
			}
		});

		// Listview Group collasped listener
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

			@Override
			public void onGroupCollapse(int groupPosition) {
				Toast.makeText(getApplicationContext(),
						listDataHeader.get(groupPosition) + " Collapsed",
						Toast.LENGTH_SHORT).show();

			}
		});

		// Listview on child click listener
		expListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				String x = listDataChild.get(listDataHeader.get(groupPosition))
						.get(childPosition);

				Toast.makeText(getApplicationContext(), x, Toast.LENGTH_SHORT)
						.show();

				// SQLiteDatabase db = openOrCreateDatabase("StudentDB",
				// Context.MODE_PRIVATE, null);

				Cursor c = Data.db.rawQuery(
						"SELECT * FROM student WHERE name='" + x + "'", null);
				if (c.moveToFirst()) {
					Toast.makeText(getApplicationContext(), c.getString(1),
							Toast.LENGTH_SHORT).show();
					Toast.makeText(getApplicationContext(), c.getString(2),
							Toast.LENGTH_SHORT).show();
					// editName.setText(c.getString(1));
					// editMarks.setText(c.getString(2));
				} else {
					// showMessage("Error", "Invalid Name");
					// clearText();
				}

				Intent Map = new Intent(getApplicationContext(), Map.class);
				startActivity(Map);
				return false;
			}

		});

		// Get The Refference Of Buttons
		btnDb = (Button) findViewById(R.id.btnDb);

		// Set OnClick Listener on SignUp button
		btnDb.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// / Create Intent for SignUpActivity and Start The Activity
				Intent Data = new Intent(getApplicationContext(), Data.class);
				startActivity(Data);
			}
		});

		// ===============

		// Get The Refference Of Buttons
		show = (Button) findViewById(R.id.search);

		// Set OnClick Listener on SignUp button
		show.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// / Create Intent for SignUpActivity and Start The Activity
				Intent Map = new Intent(getApplicationContext(), Map.class);
				startActivity(Map);
			}
		});
	}

	/*
	 * Preparing the list data
	 */
	private void prepareListData() {
		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		listDataHeader.add("Fruits");
		listDataHeader.add("Vegetables");
		listDataHeader.add("Beverages");

		// Adding child data
		List<String> top250 = new ArrayList<String>();
		top250.add("Apple");
		top250.add("Banana");

		List<String> nowShowing = new ArrayList<String>();
		nowShowing.add("Carrot");
		nowShowing.add("cabbage");

		List<String> comingSoon = new ArrayList<String>();
		comingSoon.add("Coco Cola");
		comingSoon.add("Pepsi");

		listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
		listDataChild.put(listDataHeader.get(1), nowShowing);
		listDataChild.put(listDataHeader.get(2), comingSoon);
	}
}
