package com.timetablealarm.twitter;

import twitter4j.*;
import twitter4j.auth.*;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

public class TwitterCallbackAsyncTask extends
		AsyncTask<Uri, Integer, AccessToken> {

	@Override
	protected AccessToken doInBackground(Uri... params) {
		Uri callbackUri = params[0];
        String resp = null;
        AccessToken accToken = null;
        String verifier = callbackUri.getQueryParameter("oauth_verifier");
        try {
            accToken = TwitterOAuthActivity._oauth.getOAuthAccessToken(
            		TwitterOAuthActivity._req, verifier);
        } catch (TwitterException e) {
            Log.v("ERR","callback err:" + e.getMessage());
            return null;
        }
        return accToken;
	}
	
	@Override
    protected void onPostExecute(AccessToken result){
        if (result != null) {
            /**
            /* tokenとtokenSecretの取得
            /* String token = result.getToken();
            /* String tokenSecret = result.getTokenSecret();
             */
        	String token = result.getToken();
        	String tokenSecret = result.getTokenSecret();
        	Log.d("token", token);
        	Log.d("tokenSecret", tokenSecret);
        } else {
            Log.v("ERR","callback task error.");
        }
    }

}
