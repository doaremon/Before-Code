package com.ffbao.util;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.ffbao.ui.BugReportActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;

/**
 * 
 * @FileName:UncaughtExceptionHandler.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:UncaughtExceptionHandler.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion:异常退出是否 界面 提供 UncaughtExceptionHandler
 */
public class UncaughtExceptionHandler implements java.lang.Thread.UncaughtExceptionHandler {
	private final Context context;
//	public static File logFile;
	public UncaughtExceptionHandler(Context context) {
		this.context = context;
	}
	@Override
	public void uncaughtException(Thread thread, Throwable exception) {
		final StringWriter stackTrace = new StringWriter();
		exception.printStackTrace(new PrintWriter(stackTrace));
		System.out.println(stackTrace.toString());
		saveErrorLog(stackTrace.toString());
		
		Intent intent = new Intent(context, BugReportActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra(BugReportActivity.STACKTRACE, stackTrace.toString());
		context.startActivity(intent);
		
		//退出
		ExitActivity.exit();
		
		System.exit(0);
		android.os.Process.killProcess(android.os.Process.myPid());
		
	}
	
	public static File logFile = new File(getFile() + File.separator + Constant.fileName
			 + File.separator + "error");

	private static File getFile() {
		File file = Environment.getExternalStorageDirectory();
		return file;
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @param stackInfo
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:保存需要上传日志 ErrorLog
	 */
	private void saveErrorLog(String stackInfo) {
		logFile.mkdirs();
		StringBuilder reportText = new StringBuilder();
		reportText.append("Model:").append(Build.MODEL).append("\n");
		reportText.append("Device:").append(Build.DEVICE).append("\n");
		reportText.append("Product:").append(Build.PRODUCT).append("\n");
		reportText.append("Manufacturer:").append(Build.MANUFACTURER).append("\n");
		// ϵͳ�汾
		reportText.append("Version:").append(Build.VERSION.RELEASE).append("\n");
		// Ȥ���汾
		String versionName = new ErrorUtil(context).getVersionName();
		reportText.append("QureadVersion:").append(versionName).append("\n");
		// reportText.append(context.getText(R.string.bug_report).toString().replace("version",
		// versionName));
		// ��ϸ��ջ������Ϣ
		reportText.append(stackInfo);
		// �洢bug��־
		IOUtils.saveFile(reportText.toString(), logFile, "bugs.txt");
		/*
		 * ������־ Map<String, String> params = new HashMap<String, String>();
		 * params.put("type", String.valueOf(NetWorkConfig.PLAT_ANDROID));
		 * params.put("error", reportText.toString()); new
		 * NetWorkManager(Apps.getAppContext(), NetWorkConfig.BUG_REPORT, null,
		 * -1, params, NetWorkManager.POST_REQUEST).execute();
		 */
		SharedPrefConstance.setSharePref(context, SharedPrefConstance.errorFile,new File(logFile, "bugs.txt").toString());
		
	}
}
