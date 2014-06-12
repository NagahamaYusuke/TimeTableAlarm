package com.timetablealarm.twitter;

import twitter4j.*;
import twitter4j.auth.*;

import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.*;
import android.net.Uri;
import android.os.Bundle;
import android.util.*;
import android.view.Menu;
import android.view.MenuItem;

public class TwitterOAuthActivity extends Activity {

	final String CONSUMER_KEY = "vaglMm7Ya2a5ND7RxT6KkWE8j";
	final String CONSUMER_SECRET = "F8GkUMK4t7nGtB4QtJZKXhKCRcpevu1HtpdEY6i81ffXerr1xs";
	final String CALLBACK_URL = "foo://bar";  
	final String OAUTH_VERIFIER = "oauth_verifier";  
	final String KEY_TOKEN ="pikachu";  
	final String KEY_TOKEN_SECRET ="kairyu"; 
	private Twitter mTwitter;  
	private RequestToken mRequestToken;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter_oauth);
	}
	
	private void doOAuth(){
		mTwitter = new TwitterFactory().getInstance();
		mTwitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		try {
			mRequestToken = mTwitter.getOAuthRequestToken(CALLBACK_URL);
			String url = mRequestToken.getAuthorizationURL();  
	        // ブラウザを起動して認証ページへ  
	        this.startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(url)));  
		} catch (TwitterException e){
			Log.e("TwitterException", e.toString());
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent){
		super.onNewIntent(intent);
		Uri uri = intent.getData();
		if(uri != null && uri.toString().startsWith(CALLBACK_URL)){
			try{
				String verifier = uri.getQueryParameter(OAUTH_VERIFIER);
				AccessToken accessToken = mTwitter.getOAuthAccessToken(mRequestToken, verifier);  
				
				SharedPreferences pref = getPreferences(MODE_PRIVATE);  
	            SharedPreferences.Editor editor=pref.edit();  
	            editor.putString(KEY_TOKEN,accessToken.getToken());  
	            editor.putString(KEY_TOKEN_SECRET,accessToken.getTokenSecret());  
	            editor.commit();  
	            
			} catch (TwitterException e) {
				android.util.Log.e("TwitterException", e.toString()); 
			}
		}
	}
}
