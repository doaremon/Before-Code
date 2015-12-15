package com.ffbao.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.ffbao.activity.R;


public class MyTextDialog {
	private AlertDialog dialog;
	private View view;

	public MyTextDialog(Context context) {
		dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(false);
		view = LayoutInflater.from(context).inflate(R.layout.ffb_textdialog, null);
	}

	public void show() {
		dialog.setView(view, 0, 0, 0, 0);
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

}
