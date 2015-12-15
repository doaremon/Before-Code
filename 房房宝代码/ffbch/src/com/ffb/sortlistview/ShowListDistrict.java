package com.ffb.sortlistview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ffb.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.ffbao.activity.R;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.BaseActivity;
import com.ffbao.ui.MainActivity;
import com.ffbao.util.Constant;
import com.ffbao.util.DisplayUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.SharedPrefConstance;

public class ShowListDistrict extends BaseActivity {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	private long exitTime = 0;
	private TextView titleText;

	/**
	 * 汉字转换成拼音的类
	 */
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;

	/**
	 * 根据拼音来排列ListView里面的数据类
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		ExitActivity.addActivity(this);
		titleText = (TextView) findViewById(R.id.tv_middle_actionbar_title);
		titleText.setText("城市列表");
		Button back = (Button) findViewById(R.id.ib_back_btn);
		back.setVisibility(View.GONE);
	}

	private void initViews() {
		//实例化汉字转拼音类
		characterParser = CharacterParser.getInstance();

		pinyinComparator = new PinyinComparator();

		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);

		//设置右侧触摸监听
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {

			@Override
			public void onTouchingLetterChanged(String s) {
				//该字母首次出现的位置
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}

			}
		});

		sortListView = (ListView) findViewById(R.id.country_lvcountry);
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//这里要利用adapter.getItem(position)来获取当前position所对应的对象
				Intent data=new Intent();
				int cityid=((SortModel)adapter.getItem(position)).getId();
				data.putExtra("city",((SortModel)adapter.getItem(position)).getName());
				data.putExtra("cityid",cityid+"");

				//这个0则是结果嘛，自己认为是返回吗，
				setResult(100, data);
				SharedPrefConstance.setSharePref(ShowListDistrict.this, "cityid", cityid+"");
				SharedPrefConstance.setSharePref(ShowListDistrict.this, "cityname", ((SortModel)adapter.getItem(position)).getName());
				finish();
			}
		});
		//数据源！R.array.date)
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, "userGetBuildingAreaInfo");
		showlog("发送的请求城市map值为="+map);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				showlog("返回值为="+msg);
				List<SortModel> mylist=new ArrayList<SortModel>();
				try {
					JSONObject jsonObject=new JSONObject(msg);
					int status=jsonObject.getInt("status");
					if(status!=1){
						showToast(ShowListDistrict.this, jsonObject.getString("message"));
					}else {
						JSONArray array=jsonObject.getJSONArray("result");
						for(int c=0;c<array.length();c++){
							JSONObject jsonObject2=new JSONObject(array.get(c)+"");
							SortModel model=new SortModel();
							model.setId(jsonObject2.getInt("cityId"));
							model.setName(jsonObject2.getString("cityName"));
							mylist.add(model);
							SourceDateList = filledData(mylist);
							// 根据a-z进行排序源数据
							Collections.sort(SourceDateList, pinyinComparator);
							adapter = new SortAdapter(ShowListDistrict.this, SourceDateList);
							sortListView.setAdapter(adapter);
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}


			}

			@Override
			public void onFailure(Exception error, String msg) {

			}
		});

		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);

		//根据输入框输入值的改变来过滤搜索
		mClearEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
				filterData(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (event.getAction() == KeyEvent.ACTION_DOWN
					&& event.getRepeatCount() == 0) {
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}




	@Override
	protected void onDestroy() {
		super.onDestroy();
		String cityid=SharedPrefConstance.getStringValue(ShowListDistrict.this, "cityid", "");
		String cityname=SharedPrefConstance.getStringValue(ShowListDistrict.this, "cityname", "");
		if(cityid.length()==0){
			System.exit(0);
		}

	}

	/**
	 * 为ListView填充数据
	 * @param date
	 * String [] date这个是数组类型的，也可以是list的类型的！
	 * @return
	 */
	private List<SortModel> filledData(List<SortModel> list){
		List<SortModel> mSortList = new ArrayList<SortModel>();

		for(int i=0; i<list.size(); i++){
			SortModel sortModel = new SortModel();
			sortModel.setName(list.get(i).getName());
			sortModel.setId(list.get(i).getId());
			//汉字转换成拼音
			String pinyin = characterParser.getSelling(list.get(i).getName());
			String sortString = pinyin.substring(0, 1).toUpperCase();

			// 正则表达式，判断首字母是否是英文字母
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}

			mSortList.add(sortModel);
		}
		return mSortList;

	}

	/**
	 * 根据输入框中的值来过滤数据并更新ListView
	 * @param filterStr
	 */
	private void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();

		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getName();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}

		// 根据a-z进行排序
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}

}
