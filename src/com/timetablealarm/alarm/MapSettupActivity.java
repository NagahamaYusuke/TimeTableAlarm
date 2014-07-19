package com.timetablealarm.alarm;

import jp.co.yahoo.android.maps.GeoPoint;
import jp.co.yahoo.android.maps.MapActivity;
import jp.co.yahoo.android.maps.MapController;
import jp.co.yahoo.android.maps.MapView;
import jp.co.yahoo.android.maps.MapView.MapTouchListener;
import jp.co.yahoo.android.maps.PinOverlay;

import com.timetablealarm.GPSLoad;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MapSettupActivity extends MapActivity implements OnClickListener ,LocationListener, MapTouchListener{

	private static final String YMAP_KEY = "dj0zaiZpPXgxc05MR0ZOdDlvNiZzPWNvbnN1bWVyc2VjcmV0Jng9MGQ-";
	private boolean FLAG;
	private SharedPreferences pref;
	private double Laitude;
	private double longitude;
	private MapView mapView;
	private Button OKButton;
	private Button cancelButton;
	private TextView GPStext;
	private LinearLayout MapLayout;
	private LocationManager mLocationManager;
	private ProgressDialog mProgDialog = null;
	private String Text;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		this.pref = getSharedPreferences(AlarmGPSSettingActivity.PREF_KEY, Activity.MODE_PRIVATE);
		
		Intent intent = getIntent();
		GPSLoad gps = new GPSLoad(this);
		this.FLAG = intent.getBooleanExtra("MODE", false);
		Log.d("test",Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLAITUDE, "0.0")) + ":" + (Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLAITUDE, "0.0")) == 0.0));
		if(this.FLAG){
			this.Text = "学校をここにセットする";
			if(Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLAITUDE, "0.0")) != 0.0){
				this.Laitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLAITUDE, "0.0"));
				this.longitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_ATTENDGPSLONGITUDE, "0.0"));
			} else {
				mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
				mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
				
				mProgDialog = new ProgressDialog(this);
		        mProgDialog.setMessage("現在位置取得中");
		        mProgDialog.setCancelable(true);
		        mProgDialog.show();

			}
		} else {
			this.Text = "家をここにセットする";
			if(Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLAITUDE, "0.0")) != 0.0){
				this.Laitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLAITUDE, "0.0"));
				this.longitude = Double.parseDouble(this.pref.getString(AlarmGPSSettingActivity.KEY_SLEEPGPSLONGITUDE, "0.0"));
			} else {
				mLocationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
				mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
				
				mProgDialog = new ProgressDialog(this);
		        mProgDialog.setMessage("現在位置取得中");
		        mProgDialog.setCancelable(true);
		        mProgDialog.show();
			}
		}
		
		
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

//	
//	@Override
//	protected void onPause() {
//		// TODO 自動生成されたメソッド・スタブ
//		super.onPause();
//		this.mapView.onPause();
//	}
//	
//	@Override
//	protected void onResume() {
//		// TODO 自動生成されたメソッド・スタブ
//		super.onResume();
//		this.mapView.onResume();
//	}



	@Override
	protected boolean isRouteDisplayed() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}



	@Override
	public void onLocationChanged(Location location) {
		// TODO 自動生成されたメソッド・スタブ

		if(mProgDialog != null){
			mProgDialog.dismiss();
			mProgDialog = null;
		}
		
		mLocationManager.removeUpdates(this);
		
		this.Laitude = location.getLatitude();
		this.longitude = location.getLongitude();
		
		this.mapView = new MapView(this,this.YMAP_KEY);
		MapController c = this.mapView.getMapController();
		c.setCenter(new GeoPoint((int)(this.Laitude * 1E6), (int)(this.longitude * 1E6)));
		c.setZoom(4);
		this.mapView.setBuiltInZoomControls(true);
		
		
		this.OKButton = (Button)findViewById(R.id.OKButton);
		this.OKButton.setOnClickListener(this);
		
		this.cancelButton = (Button)findViewById(R.id.cancelButton);
		this.cancelButton.setOnClickListener(this);
		
		this.GPStext = (TextView)findViewById(R.id.GPSText);
		
		this.MapLayout = (LinearLayout)findViewById(R.id.MapView);
		this.MapLayout.addView(mapView);
		
		
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



	@Override
	public boolean onLongPress(MapView arg0, Object arg1, PinOverlay arg2,
			GeoPoint point) {
		// TODO 自動生成されたメソッド・スタブ
		PinOverlay pinOverlay = new PinOverlay(PinOverlay.PIN_VIOLET);
		this.mapView.getOverlays().add(pinOverlay);
		pinOverlay.addPoint(point, this.Text);
		
		return false;
	}



	@Override
	public boolean onPinchIn(MapView arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}



	@Override
	public boolean onPinchOut(MapView arg0) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
