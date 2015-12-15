/**
 * Project Name:房房宝
 * File Name:ProjectUpdate.java
 * Package Name:com.ffbao.dao
 * Date:2014-9-15下午5:09:48
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
 */

package com.ffbao.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.ffbao.util.Constant;
import com.ffbao.util.UrlUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * ClassName:ProjectUpdate
 * 
 * Function: TODO ADD FUNCTION
 * 
 * Date: 2014-9-15 下午5:09:48
 * 
 * @author apple
 * @version
 * @since JDK 1.7
 * @see 版本更新工具类
 */
public class ProjectUpdate {
	
	

	public static String getAppCode(Context context) {
		String i = "";
		try {
			i = context.getPackageManager().getPackageInfo(
					"com.ffbao.activity", 0).versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public static void showNoticeDialog(final Context context, final String url) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setTitle("软件版本更新");
		builder.setMessage("版本可以更新哦");
		builder.setPositiveButton("下载", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent();
				intent.setAction("android.intent.action.VIEW");
				Uri content_url = Uri.parse(url);
				intent.setData(content_url);
				context.startActivity(intent);
				dialog.dismiss();
			}
		});
		builder.setNegativeButton("以后再说", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		Dialog noticeDialog = builder.create();
		noticeDialog.show();
		// 设置弹出对话框大小属性
		WindowManager.LayoutParams lp = noticeDialog.getWindow()
				.getAttributes();
		lp.width = 400;
		lp.height = 500;
		noticeDialog.getWindow().setAttributes(lp);
		noticeDialog.setCanceledOnTouchOutside(false);
	}
	
}
