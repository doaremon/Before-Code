package com.denglu;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.util.Log;

public class Httoutilsss {
	public static String fasong(String name,String uil) throws  IOException{ 
		Log.i("www", name+uil+"======");
		//����NameValuePair�����Ų���
		NameValuePair nameValuePair=new BasicNameValuePair("key", name);
		//����List<NameValuePair>���󣬴��NameValuePair����
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(nameValuePair);
		Log.i("www", list+"list-----------");
		//����HttpEntity���󣬴������List<NameValuePair>����
		HttpEntity httpEntity=new UrlEncodedFormEntity(list,HTTP.UTF_8);
		//����HttpPost���󣬲��������������ķ�������ַ��������HttpEntity��httppost.setEntity(requesthttpEntity);
		HttpPost httpPost=new HttpPost(uil);
		httpPost.setEntity(httpEntity);
		Log.i("www", httpPost+"1111111");
		//����HttpClient����������������
		HttpClient http=new DefaultHttpClient(); 

		//===========================================================

		HttpResponse httpResponse= http.execute(httpPost);

		Log.i("www",httpResponse+ "2222222222");
		//httpResponse.getEntity().getContent()�����Եõ����������ص�InputStream.
		if(httpResponse.getStatusLine().getStatusCode()==200){
			InputStream inputStream=httpResponse.getEntity().getContent();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
//			BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
//			
			StringBuffer buffer=new StringBuffer();
//			while((bufferedReader.read())!=-1){
//				buffer.append(bufferedReader.readLine());
//			} 
//			String sss=new String(buffer);
//			zuheJson json=new zuheJson();
//			String aaa=	json.zuhe(sss);
//			Log.i("www", sss+"Httoutilsss");
//			Log.i("www", aaa+"==========");
			int con=0;
			char[] buff=new char[1024];
			while((con=inputStreamReader.read(buff, 0, buff.length))!=-1){
				buffer.append(buff, 0,con);	
			}
			String str=new String(buffer);
			Log.i("www", str+"Httoutilsss");
			return str;
		}
		else{
			return "����";

		}

	}
}
