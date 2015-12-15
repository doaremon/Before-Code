/**
 * Project Name:房房宝
 * File Name:ComissionListEntity.java
 * Package Name:com.ffbao.entity
 * Date:2014-10-16下午6:48:28
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.entity;

import java.io.Serializable;

/**
 * ClassName:ComissionListEntity
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-10-16 下午6:48:28 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ComissionListEntity implements Serializable{
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private CommissionNoteInfo result;
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
	public CommissionNoteInfo getResult() {
		return result;
	}
	public void setResult(CommissionNoteInfo result) {
		this.result = result;
	}
	@Override
	public String toString() {
		return "ComissionListEntity [status=" + status + ", message=" + message
				+ ", result=" + result + "]";
	}
	public ComissionListEntity(int status, String message,
			CommissionNoteInfo result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}
	public ComissionListEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
