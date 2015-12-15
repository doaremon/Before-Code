package com.yonghuliuyan;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ShowRun implements Runnable{
	//名字，路径，生成有参构造
	private String name;
	private String url;
	private Handler handler;
	public ShowRun(String name, String url, Handler handler) {
		super();
		this.name = name;
		this.url = url;
		this.handler = handler;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Log.i("xxx", "rnu");
			String json1=HttpDown.dow(name, url);
			Log.i("xxx", json1);
			Message message=new Message();
			message.what=200;
			message.obj=json1;
			handler.sendMessage(message);
			//============
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			Message message1=new Message();
			message1.what=400;
			handler.sendMessage(message1);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
