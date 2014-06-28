package com.timetablealarm.twitter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

import twitter4j.*;
import twitter4j.auth.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.ConfigurationContext;

import com.model.DBHelper;
import com.model.TwitterDB;
import com.model.TwitterDBEntity;
import com.timetablealarm.MenuSelectActivity;
import com.timetablealarm.R;
import com.timetablealarm.R.layout;

import android.app.Activity;
import android.content.*;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.*;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TwitterOAuthActivity extends Activity {

	public static final String CONSUMER_KEY = "vaglMm7Ya2a5ND7RxT6KkWE8j";
	public static final String CONSUMER_SECRET = "F8GkUMK4t7nGtB4QtJZKXhKCRcpevu1HtpdEY6i81ffXerr1xs";
	final String CALLBACK_URL = "foo://bar";  
	final String OAUTH_VERIFIER = "oauth_verifier";
	final String KEY_TOKEN ="pikachu";  
	final String KEY_TOKEN_SECRET ="kairyu";  
	private AccessToken accessToken;
	private String token;
	private String tokenSecret;
	private DBHelper helper;
	private SQLiteDatabase db;
	private TwitterDB dao;
	

    public static RequestToken _req = null;
    public static OAuthAuthorization _oauth = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
		setContentView(R.layout.activity_twitter_oauth);
		
		helper = new DBHelper(this);
		db = helper.getReadableDatabase();
		dao = new TwitterDB(db);

		Uri uri = getIntent().getData();
		
		if(dao.findAll() == null && uri == null)
			startTwitterOAuth();
		
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		
		Uri uri = getIntent().getData();
        if(uri != null && uri.toString().startsWith("twittercallback://TwitterOAuthActivity")){
            TwitterCallbackAsyncTask callbackTask = new TwitterCallbackAsyncTask();
            callbackTask.execute(uri);
            try {
				this.accessToken = callbackTask.get();
				dao.insert(this.accessToken.getTokenSecret(), this.accessToken.getToken());
				this.TweetWithPicture("Test", this.getViewBitmap(this.findViewById(R.id.linearLayout1)));
				
				Intent intent = new Intent(TwitterOAuthActivity.this,MenuSelectActivity.class);
				startActivity(intent);
				db.close();
				this.finish();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
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
        //Oauth認証オブジ£ェクトにconsumerKeyとconsumerSecretを設定
        _oauth.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        //アプリの認証オブジェクト作成
        try {
            //認証後アプリに戻るようにcallbackを設定
            _req = _oauth.getOAuthRequestToken("twittercallback://TwitterOAuthActivity");
        } catch (TwitterException e) {
            Log.v("TEST", "err:" + e.getErrorMessage(), e);
        }
        String uri = _req.getAuthorizationURL();

        //ブラウザーアプリへ移動、RequestTokenを取得するために
        startActivity(new Intent(Intent.ACTION_VIEW , Uri.parse(uri)));
        finish();
    }
	
	private void Tweet(String text){
		Twitter twitter = new TwitterFactory().getInstance();  
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);  
		List<TwitterDBEntity> entitylist = dao.findAll();
		
		twitter.setOAuthAccessToken(entitylist.get(0).getAccessToken());  
		try {  
			twitter.updateStatus(text);  
		} catch (TwitterException e) {  
		    android.util.Log.e("TwitterException", e.toString());  
		}  
	}
	
	
	public Status TweetWithPicture(String text,Bitmap bitmap){
		Twitter twitter = new TwitterFactory().getInstance();  
		twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);  
		List<TwitterDBEntity> entitylist = dao.findAll();
		
		twitter.setOAuthAccessToken(entitylist.get(0).getAccessToken()); 
		
		StatusUpdate status = new StatusUpdate(text);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
		InputStream inputStream = new ByteArrayInputStream(bos.toByteArray());
		status.media("screenshot", inputStream);
		
		try {
			return twitter.updateStatus(status);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}
	
	public Bitmap getViewBitmap(View view){
	    view.setDrawingCacheEnabled(true);
	    Bitmap cache = view.getDrawingCache();
	    if(cache == null){
	        return null;
	    }
	    Bitmap bitmap = Bitmap.createBitmap(cache);
	    view.setDrawingCacheEnabled(false);
	    return bitmap;
	}
}
