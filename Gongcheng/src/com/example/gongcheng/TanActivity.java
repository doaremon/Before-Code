package com.example.gongcheng;

import java.util.List;

import com.adapter.MyDingdanAdapter;
import com.app.MyApplication;
import com.jiexi.jiexi;
import com.work.Work3;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
//挂号订单
public class TanActivity extends Activity{
	EditText editText;
	Button button;
	private String str;
	List<Work3>list;
	ListView listView;
	ProgressDialog dialog;
	ImageButton button2;
	Handler handler1=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			dialog.cancel();
			String str2= (String) msg.obj;
			list=jiexi.JieXidingdan(str2, Work3.class);
			MyDingdanAdapter adapter=new MyDingdanAdapter(TanActivity.this, list);
			listView.setAdapter(adapter);
		}};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tan);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		listView=(ListView)findViewById(R.id.guahaodingdanListView);
		editText=(EditText)findViewById(R.id.shurudingdan_EditText);
		button=(Button)findViewById(R.id.guahaodingdanchaxunbutton);
		button2=(ImageButton)findViewById(R.id.guahaodingdanhoutui);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		editText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(1);
				
			}
		});
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//显示详情的
				dialog=new ProgressDialog(TanActivity.this);
				dialog.setMessage("正在查询");
				dialog.show();
				String url="http://222.88.48.186:8090/Ghyy/mobile/GetOrderList";
				String str1=Map.GuahaoOrder("");
				ShowRun showRun=new ShowRun(str1, url, handler1);
				Thread thread=new Thread(showRun);
				thread.start();
			}
		});



	}
	
	
	//选择时间，然后显示在editText上面
	DatePickerDialog.OnDateSetListener oDateSetListener=new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
             str=year+"-"+(monthOfYear+1)+"-"+dayOfMonth+"";
             editText.setText(str);
		}
	};
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 1:
			return new DatePickerDialog(TanActivity.this, oDateSetListener, 2013, 6, 4);
			

		default:
			break;
		}
		return super.onCreateDialog(id);
	}


}
