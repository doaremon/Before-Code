package com.ffbao.ui.adapter;

import java.util.List;






import com.ffbao.ui.fragment.PagerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 /**
  * 
  * @FileName:MainFragmentAdapter.java
  * @Deprecatred:
  * @CopyRright (c) 2014-ffbmobile1.0.0
  * @File Numbers:MainFragmentAdapter.java
  * @author lee
  * @create Date2014-11-4
  * @Update Author: 
  * @Update Date:
  * @version 1.0.0
  * @Funtion: mainActivity 四个fragment 处理Adapter
  */
public class MainFragmentAdapter extends FragmentPagerAdapter {

	private List<PagerFragment> fragments ;
	public MainFragmentAdapter(FragmentManager fm, List<PagerFragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

}
