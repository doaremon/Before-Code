package com.example.gongcheng;

import com.app.MyApplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
//我的咨询受页面
public class FourActivity extends Activity{
	ImageButton button;
	ImageButton button1;
	ImageButton button2;
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.four);
		//我的咨询中的电话咨询
		button=(ImageButton)findViewById(R.id.dianhuazixun);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(FourActivity.this, SixActivlty.class);
				FourActivity.this.startActivity(intent);
			}
		});
		//我的咨询后退按钮
		button1=(ImageButton)findViewById(R.id.wodezixunhoutui);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		//我的咨询中的留言回复
		button2=(ImageButton)findViewById(R.id.liuyanhuifu);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(FourActivity.this, SevenActivity.class);
				FourActivity.this.startActivity(intent);

			}
		});
		//我的咨询中的电话咨询
		textView=(TextView)findViewById(R.id.dianhuazixunid);
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(FourActivity.this, SixActivlty.class);
				FourActivity.this.startActivity(intent);
			}
		});
	}

}
