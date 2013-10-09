package com.example.Activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.text.LoginFilter.UsernameFilterGeneric;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import com.example.moodle.*;
public class UserHomeActivity extends Activity {
	
	private Intent mainHomeIntent;
	private Bundle data;
	private Client client;
	private User user;
	private UserHome home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		user=User.getInstance();
		
		TextView view=(TextView)findViewById(R.id.user_name);
		try{
			view.setText(" "+user.getFullName());
		}
		catch(NullPointerException e){
			view.setText(" "+"safsg"+" "+"xfgch");
		}
	}
	
	public User getUser(){
		return user;
	}
	
	//Extract the bundle as a user
	public User getUserData(){
		return (User)data.get("userObject");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_home, menu);
		return true;
	}
	
	//extract the user name from the bundle passed upon creation
	public String getUserName(){
		return getUserData().getUsername();
	}
	
	//This method is called when the courses button is clicked
	public void coursesClicked(View v){
		User user=User.getInstance();
		//call the client's instance's method to get the courses in which the user is enrolled
		JSONObject courses=Client.getInstance().getCourses();

		JSONArray coursesArray;
		try {
			//Use the JSON object to add the courses to the user
			coursesArray = courses.getJSONArray("courses");
			ArrayList<Course> userCourses = new ArrayList<Course>();
			for (int i=0;i<coursesArray.length();i++){
				userCourses.add(new Course(new JSONObject(coursesArray.getString(i))));
				System.out.println(coursesArray.getString(i));
			}
			user.setCourses(courses);
			
			//start the Courses Activity 
			Intent nextPage =  new Intent(UserHomeActivity.this, CoursesActivity.class);
			startActivity(nextPage);
			
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
	}
	
	//This methos is called when the setting button is clicked
	public void settingsClicked(View v){
		//to be implemented
	}
	
	
	//This method is called when the profile button is clicked
	public void profileClicked(View v){
		
		System.out.println("fhfghgjghfdgfghfhfdhjfdg");
		//call the method of Client's instance to get the profile info
		JSONObject userInfo = Client.getInstance().getMyProfile();
		
		Intent nextPage =  new Intent(UserHomeActivity.this, ProfileActivity.class);
		
		//create a new userinfo parcel, put into the intent and start the intent
		UserInfo parcel = new UserInfo();
		try {
			parcel.setaddress(userInfo.getString("address"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
		try{
			parcel.setCity(userInfo.getString("city"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
		try{
			parcel.setCountry(userInfo.getString("country"));
		}
		catch (JSONException e) {
		// TODO Auto-generated catch block

		}
		try{
			parcel.setEmail(userInfo.getString("email"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
		try{
			parcel.setphone1(userInfo.getString("phone1"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
		try{
			parcel.setphone2(userInfo.getString("phone2"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
		try{
			parcel.seticq(userInfo.getString("icq"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
		try{
			parcel.setCourses(userInfo.getJSONArray("courses"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block

		}
		
		nextPage.putExtra("userInfo",parcel);
		System.out.println(((UserInfo)nextPage.getExtras().get("userInfo")).getEmail());
		startActivity(nextPage);
	}
	
	public static class UserInfo implements Parcelable{
		
		public UserInfo(){
			
		}
		
		private String emailAddress,phone1,phone2,address,icq,city,country,courses;
		
		public void setEmail(String anEmail){
			emailAddress = anEmail;
		}
		
		public void setCourses(JSONArray coursesArray) {
			courses = "";
			//System.out.println(coursesArray.toString());
			for (int i=0;i<coursesArray.length();i++){
				try {
					//System.out.println(coursesArray.getString(i));
					if (i!=0){
						courses = courses+", ";
					}
					courses = courses+(new JSONObject(coursesArray.getString(i))).getString("fullname");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(courses);
			System.out.println(courses);
		}
		
		public String getEmail(){
			return this.emailAddress;
		}
		
		public void setphone1(String anEmail){
			phone1 = anEmail;
		}
		
		public void setphone2(String anEmail){
			phone2 = anEmail;
		}
		
		public void setaddress(String anEmail){
			address = anEmail;
		}
		
		public void seticq(String anEmail){
			icq = anEmail;
		}
		
		public void setCity(String anEmail){
			city = anEmail;
		}
		
		public void setCountry(String anEmail){
			country = anEmail;
		}
		@Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		 

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			dest.writeString(emailAddress);
			dest.writeString(phone1);
			dest.writeString(phone2);
			dest.writeString(address);
			dest.writeString(icq);
			dest.writeString(city);
			dest.writeString(country);
			dest.writeString(courses);
		}
		
		public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() { 
	        public UserInfo createFromParcel(Parcel in) { 
	            return new UserInfo(in); 
	        }

			@Override
			public UserInfo[] newArray(int size) {
				// TODO Auto-generated method stub
				return new UserInfo[size];
			} 
	    }; 
	    
	    private UserInfo(Parcel in) {
	        this.emailAddress = in.readString();
	        this.phone1 = in.readString();
	        this.phone2 = in.readString();
	        this.address = in.readString();
	        this.icq = in.readString();
	        this.city = in.readString();
	        this.country = in.readString();
	        this.courses = in.readString();
	    }

		public String getAddress() {
			// TODO Auto-generated method stub
			return this.address;
		}

		public String getCountry() {
			// TODO Auto-generated method stub
			return this.country;
		}

		public String getCity() {
			// TODO Auto-generated method stub
			return this.city;
		}

		public String getIcq() {
			// TODO Auto-generated method stub
			return this.icq;
		}

		public String getPhones() {
			// TODO Auto-generated method stub
			String phones="";
			if (this.phone1 != null){
				phones=phones+phone1;
				if (this.phone2 !=null){
					phones=phones+", "+phone2;
				}
			}
			else if(this.phone2 !=null){
				phones=phone2;
			}
			return phones;
		}

		public String getCourses() {
			return this.courses;
		}

	}
		
}	
