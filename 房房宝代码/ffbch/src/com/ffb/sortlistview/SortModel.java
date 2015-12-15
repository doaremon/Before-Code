package com.ffb.sortlistview;

public class SortModel {
/**
 * provinceId":58,"provinceName":"北京市","cityId":61,"cityName":"北京市"
 */
	private String name;   //显示的数据 ，这个为cityName
	private String sortLetters;  //显示数据拼音的首字母
	private int id;//这个是cityId
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortLetters() {
		return sortLetters;
	}
	public void setSortLetters(String sortLetters) {
		this.sortLetters = sortLetters;
	}
}
