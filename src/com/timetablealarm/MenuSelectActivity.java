package com.timetablealarm;

import java.util.concurrent.ExecutionException;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import com.model.DBHelper;
import com.model.TwitterDB;
import com.timetablealarm.twitter.TwitterCallbackAsyncTask;
import com.timetablealarm.twitter.TwitterMode;
import com.timetablealarm.twitter.TwitterOAuthActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuSelectActivity extends Activity implements OnClickListener {

	private final String CONSUMER_KEY = "vaglMm7Ya2a5ND7RxT6KkWE8j";
	private final String CONSUMER_SECRET = "F8GkUMK4t7nGtB4QtJZKXhKCRcpevu1HtpdEY6i81ffXerr1xs";
	
	private Button timebutton;
	private Button sleepbutton;
	private Button attendancebutton;
	private Button alarmbutton;
	private Button twitterbutton;
	private AccessToken accessToken;
	private DBHelper helper;
	private SQLiteDatabase db;
	private TwitterDB dao;
	

    public static RequestToken _req = null;
    public static OAuthAuthorization _oauth = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_select);
		this.timebutton = (Button)findViewById(R.id.timebutton);
		this.timebutton.setOnClickListener(this);
		
		this.sleepbutton = (Button)findViewById(R.id.sleepbutton);
		this.sleepbutton.setOnClickListener(this);
		
		this.attendancebutton = (Button)findViewById(R.id.attendancebutton);
		this.attendancebutton.setOnClickListener(this);
		
		this.alarmbutton = (Button)findViewById(R.id.alarmbutton);
		this.alarmbutton.setOnClickListener(this);
		
		this.twitterbutton = (Button)findViewById(R.id.twitterbutton);
		this.twitterbutton.setOnClickListener(this);
		
		helper = new DBHelper(this);
		db = helper.getReadableDatabase();
		dao = new TwitterDB(db);
		
		if (android.os.Build.VERSION.SDK_INT > 8) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		Uri uri = getIntent().getData();
        if(uri != null && uri.toString().startsWith("twittercallback://MenuSelectActivity")){
            TwitterCallbackAsyncTask callbackTask = new TwitterCallbackAsyncTask();
            callbackTask.execute(uri);
            try {
				this.accessToken = callbackTask.get();
				dao.insert(this.accessToken.getTokenSecret(), this.accessToken.getToken());
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
        }
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.timebutton){
			TwitterMode twitterMode = new TwitterMode(dao.firstAccessToken());
		}
		if(v == this.sleepbutton){
			
		}
		if(v == this.attendancebutton){
			
		}
		if(v == this.alarmbutton){
			
		}
		if(v == this.twitterbutton){
			if(dao.findAll() == null){
				Intent intent = new Intent(MenuSelectActivity.this, TwitterOAuthActivity.class);
				startActivity(intent);
			}
			
		}
	}
	
	public Bitmap getViewBitmap(View view){
	    view.setDrawingCacheEnabled(true);
	    Bitmap cache = view.getDrawingCache();
	    if(cache == null){
	        return null;
	    }
	    Bitmap bitmap = Bitmap.createBitmap(cache);
	    view.setDrawingCacheEnabled(false);
	    return bitmap;
	}
	
}
