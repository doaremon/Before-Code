package com.demo.app;

import com.lidroid.xutils.util.LogUtils;
import com.read.http.AsyncHttpClient;
import com.read.http.AsyncHttpResponseHandler;

import android.app.Application;

public class Appcontext extends Application{
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
		asyncHttpClient.get("", new AsyncHttpResponseHandler(){});
	}
}
