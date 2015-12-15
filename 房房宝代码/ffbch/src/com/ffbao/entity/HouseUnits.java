package com.ffbao.entity;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class HouseUnits implements Serializable ,Parcelable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String description;//户型类型
	private String room;
	private String area; //平米
	private String housephoto ;// 楼盘户型图  
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getHousephoto() {
		return housephoto;
	}
	public void setHousephoto(String housephoto) {
		this.housephoto = housephoto;
	}
	public static final Parcelable.Creator<HouseUnits> CREATOR = new Creator<HouseUnits>()
			{

				@Override
				public HouseUnits[] newArray(int size)
				{
					return new HouseUnits[size];
				}

				@Override
				public HouseUnits createFromParcel(Parcel source)
				{
					return new HouseUnits(source);
				}
			};

			public HouseUnits()
			{

			}

			public HouseUnits(final Parcel in)
			{
				
				description = in.readString();
				room = in.readString();
				area = in.readString();
				housephoto = in.readString();
			}

			@Override
			public int describeContents()
			{
				return 0;
			}

			@Override
			public void writeToParcel(Parcel dest, int flags)
			{
				
				dest.writeString(description);
				dest.writeString(room);
				dest.writeString(area);
				dest.writeString(housephoto);
				
			}
}
