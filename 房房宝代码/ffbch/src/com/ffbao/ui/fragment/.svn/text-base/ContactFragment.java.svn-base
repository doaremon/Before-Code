/**
 * Project Name:房房宝
 * File Name:ContactFragment.java
 * Package Name:com.ffbao.ui.fragment
 * Date:2014-9-28下午4:01:25
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.ui.fragment;

import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.entity.ContactEntity;
import com.ffbao.ui.CustomAddActivity;
import com.ffbao.ui.adapter.ContactsAdapter;
import com.ffbao.ui.widget.XListView;
import com.ffbao.ui.widget.XListView.IXListViewListener;
import com.ffbao.util.ContactsUtil;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @FileName:ContactFragment.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ContactFragment.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 获取通讯录联系人
 */
public class ContactFragment extends PagerFragment {
	private ListView mListView;
	private ContentResolver cr;
	private List<ContactEntity> mList;
	private ContactsUtil mCu;
	private ContactsAdapter mCa;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_contact,null);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		initView(view);
	}
	
	private void initView(View v){
		cr = getActivity().getApplicationContext().getContentResolver();
		mListView = (ListView) v.findViewById(R.id.fragment_contact);
		mCu=new ContactsUtil(cr);
		mList=mCu.getPhoneInfo();
		mCa=new ContactsAdapter(mList, getActivity());
		mListView.setAdapter(mCa);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				ContactEntity ce=mList.get(arg2);
				Intent intent=new Intent(getActivity(),CustomAddActivity.class);
				intent.putExtra("name", ce.getContact_name());
				intent.putExtra("number", ce.getContact_number());
				startActivity(intent);
				getActivity().finish();
			}
		});
	}
	
	
	
	@Override
	public String getFragmentName() {
		return ContactFragment.class.getName();
	}

}
