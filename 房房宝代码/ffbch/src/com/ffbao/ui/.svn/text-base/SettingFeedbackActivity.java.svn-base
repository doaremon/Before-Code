package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ffbao.activity.R;
import com.ffbao.entity.ShowMoneyBean;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.adapter.Showmoneyadapter;
import com.ffbao.util.Constant;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;
/**
 * 
 * @FileName:SettingFeedbackActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SettingFeedbackActivity.java
 * @author ch
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 我的佣金
 */
public class SettingFeedbackActivity extends BaseActivity implements OnClickListener{
	private TextView nomoney;
	private TextView yesmoney;

	private TextView shownomoney;
	private TextView showyesmoney;

	private TextView nodata;


	private ImageView imageView;
	private int width;
	private ListView moneylistview;
	private List<ShowMoneyBean> alreadyemoneylist;
	private List<ShowMoneyBean> nomoneylist;

	private LinearLayout lay_nomoney;
	private LinearLayout lay_yesmoney;

	private int nomoneylen;
	private int yesmoneylen;
	
	private Boolean no;
	private Boolean yes;
	

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_setting_feedback);
		ExitActivity.addActivity(this);
		setActionBarTitle("我的佣金");
		setActionBarOther("");
		getMoney();
		findid();
		alreadyemoneylist=new ArrayList<ShowMoneyBean>();
		nomoneylist=new ArrayList<ShowMoneyBean>();
		WindowManager w=(WindowManager) getSystemService(Context.WINDOW_SERVICE);
		width=w.getDefaultDisplay().getWidth();
	}
	private void findid(){
		nomoney=(TextView)findViewById(R.id.nomoney);
		yesmoney=(TextView)findViewById(R.id.yesmoney);

		shownomoney=(TextView)findViewById(R.id.shownomoney);
		showyesmoney=(TextView)findViewById(R.id.showyesmoney);

		nodata=(TextView)findViewById(R.id.nodata);

		imageView=(ImageView)findViewById(R.id.moneyimg);
		moneylistview=(ListView)findViewById(R.id.moneylistview);


		lay_nomoney=(LinearLayout)findViewById(R.id.lay_nomoney);
		lay_yesmoney=(LinearLayout)findViewById(R.id.lay_yesmoney);

		lay_yesmoney.setOnClickListener(this);
		lay_nomoney.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		moneylistview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView message=(TextView) view.findViewById(R.id.baobeidannum);
				String mess= message.getText().toString();
				Intent intent = new Intent();
				intent.setClass(SettingFeedbackActivity.this,
						CustomDetailsActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString(Constant.companyID, mess.substring(5, mess.length()));
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}
	/**
	 * 获取钱
	 */
	public void getMoney(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.UserGetMyBrokerage);
		String userid = SharedPrefConstance.getStringValue(SettingFeedbackActivity.this,
				SharedPrefConstance.userid);
		String uuid = SharedPrefConstance.getStringValue(SettingFeedbackActivity.this,
				SharedPrefConstance.UUID);
		map.put("UUID", uuid);
		map.put("userid", userid);
		HttpClientRequest.getHttpPost(SettingFeedbackActivity.this, map, new CallBackListener() {

			@Override
			public void onFailure(Exception error, String msg) {

			}

			@Override
			public void onSuccess(String msg) {
				showlog(msg);
				try {
					JSONObject jsonObject=new JSONObject(msg);
					String result=jsonObject.getString("result");
					String status=jsonObject.getString("status");

					if("1".equals(status)){
						JSONObject jsonObject1=new JSONObject(result);
						String alreadmon=jsonObject1.getString("alreadyemoney");
						String nomon=jsonObject1.getString("noemoney");

						shownomoney.setText(nomon);
						showyesmoney.setText(alreadmon);

						JSONArray array=jsonObject1.getJSONArray("alreadyemoneyDetail");
						yesmoneylen=array.length();
						if(yesmoneylen==0){
							nodata.setVisibility(View.VISIBLE);
							moneylistview.setVisibility(View.GONE);
						}

						for(int c=0;c<array.length();c++){
							JSONObject object=new JSONObject(array.get(c)+"");
							ShowMoneyBean bean=new ShowMoneyBean();
							bean.setBrokerageRate(object.getString("brokerageRate"));
							bean.setBrokerageFee(object.getString("brokerageFee"));
							bean.setReportid(object.getString("reportid"));
							alreadyemoneylist.add(bean);
						}

						JSONArray array1=jsonObject1.getJSONArray("noemoneyDetail");
						nomoneylen=array1.length();
						for(int b=0;b<array1.length();b++){
							JSONObject object=new JSONObject(array1.get(b)+"");
							ShowMoneyBean bean=new ShowMoneyBean();
							bean.setBrokerageRate(object.getString("brokerageRate"));
							bean.setBrokerageFee(object.getString("brokerageFee"));
							bean.setReportid(object.getString("reportid"));
							nomoneylist.add(bean);
						}	
						Showmoneyadapter showmoneyadapter=new Showmoneyadapter(nomoneylist, SettingFeedbackActivity.this);
						moneylistview.setAdapter(showmoneyadapter);
						no=true;
						yes=false;
					}else{
						String message=jsonObject.getString("message");
						showToast(context, message);

					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});


	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.lay_nomoney:
			if(!no){
				//初始化  
				Animation translateAnimation1 = new TranslateAnimation(width/2, 0,0,0);  
				//设置动画时间                
				translateAnimation1.setDuration(200);  
				translateAnimation1.setFillAfter(true);
				imageView.startAnimation(translateAnimation1); 
				yesmoney.setTextColor(getResources().getColor(R.color.gray));
				nomoney.setTextColor(getResources().getColor(R.color.black));

				shownomoney.setTextColor(getResources().getColor(R.color.black));
				showyesmoney.setTextColor(getResources().getColor(R.color.gray));
				showlog("nomoneylen="+nomoneylen);
				if(nomoneylen==0){
					nodata.setVisibility(View.VISIBLE);
					moneylistview.setVisibility(View.GONE);
				}else {
					nodata.setVisibility(View.GONE);
					moneylistview.setVisibility(View.VISIBLE);
				}

				Showmoneyadapter showmoneyadapter=new Showmoneyadapter(nomoneylist, SettingFeedbackActivity.this);
				moneylistview.setAdapter(showmoneyadapter);
				yes=false;
				no=true;
			}
			
			break;
		case R.id.lay_yesmoney:
			if(!yes){
				//初始化  
				Animation translateAnimation = new TranslateAnimation(0, width/2,0,0);  
				//设置动画时间                
				translateAnimation.setDuration(200);  
				translateAnimation.setFillAfter(true);
				imageView.startAnimation(translateAnimation); 
				nomoney.setTextColor(getResources().getColor(R.color.gray));
				yesmoney.setTextColor(getResources().getColor(R.color.black));
				shownomoney.setTextColor(getResources().getColor(R.color.gray));
				showyesmoney.setTextColor(getResources().getColor(R.color.black));
				showlog("yesmoneylen="+yesmoneylen);
				if(yesmoneylen==0){
					nodata.setVisibility(View.VISIBLE);
					moneylistview.setVisibility(View.GONE);
				}else {
					nodata.setVisibility(View.GONE);
					moneylistview.setVisibility(View.VISIBLE);
				}

				Showmoneyadapter showmoneyadapter1=new Showmoneyadapter(alreadyemoneylist, SettingFeedbackActivity.this);
				moneylistview.setAdapter(showmoneyadapter1);
				yes=true;
				no=false;
			}
			
			break;
		default:
			break;
		}
	}

}

