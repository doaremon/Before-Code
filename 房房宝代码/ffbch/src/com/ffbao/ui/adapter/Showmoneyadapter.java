package com.ffbao.ui.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.ShowMoneyBean;
/**
 * 佣金额adapter
 * @author cc
 *
 */
public class Showmoneyadapter extends BaseAdapter{
	private List<ShowMoneyBean> list;
	private Context context;

	public Showmoneyadapter(List<ShowMoneyBean> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(list.size()!=0){
			convertView=LayoutInflater.from(context).inflate(R.layout.ffb_activity_money_adapter, null);
			TextView baobeidannum=(TextView) convertView.findViewById(R.id.baobeidannum);
			TextView moneynum=(TextView) convertView.findViewById(R.id.moneynum);
			TextView nodata=(TextView) convertView.findViewById(R.id.nodata);
			nodata.setVisibility(View.GONE);
			baobeidannum.setText("报备单号："+list.get(position).getReportid());
			moneynum.setText("￥"+list.get(position).getBrokerageFee());
		}else {
			convertView=LayoutInflater.from(context).inflate(R.layout.ffb_activity_money_adapter, null);
			LinearLayout layout=(LinearLayout) convertView.findViewById(R.id.showdata);
			layout.setVisibility(View.GONE);
		}
		return convertView;

	}

}
