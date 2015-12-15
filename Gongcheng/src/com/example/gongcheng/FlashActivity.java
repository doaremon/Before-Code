package com.example.gongcheng;

import com.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
//这个是加载Flash的页面
public class FlashActivity extends Activity{
	int PAUSE_TIME=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flash);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		new Thread(){
			public void run(){
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Message message=new Message();
				message.what=1;
				handler.sendMessage(message);
			}
		}.start();
	}
	
	Handler handler=new Handler(){
		public void handleMessage(Message message){
			if(message.what==PAUSE_TIME){
				Intent i=new Intent(FlashActivity.this,MainActivity.class);
				startActivity(i);
				FlashActivity.this.finish();
			}
		}
	};
}
