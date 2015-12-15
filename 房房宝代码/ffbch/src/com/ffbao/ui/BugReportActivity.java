package com.ffbao.ui;

import com.ffbao.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 
 * @FileName:BugReportActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:BugReportActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion:  异常退出界面
 */
public class BugReportActivity extends Activity implements  OnClickListener {
	private static final String TAG = "BugReportActivity";
	private TextView reportTextView;
	private StringBuilder reportText;
	private String versionName;
	public static final String STACKTRACE = "fbreader.stacktrace";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ffb_bug_report_view);
		reportTextView = (TextView) findViewById(R.id.report_text);
		findViewById(R.id.ib_back_btn).setVisibility(View.GONE);
		TextView view =(TextView) findViewById(R.id.tv_middle_actionbar_title);
		view.setText("错误反馈");
		initData();
		setListener();
	}

	public void initData() {
		reportTextView.setText("很抱歉，房房宝发生了异常，异常信息将在您下载启动程序时，自动反馈到房房宝服务器，我们将尽快修正此异常，并更新，谢谢您的使用！\r\n");
	}

	public void setListener() {
		findViewById(R.id.send_report).setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.getId() == R.id.send_report) {
			finish();
		}
	}
}
