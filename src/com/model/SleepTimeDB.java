package com.model;

import java.util.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SleepTimeDB {
	private static final String TABLE_NAME = "sleeptable";
	private static final String COLUMN_ID = "rowID";
	private static final String COLUMN_SLEEPTIME = "SLEEPTIME";
	private static final String COLUMN_YEAR = "YEAR";
	private static final String COLUMN_MONTH = "MONTH";
	private static final String COLUMN_DAY = "DAY";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_SLEEPTIME, COLUMN_YEAR, COLUMN_MONTH, COLUMN_DAY};
	
	private SQLiteDatabase db;
	
	public SleepTimeDB(SQLiteDatabase db){
		this.db = db;
	}
	
	public List<SleepTimeDBEntity> findAll(){
		List<SleepTimeDBEntity> entityList = new ArrayList<SleepTimeDBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_ID + " DESC", "30");
		while(cursor.moveToNext()){
			SleepTimeDBEntity entity = new SleepTimeDBEntity();
			entity.setrowID(cursor.getInt(0));
			entity.setSleepTime(cursor.getInt(1));
			entity.setYear(cursor.getInt(2));
			entity.setMonth(cursor.getInt(3));
			entity.setDay(cursor.getInt(4));
			entityList.add(entity);
		}
		if(entityList.size() == 0)
			entityList = null;
		 return entityList;
	}
	
	public boolean checkDay(int year, int month, int day){
		List<SleepTimeDBEntity> result = findAll();
		for(int i = 0; i < result.size(); i++){
			if(result.get(i).getYear() == year && result.get(i).getMonth() == month && result.get(i).getDay() == day)
				return false;
		}
		return true;
	}
	
	public long insert(SleepTimeDBEntity entity){
		ContentValues values = new  ContentValues();
		values.put(COLUMN_SLEEPTIME, entity.getSleeptime());
		values.put(COLUMN_YEAR, entity.getYear());
		values.put(COLUMN_MONTH, entity.getMonth());
		values.put(COLUMN_DAY, entity.getDay());
		return db.insert(TABLE_NAME, null, values);
	}
	
}
