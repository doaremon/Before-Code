package com.u4.home.call;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.u4.home.R;
import com.u4.home.command.CommandSender;
import com.u4.home.command.TCPMessage;
import com.u4.home.common.Appcontext;
import com.videoServices.VideoActivity;
/**
 * 这个是新的通话接通界面，以前的Tonghuashiping这个已经废弃掉 
 * 
 * @author Administrator
 *
 */
public class Myviewpage extends VideoActivity{
	private static Myviewpage _this;
	private Context context;

	private ArrayList<View> mListViews;
	private int call;
	private String targetip, targetname;
	private PageuserBroadcast broadcast;

	public Myviewpage(){
		_this = this;
		context = Myviewpage.this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myviewpage);

		System.out.println("进入myviewpage页面");

		//注册广播
		broadcast = new PageuserBroadcast();
		this.registerReceiver(broadcast, new IntentFilter("pagecalluser"));

		//得到intent传来的数据
		Intent intent = getIntent();
		call = Integer.parseInt(intent.getStringExtra("type"));
		targetip = getIntent().getStringExtra("targetip");
		targetname = intent.getStringExtra("targetname");
		Log.i("Call MyViewPage", "type:"+call+", targetip : "+targetip+", targetname:"+targetname);
		toast("type:"+call+", targetip : "+targetip+", targetname:"+targetname);
		//当是物业发来的请求，则从新加载新的页面
		if("manager".equals(targetname)){
			Log.i("com.u4", "进入的是manager");
			//----------------------添加viewpage--------------------------
			LayoutInflater mInflater = getLayoutInflater();
			View viewPagerNews1 = mInflater.inflate(R.layout.myview3, null);
			View viewPagerNews2 = mInflater.inflate(R.layout.myview4, null);

			mListViews = new ArrayList<View>();
			mListViews.add(viewPagerNews1);
			mListViews.add(viewPagerNews2);

			ViewPager vp = (ViewPager) findViewById(R.id.vp);
			vp.setAdapter(new MPagerAdapter());


			//找到对应的布局
			SurfaceView sf1 = (SurfaceView) viewPagerNews2.findViewById(R.id.myview);
			SurfaceView sf2 = (SurfaceView) viewPagerNews2.findViewById(R.id.preview);
			this.setCameraPreviewHolder(sf2.getHolder());
			this.setSurfaceHolder(sf1);

			//开锁按钮
			Button btn_open=(Button)viewPagerNews1.findViewById(R.id.btn_open);
			btn_open.setVisibility("door".equals(targetname) ? View.VISIBLE : View.GONE);
			btn_open.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					callOpenDoor();
				}
			});

			//接听按钮
			Button btn_accept=(Button)viewPagerNews1.findViewById(R.id.btn_accept);
			btn_accept.setVisibility("door".equals(targetname) ? View.VISIBLE : View.GONE);
			btn_accept.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					_this.startSendService();
					Log.i("Call Activity", "["+call+"] startSendService");
					callAccept();
				}
			});

			//挂断按钮
			Button btn_hangup=(Button)viewPagerNews1.findViewById(R.id.btn_hangup);
			btn_hangup.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					destroy();
				}
			});

			TextView tv_target=(TextView)viewPagerNews1.findViewById(R.id.tv_target);
			if("manager".equals(targetname)){
				tv_target.setText("管理中心");
			}else if("door".equals(targetname)){
				tv_target.setText("单元门");
			}else{
				tv_target.setText(targetname);
			}

			start();

		}else{
			Log.i("com.u4", "进入的是普通");
			//----------------------添加viewpage--------------------------
			LayoutInflater mInflater = getLayoutInflater();
			View viewPagerNews1 = mInflater.inflate(R.layout.myview1, null);
			View viewPagerNews2 = mInflater.inflate(R.layout.myview2, null);

			mListViews = new ArrayList<View>();
			mListViews.add(viewPagerNews1);
			mListViews.add(viewPagerNews2);

			ViewPager vp = (ViewPager) findViewById(R.id.vp);
			vp.setAdapter(new MPagerAdapter());

			//int videoWidth=intent.getIntExtra("VideoWidth",352);
			//int videoHeight=intent.getIntExtra("VideoHeight",288);

			//找到对应的布局
			SurfaceView sf1 = (SurfaceView) viewPagerNews1.findViewById(R.id.myview);
			SurfaceView sf2 = (SurfaceView) viewPagerNews2.findViewById(R.id.preview);
			this.setCameraPreviewHolder(sf2.getHolder());
			this.setSurfaceHolder(sf1);

			//开锁按钮
			Button btn_open=(Button)viewPagerNews1.findViewById(R.id.btn_open);
			btn_open.setVisibility("door".equals(targetname) ? View.VISIBLE : View.GONE);
			btn_open.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					callOpenDoor();
				}
			});

			//接听按钮
			Button btn_accept=(Button)viewPagerNews1.findViewById(R.id.btn_accept);
			btn_accept.setVisibility("door".equals(targetname) ? View.VISIBLE : View.GONE);
			btn_accept.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					_this.startSendService();
					Log.i("Call Activity", "["+call+"] startSendService");
					callAccept();
				}
			});

			//挂断按钮
			Button btn_hangup=(Button)viewPagerNews1.findViewById(R.id.btn_hangup);
			btn_hangup.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					destroy();
				}
			});

			TextView tv_target=(TextView)viewPagerNews1.findViewById(R.id.tv_target);
			if("manager".equals(targetname)){
				tv_target.setText("管理中心");
			}else if("door".equals(targetname)){
				tv_target.setText("单元门");
			}else{
				tv_target.setText(targetname);
			}

			start();
		}

	}

	public  void start(){
		this.setmFrequency(8);
		this.setmAudioFormat("aac");
		//352.288
		this.setmVideoWidth(352);
		this.setmVideoHeight(288);
		this.setmClientIp(targetip);
		this.setmAudioOnly(false);
		this.setmBitrate(250000);
		//this.setmVideoParam("rtsp://192.168.1.189:554/mpeg4cif");
		//this. setmVideoFormat("?h263") ;
		//this.setmVideoParam("?h263");

		if("door".equals(targetname)) {
			this.startReceiveService();
		} else {
			this.startSendService();
			Log.i("com.u4", "["+call+"] startSendService");
			if(call == 1) { //主叫 
				this.startReceiveService();
				Log.i("com.u4", "["+call+"] startReceiveService");
			}
			callAccept();
		}
	}

	private void callAccept() {
		CommandSender commandSender = new CommandSender();
		TCPMessage msg = new TCPMessage();
		msg.setType("2");
		msg.setIp(targetip);
		msg.setTime(Appcontext.mainInstance.getLocalIp());
		msg.setContent(Appcontext.roomNumber);
		commandSender.sendVideoChatCommand(msg);
	}

	private void callOpenDoor() {
		CommandSender commandSender = new CommandSender();
		TCPMessage msg = new TCPMessage();
		msg.setType("9");
		msg.setIp(targetip);
		commandSender.sendVideoChatCommand(msg);
	}

	class PageuserBroadcast extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			int type = Integer.parseInt(intent.getStringExtra("type"));
			Log.i("com.u4", "["+call+"] type="+type);
			switch (type) {
			case 2:
				_this.startReceiveService();
				Log.i("com.u4", "["+call+"] startReceiveService");
				break;
			case 3:
			case 4:
			case 5:
				destroy();
				break;
			default:
				break;
			}
		}
	}

	//page的适配器
	public class MPagerAdapter extends PagerAdapter{
		@Override
		public int getCount() {
			return 2;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager) container).addView(mListViews.get(position), 0);
			return mListViews.get(position);
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager) container).removeView(mListViews.get(position));
		}
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}
	}

	private void destroy() {
		unregisterReceiver(broadcast);

		CommandSender commandSender = new CommandSender();
		TCPMessage msg = new TCPMessage();
		msg.setType("3");
		msg.setIp(targetip);
		commandSender.sendVideoChatCommand(msg);

		Appcontext.busy = "";

		toast("通话结束");
		finish();
	}

	private void toast(String content) {
		Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
	}
}
