package com.crawler;

import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ConnectVPNActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WebView view = new WebView(this);
		view.setWebViewClient(new WebViewClient(){
			
		});
//		setContentView(R.layout.activity_connect_vpn);
//		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://sslvpn.ynu.ac.jp/remote/login")));
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		Uri uri = getIntent().getData();
		
	}
	
	private void startVPN(){
	}
}
