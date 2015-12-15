/**
 * Project Name:房房宝
 * File Name:CallHistoryAdapter.java
 * Package Name:com.ffbao.ui.adapter
 * Date:2014-9-28下午4:54:44
 * Copyright (c) 2014, Darcy_Cui All Rights Reserved.
 *
*/

package com.ffbao.ui.adapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.ffbao.activity.R;
import com.ffbao.entity.CallHistoryEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * ClassName:CallHistoryAdapter
 *
 * Function: TODO ADD FUNCTION
 *
 * Date:     2014-9-28 下午4:54:44 
 * @author   apple
 * @version  
 * @since    JDK 1.7
 * @see 	 
 */
public class CallHistoryAdapter extends BaseAdapter{
	private List<CallHistoryEntity> mList;
	private Context mC;
	
	public CallHistoryAdapter(List<CallHistoryEntity> mList, Context mC) {
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
			v=LayoutInflater.from(mC).inflate(R.layout.item_callhistory_adapter, null);
			vh.firstName = (TextView) v.findViewById(R.id.callHistory_firstShow);
			vh.nextName = (TextView) v.findViewById(R.id.callHistory_nextShow);
			vh.callHistoryDate = (TextView) v.findViewById(R.id.callHistory_date);
			v.setTag(vh);
		}else {
			vh=(ViewHolder) v.getTag();
		}
			CallHistoryEntity call= mList.get(arg0);
			String call_name=call.getCall_name();
			String call_nubmer=call.getCall_number();
			Date call_date=call.getCall_date();
			System.out.println(mList);
			if(call_name==null){
				vh.firstName.setText(call_nubmer);
				vh.nextName.setText("");
			}else {
				vh.firstName.setText(call_name);
				vh.nextName.setText(call_nubmer);
			}
			SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//			Date d = new Date(call_date*1000);
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String call_date_info=sfd.format(call_date);
			vh.callHistoryDate.setText(call_date_info);
		return v;
	}
	
	class ViewHolder{
		TextView firstName,nextName,callHistoryDate;
	}
	
}
