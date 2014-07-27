package com.timetablealarm.alarm;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.model.AttendDB;
import com.model.AttendDBEntity;
import com.model.DBHelper;
import com.model.SleepTimeDB;
import com.model.SleepTimeDBEntity;
import com.model.TimeTableDBEntity;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.KeyguardManager.OnKeyguardExitResult;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
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
import android.widget.TextView;
import android.widget.Toast;

public class AlarmActivity extends Activity implements OnClickListener {
	
	private Button StopButton;
	private Button SnoozeButton;
	private DBHelper dBHelper;
	private SQLiteDatabase db;
	private SleepTimeDB dao;
	private AttendDB dao2;
	private Calendar mCalender;
	private MediaPlayer player;
	
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
	    
		this.player = new MediaPlayer();
		player.setAudioStreamType(AudioManager.STREAM_ALARM);
		try {
			player.setDataSource(this, android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI);
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		player.setLooping(true);
		try {
			player.prepare();
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		player.seekTo(0);
		player.start();
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.StopButton){
			if(dao.DataNum() != 0){
				if(dao.findAll(30).get(0).getFlag()){
					SleepTimeDBEntity entity = new SleepTimeDBEntity();
					entity.setYear(mCalender.get(Calendar.YEAR));
					entity.setMonth(mCalender.get(Calendar.MONTH) + 1);
					entity.setDay(mCalender.get(Calendar.DAY_OF_MONTH));
					entity.setFlag(false);
					entity.setSleepTime(System.currentTimeMillis());
					dao.insert(entity);
				}
			}
			player.stop();
			finish();
		}
		if(v == this.SnoozeButton){
			MyAlarmManager mam = new MyAlarmManager(this);
			mam.addAlarm(0,5,0,0);
			player.stop();
			
		}
	}
	
}
