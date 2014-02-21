package com.holden.missioncompleting.util;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
		private MySQLiteOpenHelper helper;
		private SQLiteDatabase db;
		
		public DBManager(Context context){
			db = helper.getWritableDatabase();
		}
		
		public void add(List<MissionDetails> missionDetails){
			db.beginTransaction();
			try{
				for(MissionDetails md : missionDetails){
					db.execSQL("INSERT INTO missiondetails VALUES(null,?,?,?,?,?)",new Object[]{md.mission,md.starttime,md.lasttime,md.score,md.color});
				}
				
				db.setTransactionSuccessful();
			}finally{
				db.endTransaction();
			}
			
			
		}
		
}
