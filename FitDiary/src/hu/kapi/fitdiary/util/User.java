package hu.kapi.fitdiary.util;

import java.util.Date;

public class User {
	
	int id;
	String name;
	String password;
	String email;
	int sex;//0-male, 1-female
	Date birthday;
	Date lastUpdated;

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

	
	
}
