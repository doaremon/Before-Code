package com.ffbao.engine.impl;

import java.util.HashMap;
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
import com.ffbao.ui.LoginActivity;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;
import com.google.gson.Gson;
/**
 * 
 * @FileName:GetUserDetailEngine.java
 * @Deprecatred: callback listener  
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:GetUserDetailEngine.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 更新数据
 */
public class GetUserDetailEngine extends BaseEngine {

	public GetUserDetailEngine(Context context) {
		super(context);
	}

	@Override
	public void execute(Map<String, Object> map, CallBackListener callback) {

		if (defualtExecute(map, callback)) {

			HttpClientRequest.getHttpPost(context, map,
					new CallBackListener() {

						@Override
						public void onSuccess(String msg) {

							ExecuteJSONUtils.getUserDetails(context, msg);
						}

						@Override
						public void onFailure(Exception error, String msg) {
							defualtFailure(error, msg);

						}
					});
		}

	}
	
	public void execute() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetDetail);
		map.put(Constant.UUID, sp.getString(SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, sp.getString(SharedPrefConstance.userid, ""));
		if (defualtExecute(map, null)) {
			
			HttpClientRequest.getHttpPost(context, map,
					new CallBackListener() {
				
				@Override
				public void onSuccess(String msg) {
					
					ExecuteJSONUtils.getUserDetails(context, msg);
				}
				
				@Override
				public void onFailure(Exception error, String msg) {
					defualtFailure(error, msg);
					
				}
			});
		}
		
	}

}
