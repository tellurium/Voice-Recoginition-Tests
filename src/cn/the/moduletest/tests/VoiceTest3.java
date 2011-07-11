package cn.the.moduletest.tests;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.media.MediaRecorder;
import android.util.Log;

import java.io.IOException;
import java.io.File;

import cn.the.moduletest.R;

public class VoiceTest3 extends Activity implements OnClickListener {
	
	//Initialize the recorder
	private MediaRecorder recorder;
	private Button speak_btn;
	private Button stop_btn;

	private static final String PATH = "/sdcard/HJenglish";


	@Override
	public void	onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.voicetest3);

		speak_btn = (Button)findViewById(R.id.test3_speak_btn);
		speak_btn.setOnClickListener(this);
		stop_btn = (Button)findViewById(R.id.test3_stop_btn);
		stop_btn.setOnClickListener(this);

		recorder = new MediaRecorder();
		recorder.setAudioSource(MediaRecorder.AudioSource.MIC); //set microphone
		recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // set output format
		recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // set encoder

		File dir = new File(PATH);
		if (!dir.exists()) {
			Log.d("voicetest3-oncreate", "in dir exist");
			dir.mkdir();
		}
		File temp = new File(PATH, "test.3gp");
		if (!temp.exists()) {
			Log.d("voicetest3-oncreate", "in dir exist");
			try {
				temp.createNewFile();				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.test3_speak_btn:
				try {
					recorder = new MediaRecorder();
					recorder.setAudioSource(MediaRecorder.AudioSource.MIC); //set microphone
					recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); // set output format
					recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); // set encoder
					recorder.setOutputFile(PATH + "/test.3gp"); // set output file path
					recorder.prepare();
				} catch (IOException e) {
					e.printStackTrace();
				}
				recorder.start();		
				break;
			case R.id.test3_stop_btn:
				recorder.stop();
				recorder.release();
				break;
			default:
				break;
		}
	}
}