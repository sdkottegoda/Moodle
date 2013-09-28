package com.example.moodle;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Client extends AsyncTask<URL, Integer, Long>{

	private static Client instance = null;
	
	private Client(){
	}
	
	//method to return the instance of the class
	//each time the instance is returned it'll be returned according the relevant context

	
	public static Client getInstance(){
		if (instance == null){
			instance = new Client();
		}
			return instance;
	}
	public void login(){
		
	}
	
		
	public void getGrades(int aCourseid){
		
	}
	
	public void doHttpRequest(){
		
	}
	
	/*public void connect(String aUrl){
		Client c = getInstance();
	}*/

	
	@SuppressWarnings("finally")
	public JSONObject makeRequest(String token, String domainName, String aFunction, String para, String aFileName) {
		/// NEED TO BE CHANGED
        //String token = "edb1837fe76cbd7f1db866b82e58ea19";
        //String domainName = "http://localhost/Moodle/moodle";

        /// REST RETURNED VALUES FORMAT
        //String restformat = "xml"; //Also possible in Moodle 2.2 and later: 'json'
                                   //Setting it to 'json' will fail all calls on earlier Moodle version
        //if (restformat.equals("json")) {
            String restformat = " ";
        //} else {
         //   restformat = "";
        //}

        /// PARAMETERS - NEED TO BE CHANGED IF YOU CALL A DIFFERENT FUNCTION
        /*String functionName = "core_user_create_users";
        String urlParameters =
        "users[0][username]=" + URLEncoder.encode("testusername1", "UTF-8") +
        "&users[0][password]=" + URLEncoder.encode("testpassword1", "UTF-8") +
        "&users[0][firstname]=" + URLEncoder.encode("testfirstname1", "UTF-8") +
        "&users[0][lastname]=" + URLEncoder.encode("testlastname1", "UTF-8") +
        "&users[0][email]=" + URLEncoder.encode("testemail1@moodle.com", "UTF-8") +
        "&users[0][auth]=" + URLEncoder.encode("manual", "UTF-8") +
        "&users[0][idnumber]=" + URLEncoder.encode("testidnumber1", "UTF-8") +
        "&users[0][lang]=" + URLEncoder.encode("en", "UTF-8") +
        "&users[0][theme]=" + URLEncoder.encode("standard", "UTF-8") +
        "&users[0][timezone]=" + URLEncoder.encode("-12.5", "UTF-8") +
        "&users[0][mailformat]=" + URLEncoder.encode("0", "UTF-8") +
        "&users[0][description]=" + URLEncoder.encode("Hello World!", "UTF-8") +
        "&users[0][city]=" + URLEncoder.encode("testcity1", "UTF-8") +
        "&users[0][country]=" + URLEncoder.encode("au", "UTF-8") +
        "&users[0][preferences][0][type]=" + URLEncoder.encode("preference1", "UTF-8") +
        "&users[0][preferences][0][value]=" + URLEncoder.encode("preferencevalue1", "UTF-8") +
        "&users[0][preferences][1][type]=" + URLEncoder.encode("preference2", "UTF-8") +
        "&users[0][preferences][1][value]=" + URLEncoder.encode("preferencevalue2", "UTF-8") +
        "&users[1][username]=" + URLEncoder.encode("testusername2", "UTF-8") +
        "&users[1][password]=" + URLEncoder.encode("testpassword2", "UTF-8") +
        "&users[1][firstname]=" + URLEncoder.encode("testfirstname2", "UTF-8") +
        "&users[1][lastname]=" + URLEncoder.encode("testlastname2", "UTF-8") +
        "&users[1][email]=" + URLEncoder.encode("testemail2@moodle.com", "UTF-8") +
        "&users[1][timezone]=" + URLEncoder.encode("Pacific/Port_Moresby", "UTF-8");
*/
        /// REST CALL

        // Send request
        //String serverurl = domainName + "/login/token.php?username=wsuser&password=weuser@1&service=moodle_mobile_app";
        HttpURLConnection con;
        JSONObject jsonobj = null;
        String url=domainName+"/webservice/rest/server.php?wstoken="+token+"&wsfunction="+aFunction;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy); 
        
        System.out.println(url.toString());
        try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setRequestMethod("POST");
	        con.setRequestProperty("Content-Type",
	           "application/x-www-form-urlencoded");
	        con.setRequestProperty("Content-Language", "en-US");
	        con.setDoOutput(true);
	        con.setUseCaches (false);
	        con.setDoInput(true);
	        DataOutputStream wr = new DataOutputStream (
	                  con.getOutputStream ());
	        Log.d("URLParameters: ", para);
			wr.writeBytes (para);
	        wr.flush ();
	        wr.close ();
	        //Get Response
	        InputStream is =con.getInputStream();
	        /*BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String line;
	        StringBuilder response = new StringBuilder();
	        while((line = rd.readLine()) != null) {
	            response.append(line);
	            response.append('\r');
	        }
	        rd.close();
	        System.out.println(response.toString());
	        return null;
	        */
	        Source xmlSource = new StreamSource(is); 
	        Context context = App.getContext();
	        int xml_id = context.getResources().getIdentifier(aFileName, "raw", context.getPackageName());
            Source xsltSource = new StreamSource(context.getResources().openRawResource(xml_id)); 
 
            TransformerFactory transFact = TransformerFactory.newInstance(); 
            Transformer trans;
            
            
			try {
				trans = transFact.newTransformer(xsltSource);
				 
	            StringWriter writer = new StringWriter(); 	            
	            try {
					trans.transform(xmlSource, new StreamResult(writer));
				} catch (TransformerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println(e1.toString());
				}   
	            String jsonstr = writer.toString();
	        	jsonstr = jsonstr.replace("<div class=\"no-overflow\"><p>", "");
	        	jsonstr = jsonstr.replace("</p></div>", "");
	        	jsonstr = jsonstr.replace("<p>", "");
	        	jsonstr = jsonstr.replace("</p>", "");
	        	Log.d("TransformObject: ", jsonstr);
				System.out.println(jsonstr);
	        	try {
					jsonobj = new JSONObject(jsonstr);

					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.toString());
				}
			} catch (TransformerConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				//return null;
			}      
            	
        	
	        
	        
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
			System.out.println(e.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
        catch(Exception e){
        	System.out.println(e.toString());
        }
		finally{
			return jsonobj;
		}
		
		
	}
	/*
	public JSONObject convert(String message){
		
	}*/
	
	public JSONObject getCourses(){
		User user = User.getInstance();
		return this.makeRequest(user.getToken(), App.getDomainURL(), "moodle_enrol_get_users_courses", "userid="+user.getId(), "coursesxsl");
	}
	
	public JSONObject getCourseContent(int aPosition){
		User user=User.getInstance();
		Course course=user.getCourseAt(aPosition);
		return this.makeRequest(user.getToken(), App.getDomainURL(), "core_course_get_contents", "courseid="+course.getId(), "contentxsl"); 
	}
	
	public JSONObject getMyProfile(){
		User user = User.getInstance();
		JSONObject users = this.makeRequest(user.getToken(), App.getDomainURL(), "moodle_user_get_users_by_id", "userids[0]="+user.getId(), "profilexsl");
		try {
			String user0 = users.getJSONArray("users").getString(0);
			return new JSONObject(user0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected Long doInBackground(URL... arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
