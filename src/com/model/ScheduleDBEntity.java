package com.model;

import com.crawler.HtmlCrawlerEntity;

public class ScheduleDBEntity extends HtmlCrawlerEntity {
	
	private int rowID;
	
	public ScheduleDBEntity(){
		super();
	}
	
	public ScheduleDBEntity(HtmlCrawlerEntity entity){
		super(entity);
	}
	
	public ScheduleDBEntity(int rowID, String EventName, String EventDay) {
		super(EventName, EventDay);
		// TODO 自動生成されたコンストラクター・スタブ
		this.rowID= rowID;
	}
	
	public void setRowID(int rowID){
		this.rowID = rowID;
	}
	
	public int getRowID(){
		return this.rowID;
	}

}
