package hu.kapi.fitdiary.widgets;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.R.string;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class CalorieWidget extends LinearLayout {
	LinearLayout calorie, fat, carbohidrate, protein;
	TextView calorieNameTv, calorieSumTv, calorieDiffTv;
	double calorieSum=0.0, calorieDiff=0.0;
	TextView fatNameTv, fatSumTv, fatDiffTv;
	double fatSum=0.0, fatDiff=0.0;
	TextView carbohidrateNameTv, carbohidrateSumTv, carbohidrateDiffTv;
	double carbohidrateSum=0.0, carbohidrateDiff=0.0;
	TextView proteinNameTv, proteinSumTv, proteinDiffTv;
	double proteinSum=0.0, proteinDiff=0.0;
	Resources res;
	LayoutParams param;
	
	public CalorieWidget(Context context) {
		super(context);

		res = context.getResources();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		int width = display.getWidth();
		int height = display.getHeight();
		
		this.setOrientation(LinearLayout.HORIZONTAL);
		this.setBackgroundColor(Color.WHITE);
		this.setGravity(Gravity.CENTER);
		this.setWeightSum(4.0f);
		this.setLayoutParams(new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,(int) (height*0.15)));
		param = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
		
		calorie = new LinearLayout(context);
		calorie.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		calorie.setOrientation(LinearLayout.VERTICAL);
		calorie.setWeightSum(3.0f);
		calorieNameTv = new TextView(context);
		calorieNameTv.setText(res.getString(R.string.calorie));
		calorieNameTv.setGravity(Gravity.CENTER);
		calorie.addView(calorieNameTv, param);
		calorieSumTv = new TextView(context);
		calorieSumTv.setText(String.valueOf(calorieSum));
		calorieSumTv.setGravity(Gravity.CENTER);
		calorieSumTv.setTextSize(calorieSumTv.getTextSize()+2);
		calorie.addView(calorieSumTv, param);
		calorieDiffTv = new TextView(context);
		calorieDiffTv.setText(String.valueOf(calorieDiff));
		calorieDiffTv.setGravity(Gravity.CENTER);
		calorieDiffTv.setTextSize(calorieDiffTv.getTextSize()-2);
		calorie.addView(calorieDiffTv, param);
		calorie.setGravity(Gravity.CENTER);
		this.addView(calorie, param);
		
		fat = new LinearLayout(context);
		fat.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		fat.setOrientation(LinearLayout.VERTICAL);
		fat.setWeightSum(3.0f);
		fatNameTv = new TextView(context);
		fatNameTv.setText(res.getString(R.string.fat));
		fatNameTv.setGravity(Gravity.CENTER);
		fat.addView(fatNameTv, param);
		fatSumTv = new TextView(context);
		fatSumTv.setText(String.valueOf(fatSum));
		fatSumTv.setGravity(Gravity.CENTER);
		fatSumTv.setTextSize(fatSumTv.getTextSize()+2);
		fat.addView(fatSumTv, param);
		fatDiffTv = new TextView(context);
		fatDiffTv.setText(String.valueOf(fatDiff));
		fatDiffTv.setGravity(Gravity.CENTER);
		fatDiffTv.setTextSize(fatDiffTv.getTextSize()-2);
		fat.addView(fatDiffTv, param);
		fat.setGravity(Gravity.CENTER);
		this.addView(fat, param);
		
		carbohidrate = new LinearLayout(context);
		carbohidrate.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		carbohidrate.setOrientation(LinearLayout.VERTICAL);
		carbohidrate.setWeightSum(3.0f);
		carbohidrateNameTv = new TextView(context);
		carbohidrateNameTv.setText(res.getString(R.string.carbohidrate));
		carbohidrateNameTv.setGravity(Gravity.CENTER);
		carbohidrate.addView(carbohidrateNameTv, param);
		carbohidrateSumTv = new TextView(context);
		carbohidrateSumTv.setText(String.valueOf(carbohidrateSum));
		carbohidrateSumTv.setGravity(Gravity.CENTER);
		carbohidrateSumTv.setTextSize(carbohidrateSumTv.getTextSize()+2);
		carbohidrate.addView(carbohidrateSumTv, param);
		carbohidrateDiffTv = new TextView(context);
		carbohidrateDiffTv.setText(String.valueOf(carbohidrateDiff));
		carbohidrateDiffTv.setGravity(Gravity.CENTER);
		carbohidrateDiffTv.setTextSize(carbohidrateDiffTv.getTextSize()-2);
		carbohidrate.addView(carbohidrateDiffTv, param);
		carbohidrate.setGravity(Gravity.CENTER);
		this.addView(carbohidrate, param);
		
		protein = new LinearLayout(context);
		protein.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		protein.setOrientation(LinearLayout.VERTICAL);
		protein.setWeightSum(3.0f);
		proteinNameTv = new TextView(context);
		proteinNameTv.setText(res.getString(R.string.protein));
		proteinNameTv.setGravity(Gravity.CENTER);
		protein.addView(proteinNameTv, param);
		proteinSumTv = new TextView(context);
		proteinSumTv.setText(String.valueOf(proteinSum));
		proteinSumTv.setGravity(Gravity.CENTER);
		proteinSumTv.setTextSize(proteinSumTv.getTextSize()+2);
		protein.addView(proteinSumTv, param);
		proteinDiffTv = new TextView(context);
		proteinDiffTv.setText(String.valueOf(proteinDiff));
		proteinDiffTv.setGravity(Gravity.CENTER);
		proteinDiffTv.setTextSize(proteinDiffTv.getTextSize()-2);
		protein.addView(proteinDiffTv, param);
		protein.setGravity(Gravity.CENTER);
		this.addView(protein, param);
		
	}

	public double getCalorieSum() {
		return calorieSum;
	}

	public void setCalorieSum(double calorieSum) {
		this.calorieSum = calorieSum;
		this.calorieSumTv.setText(String.valueOf(calorieSum));
	}

	public double getCalorieDiff() {
		return calorieDiff;
	}

	public void setCalorieDiff(double calorieDiff) {
		this.calorieDiff = calorieDiff;
		this.calorieDiffTv.setText(String.valueOf(calorieDiff));
	}

	public double getFatSum() {
		return fatSum;
	}

	public void setFatSum(double fatSum) {
		this.fatSum = fatSum;
		this.fatSumTv.setText(String.valueOf(fatSum));
	}

	public double getFatDiff() {
		return fatDiff;
	}

	public void setFatDiff(double fatDiff) {
		this.fatDiff = fatDiff;
		this.fatDiffTv.setText(String.valueOf(fatDiff));
	}

	public double getCarbohidrateSum() {
		return carbohidrateSum;
	}

	public void setCarbohidrateSum(double carbohidrateSum) {
		this.carbohidrateSum = carbohidrateSum;
		this.carbohidrateSumTv.setText(String.valueOf(carbohidrateSum));
	}

	public double getCarbohidrateDiff() {
		return carbohidrateDiff;
	}

	public void setCarbohidrateDiff(double carbohidrateDiff) {
		this.carbohidrateDiff = carbohidrateDiff;
		this.carbohidrateDiffTv.setText(String.valueOf(carbohidrateDiff));
	}

	public double getProteinSum() {
		return proteinSum;
	}

	public void setProteinSum(double proteinSum) {
		this.proteinSum = proteinSum;
		this.proteinSumTv.setText(String.valueOf(proteinSum));
	}

	public double getProteinDiff() {
		return proteinDiff;
	}

	public void setProteinDiff(double proteinDiff) {
		this.proteinDiff = proteinDiff;
		this.proteinDiffTv.setText(String.valueOf(proteinDiff));
	}

	public void setCalorieNameTvColor(int color){
		calorieNameTv.setTextColor(color);
	}
	
	public void setCalorieSumTvColor(int color){
		calorieSumTv.setTextColor(color);
	}
	
	public void setCalorieDiffTvColor(int color){
		calorieDiffTv.setTextColor(color);
	}

	public void setFatNameTvColor(int color){
		fatNameTv.setTextColor(color);
	}

	public void setFatSumTvColor(int color){
		fatSumTv.setTextColor(color);
	}

	public void setFatDiffTvColor(int color){
		fatDiffTv.setTextColor(color);
	}

	public void setCarbohidrateNameTvColor(int color){
		carbohidrateNameTv.setTextColor(color);
	}

	public void setarbohidrateSumTvColor(int color){
		carbohidrateSumTv.setTextColor(color);
	}

	public void setCarbohidrateDiffTvColor(int color){
		carbohidrateDiffTv.setTextColor(color);
	}

	public void setProteinNameTvColor(int color){
		proteinNameTv.setTextColor(color);
	}

	public void setProteinSumTvColor(int color){
		proteinSumTv.setTextColor(color);
	}

	public void setProteinDiffTvColor(int color){
		proteinDiffTv.setTextColor(color);
	}
	
}
