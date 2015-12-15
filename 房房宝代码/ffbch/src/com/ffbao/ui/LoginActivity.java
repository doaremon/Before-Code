package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 
 * @FileName:LoginActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:LoginActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 登录界面
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText username;
	private EditText password;
	private View view;

	boolean flag = false;

	private  static int  requestResgiest = 0 ;
	private int forward = -1;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_login);

		setActionBarTitle("登录");
		setActionBarOther("注 册");
		ExitActivity.addActivity(this);
		username = (EditText) findViewById(R.id.etUsername);
		password = (EditText) findViewById(R.id.etPassword);
		view =  findViewById(R.id.editting_name_tag);

		findViewById(R.id.registtext).setOnClickListener(this);
		findViewById(R.id.password_forget).setOnClickListener(this);
		getActionBarOther().setOnClickListener(this);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if(extras != null){
			forward = extras.getInt(Constant.forward,-1);
			forwards();
		}

	}

	private boolean forwards() {
		if(forward == 1){

			findViewById(R.id.rl_back_actionbar).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					Intent intent = new Intent(context, MainActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);

					ExitActivity.exit();
				}
			});

			return true;
		}
		return false;
	}

	@Override
	protected void onResume() {
		super.onResume();
		view.setFocusable(true);
		view.setFocusableInTouchMode(true);
		view.requestFocus();
		view.requestFocusFromTouch();
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				this.exitApp();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	private long exitTime = 0;
	/**
	 * 退出程序
	 */
	private void exitApp() {
		if(forwards()){
			// 判断2次点击事件时间
			if ((System.currentTimeMillis() - exitTime) > 2000) {

				Toast.makeText(context, "再按一次退出程序", Toast.LENGTH_SHORT)
				.show();
				exitTime = System.currentTimeMillis();
			} else {

				ExitActivity.exit();
			}
		}else{

			finish();
		}
	}


	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.registtext:
			//			if (!flag) {
			//				flag = true;
			login();
			//			}
			break;
		case R.id.password_forget:

			intent.setClass(LoginActivity.this, RegiestActivity.class);
			LoginActivity.this.startActivity(intent);
			break;
		case R.id.tv_operater_others_btn:

			intent.setClass(LoginActivity.this, RegiestMessageActivity.class);
			//			LoginActivity.this.startActivity(intent);
			startActivityForResult(intent, requestResgiest);
			break;
		default:
			break;
		}

	}
	

	@Override
	protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
		super.onActivityResult(requestCode, responseCode, intent);
		if(requestCode == requestResgiest){
			if(responseCode == 100){
				Intent startActivity = new Intent(context,ChangecompanyActivity.class);
				startActivity(startActivity);
				finish();
			}
		}

	}


	private void login() {

		if (!StringUtils.isPhone(username.getText().toString())) {
			ToastUtils.showToast(this, "手机号输入错误");
			flag = false;
			return;
		}
		if (!StringUtils.isPassword(password.getText().toString())) {
			ToastUtils.showToast(this, "密码格式错误");
			flag = false;
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userLogin);
		map.put(Constant.account, username.getText().toString());
		map.put(Constant.password, password.getText().toString());
		map.put(Constant.clientIdentity, "Android");
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (ExecuteJSONUtils.login(LoginActivity.this, msg)) {
					getUserDetail();
					//					SharedPrefConstance.setEncoder(LoginActivity.this, password
					//							.getText().toString());

					verifyUpdate();
					SharedPrefConstance.setSharePref(LoginActivity.this,
							SharedPrefConstance.username, username.getText()
							.toString());
					SharedPrefConstance.setSharePref(LoginActivity.this,
							SharedPrefConstance.password, password.getText()
							.toString());
					String first = SharedPrefConstance.getStringValue(context,
							SharedPrefConstance.frist);
					if (first.length()==0) {

						callUserPushDetails();
					}
				}
				flag = false;
			}

			@Override
			public void onFailure(Exception error, String msg) {
				flag = false;
			}
		});

	}

	public void getUserDetail() {

		SharedPreferences sp = LoginActivity.this.getSharedPreferences("user",
				Context.MODE_PRIVATE);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetDetail);
		map.put(Constant.UUID, sp.getString(SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, sp.getString(SharedPrefConstance.userid, ""));
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				Log.i("chenghao", "登录返回值="+msg);
				
				if (ExecuteJSONUtils.getUserDetails(LoginActivity.this, msg)) {


					//					Intent intent = new Intent();
					//					intent.setClass(LoginActivity.this, MainActivity.class);
					//					startActivity(intent);
					DisplayUtils.setUnRefresh();
					if(forward == 0 || forward == 1){

						finish();
					}else{
						Intent intent = new Intent(context, MainActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						context.startActivity(intent);

						//退出
						ExitActivity.exit();
					}
				}

			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	//	@Override
	//	public void onBackPressed() {
	//		super.onBackPressed();
	//
	//		backState();
	//	}
	//
	//	private void backState() {
	//		finish();
	//		LoginActivity.this.startActivity(new Intent(LoginActivity.this,
	//				MainActivity.class));
	//		overridePendingTransition(R.anim.slide_left_in1, R.anim.slide_right_in1);
	//	}

	private void callUserPushDetails() {

		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);

			Map<String, Object> map = new HashMap<String, Object>();
			DisplayMetrics dm = new DisplayMetrics();
			android.view.Display display = getWindowManager()
					.getDefaultDisplay();
			display.getMetrics(dm);
			int screen_w = dm.widthPixels;
			int screen_h = dm.heightPixels;
			WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			// 判断wifi是否开启
			if (!wifiManager.isWifiEnabled()) {
				wifiManager.setWifiEnabled(true);
			}
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			int ipAddress = wifiInfo.getIpAddress();
			TelephonyManager telephonyManager = (TelephonyManager) this
					.getSystemService(Context.TELEPHONY_SERVICE);
			String imei = telephonyManager.getDeviceId();
			String te1 = telephonyManager.getLine1Number();// 获取本机号码
			String imsi = telephonyManager.getSubscriberId();// 得到用户Id
			String value = "暂无";
			String userid = SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.userid);
			String UUID = SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.UUID);
			if (StringUtils.isNull(userid)) {

				map.put(Constant.userid, userid);
			} else {

				map.put(Constant.userid, value);
			}

			// map.put(Constant.UUID, UUID);
			if (StringUtils.isNull(UUID)) {

				map.put(Constant.UUID, UUID);
			} else {

				map.put(Constant.UUID, value);
			}
			map.put(Constant.clientidentity, "Android");
			map.put(Constant.systemversion, "Android "
					+ android.os.Build.VERSION.SDK_INT);
			String versionName = packInfo.versionName;
			// map.put(Constant.clientVersion, packInfo.versionName);
			if (StringUtils.isNull(versionName)) {

				map.put(Constant.clientversion, versionName);
			} else {

				map.put(Constant.clientversion, value);
			}

			map.put(Constant.clientvga, screen_w + " * " + screen_h);

			if (StringUtils.isNull(userid)) {

				map.put(Constant.clientvga, userid);
			} else {

				map.put(Constant.clientvga, value);
			}
			String manufacturer = android.os.Build.MANUFACTURER + " "
					+ android.os.Build.MODEL;
			if (StringUtils.isNull(manufacturer)) {

				map.put(Constant.manufacturer, manufacturer);
			} else {

				map.put(Constant.manufacturer, value);
			}

			// map.put(Constant.clientip, ipAddress);
			String address = String.valueOf(ipAddress);
			if (StringUtils.isNull(address)) {

				map.put(Constant.clientip, address);
			} else {

				map.put(Constant.clientip, value);
			}

			// map.put(Constant.clientIMEI, imei);
			if (StringUtils.isNull(imei)) {

				map.put(Constant.clientIMEI, imei);
			} else {

				map.put(Constant.clientIMEI, value);
			}

			// map.put(Constant.clientPhone, te1);
			if (StringUtils.isNull(te1)) {

				map.put(Constant.clientPhone, te1);
			} else {

				map.put(Constant.clientPhone, value);
			}

			map.put(Constant.extend1, value);
			map.put(Constant.extend2, value);
			map.put(Constant.extend3, value);
			map.put(Constant.extend4, value);
			map.put(Constant.extend5, value);
			map.put(Constant.commandText, UrlUtil.userPushDetails);
			HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

				@Override
				public void onSuccess(String msg) {
					if(ExecuteJSONUtils.simpleUpdateInfo(context, msg)){
						SharedPrefConstance.setSharePrefPhone(context,
								SharedPrefConstance.frist, "frist");
					}
				}

				@Override
				public void onFailure(Exception error, String msg) {

				}
			});

		} catch (NameNotFoundException e) {

			e.printStackTrace();
		}
	}
}
