package com.holden.missioncompleting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.DBManager;
import com.holden.missioncompleting.util.EditMisisonDetailAdapter;
import com.holden.missioncompleting.util.MissionDetails;
import com.holden.missioncompleting.util.ViewHolder;

public class EditMissionDetailsActivity extends Activity {
	private ArrayList<HashMap<String, Object>> listItem =new ArrayList<HashMap<String,Object>>();;
	private EditMisisonDetailAdapter adapter = null;
	ListView listView =null;
	DBManager mgr ;
	List<MissionDetails> getdataFromDB;
	Context context = EditMissionDetailsActivity.this;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editmiddiondetails);
		
		mgr = new DBManager(this);
			Button selectall = (Button)findViewById(R.id.selectAll);
			Button deSelectall = (Button)findViewById(R.id.deSelectAll);
		 listView = (ListView)findViewById(R.id.editMdLv);
		
		// getdataFromDB= new ArrayList<MissionDetails>() ;
		getdataFromDB = mgr.query();
		
		if(!getdataFromDB.isEmpty()){
			setData(getdataFromDB);
			adapter = new EditMisisonDetailAdapter(this,listItem);
			listView.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
		
		listView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println("you clicked me: "+ arg2);
				ViewHolder holder = (ViewHolder) arg1.getTag();
				holder.checkbox.toggle();
				EditMisisonDetailAdapter.getIsSelected().put(arg2, holder.checkbox.isChecked());
			}});
		
		selectall.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for(int i =0; i<listItem.size();i++){
					EditMisisonDetailAdapter.getIsSelected().put(i,true);
				}
				adapter.notifyDataSetChanged();
				
				
			}});
		
		deSelectall.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				for(int i =0; i<listItem.size();i++){
					if(EditMisisonDetailAdapter.getIsSelected().get(i)){
						EditMisisonDetailAdapter.getIsSelected().put(i,false);
					}else{
						EditMisisonDetailAdapter.getIsSelected().put(i,true);
					}
					
				}
				
				adapter.notifyDataSetChanged();
				
			}});
		
		
		
		
		
		
	}
	
	
	public void setData(List <MissionDetails> mdlist){
		for(MissionDetails md : mdlist){
			 HashMap<String,Object> map = new HashMap<String,Object>();
			 map.put("md_ID", md._id);
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
		if(item.getItemId() == android.R.id.home){
			finish();
			return true;
		}
		if(item.getItemId() == R.id.delete_editmd_menu){
			
			for(int i = 0; i <listItem.size() ;i++){
				int j = 0;
				String [] deid  = new String[listItem.size()];
				//isSe[i] = EditMisisonDetailAdapter.getIsSelected().get(i);
				int deletePosition []=new int[listItem.size()];
				if(EditMisisonDetailAdapter.getIsSelected().get(i)){
					deid[j] = listItem.get(i).get("md_ID").toString();
					deletePosition[j] = i;
					j++;
					
					//String id = listItem.get(i).get("md_ID").toString();		
				}
				for(int m = 0; m <j ;m++){
					listItem.remove(m);
				}
				adapter.deleteItem(deid);
				adapter.notifyDataSetChanged();
			}
			
			
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void onStart(){
		
		super.onStart();
		ActionBar actionBar = this.getActionBar(); 
        actionBar.setDisplayHomeAsUpEnabled(true); 
	}
	
	 
}
