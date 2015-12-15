package com.ffbao.util;

import java.util.HashMap;
import java.util.Map;


import android.content.Context;

/**
 * 
 * @FileName:RequestMapUtils.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:RequestMapUtils.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: userUpdateInfo 用户信息修改
 */
public class RequestMapUtils {
	
	public static Map<String, Object> getUserInfo(Context context,
			String newValue, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userUpdateInfo);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.userid, ""));
		map.put(Constant.type, type);
		map.put(Constant.newvalue, newValue);

		return map;
	}
	
	
	
	
}