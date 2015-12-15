package com.example.gongcheng;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.app.MyApplication;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
//资格变更
public class Twenty_zigexiugai_persion extends Activity{
	TextView textView1;
	CheckBox checkBox;
	Button button;
	ImageButton imageButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twenty_zigebiangeng_pe);
		textView1=(TextView)findViewById(R.id.zigebiangengtext_pe);
		MyApplication.getInstance().addActivity(this);
		try {
			InputStream inputStream=getAssets().open("bgzg.txt");
			int size=inputStream.available();
			byte[]buffer= new byte[size];
			inputStream.read(buffer);
			inputStream.close();
			String text=new String(buffer,"UTF-8");
			textView1.setText(text);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//后退按钮
		imageButton=(ImageButton)findViewById(R.id.zigebiangengbutton_persion);
		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});


		button=(Button)findViewById(R.id.zigebiangengbutton_pe);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name=Map.zigeviangeng();
				String url="http://222.88.48.186:8098/sims/MemberService.do?method=expertRegistration";
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
			Log.i("ccc", msg.obj+"==========");
			Toast.makeText(Twenty_zigexiugai_persion.this, "修改成功", Toast.LENGTH_LONG).show();
		}};

}
