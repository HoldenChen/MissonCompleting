package com.holden.missioncompleting.util;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeFormat {
	Calendar c = Calendar.getInstance();
	
	public long getCurrentTimeMilSec(){
		return c.getTimeInMillis();
	}
	
	public String getCurrentTimeStr(){
		return "";
	}
	
	public String milSecToDate(){
		return "";
	}
	
	@SuppressLint("SimpleDateFormat")
	public long StrTimeToMilSec(String timestr){
		long ltime = 0;
		try {
			 c.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(timestr));
			 ltime = c.getTimeInMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ltime;
	}
	
	
	public static void main(String [] arg){
		TimeFormat tf = new TimeFormat();
		System.out.println(tf.StrTimeToMilSec("20140303143650"));
		System.out.println(tf.getCurrentTimeMilSec());
		
	}

}
