package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffbao.activity.R;
import com.ffbao.entity.Report;
import com.ffbao.entity.ReportMessage;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.CoustomAdapter;
import com.ffbao.ui.adapter.CoustomSreachAdapter;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.MyProgress;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
/**
 * 
 * @FileName:SreachActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SreachActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 搜索界面
 */
public class SreachActivity extends Activity implements OnClickListener,
		TextWatcher {

	private ListView customs;
	private View result;
	private TextView ibSearch;
	private View creal;
	private EditText actvSearch;
	private int page = 0;
	private List<Report> reqorts = new ArrayList<Report>();
	private CoustomSreachAdapter adpater;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.ffb_activity_custom_sreach);
		ExitActivity.addActivity(this);
		customs = (ListView) findViewById(R.id.lv_customs);
		ibSearch = (TextView) findViewById(R.id.ibSearch);
		actvSearch = (EditText) findViewById(R.id.actvSearch);
		creal = findViewById(R.id.sreach_cearl);
		result = findViewById(R.id.noRearchResult);

		ibSearch.setOnClickListener(this);
		creal.setOnClickListener(this);
		actvSearch.addTextChangedListener(this);
		ibSearch.setText("取消");
//		initData(actvSearch.getText().toString(), "");
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.sreach_cearl:

			actvSearch.setText("");
			break;
		case R.id.ibSearch:
			if (actvSearch.getText().toString().length()==0) {

				finish();
			} else {
				String sreach = actvSearch.getText().toString().trim();
				if(sreach.length()!=0){
					initData(sreach, "");
				}
			}
			break;

		default:
			break;
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

		if (s.length() > 0) {
			creal.setVisibility(View.VISIBLE);
			ibSearch.setText("搜索");
		} else {
			creal.setVisibility(View.GONE);
			ibSearch.setText("取消");
		}
	}

	@Override
	public void afterTextChanged(Editable s) {

	}

	private void initData(String keywords, String state) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetReportList);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.UUID));
		map.put(Constant.pageid, page + "");
		map.put(Constant.pagecount, "20");
		map.put(Constant.keywords, keywords);
		map.put(Constant.state, state);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				reqorts = ExecuteJSONUtils.getReportList(SreachActivity.this,
						msg);
				if (reqorts != null) {
					setAdapter();

					if (reqorts.size() != 0) {
						customs.setVisibility(View.VISIBLE);
						result.setVisibility(View.GONE);
					} else {
						customs.setVisibility(View.GONE);
						result.setVisibility(View.VISIBLE);
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	private void setAdapter() {

		adpater = new CoustomSreachAdapter(this, reqorts, this);
		customs.setAdapter(adpater);
		addListener();
		adpater.notifyDataSetChanged();
	}

	private void addListener() {

		customs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				intent.setClass(SreachActivity.this,
						CustomDetailsActivity.class);
				Report message = (Report) parent.getAdapter().getItem(position);
				bundle.putString(Constant.companyID,
						String.valueOf(message.getRecorid()));
				intent.putExtras(bundle);
				startActivity(intent);
				finish();
			}
		});

	}

	@Override
	protected void onPause() {
		super.onPause();
		MyProgress.getInstance().dismissDialog();
		hideKeyboard();
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:隐藏软键盘method
	 */
	public void hideKeyboard() {
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}
}
