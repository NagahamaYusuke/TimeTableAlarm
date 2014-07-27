package com.timetablealarm.timetable;

//import com.example.pl2.MainActivity.PlaceholderFragment;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import com.timetablealarm.R;
import android.app.AliasActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.*;
import android.view.*;
//import android.os.Build;
import android.widget.*;

public abstract class TimeTableBrowsing extends AliasActivity implements OnClickListener {
	
	private Button share;
	
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
	
	private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }
	
	public void onClick(View v){
		if(v == this.share){
			Intent intent = new Intent(TimeTableBrowsing.this,TimeTableCreate.class);
			// SubActivity の起動
			startActivity(intent);
		}	
		
	}
		
}
