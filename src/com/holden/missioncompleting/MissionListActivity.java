package com.holden.missioncompleting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.MissionListAdapter;

public class MissionListActivity extends Activity {
	private ListView listView = null;
	private ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String,Object>>();;
	private MissionListAdapter adapter = null;
	static boolean isBackFromAdd = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission_list);	
		adapter = new MissionListAdapter(this,listItem);
		Button btn = (Button)findViewById(R.id.add_btn);
		listView = (ListView)findViewById(R.id.mission_list);
		listView.setAdapter(adapter);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent toadd = new Intent(MissionListActivity.this,AddDetailActivity.class);
				toadd.putExtra("M_A", "MaintoAdd");
				startActivity(toadd);
				//toadd.
			}});
		
		
		
	}
	
	public void onStart(){
		super.onStart();
		if(isBackFromAdd){
			Intent receiveDetailIntent = getIntent();
			Bundle receiveDetailBundle = receiveDetailIntent.getExtras();
			String setMissionlv =receiveDetailBundle.getString("mission");
			String setStartTimelv = receiveDetailBundle.getString("startTime");
			String setLastTimelv = receiveDetailBundle.getString("lastTimeStr");
			String setScorelv = receiveDetailBundle.getString("scoreStr");
			String setColorlv = receiveDetailBundle.getString("setColor");
			if(setMissionlv==null){
				setMissionlv = "";
			}
			if(setStartTimelv==null){
				setStartTimelv = "";
			}
			if(setLastTimelv==null){
				setLastTimelv = "";
			}
			if(setScorelv==null){
				setScorelv = "";
			}
			if(setColorlv==null){
				setColorlv = "";
			}
			
			setData(setMissionlv,setStartTimelv,setLastTimelv,setScorelv,setColorlv);
			adapter.notifyDataSetChanged();
		}
		isBackFromAdd = false;
		
	}
	public void setData(String mission,String startTime,String lastTime,String score,String color){
		 HashMap<String,Object> map = new HashMap<String,Object>();
		 map.put("mission", mission);
		 map.put("startTime", startTime);
		 map.put("lastTime", lastTime);
		 map.put("score", score);
		 int resourceId=0;
		 if(color.equals("red")){
			 resourceId = R.drawable.red;
		 }
		 if(color.equals("yellow")){
			 resourceId = R.drawable.yellow;
		 }
		 if(color.equals("pinkblue")){
			 resourceId = R.drawable.pinkblue;
		 }
		 if(color.equals("green")){
			 resourceId = R.drawable.green;
		 }
		 map.put("image", resourceId);
		 listItem.add(map);
		 
			//list.add(map);
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mission_list, menu);
		return true;
	}

}
