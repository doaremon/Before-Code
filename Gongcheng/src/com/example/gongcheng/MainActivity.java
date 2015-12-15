package com.example.gongcheng;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.app.MyApplication;
import com.denglu.Hashmaps;
import com.denglu.ShixianRun;
import com.denglu.SsonTobean;
import com.denglu.StringInfor;
import com.gongyong.Config;
//主要面，登录时候的页面
public class MainActivity extends Activity {
	//handler
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 200:
				String responseJson = (String) msg.obj;
				List<Object> list = new ArrayList<Object>();
				list = SsonTobean.NYGetBeanFromJson(responseJson,
						StringInfor.class);
				List<StringInfor> list1=(List<StringInfor>) list.get(2);
				Config.infor=list1.get(0);
				Log.i("xxx", Config.infor.getDoctorid()+"123124");
				Log.i("xxx",  list1.get(0).getDoctorid()+"=====");
				if ("1".equals(list.get(0).toString())) {
					userNameValue = userName.getText().toString();
					passwordValue = password.getText().toString();

					// Toast.makeText(DoctorActivity.this, "登录成功",
					// Toast.LENGTH_SHORT).show();
					// 登录成功和记住密码框为选中状态才保存用户信息

					// 记住用户名、密码、
					Editor editor = Config.sp.edit();
					editor.putString("USER_NAME", userNameValue);
					editor.putString("PASSWORD", passwordValue);
					editor.commit();
					// 跳转界面
					Intent intent = new Intent(MainActivity.this,
							FiveActivity.class);
					MainActivity.this.startActivity(intent);
					// finish();
				} else {
					// Toast.makeText(DoctorActivity.this, "用户名或密码错误，请重新登录",
					// Toast.LENGTH_LONG).show();
					finish();
				}

				break;

			default:
				break;
			}
		}};

		// 退出时用
		private long mExitTime;
		private EditText userName, password;
		private CheckBox rem_pw, auto_login;
		private Button button;
        public static String a="1";
		private String userNameValue, passwordValue;



		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			//把activity放到容器中
			MyApplication.getInstance().addActivity(this);
			// 获得实例对象
			Config.sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
			userName = (EditText) findViewById(R.id.zhanghao);
			password = (EditText) findViewById(R.id.mima);
			// 记住密码
			rem_pw = (CheckBox) findViewById(R.id.jizhumima);
			// 自动登录
			auto_login = (CheckBox) findViewById(R.id.zidongdenglu);
			button = (Button) findViewById(R.id.denglu);


			// 判断记住密码多选框的状态
			if (Config.sp.getBoolean("ISCHECK", false)) {
				// 设置默认是记录密码状态
				rem_pw.setChecked(true);
				userName.setText(Config.sp.getString("USER_NAME", ""));
				password.setText(Config.sp.getString("PASSWORD", ""));
				// 判断自动登陆多选框状态
				if (Config.sp.getBoolean("AUTO_ISCHECK", false)) {
					// 设置默认是自动登录状态
					auto_login.setChecked(true);
					// 跳转界面
					Intent intent = new Intent(MainActivity.this,
							FiveActivity.class);
					MainActivity.this.startActivity(intent);

				}
			}

			// 登录监听事件 现在默认为用户名为：chenghao 密码：123
			button.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// 从EditText里面得到用户名和密码
					userNameValue = userName.getText().toString();
					passwordValue = password.getText().toString();
					// 加载效果
					if("1".equals(a)){
						ProgressDialog	dialog=new ProgressDialog(MainActivity.this);
						dialog.setMessage("登录中......");
						dialog.show();
					}
					
					
					//请求服务器
					String name = Hashmaps.pinjieJson(userNameValue, passwordValue);
					String url = "http://222.88.48.186:8090/Ghyy/mobile/MobileDoctorLogin";

					ShixianRun run = new ShixianRun(name, url, handler);
					Thread thread = new Thread(run);
					thread.start();			

				}


			});
			// 监听记住密码多选框按钮事件
			rem_pw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (rem_pw.isChecked()) {

						System.out.println("记住密码已选中");
						Config.sp.edit().putBoolean("ISCHECK", true).commit();

					} else {

						System.out.println("记住密码没有选中");
						Config.sp.edit().putBoolean("ISCHECK", false).commit();

					}

				}
			});
			// 监听自动登录多选框事件
			auto_login.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (auto_login.isChecked()) {
						System.out.println("自动登录已选中");
						Config.sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

					} else {
						System.out.println("自动登录没有选中");
						Config.sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
					}
				}
			});

		}


		//点2次退出
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Object mHelperUtils;
					Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
					//当需要快速退出时候，直接退出
					//MyApplication.getInstance().exit();

				} else {
					finish();
				}
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}

}
