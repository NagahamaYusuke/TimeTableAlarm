package com.timetablealarm.alarm;

import java.util.Calendar;

import com.model.AttendDB;
import com.model.DBHelper;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.Menu;
import android.view.MenuItem;

public class AttendActivity extends Activity implements LocationListener {

	private LocationManager mLocationManager;
	private double laitude;
	private double longitude;
	private double schoollaitude;
	private double schoollongitude;
	private SharedPreferences pref;
	private static final double DISTANCE = 300;
	
	private SQLiteDatabase db;
	private DBHelper helper;
	private AttendDB dao;
	private Calendar mCalender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attend);
		String gpsStatus = android.provider.Settings.Secure.getString(getContentResolver(), Secure.LOCATION_PROVIDERS_ALLOWED);
		mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		if (gpsStatus.indexOf("gps", 0) < 0){
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
		} else {
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}
		
		this.pref = getSharedPreferences(AlarmGPSSettingActivity.PREF_KEY, Activity.MODE_PRIVATE);
		this.schoollaitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLAITUDE, "0.0"));
		this.schoollongitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLONGITUDE, "0.0"));
		
		helper = new DBHelper(this);
		this.db = helper.getReadableDatabase();
		dao = new AttendDB(db);
		this.mCalender = Calendar.getInstance();
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		mLocationManager.removeUpdates(this);
		this.laitude = location.getLatitude();
		this.longitude = location.getLongitude();
		float[] result = new float[3];
		Location.distanceBetween(schoollaitude, schoollongitude, laitude, longitude, result);
		if(result[0] < this.DISTANCE){
			if(dao.checkDay(1, mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH) + 1, mCalender.get(Calendar.DAY_OF_MONTH)));
		}
		Calendar mmCalendar = Calendar.getInstance();
		mmCalendar.set(mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH) + 1,0, 0);
		finish();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
