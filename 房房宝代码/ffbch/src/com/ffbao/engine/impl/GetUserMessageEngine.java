package com.ffbao.engine.impl;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.ffbao.engine.BaseEngine;
import com.ffbao.entity.User;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.SharedPrefConstance;
import com.google.gson.Gson;
/**
 * 
 * @FileName:GetUserMessageEngine.java
 * @Deprecatred: callback listener  
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:GetUserMessageEngine.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: get user message
 */
@Deprecated
public class GetUserMessageEngine extends BaseEngine {

	public GetUserMessageEngine(Context context) {
		super(context);
	}

	@Override
	public void execute(Map<String, Object> map, CallBackListener callback) {

		if (defualtExecute(map, callback)) {

			HttpClientRequest.getHttpPost(context, map,
					new CallBackListener() {

						@Override
						public void onSuccess(String msg) {

//							{status:1,
//							message:"成功",
//							total:2,
//							result:{
//							{messageid:1,messagecontent:"客户吴大力已经订好机票"},
//							{messageid:2,messagecontent:"客户吴大力已经订好酒店"}}}
							// 此处应该有界面去处理
//							try {
//								JSONObject json = new JSONObject(msg);
//								int status = json.optInt("status");
//								if (status == 1) {
//									Editor edit = sp.edit();
//									Gson gson = new Gson();
//									User user = gson.fromJson(json.getJSONObject("result").toString(), User.class);
//									
//									edit.putString(SharedPrefConstance.servicephone,
//											user.getServicephone());
//									edit.putString(SharedPrefConstance.servicename,
//											user.getServicename());
//									edit.putString(SharedPrefConstance.tdbarcode_path,
//											user.getTdbarcode_path());
//									edit.putString(SharedPrefConstance.ffb_tdbarcode_path,
//											user.getFfb_tdbarcode_path());
//									edit.putString(SharedPrefConstance.shareGuideUrl,
//											user.getShareGuideUrl());
//									edit.putString(SharedPrefConstance.myselfimg,
//											user.getMyselfimg());
//									edit.putString(SharedPrefConstance.companyname,
//											user.getCompanyname());
//									edit.commit();
//								} else {
//									Toast.makeText(context,
//											json.optString("message"),
//											Toast.LENGTH_LONG).show();
//								}
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
						}

						@Override
						public void onFailure(Exception error, String msg) {
							defualtFailure(error, msg);

						}
					});
		}

	}

}
