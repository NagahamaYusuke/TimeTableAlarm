package com.timetablealarm.alarm;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyAlarmManager {
	private Context c;
	private AlarmManager am;
	private PendingIntent mAlarmSender;
	
	public MyAlarmManager(Context c) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.c = c;
		am = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
		Log.v("MyAlarmManager", "初期化完了");
	}
	
	public void addAlarm(int h,int M, int s, int ms){
		mAlarmSender = PendingIntent.getService(c, -1, new Intent(c, MyAlarmService.class), PendingIntent.FLAG_UPDATE_CURRENT);
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.add(Calendar.HOUR, h);
		cal.add(Calendar.MINUTE, M);
		cal.add(Calendar.SECOND, s);
		cal.add(Calendar.MILLISECOND, ms);
		Log.v("MyAlarmManagerLog", cal.getTimeInMillis() + "ms");
		am.set(AlarmManager.RTC_WAKEUP,  cal.getTimeInMillis() , mAlarmSender);
		Log.v("MyAlarmManagerLog", "アラームセット完了");
	}
}
