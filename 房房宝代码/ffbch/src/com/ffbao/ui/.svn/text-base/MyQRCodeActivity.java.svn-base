package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RequestMapUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.lidroid.xutils.BitmapUtils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 
 * @FileName:MyQRCodeActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:MyQRCodeActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion:我的二维码
 */
public class MyQRCodeActivity extends BaseActivity {

	private ImageView qrcode;
//	private TextView username;
//	private TextView phone;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_change_qrcode);
		setActionBarTitle("我的二维码");
		ExitActivity.addActivity(this);
		qrcode = (ImageView) findViewById(R.id.my_qrcode_img);
//		username = (TextView) findViewById(R.id.my_username);
//		phone = (TextView) findViewById(R.id.myself_phone);
		
		String qrcodePath = SharedPrefConstance.getStringValue(this, SharedPrefConstance.tdbarcode_path);
		BitmapUtils bitmap = new BitmapUtils(this);
		bitmap.clearCache(qrcodePath);
		bitmap.display(qrcode, qrcodePath);
	}

}
