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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.MyTimeAdapter;
import com.app.MyApplication;
import com.work.Work5;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
//时间设置
public class Twenty_two_persionActivity extends Activity{
	Button button;
	ListView listView;
	public static Work5 workpe;
	private static List<Work5> list;
	private  ProgressDialog dialog;
	ImageButton button1;
	public static int a=1;
	String str;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.twenty_two_persion);
		//后退按钮
		button1=(ImageButton)findViewById(R.id.shijianshezhizonghoutui_pe);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog=new ProgressDialog(Twenty_two_persionActivity.this);
				dialog.setMessage("正在加载");
				dialog.show();
				String  url="http://222.88.48.186:8098/sims/medicationService.do?method=editDoctorServlet";
				ShowRun showRun=new ShowRun(str, url, handler);
				Thread thread=new Thread(showRun);
				thread.start();
			}
		});
		if(list==null){
			list=new ArrayList<Work5>();
		}
		if(workpe!=null){
			Log.i("ccc", a+"--");
			list.add(workpe);
			listView=(ListView)findViewById(R.id.xianshizongshijian_persion);
			MyTimeAdapter adapter=new MyTimeAdapter(Twenty_two_persionActivity.this,list);
			adapter.notifyDataSetChanged();
			listView.setAdapter(adapter);
		}
		StringBuffer buffer=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			Work5 workpe=list.get(i);
			buffer.append(workpe.getDate()).append(":").append(workpe.getMorningst()).append("--").append(workpe.getMorningsp()).append(";").append(workpe.getDate()).append(":").append(workpe.getAfternoonst()).append("--").append(workpe.getAfternoonsp()).append(";");
			String sb=new String(buffer);
			str=Map.Timeshezhi(sb);
		}
		//添加按钮跳转
		button=(Button)findViewById(R.id.tianjiashijian_persion);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Twenty_two_persionActivity.this,Twenty_three_persionActivity.class);
				Twenty_two_persionActivity.this.startActivity(intent);
			}
		});



	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i("ccc", a+"++");
			Intent intent = new Intent(Twenty_two_persionActivity.this,TwentyActivity_persion.class);
			Twenty_two_persionActivity.this.startActivity(intent);
		}};



}
