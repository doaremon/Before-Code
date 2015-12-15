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

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
/**
 * 
 * @FileName:ChangeNameActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ChangeNameActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 修改姓名
 */
public class ChangeNameActivity extends BaseActivity {

	private EditText name;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_change_name);
		setActionBarTitle("修改姓名");
		ExitActivity.addActivity(this);
		name = (EditText) findViewById(R.id.activity_change_new_name);

		String stringValue = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.myselfname, "");
		name.setHint(stringValue);
		getUpdateUser();
	}

	private void getUpdateUser() {
		findViewById(R.id.activity_change_company_commit).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						String username = name.getText().toString().trim();
						if (!StringUtils.isNull(username)) {

							ToastUtils.showToast(ChangeNameActivity.this,
									"修改姓名不能为空");
							return;
						}
						
						if (username.length() > 20) {
							
							ToastUtils.showToast(ChangeNameActivity.this,
									"修改姓名过长");
							return;
						}
						
						Map<String, Object> map = RequestMapUtils.getUserInfo(
								ChangeNameActivity.this, username, "1");

						HttpClientRequest.getHttpPost(ChangeNameActivity.this,
								map, new CallBackListener() {

									@Override
									public void onSuccess(String msg) {
										if (ExecuteJSONUtils.simpleUpdateInfo(
												ChangeNameActivity.this, msg)) {

											SharedPrefConstance
													.setSharePref(
															ChangeNameActivity.this,
															SharedPrefConstance.myselfname,
															name.getText()
																	.toString());
											finish();
										}
									}

									@Override
									public void onFailure(Exception error,
											String msg) {

									}
								});
					}

				});
	}
}
