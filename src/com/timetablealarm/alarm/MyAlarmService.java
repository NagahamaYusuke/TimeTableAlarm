package com.timetablealarm.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyAlarmService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public void onCreate(){
		Thread thr = new Thread(null, mTask, "MyAlarmServiceThread");
		thr.start();
	}
	
	Runnable mTask = new Runnable(){

		@Override
		public void run() {
			// TODO 自動生成されたメソッド・スタブ
			Intent alarmBroadcast = new Intent();
			alarmBroadcast.setAction("MyAlarmAction");
			sendBroadcast(alarmBroadcast);
			MyAlarmService.this.stopSelf();
		}
		
	};

}
