package com.ffbao.engine.impl;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.ffbao.engine.BaseEngine;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
/**
 * 
 * @FileName:RegiestEngine.java
 * @Deprecatred: callback listener
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:RegiestEngine.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: regiest funtino
 */
@Deprecated
public class RegiestEngine extends BaseEngine {

	public RegiestEngine(Context context) {
		super(context);
	}

	@Override
	public void execute(Map<String, Object> map, CallBackListener callback) {
		
		if(defualtExecute(map, callback)){
			HttpClientRequest.getHttpPost(context, map, new CallBackListener() {
				
				@Override
				public void onSuccess(String msg) {
//					{status:1,message:"成功"}{status:4,message:"验证码无效"}
					
					try {
						JSONObject json = new JSONObject(msg);
						if(json.optInt("status") == 1){
							String message = json.optString("message");
							Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
