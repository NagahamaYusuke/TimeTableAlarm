package com.timetablealarm.timetable;

import com.timetablealarm.R;

import android.app.AliasActivity;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;


public abstract class TimeTableSetting extends AliasActivity implements OnClickListener {
	
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
	private String[] date = {"月", "火", "水", "木","金"};
	private String[] time = {"1","2","3","4","5","6"};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timetable_setting);
		
		this.subjectName = (EditText)getText(R.id.subjectName);
		String sbjName = subjectName.getText().toString();
		
		this.day = (Spinner)findViewById(R.id.day);
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);
		setContentView(linearLayout);
		
		linearLayout.addView(day, createParam(100, WC));
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.id.day, date);
		
		day.setAdapter(arrayAdapter);
		
		this.Period = (EditText)getText(R.id.period);
		String str = Period.toString();
		this.period = Integer.parseInt(str);
		
		if (savedInstanceState == null) {
//			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}    
	
	private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }
		
}
