package cn.the.moduletest.tests;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;
import android.media.MediaPlayer;

import android.speech.srec.MicrophoneInputStream;
import android.speech.srec.Recognizer;
import android.speech.srec.Recognizer.Grammar;
import android.speech.srec.WaveHeader;
import android.speech.srec.UlawEncoderInputStream;

import java.io.File;
import java.io.InputStream;

import cn.the.moduletest.R;

public class VoiceTest6 extends Activity implements OnClickListener, OnCompletionListener {
	
	private Button speak_btn;
	private Button stop_btn;
	private Button play_btn;
	private TextView mTextView;
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
		mTextView.setText("Test 6");

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
			InputStream audio = new MicrophoneInputStream(11025, 11025*5);
			String cdir = Recognizer.getConfigDir(null);
			mTextView.setText(cdir);
			Recognizer mRecognizer = new Recognizer(cdir + "/baseline11k.par");
			Recognizer.Grammar mGrammar = mRecognizer.new Grammar(cdir + "/grammers/VoiceDialer.g2g");
			mGrammar.setupRecognizer();
			mGrammar.resetAllSlots();
			mGrammar.compile();
			mRecognizer.start();
			while(true){
				switch (mRecognizer.advance()) {
					case Recognizer.EVENT_INCOMPLETE:
					case Recognizer.EVENT_STARTED:
					case Recognizer.EVENT_START_OF_VOICING:
					case Recognizer.EVENT_END_OF_VOICING:
						continue;
					case Recognizer.EVENT_RECOGNITION_RESULT:
						for (int i = 0; i < mRecognizer.getResultCount(); i++) {
							String result = mRecognizer.getResult(i, Recognizer.KEY_LITERAL);
							log(result);
							mTextView.setText(result);
						}
						break;
					case Recognizer.EVENT_NEED_MORE_AUDIO:
						mRecognizer.putAudio(audio);
						continue;
					default:
					    break;
				}
				break;
			}

			mRecognizer.stop();
            mRecognizer.destroy();
            audio.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		//String cdir = Recognizer.getConfigDir(null);
		//mTextView.setText(cdir);
		log("start");
	}

	public void stopRecording() {
		
		log("stop");
	}

	public void playback() {
		
		
		log("play");
	}

	public void log(String log) {
		Log.d("voicetest6", log);
	}
}