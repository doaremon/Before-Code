package com.example.gongcheng;

import com.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class NiceActivity extends Activity{
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nice);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		textView=(TextView)findViewById(R.id.guahaodingdantext);
				textView.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(NiceActivity.this,TanActivity.class);
						NiceActivity.this.startActivity(intent);
					}
				});
	}

}
