package com.example.bookmytravelres;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class FindingDeals extends Activity {
	DatabaseHandler dbHandler = null;
	public static TextView tvDisplayDate;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findingdeals);

		final MultiAutoCompleteTextView Sourcetextbox = ((MultiAutoCompleteTextView) findViewById(R.id.Source));
		final MultiAutoCompleteTextView Destinationtextbox = ((MultiAutoCompleteTextView) findViewById(R.id.Destination));

		final EditText PriceBox = ((EditText) findViewById(R.id.Source));

		tvDisplayDate = (TextView) findViewById(R.id.tvDate1);
		tvDisplayDate.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {

				DialogFragment picker = new FindigDealsDatePickerFragment();
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		Button search = (Button) findViewById(R.id.SearchButton);

		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String Source = Sourcetextbox.getText().toString();
				final String Destination = Destinationtextbox.getText()
						.toString();
				final String Price = PriceBox.getText().toString();
			}

		});
	}
}

class FindigDealsDatePickerFragment extends DialogFragment implements
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

		FindingDeals.tvDisplayDate.setText(formattedDate);

	}

}