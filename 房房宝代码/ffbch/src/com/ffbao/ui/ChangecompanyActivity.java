package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RequestMapUtils;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.RoleUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @FileName:ChangecompanyActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ChangecompanyActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 修改绑定经纪公司
 */
public class ChangecompanyActivity extends BaseActivity {

	private EditText store;

	private TextView compandName;

	private String storeCode;

	private View view;

	private View llcurrentcompany;

	private View hotline;

	private RichfitAlertDialog dialog;


	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_setting_store);

		ExitActivity.addActivity(this);
		store = (EditText) findViewById(R.id.ed_store);
		compandName = (TextView) findViewById(R.id.tv_compandName);
		view = findViewById(R.id.del_company);
		hotline = findViewById(R.id.hotline);
		llcurrentcompany = findViewById(R.id.llcurrentcompany);
		ViewContral();

		findViewById(R.id.pubsub_info_message).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (RoleUtils.isManager()) {

							ToastUtils.showToast(context, "您是管理员不能解除绑定");
						} else {
							if (StringUtils.isNull(store.getText().toString())) {
								// TODO 绑定门店吗

								if (store.getText().toString().length() < 5) {
									ToastUtils.showToast(context,
											"绑定经纪公司 至少六位 验证码");
									return;
								}
								storeCode = store.getText().toString();
								settingSelectCompany();

							}else{
								ToastUtils.showToast(context,
										"请输入经纪公司ID");
							}
						}
					}
				});
		findViewById(R.id.del_company).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						if (RoleUtils.isManager()) {
							ToastUtils.showToast(context, "您是管理员不能解除绑定");
						} else {
							// TODO 解绑稳点
							storeCode = "";
							showUnwrappingCompany();
						}
					}
				});

		hotline.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				final String customerservices = SharedPrefConstance.getStringValuePhone(
						context, SharedPrefConstance.customerservices);
				if (customerservices != null && customerservices.length()!=0) {
					if (dialog == null) {
						dialog = new RichfitAlertDialog(context);
					}
					if (!dialog.isShow()) {
						dialog.show();
						dialog.setContent("是否拨打电话:" + customerservices);
						dialog.setPositiveButton("取消", new OnClickListener() {

							@Override
							public void onClick(View v) {

								dialog.close();
							}
						});
						dialog.setNegativeButton("拨打", new OnClickListener() {

							@Override
							public void onClick(View v) {

								Intent intent = new Intent(Intent.ACTION_CALL, Uri
										.parse("tel:" + customerservices));
								startActivity(intent);
								dialog.close();
							}
						});
					}
				}
			}
		});

	}

	private void ViewContral() {
		String company = SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.companyname);
		if (company.equals(APP.independentname)) {
			llcurrentcompany.setVisibility(View.GONE);
			view.setVisibility(View.GONE);
			setActionBarTitle("绑定经纪公司");

		} else {
			setActionBarTitle("修改绑定经纪公司");
			compandName.setText(company);
			view.setVisibility(View.VISIBLE);

		}
	}


	private void settingSelectCompany() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userSearchCompanyID);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				ChangecompanyActivity.this, SharedPrefConstance.UUID, ""));
		map.put(Constant.companyID, storeCode);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (ExecuteJSONUtils.SearchCompany(ChangecompanyActivity.this,
						msg, storeCode)) {
					showAlertDialog();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	private void settingUnwrappingCompany() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userUnbundlingCompany);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				ChangecompanyActivity.this, SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, SharedPrefConstance.getStringValue(
				ChangecompanyActivity.this, SharedPrefConstance.userid, ""));
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (ExecuteJSONUtils.simpleUpdateInfo(context, msg)) {
					SharedPrefConstance.setSharePref(context,
							SharedPrefConstance.companyid, "0");
					SharedPrefConstance.setSharePref(context,
							SharedPrefConstance.companyname, "暂无");
					ToastUtils.showToast(context, "门店解绑成功");
					ViewContral();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	private void showAlertDialog() {
		String companname = SharedPrefConstance.getStringValue(
				ChangecompanyActivity.this,
				SharedPrefConstance.companynameUpdate, "");
		// String message = "[" + store.getText().toString() + "]" +
		// companname;
		String message = companname;
		if(dialog == null){
			dialog = new RichfitAlertDialog(context);
		}
		if(!dialog.isShow()){
			dialog.show();
			dialog.setContent(message);
			dialog.setPositiveButton("取消", new OnClickListener() {

				@Override
				public void onClick(View v) {
					SharedPrefConstance.setSharePref(context,
							SharedPrefConstance.companynameUpdate,
							"暂无");
					dialog.close();
				}
			});
			dialog.setNegativeButton("确认", new OnClickListener() {

				@Override
				public void onClick(View v) {
					settingCompany();
					dialog.close();
				}
			});
		}
	}

	private void showUnwrappingCompany() {

		String message = "确认解绑门店？";
		if(dialog == null){
			dialog = new RichfitAlertDialog(context);
		}
		if(!dialog.isShow()){
			dialog.show();
			dialog.setContent(message);
			dialog.setPositiveButton("取消", new OnClickListener() {

				@Override
				public void onClick(View v) {

					dialog.close();
				}
			});
			dialog.setNegativeButton("确认", new OnClickListener() {

				@Override
				public void onClick(View v) {

					settingUnwrappingCompany();
					dialog.close();
				}
			});
		}
	}


	private void settingCompany() {

		if (storeCode.length()==0 || storeCode.length() < 5) {
			ToastUtils.showToast(this, "绑定经纪公司 至少六位 验证码");
			return;
		}
		Map<String, Object> map = RequestMapUtils.getUserInfo(this, storeCode,
				"3");
		HttpClientRequest.getHttpPost(context, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				if (ExecuteJSONUtils.simpleUpdateInfo(context, msg)) {

					String companname = SharedPrefConstance.getStringValue(
							context, SharedPrefConstance.companynameUpdate, "");
					SharedPrefConstance.setSharePref(context,
							SharedPrefConstance.companyid, storeCode);
					SharedPrefConstance.setSharePref(context,
							SharedPrefConstance.companyname, companname);
					ToastUtils
					.showToast(ChangecompanyActivity.this, "成功绑定经纪公司");
					if (StringUtils.isNull(storeCode)) {
						finish();
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}
}
