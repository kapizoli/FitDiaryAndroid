package hu.kapi.fitdiary.fragments;

import hu.kapi.fitdiary.R;
import hu.kapi.fitdiary.communicators.SetFoodCommunicator;
import hu.kapi.fitdiary.model.Food;
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

public class AddFoodDialog extends DialogFragment{

	SetFoodCommunicator communicator;
	Button saveButton, cancelButton;
	int	pos = -1;
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (SetFoodCommunicator) activity;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_food_dialog, container);
        
        saveButton = (Button) view.findViewById(R.id.add_food_dialog_save_button);
        cancelButton = (Button) view.findViewById(R.id.add_food_dialog_cancel_button);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        
        //onClickListeners
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//TODO: create save method
				Log.d("pos", pos+"");
				communicator.onFoodSetted(new Food("asd", 1, 1, 1.0, 1.0, 1.0, 1.0, 1.0, 1, 1), pos);
				getDialog().dismiss();
			}
		});
        
        cancelButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
		});
        return view;
    }
	
	public void setAllData(FoodItem fi, int position){
		pos = position;
		Log.d("pos in setter", pos+"");
		//TODO: set the data!
	}
}
