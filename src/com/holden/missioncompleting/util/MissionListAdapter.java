package com.holden.missioncompleting.util;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.missioncompleting.R;

public class MissionListAdapter extends BaseAdapter {
	int mResourcesId ;
	private LayoutInflater mInfalter;
	ArrayList<HashMap<String, Object>> listItem;

	public MissionListAdapter(Context context,ArrayList<HashMap<String, Object>> item) {
		this.mInfalter = LayoutInflater.from(context);
		listItem = item;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItem.size();
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setResourceId(int i){
		
		mResourcesId = i;
	}
	@Override
	public View getView(int position,View convertView,ViewGroup parent){
		ViewHolder holder= new ViewHolder();
		if(convertView == null){
			//View view =convertView;
			convertView = mInfalter.inflate(R.layout.mission_list_item, null);
			holder.image = (ImageView)convertView.findViewById(R.id.itemcolor);
			holder.mission = (TextView)convertView.findViewById(R.id.missiondecription);
			holder.startTime = (TextView)convertView.findViewById(R.id.starttimetvlist);
			holder.lastTime = (TextView)convertView.findViewById(R.id.lasttimetvlist);
			holder.score = (TextView)convertView.findViewById(R.id.scoretvlist);
			convertView.setTag(holder);
//			System.out.println("rresourcesId:"+mResourcesId);
//			image.setBackgroundResource(mResourcesId);
//			//image.setBackground();
//			mission.setText(getItem(position));
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		holder.mission.setText(listItem.get(position).get("mission").toString());
		holder.startTime.setText(listItem.get(position).get("startTime").toString());
		holder.lastTime.setText(listItem.get(position).get("lastTime").toString());
		holder.score.setText(listItem.get(position).get("score").toString());
		holder.image.setBackgroundResource(Integer.parseInt(listItem.get(position).get("image").toString()));
		

		
		return convertView;
		
	}
	

}


