/**
 * Project Name:房房宝
 * File Name:HttpGetClient.java
 * Package Name:com.ffbao.update
 * Date:2014-9-15下午5:56:49
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
 */

package com.ffbao.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.exception.HttpException;
import com.ffbao.ui.BaseActivity;
import com.ffbao.ui.LoginActivity;
import com.ffbao.ui.MainActivity;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.MyProgress;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

/**
 * ClassName:HttpGetClient
 * 
 * Function: TODO ADD FUNCTION
 * 
 * Date: 2014-9-15 下午5:56:49
 * 
 * @author apple
 * @version
 * @since JDK 1.7
 * @see Http Get请求工具类
 */
public class HttpClientRequest {

	public static final String TAG = HttpClientRequest.class.getSimpleName();

	// protected Handler handler = new Handler();
	/**
	 * 网络访问 工具类
	 * 
	 * @param map
	 *            commandText=userUpdatePersionImg 对应参数参数信息
	 * @return
	 */
	static Handler handler;

	private static String histroyActivity = "";
//	private static AlertDialog create = null ;

	public static void getHttpPost(Context context, Map<String, Object> map,
			CallBackListener callBack) {
		if (handler == null) {
			handler = new Handler();
		}

		
		ExecutorService executor = APP.getInstance().getExecutor();
		if (checkNetworkStatus(context)) {
			ActivityManager actMr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			String currentActivity = actMr.getRunningTasks(1).get(0).topActivity.getClassName();
			if(!histroyActivity.equals(currentActivity)){
				
				MyProgress.getInstance().dismissDialog();
			}
			if(UrlUtil.userGetWantCitys.equals(map.get(Constant.commandText))){
			}else if(UrlUtil.userGetWantBuildings.equals(map.get(Constant.commandText))){
			}else if(UrlUtil.userPushErrors.equals(map.get(Constant.commandText))){
			}else if(UrlUtil.userGetOtherMessages.equals(map.get(Constant.commandText))){
			}else if(UrlUtil.userLogout.equals(map.get(Constant.commandText))){
			}else if(UrlUtil.userLoginUpdateVersion.equals(map.get(Constant.commandText))){
			}else if(UrlUtil.userUpdateVersion.equals(map.get(Constant.commandText))){
			}else if(UrlUtil.userGetMessagesCount.equals(map.get(Constant.commandText))){
			}else{
				MyProgress.getInstance().getProgressDialogShow(context);
			}
			executor.submit(new Task(map, callBack, context, handler));
			histroyActivity = currentActivity;
		}else{
			if(UrlUtil.userLogout.equals(map.get(Constant.commandText))){
//				if(create != null){
//				create.dismiss();
//				}
			}
		}
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param context application context
	 * @return  network flag 
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:network connection
	 */
	public static boolean checkNetworkStatus(final Context context) {
		boolean netSataus = false;
		ConnectivityManager cwjManager = (ConnectivityManager) ((Activity) context)
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		cwjManager.getActiveNetworkInfo();
		if (cwjManager.getActiveNetworkInfo() != null) {
			netSataus = cwjManager.getActiveNetworkInfo().isAvailable();
		}
		if (!netSataus) {
//			boolean showing = b.isShowing();
//			if(create==null ||  !create.isShowing()){
//				
//			Builder b = new AlertDialog.Builder(context).setTitle(
//					R.string.dialog_title_no_network).setMessage(
//					R.string.dialog_no_network_msg);
//			b.setPositiveButton(R.string.btn_confirm,
//					new DialogInterface.OnClickListener() {
//						public void onClick(DialogInterface dialog,
//								int whichButton) {
//							if (android.os.Build.VERSION.SDK_INT > 10) {
//								context.startActivity(new Intent(
//										android.provider.Settings.ACTION_SETTINGS));
//							} else {
//								context.startActivity(new Intent(
//										android.provider.Settings.ACTION_WIRELESS_SETTINGS));
//							}
//							dialog.dismiss();
//						}
//					})
//					.setNeutralButton(R.string.btn_cancel,
//							new DialogInterface.OnClickListener() {
//								public void onClick(DialogInterface dialog,
//										int whichButton) {
//									dialog.dismiss();
//								}
//							});
//				create = b.create();
//				create.show();
//			}else{
//				create.dismiss();
//				create.show();
//			}
			ToastUtils.showToast(context, "网络连接失败");
		}
		return netSataus;
	}
}

/**
 * 
 * @FileName:HttpClientRequest.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:HttpClientRequest.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: ExecutorService call Task
 */
class Task implements Runnable {

	private Map<String, Object> map;
	CallBackListener callBack;
	Context context;
	Handler handler;

	private long startTime = 0 ; 
	private int count = 0;
	
	private static int exceptionState = 0;
	private static int requestCode200 = 1;
	private static int requestCode500 = 2;
	private static int success = 3;
	private static int JSONState = 4;
	
	public Task(Map<String, Object> map, CallBackListener callBack,
			Context context, Handler handler) {
		this.map = map;
		this.callBack = callBack;
		this.context = context;
		this.handler = handler;
		
		startTime = new Date().getTime();
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @return login  flag success true and  false 
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: back login funtion
	 */
	private boolean login(final Context context) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userLogin);
		map.put(Constant.account, SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.username));
		map.put(Constant.password, SharedPrefConstance.getStringValue(context, SharedPrefConstance.password));
		map.put(Constant.clientIdentity, "Android");
		if(map.get(Constant.account)!=null &&map.get(Constant.account).toString().length()!=0&& Constant.VisitCount >count){
			while (!run(map) && !getUserDetail(context)) {
				if(!(Constant.VisitCount >count)){
					LoginOperation(context);
					return false;
				}
					count++;
					
			} 
			return true;
		}else{
			LoginOperation(context);
		}
		return false;
	}

	private void LoginOperation(final Context context) {
		
		
		handler.post(new Runnable() {

			@Override
			public void run() {

				ActivityManager actMr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
				String currentActivity = actMr.getRunningTasks(1).get(0).topActivity.getClassName();
				if(!LoginActivity.class.getName().equals(currentActivity)){
					Intent intent = new Intent();
					intent.setClass(context, LoginActivity.class);
					Bundle extras = new Bundle();
					extras.putInt(Constant.forward, 3);
					intent.putExtras(extras );
					context.startActivity(intent);
				}
			}
		});
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: get user detail 
	 */
	public boolean getUserDetail(final Context context) {

		SharedPreferences sp = context.getSharedPreferences("user",
				Context.MODE_PRIVATE);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetDetail);
		map.put(Constant.UUID, sp.getString(SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, sp.getString(SharedPrefConstance.userid, ""));
		if (run(map)) {
			return true;
		}
		return false;
	}

	@Override
	public void run() {

//		DefaultHttpClient createHttpClient = createHttpClient(60*1000, 60*1000);
//		createHttpClient.
		HttpPost hp = new HttpPost(UrlUtil.URL);
		HttpParams hpa = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(hpa, 30 * 1000);
		HttpConnectionParams.setSoTimeout(hpa, 30 * 1000);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Entry<String, Object> entry : map.entrySet()) {
			NameValuePair userName = new BasicNameValuePair(entry.getKey(),
					entry.getValue().toString());
			list.add(userName);
		}
		try {
			HttpEntity reqEdtity = new UrlEncodedFormEntity(list, "utf-8");
			hp.setEntity(reqEdtity);
			HttpClient hc = new DefaultHttpClient(hpa);
			final HttpResponse resp = hc.execute(hp);
//			handler.post(new Runnable() {
//				
//				@Override
//				public void run() {
//					ToastUtils.showErrorToast(context, resp.getStatusLine().getStatusCode()+"");
//					
//				}
//			});
			
//			Thread.sleep(1000*20);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity respEntity = resp.getEntity();
				final String result = EntityUtils.toString(respEntity, "utf-8");
				if (ExecuteJSONUtils.getMessageState(context, result) == 2) {
					if(UrlUtil.userLogout.equals(map.get(Constant.commandText))){
						return;
					}
					if (login(context)) {
						map.put(Constant.UUID, SharedPrefConstance.getStringValue(context, SharedPrefConstance.UUID));
						run();
					}
				} else {
					
					handler.post(new Runnable() {

						@Override
						public void run() {
							
							callBack.onSuccess(result);
							exitTime(3);
						}
					});
				}
				exitTime(1);
			} else {
				
				exitTime(2);
			}
		} catch (final Exception e) {
			e.printStackTrace();
			exitTime(0);
		}
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @param connectionTimeout
	 * @param socketTimeout
	 * @return
	 * @date:2014-11-7
	 * @author:lee
	 * @Funtion:创建HttpClient
	 */
	public DefaultHttpClient createHttpClient(int connectionTimeout,
			int socketTimeout) {
		HttpParams params = new BasicHttpParams();
		ConnManagerParams.setMaxTotalConnections(params, 25);
		HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
		HttpConnectionParams.setSoTimeout(params, socketTimeout);
		Log.d("SoTimeout", "" + HttpConnectionParams.getSoTimeout(params));
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, true);
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", PlainSocketFactory
				.getSocketFactory(), 80));
		schReg.register(new Scheme("https",
				SSLSocketFactory.getSocketFactory(), 443));
		ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
				params, schReg);
		return new DefaultHttpClient(conMgr, params);
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param num
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: message notify
	 */
	private  void exitTime(int num) {
		
		if(exceptionState == num){
			handler.post(new Runnable() {

				@Override
				public void run() {
					// callBack.onFailure(e, "");
					ToastUtils.showErrorToast(context, "网络忙，请重试");
				}
			});
		}else if(success == num){
			if(new Date().getTime() - startTime >20*1000){
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						
						ToastUtils.showErrorToast(context, "网络问题");
					}
				});
			}
			
		}else if(requestCode200 == num){
			
		}else if(requestCode500 == num){
			handler.post(new Runnable() {

				@Override
				public void run() {
					ToastUtils.showErrorToast(context, "请联系管理员");
				}
			});
		}
		MyProgress.getInstance().dismissDialog();
	}
	
	public boolean run(Map<String, Object> map) {
		
		HttpPost hp = new HttpPost(UrlUtil.URL);
		HttpParams hpa = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(hpa, 5 * 1000);
		HttpConnectionParams.setSoTimeout(hpa, 5 * 1000);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		for (Entry<String, Object> entry : map.entrySet()) {
			NameValuePair userName = new BasicNameValuePair(entry.getKey(),
					entry.getValue().toString());
			list.add(userName);
		}
		try {
			HttpEntity reqEdtity = new UrlEncodedFormEntity(list, "utf-8");
			hp.setEntity(reqEdtity);
			HttpClient hc = new DefaultHttpClient(hpa);
			final HttpResponse resp = hc.execute(hp);
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity respEntity = resp.getEntity();
				final String result = EntityUtils.toString(respEntity, "utf-8");
				
				boolean simpleUpdateInfo = simpleUpdateInfo(context, result);
				if(simpleUpdateInfo){
					if(map.get(Constant.commandText).equals(UrlUtil.userLogin)){
						ExecuteJSONUtils.login(context, result);
					}else if(map.get(Constant.commandText).equals(UrlUtil.userGetDetail)){
						ExecuteJSONUtils.getUserDetails(context, result);
					}
				}
				return simpleUpdateInfo;

			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @param context
	 * @param msg
	 * @return
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: parse callbackListener msg
	 */
	public  boolean simpleUpdateInfo(Context context, String msg) {
		// {status:1,message:"成功"}
		try {
			JSONObject json = new JSONObject(msg);
			int status = json.optInt("status");
			if (status == 1) {
				
				return true;
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
