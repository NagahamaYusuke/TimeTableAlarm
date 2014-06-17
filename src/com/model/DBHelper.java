package com.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "TimeTableAlarm";
	private static final int DATABASE_VERSION = 1;
	
 	private static final String CLEATE_TABLE_SQL_TWITTERTOKEN = "" + "create table twitter (" + "rowID INTEGER primary key autoincrement," +
 																"accessTokenSecret TEXT," +
 																"accessTokenKey TEXT" +
 																")";
 	
 	private static final String CLEATE_TABLE_SQL_TIMETABLE = "" + "create table timetable (" + "rowID INTEGER primary key autoincrement," +
 															 "NAME TEXT," +
 															 "Day TEXT," +
 															 "TIME INTEGER," +
 															 "teacher TEXT," +
 															 "CLASS TEXT," +
 															 "CONTINUATION INTEGER" +
 															 ")";
	/***
	 * コンストラクタ
	 * @param context
	 */
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO 自動生成されたメソッド・スタブ
		db.execSQL(CLEATE_TABLE_SQL_TWITTERTOKEN);
		db.execSQL(CLEATE_TABLE_SQL_TIMETABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
