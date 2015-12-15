package com.ffbao.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.BaseActivity;
import com.ffbao.ui.RegiestActivity;
import com.ffbao.ui.RegiestFinallyActivity;
import com.ffbao.ui.RegiestMessageActivity;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.MyProgress;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;
/**
 * 
 * @FileName:LogicFragment.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:LogicFragment.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 业绩界面fragment
 */
public class LogicFragment extends PagerFragment {
	public static final String TAG = "LogicFragment";

	private String path = "http://192.168.1.37:8080/Front/NewFile.jsp";

	private WebView webView;
	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.ffb_activity_setting_web, null, false);
	}

	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 *      android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		TextView title = (TextView) view.findViewById(R.id.tv_middle_actionbar_title);
		View back = view.findViewById(R.id.ib_back_btn);
		back.setVisibility(View.GONE);
		view.findViewById(R.id.rl_back_actionbar).setEnabled(false);
		title.setText("业务指南");
		path = SharedPrefConstance.getStringValuePhone(getActivity(),
				SharedPrefConstance.workguide);
		webView = (WebView)view. findViewById(R.id.activity_web_webview);
		webView.setWebViewClient(new webViewClient());
		showlog(path);
		webView.loadUrl(path);
	
	}
	
	@Override
	public void onResume() {
		if(path.length()==0){
			getOtherMessage();
		}
		super.onResume();
	}
	private void getOtherMessage() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetOtherMessages);
		HttpClientRequest.getHttpPost(getActivity(), map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				try {
					ExecuteJSONUtils.getOtherMessages(getActivity(), msg);
					path = SharedPrefConstance.getStringValuePhone(getActivity(),
							SharedPrefConstance.workguide);
					showlog("path="+path);
					webView.setWebViewClient(new webViewClient());
					webView.loadUrl(path);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	/**
	 * 
	 * @FileName:LogicFragment.java
	 * @Deprecatred:
	 * @CopyRright (c) 2014-ffbmobile1.0.0
	 * @File Numbers:LogicFragment.java
	 * @author lee
	 * @create Date2014-11-21
	 * @Update Author: 
	 * @Update Date:
	 * @version 1.0.0
	 * @Funtion: webViewClient 客户端处理
	 */
	class webViewClient extends WebViewClient {

		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			view.loadUrl(url);
			return true;
		}

	}

	@Override
	public String getFragmentName() {

		return LogicFragment.class.getName();
	}

	@Override
	public void onPause() {
		super.onPause();
		MyProgress.getInstance().dismissDialog();
	}
}
