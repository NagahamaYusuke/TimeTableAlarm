package com.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ScheduleDB {
	private static final String TABLE_NAME = "yearschedule";
	private static final String COLUMN_ID = "rowID";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_DAY = "DAY";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_DAY};
	
	private SQLiteDatabase db;
	
	public ScheduleDB(SQLiteDatabase db){
		this.db = db;
	}
	
	public List<ScheduleDBEntity> findAll(){
		List<ScheduleDBEntity> entityList = new ArrayList<ScheduleDBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_ID + " DESC");
		while(cursor.moveToNext())
			entityList.add(new ScheduleDBEntity(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
		if(entityList.size() == 0)
			entityList = null;
		return entityList;
	}
	
	public long insert(ScheduleDBEntity entity){
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, entity.getEventName());
		values.put(COLUMN_DAY, entity.getEventDay());
		return db.insert(TABLE_NAME, null, values);
	}
	
}
