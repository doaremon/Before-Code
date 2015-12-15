package com.ffbao.ui.widget;


import com.ffbao.activity.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 
 * @FileName:WorkflowProcessShow.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:WorkflowProcessShow.java
 * @author lee
 * @create Date2014-11-13
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 弹出框
 */
public class WorkflowProcessShow {
	/**
	 * �?��弹出的页面的内容
	 */
	/**
	 * 获取json字符�?
	 */
	// private LinearLayout ll_layout;
	private Context mThis;
	private String curTitle = "";// 当前环节
	public PopupWindow window;
	public View root;// �?��突出的控�?
	private OnClickListener mOnClickListener;
	public WorkflowProcessShow(Context context ,OnClickListener mOnClickListener) {
		this.mThis = context;
		this.mOnClickListener = mOnClickListener;
		init();
	}

	/**
	 * @param res
	 *            必须的json字符�?
	 */
	public void init() {

		root = View.inflate(mThis, R.layout.ffb_customer_select_status, null);
		window = new PopupWindow(root, -1, -2, true);
		// 注意细节:�?��要记得给弹出窗体指定�?��背景资源,如果不知道会导致 焦点获取失败, 动画播放失败
		window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// 取消背景�?
		// Set to -1 for the default animation, 0 for no animation
		window.setAnimationStyle(0);// 取消默认的动�?

	}

	/**
	 * 产生弹出 窗口
	 * 
	 * @param view
	 *            点击的图�?
	 */
	@SuppressLint("NewApi")
	public boolean getPopWindow(View view) {

		// int xoff = view.getWidth() / 2 - root.getWidth() / 2;

		DisplayMetrics metric = new DisplayMetrics();
		((Activity) mThis).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // 屏幕宽度（像素）
		// int height = metric.heightPixels; // 屏幕高度（像素）
		// float density = metric.density; // 屏幕密度�?.75 / 1.0 / 1.5�?
		// int densityDpi = metric.densityDpi; // 屏幕密度DPI�?20 / 160 / 240�?
		int xoff = -width + view.getWidth() / 2;

		TextView creatchatTextView = (TextView) root
				.findViewById(R.id.customer_all);
		TextView report = (TextView) root
				.findViewById(R.id.customer_report);
		TextView verify = (TextView) root
				.findViewById(R.id.customer_verify);
		TextView del = (TextView) root
				.findViewById(R.id.customer_del);
		TextView receiver = (TextView) root
				.findViewById(R.id.customer_receivce);
		TextView show = (TextView) root
				.findViewById(R.id.customer_show);
		TextView purchase = (TextView) root
				.findViewById(R.id.customer_purchase);
		TextView strike = (TextView) root
				.findViewById(R.id.customer_strike);
		TextView accounting = (TextView) root
				.findViewById(R.id.customer_accounting);
		TextView accounted = (TextView) root
				.findViewById(R.id.customer_accounted);

		creatchatTextView.setOnClickListener(mOnClickListener);
		report.setOnClickListener(mOnClickListener);
		verify.setOnClickListener(mOnClickListener);
		del.setOnClickListener(mOnClickListener);
		receiver.setOnClickListener(mOnClickListener);
		show.setOnClickListener(mOnClickListener);
		purchase.setOnClickListener(mOnClickListener);
		strike.setOnClickListener(mOnClickListener);
		accounting.setOnClickListener(mOnClickListener);
		accounted.setOnClickListener(mOnClickListener);
		
		if (window.isShowing()) {
			window.dismiss();
			window.update(view, xoff, 0, -1, -1);
			return false;
		} else {
			xoff = -17;
			window.showAsDropDown(view, xoff, 0);
			return true;
		}

	}

//	OnClickListener mOnClickListener = new OnClickListener() {
//
//		@Override
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.customer_all:
//				window.dismiss();
//				break;
//			default:
//				break;
//			}
//		}
//	};
	/**
	 * 是否已经完成
	 */
	private boolean isSuccess = false;
	/**
	 * �?��的状�?
	 */
	private final String STATE = "0";

}
