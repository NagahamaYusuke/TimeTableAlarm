package com.timetablealarm.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyAlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		Log.v("MyAlarmReceiverLog", "action:" + intent.getAction());
		Intent notification = new Intent(context, AlarmActivity.class);
		notification.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(notification);
	}

}
