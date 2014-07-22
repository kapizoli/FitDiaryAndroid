package hu.kapi.fitdiary;

import hu.kapi.fitdiary.fragments.StatisticsFragmentTab1;
import hu.kapi.fitdiary.fragments.StatisticsFragmentTab2;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	// Declare the number of ViewPager pages
	final int PAGE_COUNT = 2;
	private String titles[] = new String[] { "Tab1", "Tab2" };

	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {

			// Open FragmentTab1.java
		case 0:
			StatisticsFragmentTab1 fragmenttab1 = new StatisticsFragmentTab1();
			return fragmenttab1;

			// Open FragmentTab2.java
		case 1:
			StatisticsFragmentTab2 fragmenttab2 = new StatisticsFragmentTab2();
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