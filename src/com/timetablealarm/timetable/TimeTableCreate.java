package com.timetablealarm.timetable;

//import com.example.pl2.MainActivity.PlaceholderFragment;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import com.model.*;
import com.timetablealarm.R;

import android.app.Activity;
import android.app.AliasActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
//import android.os.Build;
//import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class TimeTableCreate extends Activity implements OnClickListener {
	
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
	private Button OKButton;
	private Button CancelButton;

	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private String[] date = {"月", "火", "水", "木","金","土","日"};
    

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_create);
		
		
		this.CancelButton = (Button)findViewById(R.id.CancelButton);
		this.CancelButton.setOnClickListener(this);
		
		this.OKButton = (Button)findViewById(R.id.OKButton);
		this.OKButton.setOnClickListener(this);
		
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
	}    
	
	private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.OKButton){
			TimeTableDBEntity entity = new TimeTableDBEntity();
			entity.setName(sbjName);
			entity.setDay(day.toString());
			entity.setTime(period);
			entity.setContinuation(continuity);
			entity.setTeacher(ProfName);
			entity.setClassRoom(site);
			
			dao.insert(entity);
			db.close();
			finish();
		} if(v == this.CancelButton){
			finish();
		}
	}
		
}
