package com.example.gongcheng;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.adapter.MyZixunAdapter;
import com.app.MyApplication;
import com.gongyong.Config;
import com.jiexi.jiexi;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
import com.yonghuliuyan.Work2;
//留言回复
public class SevenActivity extends Activity{

	ListView listView;
	ImageButton button;
	public static List<Work2> list;
	ProgressDialog dialog;
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 200:
				dialog.cancel();
				String fanhui=(String) msg.obj;
				listView=(ListView)findViewById(R.id.wodezixunlistview);
				list=jiexi.jxJson(fanhui, Work2.class);

				MyZixunAdapter adapter=new MyZixunAdapter(SevenActivity.this, list);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						Config.workss=list.get(arg2);

						Intent intent=new Intent(SevenActivity.this,ElevenActivity.class);
						intent.putExtra("bean", Config.workss);
						SevenActivity.this.startActivity(intent);
					}
				});

				break;

			default:
				break;
			}
		}};
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.seven);	
			//把activity放到容器中
			MyApplication.getInstance().addActivity(this);
			//加载
			dialog=new ProgressDialog(SevenActivity.this);

			dialog.setMessage("正在加载");
			dialog.show();


			//请求
			String name=Map.qingqiuJson("2098");
			Log.i("xxx", name);
			String url="http://222.88.48.186:8098/sims/medicationService.do?method=ExpertmyCommunicatServlet";
			ShowRun run1=new ShowRun(name, url, handler);
			Thread thread=new Thread(run1);
			thread.start();


			button=(ImageButton)findViewById(R.id.dianhuazixunliuyan);
			button.setOnClickListener(new OnClickListener() {			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});

		}

}
