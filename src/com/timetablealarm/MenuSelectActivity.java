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
import java.util.Calendar;
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

import com.crawler.HtmlCrawlerEntity;
import com.crawler.Html_getter;
import com.model.AlarmTimeDB;
import com.model.AlarmTimeDBEntity;
import com.model.AttendDB;
import com.model.AttendDBEntity;
import com.model.DBHelper;
import com.model.ScheduleDB;
import com.model.ScheduleDBEntity;
import com.model.SleepTimeDB;
import com.model.SleepTimeDBEntity;
import com.model.TimeTableDB;
import com.model.TwitterDB;
import com.model.TwitterDBEntity;
import com.timetablealarm.alarm.AlarmMenuActivity;
import com.timetablealarm.alarm.MyAlarmManager;
import com.timetablealarm.timetable.TimeTableMain;
import com.timetablealarm.twitter.TwitterCallbackAsyncTask;
import com.timetablealarm.twitter.TwitterMode;
import com.timetablealarm.twitter.TwitterOAuthActivity;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MenuSelectActivity extends Activity implements OnClickListener {
	
	private Button timebutton;
	private Button sleepbutton;
	private Button attendancebutton;
	private Button alarmbutton;
	private Button twitterbutton;
	private ImageButton timeicon;
	private ImageButton sleepicon;
	private ImageButton attendendanceicon;
	private ImageButton alaramicon;
	private ImageButton twittericon;
	
	private AccessToken accessToken;
	private DBHelper helper;
	private SQLiteDatabase db;
	private TwitterDB dao;
	private ScheduleDB dao2;
	private SleepTimeDB dao3;
	private AttendDB dao4;
	private AlarmTimeDB dao5;
	private TimeTableDB dao6;
	
	private Calendar mCalender;
	private int k ;

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
		
		this.timeicon = (ImageButton)findViewById(R.id.Twittericon);
		this.timeicon.setOnClickListener(this);
		
		this.sleepicon = (ImageButton)findViewById(R.id.Sleepicon);
		this.sleepicon.setOnClickListener(this);
		
		this.attendendanceicon = (ImageButton)findViewById(R.id.Attendanceicon);
		this.attendendanceicon.setOnClickListener(this);
		
		this.alaramicon = (ImageButton)findViewById(R.id.Alarmicon);
		this.alaramicon.setOnClickListener(this);
		
		this.twittericon = (ImageButton)findViewById(R.id.Twittericon);
		this.twittericon.setOnClickListener(this);
		helper = new DBHelper(this);
		db = helper.getReadableDatabase();
		dao = new TwitterDB(db);
		dao2 = new ScheduleDB(db);
		dao3 = new SleepTimeDB(this.db);
		dao4 = new AttendDB(this.db);
		dao5 = new AlarmTimeDB(this.db);
		dao6 = new TimeTableDB(this.db);
		
		this.k = 0;
		this.mCalender = Calendar.getInstance();
		
		if (android.os.Build.VERSION.SDK_INT > 8) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		
		WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
	    // ディスプレイのインスタンス生成
	    Display disp = wm.getDefaultDisplay();
	    //ターゲットデバイスの画面横幅のみを取得する
	    float width = disp.getWidth();
	    //画面幅の倍率を求める。"480"はベース画面の横幅
	    float scale = width /480;
	 
	    //指定のテキストビューのテキストサイズに倍率をかける
	    this.timebutton.setTextSize(20 * scale);
	    this.sleepbutton.setTextSize(20 * scale);
	    this.attendancebutton.setTextSize(20 * scale);
	    this.alarmbutton.setTextSize(20 * scale);
	    this.twitterbutton.setTextSize(20 * scale);
	    
	    
	    
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		if(this.db == null){
			helper = new DBHelper(this);
			db = helper.getReadableDatabase();
			dao = new TwitterDB(db);
			dao2 = new ScheduleDB(db);
			dao3 = new SleepTimeDB(this.db);
			dao4 = new AttendDB(this.db);
			dao5 = new AlarmTimeDB(this.db);
			dao6 = new TimeTableDB(this.db);
		}
		
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
	
	GPSLoad gps;
	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.timebutton || v == this.timeicon){
			if(dao2.findAll() == null){
				Html_getter html_getter = new Html_getter();
				List<HtmlCrawlerEntity> entityList = html_getter.gethtmlCrawlerData();
				for(int i = 0; i < entityList.size(); i++)
					dao2.insert(new ScheduleDBEntity(entityList.get(i)));
			}

			Intent intent = new Intent(MenuSelectActivity.this, TimeTableMain.class);
			startActivity(intent);
			db.close();
			db = null;
		}
		if(v == this.sleepbutton || v == this.sleepicon){
			if(dao3.findAll(30).size() == 0){
				Toast.makeText(this, "おやすみなさい", Toast.LENGTH_SHORT).show();
				SleepTimeDBEntity entity = new SleepTimeDBEntity();
				entity.setYear(mCalender.get(Calendar.YEAR));
				entity.setMonth(mCalender.get(Calendar.MONTH) + 1);
				entity.setDay(mCalender.get(Calendar.DAY_OF_MONTH));
				entity.setFlag(true);
				entity.setSleepTime(System.currentTimeMillis());
				dao3.insert(entity);
			} else if(!dao3.findAll(30).get(0).getFlag()){
				Toast.makeText(this, "おやすみなさい", Toast.LENGTH_SHORT).show();
				SleepTimeDBEntity entity = new SleepTimeDBEntity();
				entity.setYear(mCalender.get(Calendar.YEAR));
				entity.setMonth(mCalender.get(Calendar.MONTH) + 1);
				entity.setDay(mCalender.get(Calendar.DAY_OF_MONTH) + k);
				entity.setFlag(true);
				entity.setSleepTime(System.currentTimeMillis());
				dao3.insert(entity);
			}
			MyAlarmManager mA = new MyAlarmManager(this);
			int week = mCalender.get(Calendar.DAY_OF_WEEK) % 7;
			List<AlarmTimeDBEntity> entitylist = dao5.findALL();
			for(int i = 0; i < entitylist.size(); i++){
				AlarmTimeDBEntity entity = entitylist.get(i);
				Calendar AlarmCalendar = Calendar.getInstance();
				Log.d("Alarm", "aaa");
				if(!entity.getFlag() && ((entity.getDayInt() + 1) % 7 == week || entity.getDayInt() == (week + 5) % 7)){
					Log.d("Alarm", week + "");
					MyAlarmManager mAlarmManager = new MyAlarmManager(this);
					AlarmCalendar.set(mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH), entity.getHour(), entity.getMin(), 0);
					if(System.currentTimeMillis() > AlarmCalendar.getTimeInMillis())
						AlarmCalendar.set(mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH) + 1, entity.getHour(), entity.getMin(), 0);
					mAlarmManager.addAlarm(AlarmCalendar.getTimeInMillis());
				} else if(entity.getFlag()){
					
				}
			}
			
		}
		if(v == this.attendancebutton || v == this.attendendanceicon){
//			this.TweetWithPicture("Test #TimeTableAlarm", this.getViewBitmap(this.findViewById(R.id.menu_select_layout)));

			Toast.makeText(this, "出席しました", Toast.LENGTH_SHORT).show();

//			MyAlarmManager mam = new MyAlarmManager(this);
//			mam.addAlarm(0,0,30,0);

//			List<AttendDBEntity> entityList = dao4.findALL();
//
//			for(int i = 0; i < entityList.size(); i++){
//				Log.d("AttendDB", "rowID" + entityList.get(i).getRowID() + ", Name" + entityList.get(i).getName() + ", Num" + entityList.get(i).getNum());
//			}
			
			if(dao4.checkDay(1, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH) + 1, mCalender.get(Calendar.DAY_OF_MONTH)));
			
//			entityList = dao4.findALL();
			
//			for(int i = 0; i < entityList.size(); i++){
//				Log.d("AttendDB", "rowID" + entityList.get(i).getRowID() + ", Name" + entityList.get(i).getName() + ", Num" + entityList.get(i).getNum());
//			}

//			if(dao4.checkDay(2, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH) + 1, mCalender.get(Calendar.DAY_OF_MONTH)));
//
//			entityList = dao4.findALL();
//			
//			for(int i = 0; i < entityList.size(); i++){
//				Log.d("AttendDB", "rowID" + entityList.get(i).getRowID() + ", Name" + entityList.get(i).getName() + ", Num" + entityList.get(i).getNum());
//			}

			
			
			
//			this.TweetWithPicture("Test #TimeTableAlarm", this.getViewBitmap(this.findViewById(R.id.menu_select_layout)));

		}
		if(v == this.alarmbutton || v == this.alaramicon){
			
//			gps = new GPSLoad(this);
//			(new Thread (new Runnable() {
//				
//				@Override
//				public void run() {
//					// TODO 自動生成されたメソッド・スタブ
//					while(!gps.getFlag());
//
//					Log.d("Menu:GPS", gps.getLaitude() + ":" + gps.getLongitude());
//				}
//			})).start();
			

			Intent intent = new Intent(MenuSelectActivity.this, AlarmMenuActivity.class);
			startActivity(intent);
			db.close();
			db = null;
			
		}
		if(v == this.twitterbutton || v == this.twittericon){
			if(dao.findAll() == null){
				Intent intent = new Intent(MenuSelectActivity.this, TwitterOAuthActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(this, "登録済みです", Toast.LENGTH_SHORT);
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
	
	private Bitmap resizeBitmapToDisplaySize(View view, Bitmap src){
        int srcWidth = src.getWidth(); // 元画像のwidth
        int srcHeight = src.getHeight(); // 元画像のheight
        Log.d("test", "srcWidth = " + String.valueOf(srcWidth)
                + " px, srcHeight = " + String.valueOf(srcHeight) + " px");
 
//        // 画面サイズを取得する
          Matrix matrix = new Matrix();
//        DisplayMetrics metrics = new DisplayMetrics();
//        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        
        float screenWidth = (float)view.getWidth();
        float screenHeight = (float) view.getHeight();
        Log.d("test", "screenWidth = " + String.valueOf(screenWidth)
                + " px, screenHeight = " + String.valueOf(screenHeight) + " px");
        float[] a = {screenWidth,screenHeight};
        matrix.setValues(a);
        float widthScale = screenWidth / srcWidth;
        float heightScale = screenHeight / srcHeight;
        Log.d("test", "widthScale = " + String.valueOf(widthScale)
                + ", heightScale = " + String.valueOf(heightScale));
//        if (widthScale > heightScale) {
//            matrix.postScale(heightScale, heightScale);
//        } else {
//            matrix.postScale(widthScale, widthScale);
//        }
        
        matrix.postScale(widthScale, heightScale);
        // リサイズ
        Bitmap dst = Bitmap.createBitmap(src, 0, 0, srcWidth, srcHeight, matrix, true);
        int dstWidth = dst.getWidth(); // 変更後画像のwidth
        int dstHeight = dst.getHeight(); // 変更後画像のheight
        Log.d("test", "dstWidth = " + String.valueOf(dstWidth)
                + " px, dstHeight = " + String.valueOf(dstHeight) + " px");
        src = null;
        return dst;
    }
	
}
