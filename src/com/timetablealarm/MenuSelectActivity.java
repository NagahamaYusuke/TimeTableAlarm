package com.timetablealarm;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ExecutionException;

import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import com.model.DBHelper;
import com.model.TwitterDB;
import com.model.TwitterDBEntity;
import com.timetablealarm.twitter.TwitterCallbackAsyncTask;
import com.timetablealarm.twitter.TwitterMode;
import com.timetablealarm.twitter.TwitterOAuthActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuSelectActivity extends Activity implements OnClickListener {

	
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
			this.TweetWithPicture("Test #TimeTableAlarm", this.getViewBitmap(this.findViewById(R.id.menu_select_layout)));
			
		}
		if(v == this.alarmbutton){
			TwitterMode tm = new TwitterMode(dao.firstAccessToken());
			tm.Tweet("test");
			tm.QueryBitmap(this);
			
			
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
	    view.setDrawingCacheBackgroundColor(Color.WHITE);
	    Bitmap cache = view.getDrawingCache();
	    if(cache == null){
	        return null;
	    }
	    Bitmap bitmap = Bitmap.createBitmap(cache);
	    view.setDrawingCacheEnabled(false);
	    return bitmap;
	}
	
	public Status TweetWithPicture(String text,Bitmap bitmap){
		Twitter twitter = new TwitterFactory().getInstance();  
		twitter.setOAuthConsumer(TwitterOAuthActivity.CONSUMER_KEY, TwitterOAuthActivity.CONSUMER_SECRET);  
		List<TwitterDBEntity> entitylist = dao.findAll();
		
		twitter.setOAuthAccessToken(accessToken); 
		
		StatusUpdate status = new StatusUpdate(text);
		OutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
		InputStream inputStream = new ByteArrayInputStream(((ByteArrayOutputStream) bos).toByteArray());
		status.media("screenshot", inputStream);
		
		try {
			return twitter.updateStatus(status);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}
	
}
