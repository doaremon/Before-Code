package com.ffbao.engine.impl;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.ffbao.engine.BaseEngine;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.SharedPrefConstance;
/**
 * 
 * @FileName:RegiestCompany.java
 * @Deprecatred: callback listener
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:RegiestCompany.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion:  change company funtion
 */
@Deprecated
public class RegiestCompany extends BaseEngine {

	public RegiestCompany(Context context) {
		super(context);
	}

	@Override
	public void execute(Map<String, Object> map, CallBackListener callback) {

		if(defualtExecute(map, callback)){
			HttpClientRequest.getHttpPost(context, map, new CallBackListener() {
				
				@Override
				public void onSuccess(String msg) {
//					{status:1,message:"成功",result:{companyname:"北京链家地产"}}
					
					SharedPreferences sp = context
							.getSharedPreferences("user",
									Context.MODE_PRIVATE);
					try {
						JSONObject json = new JSONObject(msg);
						if(json.optInt("status") == 1){
							Editor edit = sp.edit();
							edit.putString(
									SharedPrefConstance.UUID,
									json.getJSONObject("result")
											.optString("UUID"));
							edit.putString(
									SharedPrefConstance.companyname,
									json.getJSONObject("result")
									.optString("companyname"));
						}else{
							String message = json.optString("message");
							Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void onFailure(Exception error, String msg) {
					defualtFailure(error, msg);
				}
			});
		}
	}

}
