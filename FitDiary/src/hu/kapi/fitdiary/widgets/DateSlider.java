package hu.kapi.fitdiary.widgets;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab0;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab1;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab2;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DateSlider extends LinearLayout{
	Fragment fragment;
	ImageView leftButton, rightButton;
	TextView date;
	Calendar cal, today, yesterday, tomorrow;
	LayoutParams param;
	SimpleDateFormat sdfhun = new SimpleDateFormat("EEE | yyyy.MM.dd");
	SimpleDateFormat sdfen = new SimpleDateFormat("EEE | MM.dd.yyyy");
	Resources res;
	String language;
	
	
	public DateSlider(Context context, Fragment fragment) {
		super(context);
		this.fragment = fragment;
		cal = Calendar.getInstance();
		today = Calendar.getInstance();
		tomorrow = Calendar.getInstance();
		yesterday = Calendar.getInstance();
//		tomorrow.setTime(tomorrow.getTime());
		tomorrow.add(Calendar.DATE, 1);
		
//		yesterday.setTime(yesterday.getTime());
		yesterday.add(Calendar.DATE, -1);
		res = context.getResources();
		language = res.getString(R.string.language);
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setWeightSum(6.0f);
		this.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) (height*0.05)));
		this.setBackgroundColor(Color.LTGRAY);
		leftButton = new ImageView(context);
		leftButton.setImageResource(R.drawable.left);
		param = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
		this.addView(leftButton,param);
		date = new TextView(context);
		date.setText(res.getString(R.string.today));
		date.setTextColor(Color.BLACK);
		param = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT, 4.0f);
		date.setGravity(Gravity.CENTER);
		this.addView(date, param);
		rightButton = new ImageView(context);
		rightButton.setImageResource(R.drawable.right);
		param = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
		this.addView(rightButton, param);
		
		//onClicklisteners:
		leftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				cal.setTime(cal.getTime());
				cal.add(Calendar.DATE, -1);
				if (cal.equals(today)) {
					date.setText(res.getString(R.string.today));
				} else if (cal.equals(yesterday)){
					date.setText(res.getString(R.string.yesterday));
				} else if (cal.equals(tomorrow)){
					date.setText(res.getString(R.string.tomorrow));
				} else if ("hun".equals(language)) {
					date.setText(sdfhun.format(cal.getTime()));
				} else {
					date.setText(sdfen.format(cal.getTime()));
				}
				
				//refresh the list
				//Complete me
				String tab = DateSlider.this.fragment.getClass().getName();
				
				if (DiaryFragmentTab0.class.getName().equals(tab)) {
					
				} else if (DiaryFragmentTab1.class.getName().equals(tab)) {
					((DiaryFragmentTab1)DateSlider.this.fragment).refreshMealList(cal.getTime());					
				} else if (DiaryFragmentTab2.class.getName().equals(tab)) {
					
				} 
			}
		});
		
		rightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				cal.setTime(cal.getTime());
				cal.add(Calendar.DATE, 1);
				if (cal.equals(today)) {
					date.setText(res.getString(R.string.today));
				} else if (cal.equals(yesterday)){
					date.setText(res.getString(R.string.yesterday));
				} else if (cal.equals(tomorrow)){
					date.setText(res.getString(R.string.tomorrow));
				} else if ("hun".equals(language)) {
					date.setText(sdfhun.format(cal.getTime()));
				} else {
					date.setText(sdfen.format(cal.getTime()));
				}
				
				//refresh the list
				//Complete me
				String tab = DateSlider.this.fragment.getClass().getName();
				
				if (DiaryFragmentTab0.class.getName().equals(tab)) {
					
				} else if (DiaryFragmentTab1.class.getName().equals(tab)) {
					((DiaryFragmentTab1)DateSlider.this.fragment).refreshMealList(cal.getTime());					
				} else if (DiaryFragmentTab2.class.getName().equals(tab)) {
					
				}
			}
		});
	}
	
	public Calendar getDateAsCalendar(){
		return cal;
	}

}
