package com.example.missioncompleting;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.util.MissionListAdapter;

public class MissionListActivity extends Activity {
	private ListView listView = null;
	private List<String> list = new ArrayList<String>();
	private MissionListAdapter adapter = null;
	private int resourcesId ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission_list);	
		adapter = new MissionListAdapter(this,list);
		Button btn = (Button)findViewById(R.id.add_btn);
		listView = (ListView)findViewById(R.id.mission_list);
		listView.setAdapter(adapter);
		//setData();
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				setData();
				adapter.notifyDataSetChanged();
			}});
		
		
		
	}
	
	public void setData(){
			list.add("Mission");
			adapter.setResourceId(R.drawable.green);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mission_list, menu);
		return true;
	}

}
