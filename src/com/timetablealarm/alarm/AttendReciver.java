package com.timetablealarm.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AttendReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO 自動生成されたメソッド・スタブ
		Intent notification = new Intent(context,AttendActivity.class);
		notification.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(notification);
	}

}
