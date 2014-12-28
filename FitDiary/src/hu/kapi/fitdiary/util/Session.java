package hu.kapi.fitdiary.util;

import hu.kapi.fitdiary.model.Food;
import hu.kapi.fitdiary.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class Session {

	private static Session instance = null;
	User actualUser;
	boolean isOnline;
	Communication communication;
	public ProgressDialog progressDialog = null;
	public static Toast lastToast = null;
	public static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public static SimpleDateFormat sdf;
	public Fragment actualFragment;
	public ArrayList<Food> foodPlanList;

	protected Session() {
		this.communication = new Communication();
		sdf = new SimpleDateFormat(Session.DATEFORMAT);
	}

	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		return instance;
	}
	
	public static void closeSession(){
		instance = null;
	}
	
	public Communication getActualCommunication() {
        return communication;
    }

	public User getActualUser() {
		return actualUser;
	}

	public void setActualUser(User actualUser) {
		this.actualUser = actualUser;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
	}
	
	public static Date GetUTCdatetimeAsDate()
	{
	    //note: doesn't check for null
	    return StringDateToDate(GetUTCdatetimeAsString());
	}

	public static String GetUTCdatetimeAsString()
	{
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(new Date());

	    return utcTime;
	}

	public static Date StringDateToDate(String StrDate)
	{
	    Date dateToReturn = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);

	    try
	    {
	        dateToReturn = (Date)dateFormat.parse(StrDate);
	    }
	    catch (ParseException e)
	    {
	        e.printStackTrace();
	    }

	    return dateToReturn;
	}

	public ArrayList<Food> getFoodPlanList() {
		return foodPlanList;
	}

	public void setFoodPlanList(ArrayList<Food> foodPlanList) {
		this.foodPlanList = foodPlanList;
	}
	
	//0-g, 1-mg, 2-l, 3-dl, 4-cl, 5-ml, 6-db 
	public String getUnitByInt(int i){
		switch (i) {
		case 0:
			return "g";
		case 1:
			return "mg";
		case 2:
			return "l";
		case 3:
			return "dl";
		case 4:
			return "cl";
		case 5:
			return "ml";
		case 6:
			return "db";
		default:
			return "n/a";
		}
	}
	
	//0-g, 1-mg, 2-l, 3-dl, 4-cl, 5-ml, 6-db 
		public int getIntByUnitString(String s){
			if (s.equalsIgnoreCase("g")) {
				return 0;
			} else if (s.equalsIgnoreCase("mg")) {
				return 1;
			} else if (s.equalsIgnoreCase("l")) {
				return 2;
			} else if (s.equalsIgnoreCase("dl")) {
				return 3;
			} else if (s.equalsIgnoreCase("cl")) {
				return 4;
			} else if (s.equalsIgnoreCase("ml")) {
				return 5;
			} else if (s.equalsIgnoreCase("db")){
				return 6;
			} else {
				return 0;
			}
		}
}
