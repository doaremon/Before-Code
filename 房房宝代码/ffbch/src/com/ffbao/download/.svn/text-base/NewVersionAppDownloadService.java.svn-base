package com.ffbao.download;

import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ffbao.activity.R;
import com.ffbao.util.MyListDialog;
import com.ffbao.util.MyTextDialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;
/**
 * 
 * @FileName:NewVersionAppDownloadService.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:NewVersionAppDownloadService.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: download service
 */
public class NewVersionAppDownloadService extends Service {

	private NotificationManager mNotificationManager;
	private Notification mNotification;
	public static final int NOTIFICATION_NEW_VERSION_APP_DOWNLOAD = 50;
	private static final int MSG_DOWNLOADING = 0;
	private static final int MSG_DOWNLOADED = 1;
	private int progress = 0;
	private String appUrl = "";
	//	private final String appName = "AMH.Mobile.apk";
	private final String appName = "fangfangbao.apk";
	private int appSize = 4 * 1000 * 1000;
	private boolean isInterrupt = false;

	@Override
	public void onCreate() {

		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		super.onCreate();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		appUrl=intent.getStringExtra("AppUrl");
		Log.i("chenghao", "NewVersionAppDownloadService onStartCommand appUrl="+appUrl);
		if(appUrl!=null && appUrl.length()!=0){
			String version=android.os.Build.VERSION.SDK;
			int ver=Integer.parseInt(version);
			if(ver>14){
				setNotification();
			}
			if(!isInterrupt ){
				isInterrupt = true;

				new Thread() {

					public void run() {
						try {

							URL downloadUrl = new URL(appUrl);
							HttpURLConnection conn = (HttpURLConnection) downloadUrl
									.openConnection();
							conn.setDoInput(true);
							conn.setConnectTimeout(50 * 1000);
							conn.setReadTimeout(50 * 1000);
							conn.connect();
							InputStream inputStream = conn.getInputStream();
							appSize = conn.getContentLength();
							byte[] buffer = new byte[2048];
							int offset = 0;
							File saveFile = makeFile(appName);
							if (saveFile.exists()) {
								saveFile.delete();
							}
							saveFile.createNewFile();
							int downloadSize = 0;

							RandomAccessFile file = new RandomAccessFile(saveFile,
									"rwd");
							while ((offset = inputStream.read(buffer, 0, 2048)) != -1) {
								file.write(buffer, 0, offset);
								downloadSize = downloadSize + 2048;
								progress = downloadSize * 100 / appSize;
							}
							file.close();
							inputStream.close();
							Message msg = new Message();
							msg.what = MSG_DOWNLOADED;
							handle.sendMessage(msg);

						} catch (Exception e) {
							String version=android.os.Build.VERSION.SDK;
							int ver=Integer.parseInt(version);
							if(ver>14){
								mNotification.tickerText = "下载最新版本失败";
								mNotificationManager.notify(
										NOTIFICATION_NEW_VERSION_APP_DOWNLOAD,
										mNotification);
							}


							isInterrupt = false;
							stopSelf();
							e.printStackTrace();
						}
					}

				}.start();
			}

			new Thread() {

				public void run() {
					while (progress < 100) {
						// if(!isInterrupt){
						try {
							Message msg = new Message();
							msg.what = MSG_DOWNLOADING;
							msg.arg1 = progress;
							handle.sendMessage(msg);
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("progress in Thread:" + progress);
						// }

					}
					// Message msg = new Message();
					// msg.what = MSG_DOWNLOADED;
					// handle.sendMessage(msg);
				}

			}.start();
		}else {
			stopSelf();
		}


		return super.onStartCommand(intent, flags, startId);
	}

	// handler for updating ui
	private Handler handle = new Handler() {
		public void handleMessage(Message msg) {
			String version=android.os.Build.VERSION.SDK;
			int ver=Integer.parseInt(version);
			switch (msg.what) {
			case MSG_DOWNLOADING:
				Log.i("chenghao", "progress in handler:" + progress +"msg.arg1 :"+msg.arg1);
				if(ver>14){
					RemoteViews contentView = mNotification.contentView;
					//				contentView
					//						.setImageViewResource(R.id.appImage, R.drawable.icon);
					//				contentView
					//				.setViewPadding(R.id.appImage, 0, 0, 100, 100);

					contentView.setTextViewText(R.id.appDownloadPercent, msg.arg1
							+ "%");
					contentView.setProgressBar(R.id.appProgressBar, 100, msg.arg1,
							false);

					mNotificationManager.notify(
							NOTIFICATION_NEW_VERSION_APP_DOWNLOAD, mNotification);
				}

				break;
			case MSG_DOWNLOADED:
				if(ver>14){
					mNotificationManager
					.cancel(NOTIFICATION_NEW_VERSION_APP_DOWNLOAD);
					Toast.makeText(NewVersionAppDownloadService.this, "下载完毕",
							Toast.LENGTH_LONG);
				}
				openFile(makeFile(appName));
				isInterrupt = false;
				break;
			default:
				break;
			}
		}


	};

	/**
	 * 
	 *  @Deprecatred:
	 * 	@date:2014-11-4
	 * 	@author:lee
	 * 	@Funtion:set notification
	 */
	private void setNotification() {
		Builder builder = new Notification.Builder(this);
		//		builder.build()
		mNotification = new Notification(R.drawable.ffb_downloading_notificaiton,
				"正在获取最新版本", 0);
		RemoteViews contentView = new RemoteViews(this.getPackageName(),
				R.layout.ffb_newversionappnotification);
		mNotification.contentView = contentView;
		Intent home = new Intent(Intent.ACTION_MAIN);
		home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		home.addCategory(Intent.CATEGORY_HOME);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, home,
				0);
		mNotification.contentIntent = contentIntent;
		mNotificationManager.notify(NOTIFICATION_NEW_VERSION_APP_DOWNLOAD,
				mNotification);
	}

	/**
	 * 
	 * @Deprecatred:todo
	 * @param f  target file
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:open file
	 */
	private void openFile(File f) {
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(android.content.Intent.ACTION_VIEW);

		String type = getMIMEType(f);
		intent.setDataAndType(Uri.fromFile(f), type);
		startActivity(intent);
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param f target file 
	 * @return file type information
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:obtain file type information
	 */
	private String getMIMEType(File f) {
		String type = "";
		String fName = f.getName();
		String end = fName
				.substring(fName.lastIndexOf(".") + 1, fName.length())
				.toLowerCase();

		if (end.equals("m4a") || end.equals("mp3") || end.equals("mid")
				|| end.equals("xmf") || end.equals("ogg") || end.equals("wav")) {
			type = "audio";
		} else if (end.equals("3gp") || end.equals("mp4")) {
			type = "video";
		} else if (end.equals("jpg") || end.equals("gif") || end.equals("png")
				|| end.equals("jpeg") || end.equals("bmp")) {
			type = "image";
		} else if (end.equals("apk")) {
			type = "application/vnd.android.package-archive";
		} else {
			type = "*";
		}
		return type;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @param fileName Create file name
	 * @return create file
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion: sdk stroage "/FFB" dir create file
	 */
	private File makeFile(String fileName) {
		File sdDir = null;
		boolean isSdCardExisted = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		if (isSdCardExisted) {
			sdDir = Environment.getExternalStorageDirectory();
			sdDir = new File(sdDir, "/FFB");
			if (!sdDir.exists())
				sdDir.mkdir();
			sdDir = new File(sdDir, "/" + fileName);

		}else {
			sdDir =new File("/data/data", "/com.ffbao.activity");
			if (!sdDir.exists())
				sdDir.mkdir();
			sdDir = new File(sdDir, "/" + fileName);
		}
		Log.i("chenghao", "创建文件="+sdDir);
		return sdDir;

	}

}
