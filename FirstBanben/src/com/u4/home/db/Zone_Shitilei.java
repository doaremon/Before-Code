package com.u4.home.db;

/**
 * 存储防区数据实体类
 * 
 * @author Administrator
 * 
 */
public class Zone_Shitilei {
	public int id;
	public String name;
	public String type;
	public int defended;
	public int undefended;
	public int status;

	@Override
	public String toString() {
		String result = "";
		/*
		 * result += "ID：" + this.ID + "，"; result += "姓名：" + this.Name + "，";
		 */
		return result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getDefended() {
		return defended;
	}

	public void setDefended(int defended) {
		this.defended = defended;
	}

	public int getUndefended() {
		return undefended;
	}

	public void setUndefended(int undefended) {
		this.undefended = undefended;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
