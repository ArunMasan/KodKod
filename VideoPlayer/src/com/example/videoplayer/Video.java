package com.example.videoplayer;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class Video extends Activity {
	Boolean isPaused = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video);
		final VideoView videoView = (VideoView) findViewById(R.id.videoView);
		final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
		String videoToPlay = "android.resource://com.example.videoplayer/"
				+ R.raw.k;
		Uri video = Uri.parse(videoToPlay);
		videoView.setVideoURI(video);
		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			public void onPrepared(MediaPlayer mp) {
				progressBar.setVisibility(View.GONE);
				videoView.requestFocus();
				videoView.start();
			}
		});

		final Button pause = (Button) findViewById(R.id.pauseButton);
		pause.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (isPaused) {
					videoView.start();
					pause.setText("Pause");
					isPaused = false;
				} else {
					videoView.pause();
					pause.setText("Play");
					isPaused = true;
				}
			}

		});

		final Button fullScreen = (Button) findViewById(R.id.fullScreenButton);
		fullScreen.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				System.out.println("Click");
				Intent intent = new Intent(Video.this,
						VideoActivity.class);
				startActivity(intent);
			}

		});

	}
}