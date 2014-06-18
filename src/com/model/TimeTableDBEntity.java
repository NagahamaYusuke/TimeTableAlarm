package com.model;

import java.io.Serializable;

public class TimeTableDBEntity implements Serializable{

	private static final long serialVersionUID = 664473719166320274L;
	private int rowID;
	private String Name;
	private String Day;
	private int time;
	private String teacher;
	private String ClassRoom;
	private boolean continuation;
	
	public void setrowID(int rowID){
		this.rowID = rowID;
	}
	
	public int getrowID(){
		return this.rowID;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	public String getName(){
		return this.Name;
	}
	
	public void setDay(String Day){
		this.Day = Day;
	}
	
	public String getDay(){
		return this.Day;
	}
	
	public void setTime(int time){
		this.time = time;
	}
	
	public int getTime(){
		return this.time;
	}
	
	public void setTeacher(String teacher){
		this.teacher = teacher;
	}
	
	public String getTeacher(){
		return this.teacher;
	}
	
	public void setClassRoom(String ClassRoom){
		this.ClassRoom = ClassRoom;
	}
	
	public String getClassRoom(){
		return this.ClassRoom;
	}
	
	public void setContinuation(boolean continuation){
		this.continuation = continuation;
	}
	
	public boolean getContinuation(){
		return this.continuation;
	}
	
}
