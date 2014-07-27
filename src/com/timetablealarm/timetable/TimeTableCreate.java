package com.timetablealarm.timetable;

import com.model.DBHelper;
import com.model.TimeTableDB;
import com.model.TimeTableDBEntity;
import com.timetablealarm.R;

import android.app.AliasActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.*;
import android.view.View.*;
import android.widget.*;
//import com.example.pl2.MainActivity.PlaceholderFragment;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
//import android.os.Build;

public abstract class TimeTableCreate extends AliasActivity implements OnClickListener {
	
	private EditText subjectName;
	private Spinner day;
	private EditText Period;
	private int period;
	private Boolean continuity;
	private String ProfName;
	private String site;
	private String sbjName;
	private DBHelper helper;
	private SQLiteDatabase db;
	private TimeTableDB dao;
	
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private String[] date = {"月", "火", "水", "木","金","土","日"};
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_create);
		
		this.subjectName = (EditText)getText(R.id.subjectName);
		sbjName = subjectName.getText().toString();
		
		
		this.day = (Spinner)findViewById(R.id.day);
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(linearLayout);
		
		linearLayout.addView(day, createParam(100, WC));
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.id.day, date);
		
		day.setAdapter(arrayAdapter);
		
		
		this.Period = (EditText)getText(R.id.period);
		String str = Period.toString();
		this.period = Integer.parseInt(str);
		
		this.helper = new DBHelper(this);
		this.db = this.helper.getReadableDatabase();
		this.dao = new TimeTableDB(this.db);
		
		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }
	
	public void onDestroy(View v){
		TimeTableDBEntity entity = new TimeTableDBEntity();
		entity.setName(sbjName);
		entity.setDay(day.toString());
		entity.setTime(period);
		entity.setContinuation(continuity);
		entity.setTeacher(ProfName);
		entity.setClassRoom(site);
		
		dao.insert(entity);
		
	}
}
