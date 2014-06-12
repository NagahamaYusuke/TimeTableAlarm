package com.timetablealarm.twitter;

import java.io.InputStream;
import java.net.URL;

import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.ConfigurationContext;

import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TwitterOAuthActivity extends Activity {

	final String CONSUMER_KEY = "vaglMm7Ya2a5ND7RxT6KkWE8j";
	final String CONSUMER_SECRET = "F8GkUMK4t7nGtB4QtJZKXhKCRcpevu1HtpdEY6i81ffXerr1xs";
	final String CALLBACK_URL = "foo://bar";  
	final String OAUTH_VERIFIER = "oauth_verifier";  
	final String KEY_TOKEN ="pikachu";  
	final String KEY_TOKEN_SECRET ="kairyu";
	

    public static RequestToken _req = null;
    public static OAuthAuthorization _oauth = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
		setContentView(R.layout.activity_twitter_oauth);
		Button btn = (Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自動生成されたメソッド・スタブ

                startTwitterOAuth();
//				executeOauth();
			}
		});
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		Uri uri = getIntent().getData();
        if(uri != null && uri.toString().startsWith("demotwittercallback://TwitterOAuthActivity")){
            TwitterCallbackAsyncTask callbackTask = new TwitterCallbackAsyncTask();
            callbackTask.execute(uri);
        }
	}
	
	private void startTwitterOAuth(){
        //Twitetr4jの設定を読み込む
		try{
			ConfigurationBuilder cb = new ConfigurationBuilder();
	        _oauth = new OAuthAuthorization(cb.build());
		} catch (Exception e) {
            Log.v("TEST", "err:" + e.getMessage(), e);
        }
        //Oauth認証オブジェクト作成
        //Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
        _oauth.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        //アプリの認証オブジェクト作成
        try {
            //認証後アプリに戻るようにcallbackを設定
            _req = _oauth.getOAuthRequestToken("demotwittercallback://TwitterOAuthActivity");
    		Log.d("aaaa","Aaaa");
        } catch (TwitterException e) {
            Log.d("TEST", "err:" + e.getErrorMessage(), e);
        }
		Log.d("aaaa","Aaaa111");
        String uri = _req.getAuthorizationURL();

		Log.d("aaaa","Aaaa");
        //ブラウザーアプリへ移動、RequestTokenを取得するために
        startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(uri)));
        finish();
    }
//	
//	private void executeOauth(){
//        //Twitetr4Jの設定を読み込む
//    	Log.e("aaa","aaa");
//        Configuration conf = ConfigurationContext.getInstance();
// 
//        //Oauth認証オブジェクト作成
//    	Log.e("aaa","aaa");
//        _oauth = new OAuthAuthorization(conf);
//        //Oauth認証オブジェクトにconsumerKeyとconsumerSecretを設定
//    	Log.e("aaa","aaa");
//        _oauth.setOAuthConsumer("iy2FEHXmSXNReJ6nYQ8FRg", "KYro4jM8BHlLSMsSdTylnTcm3pYaTCiG2UZrYK1yI4");
//        //アプリの認証オブジェクト作成
//        try {
//            _req = _oauth.getOAuthRequestToken("Callback://CallBackActivity");
//        } catch (TwitterException e) {
//        	Log.e("aaa","aaa");
//            e.printStackTrace();
//        }
//        String _uri;
//        _uri = _req.getAuthorizationURL();
//        startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(_uri)), 0);
//    }
//	
//	private void doOAuth(){
//		ConfigurationBuilder cbuilder = new ConfigurationBuilder();
//		cbuilder.setOAuthConsumerKey(CONSUMER_KEY);
//		cbuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
//		Configuration conf = cbuilder.build();
//		mOauth = new OAuthAuthorization(conf);
//		mOauth.setOAuthAccessToken(null); // これをやらないと下記getOAuthRequestToken()で例外が発生する
//		String authUrl = null;
//		try {
//			authUrl = mOauth.getOAuthRequestToken(CALLBACK_URL).getAuthorizationURL();
//		} catch (Exception e) {
//			//Logger.putLog(e.toString());
//			return;
//		}
//		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl));
//		startActivity(intent);
//	}
//	
//	@Override
//	protected void onNewIntent(Intent intent){
//		super.onNewIntent(intent);
//		Uri uri = intent.getData();
//		if (uri != null && uri.toString().startsWith(CALLBACK_URL)) {
//			String verifier = uri.getQueryParameter("oauth_verifier");
//			try {
//				AccessToken at = mOauth.getOAuthAccessToken(verifier);
//				mAccessToken = at.getToken();
//				mAccessTokenSecret = at.getTokenSecret();
//				createTwitterInstance();
//			} catch (Exception e) {
//				//Logger.putLog(e.getMessage());
//			}
//		}
//	}
//	
//	private void createTwitterInstance(){
//		ConfigurationBuilder cbuilder = new ConfigurationBuilder();
//		cbuilder.setOAuthConsumerKey(CONSUMER_KEY);
//		cbuilder.setOAuthConsumerSecret(CONSUMER_SECRET);
//		cbuilder.setOAuthAccessToken(mAccessToken);
//		cbuilder.setOAuthAccessTokenSecret(mAccessTokenSecret);
//		mConf = cbuilder.build();
//		TwitterFactory twitterFactory = new TwitterFactory(mConf);
//		mTwitter = twitterFactory.getInstance();
//	}
}
