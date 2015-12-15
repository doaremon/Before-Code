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
//��Ҫ�棬��¼ʱ���ҳ��
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

					// Toast.makeText(DoctorActivity.this, "��¼�ɹ�",
					// Toast.LENGTH_SHORT).show();
					// ��¼�ɹ��ͼ�ס�����Ϊѡ��״̬�ű����û���Ϣ

					// ��ס�û��������롢
					Editor editor = Config.sp.edit();
					editor.putString("USER_NAME", userNameValue);
					editor.putString("PASSWORD", passwordValue);
					editor.commit();
					// ��ת����
					Intent intent = new Intent(MainActivity.this,
							FiveActivity.class);
					MainActivity.this.startActivity(intent);
					// finish();
				} else {
					// Toast.makeText(DoctorActivity.this, "�û�����������������µ�¼",
					// Toast.LENGTH_LONG).show();
					finish();
				}

				break;

			default:
				break;
			}
		}};

		// �˳�ʱ��
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
			//��activity�ŵ�������
			MyApplication.getInstance().addActivity(this);
			// ���ʵ������
			Config.sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
			userName = (EditText) findViewById(R.id.zhanghao);
			password = (EditText) findViewById(R.id.mima);
			// ��ס����
			rem_pw = (CheckBox) findViewById(R.id.jizhumima);
			// �Զ���¼
			auto_login = (CheckBox) findViewById(R.id.zidongdenglu);
			button = (Button) findViewById(R.id.denglu);


			// �жϼ�ס�����ѡ���״̬
			if (Config.sp.getBoolean("ISCHECK", false)) {
				// ����Ĭ���Ǽ�¼����״̬
				rem_pw.setChecked(true);
				userName.setText(Config.sp.getString("USER_NAME", ""));
				password.setText(Config.sp.getString("PASSWORD", ""));
				// �ж��Զ���½��ѡ��״̬
				if (Config.sp.getBoolean("AUTO_ISCHECK", false)) {
					// ����Ĭ�����Զ���¼״̬
					auto_login.setChecked(true);
					// ��ת����
					Intent intent = new Intent(MainActivity.this,
							FiveActivity.class);
					MainActivity.this.startActivity(intent);

				}
			}

			// ��¼�����¼� ����Ĭ��Ϊ�û���Ϊ��chenghao ���룺123
			button.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// ��EditText����õ��û���������
					userNameValue = userName.getText().toString();
					passwordValue = password.getText().toString();
					// ����Ч��
					if("1".equals(a)){
						ProgressDialog	dialog=new ProgressDialog(MainActivity.this);
						dialog.setMessage("��¼��......");
						dialog.show();
					}
					
					
					//���������
					String name = Hashmaps.pinjieJson(userNameValue, passwordValue);
					String url = "http://222.88.48.186:8090/Ghyy/mobile/MobileDoctorLogin";

					ShixianRun run = new ShixianRun(name, url, handler);
					Thread thread = new Thread(run);
					thread.start();			

				}


			});
			// ������ס�����ѡ��ť�¼�
			rem_pw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (rem_pw.isChecked()) {

						System.out.println("��ס������ѡ��");
						Config.sp.edit().putBoolean("ISCHECK", true).commit();

					} else {

						System.out.println("��ס����û��ѡ��");
						Config.sp.edit().putBoolean("ISCHECK", false).commit();

					}

				}
			});
			// �����Զ���¼��ѡ���¼�
			auto_login.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if (auto_login.isChecked()) {
						System.out.println("�Զ���¼��ѡ��");
						Config.sp.edit().putBoolean("AUTO_ISCHECK", true).commit();

					} else {
						System.out.println("�Զ���¼û��ѡ��");
						Config.sp.edit().putBoolean("AUTO_ISCHECK", false).commit();
					}
				}
			});

		}


		//��2���˳�
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			// TODO Auto-generated method stub
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				if ((System.currentTimeMillis() - mExitTime) > 2000) {
					Object mHelperUtils;
					Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();
					//����Ҫ�����˳�ʱ��ֱ���˳�
					//MyApplication.getInstance().exit();

				} else {
					finish();
				}
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}

}
