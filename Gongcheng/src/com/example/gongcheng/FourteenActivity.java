package com.example.gongcheng;

import com.app.MyApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
//¹ÒºÅ£¬×ÉÑ¯×ÜÒ³Ãæ
public class FourteenActivity extends Activity{
	TextView textView1;
	TextView textView2;
	TextView textView3;
	ImageButton button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//°Ñactivity·Åµ½ÈÝÆ÷ÖÐ
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.fourteen);
		textView1=(TextView)findViewById(R.id.guahaodingdan_first);
		textView2=(TextView)findViewById(R.id.zixundingdan);
		textView3=(TextView)findViewById(R.id.zhangdanzixun);
		button=(ImageButton)findViewById(R.id.wodedingdanzonghoutui);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//¹ÒºÅ¶©µ¥
		textView1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FourteenActivity.this,TanActivity.class);
				FourteenActivity.this.startActivity(intent);
			}
		});
		//×ÉÑ¯¶©µ¥
		textView2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FourteenActivity.this,SixteenActivity.class);
				FourteenActivity.this.startActivity(intent);
			}
		});
		//ÕËµ¥×ÉÑ¯
		textView3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(FourteenActivity.this,EighteenActivity.class);
				FourteenActivity.this.startActivity(intent);
			}
		});
		
	}

}
