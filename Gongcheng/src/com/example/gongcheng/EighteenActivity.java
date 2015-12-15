package com.example.gongcheng;

import java.util.ArrayList;
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.MyApplication;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
//账单查询
public class EighteenActivity extends Activity{
	Spinner spinner;
	private String str;
	TextView textView;
	ProgressDialog dialog;
	ImageButton button;

	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Log.i("ccc", msg.obj+"");
			dialog.cancel();
		}};
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.eighteen);
			MyApplication.getInstance().addActivity(this);
			button=(ImageButton)findViewById(R.id.zhangdanzixunhoutui);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
			spinner=(Spinner)findViewById(R.id.yuefen_spinner);
			textView=(TextView)findViewById(R.id.zhangdanchaxun_id);
			List list=new ArrayList();
			list.add("一月");
			list.add("二月");
			list.add("三月");
			list.add("四月");
			list.add("五月");
			list.add("六月");
			list.add("七月");
			list.add("八月");
			list.add("九月");
			list.add("十月");
			list.add("十一月");
			list.add("十二月");
			ArrayAdapter adapter=new ArrayAdapter(EighteenActivity.this, R.layout.nineteen, R.id.Zhangdanchaoxun_zibuju, list);
			spinner.setAdapter(adapter);
			spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					str=arg0.getItemAtPosition(arg2).toString();
					textView.setText(str);
					int str1=arg2+1; 
					//显示详情的
					dialog=new ProgressDialog(EighteenActivity.this);
					dialog.setMessage("正在查询");
					dialog.show();
					String url="http://222.88.48.186:8098/sims/pay.do?method=OrderCost";
					String name=Map.ZdcxOrder(str1);
					ShowRun showRun=new ShowRun(name, url, handler);
					Thread thread=new Thread(showRun);
					thread.start();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {

				}
			});
		}

}
