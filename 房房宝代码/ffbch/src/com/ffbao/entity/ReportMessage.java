package com.ffbao.entity;

/**
 * 
 * @FileName:ReportMessage.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ReportMessage.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: abort report message
 */
public class ReportMessage {

	/**
	 "messageid": 2,
      "reportid": 100001,
      "messagecontent": "您的用户沈大力机票预订成功。",
      "confirmed": 1,
      "create_time": "2014/09/29 18:10:43"
	 */
	
	private String messageid;
	private String reportid;
	private String messagecontent;
	private String confirmed;
	private String create_time;
	
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getMessagecontent() {
		return messagecontent;
	}
	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
}
