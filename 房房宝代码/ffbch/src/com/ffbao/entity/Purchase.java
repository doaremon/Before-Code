package com.ffbao.entity;

import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.util.ID;

/**
 * 
 * @FileName:Purchase.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:Purchase.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion:  call userGetPurchaseList
 */
public class Purchase {

	/**
	private TextView purchaseSerial;// purchase_serial
	private TextView purchasePremiseName;// purchase_premiseName
	private TextView purchaseRommNum;// purchase_roomNum
	private TextView purchaseRoomConurbation;// purchase_RoomConurbation
	private TextView purchaseRommSite;// purchase_roomSite
	private TextView purchaseUnitCose;// purchase_unitcost
	private TextView purchaseCount;// purchase_count
	private TextView purchaseAmountAdvanced;// purchase_amountAdvanced
	private TextView purchaseActualBalance;// purchase_actualBalance
	private TextView purchaseState;// purchase_state
	private TextView purchaseCreateTime;// purchase_createTime
	private TextView purchaseOperation;// purchase_operation
	 */
/**
 * 
 * 
 * "purchaseid":1,
 * "customername":"史上",
 * "buildingname":"君安世纪城",
 * "agent_company":"pzp00-000",
 * "house_number":"902",
 * "state":4,
 * "statevalue":"已成交",
 * "purchase_city":"1",
 * "purchase_district":"1",
 * "price":1.0
 * ,"amout":"1"
 * ,"totalprice":1.0
 * ,"actiualTotal_price":5.0,
 * "agent_brokerage_fee":10.0,
 * "create_time":"2014-10-10 03:21:48",
 * "update_time":"2014-10-10 03:21:46"
 * }]}
 */
	private String purchaseid;
	private String customername;
	private String buildingname;
	private String agent_company;
	private String house_number;
	private String state;
	private String statevalue;
	private String purchase_city;
	private String purchase_district;
	private String price;
	private String amout;
	private String totalprice;   //总价
	private String actiualTotal_price; //实际总价actiualTotal_price
	private String agent_brokerage_fee;
	private String create_time;
	private String update_time;
	private String address;
	private String prepayment; // 预付金额
	private String payment; //实付金额
	private String reportid; //报备单ID
	private String agentbrokeragerate; //佣金点
	private String agentbrokeragefee; //佣金总价
	
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getAgentbrokeragerate() {
		return agentbrokeragerate;
	}
	public void setAgentbrokeragerate(String agentbrokeragerate) {
		this.agentbrokeragerate = agentbrokeragerate;
	}
	public String getAgentbrokeragefee() {
		return agentbrokeragefee;
	}
	public void setAgentbrokeragefee(String agentbrokeragefee) {
		this.agentbrokeragefee = agentbrokeragefee;
	}
	public String getPrepayment() {
		return prepayment;
	}
	public void setPrepayment(String prepayment) {
		this.prepayment = prepayment;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public String getBuildingname() {
		return buildingname;
	}
	public void setBuildingname(String buildingname) {
		this.buildingname = buildingname;
	}
	public String getAgent_company() {
		return agent_company;
	}
	public void setAgent_company(String agent_company) {
		this.agent_company = agent_company;
	}
	public String getHouse_number() {
		return house_number;
	}
	public void setHouse_number(String house_number) {
		this.house_number = house_number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatevalue() {
		return statevalue;
	}
	public void setStatevalue(String statevalue) {
		this.statevalue = statevalue;
	}
	public String getPurchase_city() {
		return purchase_city;
	}
	public void setPurchase_city(String purchase_city) {
		this.purchase_city = purchase_city;
	}
	public String getPurchase_district() {
		return purchase_district;
	}
	public void setPurchase_district(String purchase_district) {
		this.purchase_district = purchase_district;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmout() {
		return amout;
	}
	public void setAmout(String amout) {
		this.amout = amout;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getActiualTotal_price() {
		return actiualTotal_price;
	}
	public void setActiualTotal_price(String actiualTotal_price) {
		this.actiualTotal_price = actiualTotal_price;
	}
	public String getAgent_brokerage_fee() {
		return agent_brokerage_fee;
	}
	public void setAgent_brokerage_fee(String agent_brokerage_fee) {
		this.agent_brokerage_fee = agent_brokerage_fee;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	

	
	
	
	
	
}
