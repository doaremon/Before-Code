package com.denglu;

import java.io.IOException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ShixianRun implements Runnable{
	private String name;
	private String url;
	private Handler handler;
	public ShixianRun(String name, String url, Handler handler) {
		super();
		this.name = name;
		this.url = url;
		this.handler = handler;
		Log.i("www", name+url+"ShixianRun");
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			String str=	Httoutilsss.fasong(name, url);
//			MainActivity.strr =str;
			Log.i("www", str);
			Message message=new Message();
			message.what=200;
			message.obj=str;
			handler.sendMessage(message);
		} catch (IOException e) {
			Message message=new Message();
			message.what=400;
			handler.sendMessage(message);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
