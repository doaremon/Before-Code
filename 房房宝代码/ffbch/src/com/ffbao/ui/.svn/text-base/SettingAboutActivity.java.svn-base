package com.ffbao.ui;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.update.ProjectUpdate;
import com.ffbao.util.ExitActivity;
/**
 * 
 * @FileName:SettingAboutActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SettingAboutActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 关于我们
 */
public class SettingAboutActivity extends BaseActivity {

	private TextView  aboutName;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_setting_about);
		setActionBarTitle("关于我们");
		ExitActivity.addActivity(this);
		aboutName = (TextView) findViewById(R.id.activity_about_name);
		getAppVersionName();
	}
	
	public  void getAppVersionName() {
	    try {
	        PackageManager pm = getPackageManager();  
	        PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);  
	        String versionName = ProjectUpdate.getAppCode(SettingAboutActivity.this);
	        aboutName.setText("版本号：V"+versionName);
	    } catch (Exception e) {  
	        Log.e("VersionInfo", "Exception", e);  
	    }  
	}  
}
