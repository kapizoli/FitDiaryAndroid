package hu.kapi.fitdiary.communicators;

import hu.kapi.fitdiary.model.Food;

public interface FoodCommunicator {
	/**
	 * @param food
	 * @param pos, set less than 0 if you want to create a new item
	 */
	public void onFoodSetted(Food food,int pos);
}
