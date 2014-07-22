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
 	
 	private static final String CLEATE_TABLESQL_YEARSCHEDULE = "" + "create table yearschedule (" + "rowID INTEGER primary key autoincrement," +
 															   "NAME TEXT," +
 															   "Day TEXT" + 
 															   ")";
 	
 	private static final String CLEATE_TABLESQL_SLEEPTABLE = "" + "create table sleeptable (" + "rowID INTEGER primary key autoincrement," +
 															 "TIME INTEGER," +
 															 "YEAR INTEGER," +
 															 "MONTH INTEGER," +
 															 "DAY INTEGER," +
 															 "FLAG INTEGER" +
 															 ")";
 	
 	private static final String CLEATE_TABLESQL_ATTEND = "" + "create table attend (" + "rowID INTEGER primary key autoincrement," +
 														 "NAME STRING," +
														 "YEAR INTEGER," +
														 "MONTH INTEGER," +
														 "DAY INTEGER," +
 														 "NUM INTEGER" +
 														 ")";
 	
 	private static final String CLEATE_TABLESQL_ALARMTIME = "" + "create table alarmtime (" + "rowID INTEGER primary key autoincrement," +
														 	"DAY STRING," +
														 	"FLAG INTEGER," +
														 	"HOUR INTEGER," +
														 	"MIN INTEGER," +
														 	"SOUND STRING," +
														 	"SNOOZ INTEGER," +
														 	"TWEET INTEGER" +
														 	")";
 	
 	private static final String CLEATE_TABLESQL_TWEETPICTURE = "" + "create table tweetpicture (" + "rowID INTEGER primary key autoincrement," +
 															   "USERNAME STRING," + 
 															   "FILEPATH STRING," + 
 															   "URL STRING"+
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
		db.execSQL(CLEATE_TABLESQL_YEARSCHEDULE);
		db.execSQL(CLEATE_TABLESQL_SLEEPTABLE);
		db.execSQL(CLEATE_TABLESQL_ATTEND);
		db.execSQL(CLEATE_TABLESQL_ALARMTIME);
		db.execSQL(CLEATE_TABLESQL_TWEETPICTURE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
