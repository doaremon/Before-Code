package com.ffbao.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.util.EncodingUtils;

import com.ffbao.activity.R;
import com.ffbao.activity.R.layout;
import com.ffbao.activity.R.menu;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.update.ProjectUpdate;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.MyProgress;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StreamTool;
import com.ffbao.util.UrlUtil;

import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.inputmethod.InputMethodManager;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
/**
 * 
 * @FileName:WelcomeActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:WelcomeActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 欢迎界面
 */
public class WelcomeActivity extends Activity implements OnPageChangeListener,
OnTouchListener {
	private ViewPager mViewPager;
	private View view1, view2, view3, view4;
	private List<View> list;
	private ImageView mImageView;
	public String flag;

	private int count;
	private int currentItem;
	private int lastX = 0;
	private Context context;
	private AlphaAnimation animation;
	private boolean forwards = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.ffb_activity_welcome);
		ExitActivity.addActivity(this);
		currentItem = 0;
		mImageView = (ImageView) findViewById(R.id.welcome_imageView);
		mViewPager = (ViewPager) findViewById(R.id.welcome_viewPager);


		final LayoutInflater inflater = LayoutInflater
				.from(WelcomeActivity.this);
		list = new ArrayList<View>();
		code = ProjectUpdate.getAppCode(WelcomeActivity.this);

		final SharedPreferences sp = getSharedPreferences("loding", 0);
		flag = sp.getString("loding_flag", "");

		//		if (!code.equals(flag)) {
		//			welcomePager(inflater);
		//		} else {
		//			welcomeAnim(code);
		//		}
		welcomeAnim(code);
		getOtherMessage();
		try {
			String file = SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.errorFile);
			//			原来是这么写的：if (!file.isEmpty()) 没有这个函数，现在写这样的：file.length()!=0
			if (file.length()!=0) {
				//			if (!file.isEmpty()){
				sendlog(new File(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void welcomeAnim(final String code) {
		animation = new AlphaAnimation(0, 1);
		animation.setDuration(3000);// 设置动画持续时间
		animation.setRepeatCount(0);// 设置重复次数
		animation.setFillAfter(false);
		// animation.setStartOffset(500);// 执行前的等待时间
		mImageView.startAnimation(animation);
		mViewPager.setVisibility(View.GONE);
		mImageView.setOnTouchListener(this);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

				SharedPreferences sp = getSharedPreferences("loding", 0);
				SharedPreferences.Editor et = sp.edit();
				et.putString("loding_flag", code);
				et.commit();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {

				new Handler().post(new Runnable() {

					@Override
					public void run() {
						mainActivityIntent(true);
					}
				});
			}
		});
	}

	private void mainActivityIntent(boolean flag) {

		if(!forwards){
			Intent intent = new Intent(this,
					MainActivity.class);
			startActivity(intent);
			finish();
			if(flag){
				overridePendingTransition(0, 0);
			}else{
				overridePendingTransition(R.anim.ffb_alpha_in, R.anim.ffb_alpha_out);
			}
			forwards = true;
		}
	}

	private void welcomePager(final LayoutInflater inflater) {
		mViewPager.setVisibility(View.VISIBLE);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setOnTouchListener(this);
		mImageView.setVisibility(View.GONE);
		initpage(inflater);
		//		Button btn = (Button) view4.findViewById(R.id.welcome_in_btn);
		//		btn.setOnClickListener(new OnClickListener() {
		//
		//			@Override
		//			public void onClick(View v) {
		//					
		//				SharedPreferences sp = getSharedPreferences(
		//						"loding", 0);
		//				SharedPreferences.Editor et = sp.edit();
		//				et.putString("loding_flag", ProjectUpdate
		//						.getAppCode(WelcomeActivity.this));
		//				et.commit();
		//				mainActivityIntent(false);
		//			}
		//		});
		view4.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				SharedPreferences sp = getSharedPreferences(
						"loding", 0);
				SharedPreferences.Editor et = sp.edit();
				et.putString("loding_flag", ProjectUpdate
						.getAppCode(WelcomeActivity.this));
				et.commit();
				mainActivityIntent(false);

				return false;
			}
		});

	}

	private void getOtherMessage() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetOtherMessages);
		HttpClientRequest.getHttpPost(context, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				try {
					ExecuteJSONUtils.getOtherMessages(context, msg);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}

	public void initpage(LayoutInflater flater) {
		view1 = flater.inflate(R.layout.welcome_loading1, null);
		view1.setBackgroundResource(R.drawable.ffb_pic_2);
		view2 = flater.inflate(R.layout.welcome_loading1, null);
		view2.setBackgroundResource(R.drawable.ffb_pic_3);
		view3 = flater.inflate(R.layout.welcome_loading1, null);
		view3.setBackgroundResource(R.drawable.ffb_pic_4);
		view4 = flater.inflate(R.layout.welcome_loading2, null);
		view4.setBackgroundResource(R.drawable.ffb_pic_5);

		list.add(view1);
		list.add(view2);
		list.add(view3);
		list.add(view4);

		mViewPager.setAdapter(pager);
	}

	private void setcurrentPoint(int position) {
		if (position < 0 || position > count - 1 || currentItem == position) {
			return;
		}

		currentItem = position;
	}

	PagerAdapter pager = new PagerAdapter() {

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(list.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(list.get(position));

			return list.get(position);
		}
	};
	private String code;

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setcurrentPoint(arg0);

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		switch (arg1.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastX = (int) arg1.getX();
			if (ProjectUpdate.getAppCode(WelcomeActivity.this).equals(flag)) {

				mainActivityIntent(true);
			}
			break;

		case MotionEvent.ACTION_UP:
			if (!ProjectUpdate.getAppCode(WelcomeActivity.this).equals(flag)) {
				if ((lastX - arg1.getX() > 100)
						&& (mViewPager.getCurrentItem() == mViewPager
						.getAdapter().getCount() - 1)) {//
					new Handler().postDelayed(new Runnable() {
						public void run() {
							SharedPreferences sp = getSharedPreferences(
									"loding", 0);
							SharedPreferences.Editor et = sp.edit();
							et.putString("loding_flag", ProjectUpdate
									.getAppCode(WelcomeActivity.this));
							et.commit();
							mainActivityIntent(true);
						};
					}, 0);
				}
			}
		}
		return false;
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

	private void sendlog(final File file) throws Exception {
		if (file != null && file.exists()) {
			String userid = SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.userid);
			if (userid != null && userid.length()!=0) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(Constant.commandText, UrlUtil.userPushErrors);
				map.put(Constant.userid, SharedPrefConstance.getStringValue(
						context, SharedPrefConstance.userid));
				map.put(Constant.UUID, SharedPrefConstance.getStringValue(
						context, SharedPrefConstance.UUID));
				map.put(Constant.clientidentity, "0");
				map.put(Constant.errorcontent, StreamTool
						.readInputStreamString(new FileInputStream(file)));

				HttpClientRequest.getHttpPost(context, map,
						new CallBackListener() {

					@Override
					public void onSuccess(String msg) {
						if (ExecuteJSONUtils.simpleUpdateInfo(context,
								msg)) {
							file.delete();
						}
					}

					@Override
					public void onFailure(Exception error, String msg) {

					}
				});
			}
		}
	}
}
