package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.R;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.actionbarsherlock.app.SherlockFragment;

import hu.kapi.fitdiary.util.Session;
import hu.kapi.fitdiary.widgets.CalorieWidget;
import hu.kapi.fitdiary.widgets.DateSlider;
import hu.kapi.fitdiary.widgets.mealItem.MealItem;

public class DiaryFragmentTab0 extends SherlockFragment{
	DateSlider ds;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
//		View view = inflater.inflate(R.layout.diaryfragmenttab1, container, false);
//		
////		ds = (DateSlider) getView().findViewById(R.id.trainingDateSlider);
//		return view;
		LinearLayout rootView = new LinearLayout(getActivity());
		rootView.setOrientation(LinearLayout.VERTICAL);
		ds = new DateSlider(getActivity());
		rootView.addView(ds);
		
		CalorieWidget cw = new CalorieWidget(getActivity());
		rootView.addView(cw);
		
		
		
        return rootView;
	}

}
