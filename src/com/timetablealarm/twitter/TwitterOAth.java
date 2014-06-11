package com.timetablealarm.twitter;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterOAth {
	private AccessToken accessToken = null;
	private String APIKey = "vaglMm7Ya2a5ND7RxT6KkWE8j";
	private String APIcon = "F8GkUMK4t7nGtB4QtJZKXhKCRcpevu1HtpdEY6i81ffXerr1xs";
	
	public TwitterOAth() throws Exception{
		Twitter twitter = TwitterFactory.getSingleton();
		twitter.setOAuthConsumer(APIKey, APIcon);
		RequestToken requestToken =  twitter.getOAuthRequestToken();
		this.accessToken = null;
		
	}
}
