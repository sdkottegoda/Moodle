/**
*  an Android implementation of REST and XML-RPC access to Moodle 2.2 servers or higher
*  Copyright (C) 2012  Justin Stevanz, Andrew Kelson and Matthias Peitsch
*
*	Contact the.omega.online@gmail.com for further information.
*
*   This program is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/licenses/>
*/

package com.example.moodle;

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;




public class User{
	
	private static User instance = null;
	private String firstName, lastName, fullName, pictureUrl, userName, token;
	private int id;
	private ArrayList<String> functions;
	
	
	//the user will only be created by the class
	private User(){
		
	}
	
	public void setDetails(JSONObject obj){
		try{
			firstName = obj.getString("firstname");
			lastName = obj.getString("lastname");
			fullName = obj.getString("fullname");
			pictureUrl = obj.getString("userpictureurl");
			userName = obj.getString("username");
			id = obj.getInt("userid");
			functions = new ArrayList<String>();
			JSONArray funcs = obj.getJSONArray("functions");
			for (int i=0;i<funcs.length();i++){
				functions.add(funcs.getString(i));
			}
		}
		catch(JSONException e){
			
		}
	}
	
	public static User getInstance(){
		if (instance==null){
			instance = new User();
		}
			return instance;
		
	}
	
    public String getUsername() {
       return userName;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isCapable(String aFunc){
    	return functions.contains("aFunc");
    }
    
    public String getFullName() {
        return fullName;
    }

    public String getPicUrl() {
       return pictureUrl;
    }
    
    public void setID(int anid){
    	this.id=anid;
    }
    
	public void setToken(String tok) {
		if (this.token == null){
			this.token = tok;
		}
    }

    public String getToken() {
       return token;
    }
    
    public void addCourse(JSONObject obj){
    	courses.add(new Course(obj));
    }
    
    private ArrayList<Course> courses;	
    public void setCourses(JSONObject obj) {
    	courses = new ArrayList<Course>();
		JSONArray jsoncourses;
		try {
			jsoncourses = obj.getJSONArray("courses");
			for (int i=0;i<jsoncourses.length();i++){
				JSONObject aCourse = new JSONObject(jsoncourses.getString(i));
				courses.add(new Course(aCourse));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
     }
    
    public ArrayList<Course> getCourses() {
       return courses;
    }
    
    public Course getCourse(String aModuleCode) {
    	for (Course course : courses) { 
		  if (course.getModuleCode() == aModuleCode) { 
		    return course;  
		  } 
		} 
		return null; // course not found. 
    }
}
