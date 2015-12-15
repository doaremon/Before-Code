package com.ffbao.ui;

import java.util.ArrayList;
import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.activity.R.layout;
import com.ffbao.activity.R.menu;
import com.ffbao.ui.LogicDetailsActivity.MyPageChangeListener;
import com.ffbao.ui.adapter.ComissioinFragmentAdapter;
import com.ffbao.ui.fragment.CallHistoryFragment;
import com.ffbao.ui.fragment.ContactFragment;
import com.ffbao.ui.fragment.LogicCommissionCompleteFragment;
import com.ffbao.ui.fragment.LogicCommissionUnCompleteFragment;
import com.ffbao.ui.fragment.PagerFragment;
import com.ffbao.util.ExitActivity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Choreographer.FrameCallback;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
/**
 * 
 * @FileName:ContactAddActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ContactAddActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 选择客户
 */
public class ContactAddActivity extends BaseActivity {
	private RadioGroup mRadioGroup;
	private RadioButton contact_lianxi;
	private RadioButton contact_tongxun;

	private ComissioinFragmentAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.ffb_activity_contact_add);
		setActionBarTitle("选择客户");
		ExitActivity.addActivity(this);
		initBootomBar();
		super.onCreate(savedInstanceState);
	}

	private List<PagerFragment> pageList;
	private ViewPager mPager;
	
	private void pageFragment() {

		mPager = (ViewPager) findViewById(R.id.contact_pager);
		pageList = new ArrayList<PagerFragment>();
		pageList.add((PagerFragment) Fragment.instantiate(
				ContactAddActivity.this,
				CallHistoryFragment.class.getName()));
		pageList.add((PagerFragment) Fragment.instantiate(
				ContactAddActivity.this,
				ContactFragment.class.getName()));
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
			// fragment_contact_tab_lianxi fragment_contact_tab_lianxi
		contact_tongxun = (RadioButton) findViewById(R.id.fragment_contact_tab_tonghua);
		contact_lianxi = (RadioButton) findViewById(R.id.fragment_contact_tab_lianxi);
		mRadioGroup = (RadioGroup) findViewById(R.id.fragment_contact_tab_radioGruop);
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.fragment_contact_tab_tonghua:
					mPager.setCurrentItem(0);
					contact_tongxun.setChecked(true);
					break;
				case R.id.fragment_contact_tab_lianxi:
					mPager.setCurrentItem(1);
					contact_lianxi.setChecked(true);
					break;
				}
			}
		});
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
				contact_tongxun.setChecked(true);
				mPager.setCurrentItem(0, true);
				break;
			case 1:
				contact_lianxi.setChecked(true);
				mPager.setCurrentItem(1, true);
				break;
			}
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode==KeyEvent.KEYCODE_BACK){
			finish();
		}
		
		return super.onKeyDown(keyCode, event);
	}
}