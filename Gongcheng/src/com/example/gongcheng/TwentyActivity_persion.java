package com.example.gongcheng;

import com.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
//个人中心总的
public class TwentyActivity_persion extends Activity{
	TextView textView;
	TextView textView1;
	TextView textView2;
	TextView textView3;
	TextView textView4;
	TextView textView5;
	ImageButton button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twenty_geren);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		//个人信息跳转
		textView=(TextView)findViewById(R.id.gerenxingxi_persion);
		textView1=(TextView)findViewById(R.id.geren_persion);
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TwentyActivity_persion.this,Twenty_one_persion.class);
				TwentyActivity_persion.this.startActivity(intent);
			}
		});
		textView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TwentyActivity_persion.this,Twenty_two_persionActivity.class);
				TwentyActivity_persion.this.startActivity(intent);
			}
		});
		//修改密码
		textView2=(TextView)findViewById(R.id.gerenzhongxinxiugaimima_pe);
		textView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TwentyActivity_persion.this,Twenty_Mima_persion.class);
				TwentyActivity_persion.this.startActivity(intent);
			}
		});
		//意见反馈
		textView3=(TextView)findViewById(R.id.wentifankui_pe);
		textView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(TwentyActivity_persion.this,Twenty_fankui_persion.class);
				TwentyActivity_persion.this.startActivity(intent);
			}
		});
		//资格变更
		textView4=(TextView)findViewById(R.id.zigebiangengzong_pe);
		textView4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TwentyActivity_persion.this,Twenty_zigexiugai_persion.class);
				TwentyActivity_persion.this.startActivity(intent);
			}
		});
		//费用设置
		textView5=(TextView)findViewById(R.id.feiyongshezhi_pe);
		textView5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TwentyActivity_persion.this,Twenty_feiyong_persion.class);
				TwentyActivity_persion.this.startActivity(intent);
			}
		});
		button=(ImageButton)findViewById(R.id.gerenzhongxinhoutui_pe);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

}
