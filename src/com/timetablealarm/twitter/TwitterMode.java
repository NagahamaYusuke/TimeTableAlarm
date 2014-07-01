package com.timetablealarm.twitter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.model.DBHelper;
import com.model.TwitterDB;
import com.model.TwitterDBEntity;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.view.View;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TwitterMode {
	
	private AccessToken accessToken;
	
	public TwitterMode(AccessToken accessToken){
		this.accessToken = accessToken;
	}
	
	public Status TweetWithPicture(String text,Bitmap bitmap){
		Twitter twitter = new TwitterFactory().getInstance();  
		twitter.setOAuthConsumer(TwitterOAuthActivity.CONSUMER_KEY, TwitterOAuthActivity.CONSUMER_SECRET);  
		
		twitter.setOAuthAccessToken(this.accessToken); 
		
		StatusUpdate status = new StatusUpdate(text);
		OutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
		InputStream inputStream = new ByteArrayInputStream(((ByteArrayOutputStream) bos).toByteArray());
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
	    Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
	    view.setDrawingCacheEnabled(false);
	    return bitmap;
	}
	
	public Status Tweet(String text){
		Twitter twitter = new TwitterFactory().getInstance();  
		twitter.setOAuthConsumer(TwitterOAuthActivity.CONSUMER_KEY, TwitterOAuthActivity.CONSUMER_SECRET);  
		
		twitter.setOAuthAccessToken(this.accessToken);  
		try {  
			return twitter.updateStatus(text);  
		} catch (TwitterException e) {  
		    android.util.Log.e("TwitterException", e.toString());  
		}  
		return null;
	}
	
//	public Status SearchText(){
//		Twitter twitter = new TwitterFactory().getInstance();
//		twitter.setOAuthConsumer(TwitterOAuthActivity.CONSUMER_KEY, TwitterOAuthActivity.CONSUMER_SECRET);
//
//		twitter.setOAuthAccessToken(this.accessToken);  
//	}
	
}
