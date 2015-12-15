package com.example.gongcheng;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.adapter.MyxingqiAdapter;
import com.app.MyApplication;
import com.gongyong.Config;

public class Twenty_four_persionActivity extends Activity{
	ListView listView;
	String str;
	ImageButton button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.twenty_four_persion);
		listView=(ListView)findViewById(R.id.xingqilistview_persion);
		MyxingqiAdapter adapter=new MyxingqiAdapter(Twenty_four_persionActivity.this);
		listView.setAdapter(adapter);


		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Log.i("ccc", arg2+"");
				switch (arg2) {
				case 0:
					str="星期一";
					break;
				case 1:
					str="星期二";
					break;
				case 2:
					str="星期三";
					break;
				case 3:
					str="星期四";
					break;
				case 4:
					str="星期五";
					break;
				case 5:
					str="星期六";
					break;
				case 6:
					str="星期日";
					break;

				default:
					break;
				}
				Twenty_three_persionActivity.textView20.setText(str);
				finish();
			}
		});


	}

}
