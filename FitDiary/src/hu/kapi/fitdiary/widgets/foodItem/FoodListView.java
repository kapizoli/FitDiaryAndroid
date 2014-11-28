package hu.kapi.fitdiary.widgets.foodItem;

import hu.kapi.fitdiary.model.Food;

import java.util.ArrayList;

import android.content.Context;
import android.widget.LinearLayout;

public class FoodListView extends LinearLayout {
	Context mContext;
	public ArrayList<FoodItem> foodList;

	public FoodListView(Context context) {
		super(context);
		mContext = context;
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		this.setOrientation(LinearLayout.VERTICAL);
		foodList = new ArrayList<FoodItem>();
	}

	public void setList(ArrayList<Food> list) {
		FoodListView.this.removeAllViews();
		if (list == null || list.size() == 0) {
			foodList = new ArrayList<FoodItem>();
			// TODO: kiírni egy üzenetet h nincs adat!

		} else {
			for (int i = 0; i < list.size(); i++) {
				FoodItem item = new FoodItem(mContext);
				item.setTexts(list.get(i), foodList.size());
				foodList.add(item);
				FoodListView.this.addView(item);
			}
		}
	}

	public void addFoodItemToList(Food f) {
		if (f != null) {
			FoodItem item = new FoodItem(mContext);
			item.setTexts(f, foodList.size());
			foodList.add(item);
			FoodListView.this.addView(item);
		} else {
			// TODO: kiírni egy hibaüzenetet!
		}
	}

	public void modifyFoodItem(Food f, int pos) {
		if (f != null) {
			FoodItem item = new FoodItem(mContext);
			item.setTexts(f, pos);
			foodList.set(pos, item);
			FoodListView.this.removeViewAt(pos);
			FoodListView.this.addView(item, pos);
		} else {
			// TODO: kiírni egy hibaüzenetet!
		}
	}

}
