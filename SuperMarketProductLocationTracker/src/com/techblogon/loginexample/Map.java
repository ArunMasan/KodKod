package com.techblogon.loginexample;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Map extends Activity {

	ImageView point11;
	ImageView point12;
	ImageView point21;
	DrawView drawView;

	View view1;
	View view2;
	View view3;
	View view4;
	View view5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.map);

		point11 = (ImageView) findViewById(R.id.ImageView44);
		point12 = (ImageView) findViewById(R.id.SE);
		point21 = (ImageView) findViewById(R.id.carrot);

		view1 = (View) findViewById(R.id.View1);
		view2 = (View) findViewById(R.id.View2);
		view3 = (View) findViewById(R.id.View3);
		view4 = (View) findViewById(R.id.View4);
		view5 = (View) findViewById(R.id.View5);

		Toast.makeText(getApplicationContext(), Integer.toString(Data.rownum),
				Toast.LENGTH_SHORT).show();

		Toast.makeText(getApplicationContext(), Integer.toString(Data.colmnum),
				Toast.LENGTH_SHORT).show();

		point11.setVisibility(View.INVISIBLE);
		point12.setVisibility(View.INVISIBLE);
		point21.setVisibility(View.INVISIBLE);

		view1.setVisibility(View.INVISIBLE);
		view2.setVisibility(View.INVISIBLE);
		view3.setVisibility(View.INVISIBLE);
		view4.setVisibility(View.INVISIBLE);
		view5.setVisibility(View.INVISIBLE);

		if (Data.rownum == 1 && Data.colmnum == 1) {

			point11.setVisibility(View.VISIBLE);

			view1.setVisibility(View.VISIBLE);
			view2.setVisibility(View.VISIBLE);
			view3.setVisibility(View.VISIBLE);
			view4.setVisibility(View.VISIBLE);
		}

		if (Data.rownum == 1 && Data.colmnum == 2) {

			point12.setVisibility(View.VISIBLE);

			view1.setVisibility(View.VISIBLE);
			view2.setVisibility(View.VISIBLE);
			view4.setVisibility(View.VISIBLE);
			view5.setVisibility(View.VISIBLE);

			view3.setVisibility(View.INVISIBLE);
		}

		if (Data.rownum == 2 && Data.colmnum == 1) {
			point21.setVisibility(View.VISIBLE);
		}
		
		

	}
}