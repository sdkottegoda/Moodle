package com.example.Activities;

import com.example.Activities.UserHomeActivity.UserInfo;
import com.example.moodle.App;
import com.example.moodle.R;
import com.example.moodle.User;
import com.example.moodle.R.layout;
import com.example.moodle.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import android.widget.TextView;

public class ProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		// Show the Up button in the action bar.
		setupActionBar();
		
		//call the method to write user profile info
		this.populateData();
		
		
	}

	//method to write the profile information by processing the intent as well as the user data
	private void populateData() {
		UserInfo info = (UserInfo)this.getIntent().getExtras().get("userInfo");
		User user = User.getInstance();
		String toBeWritten=info.getAddress();
		if (toBeWritten!=null){
			((TextView)this.findViewById(R.id.addressTextView)).setText("Address: "+toBeWritten);
		}
		toBeWritten = info.getCity();
		if (toBeWritten!=null){
			toBeWritten = "City: "+info.getCity();
			if (info.getCountry()!=null){
				toBeWritten = toBeWritten+info.getCountry();
			}
			((TextView)this.findViewById(R.id.cityCountryTextView)).setText("City: "+info.getCity()+", "+info.getCountry());
		}
		else{
			toBeWritten = info.getCountry();
			if (toBeWritten!=null){
				((TextView)this.findViewById(R.id.cityCountryTextView)).setText("Country: "+toBeWritten);
			}
		}
		toBeWritten = info.getEmail();
		if (toBeWritten !=null){
			((TextView)this.findViewById(R.id.emailTextView)).setText("Email address: "+toBeWritten);
		}
		toBeWritten = info.getIcq();
		if (toBeWritten!=null){
			((TextView)this.findViewById(R.id.icqTextView)).setText("ICQ: "+info.getIcq());
		}
		((TextView)this.findViewById(R.id.nameTextView)).setText("Name: "+user.getFullName());
		toBeWritten = info.getPhones();
		if (toBeWritten !=null){
			((TextView)this.findViewById(R.id.phoneTextView)).setText("Contact numbers: "+info.getPhones());
		}
		toBeWritten = info.getCourses();
		if (toBeWritten !=null){
			((TextView)this.findViewById(R.id.coursesTextView)).setText("Courses enrolled: "+info.getCourses());
		}
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public Bundle getData(){
		return this.getIntent().getExtras();
	}

}
