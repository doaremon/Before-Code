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
import com.ffbao.util.RequestMapUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.ffbao.util.VerifyUtils;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 
 * @FileName:ChangePhoneActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ChangePhoneActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 修改绑定手机
 */
public class ChangePhoneActivity extends BaseActivity implements
		OnClickListener {

	private EditText phone;
	private EditText code;
	private TextView currentPhone;
	private String moblie;
	private TextView phoneCode;
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_change_phone);
		setActionBarTitle("修改绑定手机");
		ExitActivity.addActivity(this);
		phone = (EditText) findViewById(R.id.activity_change_phone_num);
		code = (EditText) findViewById(R.id.activity_change_phone_code);
		currentPhone = (TextView) findViewById(R.id.activity_change_phone_current);

		moblie = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.myselfphone, "");
		findViewById(R.id.activity_change_phone_getcode).setOnClickListener(
				this);
		findViewById(R.id.activity_change_phone_commit)
				.setOnClickListener(this);
		phoneCode = (TextView) findViewById(R.id.activity_change_phone_getcode);
		phoneCode.setOnClickListener(this);
		if (Constant.changVerifyNum > 0) {

//			VerifyUtils.counter();
			VerifyUtils.setActivityValue(phoneCode, handler,3);
			phone.setText(SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.changePhone));
		}
		currentPhone.setText("当前手机号码为："+ moblie);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.activity_change_phone_getcode:
			
			if(!StringUtils.isNull(phone.getText().toString())){
				
				ToastUtils.showErrorToast(ChangePhoneActivity.this, "手机号码不能为空");
				return;
			}
			if (!StringUtils.isPhone(phone.getText().toString())) {

				ToastUtils.showErrorToast(ChangePhoneActivity.this, "手机号输入错误");
				return;

			}
			PhoneSendCodeEngine engine = new PhoneSendCodeEngine(this);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.commandText, UrlUtil.userSendCheckCode);
			map.put(Constant.phone, phone.getText().toString());
			map.put(Constant.type, UrlUtil.modify_the_phone);
			if (StringUtils.isPhone(phone.getText().toString())) {
				if (!phone.getText().toString().equals(moblie))
//					HttpClientRequest.getHttpPost(ChangePhoneActivity.this,
//							map, new CallBackListener() {
//
//								@Override
//								public void onSuccess(String msg) {
//									System.out.println(msg);
//									ExecuteJSONUtils.phoneCode(
//											ChangePhoneActivity.this, msg);
//								}
//
//								@Override
//								public void onFailure(Exception error,
//										String msg) {
//
//								}
//							});
					engine.execute(phoneCode, map, null);
				else
					ToastUtils.showToast(ChangePhoneActivity.this, "原来绑定一样");
			}
			break;
		case R.id.activity_change_phone_commit:
			
			String number = phone.getText().toString().trim();
			if (!StringUtils.isPhone(number)) {

				ToastUtils.showErrorToast(ChangePhoneActivity.this, "手机号输入错误");
				return;

			} else if (number.equals(moblie)) {

				ToastUtils.showToast(ChangePhoneActivity.this, "原来绑定一样");
				return;
			} else if (!number.equals(SharedPrefConstance.getStringValue(context,
							SharedPrefConstance.changePhone))) {

				ToastUtils.showToast(ChangePhoneActivity.this, "验证手机号错误");
				return;
			} else if (!StringUtils.isPhoneCodeChange(ChangePhoneActivity.this, code
					.getText().toString())) {

				ToastUtils.showToast(ChangePhoneActivity.this, "验证码错误");
				return;
			}
			settingPhone();
			break;

		default:
			break;
		}
	}

	private void settingPhone() {

		Map<String, Object> map = RequestMapUtils.getUserInfo(
				ChangePhoneActivity.this, phone.getText().toString(), "4");
		HttpClientRequest.getHttpPost(ChangePhoneActivity.this, map,
				new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						if (ExecuteJSONUtils.simpleUpdateInfo(
								ChangePhoneActivity.this, msg)) {

							SharedPrefConstance.setSharePref(
									ChangePhoneActivity.this,
									SharedPrefConstance.myselfphone, phone
											.getText().toString());
							SharedPrefConstance.setSharePref(
									ChangePhoneActivity.this,
									SharedPrefConstance.username, phone
									.getText().toString());
							finish();
						}
					}

					@Override
					public void onFailure(Exception error, String msg) {

					}
				});
	}
}
