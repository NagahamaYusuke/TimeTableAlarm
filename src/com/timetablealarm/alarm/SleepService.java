package com.timetablealarm.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SleepService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	

	@Override
	public void onCreate() {
		// TODO 自動生成されたメソッド・スタブ
		Thread thr = new Thread(null, mTask, "SleepServiceThread");
		thr.start();
	}
	
	
	Runnable mTask = new Runnable() {
		
		@Override
		public void run() {
			// TODO 自動生成されたメソッド・スタブ
			Intent alarmBroadcast = new Intent();
			alarmBroadcast.setAction("SleepAction");
			sendBroadcast(alarmBroadcast);
			SleepService.this.stopSelf();
		}
	};

}
