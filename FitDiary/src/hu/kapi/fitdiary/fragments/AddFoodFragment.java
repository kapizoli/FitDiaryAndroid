package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;

public class AddFoodFragment extends SherlockFragment implements DatePickerCommunicator {
	Button addButton;
	EditText name, date, time;
	LinearLayout containerLayot;
		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_add_meal, container, false);
		
		addButton = (Button) inflater.inflate(R.id.add_meal_fragment_add_button, container);
		name = (EditText) inflater.inflate(R.id.add_meal_fragment_meal_name, container);
		date = (EditText) inflater.inflate(R.id.add_meal_fragment_meal_date, container);
		time = (EditText) inflater.inflate(R.id.add_meal_fragment_meal_time, container);
		containerLayot = (LinearLayout) inflater.inflate(R.id.add_meal_fragment_food_container_view, container);
		
		return rootView;
	}

	@Override
	public void onDatePicked(String date) {
		// TODO Auto-generated method stub
		
	}
	
}
