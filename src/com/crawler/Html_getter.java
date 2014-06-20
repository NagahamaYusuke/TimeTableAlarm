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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Html_getter {

	private static final String targetURL = "https://www.ynu.ac.jp/campus/schedule/year.html";
	private static final String charset = "UTF8";
	private String htmlArea;
	private List<String> htmlTabString;
	
    public Html_getter() {
		// TODO 自動生成されたコンストラクター・スタブ
    	htmlTabString = new ArrayList<String>();
    	htmlArea = new String();
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
//    	
//    	for(int i= 0; i < htmlTabString.size(); i++){
//    		System.out.println("正規表現" + i + "個目");
//    		System.out.println(htmlTabString.get(i));
//    	}
    }
    
    public List<String> gethtmlTabString(){
    	return this.htmlTabString;
    }
    
}