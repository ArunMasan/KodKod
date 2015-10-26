package com.example.bookmytravelres;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FlightSelectionScreen extends Activity {

	
	String source = null;
	String destination = null;
	String tripType = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flightselection);
		
		Intent intent = getIntent();
		source = intent.getStringExtra("source");
		destination =  intent.getStringExtra("destination");
		tripType = intent.getStringExtra("tripType");
		
		
		
		
		
		
	}
}
