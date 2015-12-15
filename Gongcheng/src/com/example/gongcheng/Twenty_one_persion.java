package com.example.gongcheng;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.MyApplication;
import com.gongyong.Config;

public class Twenty_one_persion extends Activity{

	//个人中心
	TextView textView1;
	TextView textView2;
	TextView textView3;
	TextView textView4;
	TextView textView5;
	Button button;
	ImageButton button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.twenty_one_persion);
		textView1=(TextView)findViewById(R.id.xingming_persion);
		textView2=(TextView)findViewById(R.id.gonghao_persion);
		textView3=(TextView)findViewById(R.id.yiyuan_persion);
		textView4=(TextView)findViewById(R.id.keshi_persion);
		textView5=(TextView)findViewById(R.id.shanchang_persion);
		textView1.setText(Config.infor.getDoctorname());
		textView2.setText(Config.infor.getDoctorid());
		textView3.setText(Config.infor.getHospitalname());
		textView4.setText(Config.infor.getDeptname());
		textView5.setText(Config.infor.getShanc());
		//后退按钮
		button2=(ImageButton)findViewById(R.id.gerenzhongxingoutui_pe);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//注销按钮
		button=(Button)findViewById(R.id.zhuxiao_persion);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 记住用户名、密码、
				Editor editor = Config.sp.edit();
				editor.putString("USER_NAME", "");
				editor.putString("PASSWORD", "");
				editor.commit();
				Intent intent=new Intent(Twenty_one_persion.this,MainActivity.class);
				Twenty_one_persion.this.startActivity(intent);
			}
		});

	}

}
