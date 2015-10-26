package com.example.bookmytravelres;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentTab1 extends Fragment {

	private int year;
	private int month;
	private int day;
	public static TextView displayDate_tv;
	public static boolean flag;

	public static TextView returnDate_tv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragmenttab1, container, false);

		
		// Spinner element
		Spinner noOfTravellers = (Spinner) view.findViewById(R.id.nooftravellers);
		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("1 Adult");
		categories.add("2 Adults");
		categories.add("3 Adults");

		ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_spinner_item,
				categories);
		noOfTravellers.setAdapter(dataadapter);

		displayDate_tv = (TextView) view.findViewById(R.id.departuredate_tv);
		returnDate_tv = (TextView) view.findViewById(R.id.returndate_tv);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		// set current date into textview
		/*
		 * tvDisplayDate.setText(new StringBuilder() // Month is 0 based, just
		 * add 1 .append(month + 1).append("-").append(day).append("-")
		 * .append(year).append(" "));
		 * 
		 * tvDisplayDate2.setText(new StringBuilder() // Month is 0 based, just
		 * add 1 .append(month + 1).append("-").append(day).append("-")
		 * .append(year).append(" "));
		 */
		
		final EditText source = ((EditText)view.findViewById(R.id.fragment1_source_city));
		final EditText destination = ((EditText)view.findViewById(R.id.fragment1_arrival_city));

		Button search = (Button) view.findViewById(R.id.search_button);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Log.w("hi", ""+source.getText().toString());
				Log.w("hi", ""+destination.getText().toString());
				Log.w("hi", ""+displayDate_tv.getText().toString());
				Log.w("hi", ""+returnDate_tv.getText().toString());
				
				/*
				InsertFlightBookData("Austin","Atlanta","2","4",2,300,2000);
				InsertFlightBookData("Austin","Atlanta","8","10",2,300,2000);
				InsertFlightBookData("Dallas","Atlanta","2","4",2,300,2000);
				InsertFlightBookData("Dallas","Atlanta","8","10",2,300,2000);
				InsertFlightBookData("San Jose","Atlanta","2","4",2,300,2000);
				InsertFlightBookData("San Jose","Atlanta","8","10",2,300,2000);
				InsertFlightBookData("San Jose","AAA","8","10",2,300,2000);
				*/
				
				Intent intent = new Intent(getActivity(), FlightSearchScreen.class);
				intent.putExtra("source", source.getText().toString());
				intent.putExtra("destination", destination.getText().toString());
				startActivity(intent);

			} 
		});

		displayDate_tv.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				DialogFragment picker = new DatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		returnDate_tv.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				flag = true;
				DialogFragment picker = new DatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		return view;

	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return null;
	}
	
	public void InsertFlightBookData(String source, String destination, String departureTime, String returnTime, int tripDuration, int price,int miles) {

		DatabaseHandler dbHandler = new DatabaseHandler(this.getActivity());
		
		ContentValues values = new ContentValues();
		values.put(DatabaseHandler.BOOK_FLIGHT_SOURCE, source);
		values.put(DatabaseHandler.BOOK_FLIGHT_DESTINATION, destination);
		values.put(DatabaseHandler.BOOK_FLIGHT_DEPARTURE_TIME, departureTime);
		values.put(DatabaseHandler.BOOK_FLIGHT_RETURN_TIME, returnTime);
		values.put(DatabaseHandler.BOOK_FLIGHT_TRIP_DURATION, tripDuration);
		values.put(DatabaseHandler.BOOK_FLIGHT_PRICE, price);
		values.put(DatabaseHandler.BOOK_FLIGHT_MILES, miles);
		dbHandler.insertBookFlightData(values);
	}

}

class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(year, month, day);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(c.getTime());

		if (FragmentTab1.flag == true) {
			FragmentTab1.returnDate_tv.setText(formattedDate);
			FragmentTab1.flag = false;
		} else {
			FragmentTab1.displayDate_tv.setText(formattedDate);
		}

	}
}