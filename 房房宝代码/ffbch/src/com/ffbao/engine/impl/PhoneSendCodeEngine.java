package com.ffbao.engine.impl;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.ffbao.engine.BaseEngine;
import com.ffbao.entity.User;
import com.ffbao.exception.HttpException;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;
import com.ffbao.util.VerifyUtils;
import com.google.gson.Gson;

/**
 * 
 * @FileName:PhoneSendCodeEngine.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:PhoneSendCodeEngine.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: send phone verfiy number ,inculde regiest funtion,forget funtion,change phone funtion
 */
public class PhoneSendCodeEngine extends BaseEngine {

	public PhoneSendCodeEngine(Context context) {

		super(context);

	}

	@Override
	public void execute(final TextView phoneCode, Map<String, Object> map1,
			CallBackListener callback) {
		if (Constant.verifyNum == 0) {

			if (map1 == null) {
				map1 = new HashMap<String, Object>();
			}
			final Map<String, Object> map = map1;
			if (callback != null) {
				HttpClientRequest.getHttpPost(context, map, callback);
			} else {
				HttpClientRequest.getHttpPost(context, map,
						new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
                           Log.i("chenghao", "返回的验证码为="+msg);
						// {status:1,message:"成功",result:{checkcode:585858}}
						// {status:3,message:"网络繁忙，稍后再试",result:{checkcode:}}
						try {
							String smswaittime = SharedPrefConstance.getStringValuePhone(context, SharedPrefConstance.smswaittime);
							int number = 120;
							if(smswaittime!=null && smswaittime.length()!=0){

								number = Integer.parseInt(smswaittime);
							}
							if (msg != null && msg.length()!=0) {
								JSONObject json = new JSONObject(msg);
								int status = json.optInt("status");
								if (status == 1) {
									if(map.get(Constant.type).equals(UrlUtil.sign_up_mobile)){
										SharedPrefConstance
										.setSharePref(
												context,
												SharedPrefConstance.regiestPhoneCode,
												json.getJSONObject(
														"result")
														.optString(
																"checkcode"));
										SharedPrefConstance
										.setSharePref(
												context,
												SharedPrefConstance.regiestPhone,
												map.get("phone")
												.toString());
										Constant.RegiestVerifyNum = number;
										VerifyUtils.counter(Integer.parseInt(UrlUtil.sign_up_mobile));
										VerifyUtils.setActivityValue(phoneCode, handler,Integer.parseInt(UrlUtil.sign_up_mobile));

									}else if(map.get(Constant.type).equals(UrlUtil.modify_the_phone)){
										SharedPrefConstance
										.setSharePref(context,
												SharedPrefConstance.changePhone,
												map.get("phone")
												.toString());
										SharedPrefConstance
										.setSharePref(
												context,
												SharedPrefConstance.changePhoneCode,
												json.getJSONObject(
														"result")
														.optString(
																"checkcode"));

										Constant.changVerifyNum = number;
										VerifyUtils.counter(Integer.parseInt(UrlUtil.modify_the_phone));
										VerifyUtils.setActivityValue(phoneCode, handler,Integer.parseInt(UrlUtil.modify_the_phone));
									}else if(map.get(Constant.type).equals(UrlUtil.forget_password)){
										SharedPrefConstance
										.setSharePref(
												context,
												SharedPrefConstance.forgetPhone,
												map.get("phone")
												.toString());
										SharedPrefConstance
										.setSharePref(
												context,
												SharedPrefConstance.forgettPhoneCode,
												json.getJSONObject(
														"result")
														.optString(
																"checkcode"));

										Constant.forgetVerifyNum = number;
										VerifyUtils.counter(Integer.parseInt(UrlUtil.forget_password));
										VerifyUtils.setActivityValue(phoneCode, handler,Integer.parseInt(UrlUtil.forget_password));
									}


								} else {
									Toast.makeText(context,
											json.optString("message"),
											Toast.LENGTH_LONG).show();
								}
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					@Override
					public void onFailure(Exception error, String msg) {

						if (error instanceof HttpException) {
							Toast.makeText(context, msg,
									Toast.LENGTH_LONG).show();
						} else {
							Toast.makeText(context, msg,
									Toast.LENGTH_LONG).show();
						}
					}
				});
			}
		}
	}
}
