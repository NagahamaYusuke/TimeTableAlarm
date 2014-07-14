package com.model;

public class AttendDBEntity {
	
	private int rowID;
	private String Name;
	private int Num;
	
	public void setRowID(int rowID){
		this.rowID = rowID;
	}
	
	public void setName(String Name){
		this.Name = Name;
	}
	
	public void setNum(int Num){
		this.Num = Num;
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
	
	
}
