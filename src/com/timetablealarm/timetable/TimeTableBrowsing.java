package com.timetablealarm.timetable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.model.DBHelper;
import com.model.TwitterDB;
import com.timetablealarm.R;
import com.timetablealarm.R.id;
import com.timetablealarm.R.layout;
import com.timetablealarm.twitter.TwitterMode;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class TimeTableBrowsing extends Activity implements OnClickListener{

	private Button share;
	private Button AA;
	private Button AB;
	private Button AC;
	private Button BA;
	private Button BB;
	private Button BC;
	private Button CA;
	private Button CB;
	private Button CC;
	private TwitterDB twitterDB;
	private DBHelper helper;
	private SQLiteDatabase db;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_table_browsing);
		
		this.share = (Button)findViewById(R.id.button1);
		this.share.setOnClickListener(this);
		
		this.helper = new DBHelper(this);
		this.db = this.helper.getReadableDatabase();
		this.twitterDB = new TwitterDB(db);
		
//		LinearLayout linearLayout = new LinearLayout(this);
//		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//		setContentView(linearLayout);
		
		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}    

    final String SAVE_DIR = "/MyPhoto/";
	public void onClick(View v){
		if(v == this.share){
		    File file = new File(Environment.getExternalStorageDirectory().getPath() + SAVE_DIR + "tmp.png");
		    if(this.twitterDB.findAll() != null){
				TwitterMode tm = new TwitterMode(this.twitterDB.firstAccessToken());
				FileInputStream fis;
				try {
					fis = new FileInputStream(file);
					Bitmap bm = BitmapFactory.decodeStream(fis);
					tm.TweetWithPicture("",bm);
				} catch (FileNotFoundException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
		    }
		}
		
		if(v == this.AA){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.AB){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.AC){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.BA){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.BB){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.BC){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.CA){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.CB){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}if(v == this.CC){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			startActivity(intent);
		}	
		
	}
		
}

