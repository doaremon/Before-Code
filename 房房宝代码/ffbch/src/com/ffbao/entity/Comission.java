package com.ffbao.entity;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @FileName:Comission.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:Comission.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: Comission javabean  ,call userGetComissionList
 */
public class Comission  {
	
	/**
	
	
	"comissionid": 9,
    "reportid": "100031",
    "agentcompany": "TestCompanyName",
    "agentname": "TestAgetnName",
    "brokeragerate": 2.0,
    "brokeragefee": 4.0,
    "state": 2,
    "statevalue": "审批通过",
    "operatorid": 100006,
    "create_time": "2014-10-18 02:42:35"
    	*/
	private String comissionid;
	private String reportid;
	private String agentcompany;
	private String agentname;
	private String brokeragerate;
	private String brokeragefee;
	private String state;
	private String statevalue;
	private String operatorid;
	private String create_time;
	private String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getComissionid() {
		return comissionid;
	}
	public void setComissionid(String comissionid) {
		this.comissionid = comissionid;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getAgentcompany() {
		return agentcompany;
	}
	public void setAgentcompany(String agentcompany) {
		this.agentcompany = agentcompany;
	}
	public String getAgentname() {
		return agentname;
	}
	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}
	public String getBrokeragerate() {
		return brokeragerate;
	}
	public void setBrokeragerate(String brokeragerate) {
		this.brokeragerate = brokeragerate;
	}
	public String getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(String brokeragefee) {
		this.brokeragefee = brokeragefee;
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
	public String getOperatorid() {
		return operatorid;
	}
	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
