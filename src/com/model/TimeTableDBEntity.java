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

    private String[] date = {"月", "火", "水", "木","金","土","日"};
	
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
	
	public int getDayforInt(){
		for(int i = 0; i < this.date.length; i++)
			if(this.date[i].equals(this.Day))
				return i;
		
		return -1;
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
