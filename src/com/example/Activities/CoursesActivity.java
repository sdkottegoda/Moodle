package com.example.Activities;

import java.util.ArrayList;

import org.json.JSONObject;

import com.example.moodle.Client;
import com.example.moodle.R;
import com.example.moodle.Course;
import com.example.moodle.User;
import com.example.moodle.R.layout;
import com.example.moodle.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;

public class CoursesActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_courses);
		// Show the Up button in the action bar.
		setupActionBar();
		

		//display the user's courses in a list
		deployCourses();
		
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
	
	public void deployCourses(){
		final ListView view=(ListView)findViewById(R.id.coursesListView);
		ArrayList<Course> coursesList=User.getInstance().getCourses();
		String[]courses= new String[coursesList.size()];
		for (int i=0;i<coursesList.size();i++){
			courses[i]=coursesList.get(i).getModuleCode()+" - "+coursesList.get(i).getFullname();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
		android.R.layout.simple_list_item_1, courses);
		view.setAdapter(adapter);
		
		view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				courseClicked(position);
			
			}
		
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.courses, menu);
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
	
	public void courseClicked(int position){
		//String view = v.toString();
		//System.out.println(User.getInstance().getCourseAt(position).getFullname());
		
		
		JSONObject courseContent = Client.getInstance().getCourseContent(position);
		System.out.println(courseContent);
		Intent nextPage =  new Intent(CoursesActivity.this, CourseActivity.class);
		nextPage.putExtra("course", User.getInstance().getCourseAt(position));
		startActivity(nextPage);
	}

	
	
	
	

}
