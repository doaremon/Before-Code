package com.ffbao.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.ui.adapter.ComissioinFragmentAdapter;
import com.ffbao.ui.fragment.LogicCommissionCompleteFragment;
import com.ffbao.ui.fragment.LogicCommissionUnCompleteFragment;
import com.ffbao.ui.fragment.PagerFragment;
import com.ffbao.util.ExitActivity;
/**
 * 
 * @FileName:LogicDetailsActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:LogicDetailsActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 消息界面
 */
public class LogicDetailsActivity extends BaseActivity {
	
	
	private TextView comissionComplete;
	private TextView comissionUNComplete;

	private List<TextView> radioBtnList = new ArrayList<TextView>();

	private ComissioinFragmentAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.ffb_activity_logic_title_two);
		initBootomBar();
		ExitActivity.addActivity(this);
		super.onCreate(savedInstanceState);

	}

	private List<PagerFragment> pageList;
	private ViewPager mPager;

	private void pageFragment() {

		mPager = (ViewPager) findViewById(R.id.pager);
		pageList = new ArrayList<PagerFragment>();
		pageList.add((PagerFragment) Fragment.instantiate(
				LogicDetailsActivity.this,
				LogicCommissionCompleteFragment.class.getName()));
		pageList.add((PagerFragment) Fragment.instantiate(
				LogicDetailsActivity.this,
				LogicCommissionUnCompleteFragment.class.getName()));
		mPagerAdapter = new ComissioinFragmentAdapter(
				getSupportFragmentManager(), pageList);
		mPager.setAdapter(mPagerAdapter);
		mPager.setOnPageChangeListener(new MyPageChangeListener());

	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(false);
	}

	private void initBootomBar() {

		pageFragment();

		comissionComplete = (TextView) findViewById(R.id.fragment_commission_tab_not);
		comissionUNComplete = (TextView) findViewById(R.id.fragment_commission_tab_got);

		radioBtnList.add(comissionComplete);
		radioBtnList.add(comissionUNComplete);

		comissionComplete.setOnClickListener(mOnClickListener);
		comissionUNComplete.setOnClickListener(mOnClickListener);

		comissionComplete.performClick();
	}

	OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.fragment_commission_tab_not:
				mPager.setCurrentItem(0, true);
				highLightTab(LogicCommissionCompleteFragment.class.getName());
				break;
			case R.id.fragment_commission_tab_got:
				mPager.setCurrentItem(1, true);
				highLightTab(LogicCommissionUnCompleteFragment.class.getName());
				break;
			}
		}
	};

	private void highLightTab(TextView selectTab) {
		for (TextView tab : radioBtnList) {
			if (tab == selectTab) {

				hightLingtabSelect(tab);
			} else {
				hightLingtabSelectNO(tab);
			}
		}
	}

	private void hightLingtabSelect(TextView v) {
		switch (v.getId()) {
		case R.id.fragment_commission_tab_not:

			v.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.ffb_segmentation_press_l));// (R.drawable.segmentation_press_l);
			v.setTextColor(getResources().getColor(R.color.bg_nav));
			break;
		case R.id.fragment_commission_tab_got:
			v.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.ffb_segmentation_press_r));
			v.setTextColor(getResources().getColor(R.color.bg_nav));
			break;
		}
	}

	private void hightLingtabSelectNO(TextView v) {

		switch (v.getId()) {
		case R.id.fragment_commission_tab_not:

			v.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.ffb_segmentation_nor_l));
			v.setTextColor(getResources().getColor(R.color.white));
			break;
		case R.id.fragment_commission_tab_got:
			v.setBackgroundDrawable(getResources().getDrawable(
					R.drawable.ffb_segmentation_nor_r));
			v.setTextColor(getResources().getColor(R.color.white));
			break;
		}
	}

	private void highLightTab(String fname) {

		setActionBarTitle("我的佣金");

		if (LogicCommissionCompleteFragment.class.getName().equals(fname)) {
			highLightTab(comissionComplete);
		} else if (LogicCommissionUnCompleteFragment.class.getName().equals(
				fname)) {
			highLightTab(comissionUNComplete);
		}
	}

	class MyPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int screen) {
			mPager.setCurrentItem(screen, true);
			switch (screen) {
			case 0:
				mPager.setCurrentItem(0, true);
				highLightTab(LogicCommissionCompleteFragment.class.getName());
//				highLightTab(HousesListFragment.class.getName());
				break;
			case 1:
				mPager.setCurrentItem(1, true);
				highLightTab(LogicCommissionUnCompleteFragment.class.getName());
//				highLightTab(CustomFragment.class.getName());
				break;
			}
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}
}
