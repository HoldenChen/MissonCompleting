package com.holden.missioncompleting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.DBManager;
import com.holden.missioncompleting.util.MissionDetails;
import com.holden.missioncompleting.util.MissionListAdapter;

public class MissionListActivity extends Activity {
	//= null;
	private ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String,Object>>();;
	private MissionListAdapter adapter = null;
	DBManager mgr ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission_list);	
		mgr = new DBManager(this);
		adapter = new MissionListAdapter(this,listItem);
		Button btn = (Button)findViewById(R.id.add_btn);
		ListView listView = (ListView)findViewById(R.id.mission_list);
		listView.setAdapter(adapter);
		List<MissionDetails> getdataFromDB= new ArrayList<MissionDetails>() ;
		getdataFromDB = mgr.query();
		
		if(!getdataFromDB.isEmpty()){
			setData(getdataFromDB);
			adapter.notifyDataSetChanged();
		}
		
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent toadd = new Intent(MissionListActivity.this,AddDetailActivity.class);
				toadd.putExtra("M_A", "MaintoAdd");
				startActivity(toadd);
				mgr.closeDB();
			}});
		
		
		
		
		
	}
	
	public void onStart(){
		super.onStart();
		
	}
	public void setData(List <MissionDetails> mdlist){
		for(MissionDetails md : mdlist){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("md-ID", md._id);
			 map.put("mission", md.mission);
			 map.put("startTime", md.starttime);
			 map.put("lastTime", md.lasttime);
			 map.put("score", md.score);
			 int resourceId=0;
			 if(md.color.equals("red")){
				 resourceId = R.drawable.red;
			 }
			 if(md.color.equals("yellow")){
				 resourceId = R.drawable.yellow;
			 }
			 if(md.color.equals("pinkblue")){
				 resourceId = R.drawable.pinkblue;
			 }
			 if(md.color.equals("green")){
				 resourceId = R.drawable.green;
			 }
			 map.put("image", resourceId);
			 listItem.add(map);
		}			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_mission_actionbar, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId()==R.id.deleteActionbarMS){
			Intent deleteMDetailsIntent = new Intent(MissionListActivity.this,EditMissionDetailsActivity.class);
			startActivity(deleteMDetailsIntent);
		}
		
		return super.onOptionsItemSelected(item);
		
	}
	
	protected void onDestory(){
		super.onDestroy();
		System.out.println("MissionListActivity is destory now");
		mgr.closeDB();
		
	}

}
