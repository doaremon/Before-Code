package com.yonghuliuyan;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.util.Log;

public class HttpDown {
	public static String dow(String name,String url) throws ClientProtocolException, IOException{
		//����NameValuePair�����Ų���
		
		NameValuePair nameValuePair=new BasicNameValuePair("key", name);
		//����List<NameValuePair>���󣬴��NameValuePair����
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(nameValuePair);
		
		//����HttpEntity���󣬴������List<NameValuePair>����
		HttpEntity entity=new UrlEncodedFormEntity(list,HTTP.UTF_8);
		//����HttpPost���󣬲��������������ķ�������ַ��������HttpEntity��httppost.setEntity(requesthttpEntity);
		HttpPost httpPost=new HttpPost(url);
		httpPost.setEntity(entity);
		
		//����HttpClient����������������
		HttpClient http=new DefaultHttpClient(); 
		HttpResponse httpResponse= http.execute(httpPost);
		if(httpResponse.getStatusLine().getStatusCode()==200){
			InputStream inputStream= httpResponse.getEntity().getContent();
			InputStreamReader inputStreamReader=new InputStreamReader(inputStream,HTTP.UTF_8);
			StringBuffer buffer=new StringBuffer();
			int coo=0;
			char[] ch=new char[1024];
             
			while((coo=inputStreamReader.read(ch, 0, ch.length))!=-1){
				buffer.append(ch, 0, coo);
			}
			String s=new String(buffer);
			
			return s;
			
		}
		else{
			return "����";
			
		}
		


	}
}
