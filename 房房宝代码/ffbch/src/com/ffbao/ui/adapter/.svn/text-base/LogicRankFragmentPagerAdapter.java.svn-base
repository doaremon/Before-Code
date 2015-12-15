package com.ffbao.ui.adapter;

import java.util.List;




import com.ffbao.ui.fragment.PagerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 
 * @FileName:LogicRankFragmentPagerAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:LogicRankFragmentPagerAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 处理业绩处理 adapter
 */
public class LogicRankFragmentPagerAdapter extends FragmentPagerAdapter
{
	private List<PagerFragment> fragmentList;

	public LogicRankFragmentPagerAdapter(FragmentManager fm, List<PagerFragment> fragments)
	{
		super(fm);
		fragmentList = fragments;
	}

	@Override
	public Fragment getItem(int position)
	{
		return fragmentList.get(position);
	}

	@Override
	public int getCount()
	{
		return fragmentList.size();
	}

	@Override
	public CharSequence getPageTitle(int position)
	{
		return fragmentList.get(position).getTitle();
	}

}
