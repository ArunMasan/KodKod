package com.example.bookmytravelres;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

public class FragmentTab3 extends Fragment {

	private int year;
	private int month;
	private int day;
	public static TextView tvdepartureCity;
	public static boolean flag;

	public static TextView tvArrivalDate;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragmenttab3, container, false);

		// Spinner element
		Spinner spinner1 = (Spinner) view.findViewById(R.id.NumberOfPassengers);
		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("1 Adult");
		categories.add("2 Adults");
		categories.add("3 Adults");

		ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_spinner_item,
				categories);
		spinner1.setAdapter(dataadapter);

		tvdepartureCity = (TextView) view.findViewById(R.id.tvDepartureDate);
		tvArrivalDate = (TextView) view.findViewById(R.id.tvArrivalDate);

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

		Button search = (Button) view.findViewById(R.id.SearchButton);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), checkStatus.class);
				startActivity(intent);
			}
		});

		tvdepartureCity.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				DialogFragment picker = new Fragment3DatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		tvArrivalDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				flag = true;
				DialogFragment picker = new Fragment3DatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		return view;

	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return null;
	}

}

class Fragment3DatePickerFragment extends DialogFragment implements
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

		if (FragmentTab3.flag == true) {
			FragmentTab3.tvArrivalDate.setText(formattedDate);
			FragmentTab3.flag = false;
		} else {
			FragmentTab3.tvdepartureCity.setText(formattedDate);
		}

	}

}