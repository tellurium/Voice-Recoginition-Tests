package cn.the.moduletest.tests;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.util.Log;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.provider.MediaStore;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.io.File;

import cn.the.moduletest.R;

public class VoiceTest3 extends Activity implements OnClickListener, OnCompletionListener {
	
	private static final int RECORD_QUEST = 8888;
	private Button speak_btn, play_btn;
	private Uri audioFileUri;


	@Override
	public void	onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.voicetest3);

		speak_btn = (Button)findViewById(R.id.test3_speak_btn);
		speak_btn.setOnClickListener(this);
		play_btn = (Button)findViewById(R.id.test3_play_btn);
		play_btn.setOnClickListener(this);
		play_btn.setEnabled(false);


	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.test3_speak_btn:
				//call the default recorder application "SoundRecorder"
				Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
				startActivityForResult(intent, RECORD_QUEST);
				break;
			case R.id.test3_play_btn:
				MediaPlayer mPlayer = MediaPlayer.create(this, audioFileUri);
				mPlayer.setOnCompletionListener(this);
				mPlayer.start();
				play_btn.setEnabled(false);
				break;
			default:
				break;
		}
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		play_btn.setEnabled(true);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == RECORD_QUEST) {
			audioFileUri = data.getData();
			play_btn.setEnabled(true);
			Log.d("VoiceTest3", audioFileUri.toString());
		}
	}
}