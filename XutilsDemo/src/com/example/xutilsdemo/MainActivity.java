package com.example.xutilsdemo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.demo.app.AppTokenUtils;
import com.demo.app.GetZip;
import com.demo.app.Jieya;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.read.http.AsyncHttpClient;
import com.read.http.AsyncHttpResponseHandler;
import com.read.http.RequestParams;
import com.xutils.db.PersonBean;

public class MainActivity extends Activity {
	@ViewInject(R.id.ceshitext)
	private TextView ceshitext;
	@ViewInject(R.id.button1)
	private Button button1;




	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(MainActivity.this);
		String sd =Environment.getExternalStorageDirectory() + "/longyuan_android/企业内刊-睿月刊.zip";
		String fi =Environment.getExternalStorageDirectory() + "/longyuan_android/what";
		File file=new File(sd);
		
		try {
			GetZip.upZipFile(file, fi);
		} catch (ZipException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
//		
		
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				HttpUtils httpUtils=new HttpUtils();
				com.lidroid.xutils.http.RequestParams params=new com.lidroid.xutils.http.RequestParams();
				
				httpUtils.send(HttpMethod.GET, "", params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				
				
				
				
				
				
				//http://ip:port/mqh/phone/enterprise?appToken=fadfdasfdasfdsafdsfdasfd	
				
//				AppTokenUtils.appTokenGenerate(, 1);
//				String sd =Environment.getExternalStorageDirectory() + "/longyuan_android/企业内刊-睿月刊.zip";
//				String fi =Environment.getExternalStorageDirectory() + "/longyuan_android/企业内刊-睿月刊";
//				File file=new File(sd);
//				try {
//					Jieya.upZipFile(file,fi);
//				} catch (ZipException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
//				Date date=new Date();
//				date.getTime();
//				String string=AppTokenUtils.appTokenGenerate(date.getTime(), 1);
				
//				AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
//				String urlString="http://192.168.50.200:8080/mqh/phone/book/232";
//				RequestParams params=new RequestParams();
//				params.put("appToken",string);
//				asyncHttpClient.post(urlString,params,new AsyncHttpResponseHandler(){
//
//					@Override
//					public void onSuccess(String content) {
//						super.onSuccess(content);
//						LogUtils.i("返回值="+content);
//					}});
				
				//---------------------------------------------------------------
//				AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
//				String urlString="http://123.57.205.126/mqh/phone/book/645";
//				RequestParams params=new RequestParams();
//				params.put("appToken","YmY0ZTM2NDItOGViYi00MTE5LTljNjctY2VmODU5ZTcyNTI1");
//				asyncHttpClient.post(urlString,new AsyncHttpResponseHandler(){
//					
//					@Override
//					public void onSuccess(String content) {
//						super.onSuccess(content);
//						LogUtils.i("����="+content);
//					}});
				
			}
		});
		



	}
	private void startdb() {
		//������ʹ�þ���������д��仰
		DbUtils utils=DbUtils.create(MainActivity.this);


		try {
			/**
			 * ����ǵ�һ�Ρ���ô��Ӧ�ô�������?������ʾ����personbean��ı�
			 */
			utils.createTableIfNotExist(PersonBean.class);
			/**
			 * ��ӷ���
			 * ��personbean��ֵ��Ȼ��save�ͺ�
			 */

			PersonBean bean=new PersonBean();
			bean.setNameString("С��a");
			bean.setAge(29);
			utils.save(bean);

			/**
			 * ɾ��
			 * ��������Ҫ�ҵ�Ҫɾ������Ǹ�ֵ������findfirst�����ҵ����bean��Ȼ��ɾ�����bean�ͺ�
			 */
			PersonBean bea=utils.findFirst(Selector.from(PersonBean.class).where("age","=","21"));
			utils.delete(bea);

			/**
			 * �޸ķ���
			 * ����Ҳ�����ҵ�Ҫ�޸ĵ����ֵ��Ȼ���ڸ����������޸ĵ�ֵ
			 */
			PersonBean beas=utils.findFirst(Selector.from(PersonBean.class).where("age","=","21"));
			bea.setAge(28);
			utils.update(bea);

			/**
			 * ��ѯ����
			 * 
			 */
			List<PersonBean> personBeans=utils.findAll(PersonBean.class);
			for(int c=0;c<personBeans.size();c++){
				Log.i("chenghao", "name="+personBeans.get(c).getNameString());
				Log.i("chenghao", "age="+personBeans.get(c).getAge());
				Log.i("chenghao", "id="+personBeans.get(c).getId());
			}


			utils.close();

		} catch (DbException e) {
			e.printStackTrace();
		}

	}


}
