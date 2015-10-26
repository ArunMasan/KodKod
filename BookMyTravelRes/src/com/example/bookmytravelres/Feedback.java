package com.example.bookmytravelres;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RatingBar;

public class Feedback extends Activity {
	DatabaseHandler dbHandler = null;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotelfeedbackfragment);

		dbHandler = new DatabaseHandler(this);
		final MultiAutoCompleteTextView commentstextbox = ((MultiAutoCompleteTextView) findViewById(R.id.comments));
		final EditText Answer1textbox = (EditText) findViewById(R.id.Answer1);
		final EditText Answer2textbox = (EditText) findViewById(R.id.Answer2);
		final EditText Answer3textbox = (EditText) findViewById(R.id.Answer3);
		final RatingBar ratingbox = (RatingBar) findViewById(R.id.ratingBar);
		final MultiAutoCompleteTextView recommendationbox = ((MultiAutoCompleteTextView) findViewById(R.id.Recommendation));

		Button submit = (Button) findViewById(R.id.SubmitButton);
		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String comments1 = commentstextbox.getText().toString();
				final String Answer1 = Answer1textbox.getText().toString();
				final String Answer2 = Answer2textbox.getText().toString();
				final String Answer3 = Answer3textbox.getText().toString();
				final float rating = ratingbox.getRating();
				final String recommendation = recommendationbox.getText()
						.toString();

			}

		});
	}

}
