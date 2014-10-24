package hu.kapi.fitdiary.activites;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.adapters.MenuListAdapter;
import hu.kapi.fitdiary.fragments.BuyTrainingPlanFragment;
import hu.kapi.fitdiary.fragments.DiaryFragment;
import hu.kapi.fitdiary.fragments.InfosFragment;
import hu.kapi.fitdiary.fragments.ProfileFragment;
import hu.kapi.fitdiary.fragments.RecipesFragment;
import hu.kapi.fitdiary.fragments.RemindersFragment;
import hu.kapi.fitdiary.fragments.StatisticsFragment;
import hu.kapi.fitdiary.fragments.TipsFragment;
import hu.kapi.fitdiary.fragments.TrainingPlanFragment;
import hu.kapi.fitdiary.util.Session;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

public class SecondActivity extends SherlockFragmentActivity {

	// Declare Variables
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	Fragment diaryFragment = new DiaryFragment(this);
	Fragment statistcsFragment = new StatisticsFragment(this);
	Fragment trainingPlanFragment = new TrainingPlanFragment();
	Fragment buyTrainingPlanFragment = new BuyTrainingPlanFragment();
	Fragment profileFragment = new ProfileFragment();
	Fragment remindersFragment = new RemindersFragment();
	Fragment infosFragment = new InfosFragment();
	Fragment recipesFragment = new RecipesFragment();
	Fragment tipsFragment = new TipsFragment();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	String TAG;
	String actualTitle;
	MenuItem addItem/* , graphItem, tableItem */;

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from drawer_main.xml
		setContentView(R.layout.drawer_main);

		Session.getInstance().actualFragment = diaryFragment;

		Resources res = getResources();
		TAG = this.getClass().getName();

		// Get the Title
		mTitle = mDrawerTitle = getTitle();

		// Generate title
		title = res.getStringArray(R.array.list_items);
		actualTitle = title[0];

		// Generate subtitle
		subtitle = res.getStringArray(R.array.list_subtitles);

		// Generate icon
		icon = new int[] { R.drawable.action_about, R.drawable.action_settings,
				R.drawable.action_about, R.drawable.action_settings,
				R.drawable.action_about, R.drawable.action_settings,
				R.drawable.action_about, R.drawable.action_settings,
				R.drawable.action_about, R.drawable.action_settings };

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.listview_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass string arrays to MenuListAdapter
		mMenuAdapter = new MenuListAdapter(SecondActivity.this, title,
				subtitle, icon);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture listview menu item click
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
				setTitle(actualTitle);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				// Set the title on the action when drawer open
				getSupportActionBar().setTitle(mDrawerTitle);
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			Intent i = new Intent(SecondActivity.this, AddActivity.class);
			if (diaryFragment == Session.getInstance().actualFragment) {
				int currentPosition = ((DiaryFragment) diaryFragment).mViewPager
						.getCurrentItem();
				Fragment currentFragment = ((FragmentPagerAdapter) ((DiaryFragment) diaryFragment).mViewPager
						.getAdapter()).getItem(currentPosition);
				Log.d("addItemSelected", currentFragment.getClass().getName());
				i.putExtra("type", currentFragment.getClass().getName());
			startActivity(i);
			} else if (recipesFragment == Session.getInstance().actualFragment) {
				Log.d("addItemSelected", Session.getInstance().actualFragment
						.getClass().getName());
				i.putExtra("type", Session.getInstance().actualFragment
						.getClass().getName());
			startActivity(i);
			} else {
				// nem kellene ide futnia
				Log.d("addItemSelected", Session.getInstance().actualFragment
						.getClass().getName());
			}
			break;
		case android.R.id.home:
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	// ListView click listener in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Locate Position
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, diaryFragment);
			Session.getInstance().actualFragment = diaryFragment;
			if (addItem != null) {
				addItem.setVisible(true);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Bevitel selected");
			break;
		case 1:
			ft.replace(R.id.content_frame, statistcsFragment);
			Session.getInstance().actualFragment = statistcsFragment;
			if (addItem != null) {
				addItem.setVisible(false);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(true);
			// }
			Log.d(TAG, "Statisztika selected");
			break;
		case 2:
			ft.replace(R.id.content_frame, trainingPlanFragment);
			Session.getInstance().actualFragment = trainingPlanFragment;
			if (addItem != null) {
				addItem.setVisible(false);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Edz�sterv selected");
			break;
		case 3:
			ft.replace(R.id.content_frame, buyTrainingPlanFragment);
			Session.getInstance().actualFragment = buyTrainingPlanFragment;
			if (addItem != null) {
				addItem.setVisible(false);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Edz�sterv v�s�rl�s selected");
			break;
		case 4:
			ft.replace(R.id.content_frame, profileFragment);
			Session.getInstance().actualFragment = profileFragment;
			if (addItem != null) {
				addItem.setVisible(false);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Profil selected");
			break;
		case 5:
			ft.replace(R.id.content_frame, remindersFragment);
			Session.getInstance().actualFragment = remindersFragment;
			if (addItem != null) {
				addItem.setVisible(true);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Eml�keztet�k selected");
			break;
		case 6:
			ft.replace(R.id.content_frame, infosFragment);
			Session.getInstance().actualFragment = infosFragment;
			if (addItem != null) {
				addItem.setVisible(false);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Inf�k selected");
			break;
		case 7:
			ft.replace(R.id.content_frame, recipesFragment);
			Session.getInstance().actualFragment = recipesFragment;
			if (addItem != null) {
				addItem.setVisible(true);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Receptek selected");
			break;
		case 8:
			ft.replace(R.id.content_frame, tipsFragment);
			Session.getInstance().actualFragment = tipsFragment;
			if (addItem != null) {
				addItem.setVisible(false);
			}
			// if (graphItem != null) {
			// graphItem.setVisible(false);
			// }
			// if (tableItem != null) {
			// tableItem.setVisible(false);
			// }
			Log.d(TAG, "Tippek selected");
			break;
		case 9:
			Intent i = new Intent(SecondActivity.this, MainActivity.class);
			// TODO: session nullázása
			startActivity(i);
			Log.d(TAG, "Kil�p�s selected");
			break;
		default:
			Log.d(TAG, "Not gonna happend :D");
			break;
		}
		ft.commit();
		mDrawerList.setItemChecked(position, true);
		// Get the title followed by the position
		setTitle(title[position]);
		actualTitle = title[position];
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}

	@Override
	public void onBackPressed() {

		FragmentManager manager = getSupportFragmentManager();
		if (manager.getBackStackEntryCount() > 0) {
			// If there are back-stack entries, leave the FragmentActivity
			// implementation take care of them.
			manager.popBackStack();

		} else {
			// Otherwise, ask user if he wants to leave :)
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.activity_main,
				(com.actionbarsherlock.view.Menu) menu);
		this.addItem = menu.findItem(R.id.action_add);
		// this.graphItem = menu.findItem(R.id.action_graph);
		// graphItem.setVisible(false);
		// this.tableItem = menu.findItem(R.id.action_table);
		// tableItem.setVisible(false);
		return super.onCreateOptionsMenu(menu);
	}

}
