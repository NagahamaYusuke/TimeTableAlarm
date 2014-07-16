package com.model;

public class AttendDBEntity {
	
	private int rowID;
	private String Name;
	private int Num;
	private int year;
	private int month;
	private int day;
	
	public void setRowID(int rowID){
		this.rowID = rowID;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	public void setNum(int Num){
		this.Num = Num;
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
	
	public int getRowID(){
		return this.rowID;
	}
	
	public String getName(){
		return this.Name;
	}
	
	public int getNum(){
		return this.Num;
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
	
	
}
