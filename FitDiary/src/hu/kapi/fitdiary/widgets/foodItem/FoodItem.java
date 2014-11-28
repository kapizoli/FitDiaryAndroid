package hu.kapi.fitdiary.widgets.foodItem;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.activites.AddActivity;
import hu.kapi.fitdiary.model.Food;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FoodItem extends LinearLayout {
	
	LinearLayout headerRootLayout, bodyLayout, headerLeftLayout, headerRightLayout;
	TextView headerNameTv,  headerCalorieTv;
	TextView bodyCalorieTv, bodyFatTv, bodyCarbohydrateTv, bodyProteinTv;
	TextView bodyCalorieNumberTv, bodyFatNumberTv, bodyCarbohydrateNumberTv, bodyProteinNumberTv;
	LayoutParams params;
	Context mContext;
	int	position;

	public FoodItem(Context context) {
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
		
		//TODO: longclick-re felugrik egy alert szerkesztés/törlés/mégsem lehetőséggel
		//szerkesztésre ugyanaz a fragmetdialog ugrik fel mint hozzáadáskor
		headerRootLayout.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
				builder.setItems(new CharSequence[]
			            {"Módosítás", "Törlés", "Mégsem"},
			            new DialogInterface.OnClickListener() {
			                public void onClick(DialogInterface dialog, int which) {
			                    // The 'which' argument contains the index position
			                    // of the selected item
			                    switch (which) {
			                        case 0:
			                        	((AddActivity)mContext).showFoodDialog(position);
			                            break;
			                        case 1:
			                            
			                            break;
			                        case 2:
			                            
			                            break;
			                        default: break;
			                    }
			                    dialog.cancel();
			                }
			            });
				
				AlertDialog alertDialog = builder.create();
				alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				alertDialog.show();
				return false;
			}
		});
	}

	public void setTexts(Food f, int p){
		position = p;
		if (f.getName() == null || "".equalsIgnoreCase(f.getName()) || " ".equalsIgnoreCase(f.getName())) {
			headerNameTv.setText("n/a");
		} else {
			headerNameTv.setText(f.getName());
		}
		
		headerCalorieTv.setText(f.getEnergy()+" cal");
		
		bodyCalorieNumberTv.setText(f.getEnergy() + " cal");
		bodyFatNumberTv.setText(f.getFat() + " g");
		bodyCarbohydrateNumberTv.setText(f.getCarbohidrate() + "g");
		bodyProteinNumberTv.setText(f.getProtein() + " g");
	}
}
