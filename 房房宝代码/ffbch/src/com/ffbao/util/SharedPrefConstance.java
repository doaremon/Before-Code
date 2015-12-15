package com.ffbao.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.ffbao.ui.LoginActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.util.Log;
/**
 * 
 * @FileName:SharedPrefConstance.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SharedPrefConstance.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 缓存本地数据 
 */
public class SharedPrefConstance {

	public static final String regiestPhoneCode = "regiestPhoneCode";
	public static final String forgettPhoneCode = "regiestPhoneCode";
	public static final String changePhoneCode = "regiestPhoneCode";
	//	public static final String regiestPhoneCode = "regiestPhoneCode";
	public static final String regiestPhone = "regiestPhone";
	public static final String forgetPhone = "forgetPhone";
	public static final String changePhone = "changePhone";
	//	public static final String regiestPhone = "regiestPhone";
	public static final String UUID = "UUID";
	public static final String userid = "userid";
	public static final String companyname = "companyname";
	public static final String companynameUpdate = "companyname";

	public static final String servicephone = "servicephone";
	public static final String isBank = "isBank";//是否有银行卡

	public static final String servicename = "servicename";
	public static final String tdbarcode_path = "tdbarcode_path";
	public static final String ffb_tdbarcode_path = "ffb_tdbarcode_path";
	public static final String shareGuideUrl = "shareGuideUrl";
	public static final String myselfimg = "myselfimg";
	public static final String myselfname = "myselfname";
	public static final String myselfphone = "myselfphone";
	public static final String sex = "sex";
	public static final String companyid = "companyid";
	public static final String version = "version";
	public static final String rolecount = "rolecount";
	public static final String password = "password";
	public static final String username = "username";
	public static final String  mustUpdateVersion = "mustUpdateVersion";
	public static final String  updateVersion = "updateVersion";
	public static final String  frist = "frist";
	public static final String  smswaittime = "smswaittime";
	public static final String  workguide = "workguide";
	public static final String  agreement = "agreement";
	public static final String  other = "other";
	public static final String  customerservices = "customerservices";

	public static final String  errorFile = "errorFile";

	public static final String  mustUpdate = "1";
	public static final String  mustORUpdate = "0";

	// TODO 报备点消息 获取条数
	public static final String reportCount = "reportCount";

	private static Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param key
	 * @param value
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:获取 SharedPreferences 获取相应属性
	 */
	public static String getStringValue(Context context, String key,
			String value) {

		if (!map.containsKey(key)) {
			SharedPreferences sp = context.getSharedPreferences("user",
					Context.MODE_PRIVATE);
			String valueMap = sp.getString(key, value);
			map.put(key, valueMap);

			return valueMap;
		} else {
			Object object = map.get(key);
			if (object != null && object.toString().length()!=0) {
				return object.toString();
			} else {
				return value;
			}
		}
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param key
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:SharedPreferences属性值
	 */
	public static String getStringValue(Context context, String key) {

		String value = "";
		if (!map.containsKey(key)) {
			SharedPreferences sp = context.getSharedPreferences("user",
					Context.MODE_PRIVATE);
			String valueMap = sp.getString(key, value);
			if(password.equals(key)){
				valueMap = getDecode(context,valueMap);
			}
			map.put(key, valueMap);

			return valueMap;
		} else {
			Object object = map.get(key);
			if (object != null && object.toString().length()!=0) {
				return object.toString();
			} else {
				return value;
			}
		}
	}	
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param key
	 * @param value
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: 设置 SharedPreferences 属性处理
	 */
	public static void setSharePref(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences("user",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		map.put(key, value);
		if(password.equals(key)){
			value =setEncoder(context, value);
		}
		edit.putString(key, value);

		edit.commit();
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param key
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:SharedPreferences属性值
	 */
	public static String getStringValuePhone(Context context, String key) {

		String value = "";
		if (!map.containsKey(key)) {
			SharedPreferences sp = context.getSharedPreferences("phone",
					Context.MODE_PRIVATE);
			String valueMap = sp.getString(key, value);
			if(password.equals(key)){
				valueMap = getDecode(context,valueMap);
			}
			map.put(key, valueMap);

			return valueMap;
		} else {
			Object object = map.get(key);
			if (object != null && object.toString().length()!=0) {
				return object.toString();
			} else {
				return value;
			}
		}
	}	
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param key
	 * @param value
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:获取受技属性
	 */
	public static void setSharePrefPhone(Context context, String key, String value) {
		SharedPreferences sp = context.getSharedPreferences("phone",
				Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
		map.put(key, value);

	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:清理所有数据
	 */
	public static void clear(Context context) {
		SharedPreferences sp = context.getSharedPreferences("user",
				Context.MODE_PRIVATE);
		Map<String, ?> all = sp.getAll();
		Editor edit = sp.edit();
		for (Entry<String, ?> entry : all.entrySet()) {
			edit.putString(entry.getKey(), "");
		}
		edit.commit();
		map.clear();
		SharedPreferences spp = context.getSharedPreferences("user",
				Context.MODE_PRIVATE);
		Map<String, ?> alls = spp.getAll();
		for(Entry<String, ?> entryy : alls.entrySet()){
			String str1=entryy.getKey();
			String str2=(String) entryy.getValue();
			Log.i("chenghao", "key="+str1);
			Log.i("chenghao", "value="+str2);
		}

	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param baseString
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:密码本地编译
	 */
	public static String setEncoder(Context context,String baseString){

		byte[] decode = Base64.encode(baseString.getBytes(), Base64.DEFAULT);
		try {
			String decodeStr = new String(decode,"UTF-8");
			//			setSharePref(context,SharedPrefConstance.password, decodeStr);
			return decodeStr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param baseString
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:密码解密
	 */
	public static String getDecode(Context context,String password){

		String decode = null ;
		//		String password = getStringValue(context, SharedPrefConstance.password);
		try {
			decode = new String(Base64.decode(password.getBytes(), Base64.DEFAULT),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}
}
