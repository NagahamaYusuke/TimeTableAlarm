package com.crawler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;


public class Html_getter {

	private static final String targetURL = "https://www.ynu.ac.jp/campus/schedule/year.html";
	private static final String charset = "UTF8";
	private String htmlArea;
	private List<String> htmlTabString;
	private List<HtmlCrawlerEntity> htmlCrawlerData;
	
    public Html_getter() {
		// TODO 自動生成されたコンストラクター・スタブ
    	htmlTabString = new ArrayList<String>();
    	htmlArea = new String();
    	htmlCrawlerData = new ArrayList<HtmlCrawlerEntity>();
        try {
        	URL url = new URL(targetURL);
            // 接続
            URLConnection uc = url.openConnection();
            // HTMLを読み込む
            BufferedInputStream bis = new BufferedInputStream(uc.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(bis, charset));
            String line;
            while ((line = br.readLine()) != null)
                htmlArea += line + "\n";
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
        	ex.printStackTrace();
        }    
    	this.Delete_Comment();
    	this.Tab_Divide();
    	for(int i = 0; i < this.htmlTabString.size(); i++)
    		EventInfomation(htmlTabString.get(i));
	}
    
    private void Delete_Comment(){
    	Pattern pattern = Pattern.compile("<!--.+?-->", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(htmlArea);
    	String string = matcher.replaceAll("");
    	htmlArea = string;
    }
    
    private void Tab_Divide(){
    	Pattern pattern = Pattern.compile("<tr>.+?</tr>", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(htmlArea);
    	while(matcher.find())
    		htmlTabString.add(matcher.group());
    	
    }
    
    private void EventInfomation(String htmlTab){
    	String EventName = "";
    	String EventDay = "";
    	Pattern pattern = Pattern.compile("<td>.+?(<br />|</td>)", Pattern.DOTALL);
    	Pattern pattern2 = Pattern.compile("<.+?>", Pattern.DOTALL);
    	Matcher matcher = pattern.matcher(htmlTab);
    	int i = 0;
    	while(matcher.find()){
    		Matcher matcher2 = pattern2.matcher(matcher.group());
    		String replaceString = matcher2.replaceAll("");
    		if(EventName.equals(""))
    			EventName = replaceString;
    		else {
    			EventDay = replaceString;
    			this.htmlCrawlerData.add(new HtmlCrawlerEntity(EventName, EventDay));
    			Log.d("Crawler", "EventName:" + this.htmlCrawlerData.get(this.htmlCrawlerData.size() - 1).getEventName());
    			Log.d("Crawler", "EventDay:" + this.htmlCrawlerData.get(this.htmlCrawlerData.size() - 1).getEventDay());
    		}
    		
    	}
    }
    
    public List<HtmlCrawlerEntity> gethtmlCrawlerData(){
    	return this.htmlCrawlerData;
    }
    
}