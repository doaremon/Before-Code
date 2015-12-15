package com.example.gongcheng;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.jiexi.jiexi;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
import com.yonghuliuyan.Work2;

public class ceshi extends Activity{
Button button;


   Handler handler=new Handler(){

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		super.handleMessage(msg);
		switch (msg.what) {
		case 200:
			String fanhui=(String) msg.obj;
			
//			List<Object> list=jiexi.jxJson(fanhui, Work2.class);
//			String s=((List<Work2>)list.get(0)).get(0).getFatherid();
//			
//			Log.i("aaa",s);
//			break;

		default:
			break;
		}
	     
	}};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xxxxxx);
		button=(Button)findViewById(R.id.sssssss);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=Map.qingqiuJson("2098");
				Log.i("xxx", name);
				String url="http://222.88.48.186:8098/sims/medicationService.do?method=ExpertmyCommunicatServlet";
				ShowRun run1=new ShowRun(name, url, handler);
				Thread thread=new Thread(run1);
				thread.start();
			}
		});
	}
   
}
