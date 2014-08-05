package com.timetablealarm.alarm;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import com.model.AttendDB;
import com.model.AttendDBEntity;
import com.model.DBHelper;
import com.model.SleepTimeDB;
import com.model.SleepTimeDBEntity;
import com.model.TimeTableDBEntity;
import com.model.TwitterDB;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;
import com.timetablealarm.twitter.TwitterMode;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.KeyguardManager.OnKeyguardExitResult;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmActivity extends Activity implements OnClickListener {
	
	private static final String PREF_KEY = "SnoozNum";
	private static final String KEY_FLAGNUM = "flagnum";
	private TextView Message;
	private Button StopButton;
	private Button SnoozeButton;
	private DBHelper dBHelper;
	private SQLiteDatabase db;
	private SleepTimeDB dao;
	private TwitterDB dao2;
	private Calendar mCalender;
	private MediaPlayer player;
	
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private static final int MAXNUM = 3;
	
	private static final String[] TweetText = {"まだこいつおきないぞ！何時だと思ってるんだ！",
											   "今日も安定の一コマ目は遅刻ですね",
											   "これは，もはやセンスやな！寝坊のセンス",
											   "ﾌﾄﾝ( ˘ω˘ )ｽﾔｧ",
											   "ﾌﾄﾝVS単位　　　ﾌﾄﾝの勝ち",
											   "単位なんて夢の中でも取れるもん！！",
											   "二度寝なう",
											   "お，おこしてく・れ・・・・",
											   "僕が授業いないこと気づかれてるのかな"
											   };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alarm);
		
		pref = getSharedPreferences(this.PREF_KEY, Activity.MODE_PRIVATE);

		StopButton = (Button)findViewById(R.id.StopButton);
		StopButton.setOnClickListener(this);
		
		SnoozeButton = (Button)findViewById(R.id.SnoozeButton);
		SnoozeButton.setOnClickListener(this);
		
		this.Message = (TextView)findViewById(R.id.balloonTextView);
		
		
		dBHelper = new DBHelper(this);
		db = dBHelper.getReadableDatabase();
		dao = new SleepTimeDB(this.db);
		dao2 = new TwitterDB(db);
		
		this.mCalender = Calendar.getInstance();
	    
		this.player = new MediaPlayer();
		player.setAudioStreamType(AudioManager.STREAM_ALARM);
		try {
			player.setDataSource(this, android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI);
		} catch (IllegalArgumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		player.setLooping(true);
		try {
			player.prepare();
		} catch (IllegalStateException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		player.seekTo(0);
		player.start();
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		if(v == this.StopButton){
			if(dao.DataNum() != 0){
				if(dao.findAll(30).get(0).getFlag()){
					SleepTimeDBEntity entity = new SleepTimeDBEntity();
					entity.setYear(mCalender.get(Calendar.YEAR));
					entity.setMonth(mCalender.get(Calendar.MONTH) + 1);
					entity.setDay(mCalender.get(Calendar.DAY_OF_MONTH));
					entity.setFlag(false);
					entity.setSleepTime(System.currentTimeMillis());
					dao.insert(entity);
				}
			}
			editor = pref.edit();
			editor.putInt(KEY_FLAGNUM, 0);
			editor.commit();
			player.stop();
			finish();
		}
		if(v == this.SnoozeButton){
			MyAlarmManager mam = new MyAlarmManager(this);
			mam.addAlarm(0,0,10,0);
			Log.d("SS",pref.getInt(KEY_FLAGNUM, 0) + "");
			if(pref.getInt(KEY_FLAGNUM, 0) >= MAXNUM){
				if(dao2.findAll() != null){
					TwitterMode TMR = new TwitterMode(dao2.firstAccessToken());
					String Tweet = this.RandomTweetText();
					TMR.Tweet(Tweet);
					this.Message.setText(Tweet);
					Toast.makeText(this, Tweet, Toast.LENGTH_SHORT);
				}
				editor = pref.edit();
				editor.putInt(KEY_FLAGNUM, pref.getInt(KEY_FLAGNUM, 0) + 1);
				editor.commit();
			} else {
				editor = pref.edit();
				editor.putInt(KEY_FLAGNUM, pref.getInt(KEY_FLAGNUM, 0) + 1);
				editor.commit();
			}
			player.stop();
			finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		if(keyCode == KeyEvent.KEYCODE_BACK) return false;
		return true;
	}
	
	private String RandomTweetText(){
		Random rand = new Random();
		return this.TweetText[rand.nextInt(this.TweetText.length)];
	}
	
}
