package com.example.bookmytravelres;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpScreen extends Activity {

	DatabaseHandler dbHandler= null;

	int cardNumber=0;
	int cardMonth=0;
	int cardYear=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signupscreen);
		dbHandler = new DatabaseHandler(this);

		final EditText firstname_et = ((EditText)findViewById(R.id.et_firstname_signup));
		final EditText middlename_et = ((EditText)findViewById(R.id.et_middlename_signup));
		final EditText lastname_et = ((EditText)findViewById(R.id.et_lastname_signup));
		final EditText address_et = ((EditText)findViewById(R.id.et_firstname_signup));
		final EditText email_et = ((EditText)findViewById(R.id.et_email_signup));
		final EditText username_et = ((EditText)findViewById(R.id.et_username_signup));
		final EditText password_et = ((EditText)findViewById(R.id.et_password_signup));
		final EditText cPassword_et = ((EditText)findViewById(R.id.et_cPassword_signup));
		final EditText cardNumber_et = (EditText)findViewById(R.id.et_cardnum_signup);
		final EditText cardMonth_et = ((EditText)findViewById(R.id.et_cardmonth_signup));
		final EditText cardYear_et = ((EditText)findViewById(R.id.et_cardmyear_signup));
		final EditText cardBillingAddress_et = ((EditText)findViewById(R.id.et_cardAddr_signup));

		Button btn_done = (Button)findViewById(R.id.btn_done);
		btn_done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String firstname = firstname_et.getText().toString();
				final String middlename = middlename_et.getText().toString();
				final String lastname = lastname_et.getText().toString();
				final String address = firstname_et.getText().toString();
				final String email = email_et.getText().toString();
				final String username = username_et.getText().toString();
				final String password = password_et.getText().toString();
				final String cPassword = cPassword_et.getText().toString();
				final String cardBillingAddress = cardBillingAddress_et.getText().toString();
				try{
					cardNumber = Integer.parseInt(cardNumber_et.getText().toString());
					cardMonth = Integer.parseInt(cardMonth_et.getText().toString());
					cardYear = Integer.parseInt(cardYear_et.getText().toString());
				} catch( NumberFormatException ex ) {
					//do something if target weight was not a number.
				}

				Toast.makeText(getApplicationContext(), "::"+username+"::"+password+"::"+cPassword, Toast.LENGTH_SHORT).show();

				if(firstname.equals("") || lastname.equals("") || address.equals("") || email.equals("")|| username.equals("")|| password.equals("")|| cPassword.equals("")||cardBillingAddress.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
					return;
				}

				if(password.equals(cPassword))
					CreateAccountData(username,password,firstname,lastname,middlename,address,email,cardNumber,cardMonth,cardYear,cardBillingAddress);
				else
					Toast.makeText(getApplicationContext(), "Passwords dont match", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		finish();
		super.onBackPressed();
	}


	public void CreateAccountData(String username, String pswd, String firstname, String lastname, String middlename, String Address, String email, int cardNumber, int cardMonth, int cardYear, String cardBillingAddress) {

		ContentValues values = new ContentValues();
		values.put(DatabaseHandler.USERNAME, username);
		values.put(DatabaseHandler.PASSWORD, pswd);
		values.put(DatabaseHandler.FIRSTNAME, firstname);
		values.put(DatabaseHandler.LASTNAME, lastname);
		values.put(DatabaseHandler.MIDDLENAME, middlename);
		values.put(DatabaseHandler.ADDRESS, Address);
		values.put(DatabaseHandler.EMAIL, email);
		values.put(DatabaseHandler.CARD_NUMBER, cardNumber);
		values.put(DatabaseHandler.CARD_MONTH, cardMonth);
		values.put(DatabaseHandler.CARD_YEAR, cardYear);
		values.put(DatabaseHandler.CARD_BILLING_ADDR, cardBillingAddress);
		dbHandler.insertCreateAccountData(values);

	}

}
