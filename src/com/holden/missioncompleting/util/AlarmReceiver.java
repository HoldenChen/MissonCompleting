package com.holden.missioncompleting.util;

import com.holden.missioncompleting.ShowAlarmActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "this is the time", Toast.LENGTH_SHORT).show();
//		Intent showAlarmIntent = new Intent(context,ShowAlarmActivity.class);
//		showAlarmIntent.putExtra("Recever", "Receiver_ShowAlarm");
//		context.startActivity(showAlarmIntent);
		int i = 0;
		System.out.println(i);
		i++;
		intent.setClass(context,ShowAlarmActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
		
		
	}

}
