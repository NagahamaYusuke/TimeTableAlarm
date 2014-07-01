package com.crawler;

public class HtmlCrawlerEntity {
	protected String EventName;
	protected String EventDay;
	
	public HtmlCrawlerEntity(String EventName, String EventDay){
		this.EventName = EventName;
		this.EventDay = EventDay;
	}
	
	public HtmlCrawlerEntity(HtmlCrawlerEntity entity){
		this.EventName = entity.getEventName();
		this.EventDay = entity.getEventDay();
	}
	
	public HtmlCrawlerEntity(){ }
	
	public void setEventName(String EventName){
		this.EventName = EventName;
	}
	
	public String getEventName(){
		return this.EventName;
	}
	
	public void setEventDay(String EventDay){
		this.EventDay = EventDay;
	}
	
	public String getEventDay(){
		return this.EventDay;
	}
}
