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
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
/**
 * 
 * @FileName:RegiestFinallyActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:RegiestFinallyActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 绑定经纪公司
 */
public class RegiestFinallyActivity extends BaseActivity implements
		OnClickListener {

	private EditText store;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_register_finally);
		setActionBarTitle("绑定经纪公司");
		ExitActivity.addActivity(this);
		store = (EditText) findViewById(R.id.etInvitationCode);

		findViewById(R.id.band_now).setOnClickListener(this);
		findViewById(R.id.band_later).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.band_now:

			String storeMessage = store.getText().toString();
			if (storeMessage != null && storeMessage.length()!=0) {
				settingSelectCompany();
			}

			break;
		case R.id.band_later:

			Intent intent = new Intent();
			intent.setClass(RegiestFinallyActivity.this, MainActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	private void settingSelectCompany() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userSearchCompanyID);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				RegiestFinallyActivity.this, SharedPrefConstance.UUID, ""));
		map.put(Constant.companyID, store.getText().toString());
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				// {status:1,message:"成功",result:{companyname:"北京链家地产"}}
				if (ExecuteJSONUtils.SearchCompany(RegiestFinallyActivity.this,
						msg, store.getText().toString())) {
					showAlertDialog();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	private void showAlertDialog() {
		String companname = SharedPrefConstance.getStringValue(
				RegiestFinallyActivity.this,
				SharedPrefConstance.companynameUpdate, "");
		String message = "[" + store.getText().toString() + "]" + companname;
		AlertDialog.Builder builder = new Builder(this);
		builder.setIcon(R.drawable.icon)
				//
				.setTitle(R.string.app_name)
				//
				.setMessage(message)
				.setPositiveButton("确认",
						new android.content.DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {

								settingCompany();
							}
						}).setNegativeButton("取消", null).show();

	}

	private void settingCompany() {

		if (store.getText().toString().length() < 5) {
			ToastUtils.showToast(this, "绑定经纪公司 至少六位 验证码");
			return;
		}
		Map<String, Object> map = RequestMapUtils.getUserInfo(this, store
				.getText().toString(), "3");
		HttpClientRequest.getHttpPost(RegiestFinallyActivity.this, map,
				new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						if (ExecuteJSONUtils.simpleUpdateInfo(
								RegiestFinallyActivity.this, msg)) {

							String companname = SharedPrefConstance
									.getStringValue(
											RegiestFinallyActivity.this,
											SharedPrefConstance.companynameUpdate,
											"");
							SharedPrefConstance.setSharePref(
									RegiestFinallyActivity.this,
									SharedPrefConstance.companyid, store
											.getText().toString());
							SharedPrefConstance
									.setSharePref(RegiestFinallyActivity.this,
											SharedPrefConstance.companyname,
											companname);
							Intent intent = new Intent();
							intent.setClass(RegiestFinallyActivity.this,
									MainActivity.class);
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
