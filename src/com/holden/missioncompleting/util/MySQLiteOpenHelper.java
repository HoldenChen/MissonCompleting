package com.holden.missioncompleting.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "MissionCompleting.db";
	public static final int VERSION = 1;
	public static final String TABLE_NAME = "missiondetails";
	public static final String ID = "id";
	public static final String MISSION = "mission";
	public static final String STATRTIME = "starttime";
	public static final String LASTTIME = "lasttime";
	public static final String SCORE = "score";
	public static final String COLOR = "color";
	public MySQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME,null,VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String strSql = "CREATE TABLE IF NOT EXISTS  "+ TABLE_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT ,mission TEXT,starttime VARCHAR,lasttime VARCHAR,score VARCHAR,color VARCHAR)";
		db.execSQL(strSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
