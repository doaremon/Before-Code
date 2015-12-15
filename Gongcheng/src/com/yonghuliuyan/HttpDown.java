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
		//生成NameValuePair对象存放参数
		
		NameValuePair nameValuePair=new BasicNameValuePair("key", name);
		//生成List<NameValuePair>对象，存放NameValuePair对象
		List<NameValuePair> list=new ArrayList<NameValuePair>();
		list.add(nameValuePair);
		
		//生成HttpEntity对象，传入参数List<NameValuePair>对象
		HttpEntity entity=new UrlEncodedFormEntity(list,HTTP.UTF_8);
		//生成HttpPost对象，并传入参数：请求的服务器地址；并设置HttpEntity：httppost.setEntity(requesthttpEntity);
		HttpPost httpPost=new HttpPost(url);
		httpPost.setEntity(entity);
		
		//生成HttpClient对象，用来发送请求
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
			return "出错";
			
		}
		


	}
}
