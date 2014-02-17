package com.example.util;

import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.missioncompleting.R;

public class MissionListAdapter extends ArrayAdapter<String> {
	int mResourcesId ;

	public MissionListAdapter(Context context
			, List<String> objects) {
		super(context,0,objects);
		// TODO Auto-generated constructor stub
	}
	public void setResourceId(int i){
		
		mResourcesId = i;
	}
	@Override
	public View getView(int position,View convertView,ViewGroup parent){
		

		View view =convertView;
		view = LayoutInflater.from(getContext()).inflate(R.layout.mission_list_item, null);
		ImageView image = (ImageView)view.findViewById(R.id.itemcolor);
		TextView text = (TextView)view.findViewById(R.id.missiondecription);
		System.out.println("rresourcesId:"+mResourcesId);
		image.setBackgroundResource(mResourcesId);
		//image.setBackground();
		text.setText(getItem(position));
		return view;
		
	}

}
