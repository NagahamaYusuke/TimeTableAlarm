package com.timetablealarm.alarm;

import java.util.StringTokenizer;

import com.model.*;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AlarmSettingActivity extends Activity implements OnClickListener {

	private RadioButton DayRadio;
	private RadioButton ClassRadio;
	private Spinner DaySpinner;
//	private Spinner TimeSpinner;
	private Button TimeSettingButton;
	private Button SoundSettingButton;
	private Button OKButton;
	private Button CancelButton;
//	private CheckBox SnoozCheckBox;
	private CheckBox TweetCheckBox;
    private String[] date = {"月", "火", "水", "木","金","土","日"};
    private String[] time = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"};

    private SQLiteDatabase db;
    private DBHelper helper;
    private AlarmTimeDB dao;
    
	private TimePickerDialog dialog;
	private TimePickerDialog.OnTimeSetListener onTimeSetListener;
	private int timeHour;
	private int timeMin;
	
	private String Sound;
	private int rowid;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_setting);
		
		Intent intent = getIntent();
		rowid = intent.getIntExtra("rowID", -1);
		
		
		this.DayRadio = (RadioButton)findViewById(R.id.DayRadio);
		this.DayRadio.setOnClickListener(this);
		
		this.ClassRadio = (RadioButton)findViewById(R.id.ClassRadio);
		this.ClassRadio.setOnClickListener(this);
		
		this.DaySpinner = (Spinner)findViewById(R.id.DaySpinner);
		ArrayAdapter<String> arrayAdepter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.date);
		arrayAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		this.DaySpinner.setAdapter(arrayAdepter);
		this.DaySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO 自動生成されたメソッド・スタブ
				Spinner spinner = (Spinner) parent;
				TimeSettingButton.setText("時間設定[" + spinner.getSelectedItem() + "曜日" + timeHour + "時" + timeMin +  "分前]");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO 自動生成されたメソッド・スタブ
				
			}
		});
		
//		this.TimeSpinner = (Spinner)findViewById(R.id.TimeSpinner);
//		for(int i = 0; i < this.time.length; i++)
//			this.time[i] += "分";
//		
//		arrayAdepter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.time);
//		arrayAdepter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		this.TimeSpinner.setAdapter(arrayAdepter);
//		this.TimeSpinner.setVisibility(View.GONE);
		
		this.TimeSettingButton = (Button)findViewById(R.id.TimeSettingButton);
		this.TimeSettingButton.setOnClickListener(this);
		
		this.SoundSettingButton = (Button)findViewById(R.id.SoundSettingButton);
		this.SoundSettingButton.setOnClickListener(this);
		
		this.OKButton = (Button)findViewById(R.id.OKButton);
		this.OKButton.setOnClickListener(this);
		
		this.CancelButton = (Button)findViewById(R.id.CancelButton);
		this.CancelButton.setOnClickListener(this);
		
//		this.SnoozCheckBox = (CheckBox)findViewById(R.id.SnoozCheckBox);
//		this.SnoozCheckBox.setOnClickListener(this);
		
		
		this.TweetCheckBox = (CheckBox)findViewById(R.id.TweetCheckBox);
		this.TweetCheckBox.setOnClickListener(this);
		
		this.timeHour = 0;
		this.timeMin = 0;
		
		onTimeSetListener = new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO 自動生成されたメソッド・スタブ
				timeHour = hourOfDay;
				timeMin = minute;
				if(DayRadio.isChecked())
					TimeSettingButton.setText("時間設定[" + timeHour + "時" + timeMin +  "分]");
				else
					TimeSettingButton.setText("時間設定[" + timeHour + "時" + timeMin +  "分前]");
			}
		};
		
		this.helper = new DBHelper(this);
		this.db = helper.getReadableDatabase();
		this.dao = new AlarmTimeDB(this.db);
		
		if(rowid != -1){
			Log.d("test", rowid + "");
			AlarmTimeDBEntity entity = dao.findALL().get(rowid);
			this.DaySpinner.setSelection(entity.getDayInt());
			this.DayRadio.setChecked(!entity.getFlag());
			this.ClassRadio.setChecked(entity.getFlag());
			if(entity.getFlag()){
				this.DaySpinner.setVisibility(View.GONE);
			}
			this.timeHour = entity.getHour();
			this.timeMin = entity.getMin();
			this.Sound = entity.getSound();
//			if(entity.getSnooz() != -1){
//				this.TimeSpinner.setVisibility(View.VISIBLE);
//				this.SnoozCheckBox.setChecked(true);
//				this.TimeSpinner.setSelection(entity.getSnooz() - 1);
//			}
			this.TweetCheckBox.setChecked(entity.getTweet());
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
//		if(v == this.SnoozCheckBox){
//			if(this.SnoozCheckBox.isChecked()){
//				this.TimeSpinner.setVisibility(View.VISIBLE);
//			} else {
//				this.TimeSpinner.setVisibility(View.GONE);
//			}
//		}
		if(v == this.ClassRadio){
			TimeSettingButton.setText("時間設定");
			this.DaySpinner.setVisibility(View.GONE);
		} 
		if(v == this.DayRadio){
			TimeSettingButton.setText("時間設定");
			this.DaySpinner.setVisibility(View.VISIBLE);
		}
		if(v == this.TimeSettingButton){
			dialog = new TimePickerDialog(this, android.R.style.Theme_Light, onTimeSetListener, this.timeHour, this.timeMin, true);
			dialog.show();
		}
		if(v == this.CancelButton)
			finish();
		if(v == this.OKButton){
			AlarmTimeDBEntity entity = new AlarmTimeDBEntity();
			entity.setDay(this.DaySpinner.getSelectedItem().toString());
			entity.setFlag(this.ClassRadio.isChecked());
			entity.setHour(timeHour);
			entity.setMin(timeMin);
			entity.setSound(Sound);
//			if(this.SnoozCheckBox.isChecked())
//				entity.setSnooz(Integer.parseInt(this.ReplaceString(this.TimeSpinner.getSelectedItem().toString())));
//			else
//				entity.setSnooz(-1);
			entity.setTweet(this.TweetCheckBox.isChecked());
			if(rowid == -1)
				this.dao.insert(entity);
			else
				this.dao.update(rowid, entity);
			finish();
		}
	}
	
	private String ReplaceString(String str){
		StringTokenizer token = new StringTokenizer(str, "分");
		return token.nextToken();
	}
}
