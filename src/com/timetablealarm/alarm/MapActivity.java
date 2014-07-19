package com.timetablealarm.alarm;

import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;

import com.timetablealarm.GPSLoad;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MapActivity extends Activity implements OnClickListener {

	private static final String YMAP_KEY = "dj0zaiZpPUtNWk1RNlNOODE1VyZzPWNvbnN1bWVyc2VjcmV0Jng9M2I-";
	private boolean FLAG;
	private SharedPreferences pref;
	private double Laitude;
	private double longitude;
	private MapView mapView;
	private Button OKButton;
	private Button cancelButton;
	private TextView GPStext;
	private LinearLayout MapLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		this.pref = getSharedPreferences(AlarmGPSSettingActivity.PREF_KEY, Activity.MODE_PRIVATE);
		
		Intent intent = getIntent();
		GPSLoad gps = new GPSLoad(this);
		this.FLAG = intent.getBooleanExtra("MODE", false);
		if(this.FLAG){
			if(Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLAITUDE, "0.0")) == 0.0){
				this.Laitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLAITUDE, "0.0"));
				this.longitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLONGITUDE, "0.0"));
			} else {
				this.Laitude = gps.getLaitude();
				this.longitude = gps.getLongitude();
			}
		} else {
			if(Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLAITUDE, "0.0")) == 0.0){
				this.Laitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLAITUDE, "0.0"));
				this.longitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLONGITUDE, "0.0"));
			} else {
				this.Laitude = gps.getLaitude();
				this.longitude = gps.getLongitude();
			}
		}
		
		this.mapView = new MapView(this, this.YMAP_KEY);
		MapController c = this.mapView.getMapController();
		c.setCenter(new GeoPoint((int)(this.Laitude * 1E6), (int)(this.longitude * 1E6)));
		
		
		this.OKButton = (Button)findViewById(R.id.OKButton);
		this.OKButton.setOnClickListener(this);
		
		this.cancelButton = (Button)findViewById(R.id.cancelButton);
		this.cancelButton.setOnClickListener(this);
		
		this.GPStext = (TextView)findViewById(R.id.GPSText);
		
		this.MapLayout = (LinearLayout)findViewById(R.id.MapView);
		this.MapLayout.addView(mapView);
		
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
