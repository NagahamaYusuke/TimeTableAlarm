package com.timetablealarm.alarm;

import java.util.Calendar;

import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.R.bool;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

public class AlarmGPSSettingActivity extends Activity implements OnClickListener {

	public static final String PREF_KEY = "AlarmGPSSetting";
	public static final String KEY_SLEEPCHECK = "sleepcheck";
	public static final String KEY_ATTENDCHECK = "attendcheck";
	public static final String KEY_SLEEPGPSLAITUDE = "sleepGPSLaitude";
	public static final String KEY_SLEEPGPSLONGITUDE = "sleepGPSLongitude";
	public static final String KEY_TIMESETFLAG = "timesetFlag";
	public static final String KEY_TIMEHOUR = "timeHour";
	public static final String KEY_TIMEMIN = "timeMin";
	public static final String KEY_ATTENDGPSLAITUDE = "attendGPSLaitude";
	public static final String KEY_ATTENDGPSLONGITUDE = "attendGPSLongitude";
	
	private CheckBox SleepCheckBox;
	private CheckBox AttendCheckBox;
	private Button GPSSetUpSleep;
	private Button TimeSetUpButton;
	private Button GPSSetUpAttend;
	private Button OKButton;
	private Button CancelButton;
	private TimePickerDialog dialog;
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private TimePickerDialog.OnTimeSetListener onTimeSetListener;
	private int timeHour;
	private int timeMin;
	private boolean timeFlag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm_gpssetting);
		
		pref = getSharedPreferences(this.PREF_KEY, Activity.MODE_PRIVATE);
		
		this.SleepCheckBox = (CheckBox)findViewById(R.id.SleepCheckBox);
		this.SleepCheckBox.setOnClickListener(this);
		
		this.AttendCheckBox = (CheckBox)findViewById(R.id.AttendCheckBox);
		this.AttendCheckBox.setOnClickListener(this);
		
		this.GPSSetUpSleep = (Button)findViewById(R.id.GPSSetUpSleep);
		this.GPSSetUpSleep.setOnClickListener(this);
		
		this.TimeSetUpButton = (Button)findViewById(R.id.TimeSetUpButton);
		this.TimeSetUpButton.setOnClickListener(this);
		
		this.GPSSetUpAttend = (Button)findViewById(R.id.GPSSetUpAttend);
		this.GPSSetUpAttend.setOnClickListener(this);
		
		this.OKButton = (Button)findViewById(R.id.OKButton);
		this.OKButton.setOnClickListener(this);
		
		this.CancelButton = (Button)findViewById(R.id.CancelButton);
		this.CancelButton.setOnClickListener(this);
		
		this.SleepCheckBox.setChecked(pref.getBoolean(KEY_SLEEPCHECK, false));

		this.timeHour = pref.getInt(KEY_TIMEHOUR, 0);
		this.timeMin = pref.getInt(KEY_TIMEMIN, 0);
		
		if(pref.getBoolean(KEY_TIMESETFLAG, false)){
			this.TimeSetUpButton.setText("時間設定" + "  [" + timeHour + "時" + timeMin + "分]");
		}
		

		if(!pref.getBoolean(this.KEY_SLEEPCHECK, false)){
			this.GPSSetUpSleep.setVisibility(View.INVISIBLE);
			this.TimeSetUpButton.setVisibility(View.INVISIBLE);
		}

		this.AttendCheckBox.setChecked(pref.getBoolean(KEY_ATTENDCHECK, false));
		if(!pref.getBoolean(this.KEY_ATTENDCHECK, false)){
			this.GPSSetUpAttend.setVisibility(View.INVISIBLE);
		}
		
		
		onTimeSetListener=new OnTimeSetListener() {
			
			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				// TODO 自動生成されたメソッド・スタブ
				timeHour = hourOfDay;
				timeMin = minute;
				timeFlag = true;
				TimeSetUpButton.setText("時間設定" + "  [" + timeHour + "時" + timeMin + "分]");
			}
		};
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.SleepCheckBox){
			if(this.SleepCheckBox.isChecked()){
				this.GPSSetUpSleep.setVisibility(View.VISIBLE);
				this.TimeSetUpButton.setVisibility(View.VISIBLE);
			} else {
				this.GPSSetUpSleep.setVisibility(View.INVISIBLE);
				this.TimeSetUpButton.setVisibility(View.INVISIBLE);
			}
		}
		if(v == this.AttendCheckBox){
			if(this.AttendCheckBox.isChecked()){
				this.GPSSetUpAttend.setVisibility(View.VISIBLE);
			} else {
				this.GPSSetUpAttend.setVisibility(View.INVISIBLE);
			}
		}
		if(v == this.CancelButton){
			finish();
		}
		if(v == this.OKButton){
			editor = pref.edit();
			editor.putBoolean(KEY_SLEEPCHECK, this.SleepCheckBox.isChecked());
			editor.putBoolean(KEY_ATTENDCHECK, this.AttendCheckBox.isChecked());
			editor.putInt(KEY_TIMEHOUR, timeHour);
			editor.putInt(KEY_TIMEMIN, timeMin);
			editor.putBoolean(KEY_TIMESETFLAG, timeFlag);
			editor.commit();
			finish();
		}
		if(v == this.TimeSetUpButton){
			dialog = new TimePickerDialog(this, android.R.style.Theme_Light, onTimeSetListener, this.timeHour, this.timeMin, true);
			dialog.show();
		}
		if(v == this.GPSSetUpSleep){
			
			Intent intent = new Intent(AlarmGPSSettingActivity.this, MapSettupActivity.class);
			intent.putExtra("MODE", false);
			startActivity(intent);
		}
		
	}

}
