package com.ffbao.entity;

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
public class HouseParamter {

//	private String startTime;//开盘时间:
//	private String developer;//开  发  商:
//	private String wuye;//物业公司
//	private String priece;//物业类型
//	private String buildType;//建筑类型
//	private String buildArea;//建筑面积
//	private String decorate;//装修情况
//	private String households; // 总  户  数
//	private String parkingDigits;//车  位  数
//	private String plotRatio;//容  积  率
//	private String greeningRate;//绿  地  率
//	private String propertyFee;//物  业  费
	
	private String open_time;//开盘时间
	private String developerName;//开发商名称
	private String property_company;//物业公司
	private String buildType;//建筑类型code 0-小高层 1-高层 2-别墅
	private String buildTypeName;//建筑类型名称
	private String buildArea;//建筑面积
	private String build_decorate;//装修情况code 0-毛坯 1-精装
	private String build_decorateName;//装修情况名称
	private String planning_resident;//规划户数
	private String parking;//车位数
	private String cubage_rate;//容积率
	private String green_rate;//绿地率
	public String getOpen_time() {
		return open_time;
	}
	public void setOpen_time(String open_time) {
		this.open_time = open_time;
	}
	public String getDeveloperName() {
		return developerName;
	}
	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	public String getProperty_company() {
		return property_company;
	}
	public void setProperty_company(String property_company) {
		this.property_company = property_company;
	}
	public String getBuildType() {
		return buildType;
	}
	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}
	public String getBuildTypeName() {
		return buildTypeName;
	}
	public void setBuildTypeName(String buildTypeName) {
		this.buildTypeName = buildTypeName;
	}
	public String getBuildArea() {
		return buildArea;
	}
	public void setBuildArea(String buildArea) {
		this.buildArea = buildArea;
	}
	public String getBuild_decorate() {
		return build_decorate;
	}
	public void setBuild_decorate(String build_decorate) {
		this.build_decorate = build_decorate;
	}
	public String getBuild_decorateName() {
		return build_decorateName;
	}
	public void setBuild_decorateName(String build_decorateName) {
		this.build_decorateName = build_decorateName;
	}
	public String getPlanning_resident() {
		return planning_resident;
	}
	public void setPlanning_resident(String planning_resident) {
		this.planning_resident = planning_resident;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String parking) {
		this.parking = parking;
	}
	public String getCubage_rate() {
		return cubage_rate;
	}
	public void setCubage_rate(String cubage_rate) {
		this.cubage_rate = cubage_rate;
	}
	public String getGreen_rate() {
		return green_rate;
	}
	public void setGreen_rate(String green_rate) {
		this.green_rate = green_rate;
	}
	

	
}
