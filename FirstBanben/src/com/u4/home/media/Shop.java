package com.u4.home.media;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.u4.home.R;
import com.u4.home.common.Base;

/**
 * 社区特供
 * 
 * @author Administrator
 * 
 */
@SuppressLint("SetJavaScriptEnabled")
public class Shop extends Base {
	private WebView wv_shop;
	private TextView tv_finish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop);

		wv_shop = (WebView) findViewById(R.id.wv_shop);
		wv_shop.getSettings().setJavaScriptEnabled(true);
		wv_shop.loadUrl("http://sq03.10668810.com");
		wv_shop.setWebViewClient(new HelloWebViewClient());

		tv_finish = (TextView) findViewById(R.id.tv_finish);
		tv_finish.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv_shop.canGoBack()) {
			wv_shop.goBack();
			return true;
		}
		return false;
	}

	private class HelloWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}
