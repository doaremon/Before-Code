package com.ffbao.ui;

import java.util.HashMap;
import java.util.Map;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.engine.impl.GetUserDetailEngine;
import com.ffbao.net.CallBackListener;
import com.ffbao.ui.adapter.BanklistDialogAdapter;
import com.ffbao.util.BankUtil;
import com.ffbao.util.Constant;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.MyListDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.UrlUtil;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 这个是在申请结佣的时候，如何没有银行卡，则跳转到次页面添加卡
 * @author cc
 *
 */
public class AddBnakActivity extends BaseActivity{
	private LinearLayout showbank;
	private EditText putbanknumber;
	private EditText putopenaccountname;
	private TextView putbankname;
	private Button submit;
	private  MyListDialog listdialog;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_addupdbank);
		setActionBarTitle("绑定银行卡");
		ExitActivity.addActivity(this);
		findid();
	}
	@Override
	protected void onResume() {
		super.onResume();
		onClick();
	}

	public void findid(){
		showbank=(LinearLayout) findViewById(R.id.showbank);
		showbank.setVisibility(View.GONE);
		putbankname=(TextView)findViewById(R.id.putbankname);
		putbanknumber=(EditText)findViewById(R.id.putbanknumber);
		putopenaccountname=(EditText)findViewById(R.id.putopenaccountname);
		submit=(Button)findViewById(R.id.submit);
	}
	public void onClick(){
		putbankname.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listdialog=new MyListDialog(AddBnakActivity.this);
				BanklistDialogAdapter adapter=new BanklistDialogAdapter(AddBnakActivity.this, APP.banklists);
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
						GetUserDetailEngine userDetail = new GetUserDetailEngine(AddBnakActivity.this);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put(Constant.commandText, UrlUtil.userAddOrDelBankCard);
						map.put(Constant.UUID, SharedPrefConstance.getStringValue(
								AddBnakActivity.this, SharedPrefConstance.UUID, ""));
						map.put(Constant.userid, SharedPrefConstance.getStringValue(
								AddBnakActivity.this, SharedPrefConstance.userid, ""));
						map.put("methodType", "1");
						map.put("bankname",subbankname);
						map.put("banknumber", subbanknumber);
						map.put("openaccountname",subopenaccountname);
						showlog("添加卡号：发送map="+map);

						userDetail.execute(map, new CallBackListener() {
							@Override
							public void onSuccess(String msg) {
								showlog("添加卡号：返回值msg="+msg);
								showToast(AddBnakActivity.this, "添加银行卡成功");
								SharedPrefConstance.setSharePref(context, SharedPrefConstance.isBank,"1");
								finish();
							}

							@Override
							public void onFailure(Exception error, String msg) {
							}
						});
					}else {
						showToast(AddBnakActivity.this, "您输入的银行卡号有误");
					}
				}else {
					showToast(AddBnakActivity.this, "请完善银行卡信息");
				}
			}
		});
	}

}
