package com.holden.missioncompleting.util;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.missioncompleting.R;
import com.holden.missioncompleting.EditMissionDetailsActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

public class EditMisisonDetailAdapter extends BaseAdapter {


	int mResourcesId ;
	private LayoutInflater mInfalter;
	ArrayList<HashMap<String, Object>> listItem;
	ViewHolder holder;
	boolean checkflag;
	
	public EditMisisonDetailAdapter(Context context,ArrayList<HashMap<String, Object>> item) {
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
		holder = new ViewHolder();
		if(convertView == null){
			//View view =convertView;
			convertView = mInfalter.inflate(R.layout.emd_list_item, null);
			holder.image = (ImageView)convertView.findViewById(R.id.itemcolore);
			holder.mission = (TextView)convertView.findViewById(R.id.missiondecriptione);
			holder.startTime = (TextView)convertView.findViewById(R.id.starttimetvliste);
			holder.lastTime = (TextView)convertView.findViewById(R.id.lasttimetvliste);
			holder.score = (TextView)convertView.findViewById(R.id.scoretvliste);
			holder.checkbox = (CheckBox)convertView.findViewById(R.id.mddelete_CB);
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
		holder.checkbox.setOnCheckedChangeListener(new LvCheckedChangeListener(position));
		

		
		return convertView;
		
	}
	
	class LvCheckedChangeListener implements OnCheckedChangeListener {
        private int position;
		public LvCheckedChangeListener(int pos){
			
			position = pos;
		}
		@Override
		public void onCheckedChanged(CompoundButton btn, boolean checkflag) {
			
			// TODO Auto-generated method stub
			int vid = btn.getId();
			if(vid == holder.checkbox.getId()){
				holder.checkbox.setChecked(checkflag);
			}
			
			System.out.println("this is the "+position+"item's checkbox values:"+checkflag);
			
		}
    }
	
	public ArrayList<String> ifStringnull(ArrayList<String> list){
		for(String str :list){
			if(str == null){
				str ="";
			}
		}
		return list;
		
	}
	
	public void deleteCheckedItem(int position){
		listItem.get(position).get("md_ID");
		EditMissionDetailsActivity eda = new EditMissionDetailsActivity();
		//DBManager mgr = new DBManager(eda.context);
		listItem.remove(position);
	}

}
