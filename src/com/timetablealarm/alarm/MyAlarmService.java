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
		Log.v("MyAlarmServiceLog", "create");
		Thread thr = new Thread(null, mTask, "MyAlarmServiceThread");
		thr.start();
		Log.v("MyAlarmServiceLog", "スレッド開始");
	}
	
	Runnable mTask = new Runnable(){

		@Override
		public void run() {
			// TODO 自動生成されたメソッド・スタブ
			Intent alarmBroadcast = new Intent();
			alarmBroadcast.setAction("MyAlarmAction");
			sendBroadcast(alarmBroadcast);
			Log.v("MyAlarmServiceLog", "通知起動メッセージを送った");
			MyAlarmService.this.stopSelf();
			Log.v("MyAlarmServiceLog", "サービス停止");
		}
		
	};

}
