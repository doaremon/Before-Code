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
 * @FileName:UpdateInfoEngine.java
 * @Deprecatred: callback listener
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:UpdateInfoEngine.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: update user information operation Funtion
 */
@Deprecated
public class UpdateInfoEngine extends BaseEngine {

	public UpdateInfoEngine(Context context) {
		super(context);

	}

	@Override
	public void execute(Map<String, Object> map, CallBackListener callback) {

		if (defualtExecute(map, callback)) {

			HttpClientRequest.getHttpPost(context, map ,
					new CallBackListener() {

						@Override
						public void onSuccess(String msg) {

							// {status:1,message:"成功"}
							try {
								JSONObject json = new JSONObject(msg);
								int status = json.optInt("status");
								if (status == 1) {
									Toast.makeText(context,
											json.optString("message"),
											Toast.LENGTH_LONG).show();
								} else {
									Toast.makeText(context,
											json.optString("message"),
											Toast.LENGTH_LONG).show();
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
