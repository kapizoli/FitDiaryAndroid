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
import android.app.Dialog;
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
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
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
	Fragment inputFragment = new DiaryFragment(this);
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

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from drawer_main.xml
		setContentView(R.layout.drawer_main);

		Session.getInstance().actualFragment = inputFragment;

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
				Log.d(TAG, "Drawer closed");
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
			addItemSelected();
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

	private void addItemSelected() {
		// TODO:az aktuális fragment alapján megmondani h mit csináljon ha
		// rányomunk
		if (inputFragment == Session.getInstance().actualFragment) {
			int currentPosition = ((DiaryFragment) inputFragment).mViewPager
					.getCurrentItem();
			Fragment currentFragment = ((FragmentPagerAdapter) ((DiaryFragment) inputFragment).mViewPager
					.getAdapter()).getItem(currentPosition);
			Log.d("addItemSelected", currentFragment.getClass().getName());
			showAddAlert(currentPosition);
		} else if (recipesFragment == Session.getInstance().actualFragment) {
			Log.d("addItemSelected", Session.getInstance().actualFragment
					.getClass().getName());
			showAddAlert(3);
		} else {
			// TODO: nem kellene ide futnia, de ha mégis akkor kezelni
			Log.d("addItemSelected", Session.getInstance().actualFragment
					.getClass().getName());
		}

	}

	public void showAddAlert(int position) {
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
		switch (position) {
		case 0:{//diaryFragment1 - training
			dialog.setContentView(R.layout.training_add_alert);
			
			Button searchButton = (Button) dialog.findViewById(R.id.training_search_button);
			searchButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//TODO: feldobni egy listát amiből választhat
					
				}
			});
			
			Button cancelButton = (Button) dialog.findViewById(R.id.training_cancel_button);
			cancelButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
			Button saveButton = (Button) dialog.findViewById(R.id.training_save_button);
			saveButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//TODO: adat küldsése a szerverre és mentés a sessionben
					
					dialog.dismiss();
				}
			});
			
			break;}
		case 1:{//diaryFragment2 - food
			dialog.setContentView(R.layout.food_add_alert);
			
			Button cancelButton = (Button) dialog.findViewById(R.id.food_cancel_button);
			cancelButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
			Button saveButton = (Button) dialog.findViewById(R.id.food_save_button);
			saveButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//TODO: adat küldsése a szerverre és mentés a sessionben
					
					dialog.dismiss();
				}
			});
			break;}
		case 2:{//diaryFragment3 - measurement
			dialog.setContentView(R.layout.measurement_add_alert);

			Button cancelButton = (Button) dialog.findViewById(R.id.measurement_cancel_button);
			cancelButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
			Button saveButton = (Button) dialog.findViewById(R.id.measurement_save_button);
			saveButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//TODO: adat küldsése a szerverre és mentés a sessionben
					
					dialog.dismiss();
				}
			});
			break;}
		case 3:{//recipesFragment
			dialog.setContentView(R.layout.recepie_add_alert);

			Button cancelButton = (Button) dialog.findViewById(R.id.recepie_cancel_button);
			cancelButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
			Button saveButton = (Button) dialog.findViewById(R.id.recepie_save_button);
			saveButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//TODO: adat küldsése a szerverre és mentés a sessionben
					
					dialog.dismiss();
				}
			});
			break;}
		default:
			break;
		}
		dialog.show();
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
			ft.replace(R.id.content_frame, inputFragment);
			Session.getInstance().actualFragment = inputFragment;
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
