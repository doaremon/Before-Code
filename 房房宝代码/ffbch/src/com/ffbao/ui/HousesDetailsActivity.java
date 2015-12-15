package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.baidu.ItemizedOverlayDemo;
import com.ffbao.entity.HouseDetail;
import com.ffbao.entity.HouseParamter;
import com.ffbao.entity.HouseUnits;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;
import com.lidroid.xutils.BitmapUtils;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * 
 * @FileName:HousesDetailsActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:HousesDetailsActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 楼盘详情
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
public class HousesDetailsActivity extends BaseActivity implements
OnClickListener {

	private TextView tvProjectName;
	private TextView tvPrice;
	private TextView cooper;
	private TextView potential;
	private TextView tvHouseStatus;

	private TextView news;
	private TextView tvAddress;

	private LinearLayout inBaiduMap;
	private String buildingid,fullname;

	private ImageView projectImg;
	private LinearLayout hezuollOpenClose;
	private ToggleButton tbOpenClose;
	private LinearLayout llInTheSaleOfUnits;
	private LinearLayout llSeeMore;
	private ToggleButton tbhuxingOpenClose;

	private TextView rulesJili;
	private TextView rulesYongjin;

	private TextView custAge;
	private TextView chasePlan;
	private TextView purchaseBudget;
	private TextView customersProfessional;
	private TextView workArea;
	private TextView livingArea;

	private TextView tvSiteDeals;

	private TextView tvCooperationRules;

	private TextView baobeiCustomer;

	private LinearLayout llSellingRealEstate;
	private LinearLayout sellingRealEstate;
	private LinearLayout llunits;
	private LinearLayout llExtensionOffSkills;
	private LinearLayout llFfdSupport;
	private RichfitAlertDialog dialog;

	private HouseDetail houseDetails;
	private TextView tvServicesCommissioners;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		//update by shenwenshi
		//date:2014-12-08
		//for change transer value 
		//Bundle to Intent
		//原来接受值是这样的，buildingid
		/*				Bundle extras = getIntent().getExtras();
				if (extras != null) {
					buildingid = extras.getString(Constant.buildingid, "");
				}*/
		//现在是这样
		Intent intent=getIntent();
		buildingid=intent.getStringExtra(Constant.buildingid);
		fullname=intent.getStringExtra("fullname");
		setContentView(R.layout.ffb_activity_houses_details);
		setActionBarTitle(fullname+"详情");
		//		setActionBarTitle("楼盘详情");
		// setActionBarOther("发送客户");
		ExitActivity.addActivity(this);

		initView();
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:初始化控件，调用赋值
	 */
	private void initView() {

		tvProjectName = (TextView) findViewById(R.id.tvProjectName);
		tvServicesCommissioners = (TextView) findViewById(R.id.tvServicesCommissioner);
		String servicePhone = SharedPrefConstance.getStringValue(
				context, SharedPrefConstance.servicephone, "");
		//servicePhone.isEmpty()
		String iflonging = SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.companyid);
		if("".equals(iflonging)){
			tvServicesCommissioners.setText("全国客服电话");
		}else {
			if (APP.nationwidecall.equals(servicePhone)) {
				tvServicesCommissioners.setText("全国客服电话");
			}else {
				tvServicesCommissioners.setText("联系经纪服务专员");
			}
		}


		tvPrice = (TextView) findViewById(R.id.tvPrice);// 18000元/平
		cooper = (TextView) findViewById(R.id.cooper);// 256个
		potential = (TextView) findViewById(R.id.potential);// 256个
		tvHouseStatus = (TextView) findViewById(R.id.tvHouseStatus);// 期房期房
		projectImg = (ImageView) findViewById(R.id.ivProjectImg);

		news = (TextView) findViewById(R.id.news);
		tvAddress = (TextView) findViewById(R.id.tvAddress);

		inBaiduMap = (LinearLayout) findViewById(R.id.in_baidu_map);

		hezuollOpenClose = (LinearLayout) findViewById(R.id.hezuollOpenClose);
		tbOpenClose = (ToggleButton) findViewById(R.id.tbOpenClose);
		tvCooperationRules = (TextView) findViewById(R.id.tvCooperationRules);

		llInTheSaleOfUnits = (LinearLayout) findViewById(R.id.llInTheSaleOfUnits);
		llSeeMore = (LinearLayout) findViewById(R.id.llSeeMore);
		tbhuxingOpenClose = (ToggleButton) findViewById(R.id.tbhuxingOpenClose);

		rulesJili = (TextView) findViewById(R.id.rules_jili);
		rulesYongjin = (TextView) findViewById(R.id.rules_yongjin);

		custAge = (TextView) findViewById(R.id.custAge);
		chasePlan = (TextView) findViewById(R.id.chasePlan);
		purchaseBudget = (TextView) findViewById(R.id.purchaseBudget);
		customersProfessional = (TextView) findViewById(R.id.customersProfessional);
		workArea = (TextView) findViewById(R.id.workArea);
		livingArea = (TextView) findViewById(R.id.livingArea);

		llSellingRealEstate = (LinearLayout) findViewById(R.id.llSellingRealEstate);
		sellingRealEstate = (LinearLayout) findViewById(R.id.sellingRealEstate);
		llunits = (LinearLayout) findViewById(R.id.llunits);
		llExtensionOffSkills = (LinearLayout) findViewById(R.id.llExtensionOffSkills);
		llFfdSupport = (LinearLayout) findViewById(R.id.llFfdSupport);

		tvSiteDeals = (TextView) findViewById(R.id.tvSiteDeals);
		baobeiCustomer = (TextView) findViewById(R.id.baobei_customer);

		inBaiduMap.setOnClickListener(this);
		projectImg.setOnClickListener(this);
		news.setOnClickListener(this);
		findViewById(R.id.detail_tag).setOnClickListener(this);
		findViewById(R.id.add_customer).setOnClickListener(this);

		findViewById(R.id.llServicesCommissioner).setOnClickListener(this);
		if (!"".equals(buildingid)) {

			initData();
		} else {
			ToastUtils.showToast(context, "楼盘信息异常");
		}



	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-14
	 * @author:lee
	 * @Funtion: 基本信息
	 */
	private void displayView() {

		tvProjectName.setText(houseDetails.getFullname());
		tvPrice.setText(houseDetails.getAvgPrice() + "元/平");// 18000元/平
		cooper.setText( houseDetails.getUser_count()+ "人");// 256个
		potential.setText( houseDetails.getCustomer_count()  + "人");// 256个
		tvHouseStatus.setVisibility(View.GONE);// 期房期房
		if (StringUtils.isNull(houseDetails.getBuilding_photo())) {
			BitmapUtils bitmapUtils = new BitmapUtils(this);
			bitmapUtils.display(projectImg, houseDetails.getBuilding_photo());
		}
		// 地图
		tvAddress.setText("[" + houseDetails.getCity() + "]"
				+ houseDetails.getFullname());
		String userid = SharedPrefConstance.getStringValue(context,
				SharedPrefConstance.userid);
		if (StringUtils.isNull(userid)) {
			baobeiCustomer.setText(houseDetails.getReport_customer_count()
					+ "人");
		} else {
			baobeiCustomer.setText("未登录");
		}
	}

	/**
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:访问获取数据
	 */
	private void initData() {

		Map<String, Object> map = new HashMap<String, Object>();
		// TODO 定义接口名称
		map.put(Constant.commandText, UrlUtil.userGetBuildingInfoList);
		map.put(Constant.userid, SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.userid));
		// map.put(Constant.UUID, SharedPrefConstance.getStringValue(this,
		// SharedPrefConstance.UUID));
		map.put(Constant.buildingid, buildingid);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				if (ExecuteJSONUtils.simpleUpdateInfo(context, msg)) {
					try {

						houseDetails = ExecuteJSONUtils.getHouseDetails(
								context, msg);
						Log.i("chenghao", "看看返回值里面有什么="+msg);
						// housetype = ExecuteJSONUtils.getHouseUnits(context,
						// msg);
						tvAddress.setText(houseDetails.getAddress());
						// baobeiCustomer.setText(houseDetails
						// .getReport_customer_count());
						siteDeals();
						saleOfUnits();
						sellingRealEstate();
						displayView();
						// news.setText("好消息");
						// hezuoll();
						// targetCustomer();
						// extensionOffSkills();
						// FFBSupport();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion: 优惠
	 */
	private void siteDeals() {

		tvSiteDeals.setText(houseDetails.getPreferential());// preferential
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:楼盘买点
	 */
	private void sellingRealEstate() {

		if (StringUtils.isNull(houseDetails.getFeature())) {
			View detailLabelContent = LayoutInflater.from(this).inflate(
					R.layout.ffb_property_detail_label_content_item, null);
			TextView content = (TextView) detailLabelContent
					.findViewById(R.id.tvContent);
			TextView label = (TextView) detailLabelContent
					.findViewById(R.id.tvLabel);
			label.setText("");
			content.setText(houseDetails.getFeature());
			llSellingRealEstate.addView(detailLabelContent, 0);
		} else {
			sellingRealEstate.setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:sale of units 再售户型图
	 */
	private void saleOfUnits() {
		final List<HouseUnits> housetype = houseDetails.getHousetypes();
		if (housetype.size() == 0) {
			llunits.setVisibility(View.GONE);
			return;
		}
		for (int i = 0; i < (housetype.size() > 3 ? 3 : housetype.size()); i++) {

			HouseUnits unit = housetype.get(i);
			View cooperative = getCooperativeView(unit ,i);
			llInTheSaleOfUnits.addView(cooperative, 0);
		}
		if (housetype.size() > 3) {
			tbhuxingOpenClose
			.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {

					if (isChecked) {

						for (int i = 3; i < housetype.size(); i++) {

							HouseUnits unit = housetype.get(i);
							View cooperative1 = getCooperativeView(unit ,i);
							llSeeMore.addView(cooperative1, 0);
						}

					} else {
						View view = null;
						do {
							view = llSeeMore.getChildAt(0);
							if (view instanceof LinearLayout) {
								llSeeMore.removeViewAt(0);
							}
						} while (view instanceof LinearLayout);

					}
				}
			});
		} else {
			tbhuxingOpenClose.setVisibility(View.GONE);
		}
	}

	/**
	 * @Deprecatred:
	 * @param units
	 * @return
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:处理户型数据处理
	 */
	private View getCooperativeView(HouseUnits units ,final int position) {

		View cooperative = LayoutInflater.from(this).inflate(
				R.layout.ffb_cooperative_apartment_item, null);
		ImageView image = (ImageView) cooperative.findViewById(R.id.ivImage);
		TextView type = (TextView) cooperative.findViewById(R.id.tvType);// 三室一厅
		TextView Area = (TextView) cooperative.findViewById(R.id.tvArea);// 面积
		TextView name = (TextView) cooperative.findViewById(R.id.tvName);// A2户型A2户型A2户型A2户型A2户型A2户型A2户型A2户型
		TextView analzea = (TextView) cooperative.findViewById(R.id.tvAnalyze);// 两房两房两房两房两房两房两房两房两房两房两房两房
		if (StringUtils.isNull(units.getHousephoto())) {
			BitmapUtils bitmapUtils = new BitmapUtils(this);
			bitmapUtils.display(image, units.getHousephoto());
		}
		type.setText(units.getRoom());
		Area.setText(units.getArea()+"㎡");
		name.setText(units.getDescription());
		analzea.setText("");
		cooperative.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//update by shenwenshi
				//date:2014-12-08
				//for change transer value 
				//Bundle to Intent
				/*				Bundle bundle = new Bundle();
				Intent unitsIntent = new Intent(context, ImageShowActivity.class);
				bundle.putString(Constant.buildingid, buildingid);
				bundle.putInt(Constant.position, position);
				bundle.putParcelableArrayList("array", (ArrayList<? extends Parcelable>) houseDetails.getHousetypes());
				bundle.putString(Constant.type, "0");
				bundle.putInt("tempIndex", 0);
				unitsIntent.putExtras(bundle);
				startActivity(unitsIntent);*/

				//new type
				Intent intent = new Intent(context, ImageShowActivity.class);
				intent.putExtra(Constant.buildingid,buildingid);
				intent.putExtra(Constant.position, position);
				intent.putExtra("array", (ArrayList<? extends Parcelable>) houseDetails.getHousetypes());
				intent.putExtra(Constant.type, "0");
				intent.putExtra("tempIndex", 0);
				startActivity(intent);		
			}
		});
		return cooperative;
	}

	@Override
	public void onClick(View v) {

		if(houseDetails == null){

			//			ToastUtils.showToast(context, "数据还在请求");
			return;
		}
		//update by shenwenshi
		//date:2014-12-08
		//for change transer value 
		//Bundle to Intent
		//Bundle bundle = new Bundle();
		Intent intentLast = new Intent();
		switch (v.getId()) {
		case R.id.in_baidu_map:
			//在跳转百度地图前进行判断。判断bena以及精度和纬度的判断！判断null ,"" ch-
			Intent intent = new Intent(HousesDetailsActivity.this,
					ItemizedOverlayDemo.class);
			if (houseDetails != null && StringUtils.isNull(houseDetails.getLongitude()) && StringUtils.isNull(houseDetails.getLatitude())) {
				if("0".equals(houseDetails.getLongitude()) || "0".equals(houseDetails.getLatitude())){
					//坐标没有进行维护
					ToastUtils.showErrorToast(this, "暂无位置信息");
				}else {
					intent.putExtra(Constant.longitude, houseDetails.getLongitude());
					intent.putExtra(Constant.latitude, houseDetails.getLatitude());
					intent.putExtra(Constant.cityid, houseDetails.getFullname());
					startActivity(intent);
				}

			}
			else {
				//坐标没有进行维护
				ToastUtils.showErrorToast(this, "暂无位置信息");
			}
			break;
		case R.id.detail_tag:
			//update by shenwenshi
			//date:2014-12-08
			//for change transer value 
			//Bundle to Intent
			/*			Intent detailIntent = new Intent(context,
					BuildingDetailsActivity.class);
			Bundle extras = new Bundle();
			extras.putString(Constant.buildingid, buildingid);
			detailIntent.putExtras(extras);
			startActivity(detailIntent);*/
			Intent detailIntent = new Intent(context,
					BuildingDetailsActivity.class);
			detailIntent.putExtra(Constant.buildingid, buildingid);
			startActivity(detailIntent);
			break;
		case R.id.ivProjectImg:
			//update by shenwenshi
			//date:2014-12-08
			//for change transer value 
			//Bundle to Intent

			// TODO 默认图片和楼房默认图片
			/*			Intent detailImageIntent = new Intent(context,
					ImageShowActivity.class);
			bundle.putString(Constant.buildingid, buildingid);
			bundle.putInt("tempIndex", 1);

			detailImageIntent.putExtras(bundle);
			startActivity(detailImageIntent);*/

			Intent detailImageIntent = new Intent(context,
					ImageShowActivity.class);
			detailImageIntent.putExtra(Constant.buildingid, buildingid);
			detailImageIntent.putExtra("tempIndex", 1);
			startActivity(detailImageIntent);

			break;
		case R.id.llServicesCommissioner:

			final String servicePhone = SharedPrefConstance.getStringValue(
					context, SharedPrefConstance.servicephone, "");
			if (servicePhone != null && servicePhone.length()!=0) {
				if (dialog == null)
					dialog = new RichfitAlertDialog(this);
				if (!dialog.isShow()) {
					dialog.show();
					dialog.setContent("是否拨打电话:" + servicePhone);
					dialog.setPositiveButton("取消", new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.close();
						}
					});
					dialog.setNegativeButton(

							"拨打", new OnClickListener() {

								@Override
								public void onClick(View v) {
									Intent intent = new Intent(Intent.ACTION_CALL, Uri
											.parse("tel:" + servicePhone));
									startActivity(intent);
									dialog.close();

								}
							});
				}
			}else{
				final String customerservices = SharedPrefConstance.getStringValuePhone(
						HousesDetailsActivity.this, SharedPrefConstance.customerservices);
				if (dialog == null)
					dialog = new RichfitAlertDialog(this);
				if (!dialog.isShow()) {
					dialog.show();
					dialog.setContent("是否拨打电话:" + customerservices);
					dialog.setPositiveButton("取消", new OnClickListener() {

						@Override
						public void onClick(View v) {
							dialog.close();
						}
					});
					dialog.setNegativeButton(

							"拨打", new OnClickListener() {

								@Override
								public void onClick(View v) {
									Intent intent = new Intent(Intent.ACTION_CALL, Uri
											.parse("tel:"+customerservices));
									startActivity(intent);
									dialog.close();

								}
							});
				}
			}
			break;
		case R.id.add_customer:

			if (!"".equals(SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.userid))) {

				if (!"0".equals(SharedPrefConstance.getStringValue(context,
						SharedPrefConstance.companyid))) {
					//update by shenwenshi
					//date:2014-12-08
					//for change transer value 
					//Bundle to Intent
					/*					Intent addCostomIntent = new Intent();
					addCostomIntent.setClass(context, CustomAddActivity.class);
					bundle.putString("wantCity_id",
							houseDetails.getBuilding_placeid());
					bundle.putString("wantCity_level", houseDetails.getLevel());
					bundle.putString("building_id",
							houseDetails.getBuildingid());
					bundle.putString("wantCity_content",
							houseDetails.getBuilding_place());
					bundle.putString("building_content",
							houseDetails.getFullname());
					addCostomIntent.putExtras(bundle);
					context.startActivity(addCostomIntent);*/

					Intent addCostomIntent = new Intent();
					addCostomIntent.setClass(context, CustomAddActivity.class);
					addCostomIntent.putExtra("wantCity_id",houseDetails.getBuilding_placeid());
					addCostomIntent.putExtra("wantCity_level",houseDetails.getLevel());
					addCostomIntent.putExtra("building_id",houseDetails.getBuildingid());
					addCostomIntent.putExtra("wantCity_content",houseDetails.getBuilding_place());
					addCostomIntent.putExtra("building_content",houseDetails.getFullname());
					
					addCostomIntent.putExtra("Agent",houseDetails.getAgent_brokerage_rate());
					addCostomIntent.putExtra("Personrebate",houseDetails.getPersonrebate());
					
					context.startActivity(addCostomIntent);
				} else {
					if (dialog == null)
						dialog = new RichfitAlertDialog(context);
					if (!dialog.isShow()) {
						dialog.setTitle("提示");
						dialog.show();
						dialog.setContent("需要绑定门店码,才能报备。");
						dialog.setNegativeButton("绑定门店码",
								new OnClickListener() {

							@Override
							public void onClick(View v) {
								dialog.close();
								Intent intent = new Intent(context,
										ChangecompanyActivity.class);
								startActivity(intent);
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
			} else {
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
							startActivity(intent);
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
			break;
		case R.id.news:

			if (!"0".equals(SharedPrefConstance.getStringValue(context,
					SharedPrefConstance.userid))) {
				Intent houseMessage = new Intent();
				houseMessage.setClass(context, HouseMessageActivity.class);
				context.startActivity(houseMessage);
			}
			break;
			//		case R.id.llsaleUnit:
			//
			//			Intent unitsIntent = new Intent(context, ImageShowActivity.class);
			//			bundle.putString(Constant.buildingid, buildingid);
			//			bundle.putString(Constant.type, "0");
			//			unitsIntent.putExtras(bundle);
			//			startActivity(unitsIntent);
			//
			//			break;
		default:
			break;
		}
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:目标客户
	 */
	private void targetCustomer() {

		custAge.setText("30-40");
		chasePlan.setText("享受生活");
		purchaseBudget.setText("200百万");
		customersProfessional.setText("经理");
		workArea.setText("北京地区");
		livingArea.setText("海南");
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:房房宝支持
	 */
	private void FFBSupport() {

		View detailLabelContent = getLayoutInflater().from(this).inflate(
				R.layout.ffb_property_detail_label_content_item, null);
		llFfdSupport.addView(detailLabelContent, 0);
		detailLabelContent = getLayoutInflater().from(this).inflate(
				R.layout.ffb_property_detail_label_content_item, null);
		llFfdSupport.addView(detailLabelContent, 0);
		detailLabelContent = getLayoutInflater().from(this).inflate(
				R.layout.ffb_property_detail_label_content_item, null);
		llFfdSupport.addView(detailLabelContent, 0);
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:拓客技巧
	 */
	private void extensionOffSkills() {

		View detailLabelContent = getLayoutInflater().from(this).inflate(
				R.layout.ffb_property_detail_label_content_item, null);
		llExtensionOffSkills.addView(detailLabelContent, 0);
		detailLabelContent = getLayoutInflater().from(this).inflate(
				R.layout.ffb_property_detail_label_content_item, null);
		llExtensionOffSkills.addView(detailLabelContent, 0);
		detailLabelContent = getLayoutInflater().from(this).inflate(
				R.layout.ffb_property_detail_label_content_item, null);
		llExtensionOffSkills.addView(detailLabelContent, 0);
	}

	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-5
	 * @author:lee
	 * @Funtion:合作规则扩展
	 */
	private void hezuoll() {

		rulesJili.setText("买一套 给你好几万。");
		rulesYongjin.setText("佣金三十万");

		tbOpenClose.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					tvCooperationRules.setMaxLines(6);
					tvCooperationRules.setVisibility(View.VISIBLE);
				} else {
					tvCooperationRules.setMaxLines(0);
					tvCooperationRules.setVisibility(View.GONE);
				}
			}
		});
	}
}
