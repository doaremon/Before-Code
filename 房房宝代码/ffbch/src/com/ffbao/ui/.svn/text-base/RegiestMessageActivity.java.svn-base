package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.engine.impl.PhoneSendCodeEngine;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.BanklistDialogAdapter;
import com.ffbao.util.BankUtil;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.MyListDialog;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.ffbao.util.VerifyUtils;

/**
 * 
 * @FileName:RegiestMessageActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:RegiestMessageActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion:注册界面
 */
public class RegiestMessageActivity extends BaseActivity implements OnClickListener {

	private EditText recomennd;
	private EditText phone;
	private EditText code;
	private EditText name;
	private EditText password;
	private EditText comfir;
	private EditText bank_number;
	private EditText account_name;
	private Handler handler = new Handler();
	private TextView phoneCode;
	private TextView clause;
	private CheckBox checkBoxflag;
	private  MyListDialog listdialog;
	private TextView select_bank;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_register_later);
		setActionBarTitle("注册");
		ExitActivity.addActivity(this);
		recomennd = (EditText) findViewById(R.id.recommend);
		phone = (EditText) findViewById(R.id.activity_change_phone_num);
		code = (EditText) findViewById(R.id.activity_change_phone_code);
		name = (EditText) findViewById(R.id.etName);
		password = (EditText) findViewById(R.id.etPassword);
		bank_number=(EditText)findViewById(R.id.bank_number);
		account_name=(EditText)findViewById(R.id.account_name);
		comfir = (EditText) findViewById(R.id.etPassword_comfir);
		phoneCode = (TextView) findViewById(R.id.activity_change_phone_getcode);
		clause = (TextView) findViewById(R.id.tv_clause);

		select_bank = (TextView) findViewById(R.id.select_bank);	
		select_bank.setOnClickListener(this);

		checkBoxflag = (CheckBox) findViewById(R.id.cb_flag);
		findViewById(R.id.pubsub_info_message).setOnClickListener(this);
		phoneCode.setOnClickListener(this);
		clause.setOnClickListener(this);
		findViewById(R.id.btn_ps_contrl).setOnClickListener(this);
		if (Constant.RegiestVerifyNum > 0) {

			VerifyUtils.setActivityValue(phoneCode, handler, 1);
			phone.setText(SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.regiestPhone));
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btn_ps_contrl:
			//			Intent intent = new Intent();
			//			intent.setClass(RegiestMessageActivity.this, CaptureActivity.class);
			//			startActivityForResult(intent, 0);
			break;
			//阅读条款
		case R.id.tv_clause:
			Intent intentClause = new Intent();
			intentClause.setClass(RegiestMessageActivity.this,
					SettingGuideActivity.class);
			intentClause.putExtra("type", "1");
			startActivity(intentClause);
			break;
			//选择银行
		case R.id.select_bank:
			listdialog=new MyListDialog(RegiestMessageActivity.this);
			BanklistDialogAdapter adapter=new BanklistDialogAdapter(RegiestMessageActivity.this, APP.banklists);
			listdialog.dialog_listview.setAdapter(adapter);
			listdialog.show();
			listdialog.dialog_listview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					listdialog.dismiss();
					select_bank.setText(APP.banklists.get(position));				
				}
			});
			break;
			//获取验证码
		case R.id.activity_change_phone_getcode:
			//首先判断手机号，如果正确则过，错误提示
			if (!StringUtils.isPhone(phone.getText().toString())) {
				ToastUtils.showErrorToast(RegiestMessageActivity.this,
						"手机号输入错误");
				return;
			}
			PhoneSendCodeEngine engine = new PhoneSendCodeEngine(
					RegiestMessageActivity.this);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.commandText, UrlUtil.userSendCheckCode);
			map.put(Constant.phone, phone.getText().toString());
			map.put(Constant.type, UrlUtil.sign_up_mobile);
			if (StringUtils.isPhone(phone.getText().toString()))
				engine.execute(phoneCode, map, null);

			break;
			//确定注册
		case R.id.pubsub_info_message:
			regiestExeute();

			break;
		default:
			break;
		}
	}

	boolean flag = false;
	private RichfitAlertDialog dialog;
	/**
	 * 注册方法
	 */
	private void regiestExeute() {
		showlog("点击注册按钮");
		String number = phone.getText().toString().trim();
		//银行卡号
		String banknumber=bank_number.getText().toString().trim();
		//银行姓名
		String bankname=select_bank.getText().toString().trim();
		//开户人姓名
		String accountname=account_name.getText().toString().trim();
		showlog("banknumber="+banknumber);
		showlog("bankname="+bankname);
		showlog("accountname="+accountname);
		if (!StringUtils.isPhone(number)) {

			ToastUtils.showErrorToast(RegiestMessageActivity.this, "手机号输入错误");
			return;

		} else if (!number.equals(SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.regiestPhone))) {

			ToastUtils.showErrorToast(RegiestMessageActivity.this, "手机号或验证码错误");
			return;

		} else if (!StringUtils.isPhoneCode(RegiestMessageActivity.this, code
				.getText().toString())) {

			ToastUtils.showErrorToast(RegiestMessageActivity.this, "验证码错误");
			return;
		} else if (!StringUtils.isNull(name.getText().toString().trim())) {
			ToastUtils.showErrorToast(RegiestMessageActivity.this, "真实姓名不能为空");
			return;
		} else if (name.getText().toString().trim().length() > 20) {
			ToastUtils
			.showErrorToast(RegiestMessageActivity.this, "真实姓名不能大于20");
			return;
		} else if (!StringUtils
				.isPassword(password.getText().toString().trim())) {
			ToastUtils.showErrorToast(RegiestMessageActivity.this,
					"密码格式错误，请输入6-20位数字或者字母");
			return;
		} else if (!password.getText().toString()
				.equals(comfir.getText().toString().trim())) {
			ToastUtils
			.showErrorToast(RegiestMessageActivity.this, "密码和确认密码不一致");
			return;
		} else if(!decide(accountname, banknumber, bankname)){
			return;
		}
		else if (!checkBoxflag.isChecked()) {
			ToastUtils.showErrorToast(RegiestMessageActivity.this, "请您同意以上条款");
			return;
		} else if (!StringUtils.isPhone(recomennd.getText().toString().trim())) {
			if (recomennd.getText().toString().trim() != null
					&& recomennd.getText().toString().trim().length()==0) {

				regiestEngineExecute();
				return;
			} else {
				// flag = true;
				ToastUtils.showErrorToast(RegiestMessageActivity.this,
						"推荐人手机号码格式错误");
				return;
			}
		}

//		VerifyVIP();
	}
	public  Boolean decide(String name,String number,String bankname){
		if(!"".equals(name) && !"".equals(number) && !"".equals(bankname)){
			if(BankUtil.checkBankCard(number)){
				return true;
			}else {
				ToastUtils.showErrorToast(RegiestMessageActivity.this, "您输入的银行卡有误");
				return false;
			}
		
		}
		if("".equals(name)){
			if(!"".equals(bankname) || !"".equals(number)){
				ToastUtils.showErrorToast(RegiestMessageActivity.this, "请完善银行卡信息");
				return false ;
			}
		}
		if("".equals(bankname)){
			if(!"".equals(name) || !"".equals(number)){
				ToastUtils.showErrorToast(RegiestMessageActivity.this, "请完善银行卡信息");
				return false ;
			}
		}
		if("".equals(number)){
			if(!"".equals(bankname) || !"".equals(name)){
				ToastUtils.showErrorToast(RegiestMessageActivity.this, "请完善银行卡信息");
				return false ;
			}
		}
		if("".equals(number) && "".equals(bankname) && "".equals(name)){
			return true;
		}
		return true;
	}

	private void VerifyVIP() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userCheckVIP);
		map.put(Constant.phone, recomennd.getText().toString());
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				try {

					String recomennded = recomennd.getText().toString();
					if (ExecuteJSONUtils.simpleUpdateInfo(context, msg)) {
						JSONObject json = new JSONObject(msg);
						String vipValue = json.getJSONObject("result")
								.getString("vip");
						if ("1".equals(vipValue)) {

							String message = json.getJSONObject("result")
									.getString("vipname");
							VIPMessage("推荐人员: " + message);
						} else {

							VIPMessage(recomennded + ",此人不能推荐。");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}
	private void VIPMessage(String message) {
		if (dialog == null)
			dialog = new RichfitAlertDialog(context);
		if (!dialog.isShow()) {
			dialog.show();
			dialog.setContent(message);
			dialog.setNegativeButton("确认", new OnClickListener() {
				@Override
				public void onClick(View v) {
					regiestEngineExecute();
					dialog.close();
				}
			});
			dialog.setPositiveButton("取消", new OnClickListener() {

				@Override
				public void onClick(View v) {
					dialog.close();
				}
			});
			dialog.setTitle("提示");
		}
	}
	/**
	 * 注册发送请求
	 */
	private void regiestEngineExecute() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userRegister);
		map.put(Constant.phone, phone.getText().toString());
		map.put(Constant.username, name.getText().toString());
		map.put(Constant.password, password.getText().toString());
		map.put(Constant.checkcode, code.getText().toString());
		map.put(Constant.referencephone, recomennd.getText().toString());
		map.put("openaccountname", account_name.getText().toString().trim());
		map.put("bankname", select_bank.getText().toString().trim());
		map.put("banknumber", bank_number.getText().toString().trim());
		showlog("发送注册请求map="+map.toString());
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				// {status:1,message:"成功"}{status:4,message:"验证码无效"}
				showlog("接收注册请求msg="+msg);
				try {
					JSONObject json = new JSONObject(msg);
					if (json.optInt("status") == 1) {
						ToastUtils.showToast(context, "注册成功");
						SharedPrefConstance.setSharePref(context,
								SharedPrefConstance.username, phone.getText()
								.toString());
						SharedPrefConstance.setSharePref(context,
								SharedPrefConstance.password, password
								.getText().toString());
						login();
					} else {
						String message = json.optString("message");
						Toast.makeText(RegiestMessageActivity.this, message,
								Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	private void login() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userLogin);
		map.put(Constant.account, phone.getText().toString());
		map.put(Constant.password, password.getText().toString());
		map.put(Constant.clientIdentity, "Android");
		HttpClientRequest.getHttpPost(RegiestMessageActivity.this, map,
				new CallBackListener() {
			@Override
			public void onSuccess(String msg) {

				try {
					JSONObject json = new JSONObject(msg);
					int status = json.optInt("status");
					if (status == 1) {

						SharedPrefConstance.setSharePref(
								RegiestMessageActivity.this,

								SharedPrefConstance.UUID, json
								.getJSONObject("result")
								.optString("UUID"));

						SharedPrefConstance.setSharePref(
								RegiestMessageActivity.this,
								SharedPrefConstance.userid, json
								.getJSONObject("result")
								.optString("userid"));

						getUserDetail();
					} else {
						Toast.makeText(RegiestMessageActivity.this,
								json.optString("message"),
								Toast.LENGTH_LONG).show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	public void getUserDetail() {

		SharedPreferences sp = RegiestMessageActivity.this
				.getSharedPreferences("user", Context.MODE_PRIVATE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetDetail);
		map.put(Constant.userid, sp.getString(SharedPrefConstance.userid, ""));
		map.put(Constant.UUID, sp.getString(SharedPrefConstance.UUID, ""));
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				ExecuteJSONUtils.getUserDetails(RegiestMessageActivity.this,
						msg);
				Intent it = new Intent();
				setResult(100, it);
				finish();
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		super.onActivityResult(requestCode, responseCode, intent);
		if (requestCode == 0) {
			if (intent != null) {
				String string = intent.getExtras().getString("result");
				Toast.makeText(RegiestMessageActivity.this, string,
						Toast.LENGTH_LONG).show();
				recomennd.setText(string);
			}
		}
	}

}
