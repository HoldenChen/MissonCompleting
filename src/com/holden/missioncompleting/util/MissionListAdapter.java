package com.holden.missioncompleting.util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.missioncompleting.R;

public class MissionListAdapter extends BaseAdapter {
		int mResourcesId ;
		private LayoutInflater mInfalter;
		ArrayList<HashMap<String, Object>> listItem;
		Context context;
		Calendar c = Calendar.getInstance();
		public MissionListAdapter(Context context,ArrayList<HashMap<String, Object>> item) {
					this.mInfalter = LayoutInflater.from(context);
					this.context = context;
					listItem = item;
		}
		
		@Override
		public int getCount() {
					return listItem.size();
		}
		
		@Override
		public Object getItem(int arg0) {
					return null;
		}
		
		@Override
		public long getItemId(int arg0) {
					return 0;
		}
		public void setResourceId(int i){
			
					mResourcesId = i;
		}
		@Override
		public View getView(int position,View convertView,ViewGroup parent){
					ViewHolder holder= new ViewHolder();
					if(convertView == null)
					{
								convertView = mInfalter.inflate(R.layout.mission_list_item, null);
								holder.image = (ImageView)convertView.findViewById(R.id.itemcolor);
								holder.mission = (TextView)convertView.findViewById(R.id.missiondecription);
								holder.startTime = (TextView)convertView.findViewById(R.id.starttimetvlist);
								holder.lastTime = (TextView)convertView.findViewById(R.id.lasttimetvlist);
								holder.score = (TextView)convertView.findViewById(R.id.scoretvlist);
								convertView.setTag(holder);
					}else{
								holder = (ViewHolder)convertView.getTag();
					}
					
					Object startTimeOb =listItem.get(position).get("startTime");
						
					if(startTimeOb==null){
							startTimeOb = "";
					}
					String missionstr =listItem.get(position).get("mission").toString();
					String startTimestr = startTimeOb.toString();
					String lastTimestr =listItem.get(position).get("lastTime").toString();
					String scorestr =listItem.get(position).get("score").toString();
					String imagestr =listItem.get(position).get("image").toString();
					if(missionstr==null){missionstr = "";}
					if(startTimestr==null){startTimestr = "";}
					if(lastTimestr==null){lastTimestr = "";}
					if(scorestr==null){scorestr = "";}
					if(imagestr==null){imagestr = "";}
					holder.mission.setText(missionstr);
					holder.startTime.setText(startTimestr);
					holder.lastTime.setText(lastTimestr);
					holder.score.setText(scorestr);
					holder.image.setBackgroundResource(Integer.parseInt(imagestr));
					return convertView;
		}
	
		public ArrayList<String> ifStringnull(ArrayList<String> list){
					for(String str :list){
						if(str == null){
							str ="";
						}
					}
					return list;
		}
}


