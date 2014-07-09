package com.timetablealarm.twitter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.timetablealarm.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import twitter4j.MediaEntity;
import twitter4j.Query;
import twitter4j.QueryResult;
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
	
	public void QueryBitmap(Activity activity){
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(TwitterOAuthActivity.CONSUMER_KEY, TwitterOAuthActivity.CONSUMER_SECRET);  
		
		twitter.setOAuthAccessToken(this.accessToken); 
		
		try {
			Query query = new Query();
			query.setQuery("#TimeTableAlarm");
			query.setResultType(Query.RECENT);
			query.setCount(100);
			Log.d("Query", "@");
			QueryResult result = twitter.search(query);
			Log.d("Query", "@");
			for(Status sts : result.getTweets()){
				Log.d("Query", "@" + sts.getUser().getScreenName() + ":" + sts.getText());
				MediaEntity[] arrMedia = sts.getMediaEntities();
				for(MediaEntity media : arrMedia){
					if(media.getMediaURL().endsWith(".png")){
						Log.d("Query", media.getMediaURL());
						URL website = new URL(media.getMediaURL());
						ReadableByteChannel rbc = Channels.newChannel(website.openStream());
						DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//						File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator +
//								activity.getString(R.string.app_name) + File.separator + "TimeTableAlarm" + df.format(sts.getCreatedAt()) + ".png");
						FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + File.separator +
								activity.getString(R.string.app_name) + File.separator + "TimeTableAlarm" + df.format(sts.getCreatedAt()) + ".png");
						Log.d("save", Environment.getExternalStorageDirectory().getPath() + File.separator +
								activity.getString(R.string.app_name) + File.separator + "TimeTableAlarm" + df.format(sts.getCreatedAt()) + ".png");
						fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//						if(!file.getParentFile().exists()){
//							file.getParentFile().mkdir();
//							createNomedia(file);
//							try{
//								
//							}
//						}
					}
				}
			}
		} catch (TwitterException e) {
			// TODO 自動生成された catch ブロック
			//Log.e("wow",e.getErrorMessage());
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
	
	private void createNomedia(File file){
		File nomediaFile = new File(file.getPath() + File.separator + ".nomedia");
		try {
			nomediaFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void QuaryTest(){
		String method = "GET";
		String urlStr = "https://api.twitter.com/1.1/search/tweets.json";
	}
	
//	public Status SearchText(){
//		Twitter twitter = new TwitterFactory().getInstance();
//		twitter.setOAuthConsumer(TwitterOAuthActivity.CONSUMER_KEY, TwitterOAuthActivity.CONSUMER_SECRET);
//
//		twitter.setOAuthAccessToken(this.accessToken);  
//	}
	
	
}
