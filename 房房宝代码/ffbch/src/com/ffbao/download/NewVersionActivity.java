package com.ffbao.download;

import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.entity.UpdateVersion;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
import com.ffbao.util.MyTextDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.ToastUtils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
/**
 * 
 * @FileName:NewVersionActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:NewVersionActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: update version dispay 
 */
public class NewVersionActivity extends Activity {
	private Intent intent;
	private UpdateVersion updateVersion;

	private NotificationManager mNotificationManager;
	private ProgressDialog progressDialog; 
	private TextView txtTitle;
	private TextView tvNewVersionClipboard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i("chenghao", "进入NewVersionActivity");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ffb_new_version_activity_layout_on_pad);
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		Bundle bundle = getIntent().getExtras();
		updateVersion = (UpdateVersion) bundle.get(Constant.UpdateVersion);
		String ismandatory = updateVersion.getMustupdate();
		tvNewVersionClipboard = (TextView) findViewById(R.id.tvNewVersionClipboard);
		tvNewVersionClipboard.setText(updateVersion.getUpdatecontent());
		txtTitle=(TextView) findViewById(R.id.txtTitle);
		Button btnOk = (Button) findViewById(R.id.ok);
		Button btnCancel = (Button) findViewById(R.id.cancel);

		btnOk.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String version=android.os.Build.VERSION.SDK;
				int ver=Integer.parseInt(version);
				tvNewVersionClipboard.setText("正在更新中...");
				if(ver>14){
					mNotificationManager.cancel(R.drawable.ffb_new_version_update);

				}else {
					progressDialog = new ProgressDialog(NewVersionActivity.this);  
					progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  
					progressDialog.setMessage("正在下载中..."); 
					progressDialog.setIndeterminate(true);//设置ProgressDialog 的进度条不明确
					progressDialog.setCancelable(true);
					progressDialog.show();
				}
				Intent data= new Intent(NewVersionActivity.this, NewVersionAppDownloadService.class);
				data.putExtra("AppUrl", updateVersion.getUrl());
				//data.putExtra("AppUrl", "http://182.92.233.245:8888/ffbclient.apk");//这个是测试的
				startService(data);
				DisplayUtils.setUnRefresh();
			}
		});

		if (ismandatory.equals("1")) {
			btnCancel.setVisibility(View.GONE);
			txtTitle.setText("强制更新");
		}
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (updateVersion != null
				&& SharedPrefConstance.mustUpdate.equals(updateVersion
						.getMustupdate())) {
			if (KeyEvent.KEYCODE_BACK == keyCode) {
				ToastUtils.showToast(this, "当前存在风险，请您更新。");
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
