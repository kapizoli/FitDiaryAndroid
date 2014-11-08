package hu.kapi.fitdiary.util;

import hu.kapi.fitdiary.model.Food;
import hu.kapi.fitdiary.model.Meal;
import hu.kapi.fitdiary.model.User;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import android.util.Log;

public class Communication {

	HttpClient httpclient;
	final String MainURL = "http://fitdiary.esy.es/";
	SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	SimpleDateFormat dateformat2 = new SimpleDateFormat("yyyy-MM-dd");

	public Communication() {
		httpclient = new DefaultHttpClient();
	}

	public String httpPost(String file, HashMap<String, String> post)
			throws ClientProtocolException, IOException {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

		Iterator it = post.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			nameValuePairs.add(new BasicNameValuePair((String) pairs.getKey(),
					(String) pairs.getValue()));
		}

		HttpPost httppost = new HttpPost(MainURL + file);
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
		HttpResponse response = httpclient.execute(httppost);
		String data = new BasicResponseHandler().handleResponse(response);
		Log.d("Communication", "httppost data " + data);
		return data;
	}

	public User authenticationUser(String email, String password) {
		HashMap<String, String> post = new HashMap<String, String>();
		post.put("action", "GET");
		post.put("Email", email);
		post.put("Password", password);

		try {
			String data = httpPost("user.php", post);
			JSONArray jsonArray = new JSONArray(data);
			JSONObject jsonObject = jsonArray.getJSONObject(0);
			int user_id = Integer.parseInt(jsonObject.getString("id"));
			String nick_name2 = jsonObject.getString("nick_name");
			String password2 = jsonObject.getString("password");
			String email2 = jsonObject.getString("email");
			int sex = Integer.parseInt(jsonObject.getString("sex"));
			String b = jsonObject.getString("birthday");
			Date birthday = dateformat.parse(b);
			String u = jsonObject.getString("last_updated");
			Date lastUpdated = dateformat.parse(u);

			User newUser = new User(user_id, nick_name2, password2, email2,
					sex, birthday, lastUpdated);
			return newUser;
		} catch (Exception e) {
			Log.e("Communication", "error " + e);
		}

		return null;
	}

	public User registerANewUser(String nick_name, String password,
			String email, int sex, String birthday) throws ParseException {

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

			if (data.equals("FAILED - EMAIL ALREADY USED")) {
				return new User(-1);
			}
			if (data.equals("FAILED")) {
				return null;
			}
			JSONObject jsonObject = new JSONObject(data);
			int user_id = Integer.parseInt(jsonObject.getString("NewID"));
			User newUser = new User(user_id, nick_name, password, email, sex,
					date, date2);
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

	public void getMealListForUser(Date begin, Date end) {
		int userID = Session.getInstance().getActualUser().getId();
		Calendar cal = Calendar.getInstance();
		cal.setTime(begin);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		begin = cal.getTime();

		cal = Calendar.getInstance();
		cal.setTime(end);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		end = cal.getTime();

		HashMap<String, String> post = new HashMap<String, String>();
		post.put("action", "GetByDays");
		post.put("user_id", String.valueOf(userID));
		post.put("begin_date", String.valueOf(new Timestamp(begin.getTime())));
		post.put("end_date", String.valueOf(new Timestamp(end.getTime())));

		try {
			String data = httpPost("meal.php", post);
			if (data.equals("FAILED")) {
				return;
			}

			Session.getInstance().getActualUser()
					.addMeals(parseMealJSON(new JSONObject(data)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setAllMealForUser() {
		int userID = Session.getInstance().getActualUser().getId();
		HashMap<String, String> post = new HashMap<String, String>();
		post.put("action", "GET");
		post.put("user_id", String.valueOf(userID));

		try {
			String data = httpPost("meal.php", post);
			if (data.equals("FAILED")) {
				return;
			}

			Session.getInstance().getActualUser()
					.setMealList(parseMealJSON(new JSONObject(data)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void setFoodPlan() {
		HashMap<String, String> post = new HashMap<String, String>();
		post.put("action", "GETFOOD");

		try {
			String data = httpPost("plan.php", post);
			if (data.equals("FAILED")) {
				return;
			}
			Session.getInstance().setFoodPlanList(parseFoodListJSON(new JSONArray(data)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ArrayList<Meal> parseMealJSON(JSONObject json)
			throws JSONException, ParseException {
		ArrayList<Meal> list = new ArrayList<Meal>();

		JSONArray meals = json.getJSONArray("meals");
		for (int i = 0; i < meals.length(); i++) {
			String d = meals.getJSONObject(i).getString("date");
			Date date = dateformat.parse(d);
			Meal meal = new Meal(Integer.valueOf(meals.getJSONObject(i)
					.getString("id")), date, meals.getJSONObject(i).getString(
					"name"));

			if (meals.getJSONObject(i).has("foods")) {
				JSONArray foods = meals.getJSONObject(i).getJSONArray("foods");
				
				for (int j = 0; j < foods.length(); j++) {
					meal.addFoodToList(parseFoodJSON(foods.getJSONObject(j)));
				}
			}
			list.add(meal);
		}

		return list;
	}

	private Food parseFoodJSON(JSONObject json) throws JSONException,
			ParseException {
		int iD = Integer.valueOf(json.getString("id"));
		String name = json.getString("name");
		int unit = Integer.valueOf(json.getString("unit"));
		double quantity = Double.valueOf(json.getString("quantity"));
		double fat = Double.valueOf(json.getString("fat"));
		double sugar = Double.valueOf(json.getString("sugar"));
		double energy = Double.valueOf(json.getString("energy"));
		double carbohidrate = Double.valueOf(json.getString("carbohidrate"));
		double protein = Double.valueOf(json.getString("protein"));
		int daily_category = Integer.valueOf(json.getString("daily_category"));
		int resource_category = Integer.valueOf(json
				.getString("resource_category"));
		
		return new Food(iD, name, unit, quantity, fat, sugar, energy,
				carbohidrate, protein, daily_category, resource_category);
	}
	
	private ArrayList<Food> parseFoodListJSON(JSONArray array) throws JSONException,
	ParseException {
		ArrayList<Food> list = new ArrayList<Food>();
		for (int j = 0; j < array.length(); j++) {
			list.add(parseFoodJSON(array.getJSONObject(j)));
		}
		return list;
	}
}
