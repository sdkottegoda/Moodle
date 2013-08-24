package com.example.moodle;

public class Client {

	private String userToken, url;
	
	public Client(String aUrl){
		url=aUrl;
	}
	
	public void login(){
		
	}
	
	public void getMyProfile(){
		
	}
	
	public void getMyCourses(){
		
	}
	
	public void setToken(String aToken){
		this.userToken=aToken;
	}
	
	public String getToken(){
		return this.userToken;
	}
	
	public void getGrades(int aCourseid){
		
	}
	
	public void doHttpRequest(){
		
	}

	public UserHome getUserHomeInfo() {
		UserHome home=new UserHome();
		this.doHttpRequest();
		return home;
	}
	
	
}
