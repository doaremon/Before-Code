package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.engine.impl.PhoneSendCodeEngine;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.ffbao.util.VerifyUtils;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 
 * @FileName:RegiestActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:RegiestActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 找回密码 验证手机号
 */
public class RegiestActivity extends BaseActivity implements OnClickListener {

	private EditText phone;
	private EditText code;
	private TextView phoneCode;
	private Handler handler = new Handler();
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_register_mobile);
		setActionBarTitle("找回密码");
		ExitActivity.addActivity(this);
		phone = (EditText) findViewById(R.id.activity_change_phone_num);
		code = (EditText) findViewById(R.id.activity_change_phone_code);
		phoneCode = (TextView) findViewById(R.id.activity_change_phone_getcode);
		findViewById(R.id.activity_change_phone_getcode).setOnClickListener(
				this);
		findViewById(R.id.activity_change_phone_commit)
				.setOnClickListener(this);

		if (Constant.forgetVerifyNum > 0) {

//			VerifyUtils.counter();
			VerifyUtils.setActivityValue(phoneCode, handler,2);
			phone.setText(SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.forgetPhone));
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.activity_change_phone_getcode:
			
			if(!StringUtils.isNull(phone.getText().toString())){
				ToastUtils.showToast(context, "手机号不能为空");
				return;
			}
			if (!StringUtils.isPhone(phone.getText().toString())) {
				
				ToastUtils.showToast(context, "手机号格式问题");
				return;
			}
				PhoneSendCodeEngine engine = new PhoneSendCodeEngine(
						RegiestActivity.this);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(Constant.commandText, UrlUtil.userSendCheckCode);
				map.put(Constant.phone, phone.getText().toString());
				map.put(Constant.type, UrlUtil.forget_password);
				if (StringUtils.isPhone(phone.getText().toString()))
					engine.execute(phoneCode, map, null);
			
			break;
		case R.id.activity_change_phone_commit:

			regiestExeute();
			break;
		default:
			break;
		}
	}

	private void regiestExeute() {
		String number = phone.getText().toString().trim();
		if (!StringUtils.isPhone(number)) {

			ToastUtils.showErrorToast(RegiestActivity.this, "手机号输入错误");
			return;

		} else if (!number.equals(SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.forgetPhone))) {
			ToastUtils.showErrorToast(RegiestActivity.this, "手机号验证码错误");
			return;

		} else if (!StringUtils.isPhoneCodeFotget(RegiestActivity.this, code
				.getText().toString())) {

			ToastUtils.showErrorToast(RegiestActivity.this, "验证码错误");
			return;
		}

		Intent intent = new Intent();
		intent.setClass(RegiestActivity.this,
				ChangePasswordForgetActivity.class);
		startActivity(intent);
		finish();
	}

}
