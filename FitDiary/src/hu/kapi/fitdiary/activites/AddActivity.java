package hu.kapi.fitdiary.activites;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.communicators.DatePickerCommunicator;
import hu.kapi.fitdiary.communicators.SetFoodCommunicator;
import hu.kapi.fitdiary.communicators.TimePickerCommunicator;
import hu.kapi.fitdiary.fragments.AddFoodDialog;
import hu.kapi.fitdiary.fragments.AddFoodFragment;
import hu.kapi.fitdiary.fragments.AddMeasurementFragment;
import hu.kapi.fitdiary.fragments.AddRecepieFragment;
import hu.kapi.fitdiary.fragments.AddTrainingFragment;
import hu.kapi.fitdiary.fragments.DatePickerFragment;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab0;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab1;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab2;
import hu.kapi.fitdiary.fragments.RecipesFragment;
import hu.kapi.fitdiary.fragments.TimePickerFragment;
import hu.kapi.fitdiary.model.Food;
import hu.kapi.fitdiary.widgets.foodItem.FoodItem;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class AddActivity extends SherlockFragmentActivity implements
		DatePickerCommunicator, TimePickerCommunicator,SetFoodCommunicator {
	public Fragment fragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("vissza");
		getSupportActionBar().setIcon(
				new ColorDrawable(getResources().getColor(
						android.R.color.transparent)));

		String name = this.getIntent().getStringExtra("type");
		fragment = null;

		if (DiaryFragmentTab0.class.getName().equals(name)) {// diary tab0
			fragment = new AddTrainingFragment();
		} else if (DiaryFragmentTab1.class.getName().equals(name)) {// diary
																	// tab1
			fragment = new AddFoodFragment();
		} else if (DiaryFragmentTab2.class.getName().equals(name)) {// diary
																	// tab2
			fragment = new AddMeasurementFragment();
		} else if (RecipesFragment.class.getName().equals(name)) {// recepies
			fragment = new AddRecepieFragment();
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.add(R.id.activity_add_fragment_container,
					fragment);

			fragmentTransaction.commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onDatePicked(String date) {
		if (AddActivity.this.fragment instanceof AddTrainingFragment) {// diary
																		// tab0

		} else if (AddActivity.this.fragment instanceof AddFoodFragment) {// diary
																			// tab1
			((AddFoodFragment) AddActivity.this.fragment).setDateByString(date);
		} else if (AddActivity.this.fragment instanceof AddMeasurementFragment) {// diary
																					// tab2

		} else if (AddActivity.this.fragment instanceof AddRecepieFragment) {// recepies

		}
	}

	@Override
	public void onTimePicked(String time) {
		if (AddActivity.this.fragment instanceof AddTrainingFragment) {// diary
																		// tab0

		} else if (AddActivity.this.fragment instanceof AddFoodFragment) {// diary
																			// tab1
			((AddFoodFragment) AddActivity.this.fragment).setTimeByString(time);
		} else if (AddActivity.this.fragment instanceof AddMeasurementFragment) {// diary
																					// tab2

		} else if (AddActivity.this.fragment instanceof AddRecepieFragment) {// recepies

		}
	}

	public void showDatePicker() {
		DialogFragment datepicker = new DatePickerFragment();
		datepicker.show(getSupportFragmentManager(), "datePicker");
	}

	public void showTimePicker() {
		DialogFragment timepicker = new TimePickerFragment();
		timepicker.show(getSupportFragmentManager(), "timePicker");

	}

	/** set f to less than 0 if you want to create an empty dialog
	 * @param f
	 */
	public void showFoodDialog(int f) {
		if (AddActivity.this.fragment instanceof AddFoodFragment) {
			
//			ArrayList<Food> foodList = new ArrayList<Food>();
//			for (int i = 0; i < 10; i++) {
//				foodList.add(new Food(i, "kaja "+i, 1, 1, 10.0, 10.0, 10.0, 10.0, 10.0, 1, 1));
//			}
//			((AddFoodFragment) AddActivity.this.fragment).fl.setList(foodList);
			
			if (f < 0) {
				AddFoodDialog foodDialog = new AddFoodDialog();
				foodDialog.show(getSupportFragmentManager(), "foodDialog");
			} else {
				AddFoodDialog foodDialog = new AddFoodDialog();
				
				FoodItem foodItem = ((AddFoodFragment) AddActivity.this.fragment).fl.foodList.get(f);
				foodDialog.setAllData(foodItem, f);
				foodDialog.show(getSupportFragmentManager(), "foodDialog");
			}
			
			
		}
	}


	@Override
	public void onFoodSetted(Food food, int pos) {
		if (pos < 0) {
			((AddFoodFragment) AddActivity.this.fragment).fl.addFoodItemToList(food);
		} else {
			((AddFoodFragment) AddActivity.this.fragment).fl.modifyFoodItem(food, pos);
		}
		
	}
}
