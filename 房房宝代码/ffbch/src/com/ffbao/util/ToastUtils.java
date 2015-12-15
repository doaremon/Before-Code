package com.ffbao.util;


import com.ffbao.APP;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @FileName:ToastUtils.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ToastUtils.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: ��˾������
 */
public class ToastUtils {
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param resId
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:��ʾ��˾
	 */
	public static void showToast(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}
	

	public static void showToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}
	
	public static void showErrorToast(Context context, String text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}
	
	public static void showErrorToast(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
	}

}
