package com.holden.missioncompleting;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.AlarmReceiver;

@SuppressLint("NewApi")
public class AddDetailActivity extends Activity {
	private EditText missionDescription = null;
	private Button timePickBtn = null;
	private CheckBox isRing = null;
	private EditText lastTimeet = null;
	private RadioGroup chooseColor = null;
	private String mission = null;
	private Calendar c;
	private int hour;
	private int minute;
	Context context = AddDetailActivity.this;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.adddetail);			
		missionDescription = (EditText)findViewById(R.id.addMissionet);
		timePickBtn = (Button)findViewById(R.id.Settimebtn);
		isRing = (CheckBox)findViewById(R.id.clockRing);
		lastTimeet = (EditText)findViewById(R.id.lasttimeEt);
		chooseColor = (RadioGroup)findViewById(R.id.choosecolorRg);
		mission = missionDescription.getText().toString();
		c = Calendar.getInstance();
		setCurrentTimeOnView();
		timePickBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				//c.setTimeInMillis(System.currentTimeMillis());      
//		           int hour = c.get(Calendar.HOUR_OF_DAY);             
//		           int minute = c.get(Calendar.MINUTE);
		           new TimePickerDialog(context, new OnTimeSetListener(){

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int ominute) {
						// TODO Auto-generated method stub
						hour = hourOfDay;
						minute = ominute;
						timePickBtn.setText(pad(hourOfDay)+":"+pad(ominute));
						
						//c.getTimeInMillis();
						c.set(Calendar.HOUR_OF_DAY, hourOfDay);
						c.set(Calendar.MINUTE, minute);
						c.set(Calendar.SECOND, 0);
						c.set(Calendar.MILLISECOND, 0);
						}	   		   
					}, hour, minute, true).show();
	    
				}});
		
		
		
		
	}
	
	public void setCurrentTimeOnView() {

		

		
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		// set current time into textview
		timePickBtn.setText(new StringBuilder().append(pad(hour)).append(":")
				.append(pad(minute)));

		// set current time into timepicker
		

	}
	
	
	 @SuppressLint("NewApi")
		protected void onStart() { 
		        super.onStart(); 
		        ActionBar actionBar = this.getActionBar(); 
		        actionBar.setDisplayHomeAsUpEnabled(true); 
		    } 
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.adddetailmenu, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId()==android.R.id.home)
		{
			
			finish();
			return true;
		}
		
		if(item.getItemId()==R.id.add){
			mission = missionDescription.getText().toString();
			Toast.makeText(this, mission, Toast.LENGTH_LONG).show();
			if(mission==null||mission.equals("")){
				Toast.makeText(this, "Mission Descrioption can't be empty", Toast.LENGTH_LONG).show();
			}
			if(isRing.isChecked()){
				AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
				Intent alarmintent = new Intent(AddDetailActivity.this,AlarmReceiver.class);
				alarmintent.setAction("ALARM_ACTION");
				PendingIntent alarmpendingIntent = PendingIntent.getBroadcast(AddDetailActivity.this, 0, alarmintent, 0);
				am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), alarmpendingIntent);
			}
		}
		return super.onOptionsItemSelected(item);
	}
	
	private static String pad(int c) {
		if (c >= 10)
			return String.valueOf(c);
		else
			return "0" + String.valueOf(c);
	}

}
