package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.util.Session;
import hu.kapi.fitdiary.widgets.CalorieWidget;
import hu.kapi.fitdiary.widgets.DateSlider;
import hu.kapi.fitdiary.widgets.DiaryItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.actionbarsherlock.app.SherlockFragment;

public class DiaryFragmentTab2 extends SherlockFragment{
	DateSlider ds;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
//		View view = inflater.inflate(R.layout.diaryfragmenttab2, container, false);
		
//		ds = (DateSlider) getView().findViewById(R.id.mealDateSlider);
		LinearLayout rootView = new LinearLayout(getActivity());
		rootView.setOrientation(LinearLayout.VERTICAL);
		ds = new DateSlider(getActivity());
		rootView.addView(ds);
		
		CalorieWidget cw = new CalorieWidget(getActivity());
		rootView.addView(cw);
        
		ScrollView scroll = new ScrollView(getActivity());
		scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		rootView.addView(scroll);
		
		LinearLayout linearLayout = new LinearLayout(getActivity());
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		scroll.addView(linearLayout);
		
		//TODO:ezeket kell feltölteni a megfelelő adatokkal
		for (int i = 0; i < 10; i++) {
			DiaryItem item = new DiaryItem(getActivity());
			linearLayout.addView(item);
		}
		
		return rootView;
//		return view;
	}

}
