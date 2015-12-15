package com.ffbao.ui.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.HouseSimple;
import com.ffbao.ui.CustomAddActivity;
import com.ffbao.ui.LoginActivity;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.RoleUtils;
import com.ffbao.util.SharedPrefConstance;
import com.lidroid.xutils.BitmapUtils;

/**
 * 
 * @FileName:HouseAdapter.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:HouseAdapter.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 获取楼盘列表
 */
public class HouseAdapter extends BaseAdapter {

	private List<HouseSimple> house;
	private Context context;
	private RichfitAlertDialog dialog;

	public HouseAdapter(List<HouseSimple> house, Context context) {
		this.house = house;
		this.context = context;
	}

	@Override
	public int getCount() {
		return house.size();
	}

	@Override
	public Object getItem(int position) {
		return house.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.new_item_house, null);
			ViewHolder holder = new ViewHolder();
			holder.floorItemImg = (ImageView) convertView.findViewById(R.id.floor_item_img);
			holder.floor_item_name=(TextView)convertView.findViewById(R.id.floor_item_name);
			holder.cooper=(TextView)convertView.findViewById(R.id.cooper);
			holder.floor_item_price=(TextView)convertView.findViewById(R.id.floor_item_price);
			holder.commission=(TextView)convertView.findViewById(R.id.commission);
			holder.addfrom=(TextView)convertView.findViewById(R.id.addfrom);

			convertView.setTag(holder);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		Log.i("chenghao", "返回name为="+RoleUtils.getRoleName());
//		for(int c=0;c<house.size();c++){
//			Log.i("chenghao", "Agent_brokerage_rate="+house.get(c).getAgent_brokerage_rate());
//			Log.i("chenghao", "AvgPrice="+house.get(c).getAvgPrice());
//			Log.i("chenghao", "Building_photo="+house.get(c).getBuilding_photo());
//			Log.i("chenghao", "Building_place="+house.get(c).getBuilding_place());
//			Log.i("chenghao", "Building_placeid="+house.get(c).getBuilding_placeid());
//			Log.i("chenghao", "Buildingid="+house.get(c).getBuildingid());
//			Log.i("chenghao", "City()="+house.get(c).getCity());
//			Log.i("chenghao", "Customer_count="+house.get(c).getCustomer_count());
//			Log.i("chenghao", "District="+house.get(c).getDistrict());
//			Log.i("chenghao", "Fullname="+house.get(c).getFullname());
//			Log.i("chenghao", "Level="+house.get(c).getLevel());
//			Log.i("chenghao", "Personrebate这个是独立经纪人的="+house.get(c).getPersonrebate());
//			Log.i("chenghao","User_count="+ house.get(c).getUser_count());
//		}
		final HouseSimple simpleHouse = house.get(position);

		if (!"".equals(simpleHouse.getBuilding_photo())) {
			BitmapUtils bitmapUtils = new BitmapUtils(context);
			bitmapUtils.display(holder.floorItemImg,
					simpleHouse.getBuilding_photo());
		} else {
			holder.floorItemImg.setImageBitmap(null);
			holder.floorItemImg
			.setBackgroundResource(R.drawable.ffb_defualt_huancunbig);
		}

		holder.floor_item_name.setText(simpleHouse.getFullname());
		holder.commission.setText(simpleHouse.getTitile());
		holder.floor_item_price.setText(simpleHouse.getAvgPrice() + "元/平"+"   ["+simpleHouse.getCity()+"]");
		holder.cooper.setText("合作经纪人："+simpleHouse.getUser_count()+"  |  "+"意向客户："+simpleHouse.getCustomer_count());


		holder.addfrom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				if (!"".equals(SharedPrefConstance.getStringValue(context,
						SharedPrefConstance.userid))) {
					Intent addCostomIntent = new Intent();
					addCostomIntent.setClass(context,
							CustomAddActivity.class);
					bundle.putString("wantCity_id",
							simpleHouse.getBuilding_placeid());
					bundle.putString("wantCity_level",
							simpleHouse.getLevel());
					bundle.putString("building_id",
							simpleHouse.getBuildingid());
					bundle.putString("wantCity_content",
							simpleHouse.getBuilding_place());
					bundle.putString("building_content",
							simpleHouse.getFullname());
					bundle.putString("Agent",
							simpleHouse.getAgent_brokerage_rate());
					bundle.putString("Personrebate",
							simpleHouse.getPersonrebate());

					addCostomIntent.putExtras(bundle);

					context.startActivity(addCostomIntent);

				}else {
					if (dialog == null)
						dialog = new RichfitAlertDialog(context);
					if (!dialog.isShow()) {
						dialog.setTitle("提示");
						dialog.show();
						dialog.setContent("请您登录");
						dialog.setNegativeButton("登录", new OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.close();
								Intent intent = new Intent(context,
										LoginActivity.class);
								context.startActivity(intent);
							}
						});
						dialog.setPositiveButton("取消", new OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.close();
							}
						});
					}
				}

			}
		});


		return convertView;
	}




	public static class ViewHolder {

		ImageView floorItemImg;
		TextView floor_item_name;
		TextView cooper;
		TextView floor_item_price;
		TextView commission;
		TextView addfrom;

	}

}
