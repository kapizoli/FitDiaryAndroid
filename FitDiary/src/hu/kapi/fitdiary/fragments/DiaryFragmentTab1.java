package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.model.Meal;
import hu.kapi.fitdiary.util.InternetConnection;
import hu.kapi.fitdiary.util.NetThread;
import hu.kapi.fitdiary.util.Session;
import hu.kapi.fitdiary.widgets.CalorieWidget;
import hu.kapi.fitdiary.widgets.DateSlider;
import hu.kapi.fitdiary.widgets.mealItem.MealListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragment;

public class DiaryFragmentTab1 extends SherlockFragment {
	DateSlider ds;
	CalorieWidget cw;
	MealListView ml;
	Refresh refreshTask;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab1.xml
		// View view = inflater.inflate(R.layout.diaryfragmenttab2, container,
		// false);

		// ds = (DateSlider) getView().findViewById(R.id.mealDateSlider);
		LinearLayout rootView = new LinearLayout(getActivity());
		rootView.setOrientation(LinearLayout.VERTICAL);
		ds = new DateSlider(getActivity(), this);
		rootView.addView(ds);

		cw = new CalorieWidget(getActivity());
		rootView.addView(cw);

		ml = new MealListView(getActivity());
		Calendar cal = ds.getDateAsCalendar();
		refreshMealList(cal.getTime());
		rootView.addView(ml);
		// ScrollView scroll = new ScrollView(getActivity());
		// scroll.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
		// LayoutParams.MATCH_PARENT));
		// rootView.addView(scroll);
		//
		// LinearLayout linearLayout = new LinearLayout(getActivity());
		// linearLayout.setOrientation(LinearLayout.VERTICAL);
		// scroll.addView(linearLayout);
		//
		// //TODO:ezeket kell feltölteni a megfelelő adatokkal
		// for (int i = 0; i < 10; i++) {
		// MealItem item = new MealItem(getActivity());
		// linearLayout.addView(item);
		// }

		return rootView;
		// return view;
	}

	public void refreshMealList(final Date date) {

		if (refreshTask != null) {
			refreshTask.cancel(true);
		}
		refreshTask = new Refresh();
		if (InternetConnection.isOnline(getActivity()) && refreshTask.getStatus() == AsyncTask.Status.PENDING) {
            refreshTask.execute(new Date[] { date });
        }
		
	}
	
	
	private class Refresh extends AsyncTask<Date, Void, ArrayList<Meal>> {
		ArrayList<Meal> l;
		
        @Override
        protected ArrayList<Meal> doInBackground(Date... dates) {
            for (int i = 0; i < 1; i++) {
            	l = Session.getInstance().getActualUser().getMealListForDay(dates[0]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }
            return l;
        }

        @Override
        protected void onPostExecute(ArrayList<Meal> result) {
        	ml.setList(result);
        }

    }
}
