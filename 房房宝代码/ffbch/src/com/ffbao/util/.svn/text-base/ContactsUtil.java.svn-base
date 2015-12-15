/**
 * Project Name:房房宝
 * File Name:ContactsUtil.java
 * Package Name:com.ffbao.util
 * Date:2014-9-15下午5:37:01
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ffbao.entity.CallHistoryEntity;
import com.ffbao.entity.ContactEntity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.Photo;

/**
 * ClassName:ContactsUtil
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-15 下午5:37:01 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ContactsUtil {
	private ContentResolver cr;

	public ContactsUtil(ContentResolver cr) {
		super();
		this.cr = cr;
	}
	//读取最近联系人
	public List<CallHistoryEntity> getCallInfo(){
		List<CallHistoryEntity> list=new ArrayList<CallHistoryEntity>();
		String[] cols={
				CallLog.Calls.NUMBER,//电话号码
				CallLog.Calls.CACHED_NAME,//联系人名
				CallLog.Calls.TYPE,//通话类型：1：已接，2：已拨，3：未接
				CallLog.Calls.DATE,//通话日期时间
				CallLog.Calls.DURATION//通话时长
				};
		Cursor cursor=cr.query(CallLog.Calls.CONTENT_URI, cols, null, null, CallLog.Calls.DATE+" DESC");
		while(cursor.moveToNext()){
			CallHistoryEntity ci=new CallHistoryEntity();
			ci.setCall_number(cursor.getString(0));
			ci.setCall_name(cursor.getString(1));
			ci.setCall_type(cursor.getString(2));
			ci.setCall_date(new Date(Long.parseLong(cursor.getString(3))));
			ci.setCall_time(cursor.getInt(4));
			list.add(ci);
			ci=null;
		}
		cursor.close();
		return list;
	}
	//读取电话联系人
	public List<ContactEntity> getPhoneInfo(){
		List<ContactEntity> list = new ArrayList<ContactEntity>();
		String[] columns = {Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID,Phone.CONTACT_ID }; 
		Cursor cursor=cr.query(Phone.CONTENT_URI, columns, null, null,null);
		while(cursor.moveToNext()){
			ContactEntity pi=new ContactEntity();
			pi.setContact_name(cursor.getString(0));
			pi.setContact_number(cursor.getString(1));
			pi.setContact_id(cursor.getString(2));
			pi.setContact_phone(cursor.getString(3));
			list.add(pi);
			pi=null;
		}
		cursor.close();
		return list;
	}
}
