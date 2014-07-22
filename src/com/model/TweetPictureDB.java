package com.model;

import java.util.*;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TweetPictureDB {

	private static final String TABLE_NAME = "tweetpicture";
	private static final String COLUMN_ID = "rowID";
	private static final String COLUMN_USERNAME = "USERNAME";
	private static final String COLUMN_FILEPATH = "FILEPATH";
	private static final String COLUMN_URL = "URL";
	private static final String[] COLUMNS = {COLUMN_ID, COLUMN_USERNAME, COLUMN_FILEPATH, COLUMN_URL};
	

	private SQLiteDatabase db;
	
	public TweetPictureDB(SQLiteDatabase db){
		this.db = db;
	}
	
	public List<TweetPictureDBEntity> findAll(){
		List<TweetPictureDBEntity> entityList = new ArrayList<TweetPictureDBEntity>();
		Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, null); 
		while(cursor.moveToNext()){
			TweetPictureDBEntity entity = new TweetPictureDBEntity();
			entity.setRowID(cursor.getInt(0));
			entity.setUserName(cursor.getString(1));
			entity.setFilePath(cursor.getString(2));
			entity.setURL(cursor.getString(3));
			entityList.add(entity);
		}
		return entityList;
	}
	
	public long insert(TweetPictureDBEntity entity){
		ContentValues values = new ContentValues();
		values.put(COLUMN_USERNAME, entity.getUserName());
		values.put(COLUMN_FILEPATH, entity.getFilePath());
		values.put(COLUMN_URL, entity.getURL());
		return db.insert(TABLE_NAME, null, values);
	}
	
}
