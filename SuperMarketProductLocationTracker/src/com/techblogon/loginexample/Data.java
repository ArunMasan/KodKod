package com.techblogon.loginexample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

public class Data extends Activity implements OnClickListener {
	EditText editRollno, editName, editMarks;
	Button btnAdd, btnDelete, btnModify, btnView, btnViewAll, btnShowInfo;
	public static SQLiteDatabase db;

	Button btnDb;
	Button show;
	Button direction;

	public static int rownum;
	public static int colmnum;

	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data);

		editRollno = (EditText) findViewById(R.id.editRollno);
		editName = (EditText) findViewById(R.id.editName);
		editMarks = (EditText) findViewById(R.id.editMarks);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnModify = (Button) findViewById(R.id.btnModify);
		btnView = (Button) findViewById(R.id.btnView);
		btnViewAll = (Button) findViewById(R.id.btnViewAll);
		btnShowInfo = (Button) findViewById(R.id.btnShowInfo);
		btnAdd.setOnClickListener(this);
		btnDelete.setOnClickListener(this);
		btnModify.setOnClickListener(this);
		btnView.setOnClickListener(this);
		btnViewAll.setOnClickListener(this);
		btnShowInfo.setOnClickListener(this);
		db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR, rowno VARCHAR, colmno VARCHAR);");

		// New
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

				Cursor c = db.rawQuery("SELECT * FROM student WHERE name='" + x
						+ "'", null);

				rownum = 0;
				colmnum = 0;

				if (c.moveToFirst()) {
					// Toast.makeText(getApplicationContext(), c.getString(1),
					// Toast.LENGTH_SHORT).show();
					rownum = Integer.parseInt(c.getString(1));
					// Toast.makeText(getApplicationContext(), c.getString(2),
					// Toast.LENGTH_SHORT).show();
					colmnum = Integer.parseInt(c.getString(2));
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
		show = (Button) findViewById(R.id.search);

		// Set OnClick Listener on SignUp button
		show.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// / Create Intent for SignUpActivity and Start The Activity
				Intent Map = new Intent(getApplicationContext(), Show.class);
				startActivity(Map);
			}
		});

		// Get The Refference Of Buttons
		direction = (Button) findViewById(R.id.direction);

		// Set OnClick Listener on SignUp button
		direction.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// / Create Intent for SignUpActivity and Start The Activity
				Intent Dij = new Intent(getApplicationContext(), Dijkstra.class);
				startActivity(Dij);
			}
		});
	}

	public void onClick(View view) {
		if (view == btnAdd) {
			if (editRollno.getText().toString().trim().length() == 0
					|| editName.getText().toString().trim().length() == 0
					|| editMarks.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please enter all values");
				return;
			}
			db.execSQL("INSERT INTO student VALUES('" + editRollno.getText()
					+ "','" + editName.getText() + "','" + editMarks.getText()
					+ "');");
			showMessage("Success", "Record added");
			clearText();
		}
		if (view == btnDelete) {
			if (editRollno.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please enter Name");
				return;
			}
			Cursor c = db.rawQuery("SELECT * FROM student WHERE name='"
					+ editRollno.getText() + "'", null);
			if (c.moveToFirst()) {
				db.execSQL("DELETE FROM student WHERE name='"
						+ editRollno.getText() + "'");
				showMessage("Success", "Record Deleted");
			} else {
				showMessage("Error", "Invalid Name");
			}
			clearText();
		}
		if (view == btnModify) {
			if (editRollno.getText().toString().trim().length() == 0) {
				showMessage("Error", "Please enter Name");
				return;
			}
			Cursor c = db.rawQuery("SELECT * FROM student WHERE name='"
					+ editRollno.getText() + "'", null);
			if (c.moveToFirst()) {
				db.execSQL("UPDATE student SET name='" + editRollno.getText()
						+ "',rowno='" + editName.getText() + "' WHERE colmno='"
						+ editMarks.getText() + "'");
				showMessage("Success", "Record Modified");
			} else {
				showMessage("Error", "Invalid Rollno");
			}
			clearText();
		}
		if (view == btnView) {
			if (editRollno.getText().length() == 0) {
				showMessage("Error", "Please enter Name");
				return;
			}
			Cursor c = db.rawQuery("SELECT * FROM student WHERE name='"
					+ editRollno.getText() + "'", null);
			if (c.moveToFirst()) {
				editName.setText(c.getString(1));
				editMarks.setText(c.getString(2));
			} else {
				showMessage("Error", "Invalid Name");
				clearText();
			}
		}
		if (view == btnViewAll) {
			Cursor c = db.rawQuery("SELECT * FROM student", null);
			if (c.getCount() == 0) {
				showMessage("Error", "No records found");
				return;
			}
			StringBuffer buffer = new StringBuffer();
			while (c.moveToNext()) {
				buffer.append("Name: " + c.getString(0) + "\n");
				buffer.append("Row#: " + c.getString(1) + "\n");
				buffer.append("Colm#: " + c.getString(2) + "\n\n");
			}
			showMessage("Product Details", buffer.toString());
		}
		if (view == btnShowInfo) {
			// showMessage("Student Management Application",
			// "Developed By Azim");
		}
	}

	public void showMessage(String title, String message) {
		Builder builder = new Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.show();
	}

	public void clearText() {
		editRollno.setText("");
		editName.setText("");
		editMarks.setText("");
		editRollno.requestFocus();
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