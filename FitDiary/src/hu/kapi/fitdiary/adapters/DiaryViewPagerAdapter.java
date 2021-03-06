package hu.kapi.fitdiary.adapters;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab0;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab1;
import hu.kapi.fitdiary.fragments.DiaryFragmentTab2;
import hu.kapi.fitdiary.util.Session;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DiaryViewPagerAdapter extends FragmentPagerAdapter  {
	
	// Declare the number of ViewPager pages
		final int PAGE_COUNT = 3;
		Context mContext;
		private String titles[];// = new String[] { "Grafikon", "Táblázat" };

		public DiaryViewPagerAdapter(FragmentManager fm, Context context) {
			super(fm);
			mContext = context;
			titles = mContext.getResources().getStringArray(R.array.input_items);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				DiaryFragmentTab0 fragmenttab1 = new DiaryFragmentTab0();
				return fragmenttab1;
			case 1:
				DiaryFragmentTab1 fragmenttab2 = new DiaryFragmentTab1();
				return fragmenttab2;
			case 2:
				DiaryFragmentTab2 fragmenttab3 = new DiaryFragmentTab2();
				return fragmenttab3;
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
