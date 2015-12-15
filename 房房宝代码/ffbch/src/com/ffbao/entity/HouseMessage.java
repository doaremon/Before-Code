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
 * @Funtion: 获取 楼盘相关活动消息
 */
public class HouseMessage {

	private String title;
	private String date;
	private List<String> imageUrls;
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
