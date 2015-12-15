package com.ffbao.ui;

import com.ffbao.activity.R;
import com.ffbao.util.ExitActivity;

import android.os.Bundle;

/**
 * 
 * @FileName:SettingAuthorActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SettingAuthorActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 消息展示
 */
public class SettingAuthorActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_setting_info);
		ExitActivity.addActivity(this);
	}
}
