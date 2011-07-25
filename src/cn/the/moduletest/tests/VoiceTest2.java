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

public class VoiceTest2 extends Activity implements OnClickListener {

	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	
	private ListView mList;

	@Override
	public void	onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Show UIs
		setContentView(R.layout.voicetest2);

		Button speak_btn = (Button) findViewById(R.id.test2_speak_btn);
		mList = (ListView) findViewById(R.id.test2_words_list);

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
			case R.id.test2_speak_btn:
				startVoiceRecognition();
				break;	
			default:
				break;
		}
	}

	public void startVoiceRecognition() {

        /** 
         *  Older version (android 1.1)
         *  Just have a test
         */
        // Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        // startActivityForResult(intent, 0);

        Intent intent = new Intent("android.speech.RecognitionService");
        startService(intent);
	}

	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getExtras().getStringArrayList("results");
            mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    results));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}