package com.timetablealarm.timetable;

import java.util.*;
import com.model.DBHelper;
import com.model.TimeTableDB;
import com.model.TimeTableDBEntity;
import com.timetablealarm.R;
import android.app.AliasActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;
import android.view.View.*;
//import android.os.Build;
import android.widget.Button;

public abstract class TimeTableMain extends AliasActivity implements OnClickListener{

	private Button AddTimeTable;
	private Button MenuManage;
	private Button ShareMenu;
	private Button InfoDetails;
	private DBHelper helper;
	private SQLiteDatabase db;
	private TimeTableDB dao;
	private Button button;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_main);
		
		this.AddTimeTable = (Button)findViewById(R.id.AddTimeTable);
		this.AddTimeTable.setOnClickListener(this);
		
		this.MenuManage = (Button)findViewById(R.id.MenuManage);
		this.MenuManage.setOnClickListener(this);
		
		this.ShareMenu=(Button)findViewById(R.id.ShareMenu);
		this.ShareMenu.setOnClickListener(this);
		
		this.InfoDetails = (Button)findViewById(R.id.InfoDetails);
		this.InfoDetails.setOnClickListener(this);
		
		this.helper = new DBHelper(this);
		this.db = this.helper.getReadableDatabase();
		this.dao = new TimeTableDB(this.db);
		
		
		
		List<TimeTableDBEntity> timetable = dao.findAll();
		System.out.println(timetable);
		
		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	public void onClick(View v){
		if(v == this.AddTimeTable){
			Intent intent = new Intent(TimeTableMain.this,TimeTableCreate.class);
			startActivity(intent);
		}
		if(v == this.MenuManage){
			Intent intent = new Intent(TimeTableMain.this,TimeTableSetting.class);
			startActivity(intent);
		}
		if(v == this.ShareMenu){
			Intent intent = new Intent(TimeTableMain.this,TimeTableBrowsing.class);
			startActivity(intent);
		}
		if(v == this.InfoDetails){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);
		}
		
	}
}
