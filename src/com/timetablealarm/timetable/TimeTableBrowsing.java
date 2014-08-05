package com.timetablealarm.timetable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
import android.widget.ImageView;
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
	
	private List<String> filenames;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_table_browsing);
		
		this.share = (Button)findViewById(R.id.button1);
		this.share.setOnClickListener(this);
		
		this.helper = new DBHelper(this);
		this.db = this.helper.getReadableDatabase();
		this.twitterDB = new TwitterDB(db);
		if(this.twitterDB.findAll() !=null){
			TwitterMode tm = new TwitterMode(this.twitterDB.firstAccessToken());
			this.filenames = tm.QueryBitmap(this);
			ImageView im[] = new ImageView[9];
			im[0] = (ImageView)findViewById(R.id.imageView1);
			im[1] = (ImageView)findViewById(R.id.imageView2);
			im[2] = (ImageView)findViewById(R.id.imageView3);
			im[3] = (ImageView)findViewById(R.id.imageView4);
			im[4] = (ImageView)findViewById(R.id.imageView5);
			im[5] = (ImageView)findViewById(R.id.imageView6);
			im[6] = (ImageView)findViewById(R.id.imageView7);
			im[7] = (ImageView)findViewById(R.id.imageView8);
			im[8] = (ImageView)findViewById(R.id.imageView9);
			
			
			for(int i = 0; i < 9 && this.filenames.size() - i > 0; i++){
				InputStream in;
				try {
					in = this.openFileInput(this.filenames.get(this.filenames.size() - 1));
					im[i].setImageBitmap(BitmapFactory.decodeStream(in));
					in.close();
				} catch (FileNotFoundException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
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

