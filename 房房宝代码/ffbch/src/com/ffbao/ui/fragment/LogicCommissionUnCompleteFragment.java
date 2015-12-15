package com.ffbao.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ffbao.activity.R;
import com.ffbao.ui.adapter.ComissionAdapter;

public class LogicCommissionUnCompleteFragment extends PagerFragment {
	public static final String TAG = "LogicRankFragment";

	private String title = "全城报备";

	/**
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_logic_comission, null,
				false);
	}

	/**
	 * @see android.support.v4.app.Fragment#onViewCreated(android.view.View,
	 *      android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ListView listView = (ListView) view.findViewById(R.id.lv_comission);
		ComissionAdapter adapter = new ComissionAdapter(getActivity());
		listView.setAdapter(adapter);

	}

	@Override
	public String getFragmentName() {

		return LogicCommissionUnCompleteFragment.class.getName();
	}

	@Override
	public String getTitle() {

		return title;
	}

}
