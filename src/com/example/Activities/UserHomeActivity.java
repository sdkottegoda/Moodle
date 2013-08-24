package com.example.Activities;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_home);
		Intent intent =getIntent();
		data=intent.getExtras();
		TextView view=(TextView)findViewById(R.id.user_name);
		try{
			view.setText(" "+this.getUserName());
		}
		catch(NullPointerException e){
			
		}
		
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
		System.out.println(R.string.app_name);
		
	}
	
	//This method is called when the profile button is clicked
	public void profileClicked(View v){
		System.out.println("profile");
		
	}
		
}	
