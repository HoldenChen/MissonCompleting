package com.holden.missioncompleting.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
		private MySQLiteOpenHelper helper;
		private SQLiteDatabase db;
		
		public DBManager(Context context){
			helper = new MySQLiteOpenHelper(context);
			db = helper.getWritableDatabase();
		}
		
		public void add(MissionDetails md){
			db.beginTransaction();
			try{
				
					db.execSQL("INSERT INTO missiondetails VALUES(null,?,?,?,?,?)",new Object[]{md.mission,md.starttime,md.lasttime,md.score,md.color});
			
				
				db.setTransactionSuccessful();
			}finally{
				db.endTransaction();
			}
			System.out.println("add successfully");
			
		}
		
		public void updateStartTime(MissionDetails md){
			ContentValues cv = new ContentValues();
			cv.put("startTime", md.starttime);
			db.update("missiondetails", cv, "mission = ?", new String[] {md.mission});
			
		}
		
		public void deleteOldMisisonDetails(String[] itemID){
			for(int i =0 ;i <itemID.length;i++){
				db.delete("missiondetails", "_id =  ?", new String[]{itemID[i]});
				
			}
			
			
		}
		
		public List<MissionDetails> query(){
			ArrayList<MissionDetails> mds = new ArrayList<MissionDetails>();
			
			Cursor c = queryTheCursor();
			
			while(c.moveToNext()){
				MissionDetails md = new MissionDetails();
				md._id = c.getInt(c.getColumnIndex("_id"));
				md.mission = c.getString(c.getColumnIndex("mission"));
				md.starttime = c.getString(c.getColumnIndex("starttime"));
				md.lasttime = c.getString(c.getColumnIndex("lasttime"));
				md.score = c.getString(c.getColumnIndex("score"));
				md.color = c.getString(c.getColumnIndex("color"));
				mds.add(md);
				
			}
			c.close();
			return mds;
		}

		private Cursor queryTheCursor() {
			Cursor c = db.rawQuery("SELECT * FROM missiondetails", null);
			return c;
		}
		
		public void closeDB(){
			db.close();
		}
}
