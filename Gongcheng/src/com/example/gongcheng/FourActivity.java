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
//�ҵ���ѯ��ҳ��
public class FourActivity extends Activity{
	ImageButton button;
	ImageButton button1;
	ImageButton button2;
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//��activity�ŵ�������
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.four);
		//�ҵ���ѯ�еĵ绰��ѯ
		button=(ImageButton)findViewById(R.id.dianhuazixun);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(FourActivity.this, SixActivlty.class);
				FourActivity.this.startActivity(intent);
			}
		});
		//�ҵ���ѯ���˰�ť
		button1=(ImageButton)findViewById(R.id.wodezixunhoutui);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();

			}
		});
		//�ҵ���ѯ�е����Իظ�
		button2=(ImageButton)findViewById(R.id.liuyanhuifu);
		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(FourActivity.this, SevenActivity.class);
				FourActivity.this.startActivity(intent);

			}
		});
		//�ҵ���ѯ�еĵ绰��ѯ
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
