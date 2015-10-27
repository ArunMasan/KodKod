package com.techblogon.loginexample;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MapPro extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// LinearLayOut Setup
		RelativeLayout relativeLayout = new RelativeLayout(this);

		// Row1 - Colm1
		// ImageView Setup
		ImageView imageView1 = new ImageView(this);
		// setting image resource
		imageView1.setImageResource(R.drawable.grass);
		// setting image position

		imageView1.setX(150); // Left
		imageView1.setY(200); // Top

		// adding view to layout
		relativeLayout.addView(imageView1);

		// ==================================
		ImageView imageView2 = new ImageView(this);
		// setting image resource
		imageView2.setImageResource(R.drawable.grass);
		// setting image position

		imageView2.setX(200);
		imageView2.setY(200);

		// adding view to layout
		relativeLayout.addView(imageView2);
		// =====================================
		ImageView imageView3 = new ImageView(this);
		// setting image resource
		imageView3.setImageResource(R.drawable.grass);
		// setting image position

		imageView3.setX(250);
		imageView3.setY(200);

		// adding view to layout
		relativeLayout.addView(imageView3);
		// ====================================
		// Row1 - Colm2
		// ImageView Setup
		ImageView imageView11 = new ImageView(this);
		// setting image resource
		imageView11.setImageResource(R.drawable.grass);
		// setting image position

		imageView11.setX(450);
		imageView11.setY(200);

		// adding view to layout
		relativeLayout.addView(imageView11);

		// ==================================
		ImageView imageView12 = new ImageView(this);
		// setting image resource
		imageView12.setImageResource(R.drawable.grass);
		// setting image position

		imageView12.setX(500);
		imageView12.setY(200);

		// adding view to layout
		relativeLayout.addView(imageView12);
		// =====================================
		ImageView imageView13 = new ImageView(this);
		// setting image resource
		imageView13.setImageResource(R.drawable.grass);
		// setting image position

		imageView13.setX(550);
		imageView13.setY(200);

		// adding view to layout
		relativeLayout.addView(imageView13);
		// ====================================

		// Row2-Colm1
		// ImageView Setup
		ImageView imageView4 = new ImageView(this);
		// setting image resource
		imageView4.setImageResource(R.drawable.grass);
		// setting image position

		imageView4.setX(150);
		imageView4.setY(400);

		// adding view to layout
		relativeLayout.addView(imageView4);

		// ==================================
		ImageView imageView5 = new ImageView(this);
		// setting image resource
		imageView5.setImageResource(R.drawable.grass);
		// setting image position

		imageView5.setX(250);
		imageView5.setY(400);

		// adding view to layout
		relativeLayout.addView(imageView5);
		// =====================================
		ImageView imageView6 = new ImageView(this);
		// setting image resource
		imageView6.setImageResource(R.drawable.grass);
		// setting image position

		imageView6.setX(300);
		imageView6.setY(400);

		// adding view to layout
		relativeLayout.addView(imageView6);
		// =====================================
		// Row2 - Colm2
		// ImageView Setup
		ImageView imageView21 = new ImageView(this);
		// setting image resource
		imageView21.setImageResource(R.drawable.grass);
		// setting image position

		imageView21.setX(450);
		imageView21.setY(400);

		// adding view to layout
		relativeLayout.addView(imageView21);

		// ==================================
		ImageView imageView22 = new ImageView(this);
		// setting image resource
		imageView22.setImageResource(R.drawable.grass);
		// setting image position

		imageView22.setX(500);
		imageView22.setY(400);

		// adding view to layout
		relativeLayout.addView(imageView22);
		// =====================================
		ImageView imageView23 = new ImageView(this);
		// setting image resource
		imageView23.setImageResource(R.drawable.grass);
		// setting image position

		imageView23.setX(550);
		imageView23.setY(400);

		// adding view to layout
		relativeLayout.addView(imageView23);
		// ===============================
		// Row3-Colm1
		// ImageView Setup
		ImageView imageView7 = new ImageView(this);
		// setting image resource
		imageView7.setImageResource(R.drawable.grass);
		// setting image position

		imageView7.setX(150);
		imageView7.setY(600);

		// adding view to layout
		relativeLayout.addView(imageView7);

		// ==================================
		ImageView imageView8 = new ImageView(this);
		// setting image resource
		imageView8.setImageResource(R.drawable.grass);
		// setting image position

		imageView8.setX(250);
		imageView8.setY(600);

		// adding view to layout
		relativeLayout.addView(imageView8);
		// =====================================
		ImageView imageView9 = new ImageView(this);
		// setting image resource
		imageView9.setImageResource(R.drawable.grass);
		// setting image position

		imageView9.setX(300);
		imageView9.setY(600);

		// adding view to layout
		relativeLayout.addView(imageView9);
		// =========================================
		// Row3 - Colm2
		// ImageView Setup
		ImageView imageView31 = new ImageView(this);
		// setting image resource
		imageView31.setImageResource(R.drawable.grass);
		// setting image position

		imageView31.setX(450);
		imageView31.setY(600);

		// adding view to layout
		relativeLayout.addView(imageView31);

		// ==================================
		ImageView imageView32 = new ImageView(this);
		// setting image resource
		imageView32.setImageResource(R.drawable.grass);
		// setting image position

		imageView32.setX(500);
		imageView32.setY(600);

		// adding view to layout
		relativeLayout.addView(imageView32);
		// =====================================
		ImageView imageView33 = new ImageView(this);
		// setting image resource
		imageView33.setImageResource(R.drawable.grass);
		// setting image position

		imageView33.setX(550);
		imageView33.setY(600);

		// adding view to layout
		relativeLayout.addView(imageView33);
		// =============================

		// make visible to program
		 setContentView(relativeLayout);

		//setContentView(R.layout.activity_main);
		// ===============================================

		// ArrayList<Tile> tiles;
		// Iterator<Tile> tileIterator;

		// tiles = new ArrayList<Tile>();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int R = (int) ((Math.random() * (2 - 0) + 0));
				if (R == 0) {
					// tiles.add(new Tile(new Texture("grassCenterBlock.png"),
					// i * 50, j * 50, 50, 50));

				}
			}
		}
	}

}
