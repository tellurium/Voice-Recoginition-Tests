package cn.the.moduletest.tests;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import android.content.res.Resources;
import android.os.SystemClock;
import android.util.Log;
import android.os.Environment;


import cn.the.moduletest.R;

public class LittleTest1 extends Activity {

	// private final long timeValue = 70000L;
	// private Handler mHandler;
	// private Runnable mTask;

	private TextView timerView;	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.littletest1);

		initResourceRefs();
		initDefaultValue();

	}


	/*
	 * Initialize refenrences to the views
	 */
	private void initResourceRefs() {
		timerView = (TextView) findViewById(R.id.littletest1_timerview);
	}

	/*
	 * Initialize default value
	 */
	private void initDefaultValue() {
	 	String text1 = Environment.getDataDirectory().toString();
	 	String text2 = Environment.getDownloadCacheDirectory().toString();
	 	String text3 = Environment.getExternalStorageDirectory().toString();
	 	
	 	
	 	timerView.setText(text1+"#"+text2+"#"+text3);
	 	// mHandler = new Handler();

	 	// mTask = new Runnable() {
	 	// 	Resources res = getResources();
	 	// 	String timeStr = "";
	 	// 	long t = timeValue;

	 	// 	@Override
	 	// 	public void run() {
	 	// 		if (t < 60000) {
	 	// 			timeStr = String.format(res.getString(R.string.sec_available), t);
	 	// 		} else if (timeValue < 540000) {
	 	// 			timeStr = String.format(res.getString(R.string.min_available), t/60 + 1);
   //      			timerView.setText(timeStr);
	 	// 		}
	 	// 		t -= 1000;
	 	// 		Log.d("initDefautValue", t+"");
	 	// 		mHandler.postDelayed(this, 1000);

	 	// 	}
	 	// };

	}


	@Override
	public void onPause(){

		super.onPause();
		//一定要在暂停后移除runnable对象，否则它会在后台默默的运行直到天荒地老

		//mHandler.removeCallbacks(mTask);

	}

	@Override
	public void onResume(){

		super.onResume();

		//mHandler.postDelayed(mTask, 100);	
	}



}