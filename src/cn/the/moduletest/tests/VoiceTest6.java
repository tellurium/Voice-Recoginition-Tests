package cn.the.moduletest.tests;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;
import android.media.MediaPlayer;

import java.io.File;

import cn.the.moduletest.R;

public class VoiceTest5 extends Activity implements OnClickListener, OnCompletionListener {
	
	private Button speak_btn;
	private Button stop_btn;
	private Button play_btn;
	private TextView mTextView;
	private MediaRecorder mRecorder;
	private MediaPlayer mPlayer;
	private File audioFile;

	@Override
	public void	onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.voicetest4);
		
		speak_btn = (Button) findViewById(R.id.test4_speak_btn);
		stop_btn = (Button) findViewById(R.id.test4_stop_btn);
		play_btn = (Button) findViewById(R.id.test4_play_btn);
		mTextView = (TextView) findViewById(R.id.text_view);

		speak_btn.setOnClickListener(this);
		stop_btn.setOnClickListener(this);
		play_btn.setOnClickListener(this);
		mTextView.setText("Test 5");

		mRecorder = new MediaRecorder();
		mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/YesHJ/voicetest/");
		path.mkdirs();
		try {
			audioFile = File.createTempFile("recording", ".3gp", path);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mRecorder.setOutputFile(audioFile.getAbsolutePath());

		mPlayer = new MediaPlayer();
		mPlayer.setOnCompletionListener(this);

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.test4_speak_btn:
				startRecording();
				break;
			case R.id.test4_stop_btn:
				stopRecording();
				break;
			case R.id.test4_play_btn:
				playback();
				break;
			default: 
				break;	
		}
	}

	public void startRecording() {
		try {
			mRecorder.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mRecorder.start();

		log("start");
	}

	public void stopRecording() {
		mRecorder.stop();

		mRecorder.release();

		try {
			mPlayer.setDataSource(audioFile.getAbsolutePath());
			mPlayer.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log("stop");
	}

	public void playback() {
		
		mPlayer.start();		
		log("play");
	}

	public void log(String log) {
		Log.d("voicetest5", log);
	}
}