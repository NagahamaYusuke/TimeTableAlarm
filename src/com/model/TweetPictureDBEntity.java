package com.model;

public class TweetPictureDBEntity {
	private int rowID;
	private String userName;
	private String filePath;
	private String URL;
	
	public void setRowID(int rowID){
		this.rowID = rowID;
	}
	
	public void setUserName(String userName){
		this.filePath = userName;
	}
	
	public void setFilePath(String filePath){
		this.filePath = filePath;
	}
	
	public void setURL(String URL){
		this.URL = URL;
	}
	
	public int getRowID(){
		return this.rowID;
	}
	
	public String getUserName(){
		return this.userName;
	}
	
	public String getFilePath(){
		return this.filePath;
	}
	
	public String getURL(){
		return this.URL;
	}
}
