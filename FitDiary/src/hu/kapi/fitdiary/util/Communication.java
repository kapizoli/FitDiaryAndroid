package hu.kapi.fitdiary.util;

import hu.kapi.fitdiary.RegistrationActivity;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.util.Log;

public class Communication {

	HttpClient httpclient;
	final String MainURL = "http://fitdiary.netne.net/";
	 SimpleDateFormat dateformat = new SimpleDateFormat(
             "yyyy-MM-dd hh:mm:ss");
	 SimpleDateFormat dateformat2 = new SimpleDateFormat(
             "yyyy-MM-dd");
	 
	public Communication() {
        httpclient = new DefaultHttpClient();
    }
	
	public String httpPost(String file, HashMap<String, String> post) throws ClientProtocolException, IOException {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        Iterator it = post.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            nameValuePairs.add(new BasicNameValuePair((String) pairs.getKey(), (String) pairs.getValue()));
        }

        HttpPost httppost = new HttpPost(MainURL + file);
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        HttpResponse response = httpclient.execute(httppost);
        String data = new BasicResponseHandler().handleResponse(response);
        Log.d("Communication", "httppost data "+data);
        return data;
    }
	
	public User authenticationUser(String name, String password) {
		HashMap<String, String> post = new HashMap<String, String>();
        post.put("action", "GET");
        post.put("NickName", name);
        post.put("Password", password);

        try {
            String data = httpPost("user.php", post);
            Log.d("Communication", "data "+data);
            JSONArray jsonArray = new JSONArray(data);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Log.d("Communication", "jsonObject "+jsonObject.toString());
            int user_id = Integer.parseInt(jsonObject.getString("id"));
            String nick_name2 = jsonObject.getString("nick_name");
            String password2 = jsonObject.getString("password");
            String email = jsonObject.getString("email");
            int sex = Integer.parseInt(jsonObject.getString("sex"));
            String b = jsonObject.getString("birthday");
            Date birthday = dateformat.parse(b);
            String u = jsonObject.getString("last_updated");
            Date lastUpdated = dateformat.parse(u);

            User newUser = new User(user_id, nick_name2, password2, email, sex, birthday, lastUpdated);
            Log.d("Communication", "logged user: "+newUser.toString());
            return newUser;
        } catch (Exception e) {
        	Log.e("Communication", "error "+e);
        }

        return null;
	}
	
	public User registerANewUser(String nick_name, String password, String email, int sex, String birthday) throws ParseException {

        HashMap<String, String> post = new HashMap<String, String>();
        post.put("action", "ADD");
        post.put("NickName", nick_name);
        post.put("Password", password);
        post.put("Email", email);
        post.put("Sex", String.valueOf(sex));
        Date date = dateformat2.parse(birthday);
        post.put("Birthday", String.valueOf(new Timestamp(date.getTime())));
        Date date2 = Session.GetUTCdatetimeAsDate();
        post.put("Last_updated", String.valueOf(new Timestamp(date2.getTime())));
        

        try {
            String data = httpPost("user.php", post);

            //TODO:ha az email már használatban van le kell kezelni
            if (data.equals("FAILED - EMAIL ALREADY USED")) {
            	Log.d("Communication", "if FAILED - EMAIL ALREADY USED");
            	//throw new emailalreadyusedexception
            }
            if (data.equals("FAILED")) {
            	Log.d("Communication", "if FAILED");
                return null;
            }
            JSONObject jsonObject = new JSONObject(data);
            int user_id = Integer.parseInt(jsonObject.getString("NewID"));
            User newUser = new User(user_id, nick_name, password, email, sex, date, date2);
            Log.d("Communication", "registrated user: "+newUser.toString());
            return newUser;
        } catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }
	
	
}
