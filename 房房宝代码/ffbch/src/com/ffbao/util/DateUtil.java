/**
 * Project Name:房房宝
 * File Name:DateUtil.java
 * Package Name:com.ffbao.util
 * Date:2014-9-15下午6:00:54
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:DateUtil
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-15 下午6:00:54 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 存放接口以及网页链接的工具类
 */
@SuppressLint("SimpleDateFormat")
public class DateUtil {
	
	/**
	 * 获取当前日期
	 * 
	 * @return iphone_yyyy_MM_dd的形式
	 */
	public static String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("_yyyy_MM_dd");
		Date curDate = new Date(System.currentTimeMillis());
		return "" + formatter.format(curDate);
	}
	
}
