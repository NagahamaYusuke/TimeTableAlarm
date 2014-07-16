package com.model;

public class AlarmTimeDBEntity {

	private int rowID;
	private String Day;
	private boolean Flag;
	private int hour;
	private int min;
	private String sound;
	private int snooz;
	private boolean tweet;
	
	public void setRowID(int rowID){
		this.rowID = rowID;
	}
	
	public void setDay(String Day){
		this.Day = Day;
	}
	
	public void setFlag(boolean Flag){
		this.Flag = Flag;
	}
	
	public void setFlag(int Flag){
		if(Flag == 1) this.Flag = true;
		else this.Flag = false;
	}
	
	public void setHour(int hour){
		this.hour = hour;
	}
	
	public void setMin(int min){
		this.min = min;
	}
	
	public void setSound(String sound){
		this.sound = sound;
	}
	
	public void setSnooz(int snooz){
		this.snooz = snooz;
	}
	
	public void setTweet(boolean tweet){
		this.tweet = tweet;
	}
	
	public void setTweet(int tweet){
		if(tweet == 1) this.tweet = true;
		else this.tweet = false;
	}
	
	public int getRowID(){
		return this.rowID;
	}
	
	public String getDay(){
		return this.Day;
	}
	
	public boolean getFlag(){
		return this.Flag;
	}
	
	public int getFlagForDB(){
		if(this.Flag) return 1;
		else return 0;
	}
	
	public int getHour(){
		return this.hour;
	}
	
	public int getMin(){
		return this.min;
	}
	
	public String getSound(){
		return this.sound;
	}
	
	public int getSnooz(){
		return this.snooz;
	}
	
	public boolean getTweet(){
		return this.tweet;
	}
	
	public int getTweetForDB(){
		if(tweet) return 1;
		else return 0;
	}
	
}
