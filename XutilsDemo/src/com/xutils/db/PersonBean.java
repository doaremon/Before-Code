package com.xutils.db;

import com.lidroid.xutils.db.annotation.Id;


public class PersonBean {
	@Id
	private int id;
	private String nameString;
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}



}
