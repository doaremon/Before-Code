package com.ffbao.ui.fragment;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.download.NewVersionActivity;
import com.ffbao.engine.impl.GetUserDetailEngine;
import com.ffbao.entity.UpdateVersion;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.BaseActivity;
import com.ffbao.ui.LoginActivity;
import com.ffbao.ui.SettingAboutActivity;
import com.ffbao.ui.SettingFeedbackActivity;
import com.ffbao.ui.SettingGuideActivity;
import com.ffbao.ui.SettingInfoActivity;
import com.ffbao.ui.SettingShareActivity;
import com.ffbao.ui.widget.RoundImageView;
import com.ffbao.update.ProjectUpdate;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.MyProgress;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.RoleUtils;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

/**
 * 
 * @FileName:SettingsFragment.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SettingsFragment.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 设置界面Fragment
 */
public class SettingsFragment extends PagerFragment implements OnClickListener {
	public static final String TAG = "SettingsFragment";

	private TextView username;
	private TextView phone;
	private TextView service;
	private View  teamBuying;
	private LinearLayout llservice;
	private TextView version;
	private RoundImageView avatar;
	private ImageView avatar1;
	private View serviceBtn;
	private RelativeLayout updateVersion;


	private View info;

	private View btn;

	private TextView login;



	private RichfitAlertDialog dialog;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tab_settings, container,
				false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		initView(view);
		TextView titleText = (TextView) view
				.findViewById(R.id.tv_middle_actionbar_title);
		if (titleText != null) {
			titleText.setText("设置");
		}
		Button back = (Button) view.findViewById(R.id.ib_back_btn);
		back.setVisibility(View.GONE);
		view.findViewById(R.id.rl_back_actionbar).setEnabled(false);
	}

	/**
	 * @Deprecatred:
	 * @param view
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 获取控件 和 添加监听
	 */
	private void initView(View view) {

		username = (TextView) view.findViewById(R.id.contact_info_person_name);
		phone = (TextView) view.findViewById(R.id.contact_info_phone);
		service = (TextView) view.findViewById(R.id.static_phone);
		version = (TextView) view.findViewById(R.id.contact_info_identitys);

		avatar = (RoundImageView) view.findViewById(R.id.contact_userhead_img);
		avatar1 = (ImageView) view.findViewById(R.id.contact_userhead_img1);
		updateVersion = (RelativeLayout) view
				.findViewById(R.id.rl_setting_version);
		serviceBtn = view.findViewById(R.id.settings_new_msg_attention_btn);
		llservice = (LinearLayout) view.findViewById(R.id.rl_settings_service);

		info = view.findViewById(R.id.rl_settings_info);
		View feedback = view.findViewById(R.id.rl_setting_feedback);
		View about = view.findViewById(R.id.rl_setting_about);
		View guide = view.findViewById(R.id.rl_setting_guide);
		View share = view.findViewById(R.id.rl_setting_share);
		btn = view.findViewById(R.id.settings_exit_btn);
		login = (TextView) view.findViewById(R.id.tvLogin);

		updateVersion.setOnClickListener(this);
		feedback.setOnClickListener(this);
		about.setOnClickListener(this);
		guide.setOnClickListener(this);
		share.setOnClickListener(this);
		btn.setOnClickListener(this);

		// TODO  团购
		teamBuying =  view.findViewById(R.id.rl_setting_hotLine);
		// TODO 团购
		teamBuying.setOnClickListener(this);


		setDisplayValue();
	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 设置版本
	 */
	private void setDisplayValue() {

		service.setText(SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.servicename, ""));
		String appCode = ProjectUpdate.getAppCode(getActivity());
		String versionValue = SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.updateVersion);
		showlog("appCode="+appCode+",versionValue="+versionValue);
		if (appCode != null && versionValue.equals(appCode)) {
			version.setText("当前版本为:"+appCode);
		} else {
			if (versionValue.length()==0) {
				//已经是最新版本
				version.setText("");
			} else {
				version.setText("当前版本" + appCode);
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		String avatarPath = SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.myselfimg, "");
		BitmapUtils utils = new BitmapUtils(getActivity());
		utils.display(avatar1, avatarPath, new BitmapLoadCallBack<View>() {

			@Override
			public void onLoadCompleted(View arg0, String arg1, Bitmap bitmap,
					BitmapDisplayConfig config, BitmapLoadFrom arg4) {

				avatar.setImageBitmap(bitmap);
			}

			@Override
			public void onLoadFailed(View arg0, String arg1, Drawable arg2) {
				//				showToast(getActivity(), "头像获取失败");
			}
		});
		username.setText(SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.myselfname, ""));
		if(APP.independentname.equals(SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.companyname))){
			phone.setText("");
			llservice.setVisibility(View.GONE);
		}else {
			phone.setText(SharedPrefConstance.getStringValue(getActivity(),
					SharedPrefConstance.companyname, ""));
		}
		service.setText(SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.servicename, ""));
		String userid = SharedPrefConstance.getStringValue(getActivity(),
				SharedPrefConstance.userid);
		if (!"".equals(userid)) {
			llservice.setVisibility(View.VISIBLE);
			llservice.setOnClickListener(this);
			info.setOnClickListener(this);
			serviceBtn.setOnClickListener(this);
			info.setBackgroundResource(R.drawable.ffb_user_bg);
			getUserDetail();
		} else {
			username.setVisibility(View.GONE);
			phone.setVisibility(View.GONE);
			llservice.setVisibility(View.GONE);
			login.setText("您还未登录,请立即登录");
			login.setOnClickListener(this);
			btn.setVisibility(View.GONE);
			info.setBackgroundResource(R.drawable.ffb_user_bg_nor);
		}
	}
	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion: 更新数据 用户数据
	 */
	private void getUserDetail() {
		GetUserDetailEngine userDetail = new GetUserDetailEngine(getActivity());
		// 更新数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetDetail);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.userid, ""));
		showlog("登录后更新数据：发送map="+map);
		userDetail.execute(map, new CallBackListener() {
			@Override
			public void onSuccess(String msg) {
				showlog("登录后更新数据：返回msg="+msg);
				showlog("现在的角色为="+RoleUtils.getRoleName());
				if (ExecuteJSONUtils.getUserDetails(getActivity(), msg)) {
					username.setText(SharedPrefConstance.getStringValue(
							getActivity(), SharedPrefConstance.myselfname, ""));
					if(APP.independentname.equals(SharedPrefConstance.getStringValue(getActivity(), SharedPrefConstance.companyname, ""))){
						phone.setText("");
						llservice.setVisibility(View.GONE);
					}else {
						phone.setText(SharedPrefConstance.getStringValue(getActivity(), SharedPrefConstance.companyname, ""));
					}
					service.setText(SharedPrefConstance.getStringValue(
							getActivity(), SharedPrefConstance.servicename, ""));
				}
			}
			@Override
			public void onFailure(Exception error, String msg) {
			}
		});
	}

	@Override
	public String getFragmentName() {
		return SettingsFragment.class.getName();
	}

	@Override
	public void onClick(View view) {
		if(!((BaseActivity)getActivity()).ClickApp()){
			return ;
		}
		Intent intent = new Intent();
		switch (view.getId()) {
		//跳转个人资料
		case R.id.rl_settings_info:
			intent.setClass(getActivity(), SettingInfoActivity.class);
			startActivity(intent);
			break;
			//全国客服电话
		case R.id.rl_setting_hotLine: 
			final String customerservices = SharedPrefConstance.getStringValuePhone(
					getActivity(), SharedPrefConstance.customerservices);
			if (customerservices != null && customerservices.length()!=0) {
				if (dialog == null) {
					dialog = new RichfitAlertDialog(getActivity());
				}
				if (!dialog.isShow()) {
					dialog.show();
					dialog.setContent("是否拨打电话:" + customerservices);
					dialog.setPositiveButton("取消", new OnClickListener() {
						@Override
						public void onClick(View v) {

							dialog.close();
						}
					});
					dialog.setNegativeButton("拨打", new OnClickListener() {
						@Override
						public void onClick(View v) {

							Intent intent = new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + customerservices));
							startActivity(intent);
							dialog.close();
						}
					});
				}
			}
			break;
			//我的佣金
		case R.id.rl_setting_feedback:
			String userid = SharedPrefConstance.getStringValue(getActivity(),
					SharedPrefConstance.userid);
			String uuid = SharedPrefConstance.getStringValue(getActivity(),
					SharedPrefConstance.UUID);
			showlog("userid="+userid+"-- uuid="+uuid);
			if(!"".equals(userid) && !"".equals(uuid)){
				intent.setClass(getActivity(), SettingFeedbackActivity.class);
				startActivity(intent);
			}else {
				intent.setClass(getActivity(), LoginActivity.class);
				Bundle bundle = new Bundle();
				bundle.putInt(Constant.forward, 0);
				intent.putExtras(bundle);
				startActivity(intent);
			}

			break;
			//关于我们
		case R.id.rl_setting_about:
			intent.setClass(getActivity(), SettingAboutActivity.class);
			startActivity(intent);
			break;
			//房房宝指南
		case R.id.rl_setting_guide:
			intent.setClass(getActivity(), SettingGuideActivity.class);
			startActivity(intent);
			break;
			//邀请好友安装
		case R.id.rl_setting_share:
			intent.setClass(getActivity(), SettingShareActivity.class);
			startActivity(intent);
			break;
			//联系经纪服务专员
		case R.id.settings_new_msg_attention_btn:
			final String servicePhone = SharedPrefConstance.getStringValue(
					getActivity(), SharedPrefConstance.servicephone, "");
			if (servicePhone != null && servicePhone.length()!=0) {
				if (dialog == null) {
					dialog = new RichfitAlertDialog(getActivity());
				}
				if (!dialog.isShow()) {
					dialog.show();
					dialog.setContent("是否拨打电话:" + servicePhone);
					dialog.setPositiveButton("取消", new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.close();
						}
					});
					dialog.setNegativeButton("拨打", new OnClickListener() {

						@Override
						public void onClick(View v) {

							Intent intent = new Intent(Intent.ACTION_CALL, Uri
									.parse("tel:" + servicePhone));
							startActivity(intent);
							dialog.close();
						}
					});
				}
			}
			break;
		case R.id.rl_setting_version:
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.commandText, UrlUtil.userUpdateVersion);
			map.put(Constant.clientVersion, ProjectUpdate.getAppCode(getActivity()));
			map.put(Constant.type, "1");
			HttpClientRequest.getHttpPost(getActivity(), map, new CallBackListener() {

				@Override
				public void onSuccess(String msg) {
					// TODO Auto-generated method stub
					try {
						UpdateVersion updateVersion = ExecuteJSONUtils
								.ParseVersion(getActivity(), msg);

						if (updateVersion != null
								&& updateVersion.getUrl().length()!=0) {

							SharedPrefConstance.setSharePref(getActivity(),
									SharedPrefConstance.mustUpdateVersion,
									updateVersion.getMustupdate());
							SharedPrefConstance.setSharePref(getActivity(),
									SharedPrefConstance.updateVersion,
									updateVersion.getServerversion());
							//这里修改过的。。。。。。
							if (!"2".equals(updateVersion.getMustupdate())) {
								Intent intent = new Intent(getActivity(),
										NewVersionActivity.class);
								intent.putExtra(Constant.UpdateVersion,
										updateVersion);
								startActivity(intent);
							}
							else {
								ToastUtils.showToast(getActivity(), "已经最新版本");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				@Override
				public void onFailure(Exception error, String msg) {
				}
			});
			break;
			//退出登录
		case R.id.settings_exit_btn:
			if (dialog == null) {
				dialog = new RichfitAlertDialog(getActivity());
			}
			if (!dialog.isShow()) {
				dialog.show();
				dialog.setContent("确认退出?");
				dialog.setPositiveButton("取消", new OnClickListener() {

					@Override
					public void onClick(View v) {
						dialog.close();
					}
				});
				dialog.setNegativeButton("确认", new OnClickListener() {

					@Override
					public void onClick(View v) {
						exitAPP();
						dialog.close();
					}
				});
			}
			break;
			//跳转到登录页面
		case R.id.tvLogin:
			intent.setClass(getActivity(), LoginActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt(Constant.forward, 0);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:注销用户操作
	 */
	private void exitAPP() {
		SharedPrefConstance.clear(getActivity());
		Intent intent = new Intent();
		intent.setClass(getActivity(), LoginActivity.class);
		DisplayUtils.setUnRefresh();
		startActivity(intent);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userLogout);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.userid, ""));
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				getActivity(), SharedPrefConstance.UUID, ""));
		showlog("发送注销用户-map为："+map);
		HttpClientRequest.getHttpPost(getActivity(), map,
				new CallBackListener() {
			@Override
			public void onSuccess(String msg) {
			}
			@Override
			public void onFailure(Exception error, String msg) {
			}
		});
	}
	@Override
	public void onPause() {
		super.onPause();
		MyProgress.getInstance().dismissDialog();
	}
}
