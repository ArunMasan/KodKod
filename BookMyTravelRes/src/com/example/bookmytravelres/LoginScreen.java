package com.example.bookmytravelres;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;


public class LoginScreen extends Activity {
	private Button mLoginBtn = null;
	private Button mCreateAccountBtn = null;
	//private Button mForgotPwdBtn = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginscreen);
		mLoginBtn=(Button) findViewById(R.id.login_btn);
		mCreateAccountBtn=(Button) findViewById(R.id.createaccount);
		//mForgotPwdBtn=(Button) findViewById(R.id.forgotpassword);
		final EditText username_et = (EditText)findViewById(R.id.username_edittext);

		final EditText password_et = (EditText)findViewById(R.id.password_edittext);

		mLoginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Intent myintent2 = new Intent(LoginScreen.this,HomePageScreen.class);
				//startActivity(myintent2);

				if(username_et.getText().toString().equals("") || password_et.getText().toString().equals("") )
				{
					Toast.makeText(getApplicationContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
					return;
				}

				DatabaseHandler dbHandler = new DatabaseHandler(LoginScreen.this);

				if(dbHandler.getLoginDetails(username_et.getText().toString(),password_et.getText().toString()))
				{
					Toast.makeText(getApplicationContext(),  "Login Successful", Toast.LENGTH_LONG).show();
					Intent myintent = new Intent(LoginScreen.this,MainActivity1.class);
					startActivity(myintent);
					finish();
				}
				else
					Toast.makeText(getApplicationContext(),  "Username or Password does not match", Toast.LENGTH_LONG).show();

			}}
				);
		mCreateAccountBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myintent2 = new Intent(LoginScreen.this,SignUpScreen.class);
				startActivity(myintent2);
			}}
				);
		/* mForgotPwdBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent myintent2 = new Intent(LoginScreen.this,ForgotPassword.class);
				startActivity(myintent2);
			}}
				);*/
	}


}
