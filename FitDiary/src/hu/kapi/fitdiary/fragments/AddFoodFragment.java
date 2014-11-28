package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.activites.AddActivity;
import hu.kapi.fitdiary.widgets.foodItem.FoodListView;

import java.util.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;

public class AddFoodFragment extends SherlockFragment {
	Button addButton;
	EditText name, date, time;
	LinearLayout containerLayot;
	public FoodListView fl;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_add_meal, container,
				false);

		addButton = (Button) rootView
				.findViewById(R.id.add_meal_fragment_add_button);
		addButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((AddActivity)getActivity()).showFoodDialog(-1);
				
			}
		});
		
		name = (EditText) rootView
				.findViewById(R.id.add_meal_fragment_meal_name);
		date = (EditText) rootView
				.findViewById(R.id.add_meal_fragment_meal_date);
		Calendar c = Calendar.getInstance();
		date.setText(String.format("%04d-%02d-%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH)+1, c.get(Calendar.DAY_OF_MONTH)));
		date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((AddActivity)getActivity()).showDatePicker();
				
			}
		});
		
		time = (EditText) rootView
				.findViewById(R.id.add_meal_fragment_meal_time);
        time.setText(String.format("%02d:%02d", c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)));
        time.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((AddActivity)getActivity()).showTimePicker();
				
			}
		});
		containerLayot = (LinearLayout) rootView
				.findViewById(R.id.add_meal_fragment_food_container_view);
		fl = new FoodListView(getActivity());
		containerLayot.addView(fl);
		return rootView;
	}

	public String getDateAsString() {
		return date.getText().toString();
	}

	public void setDateByString(String date) {
		this.date.setText(date);
	}

	public String getTimeAsString() {
		return time.getText().toString();
	}

	public void setTimeByString(String time) {
		this.time.setText(time);
	}
}
