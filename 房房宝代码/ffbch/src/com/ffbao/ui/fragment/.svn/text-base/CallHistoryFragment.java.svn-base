/**
 * Project Name:房房宝
 * File Name:CallHistoryFragment.java
 * Package Name:contact_lianxi
 * Date:2014-9-28下午3:59:47
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.List;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ffbao.activity.R;
import com.ffbao.entity.CallHistoryEntity;
import com.ffbao.ui.CustomAddActivity;
import com.ffbao.ui.adapter.CallHistoryAdapter;
import com.ffbao.ui.fragment.PagerFragment;
import com.ffbao.util.ContactsUtil;

/**
 * ClassName:CallHistoryFragment
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-28 下午3:59:47 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class CallHistoryFragment extends PagerFragment{
	private ListView mListView;
	private ContentResolver cr;
	private ContactsUtil mCu;
	private List<CallHistoryEntity> mList;
	private CallHistoryAdapter mCa;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_callhistory, null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}
	
	private void initView(View v){
		cr=getActivity().getApplicationContext().getContentResolver();
		mListView = (ListView) v.findViewById(R.id.fragment_callHistory);
		mCu=new ContactsUtil(cr);
		mList=mCu.getCallInfo();
		mCa=new CallHistoryAdapter(mList, getActivity());
		mListView.setAdapter(mCa);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				CallHistoryEntity call=mList.get(arg2);
				Intent intent=new Intent(getActivity(),CustomAddActivity.class);
				intent.putExtra("name", call.getCall_name());
				intent.putExtra("number", call.getCall_number());
				startActivity(intent);
				getActivity().finish();
			}
		});
	}
	
	@Override
	public String getFragmentName() {
		// TODO Auto-generated method stub
		return CallHistoryFragment.class.getName();
	}

}
