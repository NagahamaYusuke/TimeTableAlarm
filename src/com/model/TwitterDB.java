package com.model;

import java.util.*;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TwitterDB {
	private static final String TABLE_NAME = "twitter";
	private static final String COLUMN_ID = "rowID";
	private static final String COLUMN_PRIVATEID = "privateID";
	private static final String COLUMN_ACCESSTOKENSECRET = "accessTokenSecret";
	private static final String COLUMN_ACCESSTOKENKEY = "accessTokenKey";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_PRIVATEID, COLUMN_ACCESSTOKENSECRET, COLUMN_ACCESSTOKENKEY};
	
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
			entity.setPrivateID(cursor.getInt(1));
			entity.setAccessToken(cursor.getString(2), cursor.getString(3));
			entityList.add(entity);
		}
		return entityList;
		
	}
	
}
