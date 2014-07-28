package com.timetablealarm.timetable;

import java.util.List;

import com.model.*;
import com.timetablealarm.R;

import android.app.Activity;
import android.app.AliasActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
//import android.os.Build;
import android.widget.Button;
import android.widget.ImageButton;

public class TimeTableMain extends Activity implements OnClickListener {

	private ImageButton AddTimeTable;
	private ImageButton MenuManage;
	private ImageButton ShareMenu;
	private ImageButton InfoDetails;
	private DBHelper helper;
	private SQLiteDatabase db;
	private TimeTableDB dao;
	private Button[][] button = new Button[5][6];
	private TimeTableDBEntity entity;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_main);

		this.AddTimeTable = (ImageButton)findViewById(R.id.AddTimeTable);
		this.AddTimeTable.setOnClickListener(this);

		this.MenuManage = (ImageButton)findViewById(R.id.MenuManage);
		this.MenuManage.setOnClickListener(this);
		
		this.ShareMenu=(ImageButton)findViewById(R.id.ShareMenu);
		this.ShareMenu.setOnClickListener(this);
		
		this.InfoDetails = (ImageButton)findViewById(R.id.InfoDetails);
		this.InfoDetails.setOnClickListener(this);
	

		this.helper = new DBHelper(this);
		this.db = this.helper.getReadableDatabase();
		this.dao = new TimeTableDB(this.db);
		
		setButton();

		List<TimeTableDBEntity> timetable = dao.findAll();
		for(int i = 0; i < timetable.size(); i++){
			entity = timetable.get(i);
		
			if(entity.getDay().equals("月")&&entity.getTime()==1)this.button[0][0].setText(entity.getName());
			if(entity.getDay().equals("月")&&entity.getTime()==2)this.button[0][1].setText(entity.getName());
			if(entity.getDay().equals("月")&&entity.getTime()==3)this.button[0][2].setText(entity.getName());
			if(entity.getDay().equals("月")&&entity.getTime()==4)this.button[0][3].setText(entity.getName());
			if(entity.getDay().equals("月")&&entity.getTime()==5)this.button[0][4].setText(entity.getName());
			if(entity.getDay().equals("月")&&entity.getTime()==6)this.button[0][5].setText(entity.getName());
	
			if(entity.getDay().equals("火")&&entity.getTime()==1)this.button[1][0].setText(entity.getName());
			if(entity.getDay().equals("火")&&entity.getTime()==2)this.button[1][1].setText(entity.getName());
			if(entity.getDay().equals("火")&&entity.getTime()==3)this.button[1][2].setText(entity.getName());
			if(entity.getDay().equals("火")&&entity.getTime()==4)this.button[1][3].setText(entity.getName());
			if(entity.getDay().equals("火")&&entity.getTime()==5)this.button[1][4].setText(entity.getName());
			if(entity.getDay().equals("火")&&entity.getTime()==6)this.button[1][5].setText(entity.getName());
			
			if(entity.getDay().equals("水")&&entity.getTime()==1)this.button[2][0].setText(entity.getName());
			if(entity.getDay().equals("水")&&entity.getTime()==2)this.button[2][1].setText(entity.getName());
			if(entity.getDay().equals("水")&&entity.getTime()==3)this.button[2][2].setText(entity.getName());
			if(entity.getDay().equals("水")&&entity.getTime()==4)this.button[2][3].setText(entity.getName());
			if(entity.getDay().equals("水")&&entity.getTime()==5)this.button[2][4].setText(entity.getName());
			if(entity.getDay().equals("水")&&entity.getTime()==6)this.button[2][5].setText(entity.getName());
	
			if(entity.getDay().equals("木")&&entity.getTime()==1)this.button[3][0].setText(entity.getName());
			if(entity.getDay().equals("木")&&entity.getTime()==2)this.button[3][1].setText(entity.getName());
			if(entity.getDay().equals("木")&&entity.getTime()==3)this.button[3][2].setText(entity.getName());
			if(entity.getDay().equals("木")&&entity.getTime()==4)this.button[3][3].setText(entity.getName());
			if(entity.getDay().equals("木")&&entity.getTime()==5)this.button[3][4].setText(entity.getName());
			if(entity.getDay().equals("木")&&entity.getTime()==6)this.button[3][5].setText(entity.getName());
			
			if(entity.getDay().equals("金")&&entity.getTime()==1)this.button[4][0].setText(entity.getName());
			if(entity.getDay().equals("金")&&entity.getTime()==2)this.button[4][1].setText(entity.getName());
			if(entity.getDay().equals("金")&&entity.getTime()==3)this.button[4][2].setText(entity.getName());
			if(entity.getDay().equals("金")&&entity.getTime()==4)this.button[4][3].setText(entity.getName());
			if(entity.getDay().equals("金")&&entity.getTime()==5)this.button[4][4].setText(entity.getName());
			if(entity.getDay().equals("金")&&entity.getTime()==6)this.button[4][5].setText(entity.getName());
		}
		
		System.out.println(timetable);
		

		
		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	public void setButton(){
		//ボタンID設定ここから
		this.button[0][0] = (Button)findViewById(R.id.button11);
		this.button[0][0].setOnClickListener(this);
		this.button[0][1] = (Button)findViewById(R.id.button12);
		this.button[0][1].setOnClickListener(this);
		this.button[0][2] = (Button)findViewById(R.id.button13);
		this.button[0][2].setOnClickListener(this);
		this.button[0][3] = (Button)findViewById(R.id.button14);
		this.button[0][3].setOnClickListener(this);
		this.button[0][4] = (Button)findViewById(R.id.button15);
		this.button[0][4].setOnClickListener(this);
		this.button[0][5] = (Button)findViewById(R.id.button16);
		this.button[0][5].setOnClickListener(this);
		
		this.button[1][0] = (Button)findViewById(R.id.button21);
		this.button[1][0].setOnClickListener(this);
		this.button[1][1] = (Button)findViewById(R.id.button22);
		this.button[1][1].setOnClickListener(this);
		this.button[1][2] = (Button)findViewById(R.id.button23);
		this.button[1][2].setOnClickListener(this);
		this.button[1][3] = (Button)findViewById(R.id.button24);
		this.button[1][3].setOnClickListener(this);
		this.button[1][4] = (Button)findViewById(R.id.button25);
		this.button[1][4].setOnClickListener(this);
		this.button[1][5] = (Button)findViewById(R.id.button26);
		this.button[1][5].setOnClickListener(this);
		
		this.button[2][0] = (Button)findViewById(R.id.button31);
		this.button[2][0].setOnClickListener(this);
		this.button[2][1] = (Button)findViewById(R.id.button32);
		this.button[2][1].setOnClickListener(this);
		this.button[2][2] = (Button)findViewById(R.id.button33);
		this.button[2][2].setOnClickListener(this);
		this.button[2][3] = (Button)findViewById(R.id.button34);
		this.button[2][3].setOnClickListener(this);
		this.button[2][4] = (Button)findViewById(R.id.button35);
		this.button[2][4].setOnClickListener(this);
		this.button[2][5] = (Button)findViewById(R.id.button36);
		this.button[2][5].setOnClickListener(this);
		
		this.button[3][0] = (Button)findViewById(R.id.button41);
		this.button[3][0].setOnClickListener(this);
		this.button[3][1] = (Button)findViewById(R.id.button42);
		this.button[3][1].setOnClickListener(this);
		this.button[3][2] = (Button)findViewById(R.id.button43);
		this.button[3][2].setOnClickListener(this);
		this.button[3][3] = (Button)findViewById(R.id.button44);
		this.button[3][3].setOnClickListener(this);
		this.button[3][4] = (Button)findViewById(R.id.button45);
		this.button[3][4].setOnClickListener(this);
		this.button[3][5] = (Button)findViewById(R.id.button46);
		this.button[3][5].setOnClickListener(this);
		
		this.button[4][0] = (Button)findViewById(R.id.button51);
		this.button[4][0].setOnClickListener(this);
		this.button[4][1] = (Button)findViewById(R.id.button52);
		this.button[4][1].setOnClickListener(this);
		this.button[4][2] = (Button)findViewById(R.id.button53);
		this.button[4][2].setOnClickListener(this);
		this.button[4][3] = (Button)findViewById(R.id.button54);
		this.button[4][3].setOnClickListener(this);
		this.button[4][4] = (Button)findViewById(R.id.button55);
		this.button[4][4].setOnClickListener(this);
		this.button[4][5] = (Button)findViewById(R.id.button56);
		this.button[4][5].setOnClickListener(this);
		
		//時間割ボタンID設定ここまで
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 * @return 
	 */
	

	public void onClick(View v){
		if(v == this.AddTimeTable){
			Intent intent = new Intent(TimeTableMain.this,TimeTableCreate.class);
			startActivity(intent);}
		if(v == this.MenuManage){
			Intent intent = new Intent(TimeTableMain.this,TimeTableSetting.class);
			startActivity(intent);}
		if(v == this.ShareMenu){
			Intent intent = new Intent(TimeTableMain.this,TimeTableBrowsing.class);
			startActivity(intent);}
		if(v == this.InfoDetails){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		
		//時間割ボタンアクション設定ここから
		
		if(v == this.button[0][0]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[0][1]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[0][2]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[0][3]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[0][4]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[0][5]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}

		if(v == this.button[1][0]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[1][1]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[1][2]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[1][3]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[1][4]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[1][5]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		
		if(v == this.button[2][0]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[2][1]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[2][2]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[2][3]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[2][4]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[2][5]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		
		if(v == this.button[3][0]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[3][1]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[3][2]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[3][3]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[3][4]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[3][5]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		
		if(v == this.button[4][0]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[4][1]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[4][2]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[4][3]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[4][4]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		if(v == this.button[4][5]){
			Intent intent = new Intent(TimeTableMain.this,TimeTableInfomation.class);
			startActivity(intent);}
		
		//時間割ボタンアクション設定ここまで		
		
	}
}
