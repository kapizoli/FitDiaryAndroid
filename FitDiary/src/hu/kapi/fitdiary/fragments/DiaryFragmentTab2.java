package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.util.Session;
import hu.kapi.fitdiary.widgets.DateSlider;
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
//		View view = inflater.inflate(R.layout.diaryfragmenttab3, container, false);
//
////		ds = (DateSlider) getView().findViewById(R.id.measurementDateSlider);
//		
//		return view;
		LinearLayout rootView = new LinearLayout(getActivity());
		ds = new DateSlider(getActivity());
		rootView.addView(ds);
        return rootView;
	}	
}
