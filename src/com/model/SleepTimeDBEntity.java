package com.model;

import java.io.Serializable;

import android.R.bool;

public class SleepTimeDBEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6185943356812884562L;
	private int rowID;
	private int sleeptime;
	private int year;
	private int month;
	private int day;
	private boolean flag;
	
	public void setrowID(int rowID){
		this.rowID = rowID;
	}
	
	public void setSleepTime(int sleeptime){
		this.sleeptime = sleeptime;
	}
	
	public void setYear(int year){
		this.year = year;
	}
	
	public void setMonth(int month){
		this.month = month;
	}
	
	public void setDay(int day){
		this.day = day;
	}
	
	public void setFlag(boolean flag){
		this.flag = flag;
	}
	
	public void setFlag(int flag){
		if(flag == 1) this.flag = true;
		else this.flag = false;
	}
	
	public int getrowID(){
		return this.rowID;
	}
	
	public int getSleeptime(){
		return this.sleeptime;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	public int getDay(){
		return this.day;
	}
	
	public int getFlagForDB(){
		if(this.flag) return 1;
		else return 0;
	}
	
	public boolean getFlag(){
		return this.flag;
	}
	
	
	
	

}
