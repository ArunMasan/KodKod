package com.techblogon.loginexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
	Paint paint = new Paint();

	public DrawView(Context context) {
		super(context);
		paint.setColor(Color.BLACK);
	}

	@Override
	public void onDraw(Canvas canvas) {
		// canvas.drawLine(200, 200, 200, 600, paint);
		// canvas.drawLine(100, 100, 200, 200, paint);// h

		// canvas.drawLine(20, 0, 0, 20, paint);

		canvas.drawLine(0, 0, 200, 600, paint);
	}

}