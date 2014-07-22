package hu.kapi.fitdiary.fragments;

import com.actionbarsherlock.app.SherlockFragment;
import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.R.layout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class InputFragment extends SherlockFragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_input, container, false);
		return rootView;
	}
	
}
