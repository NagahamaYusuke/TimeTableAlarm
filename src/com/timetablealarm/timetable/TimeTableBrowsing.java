package com.timetablealarm.timetable;

import com.timetablealarm.R;
import android.app.AliasActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.*;
import android.view.*;
import android.widget.*;

public abstract class TimeTableBrowsing extends AliasActivity implements OnClickListener {
	
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
		setContentView(R.layout.timetable_browsing);
		
		this.share = (Button)findViewById(R.id.button1);
		this.share.setOnClickListener(this);
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(linearLayout);
		
		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}    
	
	public void onClick(View v){
		if(v == this.share){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			// SubActivity の起動
			startActivity(intent);
		}
		
		if(v == this.AA){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.AB){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.AC){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.BA){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.BB){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.BC){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.CA){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.CB){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}if(v == this.CC){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableBrowsing_view.class);
			startActivity(intent);
		}	
		
	}
		
}
