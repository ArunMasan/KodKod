package com.example.bookmytravelres;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
//import android.view.WindowManager;



public class MainActivity extends Activity {

	private static boolean activityFinish = false;
	private int mTimerTime = 5000;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Handler handler = new Handler();

		// run a thread after 2 seconds to start the home screen
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				if(activityFinish)
				{
					activityFinish = false;
					return;
				}
				Intent intent = new Intent(MainActivity.this,LoginScreen.class);
			    startActivity(intent);

				// make sure we close the splash screen so the user won't come back when it presses back key
				finish();
			}

		}, mTimerTime);

	}

	/*@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD); 
	}*/

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_HOME || keyCode==KeyEvent.KEYCODE_BACK)
		{
			activityFinish = true;
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
}
