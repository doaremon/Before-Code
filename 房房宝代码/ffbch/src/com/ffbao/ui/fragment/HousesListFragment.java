package com.ffbao.ui.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ffb.sortlistview.ShowListDistrict;
import com.ffbao.activity.R;
import com.ffbao.entity.HouseSimple;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.HousesDetailsActivity;
import com.ffbao.ui.adapter.HouseAdapter;
import com.ffbao.ui.widget.XListView;
import com.ffbao.ui.widget.XListView.IXListViewListener;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.MyProgress;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;

/**
 * 
 * @FileName:HousesListFragment.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:HousesListFragment.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 楼盘Fragment
 */
public class HousesListFragment extends PagerFragment implements
IXListViewListener {

	public static final String TAG = "HousesListFragment";
	private XListView houses;
	private int page = 0;
	private int pageSize = 20;

	private List<HouseSimple> buildingListState = new ArrayList<HouseSimple>();
	private int position = 0;

	private View result;
	private TextView titleText;
	private String cityid;
	private String cityname;


	/**
	 * @see android.support.v4.app.ListFragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tab_houses, container, false);
	}

	/**
	 * @see android.support.v4.app.ListFragment#onViewCreated(android.view.View,
	 *      android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		//进入apk判断是否是第一次进入，第一次进入则没有cityid这个值，如果第一次进入，则跳转到选择城市中，如果不是第一次，则直接显示城市名字
		cityid=SharedPrefConstance.getStringValue(getActivity(), "cityid", "");
		cityname=SharedPrefConstance.getStringValue(getActivity(), "cityname", "");
		
		showlog("这里走city有值的情况");
		initView(view);
		titleText = (TextView) view
				.findViewById(R.id.tv_middle_actionbar_title);
		result = view.findViewById(R.id.noData);
		if (titleText != null) {
			titleText.setText(cityname);
		}
		Button back = (Button) view.findViewById(R.id.ib_back_btn);
		houses.setPullLoadEnable(false);
		back.setVisibility(View.INVISIBLE);
		back.setOnClickListener(null);
		result.setVisibility(View.GONE);
		view.findViewById(R.id.rl_back_actionbar).setEnabled(false);
		// String userid = SharedPrefConstance.getStringValue(getActivity(),
		// SharedPrefConstance.userid);
		// if (!"".equals(userid)) {
		//
		// back.setBackgroundResource(R.drawable.t_tag_img_news);
		// } else {
		// back.setVisibility(View.INVISIBLE);
		// back.setOnClickListener(null);
		// }
		// TODO 一会解开
		
		titleText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(), ShowListDistrict.class);
				//0 。目前没有什么可一区分的请求码
				startActivityForResult(intent, 0);
			}
		});
		
		if("".equals(cityname)){
			showlog("这里走city没有值的情况");
			Intent intent=new Intent(getActivity(), ShowListDistrict.class);
			//0 。目前没有什么可一区分的请求码
			startActivityForResult(intent, 0);
		}else{
			onRefresh();
		}
		
		}

	

	/**
	 * 请求返回值
	 */

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null){

			//这个是返回码，返回各种不同的数据
			if(resultCode==100){
				String city=data.getStringExtra("city");
				cityid=data.getStringExtra("cityid");
				showlog("cityid="+cityid);
				titleText.setText(city);
				page = 0;
				buildingListState.clear();
				initData(cityid);
			}

		}
	}

	@Override
	public void onResume() {
		super.onResume();
		// BaseActivity activity = ((BaseActivity)getActivity());
		// activity.setActionBarTitle("¥��");
		// ImageButton actionBarBackImage = activity.getActionBarBackImage();
		// actionBarBackImage.setBackgroundResource(R.drawable.t_tag_img_news);

		//		page = 0;
		//		buildingListState.clear();
		//		initData();

		//		onRefresh();
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:访问楼旁列表接口
	 */
	private void initData(String cityid) {
		showlog("执行initData 访问楼旁列表接口");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetBuildingList);
		map.put(Constant.pageid, page + "");
		map.put("cityId", cityid);
		map.put(Constant.pagecount, pageSize + "");
		showlog("发送的map为="+map);
		HttpClientRequest.getHttpPost(getActivity(), map,
				new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				showlog("楼盘返回为="+msg);
				try {


					if (page == 0) {
						buildingListState.clear();
						houses.stopRefresh();
					}

					position = buildingListState.size() - 1;
					List<HouseSimple> buildingListState2 = ExecuteJSONUtils
							.getBuildingListState(getActivity(), msg);
					if (buildingListState != null) {

						buildingListState.addAll(buildingListState2);
						if (buildingListState.size() != 0) {
							houses.setVisibility(View.VISIBLE);
							result.setVisibility(View.GONE);
						} else {
							houses.setVisibility(View.GONE);
							result.setVisibility(View.VISIBLE);
						}
						setaAdapter();
					} else {
						buildingListState = new ArrayList<HouseSimple>();
					}

					if (page == 0) {

						houses.stopRefresh();
						if (buildingListState.size() > 10) {
							houses.setPullLoadEnable(true);
						}
					} else {
						houses.stopLoadMore();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {
				houses.stopRefresh();
				houses.stopLoadMore();
			}
		});
	}

	/**
	 * @Deprecatred:
	 * @param view
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:获取控件findViewById
	 */

	private void initView(View view) {

		houses = (XListView) view.findViewById(R.id.lv_houses);
		houses.setXListViewListener(this);
		setaAdapter();
	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:设置adapter
	 */
	private void setaAdapter() {


		HouseAdapter adpater = new HouseAdapter(buildingListState,
				getActivity());
		houses.setAdapter(adpater);
		houses.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				HouseSimple simpleHouse = (HouseSimple) parent.getItemAtPosition(position);
				//update by shenwenshi
				//date:2014-12-08
				//for change transer value 
				//Bundle to Intent
				//原来是这样写的。

				/*								Intent intent = new Intent();
								intent.setClass(getActivity(), HousesDetailsActivity.class);
								Bundle extras = new Bundle();
								extras.putString(Constant.buildingid,
										String.valueOf(simpleHouse.getBuildingid()));
								intent.putExtras(extras);
								startActivity(intent);*/
				//现在改成：
				Intent intent = new Intent(getActivity(), HousesDetailsActivity.class);
				intent.putExtra("fullname", simpleHouse.getFullname());
				intent.putExtra(Constant.buildingid, String.valueOf(simpleHouse.getBuildingid()));
				startActivity(intent);
			}
		});
		if (buildingListState.size() > 10) {
			houses.setPullLoadEnable(true);

			houses.setSelection(position-2);
		}


	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:隐藏软键盘
	 */
	public void hideKeyboard() {
		View view = getActivity().getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getActivity()
					.getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}

	}

	@Override
	public String getFragmentName() {

		return HousesListFragment.class.getName();
	}

	@Override
	public void onRefresh() {

		page = 0;
		initData(cityid);
		//		houses.stopRefresh();
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		houses.setRefreshTime(dateFormat.format(date));
	}

	@Override
	public void onLoadMore() {
		page += 1;
		initData(cityid);
		//		houses.stopLoadMore();
	}

	@Override
	public void onPause() {
		super.onPause();
		MyProgress.getInstance().dismissDialog();
	}
}
