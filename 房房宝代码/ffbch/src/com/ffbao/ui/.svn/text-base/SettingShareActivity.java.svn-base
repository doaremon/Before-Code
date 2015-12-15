package com.ffbao.ui;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.sina.weibo.SinaWeibo.ShareParams;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;
import cn.sharesdk.wechat.utils.WechatResp;

import com.ffbao.activity.R;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
import com.google.zxing.BarcodeFormat;
import com.lidroid.xutils.BitmapUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @FileName:SettingShareActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SettingShareActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 邀请好友安装
 */
public class SettingShareActivity extends BaseActivity implements
OnClickListener {

	private TextView dialog_share_moments, dialog_share_weixin,
	dialog_share_weibo;
	private TextView shareSMS;
	private TextView shareMoments;
	private TextView shareWeiXin;
	private TextView shareWeiBo;
	private ImageView img;
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_setting_share);
		ShareSDK.initSDK(this);
		setActionBarTitle("邀请好友");
		ExitActivity.addActivity(this);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		dialog_share_moments = (TextView) findViewById(R.id.dialog_share_moments);
		dialog_share_weixin = (TextView) findViewById(R.id.dialog_share_weixin);
		dialog_share_weibo = (TextView) findViewById(R.id.dialog_share_weibo);
		dialog_share_moments.setOnClickListener(this);
		dialog_share_weixin.setOnClickListener(this);
		dialog_share_weibo.setOnClickListener(this);
		// TODO Auto-generated method stub
		shareSMS = (TextView) findViewById(R.id.dialog_share_sms);
		shareMoments = (TextView) findViewById(R.id.dialog_share_moments);
		shareWeiXin = (TextView) findViewById(R.id.dialog_share_weixin);
		shareWeiBo = (TextView) findViewById(R.id.dialog_share_weibo);
		img = (ImageView) findViewById(R.id.img);

		BitmapUtils bitmapUtils = new BitmapUtils(this);
		String ffbPath = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.ffb_tdbarcode_path);
		bitmapUtils.clearCache(ffbPath);
		bitmapUtils.display(img, ffbPath);
		shareSMS.setOnClickListener(this);
	}

	private void sionShare() {
		new AlertDialog.Builder(this)
		.setTitle("新浪分享")
		.setMessage("确认分享？")
		.setNegativeButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
//SharedPrefConstance.getStringValue(
//				SettingShareActivity.this,
//				SharedPrefConstance.myselfname)
				ShareParams sp = new ShareParams();
				
				sp.setText("Hi!房房宝APP不错，面向旅游地产，楼盘多、结佣快、服务又到位，推荐你试试！下载链接:http://d.fangfangbao.com");
				sp.setImagePath("http://img3.douban.com/f/fm/6b922536970a3676b4c0b1d464bac01d5573f22f/pics/fm/home/fm_logoV1.png");
				Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
				weibo.setPlatformActionListener(new PlatformActionListener() {

					@Override
					public void onError(Platform arg0, int arg1,
							final Throwable arg2) {

						handler.post(new Runnable() {

							@Override
							public void run() {
								showlog("这个是错误信息="+arg2.getLocalizedMessage());
								Toast.makeText(
										SettingShareActivity.this,
										"新浪分享失败", Toast.LENGTH_LONG)
										.show();

							}
						});
					}

					@Override
					public void onComplete(Platform arg0, int arg1,
							HashMap<String, Object> arg2) {

					}

					@Override
					public void onCancel(Platform arg0, int arg1) {

					}
				}); // 设置分享事件回调
				// 执行图文分享
				weibo.share(sp);
			}
		})
		.setPositiveButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		}).create().show();
	}

	@Override
	public void onClick(View v) {

		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.dialog_share_sms:
			Uri uri = Uri.parse("smsto:");
			intent = new Intent(Intent.ACTION_SENDTO, uri);
			intent.putExtra("sms_body", "Hi!房房宝APP不错，面向旅游地产，楼盘多、结佣快、服务又到位，推荐你试试！下载链接:http://d.fangfangbao.com");
			startActivity(intent);

			break;
		case R.id.dialog_share_moments:
			friendShare();
			break;
		case R.id.dialog_share_weixin:

			weiShare();
			break;
		case R.id.dialog_share_weibo:

			sionShare();
			break;

		default:
			break;
		}
	}

	private void friendShare() {
		ShareParams sp = new ShareParams();
		sp.setShareType(Platform.SHARE_WEBPAGE);
		sp.setImageUrl("http://f.fangfangbao.com/androidshare.png");
//		sp.setTitle("测试");// 分享的标题
		sp.setText("Hi!房房宝APP不错，面向旅游地产，楼盘多、结佣快、服务又到位，推荐你试试！下载链接:http://d.fangfangbao.com");
//		sp.setUrl("http://www.kencoo.com/content/share/video/20141014150409292160711");
		Platform weibo = ShareSDK.getPlatform(WechatMoments.NAME);
		weibo.setPlatformActionListener(new PlatformActionListener() {

			@Override
			public void onError(Platform arg0, int arg1, Throwable arg2) {

				handler.post(new Runnable() {

					@Override
					public void run() {

						// Toast.makeText(SettingShareActivity.this, "操作错误",
						// Toast.LENGTH_LONG).show();
						HttpClientRequest.checkNetworkStatus(context);

					}
				});
			}

			@Override
			public void onComplete(Platform arg0, int arg1,
					HashMap<String, Object> arg2) {
				//				handler.post(new Runnable() {
				//
				//					@Override
				//					public void run() {
				//						Toast.makeText(SettingShareActivity.this, "操作成功",
				//								Toast.LENGTH_LONG).show();
				//					}
				//				});
			}

			@Override
			public void onCancel(Platform arg0, int arg1) {
				//				handler.post(new Runnable() {
				//
				//					@Override
				//					public void run() {
				//						Toast.makeText(SettingShareActivity.this, "操作取消",
				//								Toast.LENGTH_LONG).show();
				//					}
				//				});
			}
		}); // 设置分享事件回调
		// 执行图文分享
		weibo.share(sp);

	}

	private void weiShare() {

		// Toast.makeText(SettingShareActivity.this, "这个是微信分享", 1).show();

		Wechat.ShareParams sp = new Wechat.ShareParams();
		sp.setShareType(Platform.SHARE_WEBPAGE);
		//房房宝经纪人app不错，楼盘多、结佣快、服务又到位,推荐你试试！下载链接:http://d.fangfangbao.com
		sp.setText("Hi!房房宝APP不错，面向旅游地产，楼盘多、结佣快、服务又到位，推荐你试试！下载链接:http://d.fangfangbao.com");
		// sp.setImagePath("/mnt/sdcard/测试分享的图片.jpg");
		Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
		wechat = ShareSDK.getPlatform("Wechat");
		Log.i("chenghao", "微信分享：wechat="+wechat);
		wechat.setPlatformActionListener(new PlatformActionListener() {

			public void onError(final Platform platform, final int action,
					final Throwable t) {
				// 操作失败的处理代码

				// Message msg = handler.obtainMessage();
				// msg.what = SHARE_FAIL;
				// msg.arg1 = action;
				// msg.obj = t;
				// MainActivity.this.handler.sendMessage(msg);
				handler.post(new Runnable() {

					@Override
					public void run() {

						Toast.makeText(SettingShareActivity.this, "微信分享失败",
								Toast.LENGTH_LONG).show();

					}
				});
			}

			public void onComplete(Platform platform, int action,
					HashMap<String, Object> res) {
				// 操作成功的处理代码
				//				handler.post(new Runnable() {
				//
				//					@Override
				//					public void run() {
				//						Toast.makeText(SettingShareActivity.this, "操作成功",
				//								Toast.LENGTH_LONG).show();
				//					}
				//				});
			}

			public void onCancel(Platform platform, int action) {
				// 操作取消的处理代码
				//				handler.post(new Runnable() {
				//
				//					@Override
				//					public void run() {
				//						Toast.makeText(SettingShareActivity.this, "操作取消",
				//								Toast.LENGTH_LONG).show();
				//					}
				//				});
			}
		}); // 设置分享事件回调
		// 执行图文分享
		wechat.share(sp);
	}

}
