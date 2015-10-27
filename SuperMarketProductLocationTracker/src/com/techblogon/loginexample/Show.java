package com.techblogon.loginexample;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Show extends Activity {

	TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trialmap);

		ImageView lineSEHor = (ImageView) findViewById(R.id.lineSEHor);
		ImageView lineNWHor = (ImageView) findViewById(R.id.lineNWHor);
		ImageView lineNWVer = (ImageView) findViewById(R.id.lineNWVer);

		ImageView lineSEVer = (ImageView) findViewById(R.id.lineSEVer);
		ImageView lineAIVer = (ImageView) findViewById(R.id.lineAIVer);

		ImageView lineAIHor = (ImageView) findViewById(R.id.lineAIHor);
		ImageView lineDBHor = (ImageView) findViewById(R.id.lineDBHor);

		ImageView lineFWVer = (ImageView) findViewById(R.id.lineFWVer);
		ImageView lineELVer = (ImageView) findViewById(R.id.lineELVer);

		ImageView lineELHor = (ImageView) findViewById(R.id.lineELHor);
		ImageView lineMasHor = (ImageView) findViewById(R.id.lineMasHor);

		ImageView lineDBVer = (ImageView) findViewById(R.id.lineDBVer);

		ImageView lineFWHor = (ImageView) findViewById(R.id.lineFWHor);
		ImageView lineMasVer = (ImageView) findViewById(R.id.lineMasVer);
		ImageView lineTrimVer = (ImageView) findViewById(R.id.lineTrimVer);
		ImageView lineTrimHoz = (ImageView) findViewById(R.id.lineTrimHoz);

		ImageView lineOSHor = (ImageView) findViewById(R.id.lineOSHor);
		ImageView lineWLHor = (ImageView) findViewById(R.id.lineWLHor);

		ImageView linePHor = (ImageView) findViewById(R.id.linePHor);
		ImageView lineSHor = (ImageView) findViewById(R.id.lineSHor);

		// To draw line
		// View ivHorizontalLine = findViewById(R.id.ivhorizontalline);

		// TranslateAnimation horizontalLineAnimation = new TranslateAnimation(
		// 100, 500, 100, 600);
		// horizontalLineAnimation.setDuration(100000);

		// canvas.drawLine(10, 10, 90, 10, paint);

		// ivHorizontalLine.startAnimation(horizontalLineAnimation);

		text = (TextView) findViewById(R.id.textView20);
		text.setText(Dijkstra.out);

		lineSEHor.setVisibility(View.INVISIBLE);
		lineNWHor.setVisibility(View.INVISIBLE);

		lineSEVer.setVisibility(View.INVISIBLE);
		lineAIVer.setVisibility(View.INVISIBLE);

		lineAIHor.setVisibility(View.INVISIBLE);
		lineDBHor.setVisibility(View.INVISIBLE);

		lineFWVer.setVisibility(View.INVISIBLE);
		lineELVer.setVisibility(View.INVISIBLE);

		lineELHor.setVisibility(View.INVISIBLE);
		lineMasHor.setVisibility(View.INVISIBLE);

		lineNWVer.setVisibility(View.INVISIBLE);
		lineDBVer.setVisibility(View.INVISIBLE);

		lineFWHor.setVisibility(View.INVISIBLE);
		lineMasVer.setVisibility(View.INVISIBLE);
		lineTrimVer.setVisibility(View.INVISIBLE);
		lineTrimHoz.setVisibility(View.INVISIBLE);
		lineWLHor.setVisibility(View.INVISIBLE);
		lineOSHor.setVisibility(View.INVISIBLE);
		linePHor.setVisibility(View.INVISIBLE);
		lineSHor.setVisibility(View.INVISIBLE);

		// 1 sections
		// 1,1
		if (Dijkstra.out.contains("Software Eng Book")) {
			lineSEHor.setVisibility(View.VISIBLE);
			lineSEVer.setVisibility(View.VISIBLE);

			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineSEHor.setVisibility(View.INVISIBLE);
			} else if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineSEVer.setVisibility(View.INVISIBLE);
				lineSEHor.setVisibility(View.VISIBLE);
			}
			if (Dijkstra.out.contains("Entrance")) {
				lineSEHor.setVisibility(View.INVISIBLE);
				lineSEVer.setVisibility(View.VISIBLE);
			}

		}

		// 1,2
		if (Dijkstra.out.contains("Networks Book")) {
			lineNWHor.setVisibility(View.VISIBLE);
			lineNWVer.setVisibility(View.VISIBLE);

			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineNWHor.setVisibility(View.INVISIBLE);
			} else if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineNWVer.setVisibility(View.INVISIBLE);
				lineNWHor.setVisibility(View.VISIBLE);
			}
		}

		// 1,3
		if (Dijkstra.out.contains("Artificial")) {
			lineAIHor.setVisibility(View.VISIBLE);
			lineAIVer.setVisibility(View.VISIBLE);
			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineAIHor.setVisibility(View.INVISIBLE);
			}
			if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineAIVer.setVisibility(View.INVISIBLE);
				lineAIHor.setVisibility(View.VISIBLE);
			}
			if (Dijkstra.out.contains("Powder")) {
				lineAIHor.setVisibility(View.VISIBLE);
			}
			if (Dijkstra.out.contains("Eye")) {
				lineAIVer.setVisibility(View.INVISIBLE);
				lineAIHor.setVisibility(View.VISIBLE);
			}
			if (Dijkstra.out.contains("-> Entrance")) {
				lineAIHor.setVisibility(View.INVISIBLE);
				lineAIVer.setVisibility(View.VISIBLE);
			}

		}

		// 1,4
		if (Dijkstra.out.contains("DataBase")) {
			lineDBHor.setVisibility(View.VISIBLE);
			// lineDBVer.setVisibility(View.VISIBLE);
			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineDBHor.setVisibility(View.INVISIBLE);
			} else if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineDBVer.setVisibility(View.INVISIBLE);
				lineDBHor.setVisibility(View.VISIBLE);
			}
		}

		// 1,4
		if (Dijkstra.out.contains("Wireless")) {
			lineWLHor.setVisibility(View.VISIBLE);

		}

		// 1,4
		if (Dijkstra.out.contains("OS")) {
			lineOSHor.setVisibility(View.VISIBLE);
			// lineDBVer.setVisibility(View.VISIBLE);
		}

		// 2 section
		// 1,1
		if (Dijkstra.out.contains("Neutrogena Face wash")) {
			lineFWVer.setVisibility(View.VISIBLE);
			lineFWHor.setVisibility(View.VISIBLE);
			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineFWHor.setVisibility(View.INVISIBLE);
			}
			if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineFWVer.setVisibility(View.INVISIBLE);
				lineFWHor.setVisibility(View.VISIBLE);
			}
			if (Dijkstra.out.contains("-> Entrance")) {
				lineFWHor.setVisibility(View.INVISIBLE);
				lineFWVer.setVisibility(View.VISIBLE);
			}
		}

		// 2,1
		if (Dijkstra.out.contains("Mac Eye Liner")) {
			lineELVer.setVisibility(View.VISIBLE);
			lineELHor.setVisibility(View.VISIBLE);
			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineELHor.setVisibility(View.INVISIBLE);
			}
			if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineELVer.setVisibility(View.INVISIBLE);
				lineELHor.setVisibility(View.VISIBLE);
			}
			if (Dijkstra.out.contains("-> Entrance")) {
				lineELHor.setVisibility(View.VISIBLE);
				lineELVer.setVisibility(View.VISIBLE);
			}
		}

		// 1,2
		if (Dijkstra.out.contains("Philips Trimmer")) {

			lineTrimVer.setVisibility(View.VISIBLE);
			lineTrimHoz.setVisibility(View.VISIBLE);
			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineTrimHoz.setVisibility(View.INVISIBLE);
			} else if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineTrimVer.setVisibility(View.INVISIBLE);
				lineTrimHoz.setVisibility(View.VISIBLE);
			}
		}

		// 2,2
		if (Dijkstra.out.contains("L'orel Mascara")) {
			lineMasHor.setVisibility(View.VISIBLE);
			lineMasVer.setVisibility(View.VISIBLE);
			if (Dijkstra.level2 - Dijkstra.level1 > 0) {
				lineMasHor.setVisibility(View.INVISIBLE);
			} else if (Dijkstra.level2 - Dijkstra.level1 == 0) {
				lineMasVer.setVisibility(View.INVISIBLE);
				lineMasHor.setVisibility(View.VISIBLE);
			}
		}

		if (Dijkstra.out.contains("Powder")) {
			linePHor.setVisibility(View.VISIBLE);
		}

		if (Dijkstra.out.contains("Shampoo")) {
			lineSHor.setVisibility(View.VISIBLE);
		}
	}
}