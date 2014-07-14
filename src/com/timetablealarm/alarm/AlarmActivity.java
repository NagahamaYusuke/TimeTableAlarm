package com.timetablealarm.alarm;

import java.util.Calendar;

import com.model.DBHelper;
import com.model.SleepTimeDB;
import com.model.SleepTimeDBEntity;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.KeyguardManager.OnKeyguardExitResult;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class AlarmActivity extends Activity implements OnClickListener {
	
	private Button StopButton;
	private Button SnoozeButton;
	private DBHelper dBHelper;
	private SQLiteDatabase db;
	private SleepTimeDB dao;
	private Calendar mCalender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);

		StopButton = (Button)findViewById(R.id.StopButton);
		StopButton.setOnClickListener(this);
		
		SnoozeButton = (Button)findViewById(R.id.SnoozeButton);
		SnoozeButton.setOnClickListener(this);
		
		dBHelper = new DBHelper(this);
		db = dBHelper.getReadableDatabase();
		dao = new SleepTimeDB(this.db);
		
		this.mCalender = Calendar.getInstance();
	    
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.StopButton){
			SleepTimeDBEntity entity = new SleepTimeDBEntity();
			entity.setYear(mCalender.get(Calendar.YEAR));
			entity.setMonth(mCalender.get(Calendar.MONTH) + 1);
			entity.setDay(mCalender.get(Calendar.DAY_OF_MONTH));
			entity.setFlag(false);
			entity.setSleepTime(System.currentTimeMillis());
			dao.insert(entity);
		}
		if(v == this.SnoozeButton){
			
		}
	}
	
}
