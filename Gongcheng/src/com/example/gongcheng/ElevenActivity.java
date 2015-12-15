package com.example.gongcheng;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.adapter.XiangqingAdapter;
import com.app.MyApplication;
import com.gongyong.Config;
import com.jiexi.jiexi;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
import com.yonghuliuyan.Work2;
//��ѯ���飬ҽ���ظ���
public class ElevenActivity extends Activity{
	//��ʼ������
	ListView listView;
	List<Work2>lists;
	List list2;
	ProgressDialog dialog;
	Button button;
	EditText editText;
	ImageButton button2;
	private String val=null;
	//��ʾ�����handler
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 200:
				dialog.cancel();
				String str=(String) msg.obj;
				listView=(ListView)findViewById(R.id.zixunxiangqing);
				lists=jiexi.jxJson(str, Work2.class);
				XiangqingAdapter adapter=new XiangqingAdapter(ElevenActivity.this, lists);
				listView.setAdapter(adapter);

				break;

			default:
				break;
			}
		}};
		//ҽ���ظ�ʱ���handler
		Handler handler2=new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				String ms=(String) msg.obj;
				list2=jiexi.jxDoctorBack(ms);
				String ob=(String) list2.get(0);
				if("1".equals(ob)){
					XiangqingAdapter adapter=new XiangqingAdapter(ElevenActivity.this, lists);
					listView.setSelection(lists.size()-1);
					listView.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
			}};
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				// TODO Auto-generated method stub
				super.onCreate(savedInstanceState);
				//��activity�ŵ�������
				MyApplication.getInstance().addActivity(this);
				setContentView(R.layout.eleven);
				editText=(EditText)findViewById(R.id.qingshuruid_EditText);
				//ҽ���ظ���ť
				button=(Button)findViewById(R.id.doctourButton);
				button.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						val=editText.getText().toString().trim();
						String name1=Map.doctorJsonBack(val);
						Toast.makeText(ElevenActivity.this, "���ڻظ�.....", Toast.LENGTH_LONG);
						String url1="http://222.88.48.186:8098/sims/medicationService.do?method=expertMessage_Var";
						ShowRun run1=new ShowRun(name1, url1, handler2);
						Thread thread=new Thread(run1);
						thread.start();
					}
				});
				//������ʾ
				//��ʾ�����
				dialog=new ProgressDialog(ElevenActivity.this);
				dialog.setMessage("���ڼ���");
				dialog.show();
				String father=	Config.workss.getFatherid();
				String name=Map.qingqiuJson1(father);
				Log.i("xxx", name);
				String url="http://222.88.48.186:8098/sims/medicationService.do?method=expertDetailCommunicat";
				ShowRun run1=new ShowRun(name, url, handler);
				Thread thread=new Thread(run1);
				thread.start();
                //���ذ�ť
				button2=(ImageButton)findViewById(R.id.zixunxiangqinghoutui);
				button2.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						finish();
					}
				});

			}

}
