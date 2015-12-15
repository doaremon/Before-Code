package com.example.gongcheng;

import com.app.MyApplication;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
//意见反馈
public class Twenty_fankui_persion extends Activity{
	EditText editText;
	Button button;
	ImageButton imageButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twenty_yijianfankui_pe);
		MyApplication.getInstance().addActivity(this);
		editText=(EditText)findViewById(R.id.yijianfankuiwenben_pe);
		//后退按钮
		imageButton=(ImageButton)findViewById(R.id.yijianfankuibutton_pe);
		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		button=(Button)findViewById(R.id.yijianfankuianniu_pe);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String str=editText.getText().toString().trim();
				String name=Map.wentifankui(str);
				String url="http://hi.baidu.com/justtmiss/item/3b7d683e2fa66684b611db11";
				ShowRun showRun=new ShowRun(name, url, handler);
				Thread thread=new Thread(showRun);
				thread.start();
			}
		});
	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i("ccc", msg.obj+"");
			Toast.makeText(Twenty_fankui_persion.this, "反馈成功", Toast.LENGTH_LONG).show();
		}};

}
