package cn.the.moduletest;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Main extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new SimpleAdapter(this, //getTestsActivities("com.android.soundrecorder"), //can get the soundrecorder app
        		(List<Map<String, ?>>) getTestsActivities("cn.the.moduletest.tests"),
        		android.R.layout.simple_list_item_1, new String[]{"title"},
        		new int[]{android.R.id.text1}));
        getListView().setTextFilterEnabled(true);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map<String,?> map = (Map<String,?>) l.getItemAtPosition(position);
        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
    
    private List<?> getTestsActivities(String path){
    	List<Map<String,?>> myTests = new ArrayList<Map<String,?>>();
    	Intent mIntent = new Intent(Intent.ACTION_MAIN, null);
    	mIntent.addCategory(Intent.CATEGORY_TEST);
    	
    	PackageManager pm = getPackageManager();
    	List<ResolveInfo> list = pm.queryIntentActivities(mIntent, 0);
    	
    	if(null == list) return myTests;
    	
    	int len = list.size();
    	
    	for(int i = 0; i < len; i++){
    		ResolveInfo info = list.get(i);
    		String activityName = info.activityInfo.name;
 
    		if(path.length()== 0 || activityName.startsWith(path)){
    			String[] labelPath = activityName.split("\\.");
    			String nextLabel = labelPath[labelPath.length-1];
    			addItem(myTests, nextLabel, activityIntent(info.activityInfo.packageName,
    					info.activityInfo.name));
    		}
    	}
    	
    	Collections.sort(myTests, sDisplayNameComparator);
    	
    	return myTests;
    }
    
    private final static Comparator<Map<String, ?>> sDisplayNameComparator = new Comparator<Map<String, ?>>() {
        private final Collator collator = Collator.getInstance();

        public int compare(Map<String,?> map1, Map<String,?> map2) {
            return collator.compare(map1.get("title"), map2.get("title"));
        }
    };
    
    private Intent activityIntent(String packagePath, String testName){
    	return new Intent().setClassName(packagePath, testName);
    }
    
    private void addItem(List<Map<String, ?>> tests, String testName, Intent intent){
    	Map<String, Object> temp = new HashMap<String, Object>();
    	temp.put("title", testName);
    	temp.put("intent", intent);
    	tests.add(temp);
    }
}
