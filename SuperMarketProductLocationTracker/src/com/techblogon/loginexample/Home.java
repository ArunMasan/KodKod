package com.techblogon.loginexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends Activity {
	Button btnDb;
	Button Show;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

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
		Show = (Button) findViewById(R.id.search);

		// Set OnClick Listener on SignUp button
		Show.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// / Create Intent for SignUpActivity and Start The Activity
				Intent Map = new Intent(getApplicationContext(), Map.class);
				startActivity(Map);
			}
		});
	}

}
