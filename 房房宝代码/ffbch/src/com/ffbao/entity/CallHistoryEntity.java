/**
 * Project Name:房房宝
 * File Name:CallHistoryEntity.java
 * Package Name:com.ffbao.entity
 * Date:2014-9-28下午4:19:23
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName:CallHistoryEntity
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-28 下午4:19:23 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class CallHistoryEntity implements Serializable{
//	CallLog.Calls.NUMBER,//电话号码
//	CallLog.Calls.CACHED_NAME,//联系人名
//	CallLog.Calls.TYPE,//通话类型：1：已接，2：已拨，3：未接
//	CallLog.Calls.DATE,//通话日期时间
//	CallLog.Calls.DURATION//通话时长
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;
	private String call_number;
	private String call_name;
	private String call_type;
	private Date call_date;
	private int call_time;
	public String getCall_number() {
		return call_number;
	}
	public void setCall_number(String call_number) {
		this.call_number = call_number;
	}
	public String getCall_name() {
		return call_name;
	}
	public void setCall_name(String call_name) {
		this.call_name = call_name;
	}
	public String getCall_type() {
		return call_type;
	}
	public void setCall_type(String call_type) {
		this.call_type = call_type;
	}
	public Date getCall_date() {
		return call_date;
	}
	public void setCall_date(Date call_date) {
		this.call_date = call_date;
	}
	public int getCall_time() {
		return call_time;
	}
	public void setCall_time(int call_time) {
		this.call_time = call_time;
	}
	@Override
	public String toString() {
		return "CallHistoryEntity [call_number=" + call_number + ", call_name="
				+ call_name + ", call_type=" + call_type + ", call_date="
				+ call_date + ", call_time=" + call_time + "]";
	}
	public CallHistoryEntity(String call_number, String call_name,
			String call_type, Date call_date, int call_time) {
		super();
		this.call_number = call_number;
		this.call_name = call_name;
		this.call_type = call_type;
		this.call_date = call_date;
		this.call_time = call_time;
	}
	public CallHistoryEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
