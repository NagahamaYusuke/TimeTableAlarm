package com.timetablealarm.timetable;

//import com.example.pl2.MainActivity.PlaceholderFragment;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import com.timetablealarm.R;

import android.app.AliasActivity;
import android.os.Bundle;
import android.view.View.*;


public abstract class TimeTableInfomation extends AliasActivity implements OnClickListener {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_info);
	}
}
