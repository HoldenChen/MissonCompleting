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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.missioncompleting.R;
import com.holden.missioncompleting.util.AlarmReceiver;
import com.holden.missioncompleting.util.DBManager;
import com.holden.missioncompleting.util.MissionDetails;
import com.holden.missioncompleting.util.MyAppalication;

@SuppressLint("NewApi")
public class AddDetailActivity extends Activity {
	private DBManager mgr;
	private EditText missionDescriptionet = null;
	private Button timePickBtn = null;
	private CheckBox isRingcb = null;
	private EditText lastTimeet = null;
	private EditText scoReet = null;
	private RadioGroup chooseColorrg = null;
	private String mission = null;
	private String scoreStr ;
	private String lastTimeStr;
	private Calendar c;
	private int hour;
	private int minute;
	private RadioButton redRb;
	private RadioButton yellowRb;
	private RadioButton greenRb;
	private RadioButton pinkblueRb;
	private String startTime;
	String setColor = "red";
	Context context = AddDetailActivity.this;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		MyAppalication.getInstance().addActivity(AddDetailActivity.this);
		setContentView(R.layout.adddetail);			
		missionDescriptionet = (EditText)findViewById(R.id.addMissionet);
		timePickBtn = (Button)findViewById(R.id.Settimebtn);
		isRingcb = (CheckBox)findViewById(R.id.clockRing);
		lastTimeet = (EditText)findViewById(R.id.lasttimeEt);
		scoReet = (EditText)findViewById(R.id.missionScore);
		chooseColorrg = (RadioGroup)findViewById(R.id.choosecolorRg);
		redRb = (RadioButton)findViewById(R.id.redrb);
		yellowRb = (RadioButton)findViewById(R.id.yellowrb);
		greenRb = (RadioButton)findViewById(R.id.greenrb);
		pinkblueRb = (RadioButton)findViewById(R.id.pinkbluerb);
	    final int redID =redRb.getId(),yellowID=yellowRb.getId(),greenID=greenRb.getId(),pinkBlueID=pinkblueRb.getId();
		mgr = new DBManager(this);
		mission = missionDescriptionet.getText().toString();
		c = Calendar.getInstance();
		setCurrentTimeOnView();
		chooseColorrg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup rg, int item) {
				// TODO Auto-generated method stub
				if(item == redID){
					setColor = "red";
					System.out.println("you click red");
				}
				if(item == yellowID){
					setColor = "yellow";
					System.out.println("you click yellow");
				}
				if(item == greenID){
					setColor = "green";
					System.out.println("you click green");
				}
				if(item == pinkBlueID){
					setColor = "pinkblue";
					System.out.println("you click pinkBlue");
				}
				System.out.println(setColor);
				
			}});
		timePickBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
		           new TimePickerDialog(context, new OnTimeSetListener(){

					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int ominute) {
						// TODO Auto-generated method stub
						hour = hourOfDay;
						minute = ominute;
						timePickBtn.setText(pad(hourOfDay)+":"+pad(ominute));
						startTime = pad(hourOfDay)+":"+pad(ominute);
						//c.getTimeInMillis();
						c.set(Calendar.HOUR_OF_DAY, hourOfDay);
						c.set(Calendar.MINUTE, minute);
						c.set(Calendar.SECOND, 0);
						c.set(Calendar.MILLISECOND, 0); 
						}	   		   
					}, hour, minute, true).show();
	    
				}});
		
		
		
		
	}
	
	public void onDestory(){
		super.onDestroy();
		mgr.closeDB();
	}
	
	public void setCurrentTimeOnView() {	
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		// set current time into textview
		startTime = pad(hour)+":"+pad(minute);
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
		mission = missionDescriptionet.getText().toString();
		scoreStr = scoReet.getText().toString();
		lastTimeStr = lastTimeet.getText().toString();
		if(item.getItemId()==android.R.id.home)
		{
			
			finish();
			return true;
		}
		
		if(item.getItemId()==R.id.add){
			
			if(checkStr(mission)){
				Toast.makeText(this, "Mission Descrioption can't be empty", Toast.LENGTH_LONG).show();
			}
			if(isRingcb.isChecked()){
				AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
				Intent alarmintent = new Intent(AddDetailActivity.this,AlarmReceiver.class);
				alarmintent.setAction("ALARM_ACTION");
				PendingIntent alarmpendingIntent = PendingIntent.getBroadcast(AddDetailActivity.this, 0, alarmintent, 0);
				am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), alarmpendingIntent);
			}
			if(checkStr(lastTimeStr)){
				Toast.makeText(this, "Planed time can't be empty", Toast.LENGTH_LONG).show();
			}
			if(checkStr(scoreStr)){
				Toast.makeText(this, "Mission's score can't be empty", Toast.LENGTH_LONG).show();
			}
			else{
				MissionDetails md = new MissionDetails();
				md.mission = mission;
				md.starttime = startTime;
				md.lasttime = lastTimeStr;
				md.score = scoreStr;
				md.color = setColor;
				System.out.println(setColor);
				mgr.add(md);
				Intent addTomainIntent = new Intent(AddDetailActivity.this,MissionListActivity.class);
				startActivity(addTomainIntent);
				finish();
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
	
	public boolean checkStr(String str){
		if(str==null||str.equals("")){
			return true;
		}
		else{
			return false;
		}
	}

}
