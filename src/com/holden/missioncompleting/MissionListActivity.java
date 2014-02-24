package com.holden.missioncompleting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.DBManager;
import com.holden.missioncompleting.util.MissionDetails;
import com.holden.missioncompleting.util.MissionListAdapter;
import com.holden.missioncompleting.util.MyAppalication;

public class MissionListActivity extends Activity {
	//= null;
	private ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String,Object>>();;
	private MissionListAdapter adapter = null;
	DBManager mgr ;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		 MyAppalication.getInstance().addActivity(MissionListActivity.this);
		setContentView(R.layout.activity_mission_list);	
		
		
		Button btn = (Button)findViewById(R.id.add_btn);
		 listView = (ListView)findViewById(R.id.mission_list);
		
		
		
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Intent toadd = new Intent(MissionListActivity.this,AddDetailActivity.class);
				toadd.putExtra("M_A", "MaintoAdd");
				startActivity(toadd);
				
			}});
	}
	
	public void onStart(){
		super.onStart();
		mgr = new DBManager(this);
		List<MissionDetails> getdataFromDB= new ArrayList<MissionDetails>() ;
		getdataFromDB = mgr.query();
		
		if(!getdataFromDB.isEmpty()){
			setData(getdataFromDB);
			adapter = new MissionListAdapter(this,listItem);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
			mgr.closeDB();
		}
	}
	
	//public void 
	public void setData(List <MissionDetails> mdlist){
		listItem.clear();
		for(MissionDetails md : mdlist){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			
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
	
	private long exitTime = 0;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){   
	        if((System.currentTimeMillis()-exitTime) > 2000){  
	            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();                                
	            exitTime = System.currentTimeMillis();   
	        } else {
	        	 MyAppalication.getInstance().exit();
	        }
	        return true;   
	    }
	    return super.onKeyDown(keyCode, event);
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
		//mgr.closeDB();
		
	}

}
