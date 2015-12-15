package com.ffbao.entity;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 
 * @FileName:Topic.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:Topic.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 */
public class Topic  implements Serializable ,Parcelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * "reportid": "100075",
      "topicid": 33,
      "topiccontent": "GG GG GG",
      "username": "王小林",
      "rolename": "经纪人",
      "companyname": "北京-总部",
      "answercount": 2,
      "create_time": "2014-10-16 03:38:54"
	 */

	
	private String reportid;
	private String topicid;
	private String topiccontent;
	private String username;
	private String rolename;
	private String companyname;
	private String answercount;
	private String answercontent;
	private String createtime;
	private String create_time;

	private String answername;

	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getAnswercount() {
		return answercount;
	}
	public void setAnswercount(String answercount) {
		this.answercount = answercount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getAnswercontent() {
		return answercontent;
	}
	public void setAnswercontent(String answercontent) {
		this.answercontent = answercontent;
	}
	public String getAnswername() {
		return answername;
	}
	public void setAnswername(String answername) {
		this.answername = answername;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}
	public String getTopiccontent() {
		return topiccontent;
	}
	public void setTopiccontent(String topiccontent) {
		this.topiccontent = topiccontent;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public static final Parcelable.Creator<Topic> CREATOR = new Creator<Topic>()
	{

		@Override
		public Topic[] newArray(int size)
		{
			return new Topic[size];
		}

		@Override
		public Topic createFromParcel(Parcel source)
		{
			return new Topic(source);
		}
	};

	public Topic()
	{

	}

	public Topic(final Parcel in)
	{
		
		reportid = in.readString();
		topicid = in.readString();
		topiccontent = in.readString();
		createtime = in.readString();
		answername = in.readString();
		answercontent = in.readString();
		username = in.readString();
		answercount = in.readString();
		rolename = in.readString();
		companyname = in.readString();
		create_time = in.readString();
		
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		
		dest.writeString(reportid);
		dest.writeString(topicid);
		dest.writeString(topiccontent);
		dest.writeString(createtime);
		dest.writeString(answername);
		dest.writeString(answercontent);
		dest.writeString(username);
		dest.writeString(answercount);
		dest.writeString(rolename);
		dest.writeString(companyname);
		dest.writeString(create_time);
		
	}
}
