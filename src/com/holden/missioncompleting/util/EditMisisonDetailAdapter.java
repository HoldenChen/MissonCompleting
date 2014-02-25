package com.holden.missioncompleting.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.missioncompleting.R;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class EditMisisonDetailAdapter extends BaseAdapter {

	private static SparseBooleanArray isSelected;
	static int mResourcesId ;
	private LayoutInflater mInfalter;
	ArrayList<HashMap<String, Object>> listItem;
	ViewHolder holder;
	Context context;
	HashMap<Integer,Boolean> checkboxlist;
	
	public EditMisisonDetailAdapter(Context context,ArrayList<HashMap<String, Object>> item) {
		this.mInfalter = LayoutInflater.from(context);
		this.context = context;
		listItem = item;
        isSelected = new SparseBooleanArray();
        initCheckboxlist();
        System.out.println("listem's size"+listItem.size());
	}
	
	 public static SparseBooleanArray getIsSelected() {
	        return isSelected;
	    }
	public void initCheckboxlist(){
		for(int i=0 ; i < listItem.size(); i++){
			isSelected.put(i, false);
		}
	}
	


	    public static void setIsSelected(SparseBooleanArray isSelected) {
	    		EditMisisonDetailAdapter.isSelected = isSelected;
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
	
	public void setCheckboxState(SparseBooleanArray isSelected){
		EditMisisonDetailAdapter.isSelected = isSelected;
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
		holder.checkbox.setChecked(isSelected.get(position));	
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
	
	public  void deleteItem(List<String> deidList){
		
		DBManager mgr = new DBManager(context);
		mgr.deleteOldMisisonDetails(deidList);
	}
}
