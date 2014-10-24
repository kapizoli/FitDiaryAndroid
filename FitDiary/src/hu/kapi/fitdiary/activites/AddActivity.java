package hu.kapi.fitdiary.activites;



import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.fragments.AddFoodFragment;
import hu.kapi.fitdiary.fragments.AddMeasurementFragment;
import hu.kapi.fitdiary.fragments.AddRecepieFragment;
import hu.kapi.fitdiary.fragments.AddTrainingFragment;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab0;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab1;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab2;
import hu.kapi.fitdiary.fragments.RecipesFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

public class AddActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("vissza");
		getSupportActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));

		String name = this.getIntent().getStringExtra("type");
		Fragment fragment = null;
		
		if (DiaryFragmentTab0.class.getName().equals(name)) {//diary tab0
			fragment = new AddTrainingFragment();
		} else if (DiaryFragmentTab1.class.getName().equals(name)) {//diary tab1
			fragment = new AddFoodFragment();
		} else if (DiaryFragmentTab2.class.getName().equals(name)) {//diary tab2
			fragment = new AddMeasurementFragment();
		} else if (RecipesFragment.class.getName().equals(name)) {//recepies
			fragment = new AddRecepieFragment();
		}
		
		if (fragment != null) {
	        FragmentManager fragmentManager = getSupportFragmentManager();
	        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
	        fragmentTransaction.add(R.id.activity_add_fragment_container,fragment);
	
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
}
