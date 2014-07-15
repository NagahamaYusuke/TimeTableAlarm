package com.model;

import java.util.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AlarmTimeDB {
	
	private static final String TABLE_NAME   = "alarmtime";
	private static final String COLUMN_ID    = "rowID";
	private static final String COLUMN_DAY   = "DAY";
	private static final String COLUMN_FLAG  = "FLAG";
	private static final String COLUMN_HOUR  = "HOUR";
	private static final String COLUMN_MIN   = "MIN";
	private static final String COLUMN_SOUND = "SOUND";
	private static final String COLUMN_SNOOZ = "SNOOZ";
	private static final String COLUMN_TWEET = "TWEET";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_DAY, COLUMN_FLAG, COLUMN_HOUR, COLUMN_MIN, COLUMN_SOUND, COLUMN_SNOOZ, COLUMN_TWEET};
	
	private SQLiteDatabase db;
	
	public AlarmTimeDB(SQLiteDatabase db){
		this.db = db;
	}
	
	public List<AlarmTimeDBEntity> findALL(){
		List<AlarmTimeDBEntity> entityList = new ArrayList<AlarmTimeDBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
		while(cursor.moveToNext()){
			AlarmTimeDBEntity entity = new AlarmTimeDBEntity();
			entity.setRowID(cursor.getInt(0));
			entity.setDay(cursor.getString(1));
			entity.setFlag(cursor.getInt(2));
			entity.setHour(cursor.getInt(3));
			entity.setMin(cursor.getInt(4));
			entity.setSound(cursor.getString(5));
			entity.setSnooz(cursor.getInt(6));
			entity.setTweet(cursor.getInt(7));
			entityList.add(entity);
		}
		return entityList;
	}
	
	public AlarmTimeDBEntity getentity(int rowid){
		List<AlarmTimeDBEntity> entity = this.findALL();
		for(int i = 0; i < entity.size(); i++){
			if(entity.get(i).getRowID() == rowid)
				return entity.get(i);
		}
		return null;
	}
	
	public long insert(AlarmTimeDBEntity entity){
		ContentValues values = new ContentValues();
		values.put(COLUMN_DAY, entity.getDay());
		values.put(COLUMN_FLAG, entity.getFlagForDB());
		values.put(COLUMN_HOUR, entity.getHour());
		values.put(COLUMN_MIN, entity.getMin());
		values.put(COLUMN_SOUND, entity.getSound());
		values.put(COLUMN_SNOOZ, entity.getSnooz());
		values.put(COLUMN_TWEET, entity.getTweetForDB());
		return db.insert(TABLE_NAME, null, values);
	}
	
	public long delete(int rowID){
		return db.delete(TABLE_NAME, COLUMN_ID + " = " + rowID, null);
	}
	
	
}
