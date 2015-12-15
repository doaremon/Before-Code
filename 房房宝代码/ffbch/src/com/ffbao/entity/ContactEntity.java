/**
 * Project Name:房房宝
 * File Name:ContactEntity.java
 * Package Name:com.ffbao.entity
 * Date:2014-9-28下午4:24:04
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.entity;

import java.io.Serializable;

/**
 * ClassName:ContactEntity
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-28 下午4:24:04 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */

// 获取电话联系人
public class ContactEntity implements Serializable{
	
	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = 1L;
	private String contact_id;
	private String contact_name;
	private String contact_number;
	private String contact_phone;
	public String getContact_id() {
		return contact_id;
	}
	public void setContact_id(String contact_id) {
		this.contact_id = contact_id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	@Override
	public String toString() {
		return "ContactEntity [contact_id=" + contact_id + ", contact_name="
				+ contact_name + ", contact_number=" + contact_number
				+ ", contact_phone=" + contact_phone + "]";
	}
	public ContactEntity(String contact_id, String contact_name,
			String contact_number, String contact_phone) {
		super();
		this.contact_id = contact_id;
		this.contact_name = contact_name;
		this.contact_number = contact_number;
		this.contact_phone = contact_phone;
	}
	public ContactEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}