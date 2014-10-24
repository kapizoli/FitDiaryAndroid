package hu.kapi.fitdiary.widgets.mealItem;

import hu.kapi.fitdiary.model.Meal;

import java.util.ArrayList;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MealListView extends ScrollView {
Context mContext;
LinearLayout linearLayout;

	public MealListView(Context context) {
		super(context);
		mContext = context;
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		
		linearLayout = new LinearLayout(mContext);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		this.addView(linearLayout);
	}
	
	public void setList (ArrayList<Meal> list){
		for (int i = 0; i < list.size(); i++) {
			MealItem item = new MealItem(mContext);
			item.setTexts(list.get(i));
			linearLayout.addView(item);
		}
	}

}
