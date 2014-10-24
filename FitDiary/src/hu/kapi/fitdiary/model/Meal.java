package hu.kapi.fitdiary.model;

import java.util.ArrayList;
import java.util.Date;

public class Meal {
	int ID;
	String name;
	Date date;
	ArrayList<Food> foodList;

	public Meal(int iD, Date date, String name) {
		super();
		ID = iD;
		this.date = date;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addFoodToList(Food f) {
		if (this.foodList == null)
			this.foodList = new ArrayList<Food>();
		this.foodList.add(f);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Food> getFoodList() {
		return foodList;
	}

	public void setFoodList(ArrayList<Food> foodList) {
		this.foodList = foodList;
	}

	@Override
	public String toString() {
		return "Meal [ID=" + ID + ", date=" + date + ", foodList=" + foodList
				+ "]";
	}

	public String toString1() {
		return "Meal [ID=" + ID + ", date=" + date + "]";
	}

	public double getQuantitySum() {
		double sum = 0.0;
		if (this.foodList != null)
			for (Food food : this.foodList) {
				sum += food.getQuantity();
			}
		return sum;
	}

	public double getFatSum() {
		double sum = 0.0;
		if (this.foodList != null)
			for (Food food : this.foodList) {
				sum += food.getFat();
			}
		return sum;
	}

	public double getSugarSum() {
		double sum = 0.0;
		if (this.foodList != null)
			for (Food food : this.foodList) {
				sum += food.getSugar();
			}
		return sum;
	}

	public double getEnergySum() {
		double sum = 0.0;
		if (this.foodList != null)
			for (Food food : this.foodList) {
				sum += food.getEnergy();
			}
		return sum;
	}

	public double getCarbohidrateSum() {
		double sum = 0.0;
		if (this.foodList != null)
			for (Food food : this.foodList) {
				sum += food.getCarbohidrate();
			}
		return sum;
	}

	public double getProteinSum() {
		double sum = 0.0;
		if (this.foodList != null)
			for (Food food : this.foodList) {
				sum += food.getProtein();
			}
		return sum;
	}
}
