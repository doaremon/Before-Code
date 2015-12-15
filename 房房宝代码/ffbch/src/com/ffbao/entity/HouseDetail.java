package com.ffbao.entity;

import java.util.ArrayList;
import java.util.List;

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
public class HouseDetail {

	// simpleHouse
	// private HouseSimple simpleHouse;
	/**
	 * "buildingid": 80, "customer_count": "0", "building_place": "海淀区",
	 * "purpose": "", "city": "北京市", "talkskills": [], "vocation": "", "level":
	 * 3, "building_photo":
	 * "http:\/\/182.92.233.245:8888\/images\/803b334418a34730b75c163f29caf7a5xiaoguotu.png"
	 * , "user_count": "0", "feature": "老有特色了", "report_customer_count": 0,
	 * "longitude": 100, "building_placeid": 32, "district": "海淀区", "fullname":
	 * "空中花园", "support": [], "customerage": "", "preferential": "优惠优惠",
	 * "housetype":[] "agent_brokerage_rate": 30, "workaddress": "", "avgPrice":
	 * 1000, "address": "", "propertys": "", "latitude": 200
	 */
	private String buildingid;// 楼盘id
	private String fullname;// 楼盘名称
	private String avgPrice;// 楼盘均价
	private String building_placeid;// 地区id
	private String level;// 楼盘地点level
	private String building_place;// 楼盘地点
	private String user_count;// 合作经纪人总数
	private String customer_count;// 意向客户总数
	private String agent_brokerage_rate;// 佣金点
	private String building_photo;// 楼盘图片
	private String city;// 所属城市
	private String district;// 所属地区

	private String longitude;// 经度
	private String latitude;// 纬度
	private String report_customer_count;// 已报客户数
	private String feature;// 楼盘买点
	private String preferential;// 优惠信息
	private List<HouseUnits> housetypes = new ArrayList<HouseUnits>();// 户型图
	// TargetCustomer
	private String customerage;// 客户年龄
	private String purpose;// 购房用途
	private String propertys;// 购房预算
	private String vocation;// 客户职业
	private String workaddress;// 工作区域
	private String address;// 居住区域
	
	private String personrebate;//独立经纪人返点
	public String getPersonrebate() {
		return personrebate;
	}

	public void setPersonrebate(String personrebate) {
		this.personrebate = personrebate;
	}

	private List<String> talkskillsed = new ArrayList<String>();// 拓客技巧
	private List<String> supported = new ArrayList<String>();// 支持

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getReport_customer_count() {
		return report_customer_count;
	}

	public void setReport_customer_count(String report_customer_count) {
		this.report_customer_count = report_customer_count;
	}

	public List<HouseUnits> getHousetypes() {
		return housetypes;
	}

	public void setHousetypes(List<HouseUnits> housetypes) {
		this.housetypes = housetypes;
	}

	public String getCustomerage() {
		return customerage;
	}

	public void setCustomerage(String customerage) {
		this.customerage = customerage;
	}

	public String getPropertys() {
		return propertys;
	}

	public void setPropertys(String propertys) {
		this.propertys = propertys;
	}

	public List<String> getTalkskillsed() {
		return talkskillsed;
	}

	public void setTalkskillsed(List<String> talkskillsed) {
		this.talkskillsed = talkskillsed;
	}

	public List<String> getSupported() {
		return supported;
	}

	public void setSupported(List<String> supported) {
		this.supported = supported;
	}

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

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getPreferential() {
		return preferential;
	}

	public void setPreferential(String preferential) {
		this.preferential = preferential;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public String getWorkaddress() {
		return workaddress;
	}

	public void setWorkaddress(String workaddress) {
		this.workaddress = workaddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
