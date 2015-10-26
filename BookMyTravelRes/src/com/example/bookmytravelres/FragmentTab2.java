package com.example.bookmytravelres;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentTab2 extends Fragment implements OnItemSelectedListener {
	private int year;
	private int month;
	private int day;
	public static boolean flag;

	public static TextView tvDepartureDate;

	public static TextView tvReturningDate;

	public static Button search;

	public static String[] countries;

	private AutoCompleteTextView actv;
	private MultiAutoCompleteTextView mactv;

	public static String spinnerSelectedDestination;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragmenttab2, container, false);

		Spinner spinner1 = (Spinner) view.findViewById(R.id.NumberofPersons);
		// Spinner Drop down elements
		List<String> categories = new ArrayList<String>();
		categories.add("1 Adult");
		categories.add("2 Adults");
		categories.add("3 Adults");

		// Auto Complete
		countries = getResources().getStringArray(R.array.list_of_countries);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1,
				countries);

		actv = (AutoCompleteTextView) view
				.findViewById(R.id.autoCompleteTextView1);
		/*
		 * mactv = (MultiAutoCompleteTextView) view
		 * .findViewById(R.id.multiAutoCompleteTextView1);
		 */

		actv.setAdapter(adapter);
		actv.setValidator(new Validator());
		/* mactv.setAdapter(adapter); */

		/* mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); */
		// Auto Complete

		ArrayAdapter<String> dataadapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_spinner_item,
				categories);
		spinner1.setAdapter(dataadapter);

		tvDepartureDate = (TextView) view.findViewById(R.id.tvDeparturedate);
		tvReturningDate = (TextView) view.findViewById(R.id.tvReturningDate);

		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);

		tvDepartureDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				DialogFragment picker = new Fragment2DatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		tvReturningDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				flag = true;
				DialogFragment picker = new Fragment2DatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		final EditText destination = ((EditText) view
				.findViewById(R.id.destination));
		
		final TextView CheckIn = ((TextView) view
				.findViewById(R.id.tvDeparturedate));
		final TextView CheckOut = ((TextView) view
				.findViewById(R.id.tvReturningDate));

		// Spinner element
		Spinner spinner = (Spinner) view.findViewById(R.id.SourceCity);

		// Spinner click listener
		spinner.setOnItemSelectedListener(this);

		// Spinner Drop down elements
		List<String> countryList = new ArrayList<String>();
		countryList.add("Austin");
		countryList.add("Dallas");
		countryList.add("San Antonio");

		// Creating adapter for spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_spinner_item,
				countryList);

		// Drop down layout style - list view with radio button
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinner.setAdapter(dataAdapter);

		search = (Button) view.findViewById(R.id.SearchButton);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(),
						HotelSearchScreen.class);

				// Log.w("CheckIn", ""+CheckIn.getText().toString());
				intent.putExtra("CheckIn", CheckIn.getText().toString());
				intent.putExtra("CheckOut", CheckOut.getText().toString());
				intent.putExtra("destination", destination.getText().toString());

				startActivity(intent);

			}

		});
		return view;
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {

		return null;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		spinnerSelectedDestination = parent.getItemAtPosition(position)
				.toString();
		Toast.makeText(this.getActivity(), spinnerSelectedDestination,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}

class Fragment2DatePickerFragment extends DialogFragment implements
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

		if (FragmentTab2.flag == true) {
			FragmentTab2.tvReturningDate.setText(formattedDate);
			FragmentTab2.flag = false;
		} else {
			FragmentTab2.tvDepartureDate.setText(formattedDate);
		}

	}

}

class Validator implements AutoCompleteTextView.Validator {

	@Override
	public boolean isValid(CharSequence text) {
		Log.v("Test", "Checking if valid: " + text);
		Arrays.sort(FragmentTab2.countries);
		if (Arrays.binarySearch(FragmentTab2.countries, text.toString()) > 0) {

			return true;
		}

		return false;
	}

	@Override
	public CharSequence fixText(CharSequence invalidText) {
		Log.v("Test", "Returning fixed text");

		/*
		 * I'm just returning an empty string here, so the field will be
		 * blanked, but you could put any kind of action here, like popping up a
		 * dialog?
		 * 
		 * Whatever value you return here must be in the list of valid words.
		 */
		return "";
	}
}