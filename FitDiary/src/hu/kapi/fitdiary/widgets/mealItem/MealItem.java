package hu.kapi.fitdiary.widgets.mealItem;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.model.Meal;
import hu.kapi.fitdiary.util.Session;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MealItem extends LinearLayout {
	
	LinearLayout headerRootLayout, bodyLayout, headerLeftLayout, headerRightLayout;
	TextView headerNameTv, headerDateTv, headerCalorieTv;
	TextView bodyCalorieTv, bodyFatTv, bodyCarbohydrateTv, bodyProteinTv;
	TextView bodyCalorieNumberTv, bodyFatNumberTv, bodyCarbohydrateNumberTv, bodyProteinNumberTv;
	LayoutParams params;
	Context mContext;

	public MealItem(Context context) {
		super(context);
		mContext = context;
		
		this.setOrientation(LinearLayout.VERTICAL);
		
		//header
		headerRootLayout = new LinearLayout(context);
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
		headerRootLayout.setLayoutParams(params);
		headerRootLayout.setBackgroundColor(Color.GRAY);
		
		headerLeftLayout = new LinearLayout(context);
		headerLeftLayout.setOrientation(LinearLayout.VERTICAL);
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
		params.setMargins(20, 10, 0, 10);
		headerLeftLayout.setLayoutParams(params);
		headerRootLayout.addView(headerLeftLayout);
		
		headerRightLayout = new LinearLayout(context);
		headerRightLayout.setOrientation(LinearLayout.VERTICAL);
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
		params.setMargins(0, 10, 20, 10);
		headerRightLayout.setLayoutParams(params);
		headerRightLayout.setGravity(Gravity.RIGHT);
		headerRootLayout.addView(headerRightLayout);
		
		
		headerNameTv = new TextView(context);
//		headerNameTv.setText("headerNameTv");
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
		headerLeftLayout.addView(headerNameTv, params);
		
		headerDateTv = new TextView(context);
//		headerDateTv.setText("headerDateTv");
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
		headerLeftLayout.addView(headerDateTv, params);
		
		headerCalorieTv = new TextView(context);
//		headerCalorieTv.setText("headerCalorieTv");
		headerCalorieTv.setLayoutParams(params);
		headerCalorieTv.setGravity(Gravity.RIGHT);
		headerRightLayout.addView(headerCalorieTv);
		
		
		this.addView(headerRootLayout);
		
		//body
		bodyLayout = new LinearLayout(context);
		bodyLayout.setOrientation(LinearLayout.HORIZONTAL);
		params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 2.0f);
		params.setMargins(20, 10, 20, 10);
		bodyLayout.setLayoutParams(params);
		
		bodyLayout.setVisibility(LinearLayout.GONE);
		this.addView(bodyLayout);
		
		bodyCalorieTv = new TextView(context); bodyCalorieNumberTv = new TextView(context);
		bodyFatTv = new TextView(context); bodyFatNumberTv = new TextView(context);
		bodyCarbohydrateTv = new TextView(context); bodyCarbohydrateNumberTv = new TextView(context);
		bodyProteinTv = new TextView(context); bodyProteinNumberTv = new TextView(context);
		
		LinearLayout bodyLeft = new LinearLayout(context);
		bodyLeft.setOrientation(LinearLayout.VERTICAL);
		bodyLeft.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
		
		LinearLayout bodyRight = new LinearLayout(context);
		bodyRight.setOrientation(LinearLayout.VERTICAL);
		bodyRight.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
		bodyRight.setGravity(Gravity.RIGHT);
		
		bodyLeft.addView(bodyCalorieTv); bodyRight.addView(bodyCalorieNumberTv);
		bodyLeft.addView(bodyFatTv); bodyRight.addView(bodyFatNumberTv);
		bodyLeft.addView(bodyCarbohydrateTv); bodyRight.addView(bodyCarbohydrateNumberTv);
		bodyLeft.addView(bodyProteinTv); bodyRight.addView(bodyProteinNumberTv);
		
		bodyCalorieTv.setText("Calorie:"); //bodyCalorieNumberTv.setText("1");
		bodyFatTv.setText("Fat:"); //bodyFatNumberTv.setText("2");
		bodyCarbohydrateTv.setText("Carbohydrate:"); //bodyCarbohydrateNumberTv.setText("3");
		bodyProteinTv.setText("Protein"); //bodyProteinNumberTv.setText("4");
		
		bodyLayout.addView(bodyLeft);
		bodyLayout.addView(bodyRight);
		
		//onclicklisteners
		headerRootLayout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (bodyLayout.getVisibility() == LinearLayout.GONE) {
					
					Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_down);
					animation.setStartOffset(0);
					animation.setFillAfter(true);
					bodyLayout.startAnimation(animation);
//					bodyLayout.setVisibility(LinearLayout.VISIBLE);
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
					    @Override
					    public void run() {
					        // Set visibility after animation
					    	bodyLayout.setVisibility(LinearLayout.VISIBLE);
					    }
					}, animation.getDuration());
					
				} else {
					
					Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_up);
					animation.setStartOffset(0);
					animation.setFillAfter(true);
					bodyLayout.startAnimation(animation);
//					bodyLayout.setVisibility(LinearLayout.GONE);
					final Handler handler = new Handler();
					handler.postDelayed(new Runnable() {
					    @Override
					    public void run() {
					        // Set visibility after animation
					    	bodyLayout.setVisibility(LinearLayout.GONE);
					    }
					}, animation.getDuration());
					
				}
			}
		});
		
		
	}

	public void setTexts(Meal m){
		if (m.getName() == null || "".equalsIgnoreCase(m.getName()) || " ".equalsIgnoreCase(m.getName())) {
			headerNameTv.setText("n/a");
		} else {
			headerNameTv.setText(m.getName());
		}
		
		if (m.getDate() == null) {
			headerDateTv.setText("n/a");
		} else {
			headerDateTv.setText(Session.sdf.format(m.getDate()));
		}
		
		headerCalorieTv.setText(m.getEnergySum()+" cal");
		
		bodyCalorieNumberTv.setText(m.getEnergySum() + " cal");
		bodyFatNumberTv.setText(m.getFatSum() + " g");
		bodyCarbohydrateNumberTv.setText(m.getCarbohidrateSum() + "g");
		bodyProteinNumberTv.setText(m.getProteinSum() + " g");
	}
}
