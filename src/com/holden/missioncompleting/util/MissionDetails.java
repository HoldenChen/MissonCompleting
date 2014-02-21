package com.holden.missioncompleting.util;

public class MissionDetails {
	public String mission;
	public String starttime;
	public String lasttime;
	public String score;
	public String color;
	
	public MissionDetails(){}
	
	public MissionDetails(String mission,String starttime,String lasttime,String score,String color){
		this.mission = mission;
		this.starttime = starttime;
		this.lasttime = lasttime;
		this.score = score;
		
	}

}
