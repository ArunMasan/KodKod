package com.example.videoplayer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	Boolean isPaused = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		VideoView videoView = (VideoView) findViewById(R.id.videoView);
		MediaController mediaController = new MediaController(this);
		mediaController.setAnchorView(videoView);
		String videoToPlay = "android.resource://com.example.videoplayer/"
				+ R.raw.k;
		Uri video = Uri.parse(videoToPlay);
		videoView.setMediaController(mediaController);
		videoView.setVideoURI(video);
		videoView.start();
	}
}