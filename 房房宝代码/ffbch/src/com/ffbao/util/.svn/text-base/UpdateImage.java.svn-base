package com.ffbao.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ffbao.APP;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;
/**
 * 
 * @FileName:UpdateImage.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:UpdateImage.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 图片类型
 */
public class UpdateImage {
	/**
	 * 
	 * @Deprecatred: 与服务接口不匹配
	 * @param context
	 * @param fileName
	 * @param bitmap
	 * @param callBackListener
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:上传图片接口
	 */
	@Deprecated
	public static void setUpdateImage(final Context context,
			final String fileName, final Bitmap bitmap,
			final CallBackListener callBackListener) {
		APP.getInstance().getExecutor().submit(new Runnable() {

			@Override
			public void run() {

				try {
					String boundary = "---------------------------7d33a816d302b6";
					String upLoadServerUri = SharedPrefConstance
							.getStringValue(context,
									SharedPrefConstance.myselfimg);
					String userid = SharedPrefConstance.getStringValue(context,
							SharedPrefConstance.userid);
					String UUID = SharedPrefConstance.getStringValue(context,
							SharedPrefConstance.UUID);
					// TODO 这个地方 因为 上传头像路径错误不能正常调试
					upLoadServerUri = UrlUtil.URL;
					URL url = new URL(upLoadServerUri);
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection(); // Open
					conn.setDoInput(true); // Allow Inputs
					conn.setDoOutput(true); // Allow Outputs
					conn.setUseCaches(false); // Don't use a Cached Copy
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Connection", "Keep-Alive");
					// conn.setRequestProperty("ENCTYPE",
					// "multipart/form-data");
					conn.setRequestProperty("Content-Type",
							"multipart/form-data;boundary=" + boundary);
					// conn.setRequestProperty("filename", fileName);
					conn.setRequestProperty("x-myapp-param1",
							"Parameter 1 text");
					conn.setRequestProperty("userid", userid);
					conn.setRequestProperty("UUID", UUID);
					conn.setRequestProperty("filename", "liwei");
					conn.setRequestProperty("commandText",
							UrlUtil.userUpdatePersonImg);// +UrlUtil.userUpdatePersonImg

					DataOutputStream dos = new DataOutputStream(conn
							.getOutputStream());
					dos.writeBytes(boundary);

					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
					dos.write(baos.toByteArray());
					dos.writeBytes("Content-Disposition: form-data; name=\"file_name\";filename=\""
							+ fileName);

					conn.setConnectTimeout(30000);
					conn.setReadTimeout(30000);
					conn.connect();
					if (callBackListener == null) {
						if (conn.getResponseCode() == 200) {

							InputStream inStream = conn.getInputStream();
							String msg = new String(StreamTool
									.readInputStream(inStream));
							ExecuteJSONUtils.UpdatePersonImg(context, msg);

						} else {
							ToastUtils.showToast(context, "网络异常 请重试！xxxxxxx");
						}
					} else {
						if (conn.getResponseCode() == 200) {
							InputStream inStream = conn.getInputStream();
							String msg = new String(StreamTool
									.readInputStream(inStream));
							// callBackListener.onSuccess(msg);

						} else {
							ToastUtils.showToast(context, "网络异常 请重试！xxxxxxx");
						}
					}
					baos.flush();
					baos.close();

					dos.flush();
					dos.close();

				} catch (Exception e) {
					callBackListener.onFailure(e, "网络异常 请重试！xxxxxxx");
					e.printStackTrace();
				}
			}
		});

	}
	/**
	 * 
	 * @Deprecatred:
	 * @param filePath
	 * @param context
	 * @param asyncHttpResponseHandler
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: 上传调用方法
	 */
	public static void uploadFile(File filePath, final Context context,AsyncHttpResponseHandler asyncHttpResponseHandler) {

		// String upLoadServerUri = UrlUtil.URL;
		// String userid = SharedPrefConstance.getStringValue(context,
		// SharedPrefConstance.userid);
		// String UUID = SharedPrefConstance.getStringValue(context,
		// SharedPrefConstance.UUID);
		// /**
		// * conn.setRequestProperty("userid", userid);
		// conn.setRequestProperty("UUID", UUID);
		// conn.setRequestProperty("filename", "liwei");
		// conn.setRequestProperty("commandText",
		// UrlUtil.userUpdatePersonImg);
		// */
		// RequestParams params = new RequestParams();
		// params.addQueryStringParameter(Constant.userid, userid);
		// params.addQueryStringParameter(Constant.UUID,UUID);
		// params.addQueryStringParameter(Constant.commandText,
		// UrlUtil.userUpdatePersonImg);
		// params.addQueryStringParameter("filename", "photo");
		// params.addBodyParameter("path", url);
		// HttpUtils http = new HttpUtils();
		// http.send(HttpRequest.HttpMethod.POST,
		// upLoadServerUri, params,
		// new RequestCallBack<String>() {
		//
		// @Override
		// public void onFailure(HttpException arg0, String arg1) {
		//
		// ToastUtils.showToast(context, "失败了");
		// }
		//
		// @Override
		// public void onSuccess(ResponseInfo<String> arg0) {
		//
		//
		// ToastUtils.showToast(context, "成功了");
		// }
		//
		// });
		// RequestParams params = new RequestParams();
		// params.put("file", file);
		// new
		// AsyncHttpClient().post("http://192.168.1.200:8080/13.Web/UploadServlet",
		// params , new AsyncHttpResponseHandler(){
		// public void onSuccess(String content) {
		// Toast.makeText(getApplicationContext(), "上传成功!",
		// Toast.LENGTH_SHORT).show();
		// }
		// });

		try {
			AsyncHttpClient httpClient = new AsyncHttpClient();

			String userid = SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.userid);
			String UUID = SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.UUID);
			String url = UrlUtil.URL;
			RequestParams param = new RequestParams();
			param.put(Constant.userid, userid);
			param.put(Constant.UUID, UUID);
			param.put(Constant.commandText, UrlUtil.userUpdatePersonImg);
			param.put("filename", "photo");
			param.put("file", filePath);

			httpClient.post(url, param, asyncHttpResponseHandler);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
