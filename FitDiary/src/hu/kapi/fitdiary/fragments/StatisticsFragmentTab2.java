package hu.kapi.fitdiary.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.R.layout;

public class StatisticsFragmentTab2 extends SherlockFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab2.xml
		View view = inflater.inflate(R.layout.statisticsfragmenttab2, container, false);
		return view;
	}
}
