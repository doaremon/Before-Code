package com.example.gongcheng;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//登录后是否称谓咨询医生的页面
public class FiveActivity extends Activity{
	TextView textView;
	Button button1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.five);
		//把activity放到容器中
		MainActivity.a="2";
		MyApplication.getInstance().addActivity(this);
		textView=(TextView)findViewById(R.id.liushuchu);
		try {
			InputStream inputStream=getAssets().open("bgzg.txt");
			int size=inputStream.available();
			byte[]buffer= new byte[size];
			inputStream.read(buffer);
			inputStream.close();
			String text=new String(buffer,"UTF-8");
			textView.setText(text);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		button1=(Button)findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FiveActivity.this,FirstActivity.class);
				FiveActivity.this.startActivity(intent);

			}
		});

	}

}
