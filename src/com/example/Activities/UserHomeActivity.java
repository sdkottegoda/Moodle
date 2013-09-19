package com.example.Activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
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
		JSONObject courses=Client.getInstance().getCourses();

		JSONArray coursesArray;
		try {
			coursesArray = courses.getJSONArray("courses");
			ArrayList<Course> userCourses = new ArrayList<Course>();
			for (int i=0;i<coursesArray.length();i++){
				userCourses.add(new Course(new JSONObject(coursesArray.getString(i))));
				System.out.println(coursesArray.getString(i));
			}
			user.setCourses(courses);
			
			Intent nextPage =  new Intent(UserHomeActivity.this, CoursesActivity.class);
			startActivity(nextPage);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//This method is called when the profile button is clicked
	public void profileClicked(View v){
			
	}
		
}	
