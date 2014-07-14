package com.model;

import java.util.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AttendDB {

	/****
	 * 	更新日時をフィールドに追加すべき
	 */
	
	private static final String TABLE_NAME = "attend";
	private static final String COLUMN_ID = "rowID";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_NUM = "NUM";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_NAME, COLUMN_NUM};
	
	private SQLiteDatabase db;
	
	public AttendDB(SQLiteDatabase db){
		this.db = db;
		if(findALL() == null){
			AttendDBEntity entity = new AttendDBEntity();
			entity.setNum(0);
			entity.setName("Attend");
			this.insert(entity);
			entity.setName("Delay");
			this.insert(entity);
			entity.setName("Absent");
			this.insert(entity);
		}
	}
	
	public List<AttendDBEntity> findALL(){
		List<AttendDBEntity> entityList = new ArrayList<AttendDBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null);
		while(cursor.moveToNext()){
			AttendDBEntity entity = new AttendDBEntity();
			entity.setRowID(cursor.getInt(0));
			entity.setName(cursor.getString(1));
			entity.setNum(cursor.getInt(2));
			entityList.add(entity);
		}
		if(entityList.size() == 0){
			entityList = null;
		}
		return entityList;
	}
	
	private long insert(AttendDBEntity entity){
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, entity.getName());
		values.put(COLUMN_NUM, entity.getNum());
		return db.insert(TABLE_NAME, null, values);
	}
	
	/***
	 * 
	 * @param num 1:attend 2:delay 3:absent
	 * @return
	 */
	public long update(int num){
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_NUM, (findALL().get(num - 1).getNum()) + 1);
		return db.update(TABLE_NAME, cv, COLUMN_ID + " = " + num, null);
	}
	
}
