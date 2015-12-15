package com.ffbao.ui;

import com.ffbao.activity.R;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 
 * @FileName:SettingGuideActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SettingGuideActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 房房宝指南
 */
public class SettingGuideActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_setting_web);
		Intent intent = getIntent();
		String path = null;
		if (intent != null) {
			String type = intent.getStringExtra("type");
			if(type != null){
				if ("1".equals(type)) {

					setActionBarTitle("隐私条款");
					path = SharedPrefConstance.getStringValuePhone(this,
							SharedPrefConstance.agreement);
				} else {
					setActionBarTitle("房房宝指南");
					path = SharedPrefConstance.getStringValue(this,
							SharedPrefConstance.shareGuideUrl);
				}
			}else{
				setActionBarTitle("隐私条款");
				path = SharedPrefConstance.getStringValuePhone(this,
						SharedPrefConstance.agreement);
			}
		}

		ExitActivity.addActivity(this);
		WebView webView = (WebView) findViewById(R.id.activity_web_webview);
		webView.setWebViewClient(new webViewClient());
		webView.loadUrl(path);
	}

	class webViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			view.loadUrl(url);
			return true;
		}

	}
}
