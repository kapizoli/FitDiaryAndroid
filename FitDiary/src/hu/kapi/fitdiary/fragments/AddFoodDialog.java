package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.communicators.FoodCommunicator;
import hu.kapi.fitdiary.model.Food;
import hu.kapi.fitdiary.util.Session;
import hu.kapi.fitdiary.widgets.foodItem.FoodItem;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AddFoodDialog extends DialogFragment{

	FoodCommunicator communicator;
	Button saveButton, cancelButton, searchButton;
	EditText nameET, unitET, quantityET, fatET, sugarET, calorieET, carbohidrateET, proteinET, dailyCategoryET, resourceCategoryET;
	int	pos = -1;
	Food mFood;
	
	public AddFoodDialog(){
		mFood = null;
	}
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (FoodCommunicator) activity;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food_dialog, container);
        
        saveButton = (Button) view.findViewById(R.id.add_food_dialog_save_button);
        cancelButton = (Button) view.findViewById(R.id.add_food_dialog_cancel_button);
        searchButton = (Button) view.findViewById(R.id.add_food_dialog_search_button);
        nameET = (EditText) view.findViewById(R.id.add_food_dialog_name_et);
        //TODO: beállítani egy array listenert a név mezőre és ha kiválasztja akkor töltse be az összes adatát!!!
        
        unitET = (EditText) view.findViewById(R.id.add_food_dialog_unit_et);
        quantityET = (EditText) view.findViewById(R.id.add_food_dialog_quantity_et);
        fatET = (EditText) view.findViewById(R.id.add_food_dialog_fat_et);
        sugarET = (EditText) view.findViewById(R.id.add_food_dialog_sugar_et);
        calorieET = (EditText) view.findViewById(R.id.add_food_dialog_calorie_et);
        carbohidrateET = (EditText) view.findViewById(R.id.add_food_dialog_carbohidrate_et);
        proteinET = (EditText) view.findViewById(R.id.add_food_dialog_protein_et);
        dailyCategoryET = (EditText) view.findViewById(R.id.add_food_dialog_daily_category_et);
        resourceCategoryET = (EditText) view.findViewById(R.id.add_food_dialog_resource_category_et);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (mFood != null){
        	if (mFood.getName() == null || "".equalsIgnoreCase(mFood.getName()) || " ".equalsIgnoreCase(mFood.getName())) {
    			nameET.setText("n/a");
    		} else {
    			nameET.setText(mFood.getName());
    		}
    		
            unitET.setText(Session.getInstance().getUnitByInt(mFood.getUnit()));
            quantityET.setText(Double.toString(mFood.getQuantity()));
            fatET.setText(Double.toString(mFood.getFat()));
            sugarET.setText(Double.toString(mFood.getSugar()));
            calorieET.setText(Double.toString(mFood.getEnergy()));
            carbohidrateET.setText(Double.toString(mFood.getCarbohidrate()));
            proteinET.setText(Double.toString(mFood.getProtein()));
            dailyCategoryET.setText(Integer.toString(mFood.getDaily_category()));
            resourceCategoryET.setText(Integer.toString(mFood.getResource_category()));
        }
        
        //onClickListeners
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO: create save method
				Log.d("pos", pos+"");
				setMFoodByForm();
				communicator.onFoodSetted(mFood, pos);
				getDialog().dismiss();
			}
		});
        
        cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
		});
        
        searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO: alertben keresési lehetőség és beállítani az adatokat ebben a dialogban!!!
			}
		});
        
        return view;
    }
	
	public void setAllData(FoodItem fi, int position){
		pos = position;
		Log.d("pos in setter", pos+"");
		mFood = fi.mFood;
		
	}
	
	//TODO: megvizsgálni h a megadott adatok formailag helyesek-e
	public void setMFoodByForm() {
		//TODO:megfelelően van-e kitöltve
		//if (megfelelőenVanKitöltve){
			if (mFood == null){
				mFood = new Food();
			}
		//} else {
			//hibaüzenet
			
			//return
		//}
		if (nameET.getText() == null || "".equalsIgnoreCase(nameET.getText().toString()) || " ".equalsIgnoreCase(nameET.getText().toString())) {
			//??
		} else {
			mFood.setName(nameET.getText().toString());
		}
		
        mFood.setUnit(Session.getInstance().getIntByUnitString(unitET.getText().toString()));
        mFood.setQuantity(Double.parseDouble(quantityET.getText().toString()));
        mFood.setFat(Double.parseDouble(fatET.getText().toString()));
        mFood.setSugar(Double.parseDouble(sugarET.getText().toString()));
        mFood.setEnergy(Double.parseDouble(calorieET.getText().toString()));
        mFood.setCarbohidrate(Double.parseDouble(carbohidrateET.getText().toString()));
        mFood.setProtein(Double.parseDouble(proteinET.getText().toString()));
        mFood.setDaily_category(Integer.parseInt(dailyCategoryET.getText().toString()));
        mFood.setResource_category(Integer.parseInt(resourceCategoryET.getText().toString()));
	}
}
