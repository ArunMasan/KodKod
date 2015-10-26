package com.example.bookmytravelres;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bookmytravelres.DatabaseHandler.BookFlightDataObject;

public class HotelSearchScreen extends Activity {
	String source = null;
	String destination_val = null;
	String tripType = null;
	String CheckInTime_val = null;
	String CheckOutTime_val = null;

	public static TextView flightsearch_destination_tv;
	public static TextView CheckInTime_textview_textview;
	public static TextView CheckOutTime_textview_textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotelsearchtop);

		Intent intent = getIntent();
		CheckInTime_val = intent.getStringExtra("CheckIn");
		CheckOutTime_val = intent.getStringExtra("CheckOut");
		destination_val = intent.getStringExtra("destination");

		flightsearch_destination_tv = (TextView) findViewById(R.id.flightsearch_destination_tv_value);
		flightsearch_destination_tv.setText(destination_val);

		CheckInTime_textview_textview = (TextView) findViewById(R.id.CheckInTime_value);
		CheckInTime_textview_textview.setText(CheckInTime_val);

		CheckOutTime_textview_textview = (TextView) findViewById(R.id.CheckOutTime_value);
		CheckOutTime_textview_textview.setText(CheckOutTime_val);

		ListView listView = (ListView) findViewById(R.id.hotelsearch_lv);

		DatabaseHandler dbHandler = new DatabaseHandler(getApplicationContext());
		ArrayList<BookFlightDataObject> arrayList = dbHandler
				.getFlightBookData("Austin", "Atlanta");
		listviewAdapter adapter = new listviewAdapter(this, arrayList);
		listView.setAdapter(adapter);

	}

	public class listviewAdapter extends BaseAdapter {
		public ArrayList<BookFlightDataObject> list;
		Activity activity;

		public listviewAdapter(Activity activity,
				ArrayList<BookFlightDataObject> list) {
			super();
			this.activity = activity;
			this.list = list;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		private class ViewHolder {
			TextView source_tv;
			TextView departure_tv;
			TextView depTime_tv;
			TextView arrivalTime_tv;
			TextView travelDuration_tv;
			TextView price_tv;
			TextView miles_tv;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub

			// TODO Auto-generated method stub
			ViewHolder holder;
			LayoutInflater inflater = activity.getLayoutInflater();

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.flightsearch_lv_row,
						null);
				holder = new ViewHolder();
				holder.source_tv = (TextView) convertView
						.findViewById(R.id.flightsearch_lv_row_source);
				holder.departure_tv = (TextView) convertView
						.findViewById(R.id.flightsearch_lv_row_departure);
				holder.depTime_tv = (TextView) convertView
						.findViewById(R.id.flightsearch_lv_row_departure_time);
				holder.arrivalTime_tv = (TextView) convertView
						.findViewById(R.id.flightsearch_lv_row_arrival_time);
				holder.travelDuration_tv = (TextView) convertView
						.findViewById(R.id.flightsearch_lv_row_travel_duration);
				holder.price_tv = (TextView) convertView
						.findViewById(R.id.flightsearch_lv_row_price);
				holder.miles_tv = (TextView) convertView
						.findViewById(R.id.flightsearch_lv_row_miles);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.source_tv.setText(source);
			holder.departure_tv.setText(list.get(position).getDestination());
			holder.depTime_tv.setText(list.get(position).getDepartureTime());
			holder.arrivalTime_tv
					.setText(list.get(position).getReturningTime());
			holder.travelDuration_tv.setText(""
					+ list.get(position).getTripDuration());
			holder.price_tv.setText("" + list.get(position).getPrice());
			holder.miles_tv.setText("" + list.get(position).getMiles());
			return convertView;
		}

	}
}
