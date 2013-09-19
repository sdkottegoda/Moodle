package com.example.moodle;

import org.json.JSONException;
import org.json.JSONObject;

public class UserHome {
	
	private String fullName, siteName;
	private static UserHome instance = null;
	private UserHome(JSONObject homeObj){
		try{
			fullName = homeObj.getString("fullname");
			siteName = homeObj.getString("sitename");
		}
		catch(JSONException e){
			
		}
		catch(NullPointerException e){
			
		}
	}
	public static UserHome getInstance(){
		if (instance==null){
			instance = new UserHome(null);
		}
		return instance;
	}
	public static UserHome getInstance(JSONObject aHome){
		if (instance==null){
			instance = new UserHome(aHome);
		}
		return instance;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public String getSiteName() {
		return siteName;
	}
}
