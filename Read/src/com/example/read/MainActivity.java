package com.example.read;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends BaseActivity {
	private WindowManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setstr();
		manager=getWindowManager();
		int width=manager.getDefaultDisplay().getWidth();
		int height=manager.getDefaultDisplay().getHeight();
		showlog("现在的宽为="+width);
		showlog("现在的高为="+height);
		//		Mytextview mytextview=new Mytextview(MainActivity.this, str,width,height);
		setContentView(R.layout.activity_main);

		RelativeLayout layout=(RelativeLayout) findViewById(R.id.mylayout);
		//		Mytextview mytextview=new Mytextview(MainActivity.this, str,width,height);
		Myview myview=new Myview(MainActivity.this, str, width, height);
		
//		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
//		lp.setMargins(100,100,100,100);
//		myview.setLayoutParams(lp);
		layout.addView(myview);

		//		int newwidth=width-110;
		//		showlog("左右都有10px的流出=最后="+newwidth);
		//		

		//		
		//		TextView textView=new TextView(MainActivity.this);
		//		textView.setText(str);
		//		showlog("str的长度="+str.length());
		//		int x=(int)textView.getTextScaleX();
		//		textView.setTextSize(50);
		//		int xx=(int) textView.getTextSize();
		//		showlog("字艰巨-"+x+";;;xx="+xx);
		//		showlog("一行能放="+ newwidth/xx);
		//		layout.addView(textView);
		//		int strsize=str.length();
		//		int index=newwidth/xx;
		//		showlog(strsize/index+"====");
		//		



	}



}
