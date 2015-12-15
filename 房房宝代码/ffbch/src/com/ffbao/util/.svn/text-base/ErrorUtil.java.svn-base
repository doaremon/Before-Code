package com.ffbao.util;

import android.content.Context;
import android.content.pm.PackageInfo;

/**
 * 
 * @FileName:ErrorUtil.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ErrorUtil.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: exception obtain VersionName
 */
public class ErrorUtil {
	private final Context myContext;

	public ErrorUtil(Context context) {
		myContext = context;
	}

	public String getVersionName() {
		try {
			final PackageInfo info = myContext.getPackageManager().getPackageInfo(myContext.getPackageName(), 0);
			return info.versionName + " (" + info.versionCode + ")";
		} catch (Exception e) {
			return "";
		}
	}
}
