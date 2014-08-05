package com.timetablealarm;

import java.io.ByteArrayOutputStream;
import java.util.*;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class ConnectVPN {
	private WebView webView;
	
	public ConnectVPN(Activity activity) {
		// TODO 自動生成されたコンストラクター・スタブ
		webView = new WebView(activity);
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		Log.d("11", "1");
		CookieSyncManager.createInstance(activity);
		CookieSyncManager.getInstance().startSync();
		CookieManager.getInstance().setAcceptCookie(true);
		CookieManager.getInstance().removeExpiredCookie();
		Log.d("11", "1");
		
		DefaultHttpClient httpClient;
		httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		httpClient.getParams().setParameter("http.connection.timeout", 5000);
		httpClient.getParams().setParameter("http.socket.timeout", 3000);
		HttpPost httpPost = new HttpPost("https://sslvpn.ynu.ac.jp/remote/login");
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("username", "b1264294"));
		nameValuePair.add(new BasicNameValuePair("credential", "VNjn3511!!!"));
		//nameValuePair.add(new BasicNameValuePair("site", "ynuVPN"));

		Log.d("11", "1");
		try{
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
			HttpResponse response = httpClient.execute(httpPost);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			response.getEntity().writeTo(byteArrayOutputStream);
		} catch (Exception e){
			Log.e("loginError","");
		}
		Log.d("11", "1");
		CookieStore cookieStr = httpClient.getCookieStore();
		Cookie cookie = null;
		if(cookieStr != null){
			List<Cookie> cookies = cookieStr.getCookies();
			if(!cookies.isEmpty()){
				cookie = cookies.get(cookies.size() - 1);
			}
			if(cookie !=null){
				String cookieString = cookie.getName() + "=" + cookie.getValue()+ "; domain=" + cookie.getDomain();
				CookieManager.getInstance().setCookie("https://sslvpn.ynu.ac.jp/",cookieString);
				CookieSyncManager.getInstance().sync();
			}
			
		}

		Log.d("11", "1");
		webView.loadUrl("https://sslvpn.ynu.ac.jp/remote/login");
		//webView.loadUrl("https://sslvpn.ynu.ac.jp/proxy/https/risyu.jmk.ynu.ac.jp/gakumu_portal/login.aspx");
	}
	
	
	public WebView getWebview(){
		return this.webView;
	}
	
}
