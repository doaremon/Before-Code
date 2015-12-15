/**
 * Project Name:房房宝
 * File Name:ContactsAdapter.java
 * Package Name:com.ffbao.ui.adapter
 * Date:2014-9-28下午5:20:34
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.ui.adapter;

import java.util.List;

import com.ffbao.activity.R;
import com.ffbao.entity.ContactEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * ClassName:ContactsAdapter
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-28 下午5:20:34 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class ContactsAdapter extends BaseAdapter {
	private List<ContactEntity> mList;
	private Context mC;
	
	public ContactsAdapter(List<ContactEntity> mList, Context mC) {
		super();
		this.mList = mList;
		this.mC = mC;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		View v = arg1;
		ViewHolder vh=null;
		if(v==null){
			vh=new ViewHolder();
			v=LayoutInflater.from(mC).inflate(R.layout.item_contact_adapter, null);
			vh.firstName = (TextView) v.findViewById(R.id.contacts_firstShow);
			vh.nextNumber = (TextView) v.findViewById(R.id.contacts_nextShow);
			v.setTag(vh);
		}else {
			vh=(ViewHolder) v.getTag();
		}
			ContactEntity contact= mList.get(arg0);
			String name = contact.getContact_name();
			String number = contact.getContact_number();
			vh.firstName.setText(name);
			vh.nextNumber.setText(number);
		return v;
	}
	
	class ViewHolder{
		TextView firstName,nextNumber;
	}
}
