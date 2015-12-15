package com.u4.home.main;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.u4.home.R;
import com.u4.home.call.CallIndex;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.control.ControlIndex;
import com.u4.home.db.Guanggaobean;
import com.u4.home.db.Init;
import com.u4.home.media.MediaIndex;
import com.u4.home.media.Shop;
import com.u4.home.monitor.MonitorIndex;
import com.u4.home.safe.SafeIndex;


public class HomeIndex extends Base {

	//	private VideoView videoView;
	//	private ImageView imageView;
	//	private Timer timer;
	//	private int idx, sec, max, lng;

	private List<Guanggaobean> list = new ArrayList<Guanggaobean>();
	private WeatherBroadcast weatherBroadcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);

		Init db = new Init(context);
		db.open();
		db.init();
		db.close();

		startServiceUSB();

		findId();
		advertisement();

		//		videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
		//			@Override
		//			public void onPrepared(MediaPlayer mp) {
		//				mp.start();
		//				mp.setLooping(true);
		//			}
		//		});
		// Guanggaobean bean;
		// bean=new Guanggaobean();
		// bean.time=3;
		// bean.type="image";
		// bean.file="/sdcard/ad1.jpg";
		// list.add(bean);
		//
		// bean=new Guanggaobean();
		// bean.time=3;
		// bean.type="image";
		// bean.file="/sdcard/ad1.jpg";
		// list.add(bean);

		// bean=new Guanggaobean();
		// bean.time=40;
		// bean.type="video";
		// bean.file="/sdcard/ad2.mp4";
		// list.add(bean);
		//
		// bean=new Guanggaobean();
		// bean.time=40;
		// bean.type="video";
		// bean.file="/sdcard/ad3.mp4";
		// list.add(bean);
		// bean=new Guanggaobean();
		// bean.time=40;
		// bean.type="video";
		// bean.file="/sdcard/geshi1.mp4";
		// list.add(bean);
		// bean=new Guanggaobean();
		// bean.time=40;
		// bean.type="video";
		// bean.file="/sdcard/geshi2.mp4";
		// list.add(bean);
		// 
		//


		// String name="ad";
		// String url="http://192.168.1.91:8080/api";
		// ShixianRun shixianRun=new ShixianRun(name, url, handler);
		// Thread thread=new Thread(shixianRun);
		// thread.start();
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("code="+Appcontext.mainInstance.getDeviceCode());
		//		if(Appcontext.adarray!=null && Appcontext.isdowntrue){
		//			idx=0;
		//			sec=0;
		//			lng=Appcontext.adarray.length();
		//			try {
		//				max=Appcontext.adarray.getJSONObject(idx).getInt("time");
		//			} catch (JSONException e) {
		//				e.printStackTrace();
		//			}
		//			myMain();
		//		}

		weatherBroadcast = new WeatherBroadcast();
		registerReceiver(weatherBroadcast, new IntentFilter("weather"));
		registerReceiver(new adcast(), new IntentFilter("OK"));
		showLog("registerReceiver : WeatherBroadcast");

		setWeather();
	}

	public void findmac() {
		String macAddress = null;
		WifiManager wifiMgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
		if (null != info) {
			macAddress = info.getMacAddress();
		}
		showLog("mac:" + macAddress);
		showToast(macAddress);
	}

	@Override
	protected void onStop() {
		super.onStop();

		unregisterReceiver(weatherBroadcast);
		showLog("unregisterReceiver : WeatherBroadcast");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// timer.cancel();
	}

	//	private void playvideo(String path){
	//		videoView.setVideoPath(path);
	//		videoView.requestFocus();
	//		videoView.start();
	//	}
	//	Handler handler = new Handler() {
	//		@Override
	//		public void handleMessage(Message msg) {
	//			super.handleMessage(msg);
	//			if(Appcontext.adarray!=null){
	//				if (sec == 0) {
	//					showLog("执行显示");
	//					try {
	//						String path=Environment.getExternalStorageDirectory() + "/U4ad/"+Appcontext.adarray.getJSONObject(idx).getString("name");
	//						showLog("path="+path);
	//						if (Appcontext.adarray.getJSONObject(idx).getString("type") .equals("image")) {
	//							videoView.setVisibility(View.GONE);
	//							videoView.stopPlayback();
	//							imageView.setVisibility(View.VISIBLE);
	//							// imageView.setBackground(Drawable.createFromPath(list.get(idx).file));
	//							imageView.setImageBitmap(BitmapFactory.decodeFile(path));
	//						} else if (Appcontext.adarray.getJSONObject(idx).getString("type") .equals("video")) {
	//							imageView.setVisibility(View.GONE);
	//							videoView.setVisibility(View.VISIBLE);
	//							playvideo(path);
	//						}
	//					} catch (JSONException e) {
	//						e.printStackTrace();
	//					}
	//				}
	//				if (sec >= max) {
	//					idx++;
	//					if (idx >=lng)
	//						idx = 0;
	//					sec = 0;
	//					try {
	//						max = Appcontext.adarray.getJSONObject(idx).getInt("time");
	//					} catch (JSONException e) {
	//						e.printStackTrace();
	//					}
	//					showLog("if idx="+idx+",sec="+sec);
	//				} else {
	//					sec++;
	//					showLog("else ="+sec+"+sec");
	//				}
	//			}
	//
	//
	//		}
	//	};

	//	public void myMain() {
	//		timer = new Timer();
	//		timer.schedule(new TimerTask() {
	//			@Override
	//			public void run() {
	//				handler.sendMessage(new Message());
	//			}
	//		}, 0, 1000);
	//	}


	public void findId() {
		// set header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_weather, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// set main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.menu_grid, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		//		imageView = (ImageView) findViewById(R.id.mainguanggaotp);
		//		videoView = (VideoView) findViewById(R.id.mainguanggaosp);

		// set menu content
		int menu_name[] = { R.string.call_main, R.string.control_main, R.string.media_main, R.string.shop_main, R.string.safe_main, R.string.monitor_main };
		int menu_icon[] = { R.drawable.bnt_phone_07, R.drawable.bnt_dian_12, R.drawable.btn_yingy, R.drawable.btn_sheq_23, R.drawable.btn_anq, R.drawable.btn_shex_24 };
		Class<?> menu_jump[] = { CallIndex.class, ControlIndex.class, MediaIndex.class, Shop.class, SafeIndex.class, MonitorIndex.class };

		// set menu view
		GridView gvMenu = (GridView) findViewById(R.id.gv_menu);
		gvMenu.setAdapter(new MenuAdapter(menu_name, menu_icon, menu_jump));
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {// event.getAction() !=
		// KeyEvent.ACTION_UP
		// //不响应按键抬起时的动作
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && event.getAction() != KeyEvent.ACTION_UP) {
			new AlertDialog.Builder(context).setTitle("提示").setMessage("确认退出吗？").setIcon(R.drawable.ic_launcher).setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Appcontext.mainInstance.exit();
					// finish();
					// System.exit(0);
					// android.os.Process.killProcess(android.os.Process.myPid());
					// // 结束进程
				}
			}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			}).show();
			return false;
		} else {
			return super.dispatchKeyEvent(event);
		}
	}

}
