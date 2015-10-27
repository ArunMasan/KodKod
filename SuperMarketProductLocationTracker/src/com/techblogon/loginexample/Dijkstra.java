package com.techblogon.loginexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Dijkstra extends Activity {
	public static String out;
	public static int level1, level2;

	/** Called when the activity is first created. */
	Button button1; // ELEMENTS PRESENT ON THE SCREEN-2 LABELS,1
	// TEXTBOX AND 1 BUTTON
	Spinner spin1, spin2;
	TextView tv;
	private static int count; // DECLARATION OF THE VARIABLES USED IN
	// THE PROGRAM
	static Integer n;
	static int[][] G;
	static int[] d;
	static int[] last_vertex;
	static boolean[] s;
	static int source;
	int flag = 0, flag2 = 0;
	int i = 0, j = 0;
	static int[] path;
	static int var;
	Context context = this;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fromto);
		button1 = (Button) findViewById(R.id.button1);
		// LINKS WITH TE XML ELEMENTS
		tv = (TextView) findViewById(R.id.lbl1);
		// THESE ARE THE VARIOUS "SPOTS", OBVIOUSLY NOT ALL ARE MENTIONED
		final String[] items = new String[] { "Entrance", "Software Eng Book",
				"Wirelesss Book", "Networks Book",
				"Artificial Intelligence Book", "OS Book", "DataBase Book",
				"Neutrogena Face wash", "Powder", "Philips Trimmer",
				"Mac Eye Liner", "Shampoo", "L'orel Mascara" };
		final Spinner spin1 = (Spinner) findViewById(R.id.Spinner01);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spin1.setAdapter(adapter);
		spin1.setSelection(0);

		String[] items2 = new String[] { "Entrance", "Software Eng Book",
				"Wirelesss Book", "Networks Book",
				"Artificial Intelligence Book", "OS Book", "DataBase Book",
				"Neutrogena Face wash", "Powder", "Philips Trimmer",
				"Mac Eye Liner", "Shampoo", "L'orel Mascara" };
		final Spinner spin2 = (Spinner) findViewById(R.id.Spinner02);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, items2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin2.setAdapter(adapter2);
		spin2.setSelection(1);
		button1.setOnClickListener(new OnClickListener()
		// EXECUTES WHEN THE "BUTTON" IS CLICKED
		{
			public void onClick(View v) {
				int a = spin1.getSelectedItemPosition();
				int b = spin2.getSelectedItemPosition();

				// own level

				if (a == 1  || a == 2 || a == 3 || a == 4) {
					level1 = 1;
				} else if (a == 5 || a == 6 || a == 7) {
					level1 = 2;
				} else if (a == 8 || a == 9 || a == 10) {
					level1 = 3;
				} else if (a == 11 || a == 12 || a == 13) {
					level1 = 4;
				}

				if (b == 2 || b == 3 || b == 4) {
					level2 = 1;
				} else if (b == 5 || b == 6 || b == 7) {
					level2 = 2;
				} else if (b == 8 || b == 9 || b == 10) {
					level2 = 3;
				} else if (b == 11 || b == 12 || b == 13) {
					level2 = 4;
				}

				n = 13;
				G = new int[n + 1][n + 1];
				s = new boolean[n + 1];
				for (int x = 1; x <= n; x++)
					for (int y = 1; y <= n; y++) {
						if (x != y)
							G[x][y] = 4000;// +ve infinity
						else
							G[x][y] = 0;
					}
				// THESE ARE THE DISTANCES BETWEEN 2 NODES OF THE GRAPH, IF A
				// DIRECT PATH EXISTS BETWEEN THEM
				/*
				 * G[1][2] = 15; // VALUES WHICH CAN ALSO BE CHANGED AS PER
				 * NEEDS // G[1][3] = 20; // G[1][4] = 15; // G[1][5] = 20;
				 * G[1][6] = 15; // G[1][7] = 20; G[1][8] = 18; // G[1][9] = 23;
				 * G[1][10] = 18; // G[1][11] = 23; G[1][12] = 23; // G[1][13] =
				 * 28; G[1][14] = 23; // G[1][15] = 28; G[1][16] = 25; //
				 * G[1][17] = 30;
				 * 
				 * // 2 row // VALUES WHICH CAN ALSO BE CHANGED AS PER NEEDS
				 * G[2][3] = 5; G[2][4] = 6; // G[2][5] = 10; G[2][6] = 3; //
				 * G[2][7] = 10; G[2][8] = 6; // G[2][9] = 15; G[2][10] = 6; //
				 * G[2][11] = 15; G[2][12] = 10; G[2][14] = 10; G[2][16] = 12;
				 * G[2][3] = 14; // ==
				 * 
				 * // 3 row
				 * 
				 * G[3][5] = 3; G[3][7] = 3; G[3][9] = 6; G[3][11] = 6; G[3][13]
				 * = 10; G[3][15] = 10; G[3][17] = 12; G[3][4] = 5;
				 * 
				 * // 4 row
				 * 
				 * G[4][5] = 5; G[4][8] = 4; G[4][10] = 4; G[4][12] = 7;
				 * G[4][14] = 7; G[4][16] = 9;
				 */
				//

				/*
				 * G[8][9] = 4; G[9][10] = 2; G[5][10] = 3; G[7][10] = 3;
				 * 
				 * G[1][11] = 1; G[11][4] = 1;
				 */

				G[1][2] = 1;
				G[2][3] = 1;
				G[3][4] = 1;
				G[4][7] = 1;
				G[7][6] = 1;
				G[6][5] = 1;
				G[5][2] = 1;

				G[2][1] = 1;
				G[3][2] = 1;
				G[4][3] = 1;
				G[7][4] = 1;
				G[6][7] = 1;
				G[5][6] = 1;
				G[2][5] = 1;
				// II
				G[5][8] = 1;
				G[6][9] = 1;
				G[7][10] = 1;

				G[8][5] = 1;
				G[9][6] = 1;
				G[10][7] = 1;

				G[8][11] = 1;
				G[11][12] = 1;
				G[12][13] = 1;
				G[13][10] = 1;
				G[10][9] = 1;
				G[9][8] = 1;

				G[11][8] = 1;
				G[12][11] = 1;
				G[13][12] = 1;
				G[10][13] = 1;
				G[9][10] = 1;
				G[8][9] = 1;

				source = a + 1;
				int u;
				d = new int[n + 1];
				last_vertex = new int[n + 1];

				for (int i = 1; i <= n; i++) {
					s[i] = false;
					d[i] = G[source][i];
					last_vertex[i] = source;
				}
				s[source] = true;
				d[source] = 0;
				for (int i = 2; i < n; i++)
				// THE MAIN "DIJKSTRA'S" ALGORITHM
				{
					u = choseNextU();
					s[u] = true;
					for (int w = 1; w <= n; w++) {
						if ((G[u][w] != 0) && (s[w] == false)) {
							if (d[w] > d[u] + G[u][w]) {
								d[w] = d[u] + G[u][w];
								// DISTANCE BETWEEN SOURCE AND W, CAN PRINT THIS
								// TOO IF NEEDED
								last_vertex[w] = u;
								// STORES THE LAST BUT ONE VERTEX IN THE
								// SHORTEST PATH FROM SOURCE TO W
							}
						}
					}
				}
				path = new int[100]; // GETS THE PATH
				// IN THE REVERSE DIRECTION
				var = 1;
				Path(b + 1);
				path[var++] = source;
				int[] path2;
				path2 = new int[100];
				for (int i = 1; i <= var - 1; i++) {
					path2[i] = path[var - i]; // ACTUAL PATH(NON-
					// REVERSE)
				}
				out = "Your path should be:\n" + items[source - 1];
				for (int i = 2; i <= var - 1; i++) {
					out += " -> " + items[path2[i] - 1];
				}
				AlertDialog.Builder alertbox = new AlertDialog.Builder(context);
				// PRINTS THE PATH IN AN ALERT BOX
				alertbox.setMessage(out);
				alertbox.setNeutralButton("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {

								// System.exit(0);
								Intent Map = new Intent(
										getApplicationContext(), Show.class);
								startActivity(Map);
							}
						});
				// show it
				alertbox.show();
			}

		});
		// Levels

	}

	static int choseNextU()
	// USED FOR GETTING THE NEXT VERTEX, WHILE COMPUTATON OF THE
	// ALGORITHM
	{
		int minu = -1;
		for (int y = 1; y <= n; y++)
			if (s[y] == false) {
				if (minu == -1) {
					if (d[y] != 0)
						minu = y;
					continue;
				} else if ((d[minu] > d[y]) && (d[y] != 0))
					minu = y;
			}
		return minu;
	}

	static void Path(int k) // GETS THE SHORTEST PATH
	{
		if (k != source) {
			path[var++] = k;
			Path(last_vertex[k]);
		}
	}
}
