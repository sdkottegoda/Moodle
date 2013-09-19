package com.example.moodle;

import android.app.Application;
import android.content.Context;

public class App extends Application{

	private static Context context;
	private static String domainURL = null;
	
	@Override
	public void onCreate(){
		super.onCreate();
		context = this;
	}
	
	public static Context getContext(){
		return context;
	}
	
	public static void setDomainUrl(String aURL){
			domainURL = aURL;
			//System.out.println(domainURL);
	}
	
	public static String getDomainURL(){
		return domainURL;
	}
}
