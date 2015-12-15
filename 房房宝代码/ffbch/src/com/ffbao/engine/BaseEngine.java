package com.ffbao.engine;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.exception.HttpException;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @FileName:BaseEngine.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:BaseEngine.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: extracting the common part of package
 */
public abstract class BaseEngine {

	protected Context context;
	protected SharedPreferences sp;
	protected Handler handler;

	public BaseEngine(Context context) {

		this.context = context;
		sp = context
				.getSharedPreferences("user",
						Context.MODE_PRIVATE);
		handler = new Handler();
	}

	public  void execute(Map<String, Object> map,
			CallBackListener callback){}
	
	public  void execute(TextView view,Map<String, Object> map,
			CallBackListener callback){}

	public boolean defualtExecute(Map<String, Object> map,
			CallBackListener callback) {
		if (map == null) {
			map = new HashMap<String, Object>();
		}
		if (callback != null) {
			HttpClientRequest.getHttpPost(context, map, callback );
		} else {
			return true;
		}

		return false;
	}

	public void defualtFailure(Exception error, String msg) {
		if (error instanceof HttpException) {
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}
	}
	
	
	public void  executeSuccess(){
		
	}
}
