package com.timetablealarm.twitter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import android.graphics.Bitmap;
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
	
	public Status Tweet(String text){
		Twitter twitter = new TwitterFactory().getInstance();
		
		twitter.setOAuthAccessToken(accessToken);  
		try {  
			 Status status = twitter.updateStatus(text);
			 return status;
		} catch (TwitterException e) {  
		    android.util.Log.e("TwitterException", e.toString());  
		}
		return null;
	}
	
	public Status TweetWithPicture(String text,Bitmap bitmap){
		Twitter tw = new TwitterFactory().getInstance();
		
		tw.setOAuthAccessToken(accessToken);
		
		StatusUpdate status = new StatusUpdate(text);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
		InputStream inputStream = new ByteArrayInputStream(bos.toByteArray());
		status.media("screenshot", inputStream);
		
		try {
			return tw.updateStatus(status);
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return null;
	}
	
}
