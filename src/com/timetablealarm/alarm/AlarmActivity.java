package com.timetablealarm.alarm;

import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class AlarmActivity extends Activity {

	private WakeLock wakelock;
	private KeyguardLock keylock;
	
	private MediaPlayer mp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);

	    Log.v("通知ログ", "create");
	    
	    this.wakelock = ((PowerManager)getSystemService(Context.POWER_SERVICE)).
	    		newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | 
	    				PowerManager.ON_AFTER_RELEASE, "disableLock");
	    this.wakelock.acquire();
	    KeyguardManager keygurd = (KeyguardManager)getSystemService(Context.KEYGUARD_SERVICE);
	    this.keylock = keygurd.newKeyguardLock("disableLock");
	    this.keylock.disableKeyguard();
	}
}
