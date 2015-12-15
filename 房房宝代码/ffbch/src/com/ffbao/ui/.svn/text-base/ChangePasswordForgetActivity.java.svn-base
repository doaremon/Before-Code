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
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * 
 * @FileName:ChangePasswordForgetActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ChangePasswordForgetActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 找回密码
 */
public class ChangePasswordForgetActivity extends BaseActivity {

	private EditText newPWD;
	private EditText comfir;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_change_pwd_forget);
		setActionBarTitle("找回密码");
		ExitActivity.addActivity(this);
		newPWD = (EditText) findViewById(R.id.activity_change_pwd_new);
		comfir = (EditText) findViewById(R.id.activity_change_pwd_new1);

		findViewById(R.id.activity_change_pwd_commit).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						
						if (!StringUtils
								.isPassword(newPWD.getText().toString())) {
							ToastUtils.showErrorToast(
									ChangePasswordForgetActivity.this,
									"请输入6-20位数字或字母");
							return;
						} else if (!newPWD.getText().toString()
								.equals(comfir.getText().toString())) {
							ToastUtils.showErrorToast(
									ChangePasswordForgetActivity.this,
									"新密码和确认密码不一致");
							return;
						}
						forgetPasswordForgetExeuct();
					}
				});
	}

	private void forgetPasswordForgetExeuct() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userForgetPassword);
		map.put(Constant.phone, SharedPrefConstance.getStringValue(this, SharedPrefConstance.forgetPhone));
		map.put(Constant.newpassword,newPWD.getText().toString());
		HttpClientRequest.getHttpPost(ChangePasswordForgetActivity.this, map,
				new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						if (ExecuteJSONUtils.simpleUpdateInfo(
								ChangePasswordForgetActivity.this, msg)) {

							Intent intent = new Intent();
							intent.setClass(ChangePasswordForgetActivity.this,
									LoginActivity.class);
							startActivity(intent);
							finish();
						}
					}

					@Override
					public void onFailure(Exception error, String msg) {

					}
				});
	}
}
