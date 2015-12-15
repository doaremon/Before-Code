package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
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
 * @FileName:ChangePWDActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ChangePWDActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 修改密码
 */
public class ChangePWDActivity extends BaseActivity {

	private EditText oldPWD;
	private EditText newPWD;
	private EditText ComfirPWD;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_change_pwd);
		setActionBarTitle("修改密码");
		ExitActivity.addActivity(this);
		oldPWD = (EditText) findViewById(R.id.activity_change_pwd_old);
		newPWD = (EditText) findViewById(R.id.activity_change_pwd_new);
		ComfirPWD = (EditText) findViewById(R.id.activity_change_pwd_new1);

		findViewById(R.id.activity_change_pwd_commit).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (!StringUtils.isNull(oldPWD.getText().toString()
								.trim())) {
							ToastUtils.showToast(ChangePWDActivity.this,
									"当前密码不能为空");
							return;
						} else if (!StringUtils.isPassword(newPWD.getText()
								.toString().trim())) {
							ToastUtils.showToast(ChangePWDActivity.this,
									"新密码格式错误");
							return;
						} else if (oldPWD.getText().toString()
								.equals(newPWD.getText().toString())) {
							ToastUtils.showToast(ChangePWDActivity.this,
									"当前密码和新密码不能相同");
							return;

						} else if (!newPWD.getText().toString()
								.equals(ComfirPWD.getText().toString())) {
							ToastUtils.showToast(ChangePWDActivity.this,
									"新密码和确认密码不相同");
							return;
						}
						settingPWD();

					}
				});
	}

	private void settingPWD() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userUpdatePassword);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				ChangePWDActivity.this, SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, SharedPrefConstance.getStringValue(
				ChangePWDActivity.this, SharedPrefConstance.userid, ""));
		map.put(Constant.oldpassword, oldPWD.getText().toString());
		map.put(Constant.newpassword, newPWD.getText().toString());

		HttpClientRequest.getHttpPost(ChangePWDActivity.this, map,
				new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						if (ExecuteJSONUtils.simpleUpdateInfo(
								ChangePWDActivity.this, msg)) {

							finish();
							Intent intent = new Intent();
							intent.setClass(ChangePWDActivity.this,
									LoginActivity.class);
							ChangePWDActivity.this.startActivity(intent);
							SharedPrefConstance.setSharePref(context,
									SharedPrefConstance.password, newPWD
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
