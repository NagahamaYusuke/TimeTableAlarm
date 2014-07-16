package com.timetablealarm.timetable;

//import com.example.pl2.MainActivity.PlaceholderFragment;
//import android.support.v7.app.ActionBarActivity;
//import android.support.v7.app.ActionBar;
//import android.support.v4.app.Fragment;
import com.timetablealarm.R;

import android.app.Activity;
import android.app.AliasActivity;
import android.os.Bundle;
import android.view.View;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
//import android.os.Build;
//import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class TimeTableCreate extends Activity implements OnClickListener {
	
	private EditText subjectName;
	private Spinner day;
	private EditText Period;
	private int period;
	private Boolean continuity;
	private int endPeriod;
	private String ProfName;
	private String site;
	
	private final int FP = ViewGroup.LayoutParams.FILL_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    private String[] date = {"月", "火", "水", "木","金","土","日"};
    private String[] time = {"1","2","3","4","5","6","7"};

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_create);
		
		this.subjectName = (EditText)findViewById(R.id.subjectName);
		String sbjName = subjectName.getText().toString();
		
		
		this.day = (Spinner)findViewById(R.id.day);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.spinerrow, date);

		day.setAdapter(arrayAdapter);
		
		
		
		this.Period = (EditText)findViewById(R.id.period);
	}    
	
	private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
		
}
