package com.example.gongcheng;

import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.adapter.ZixunDingdanAdapter;
import com.app.MyApplication;
import com.jiexi.jiexi;
import com.work.Work4;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
public class SixteenActivity extends Activity{
	//咨询订单页面
	Button button;
	EditText editText;
	List<Work4> list;
	ListView listView;
	Button button1;
	ProgressDialog dialog;
	ImageButton  button2;
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			dialog.cancel();
			String js=(String) msg.obj;
			list=jiexi.JieXizixundd(js, Work4.class);
			ZixunDingdanAdapter adapter=new ZixunDingdanAdapter(SixteenActivity.this, list);
			listView.setAdapter(adapter);
		}};
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.sixteen);
			//把activity放到容器中
			MyApplication.getInstance().addActivity(this);
			button2=(ImageButton)findViewById(R.id.zixundingdanhoutui123);
			button2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
			listView=(ListView)findViewById(R.id.zixundingdanListView);
			editText=(EditText)findViewById(R.id.srzixundingdan_EditText);
			editText.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					showDialog(1);
				}
			});

			button=(Button)findViewById(R.id.zixundingdanchaxunbutton);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					//显示详情的
					dialog=new ProgressDialog(SixteenActivity.this);
					dialog.setMessage("正在查询");
					dialog.show();
					String url="http://222.88.48.186:8098/sims/pay.do?method=PhoneOrderQureyofdoc";
					String name=Map.ZxddOrder("");
					ShowRun showRun=new ShowRun(name, url, handler);
					Thread thread=new Thread(showRun);
					thread.start();
				}
			});
		}
		DatePickerDialog.OnDateSetListener onDateSetListener=new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {

			}
		};
		@Override
		protected Dialog onCreateDialog(int id) {
			switch (id) {
			case 1:
				return new DatePickerDialog(SixteenActivity.this,onDateSetListener,2013,6,7);
			default:
				break;
			}
			return super.onCreateDialog(id);
		}


}
