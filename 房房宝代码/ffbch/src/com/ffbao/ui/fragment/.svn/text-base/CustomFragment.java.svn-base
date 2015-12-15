package com.ffbao.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.Report;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.BaseActivity;
import com.ffbao.ui.BottomMenuActivity;
import com.ffbao.ui.ChangecompanyActivity;
import com.ffbao.ui.CustomDetailsActivity;
import com.ffbao.ui.CustomMessageActivity;
import com.ffbao.ui.LoginActivity;
import com.ffbao.ui.RegiestFinallyActivity;
import com.ffbao.ui.RegiestMessageActivity;
import com.ffbao.ui.SreachActivity;
import com.ffbao.ui.adapter.CoustomAdapter;
import com.ffbao.ui.widget.WorkflowProcessShow;
import com.ffbao.ui.widget.XListView;
import com.ffbao.ui.widget.XListView.IXListViewListener;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.MyProgress;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;

/**
 * 
 * @FileName:CustomFragment.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:CustomFragment.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 客户Fragment
 */
public class CustomFragment extends PagerFragment implements OnClickListener,
IXListViewListener {
	public static final String TAG = "ContactsFragment";
	private XListView customs;
	private View result;
	private List<Report> reqorts = new ArrayList<Report>();
	private WorkflowProcessShow show;
	private BaseActivity activity;
	private int page = 0;
	private CoustomAdapter adpater;
	private String KeepState = "";
	private int position = 0;
	private String userid;
	private View logined;
	private View notBinded;
	private TextView titleText;

	private TextView unreadText;
	private String companyid;
	private TextView imOther;
	private Button back;

	private boolean popwindowShow;
	private RichfitAlertDialog dialog;

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tab_custom, container, false);
	}

	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 *      android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		customs = (XListView) view.findViewById(R.id.lv_customs);
		logined = view.findViewById(R.id.notLogined);
		notBinded = view.findViewById(R.id.notBinded);
		result = view.findViewById(R.id.noData);
		unreadText = (TextView) view
				.findViewById(R.id.main_tab_chat_unread_text);
		View headerLayout = view.findViewById(R.id.rl_chat_nv);

		show = new WorkflowProcessShow(getActivity(), this);

		view.findViewById(R.id.tvBind).setOnClickListener(this);
		// view.findViewById(R.id.tvRegister).setOnClickListener(this);
		// view.findViewById(R.id.btnLogin).setOnClickListener(this);
		view.findViewById(R.id.rl_back_actionbar).setOnClickListener(this);
		customs.setXListViewListener(this);
		customs.setPullLoadEnable(false);
		userid = SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.userid);

		companyid = SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.companyid);
		titleText = (TextView) view
				.findViewById(R.id.tv_middle_actionbar_title);
		if (titleText != null) {
			titleText.setText("所有报备");
			Drawable nav_up = getResources().getDrawable(
					R.drawable.ffb_jiantou_down);
			nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
					nav_up.getMinimumHeight());
			titleText.setCompoundDrawables(null, null, nav_up, null);
			headerLayout.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {
						@Override
						public void onGlobalLayout() {
							// if(show.window.isShowing() & popwindowShow ){
							// popwindowShow = show.window.isShowing();
							PopWindowShowState();
							// }
						}
					});
		}
		back = (Button) view.findViewById(R.id.ib_back_btn);
		imOther = (TextView) view.findViewById(R.id.tv_operater_others_btn);
		if (!"".equals(userid)) {

			back.setBackgroundResource(R.drawable.ffb_c_header_news_bg);
			// back.setOnClickListener(this);
			titleText.setOnClickListener(this);
			imOther.setOnClickListener(this);
			imOther.setText("添加报备");
		} else {

			imOther.setText("");
			imOther.setOnClickListener(null);
			titleText.setOnClickListener(null);
			back.setVisibility(View.INVISIBLE);
		}

		View convertView = LayoutInflater.from(getActivity()).inflate(
				R.layout.ffb_search_widget_no_cannel1, null);
		convertView.findViewById(R.id.actv_search).setOnClickListener(this);
		customs.addHeaderView(convertView);
		DisplayUtils.setUnRefresh();
	}

	@Override
	public void onResume() {
		userid = SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.userid);
		companyid = SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.companyid);
		if (!"".equals(userid)) {
			// String companyID = SharedPrefConstance.getStringValue(
			// getActivity(), SharedPrefConstance.companyid);
			// if (!"".equals(companyID)) {

			// 控制刷新状态
			if(DisplayUtils.getFragmentState()){
				initView();
			}
			DisplayUtils.setRefreshComplete();


			initMessageCount();
			// } else {
			// setActiontitle();
			// notBinded.setVisibility(View.VISIBLE);
			// }
			if (!"0".equals(companyid)) {
				imOther.setOnClickListener(this);
				imOther.setText("添加报备");
			}
			titleText.setOnClickListener(this);
			back.setVisibility(View.VISIBLE);
			back.setBackgroundResource(R.drawable.ffb_c_header_news_bg);
		} else {
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			intent.putExtra(Constant.forward, 1);
			//原来写的
			//			Bundle bundle = new Bundle();
			//			bundle.putInt(Constant.forward, 1);
			//			intent.putExtras(bundle);
			startActivity(intent);

			imOther.setText("");
			imOther.setOnClickListener(null);
			titleText.setOnClickListener(null);
			back.setVisibility(View.INVISIBLE);
		}
		super.onResume();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (BaseActivity) activity;
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:进入界面，调整数据
	 */
	private void initView() {

		// initData("", KeepState);
		// setAdapter();
		onRefresh();
		//		initMessageCount();
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:设置adpater
	 */
	private void setAdapter() {

		// if (adpater == null) {
		adpater = new CoustomAdapter(getActivity(), reqorts);
		// }
		customs.setAdapter(adpater);

		if (position > 3) {

			customs.setSelection(position - 3);
		} else {
			customs.setSelection(position);
		}
		addListener();
		adpater.notifyDataSetChanged();
	}

	/*
	 * 
	 * @Deprecatred:
	 * 
	 * @date:2014-11-13
	 * 
	 * @author:lee
	 * 
	 * @Funtion:访问获取消息 未读消息数量
	 */
	private void initMessageCount() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetMessagesCount);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.UUID));
		HttpClientRequest.getHttpPost(getActivity(), map,
				new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				int messageCount = ExecuteJSONUtils.getMessageCount(
						getActivity(), msg);
				if (messageCount != 0) {
					// ToastUtils.showToast(getActivity(), "" +
					// ExecuteJSONUtils.getMessageCount(
					// getActivity(), msg));
					unreadText.setText(messageCount + "");
					unreadText.setVisibility(View.VISIBLE);
				} else {
					unreadText.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	/**
	 * @Deprecatred:
	 * @param keywords
	 * @param state
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:访问所有报备单消息
	 */
	private void initData(String keywords, String state) {

		KeepState = state;
		// activity.getActionBarBackImage().setVisibility(View.VISIBLE);
		// activity.getActionBarOther().setText("添加客户");
		// activity.getActionBarBackLayout().setOnClickListener(this);
		// activity.getActionBarOther().setOnClickListener(this);
		// activity.getActionBarTitle().setOnClickListener(this);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetReportList);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.userid));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.UUID));
		map.put(Constant.pageid, page + "");
		map.put(Constant.pagecount, "20");
		map.put(Constant.keywords, keywords);
		map.put(Constant.state, state);
		HttpClientRequest.getHttpPost(getActivity(), map,
				new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				if (page == 0) {
					reqorts.clear();
					customs.stopRefresh();
					customs.setPullLoadEnable(false);
				}

				position = reqorts.size() - 1;
				reqorts.addAll(ExecuteJSONUtils.getReportList(
						getActivity(), msg));
				if (reqorts != null) {


					if (reqorts.size() != 0) {
						customs.setVisibility(View.VISIBLE);
						result.setVisibility(View.GONE);
						setAdapter();
					} else {
						customs.setVisibility(View.GONE);
						result.setVisibility(View.VISIBLE);
					}

					if (page == 0) {

						customs.stopRefresh();
						if (reqorts.size() > 10) {
							customs.setPullLoadEnable(true);
						}
					} else {
						customs.stopLoadMore();
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:ListView 加入listener
	 */
	private void addListener() {
		customs.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// if (position == 0) {
				// ToastUtils.showToast(getActivity(), "索索了");
				// } else {
				Report report = (Report) parent.getItemAtPosition(position);
				//原来的写法
				//				Intent intent = new Intent();
				//				intent.setClass(getActivity(), CustomDetailsActivity.class);
				//				Bundle bundle = new Bundle();
				//				if (report != null) {
				//					bundle.putString(Constant.companyID,
				//							String.valueOf(report.getRecorid()));
				//				}
				//				intent.putExtras(bundle);
				//				startActivity(intent);
				// }
				//新写法
				Intent intent = new Intent(getActivity(), CustomDetailsActivity.class);
				if (report != null) {
					intent.putExtra(Constant.companyID, String.valueOf(report.getRecorid()));
				}
				startActivity(intent);
			}
		});
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:隐藏软键盘method
	 */
	public void hideKeyboard() {
		View view = getActivity().getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getActivity()
					.getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	@Override
	public String getFragmentName() {

		return CustomFragment.class.getName();
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();

		switch (v.getId()) {
		case R.id.tv_middle_actionbar_title:

			show.getPopWindow(v);
			PopWindowShowState();
			break;
		case R.id.tv_operater_others_btn:

			addOpertation();
			break;
		case R.id.ib_back_btn:
			intent = new Intent(getActivity(), CustomMessageActivity.class);
			startActivity(intent);
			break;
		case R.id.rl_back_actionbar:
			intent = new Intent(getActivity(), CustomMessageActivity.class);
			startActivity(intent);
			break;
		case R.id.tvBind:

			intent.setClass(getActivity(), RegiestFinallyActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		case R.id.tvRegister:

			intent.setClass(getActivity(), RegiestMessageActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		case R.id.btnLogin:

			intent.setClass(getActivity(), LoginActivity.class);
			startActivity(intent);
			// getActivity().finish();
			break;
		case R.id.actv_search:

			intent.setClass(getActivity(), SreachActivity.class);
			startActivity(intent);

			break;
		default:

			WorkflowProcessShowClick(v);
			break;
		}

	}

	private void addOpertation() {
		Intent intent;
		String companyID = SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.companyid);
		if (!"0".equals(companyID)) {
			intent = new Intent(getActivity(), BottomMenuActivity.class);
			startActivity(intent);
		} else {
			if (dialog == null)
				dialog = new RichfitAlertDialog(getActivity());
			if (!dialog.isShow()) {
				dialog.setTitle("提示");
				dialog.show();

				// final EditText et_servierUrl = new
				// EditText(getActivity());
				// dialog.setContent(et_servierUrl);
				dialog.setContent("需要绑定门店码,才能报备。");
				dialog.setNegativeButton("绑定门店码", new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.close();
						Intent intent = new Intent(getActivity(),
								ChangecompanyActivity.class);
						startActivity(intent);
					}
				});
				dialog.setPositiveButton("取消", new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.close();
					}
				});
			}
		}
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:查询报备单状态旁边 图片按钮处理
	 */
	private void PopWindowShowState() {
		Drawable nav_up;
		if (!"".equals(userid) && getActivity() != null) {
			if (show.window.isShowing()) {
				nav_up = getActivity().getResources().getDrawable(
						R.drawable.ffb_jiantou_up);
			} else {
				nav_up = getActivity().getResources().getDrawable(
						R.drawable.ffb_jiantou_down);
			}
			nav_up.setBounds(0, 0, nav_up.getMinimumWidth(),
					nav_up.getMinimumHeight());
			titleText.setCompoundDrawables(null, null, nav_up, null);
		} else {
			// nav_up=getResources().getDrawable(R.drawable.jiantou_up);
			// nav_up.setBounds(0, 0, 0, 0);
			titleText.setCompoundDrawables(null, null, null, null);
		}
	}

	/**
	 * @Deprecatred:
	 * @param v
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:popwindow 处理应该状态
	 */
	private void WorkflowProcessShowClick(View v) {

		page = 0;
		reqorts.clear();
		switch (v.getId()) {
		case R.id.customer_all:

			KeepState = "";
			titleText.setText("所有报备");
			break;
		case R.id.customer_report:

			KeepState = "0";
			titleText.setText("报备成功");
			break;
		case R.id.customer_verify:

			KeepState = "2";
			titleText.setText("已经验证");
			break;
		case R.id.customer_del:

			KeepState = "99"; // 行程已定行程已定 50
			titleText.setText("已经作废");
			break;
		case R.id.customer_receivce: // 已接待

			KeepState = "8";
			titleText.setText("已经接待");
			break;
		case R.id.customer_show: // 已看房

			KeepState = "9";
			titleText.setText("已经看房");
			break;
		case R.id.customer_purchase: // 已申购

			KeepState = "10";
			titleText.setText("申购");
			break;
		case R.id.customer_strike: // 已成交

			KeepState = "14";
			titleText.setText("成交");
			break;
		case R.id.customer_accounting:

			KeepState = "15";
			titleText.setText("申请结佣");
			break;
		case R.id.customer_accounted:

			KeepState = "18";
			titleText.setText("结佣");
			break;
		default:
			break;
		}
		show.window.dismiss();
		initData("", KeepState);
		PopWindowShowState();
	}

	private void findKeywords(String keywords) {
		initData(keywords, "");
	}

	@Override
	public void onRefresh() {

		page = 0;
		initData("", KeepState);
		//		reqorts.clear();
		// customs.stopRefresh();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		customs.setRefreshTime(dateFormat.format(date));

	}

	@Override
	public void onLoadMore() {
		page += 1;
		initData("", KeepState);
		// customs.stopLoadMore();
	}

	@Override
	public void onPause() {
		super.onPause();
		MyProgress.getInstance().dismissDialog();
		hideKeyboard();
	}
}
