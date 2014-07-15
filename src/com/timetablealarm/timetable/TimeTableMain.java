package com.timetablealarm.timetable;

import com.timetablealarm.R;

import android.app.Activity;
import android.app.AliasActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
//import android.os.Build;
import android.widget.Button;
import android.widget.ImageButton;

public class TimeTableMain extends Activity implements OnClickListener {

	private ImageButton AddTimeTable;
	private ImageButton MenuManage;
	private ImageButton ShareMenu;
	private ImageButton InfoDetails;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_main);

		this.AddTimeTable = (ImageButton)findViewById(R.id.AddTimeTable);
		this.AddTimeTable.setOnClickListener(this);

		this.MenuManage = (ImageButton)findViewById(R.id.MenuManage);
		this.MenuManage.setOnClickListener(this);
		
		this.ShareMenu=(ImageButton)findViewById(R.id.ShareMenu);
		this.ShareMenu.setOnClickListener(this);
		
		this.InfoDetails = (ImageButton)findViewById(R.id.InfoDetails);
		this.InfoDetails.setOnClickListener(this);
	


		
		if (savedInstanceState == null) {
//			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}


	/**
	 * A placeholder fragment containing a simple view.
	 * @return 
	 */
	
	public void onClick(View v){
		if(v == this.AddTimeTable){
			Intent intent = new Intent(TimeTableMain.this,TimeTableCreate.class);
			// SubActivity の起動
			startActivity(intent);
		}
		if(v == this.MenuManage){
			Intent intent = new Intent(TimeTableMain.this,TimeTableSetting.class);
			startActivity(intent);
		}
		if(v == this.ShareMenu){
			
		}
		if(v == this.InfoDetails){
			
		}
		
		
	}
}
