package com.ffbao.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.ffbao.activity.R;


public class MyListDialog {
	private AlertDialog dialog;
	private View view;
	public ListView dialog_listview;

	public MyListDialog(Context context) {
		dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(false);
		view = LayoutInflater.from(context).inflate(R.layout.ffb_dialog, null);
		dialog_listview = (ListView) view.findViewById(R.id.dialog_listview);
	}

	public void show() {
		dialog.setView(view, 0, 0, 0, 0);
		dialog.getWindow().setGravity(Gravity.CENTER);
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

}
