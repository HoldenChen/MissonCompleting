package com.holden.missioncompleting.util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
	private int hour;
	private int minute;
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
							holder.delayBtn = (Button)convertView.findViewById(R.id.delayBtn);
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
				holder.delayBtn.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View arg0) {
					final View view = 	LayoutInflater.from(context).inflate(R.layout.delaydialogview, null);
//						new AlertDialog.Builder(context).setTitle("Set Your Delay Time").setView(view).setPositiveButton("OK", new DialogInterface.OnClickListener(){
//
//						//	@Override
//						//	public void onClick(View arg0) {
//								// TODO Auto-generated method stub
//								
//							}).show();

						// TODO Auto-generated method stub
//				           new TimePickerDialog(context, new OnTimeSetListener(){
//
//							@Override
//							public void onTimeSet(TimePicker view, int hourOfDay, int ominute) {
//								// TODO Auto-generated method stub
//								hour = hourOfDay;
//								minute = ominute;
//								//timePickBtn.setText(pad(hourOfDay)+":"+pad(ominute));
//								String new_startTime = pad(hourOfDay)+":"+pad(ominute);
//								//c.getTimeInMillis();
//								c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//								c.set(Calendar.MINUTE, minute);
//								c.set(Calendar.SECOND, 0);
//								c.set(Calendar.MILLISECOND, 0);
//								}	   		   
//							}, hour, minute, true).show();
			    
						
					}});
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
	
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}
	

}


