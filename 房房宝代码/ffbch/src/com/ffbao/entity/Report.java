package com.ffbao.entity;

/**
 * 
 * @FileName:Report.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:Report.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: get report list ，call usergetReportList
 */
public class Report {

	/**
	 * Report
	 * recorid:1,
	 * name:"吴大力",
	 * intention_building:"绿地国宝"
	 * ,phone:"13810488238",
	 * createdate:"2014-09-21 10:00:00"，
	 * state:"报备成功"
	 */
	
	
	private int recorid;
	private String name;
	private String intention_building;
	private String intention_city;
	private String phone;
	private String createdate;
	private String state;
	
	public String getIntention_city() {
		return intention_city;
	}
	public void setIntention_city(String intention_city) {
		this.intention_city = intention_city;
	}
	public int getRecorid() {
		return recorid;
	}
	public void setRecorid(int recorid) {
		this.recorid = recorid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntention_building() {
		return intention_building;
	}
	public void setIntention_building(String intention_building) {
		this.intention_building = intention_building;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
