package com.holden.missioncompleting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.DBManager;
import com.holden.missioncompleting.util.EditMisisonDetailAdapter;
import com.holden.missioncompleting.util.MissionDetails;

public class EditMissionDetailsActivity extends Activity {
	private ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String,Object>>();;
	private EditMisisonDetailAdapter adapter = null;
	ListView listView =null;
	DBManager mgr ;
	Context context = EditMissionDetailsActivity.this;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editmiddiondetails);
		
		mgr = new DBManager(this);
		adapter = new EditMisisonDetailAdapter(this,listItem);
		 listView = (ListView)findViewById(R.id.editMdLv);
		listView.setAdapter(adapter);
		List<MissionDetails> getdataFromDB= new ArrayList<MissionDetails>() ;
		getdataFromDB = mgr.query();
		
		if(!getdataFromDB.isEmpty()){
			setData(getdataFromDB);
			adapter.notifyDataSetChanged();
		}
		
		//listView.seto
		
		
		
		
		
		
	}
	
	
	public void setData(List <MissionDetails> mdlist){
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.editmd_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == R.id.delete_editmd_menu){
			
			
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	 
}
