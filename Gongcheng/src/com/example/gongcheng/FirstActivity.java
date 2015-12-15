package com.example.gongcheng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.adapter.GrAdapter;
import com.app.MyApplication;
//�ҵ���ѯ���ҵĶ������������ĵ���ҳ��
public class FirstActivity extends Activity {
	private GridView gridView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		//��activity�ŵ�������
		MyApplication.getInstance().addActivity(this);
		//gridViewʹ���������̬����
		gridView = (GridView)findViewById(R.id.gridView1);

		GrAdapter adapter = new GrAdapter(FirstActivity.this);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					Intent intent=new Intent(FirstActivity.this,FourActivity.class);
					FirstActivity.this.startActivity(intent);
					
					break;
				case 1:
					Intent intent1=new Intent(FirstActivity.this,FourteenActivity.class);
					FirstActivity.this.startActivity(intent1);
					
					break;
				case 2:
					Intent intent2=new Intent(FirstActivity.this,TwentyActivity_persion.class);
					FirstActivity.this.startActivity(intent2);
					
					break;

				default:
					break;
				}
			}
		});
		
	}

	

}
