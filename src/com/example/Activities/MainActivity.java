package com.example.Activities;

import java.util.ArrayList;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.*;
import java.io.*;

import com.example.moodle.AppStatus;
import com.example.moodle.Client;
import com.example.moodle.R;
import com.example.moodle.TokenHttpRequest;
import com.example.moodle.User;
import com.example.moodle.R.id;
import com.example.moodle.R.layout;
import com.example.moodle.R.menu;

public class MainActivity extends Activity {
	
	
	
	Button login;
	EditText siteUrl, username, password;
	User user;
	SharedPreferences saved;
	String loginDetails;
	
	ProgressDialog dialog;

	
	
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /*public void sendMessage(View aView){
    	System.out.println("Send message");

    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
   
    	intent.putExtra(EXTRA_MESSAGE, message);
    	
    	startActivity(intent);
    }*/
    
    private String usr;
    private String pwd;
    private Client client;
    
    public void login(View v){
        	
    	//Intent intent = new Intent(this,UserHomeActivity.class);
    	
    	//Read the user name text field and store user name
    	EditText eText1=(EditText) findViewById(R.id.username);
    	usr=eText1.getText().toString();
    	
    	//Read the password text field and store password
    	EditText eText2=(EditText) findViewById(R.id.password);
    	pwd=eText2.getText().toString();
    	
    	switch(v.getId())
		{
			case R.id.login_button:
				//this dialog box needs to be run separately....
				
				dialog = ProgressDialog.show(this, "", 
		                "Logging in, please wait", true);
				
				new Thread(new Runnable(){
        		public void run(){
        		try{	
				
        			if (AppStatus.getInstance(MainActivity.this).isOnline(MainActivity.this)) {  
        				
        				String conType = AppStatus.getInstance(MainActivity.this).getConnectionType(MainActivity.this);
        				if (conType == null){
        					conType = "Unknown";
        				}
					} 
        			else {     
        				messageHandler.sendEmptyMessage(0);
        				runOnUiThread(new Runnable(){
        					public void run(){
        						Toast.makeText(getApplicationContext(), "You are not online!!!!", Toast.LENGTH_LONG).show();
        					}
        				});
	        			
	        			// enter details for offline access to some files. Restore the database.
	        		
        			} 
        		}
        		catch (Exception e){
        			System.out.println(e.getMessage());
        		}
        		
				String siteUrlVal = "http://10.0.2.2/Moodle/moodle/";

				System.out.print("token");
				//checks for http:// entry
				
				/*if(!(siteUrlVal.substring(0, 7).toLowerCase().compareTo("http://")==0)) {
					siteUrlVal = "http://"+siteUrlVal;
					siteUrl.setText(siteUrlVal);
				}*/
				
				
				String usrUri = Uri.encode(usr);
				String pwdUri = Uri.encode(pwd);
				
				
				saved = getSharedPreferences(loginDetails, MODE_PRIVATE);
			
				SharedPreferences.Editor e = saved.edit();
				e.putString("siteUrlVal", siteUrlVal);
				e.putString("usr", usr);
				e.putString("pwd", pwd);
				e.commit();

				
				
				String url = siteUrlVal + "/login/token.php?username=" + usrUri + "&password=" + pwdUri + "&service=moodle_mobile_app";
				TokenHttpRequest tokenRequest = new TokenHttpRequest();
				String token = tokenRequest.doHTTPRequest(url); 
		        //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_LONG).show(); 
		        
		        if (token != null && token != "") {
		        	

					dialog.dismiss();
		        	String serverurl = siteUrlVal + "/webservice/rest/server.php" + "?wstoken=" + token + "&wsfunction=";
			        
			        user = new User();
			        user.setUsername(usr);
			        user.setPassword(pwd);
			        user.setToken(token);
			        user.setTokenCreateDate();
			        user.setUrl(url);
			        
			        Client client = new Client(serverurl);
			        user.setHome(client.getUserHomeInfo());
			        /*try{
			        MoodleWebService webService = new MoodleWebService(MainActivity.this);
			        SiteInfo siteInfo = new SiteInfo();
			        webService.getSiteinfo(serverurl, siteInfo);
			        user.setSiteInfo(siteInfo);
			        ArrayList<Course> courses = new ArrayList<Course>();
			        webService.getUserCourses(serverurl, siteInfo.getUserid(), courses);
			        
			        	
			        if (courses.size() > 0) {		        	
			        	for(int i = 0; i < courses.size(); i++) { 
			    	        Course c = courses.get(i); 
			    	        ArrayList<CourseContent> coursecontents = new ArrayList<CourseContent>();
			    	        webService.getCourseContents(serverurl, c.getId(), coursecontents);
			    	        
			    	        if (coursecontents.size() > 0) {
			    	        	c.setCourseContent(coursecontents);
			    	        }
			    	    } 
			        	user.setCourses(courses);
			        	*/
			        	Intent nextPage =  new Intent(MainActivity.this, UserHomeActivity.class);
						nextPage.putExtra("userObject", user); 
						startActivity(nextPage);/*
			        }
			        else {
			        	messageHandler.sendEmptyMessage(0);
		        		
			        	Log.e("Course Error", "User is not enrolled in any courses");
			        	Toast.makeText(getApplicationContext(), "This User is not Enrolled in any Courses, please contact your Lecturer", Toast.LENGTH_LONG).show();
			        	
			        }
			        }
			        catch(Exception exc){
			        	System.out.println(exc.getMessage());
			        }*/
			        System.out.println(token);
		        } 
		        else {
		        	
		        	messageHandler.sendEmptyMessage(0);
		        	runOnUiThread(new Runnable(){
    					public void run(){
    						Toast.makeText(getApplicationContext(), "The username and password are incorrect. Please try again!", Toast.LENGTH_LONG).show();
    			        	Toast.makeText(getApplicationContext(), "If details are correct contact Site Admin to enable REST protocol", Toast.LENGTH_LONG).show();
    					}
    				});
		        	
		        	
		        	
		        }
		        
	        		}
	        	}).start();
	            
		    break;
			default:
					
				
		}
    	
    	
	}
    
    public Client getClient(){
		return this.client;
	}
    
	private Handler messageHandler = new Handler() {

	    public void handleMessage(Message msg) {
	        super.handleMessage(msg);
	        dialog.dismiss();

	    }
	};

    	
    }
    

