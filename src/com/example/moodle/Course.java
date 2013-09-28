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

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable{
	
	private String shortName, fullName, moduleCode, id;
	
	public Course(JSONObject aCourse) {
		try {
			id=aCourse.get("id").toString();
			shortName=aCourse.get("shortname").toString();
			fullName=aCourse.get("fullname").toString();
			moduleCode=aCourse.get("idnumber").toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*public void setId(int id) {
       this.id = id;
    }*/

    public String getId() {
       return id;
    }

	/*private String shortname;	
	public void setShortName(String shortname) {
       this.shortname = shortname;
    }*/

    public String getShortName() {
       return shortName;
    }
	    
    /*private String fullname;	
	public void setFullname(String fullname) {
       this.fullname = fullname;
    }*/

    public String getFullname() {
       return fullName;
    }
    
    /*private int enrolledusercount;	
	public void setEnrolledUserCount(int enrolledusercount) {
       this.enrolledusercount = enrolledusercount;
    }*/

    /* int getEnrolledUserCount() {
       return enrolledusercount;
    }*/
    
    /*private String idnumber;	
	public void setIdNumber(String idnumber) {
       this.idnumber = idnumber;
    }*/

    public String getModuleCode() {
       return moduleCode;
    }

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
    
    /*private int visible;	
	public void setVisible(int visible) {
       this.visible = visible;
    }*/

    /*public int getVisible() {
       return visible;
    }*/

    /*public void populateCourse(JSONObject jsonObject) {    
    	    	 
    	try {  
    		if (jsonObject != null) {
    			
	    		String id = jsonObject.getString("id"); 
	    		this.setId(Integer.valueOf(id));
		        String shortname = jsonObject.optString("shortname"); 
		        if (shortname != null && shortname.trim().length() > 0)
		        	this.setShortName(shortname);
		        String fullname = jsonObject.optString("fullname");  
		        if (fullname != null && fullname.trim().length() > 0)
		        	this.setFullname(fullname);
		        String enrolledusercount = jsonObject.optString("enrolledusercount");  
		        if (enrolledusercount != null && enrolledusercount.trim().length() > 0)
		        	this.setEnrolledUserCount(Integer.valueOf(enrolledusercount));
		        String idnumber = jsonObject.optString("idnumber"); 
		        if (idnumber != null && idnumber.trim().length() > 0) 
		        	this.setIdNumber(idnumber);
		        String visible = jsonObject.optString("visible");  
		        if (visible != null && visible.trim().length() > 0)
		        	this.setVisible(Integer.valueOf(visible));
		        
    		}
    	} catch (JSONException e) { 
    	    e.printStackTrace(); 
    	}
    }*/
    


    
}
