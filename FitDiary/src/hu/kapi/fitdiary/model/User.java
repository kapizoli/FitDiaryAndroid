package hu.kapi.fitdiary.model;

import hu.kapi.fitdiary.util.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import android.util.Log;

public class User {

	int id;
	String name;
	String password;
	String email;
	int sex;// 0-male, 1-female
	Date birthday;
	Date lastUpdated;

	ArrayList<Meal> mealList;

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", sex=" + sex + ", birthday="
				+ birthday + ", lastUpdated=" + lastUpdated + "]";
	}

	public User(int id, String name, String password, String email, int sex,
			Date birthday, Date lastUpdated) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.birthday = birthday;
		this.lastUpdated = lastUpdated;
	}

	public User(int id) {
		super();
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public int getId() {
		return id;
	}

	public ArrayList<Meal> getMealList() {
		return mealList;
	}

	public void setMealList(ArrayList<Meal> mealList) {
		this.mealList = mealList;
	}

	private void addMealToList(Meal m) {
		if (this.mealList == null) {
			this.mealList = new ArrayList<Meal>();
			this.mealList.add(m);
		}
	}

	public void addMeals(ArrayList<Meal> list) {
		//TODO: ha szerveren törlünk egy adatot az attól még a kliensen ott marad, ki kell keresni és törölni (v újra kell húzni a lokál db-t)
		if (User.this.mealList != null)
			for (Meal meal : list) {
				Boolean isMealContain = false;
				Meal tmp = null;
				for (Meal meal2 : User.this.mealList) {
					if (meal.ID == meal2.ID) {
						isMealContain = true;
						tmp = meal2;
						break;
					}
				}
				if (!isMealContain) {
					addMealToList(meal);
				} else {
					for (Food food : meal.foodList) {
						Boolean isFoodContain = false;
						for (Food food2 : tmp.foodList) {
							if (food.ID == food2.ID) {
								isFoodContain = true;
								break;
							}
						}
						if (!isFoodContain) {
							meal.foodList.add(food);
						}
					}
				}
			}
		else {
			setMealList(list);
		}
	}

	public ArrayList<Meal> getMealListForDays(Date begin, Date end) {
		Session.getInstance().getActualCommunication().getMealListForUser(begin, end);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(begin);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(end);
		Calendar cal3 = Calendar.getInstance();
		
		if (mealList != null) {
			ArrayList<Meal> list = new ArrayList<Meal>();
			for (Meal meal : mealList) {
				cal3.setTime(meal.date);
				if (cal1.get(Calendar.YEAR) <= cal3.get(Calendar.YEAR) &&
						cal1.get(Calendar.DAY_OF_YEAR) <= cal3.get(Calendar.DAY_OF_YEAR)
						&& cal2.get(Calendar.YEAR) >= cal3.get(Calendar.YEAR) &&
								cal2.get(Calendar.DAY_OF_YEAR) >= cal3.get(Calendar.DAY_OF_YEAR)) {
					list.add(meal);
				}
			}
			return list;
		} else {
			return new ArrayList<Meal>();
		}
	}

	public ArrayList<Meal> getMealListForDay(Date begin) {
		Calendar c = Calendar.getInstance();
		c.setTime(begin);
		c.add(Calendar.DATE, 1);
		Date end = c.getTime();
		Session.getInstance().getActualCommunication().getMealListForUser(begin, end);
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(begin);
		Calendar cal2 = Calendar.getInstance();

		if (mealList != null) {
			ArrayList<Meal> list = new ArrayList<Meal>();
			for (Meal meal : mealList) {
				cal2.setTime(meal.date);
				if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
						cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
					list.add(meal);
				}
			}
			return list;
		} else {
			return new ArrayList<Meal>();
		}

	}
}
