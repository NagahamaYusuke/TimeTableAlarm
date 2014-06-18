package com.model;

import java.io.Serializable;

import twitter4j.auth.AccessToken;

public class TwitterDBEntity implements Serializable {
	
	private static final long serialVersionUID = -7740999299367315495L;
	private int rowID;
	private String accessTokenSecret;
	private String accessTokenKey;
	
	public int getRowID(){
		return rowID;
	}
	
	public void setRowID(int rowID){
		this.rowID = rowID;
	}
	
	public String getAccessTokenSecret(){
		return this.accessTokenSecret;
	}
	
	public void setAccessTokenSecret(String accessTokenSecret){
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getAccessTokenkey(){
		return this.accessTokenKey;
	}
	
	public void setAccessTokenKey(String accessTokenKey){
		this.accessTokenKey = accessTokenKey;
	}
	
	public AccessToken getAccessToken(){
		return new AccessToken(accessTokenKey, accessTokenSecret);
	}
	
	public void setAccessToken(AccessToken accessToken){
		this.accessTokenKey = accessToken.getToken();
		this.accessTokenSecret = accessToken.getTokenSecret();
	}
	
	public void setAccessToken(String accessTokenSecret, String accessTokenKey){
		this.accessTokenKey = accessTokenKey;
		this.accessTokenSecret = accessTokenSecret;
	}
	
}
