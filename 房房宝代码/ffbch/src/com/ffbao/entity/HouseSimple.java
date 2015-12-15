package com.ffbao.entity;
/**
 * 
 * @FileName:SimpleHouse.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SimpleHouse.java
 * @author lee
 * @create Date2014-11-5
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: userGetBuildingInfoList interface
 */
public class HouseSimple {

	//	private String buildID;/*当前楼盘的ID*/
	//	private String buildName;/*楼盘姓名*/
	//	private String priece;/*价钱*/
	//	private String isNew; /*是否最新*/
	//	private String buildActivity;/*楼盘活动*/
	//	private String commission;/*佣金*/
	//	private String agents;/*合作经纪人*/
	//	private String customers;/*意向客户*/
	//	private String floorItemImg;/*获取楼盘图片*/

	private String buildingid;//楼盘id
	private String fullname;//楼盘名称	
	private String avgPrice;//楼盘均价
	private String building_placeid;//地区id
	private String level;//楼盘地点level
	private String building_place;//楼盘地点
	private String user_count;//合作经纪人总数
	private String customer_count;//意向客户总数
	public String getTitile() {
		return titile;
	}
	public void setTitile(String titile) {
		this.titile = titile;
	}
	private String agent_brokerage_rate;//佣金点
	private String personrebate;//独立经纪人返点
	private String building_photo;//楼盘图片
	private String city;//所属城市
	private String district;//所属地区
	private String titile;//xx元起
	public String getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(String buildingid) {
		this.buildingid = buildingid;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}
	public String getBuilding_placeid() {
		return building_placeid;
	}
	public void setBuilding_placeid(String building_placeid) {
		this.building_placeid = building_placeid;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBuilding_place() {
		return building_place;
	}
	public void setBuilding_place(String building_place) {
		this.building_place = building_place;
	}
	public String getUser_count() {
		return user_count;
	}
	public void setUser_count(String user_count) {
		this.user_count = user_count;
	}
	public String getCustomer_count() {
		return customer_count;
	}
	public void setCustomer_count(String customer_count) {
		this.customer_count = customer_count;
	}
	public String getAgent_brokerage_rate() {
		return agent_brokerage_rate;
	}
	public void setAgent_brokerage_rate(String agent_brokerage_rate) {
		this.agent_brokerage_rate = agent_brokerage_rate;
	}
	public String getPersonrebate() {
		return personrebate;
	}
	public void setPersonrebate(String personrebate) {
		this.personrebate = personrebate;
	}
	public String getBuilding_photo() {
		return building_photo;
	}
	public void setBuilding_photo(String building_photo) {
		this.building_photo = building_photo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}




}
