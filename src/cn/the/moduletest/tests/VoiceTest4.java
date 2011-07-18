package cn.the.moduletest.tests;

import android.app.Activity;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.View;
import android.os.Bundle;
import android.util.Log;
import android.speech.srec.MicrophoneInputStream;

import java.io.InputStream;
import java.io.OutputStream;

import cn.the.moduletest.R;

public class VoiceTest4 extends Activity implements OnClickListener {

	private Button speak_btn;
	private Button stop_btn;
	private Button play_btn;
	private InputStream mMicrophone;
	private Thread mRecordingThread;
	private boolean isStoppedListenning;
	
	@Override
	public void	onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.voicetest4);

		speak_btn = (Button) findViewById(R.id.test4_speak_btn);
		stop_btn = (Button) findViewById(R.id.test4_stop_btn);
		play_btn = (Button) findViewById(R.id.test4_play_btn);

		speak_btn.setOnClickListener(this);
		stop_btn.setOnClickListener(this);
		play_btn.setOnClickListener(this);
		
		try	{
			mMicrophone = new MicrophoneInputStream(8000, 8000*15);	
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		log("start");
	}

	public void stopRecording() {
		log("stop");
	}

	public void playback() {
		log("play");
	}

	public void log(String log) {
		Log.d("voicetest4", log);
	}
}

