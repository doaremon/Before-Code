/**
 * Project Name:Zorro
 * File Name:FlowInfo.java
 * Package Name:com.he.zorro
 * Date:2014-9-24����11:06:55
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.entity;

import java.io.Serializable;

/**
 * ClassName:FlowInfo
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-24 ����11:06:55 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class FlowInfo implements Serializable{

	/**
	 * serialVersionUID:TODO(��һ�仰�������������ʾʲô).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;
	private String goodsInfo;
	private String goodsTime;
	private String personInfo;
	private String personPhone;
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public String getGoodsTime() {
		return goodsTime;
	}
	public void setGoodsTime(String goodsTime) {
		this.goodsTime = goodsTime;
	}
	public String getPersonInfo() {
		return personInfo;
	}
	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}
	public String getPersonPhone() {
		return personPhone;
	}
	public void setPersonPhone(String personPhone) {
		this.personPhone = personPhone;
	}
	@Override
	public String toString() {
		return "FlowInfo [goodsInfo=" + goodsInfo + ", goodsTime=" + goodsTime
				+ ", personInfo=" + personInfo + ", personPhone=" + personPhone
				+ "]";
	}
	public FlowInfo(String goodsInfo, String goodsTime, String personInfo,
			String personPhone) {
		super();
		this.goodsInfo = goodsInfo;
		this.goodsTime = goodsTime;
		this.personInfo = personInfo;
		this.personPhone = personPhone;
	}
	public FlowInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
