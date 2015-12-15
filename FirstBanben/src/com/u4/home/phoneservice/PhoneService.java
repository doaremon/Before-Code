package com.u4.home.phoneservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Thread.State;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.u4.home.common.Appcontext;
import com.u4.home.db.Equipment_DB;
import com.u4.home.db.Theme_DB;
/**
 * 手机服务
 * @author Administrator
 *
 */
public class PhoneService extends Service{
	private ServerSocket ss;

	private JSONArray jsonDataswitch,jsonDatacurtain,jsonDatalight,jsonDatainfrared,jsonDatatheme;

	private InputStreamReader inputStreamReader;

	private BufferedReader bufferedReader;

	private String request;
	private Equipment_DB db;
	private Theme_DB tmdb;

	private String mac, indexname,value;
	private Thread thread;

	private Boolean detectionThread=false;
	JSONObject requestjsonObject = null;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		showlog( "手机服务端启动");
		super.onCreate();
		db = new Equipment_DB(PhoneService.this);
		tmdb = new Theme_DB(PhoneService.this);
		startphoneservice(10086);
		servicestate();
	}
	/**
	 * 启动手机服务的handler
	 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			showlog("页面收到请求指令为  ：" + request);
			if ("sendlight".equals(indexname) || "sendswitch".equals(indexname) || "sendcurtain".equals(indexname) ) {
				try {
					JSONObject jsonObject = new JSONObject(request);

					JSONObject objectsend=new JSONObject(jsonObject.getString("value"));

					String cmd = Appcontext.serviceUSB.createData(objectsend.getInt("type_id"), objectsend.getString("code"), objectsend.getString("unit"), objectsend.getInt("status"));
					showlog("发出的指令为  type_id ：" + objectsend.getInt("type_id"));
					showlog("发出的指令为  code ：" + objectsend.getString("code"));
					showlog("发出的指令为  unit ：" + objectsend.getString("unit"));
					showlog("发出的指令为  status ：" + objectsend.getInt("status"));
					showlog("发出的指令为：" + cmd);
					//					showToast("发出的指令为：" + cmd);
					db.equipmentSet(objectsend.getInt("id"), objectsend.getInt("status"));
					if (Appcontext.serviceUSB != null && cmd.length() == 22)
						Appcontext.serviceUSB.sendData(cmd);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}else if("sendtheme".equals(indexname)){
				try {
					JSONObject jsonObjectsendtheme = new JSONObject(request);
					final JSONArray jsonTheme = new JSONArray(jsonObjectsendtheme.getString("value"));
					showlog("array="+jsonTheme.toString());
					Runnable runnable=new Runnable() {
						@Override
						public void run() {
							JSONObject item;
							for (int i = 0; i < jsonTheme.length(); i++) {
								try {
									item = new JSONObject(jsonTheme.get(i).toString());
									String cmd = Appcontext.serviceUSB.createData(item.getInt("type"), item.getString("code"), item.getString("unit"), item.getInt("status"));
									showlog("发出的指令为  type ：" + item.getInt("type"));
									showlog("发出的指令为  code ：" + item.getString("code"));
									showlog("发出的指令为  unit ：" + item.getString("unit"));
									showlog("发出的指令为  status ：" + item.getInt("status"));
									showlog("发出的指令为：" + cmd);
									if (Appcontext.serviceUSB != null && cmd.length() == 22){
										Appcontext.serviceUSB.sendData(cmd);
									}
									//						Thread.sleep(200);
								} catch (JSONException e) {
									e.printStackTrace();
								} 
							}
						}
					};
					Thread thread=new Thread(runnable);
					thread.start();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}else if("sendinfrared".equals(indexname)){
				try {
					JSONObject jsonObject=new JSONObject(request);
					JSONObject jsonvalue=new JSONObject(jsonObject.getString("value"));
					int type_id=jsonvalue.getInt("type_id");
					String code=jsonvalue.getString("code");
					int cmd=jsonvalue.getInt("cmd");
					String temp=jsonvalue.getString("temp");
					String data = Appcontext.serviceUSB.createData(type_id, code, "", cmd, temp);
					if (Appcontext.serviceUSB != null && data.length() == 22)
						Appcontext.serviceUSB.sendData(data);

					showlog("发出的指令为：" + data);
					//					showToast("发出的指令为：" + data);
				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

		}
	};

	/**
	 * 启动手机服务
	 */
	public void startphoneservice(final int prot) {
		db.open();
		tmdb.open();
		Runnable runnable=new Runnable() {

			@Override
			public void run() {
				try {
					if (ss == null) {
						ss = new ServerSocket(prot);
						System.out.println("服务器启动...");
					}
					// 服务器无穷的循环等待客户端的请求
					while (true) {
						/*
						 * accept()方法会在等待用户的socket连接时闲置着，当用户链接
						 * 上来时，此方法会返回一个socket(在不同的端口上)以便与客户端
						 * 通信。Socket与ServerSocket的端口不同，因此ServerSocket可以
						 * 空闲出来等待其他客户端
						 */
						// 这个方法会停下来等待要求到达之后再继续
						Socket s = ss.accept();
						inputStreamReader = new InputStreamReader(s.getInputStream(), "UTF-8");
						bufferedReader = new BufferedReader(inputStreamReader);
						request = bufferedReader.readLine();
						handler.sendEmptyMessage(0);
						showlog("接收到了客户端的请求:" + request);

						requestjsonObject=null;

						try {
							requestjsonObject = new JSONObject(request);
							showlog("requestjsonObject ="+requestjsonObject);
							indexname = requestjsonObject.getString("indexname");
							mac = requestjsonObject.getString("mac");
							value=requestjsonObject.getString("value");
						} catch (JSONException e1) {
							e1.printStackTrace();
						}

						PrintWriter printWriter = new PrintWriter(s.getOutputStream());

						JSONObject jsonObject = new JSONObject();
						//当requestjsonObject为null的时候，则执行
						if (requestjsonObject == null) {
							try {
								jsonObject.put("mac", "error");
								jsonObject.put("indexname", "error");
								jsonObject.put("value", "error requestjsonObject==null");
							} catch (JSONException e) {
								e.printStackTrace();
							}
						} 
						//当requestjsonObject不为null的时候，则开始判断mac地址，如果mac对应的话，则执行if，否则执行else
						else {
							if(Appcontext.getInstance().getDeviceCode().equals(mac)){
								if("select".equals(indexname)){
									jsonDatalight=db.equipmentList(1);
									jsonDataswitch=db.equipmentList(2);
									jsonDatacurtain=db.equipmentList(3);
									jsonDatainfrared=db.equipmentList(4);
									jsonDatatheme=tmdb.themeList();

									JSONArray array=new JSONArray();
									array.put(jsonDatalight);
									array.put(jsonDataswitch);
									array.put(jsonDatacurtain);
									array.put(jsonDatainfrared);
									array.put(jsonDatatheme);
									try {
										jsonObject.put("mac", mac);
										jsonObject.put("indexname", "select");
										jsonObject.put("value", array);
									} catch (JSONException e) {
										e.printStackTrace();
									}
								}else if("sendlight".equals(indexname)||"sendswitch".equals(indexname)||"sendcurtain".equals(indexname)||"sendinfrared".equals(indexname)||"sendtheme".equals(indexname)){
									System.out.println("进入发送返回");
									try {
										jsonObject.put("mac", mac);
										jsonObject.put("indexname", indexname);
										jsonObject.put("value", "ok");
									} catch (JSONException e) {
										e.printStackTrace();
									}
								}
							}else{
								try {
									jsonObject.put("mac", "error");
									jsonObject.put("indexname", "error");
									jsonObject.put("value", "error  mac no");
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						}
						printWriter.println(jsonObject);
						printWriter.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
					showlog("抛出异常========================="+e.toString());
					detectionThread=true;
					thread.interrupt();
					thread=null;
				}	
			}
		};
		thread=new Thread(runnable);
		thread.start();
	}

	//开启一个timer一直跑，来检测手机服务是否挂掉。当服务突然挂掉的时候，则开始重启

	Handler handler2=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			showlog("循环线程检测中   ：  "+msg.obj);
			if(detectionThread){
				detectionThread=false;
				showlog("重新启动手机服务中。。。。。。。。。 ");
				startphoneservice(10086);
			}
		}};
		public void servicestate(){
			Timer timer=new Timer();
			timer.schedule(new TimerTask() {

				@Override
				public void run() {
					Message message=new Message();
					if(thread!=null){
						State s=thread.getState();
						message.obj=s;
					}else {
						message.obj="thread 为null";
					}

					handler2.sendMessage(message);
				}
			}, 0, 1000*1);
		}

		public void showToast(String str){
			Toast.makeText(PhoneService.this,str, Toast.LENGTH_SHORT).show();
		}
		public void showlog(String str){
			Log.i("u4phoneservice", str);
		}

}
