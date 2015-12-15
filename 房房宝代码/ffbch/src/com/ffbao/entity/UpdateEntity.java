/**
 * Project Name:房房宝
 * File Name:UpdateEntity.java
 * Package Name:com.ffbao.entity
 * Date:2014-9-30下午12:18:39
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
 */

package com.ffbao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName:UpdateEntity
 * 
 * Function: TODO ADD FUNCTION
 * 
 * Date: 2014-9-30 下午12:18:39
 * 
 * @author apple
 * @version
 * @since JDK 1.7
 * @see
 */
public class UpdateEntity implements Serializable {
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private List<UpdateResultEntity> result;
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
	public List<UpdateResultEntity> getResult() {
		return result;
	}
	public void setResult(List<UpdateResultEntity> result) {
		this.result = result;
	}
	public UpdateEntity(int status, String message,
			List<UpdateResultEntity> result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}
	@Override
	public String toString() {
		return "UpdateEntity [status=" + status + ", message=" + message
				+ ", result=" + result + "]";
	}
	public UpdateEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}