package com.ffbao.ui.adapter;

import java.util.List;






import com.ffbao.ui.fragment.PagerFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ComissioinFragmentAdapter extends FragmentPagerAdapter {

	private List<PagerFragment> fragments ;
	public ComissioinFragmentAdapter(FragmentManager fm, List<PagerFragment> fragments) {
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
