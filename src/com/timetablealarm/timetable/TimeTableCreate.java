package com.timetablealarm.timetable;

import com.model.DBHelper;
import com.model.TimeTableDB;
import com.model.TimeTableDBEntity;
import com.timetablealarm.R;

import android.app.Activity;
import android.app.AliasActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.*;
import android.widget.*;

public class TimeTableCreate extends Activity implements OnClickListener {
	
	private EditText subjectName;
	private Spinner day;
	private EditText Period;
	private int period;
	private EditText ProfName;
	private EditText site;
	private String sbjName;
	private DBHelper helper;
	private SQLiteDatabase db;
	private TimeTableDB dao;
	private Button OKButton;
	private Button cancelButton;
	private CheckBox continuity;
	
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private String[] date = {"月", "火", "水", "木","金","土","日"};
    protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_create);
		
		this.subjectName = (EditText)findViewById(R.id.subjectName1);
		sbjName = subjectName.getText().toString();
		
		this.OKButton = (Button)findViewById(R.id.OKButton);
		this.OKButton.setOnClickListener(this);
		
		this.cancelButton = (Button)findViewById(R.id.CancelButton);
		this.cancelButton.setOnClickListener(this);
		
		this.day = (Spinner)findViewById(R.id.day);
		this.continuity = (CheckBox)findViewById(R.id.continuity);
		
//		LinearLayout linearLayout = new LinearLayout(this);
//		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//		setContentView(linearLayout);
//		
//		linearLayout.addView(day, createParam(100, WC));
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinerrow, date);
		
		day.setAdapter(arrayAdapter);
		
		this.ProfName = (EditText)findViewById(R.id.ProfName);
		this.Period = (EditText)findViewById(R.id.period);
		this.site = (EditText)findViewById(R.id.site);
		
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
//		TimeTableDBEntity entity = new TimeTableDBEntity();
//		entity.setName(sbjName);
//		entity.setDay(day.toString());
//		entity.setTime(period);
//		entity.setContinuation(continuity);
//		entity.setTeacher(ProfName);
//		entity.setClassRoom(site);
//		
//		dao.insert(entity);
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.OKButton){

			TimeTableDBEntity entity = new TimeTableDBEntity();
			entity.setName(this.subjectName.getText().toString());
			entity.setDay(this.date[this.day.getSelectedItemPosition()]);
			Log.d("test",Integer.parseInt(this.Period.getText().toString()) + "");
			entity.setTime(Integer.parseInt(this.Period.getText().toString()));
			entity.setContinuation(this.continuity.isChecked());
			entity.setTeacher(ProfName.getText().toString());
			entity.setClassRoom(site.getText().toString());
			
			dao.insert(entity);
			finish();
		}
		if(v == this.cancelButton)
			finish();
		
	}
}
