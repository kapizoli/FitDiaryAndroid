package hu.kapi.fitdiary.adapters;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.R.array;
import hu.kapi.fitdiary.fragments.StatisticsFragmentTab1;
import hu.kapi.fitdiary.fragments.StatisticsFragmentTab2;
import hu.kapi.fitdiary.util.Session;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class StatisticsViewPagerAdapter extends FragmentPagerAdapter {

	// Declare the number of ViewPager pages
	final int PAGE_COUNT = 2;
	Context mContext;
	private String titles[];// = new String[] { "Grafikon", "Táblázat" };

	public StatisticsViewPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		mContext = context;
		titles = mContext.getResources().getStringArray(R.array.statistics_items);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			StatisticsFragmentTab1 fragmenttab1 = new StatisticsFragmentTab1();
			Session.getInstance().actualFragment = fragmenttab1;
			return fragmenttab1;
		case 1:
			StatisticsFragmentTab2 fragmenttab2 = new StatisticsFragmentTab2();
			Session.getInstance().actualFragment = fragmenttab2;
			return fragmenttab2;
		}
		return null;
	}

	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

}