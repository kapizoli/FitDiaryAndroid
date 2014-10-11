package hu.kapi.fitdiary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	public Fragment actualFragment;

	protected Session() {
		this.communication = new Communication();
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
	
}
