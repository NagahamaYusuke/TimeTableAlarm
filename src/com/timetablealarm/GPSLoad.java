package com.timetablealarm;


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class GPSLoad implements LocationListener{
	
	private LocationManager l;
	private double latitude;
	private double longitude;
	private boolean flag;
	
	public GPSLoad(Activity activity) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.flag = false;
		l = (LocationManager)activity.getSystemService(Context.LOCATION_SERVICE);
		l.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ
		Log.d("GPStest", location.getLatitude()*1E6 + "");
		Log.d("GPStest", location.getLongitude()*1E6 + "");
		this.latitude = location.getLatitude();
		this.longitude = location.getLongitude();
		this.flag = true;
		
		l.removeUpdates(this);
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

	public double getLaitude(){
		return this.latitude;
	}
	
	public double getLongitude(){
		return this.longitude;
	}
	
	public boolean getFlag(){
		return flag;
	}
	
}