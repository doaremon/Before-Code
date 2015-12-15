package com.ffbao.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.engine.impl.GetUserDetailEngine;
import com.ffbao.entity.BankBean;
import com.ffbao.net.CallBackListener;
import com.ffbao.ui.adapter.BanklistDialogAdapter;
import com.ffbao.util.BankUtil;
import com.ffbao.util.Constant;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.MyListDialog;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;

/**
 * 添加和修改银行卡号
 * @author cc
 *
 */
public class AddUpdbankActivity extends BaseActivity {
	public LinearLayout addbank;
	public LinearLayout showbank;

	private RichfitAlertDialog dialog;
	private List<BankBean> banklist;
	private BankListadapter adapter;
	private ListView showbanklist;
	private EditText putbanknumber;
	private EditText putopenaccountname;
	private TextView putbankname;
	private Button submit;
	private  MyListDialog listdialog;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_addupdbank);
		Intent intent=getIntent();
		String type=intent.getStringExtra("type");
		findId();
		if("1".equals(type)){
			setActionBarTitle("银行卡信息");
			addbank.setVisibility(View.GONE);
			getuserGetBankCardInfo();
		}else {
			setActionBarTitle("绑定银行卡");
			showbank.setVisibility(View.GONE);
		}
		banklist=new ArrayList<BankBean>();
		ExitActivity.addActivity(this);
	}
	/**
	 * 查询银行卡接口
	 */
	public void getuserGetBankCardInfo(){
		GetUserDetailEngine userDetail = new GetUserDetailEngine(AddUpdbankActivity.this);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userGetBankCardInfo);
		map.put(Constant.UUID, SharedPrefConstance.getStringValue(
				AddUpdbankActivity.this, SharedPrefConstance.UUID, ""));
		map.put(Constant.userid, SharedPrefConstance.getStringValue(
				AddUpdbankActivity.this, SharedPrefConstance.userid, ""));
		showlog("查询银行卡：发送map="+map);
		userDetail.execute(map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				showlog("查询银行卡返回值msg="+msg);
				try {
					JSONObject jsonObject=new JSONObject(msg);
					int status=jsonObject.getInt("status");
					if(status!=1){
						showToast(AddUpdbankActivity.this,jsonObject.getString("message"));
					}else{
						String result=jsonObject.getString("result");
						JSONObject jsonObject2=new JSONObject(result);
						JSONArray array=jsonObject2.getJSONArray("backCardInfo");
						for(int c=0;c<array.length();c++){
							JSONObject jsonObject3=new JSONObject(array.get(c).toString());
							BankBean bankBean=new BankBean();
							bankBean.setBankname(jsonObject3.getString("bankname"));
							bankBean.setBanknumber(jsonObject3.getString("banknumber"));
							bankBean.setOpenaccountname(jsonObject3.getString("openaccountname"));
							banklist.add(bankBean);
						}
						adapter=new BankListadapter();
						showbanklist.setAdapter(adapter);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			@Override
			public void onFailure(Exception error, String msg) {

			}
		});
	}
	public void findId(){

		submit=(Button)findViewById(R.id.submit);
		putbankname=(TextView)findViewById(R.id.putbankname);
		putbanknumber=(EditText)findViewById(R.id.putbanknumber);
		putopenaccountname=(EditText)findViewById(R.id.putopenaccountname);
		addbank=(LinearLayout)findViewById(R.id.addbank);
		showbank=(LinearLayout)findViewById(R.id.showbank);
		showbanklist=(ListView)findViewById(R.id.showbanklist);
	}

	@Override
	protected void onResume() {
		super.onResume();
		showbanklist.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				if (dialog == null) {
					dialog = new RichfitAlertDialog(AddUpdbankActivity.this);
				}
				if (!dialog.isShow()) {
					dialog.show();
					dialog.setContent("是否删除银行卡？");
					dialog.setPositiveButton("取消", new OnClickListener() {
						@Override
						public void onClick(View v) {
							dialog.close();
						}
					});
					dialog.setNegativeButton("确定", new OnClickListener() {

						@Override
						public void onClick(View v) {
							BankBean bankBean=banklist.get(position);
							GetUserDetailEngine userDetail = new GetUserDetailEngine(AddUpdbankActivity.this);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put(Constant.commandText, UrlUtil.userAddOrDelBankCard);
							map.put(Constant.UUID, SharedPrefConstance.getStringValue(
									AddUpdbankActivity.this, SharedPrefConstance.UUID, ""));
							map.put(Constant.userid, SharedPrefConstance.getStringValue(
									AddUpdbankActivity.this, SharedPrefConstance.userid, ""));
							map.put("methodType", "2");
							map.put("bankname", bankBean.getBankname());
							map.put("banknumber", bankBean.getBanknumber());
							map.put("openaccountname", bankBean.getOpenaccountname());
							showlog("删除卡号：发送map="+map);

							userDetail.execute(map, new CallBackListener() {

								@Override
								public void onSuccess(String msg) {
									showlog("删除卡号：返回值msg="+msg);
									banklist.remove(position);
									adapter.notifyDataSetChanged();
									showToast(AddUpdbankActivity.this, "删除银行卡成功");
									if(banklist.size()==0){
										SharedPrefConstance.setSharePref(context, SharedPrefConstance.isBank,"0");
										finish();
									}
									dialog.close();
								}

								@Override
								public void onFailure(Exception error, String msg) {

								}
							});

						}
					});
				}
				return true;
			}
		});

		putbankname.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listdialog=new MyListDialog(AddUpdbankActivity.this);
				BanklistDialogAdapter adapter=new BanklistDialogAdapter(AddUpdbankActivity.this, APP.banklists);
				listdialog.dialog_listview.setAdapter(adapter);
				listdialog.show();
				listdialog.dialog_listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						listdialog.dismiss();
						putbankname.setText(APP.banklists.get(position));				
					}
				});
			}
		});

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String subbanknumber=putbanknumber.getText().toString().trim();
				String subopenaccountname=putopenaccountname.getText().toString().trim();
				String subbankname=putbankname.getText().toString().trim();
				if(!"".equals(subbankname) && !"".equals(subopenaccountname) && !"".equals(subbanknumber)){
					if(BankUtil.checkBankCard(subbanknumber)){
						//开始提交
						GetUserDetailEngine userDetail = new GetUserDetailEngine(AddUpdbankActivity.this);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put(Constant.commandText, UrlUtil.userAddOrDelBankCard);
						map.put(Constant.UUID, SharedPrefConstance.getStringValue(
								AddUpdbankActivity.this, SharedPrefConstance.UUID, ""));
						map.put(Constant.userid, SharedPrefConstance.getStringValue(
								AddUpdbankActivity.this, SharedPrefConstance.userid, ""));
						map.put("methodType", "1");
						map.put("bankname",subbankname);
						map.put("banknumber", subbanknumber);
						map.put("openaccountname",subopenaccountname);
						showlog("添加卡号：发送map="+map);

						userDetail.execute(map, new CallBackListener() {
							@Override
							public void onSuccess(String msg) {
								showlog("添加卡号：返回值msg="+msg);
								showToast(AddUpdbankActivity.this, "添加银行卡成功");
								SharedPrefConstance.setSharePref(context, SharedPrefConstance.isBank,"1");
								finish();
							}

							@Override
							public void onFailure(Exception error, String msg) {
							}
						});
					}else {
						showToast(AddUpdbankActivity.this, "您输入的银行卡号有误");
					}
				}else {
					showToast(AddUpdbankActivity.this, "请完善银行卡信息");
				}
			}
		});
	}
	/**
	 * 添加和删除银行啦
	 * @param type
	 */
	public void getuserAddOrDelBankCard(String type,BankBean bankbean){

	}

	/**
	 * adapter
	 * @author cc
	 *
	 */
	public class BankListadapter extends BaseAdapter{

		@Override
		public int getCount() {
			return banklist.size();
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
			convertView=LayoutInflater.from(AddUpdbankActivity.this).inflate(R.layout.ffb_banklist_adapter, null);
			TextView adapter_bankname=(TextView)convertView.findViewById(R.id.adapter_bankname);
			TextView adapter_banknumber=(TextView)convertView.findViewById(R.id.adapter_banknumber);
			adapter_bankname.setText(banklist.get(position).getBankname());
			String number=banklist.get(position).getBanknumber();
			adapter_banknumber.setText("**** **** "+number.substring(number.length()-4, number.length()));

			return convertView;
		}

	}
}
