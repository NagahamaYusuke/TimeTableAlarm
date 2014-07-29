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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_browsing_view);

		this.share = (Button)findViewById(R.id.button1);
		this.share.setOnClickListener(this);

		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(linearLayout);

		if (savedInstanceState == null) {
		}
	}

	public void onClick(View v){
		if(v == this.button1){
			Intent intent = new Intent(TimeTableBrowsing_view.this,TimeTableBrowsing.class);
			startActivity(intent);
		}
		
	}


}
