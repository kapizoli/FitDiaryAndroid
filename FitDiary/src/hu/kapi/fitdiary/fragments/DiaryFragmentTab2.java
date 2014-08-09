package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.CalorieWidget;
import hu.kapi.fitdiary.DateSlider;
import hu.kapi.fitdiary.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
        
		return rootView;
//		return view;
	}

}
