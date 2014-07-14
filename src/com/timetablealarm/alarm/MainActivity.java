package com.timetablealarm.alarm;

import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyAlarmManager mam = new MyAlarmManager(this);
		mam.addAlarm(0,0,0,1);
	}
}
