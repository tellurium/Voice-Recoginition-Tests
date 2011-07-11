package cn.the.moduletest.tests;

import android.view.View.OnClickListener;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.the.moduletest.R;

public class VoiceTest1 extends Activity implements OnClickListener {

	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	
	private ListView mList;

	@Override
	public void	onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Show UIs
		setContentView(R.layout.voicetest1);

		Button speak_btn = (Button) findViewById(R.id.test1_speak_btn);
		mList = (ListView) findViewById(R.id.test1_words_list);

		PackageManager pm = getPackageManager();
		List<ResolveInfo> activities = pm.queryIntentActivities(
				new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);

		if (activities.size() != 0) {
			speak_btn.setOnClickListener(this);
		} else {
			speak_btn.setEnabled(false);
			speak_btn.setText("Recognizer not present");
		}
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.test1_speak_btn:
				startVoiceRecognition();
				break;	
			default:
				break;
		}
	}

	public void startVoiceRecognition() {

		/** 
		 *	android Sample source code
		 *  It doesn't work
		 *  I don't know why
		 */
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech recognition demo");
        startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
	}

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            // Fill the list view with the strings the recognizer thought it could have heard
            ArrayList<String> matches = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    matches));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}