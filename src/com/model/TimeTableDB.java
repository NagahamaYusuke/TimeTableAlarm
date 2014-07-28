package com.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TimeTableDB {
	private static final String TABLE_NAME = "timetable";
	private static final String COLUMN_ID = "rowID";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_DAY = "Day";
	private static final String COLUMN_TIME = "TIME";
	private static final String COLUMN_TEACHER = "teacher";
	private static final String COLUMN_CLASS = "CLASS";
	private static final String COLUMN_CONTINUATION = "CONTINUATION";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_DAY, COLUMN_TIME, COLUMN_TEACHER, COLUMN_CLASS, COLUMN_CONTINUATION};
	
	private SQLiteDatabase db;
	
	public TimeTableDB(SQLiteDatabase db){
		this.db = db;
	}
	
	public List<TimeTableDBEntity> findAll(){
		List<TimeTableDBEntity> entityList = new ArrayList<TimeTableDBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_ID + " DESC"); 
		while(cursor.moveToNext()){
			TimeTableDBEntity entity = new TimeTableDBEntity();
			entity.setrowID(cursor.getInt(0));
			entity.setName(cursor.getString(1));
			entity.setDay(cursor.getString(2));
			entity.setTime(cursor.getInt(3));
			entity.setTeacher(cursor.getString(4));
			entity.setClassRoom(cursor.getString(5));
			if(cursor.getInt(6) == 1)
				entity.setContinuation(true);
			else 
				entity.setContinuation(false);
			entityList.add(entity);
		}
		return entityList;
	}
	
	/***
	 * 
	 * @param num 0:月曜 ~ 6:日曜
	 * @return
	 */
//	public List<TimeTableDBEntity> getDay(int num){
//		List<TimeTableDBEntity> entityList = new ArrayList<TimeTableDBEntity>();
//		List<TimeTableDBEntity> TargetEntityList = this.findAll();
//		for(int i = 0; i < TargetEntityList.size(); i++){
//			TimeTableDBEntity Tentity = TargetEntityList.get(i);
//			if(Tentity.getDayforInt() == num){
//				TimeTableDBEntity entity = new TimeTableDBEntity();
//				entity.setrowID(Tentity.getrowID());
//				entity.setDay(Tentity.getDay());
//				entity.setClassRoom(Tentity.getClassRoom());
//				entity.setContinuation(Tentity.getContinuation());
//				entity.setName(Tentity.getName());
//			}
//		}
//	}
	
	public long insert(TimeTableDBEntity entity){
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, entity.getName());
		values.put(COLUMN_DAY, entity.getDay());
		values.put(COLUMN_TIME, entity.getTime());
		values.put(COLUMN_TEACHER, entity.getTeacher());
		values.put(COLUMN_CLASS, entity.getClassRoom());
		if(entity.getContinuation())
			values.put(COLUMN_CONTINUATION, 1);
		else
			values.put(COLUMN_CONTINUATION, 0);
		
		return db.insert(TABLE_NAME, null, values);
	}
	
	
}
