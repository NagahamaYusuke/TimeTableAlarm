package com.timetablealarm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
		@Override
		public void run() {
		  // MainActivityを呼び出す
		  Intent intent = new Intent(getApplicationContext(), MenuSelectActivity.class);
		  // アクティビティ(MainActivity)を起動する
		  startActivity(intent);
		  // SplashActivityを終了する
		  SplashActivity.this.finish();
		  }
		}, 2 * 1000); // 2000ミリ秒後（2秒後）に実行
	}
}
