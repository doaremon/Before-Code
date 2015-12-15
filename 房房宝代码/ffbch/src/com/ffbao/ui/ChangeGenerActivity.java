package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RequestMapUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * 
 * @FileName:ChangeGenerActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ChangeGenerActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion:修改性别
 */
public class ChangeGenerActivity extends BaseActivity implements
		OnClickListener {

	private ImageView male;
	private ImageView female;
	private String sex;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_change_gender);
		setActionBarTitle("性别");
		ExitActivity.addActivity(this);
		male = (ImageView) findViewById(R.id.iv_setting_male);
		female = (ImageView) findViewById(R.id.iv_setting_female);

		sex = SharedPrefConstance.getStringValue(this, SharedPrefConstance.sex,
				"");
		defualt();

		findViewById(R.id.activity_gender_male).setOnClickListener(this);
		findViewById(R.id.activity_gender_female).setOnClickListener(this);
	}

	public void defualt() {

		if ("男".equals(sex)) {
			defualtInit();
			male.setVisibility(View.VISIBLE);
		} else if("女".equals(sex)){
			defualtInit();
			female.setVisibility(View.VISIBLE);
		}
	}

	public void defualtInit() {
		male.setVisibility(View.INVISIBLE);
		female.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.activity_gender_male:

			sex = "男";
			defualt();
			settingGener();
			break;
		case R.id.activity_gender_female:

			sex = "女";
			defualt();
			settingGener();
			break;

		default:
			break;
		}
	}

	private void settingGener() {

		Map<String, Object> map = RequestMapUtils.getUserInfo(this, sex, "2");
		HttpClientRequest.getHttpPost(ChangeGenerActivity.this, map,
				new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						if (ExecuteJSONUtils.simpleUpdateInfo(
								ChangeGenerActivity.this, msg)) {

							SharedPrefConstance.setSharePref(
									ChangeGenerActivity.this,
									SharedPrefConstance.sex, sex);

							finish();
						}
					}

					@Override
					public void onFailure(Exception error, String msg) {

					}
				});
	}

}
