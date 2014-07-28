package com.timetablealarm.timetable;

import com.timetablealarm.R;
import com.timetablealarm.R.id;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_table_browsing);
		
		this.share = (Button)findViewById(R.id.button1);
		this.share.setOnClickListener(this);
		
//		LinearLayout linearLayout = new LinearLayout(this);
//		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
//		setContentView(linearLayout);
		
		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}    
	
	public void onClick(View v){
		if(v == this.share){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			// SubActivity の起動
			startActivity(intent);
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

