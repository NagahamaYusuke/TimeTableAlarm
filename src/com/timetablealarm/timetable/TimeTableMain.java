package com.timetablealarm.timetable;

import com.timetablealarm.R;

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

public abstract class TimeTableMain extends AliasActivity implements OnClickListener {

	private Button AddTimeTable;
	private Button MenuManage;
	private Button ShareMenu;
	private Button InfoDetails;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_main);

		this.AddTimeTable = (Button)findViewById(R.id.AddTimeTable);
		this.AddTimeTable.setOnClickListener(this);

		this.MenuManage = (Button)findViewById(R.id.MenuManage);
		this.MenuManage.setOnClickListener(this);
		
		this.ShareMenu=(Button)findViewById(R.id.ShareMenu);
		this.ShareMenu.setOnClickListener(this);
		
		this.InfoDetails = (Button)findViewById(R.id.InfoDetails);
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
