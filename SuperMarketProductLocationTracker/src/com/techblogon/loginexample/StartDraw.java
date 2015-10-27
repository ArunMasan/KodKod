package com.techblogon.loginexample;

//import com.techblogon.loginexample.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class StartDraw extends Activity { 
	DrawView drawView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		drawView = new DrawView(this);
		drawView.setBackgroundColor(Color.WHITE);
		setContentView(R.layout.activity_main);

	}
}