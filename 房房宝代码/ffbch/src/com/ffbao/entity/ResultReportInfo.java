/**
 * Project Name:房房宝
 * File Name:ResultReportInfo.java
 * Package Name:com.ffbao.entity
 * Date:2014-10-8下午3:33:23
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.entity;

import java.io.Serializable;

/**
 * ClassName:ResultReportInfo
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-10-8 下午3:33:23 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ResultReportInfo implements Serializable{
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private EditReportedInfo result;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public EditReportedInfo getResult() {
		return result;
	}
	public void setResult(EditReportedInfo result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ResultReportInfo [status=" + status + ", message=" + message
				+ ", result=" + result + "]";
	}
	public ResultReportInfo(int status, String message, EditReportedInfo result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}
	public ResultReportInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}