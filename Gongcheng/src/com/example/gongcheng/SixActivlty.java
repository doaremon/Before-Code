package com.example.gongcheng;

import com.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
//电话咨询中我的咨询页面
public class SixActivlty extends Activity{
ImageButton button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.six);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		//后退按钮
		button=(ImageButton)findViewById(R.id.dianhuazixunhoutui);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent=new Intent(SixActivlty.this, FourActivity.class);
//				SixActivlty.this.startActivity(intent);
				finish();
			}
		});
	}

}
