package com.model;

import java.util.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TwitterDB {
	private static final String TABLE_NAME = "twitter";
	private static final String COLUMN_ID = "rowID";
	private static final String COLUMN_ACCESSTOKENSECRET = "accessTokenSecret";
	private static final String COLUMN_ACCESSTOKENKEY = "accessTokenKey";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_ACCESSTOKENSECRET, COLUMN_ACCESSTOKENKEY};
	
	private SQLiteDatabase db;
	
	public TwitterDB(SQLiteDatabase db){
		this.db = db;
	}
	
	public List<TwitterDBEntity> findAll(){
		List<TwitterDBEntity> entityList = new ArrayList<TwitterDBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, COLUMN_ID + " DESC");
		while(cursor.moveToNext()){
			TwitterDBEntity entity = new TwitterDBEntity();
			entity.setRowID(cursor.getInt(0));
			entity.setAccessToken(cursor.getString(1), cursor.getString(2));
			entityList.add(entity);
		}
		return entityList;
	}
	
	public long insert(String accessTokenSecret, String accessTokenKey){
		ContentValues values = new ContentValues();
		values.put(COLUMN_ACCESSTOKENSECRET, accessTokenSecret);
		values.put(COLUMN_ACCESSTOKENKEY, accessTokenKey);
		return db.insert(TABLE_NAME, null, values);
	}
	
}
