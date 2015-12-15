package com.ffbao.entity;

import android.os.Parcel;
import android.os.Parcelable;
///**
// * update entriy
// * @author lee
// * 
// */
/**
 * @FileName :UpdateVersion。java
 * @Deprecatred：
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers：2014010101
 * @Author：lee
 * @Create Date：2014-09-25
 * @Update Author： lee
 * @Update Date：2014年11月4日10:25:50
 * @Version： 0.9.8
 */
public class UpdateVersion  implements Parcelable{
	
	
	/**
	 * serverversion:"4",
	 * mustupdate:0,
	 * url:"http://f1.ffb.net/ffbmobile.apk",
	 * updatecontent:"修改主题图片",
	 * updatetitle:"房房网更新"
	 */
	private String serverversion; /* current service version*/
	private String mustupdate;    /* is update flag  0 should  update but force update1 force update，2 current new version*/
	private String url;			  /* download apk url*/ 
	private String updatecontent; /* update notify content*/
	private String updatetitle;   /* update notify title*/
	public String getServerversion() {
		return serverversion;
	}
	public void setServerversion(String serverversion) {
		this.serverversion = serverversion;
	}
	public String getMustupdate() {
		return mustupdate;
	}
	public void setMustupdate(String mustupdate) {
		this.mustupdate = mustupdate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUpdatecontent() {
		return updatecontent;
	}
	public void setUpdatecontent(String updatecontent) {
		this.updatecontent = updatecontent;
	}
	public String getUpdatetitle() {
		return updatetitle;
	}
	public void setUpdatetitle(String updatetitle) {
		this.updatetitle = updatetitle;
	}
	

	public static final Parcelable.Creator<UpdateVersion> CREATOR = new Creator<UpdateVersion>()
			{

				@Override
				public UpdateVersion[] newArray(int size)
				{
					return new UpdateVersion[size];
				}

				@Override
				public UpdateVersion createFromParcel(Parcel source)
				{
					return new UpdateVersion(source);
				}
			};

			public UpdateVersion()
			{

			}

			public UpdateVersion(final Parcel in)
			{
				serverversion = in.readString();
				mustupdate = in.readString();
				url = in.readString();
				updatecontent = in.readString();
				updatetitle = in.readString();
				
			}

			public int describeContents()
			{
				return 0;
			}

			public void writeToParcel(Parcel dest, int flags)
			{
				dest.writeString(serverversion);
				dest.writeString(mustupdate);
				dest.writeString(url);
				dest.writeString(updatecontent);
				dest.writeString(updatetitle);
				
			}
}
