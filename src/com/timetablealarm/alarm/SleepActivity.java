package com.timetablealarm.alarm;

import java.util.Calendar;
import java.util.List;

import com.model.*;
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
import android.widget.Toast;

public class SleepActivity extends Activity implements LocationListener {

	private LocationManager mLocationManager;
	private double laitude;
	private double longitude;
	private double houselaitude;
	private double houselongitude;
	private SharedPreferences pref;
	private static final double DISTANCE = 300;
	
	private SQLiteDatabase db;
	private DBHelper helper;
	private SleepTimeDB dao;
	private AlarmTimeDB dao2;
	private TimeTableDB dao3;
	private Calendar mCalender;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sleep);
		
		String gpsStatus = android.provider.Settings.Secure.getString(getContentResolver(), Secure.LOCATION_PROVIDERS_ALLOWED);
		mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		
		if (gpsStatus.indexOf("gps", 0) < 0){
			mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, this);
		} else {
			mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		}
		
		this.pref = getSharedPreferences(AlarmGPSSettingActivity.PREF_KEY, Activity.MODE_PRIVATE);
		this.houselaitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLAITUDE, "0.0"));
		this.houselongitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLONGITUDE, "0.0"));
		

		helper = new DBHelper(this);
		this.db = helper.getReadableDatabase();
		dao = new SleepTimeDB(db);
		dao2 = new AlarmTimeDB(db);
		dao3 = new TimeTableDB(db);
		this.mCalender = Calendar.getInstance();
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		mLocationManager.removeUpdates(this);
		this.laitude = location.getLatitude();
		this.longitude = location.getLongitude();
		float[] result = new float[3];
		Location.distanceBetween(houselaitude, houselongitude, laitude, longitude, result);
		if(result[0] < this.DISTANCE){
			if(dao.findAll(30) == null){
				Toast.makeText(this, "おやすみなさい", Toast.LENGTH_SHORT).show();
				SleepTimeDBEntity entity = new SleepTimeDBEntity();
				entity.setYear(mCalender.get(Calendar.YEAR));
				entity.setMonth(mCalender.get(Calendar.MONTH) + 1);
				entity.setDay(mCalender.get(Calendar.DAY_OF_MONTH));
				entity.setFlag(true);
				entity.setSleepTime(System.currentTimeMillis());
				dao.insert(entity);
			} else if(!dao.findAll(30).get(0).getFlag()){
				Toast.makeText(this, "おやすみなさい", Toast.LENGTH_SHORT).show();
				SleepTimeDBEntity entity = new SleepTimeDBEntity();
				entity.setYear(mCalender.get(Calendar.YEAR));
				entity.setMonth(mCalender.get(Calendar.MONTH) + 1);
				entity.setDay(mCalender.get(Calendar.DAY_OF_MONTH));
				entity.setFlag(true);
				entity.setSleepTime(System.currentTimeMillis());
				dao.insert(entity);
			}
			MyAlarmManager mA = new MyAlarmManager(this);
			int week = mCalender.get(Calendar.DAY_OF_WEEK) % 7;
			List<AlarmTimeDBEntity> entitylist = dao2.findALL();
			for(int i = 0; i < entitylist.size(); i++){
				AlarmTimeDBEntity entity = entitylist.get(i);
				Calendar AlarmCalendar = Calendar.getInstance();
				if(!entity.getFlag() && (entity.getDayInt() + 1) % 7 == week){
					MyAlarmManager mAlarmManager = new MyAlarmManager(this);
					AlarmCalendar.set(mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH) + 1, entity.getHour(), entity.getHour(), 0);
					mAlarmManager.addAlarm(AlarmCalendar.getTimeInMillis());
				} else {
					
				}
			}
		}
		
		Calendar mmCalendar = Calendar.getInstance();
		mmCalendar.set(mCalender.get(Calendar.YEAR), mCalender.get(Calendar.MONTH), mCalender.get(Calendar.DAY_OF_MONTH) + 1,pref.getInt(AlarmGPSSettingActivity.KEY_TIMEHOUR, 0), pref.getInt(AlarmGPSSettingActivity.KEY_TIMEMIN, 0));
		MyAlarmManager mA = new MyAlarmManager(this);
		mA.addTime(mmCalendar.getTimeInMillis());
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
