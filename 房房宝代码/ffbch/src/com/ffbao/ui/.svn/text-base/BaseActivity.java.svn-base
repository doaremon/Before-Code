package com.ffbao.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.download.NewVersionActivity;
import com.ffbao.entity.UpdateVersion;
import com.ffbao.net.CallBackListener;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.update.ProjectUpdate;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.MyProgress;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UrlUtil;

import android.R.bool;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @FileName:BaseActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:BaseActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 基类 acitvity
 */
public class BaseActivity extends FragmentActivity {

	private TextView titleText;
	private TextView imOther;
	private Button back;
	private TextView redBtn;
	public Context context;
	private RichfitAlertDialog dialog;
	private long startTime = 0;
	private static Boolean ifshow=true;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		context = this;

	}

	@Override
	protected void onResume() {
		if (!(this instanceof MainActivity)) {
			if (SharedPrefConstance.mustUpdate
					.equals(SharedPrefConstance.getStringValue(this,
							SharedPrefConstance.mustUpdateVersion))) {
				showlog("进入更新方法");
				verifyUpdate();
			}
		}
		super.onResume();
		hideKeyboard();
	}


	/**
	 * 
	 * @Deprecatred:
	 * @date:2014-11-13
	 * @author:lee
	 * @Funtion:隐藏软键盘method
	 */
	public void hideKeyboard() {
		View view = getWindow().peekDecorView();
		if (view != null) {
			InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
			inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
		}
	}

	//查询城市
	public String selectcity(String id){
		String city="109-石家庄市,145-张家口市,176-承德市,196-秦皇岛市,208-唐山市,229-廊坊市,246-保定市,290-衡水市,310-沧州市,337-邢台市,372-邯郸市," +
				"407-太原市,421-朔州市,432-大同市,451-阳泉市,459-长治市,483-晋城市,494-忻州市,521-晋中市,542-临汾市,574-吕梁市,598-运城市,"+
				"2-北京市,"+
				"23-天津市,"+
				"45-上海市,"+
				"67-重庆市,"+
				"623-呼和浩特市,638-包头市,651-乌海市,655-赤峰市,677-通辽市,692-呼伦贝尔市,713-鄂尔多斯市,729-乌兰察布市,750-巴彦淖尔市,764-兴安盟,775-锡林郭勒盟,798-阿拉善盟,"+
				"805-沈阳市,822-朝阳市,832-阜新市,842-铁岭市,853-抚顺市,864-本溪市,873-辽阳市,882-鞍山市,892-丹东市,900-大连市,912-营口市,919-盘锦市,926-锦州市,936-葫芦岛市,"+
				"946-长春市,958-白城市,966-松原市,976-吉林市,987-四平市,996-辽源市,1003-通化市,1014-白山市,1025-延边州,"+
				"1037-哈尔滨市,1064-齐齐哈尔市,1089-七台河市,1095-黑河市,1105-大庆市,1119-鹤岗市,1130-伊春市,1149-佳木斯市,1165-双鸭山市,1178-鸡西市,1189-牡丹江市,1202-绥化市,1219-大兴安岭地区,"+
				"1227-南京市,1243-徐州市,1259-连云港市,1271-宿迁市,1280-淮安市,1293-盐城市,1308-扬州市,1317-泰州市,1324-南通市,1335-镇江市,1342-常州市,1350-无锡市,1359-苏州市,"+
				"1372-杭州市,1387-湖州市,1396-嘉兴市,1406-舟山市,1413-宁波市,1425-绍兴市,1433-衢州市,1442-金华市,1453-台州市,1465-温州市,1483-丽水市,"+
				"1501-合肥市,1512-宿州市,1522-淮北市,1528-亳州市,1536-阜阳市,1549-蚌埠市,1560-淮南市,1568-滁州市,1581-马鞍山市,1587-芜湖市,1598-铜陵市,1604-安庆市,1623-黄山市,1635-六安市,1648-巢湖市,1658-池州市,1666-宣城市,"+
				"1680-福州市,1699-南平市,1713-莆田市,1719-三明市,1740-泉州市,1758-厦门市,1765-漳州市,1785-龙岩市,1798-宁德市,"+
				"1813-南昌市,1827-九江市,1849-景德镇市,1855-鹰潭市,1860-新余市,1864-萍乡市,1873-赣州市,1907-上饶市,1930-抚州市,1952-宜春市,1967-吉安市,"+
				"1993-济南市,2006-青岛市,2019-聊城市,2029-德州市,2047-东营市,2056-淄博市,2068-潍坊市,2081-烟台市,2095-威海市,2100-日照市,2107-临沂市,2129-枣庄市,2136-济宁市,2154-泰安市,2163-莱芜市,2166-滨州市,2179-菏泽市,"+
				"2198-郑州市,2212-开封市,2228-三门峡市,2238-洛阳市,2262-焦作市,2277-新乡市,2296-鹤壁市,2304-安阳市,2318-濮阳市,2330-商丘市,2346-许昌市,2356-漯河市,2364-平顶山市,2379-南阳市,2400-信阳市,2417-周口市,2436-驻马店市,2455-济源市,"+
				"2457-武汉市,2471-十堰市,2485-襄樊市,2498-荆门市,2506-孝感市,2517-黄冈市,2535-鄂州市,2539-黄石市,2547-咸宁市,2558-荆州市,2570-宜昌市,2589-随州市,2592-省直辖县级行政单位,2598-恩施州,"+
				"2614-长沙市,2628-张家界市,2635-常德市,2651-益阳市,2661-岳阳市,2675-株洲市,2689-湘潭市,2696-衡阳市,2714-郴州市,2734-永州市,2755-邵阳市,2776-怀化市,2799-娄底市,2807-湘西州,"+
				"2823-广州市,2836-深圳市,2843-清远市,2857-韶关市,2872-河源市,2884-梅州市,2899-潮州市,2905-汕头市,2914-揭阳市,2923-汕尾市,2930-惠州市,2937-东莞市,2938-深圳市,2945-珠海市,2949-中山市,2950-江门市,2958-佛山市,2964-肇庆市,2976-云浮市,2985-阳江市,2992-茂名市,3000-湛江市,"+
				"3016-南宁市,3029-桂林市,3059-柳州市,3076-梧州市,3087-贵港市,3094-玉林市,3105-钦州市,3112-北海市,3118-防城港市,3124-崇左市,3137-百色市,3161-河池市,3182-来宾市,3193-贺州市,"+
				"3202-海口市,3207-三亚市,3208-省直辖行政单位,"+
				"3236-成都市,3261-广元市,3273-绵阳市,3289-德阳市,3298-南充市,3313-广安市,3322-遂宁市,3331-内江市,3340-乐山市,3358-自贡市,3367-泸州市,3379-宜宾市,3399-攀枝花市,3407-巴中市,3415-达州市,3428-资阳市,3435-眉山市,3447-雅安市,3463-阿坝州,3490-甘孜州,3527-凉山州,"+
				"3562-贵阳市,3576-六盘水市,3583-遵义市,3608-安顺市,3620-毕节地区,3636-铜仁地区,3656-黔东南州,3688-黔南州,3711-黔西南州,"+
				"3729-昆明市,3752-曲靖市,3769-玉溪市,3787-保山市,3797-昭通市,3819-丽江市,3829-思茅市,3849-临沧市,3865-德宏州,3874-怒江州,3884-迪庆州,3891-大理州,3915-楚雄州,3935-红河州,3960-文山州,3977-西双版纳州,"+
				"3984-拉萨市,4000-那曲地区,4021-昌都地区,4044-林芝地区,4059-山南地区,4084-日喀则地区,4120-阿里地区,"+
				"4137-西安市,4155-延安市,4181-铜川市,4187-渭南市,4207-咸阳市,4232-宝鸡市,4254-汉中市,4276-榆林市,4300-安康市,4320-商洛市,"+
				"4335-兰州市,4347-嘉峪关市,4352-白银市,4361-天水市,4374-武威市,4382-酒泉市,4394-张掖市,4406-庆阳市,4422-平凉市,4436-定西市,4450-陇南市,4467-临夏州,4483-甘南州,"+
				"4500-西宁市,4511-海东地区,4524-海北州,4533-海南州,4544-黄南州,4553-果洛州,4566-玉树州,4579-海西州,"+
				"4589-银川市,4598-石嘴山市,4603-吴忠市,4610-固原市,4620-中卫市,"+
				"4625-乌鲁木齐市,4635-克拉玛依市,4640-自治区直辖县级行政单位,4645-喀什地区,4669-阿克苏地区,4687-和田地区,4702-吐鲁番地区,4708-哈密地区,4714-克孜勒苏柯州,4722-博尔塔拉州,4728-昌吉州,4742-巴音郭楞州,4760-伊犁州,4779-塔城地区,4792-阿勒泰地区,"+
				"4803-香港特别行政区,"+
				"4823-澳门特别行政区,"+
				"4826-台北,4827-高雄,4828-台中,4829-花莲,4830-基隆,4831-嘉义,4832-金门,4833-连江,4834-苗栗,4835-南投,4836-澎湖,4837-屏东,4838-台东,4839-台南,4840-桃园,4841-新竹,4842-宜兰,4843-云林,4844-彰化";

		String []spindex=city.split(",");
		String str=null;
		for(int c=0;c<spindex.length;c++){
			if(id.equals(spindex[c].split("-")[0])){
				str= spindex[c].split("-")[1];
			};
		}
		return str;
	}
	//查询省份
	public String  selectprovince(String id){
		String city = null;
		for(int c=0;c<APP.citylist.size();c++){
			if(id.equals(APP.citylist.get(c).getId())){
				city=APP.citylist.get(c).getCity();
			}
		}
		return city;
	}


	public void init() {

		back = (Button) findViewById(R.id.ib_back_btn);
		if (back.getVisibility() != View.VISIBLE) {
			back.setVisibility(View.VISIBLE);
		}

		setActionBarDialog(false);
		setActionBarOther("");
		getActionBarNew(false, "");
		if (!(this instanceof MainActivity)) {
			findViewById(R.id.rl_back_actionbar).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View view) {
							finish();
						}
					});
			findViewById(R.id.rl_back_actionbar).setOnFocusChangeListener(
					new OnFocusChangeListener() {

						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							finish();
						}
					});
		}
	}

	public Button getActionBarBackImage() {

		back = (Button) findViewById(R.id.ib_back_btn);
		return back;
	}

	public View getActionBarBackLayout() {

		View view = findViewById(R.id.rl_back_actionbar);
		return view;
	}

	public TextView setActionBarOther(String other) {
		imOther = (TextView) findViewById(R.id.tv_operater_others_btn);
		imOther.setText(other);
		return imOther;
	}

	public TextView getActionBarOther() {
		imOther = (TextView) findViewById(R.id.tv_operater_others_btn);
		return imOther;
	}

	public void setActionBarTitle(String title) {

		init();
		titleText = (TextView) findViewById(R.id.tv_middle_actionbar_title);
		if (titleText != null) {
			titleText.setText(title);
		}
	}

	public TextView getActionBarTitle() {

		titleText = (TextView) findViewById(R.id.tv_middle_actionbar_title);
		return titleText;
	}

	public void setActionBarDialog(boolean flag) {

		View view = findViewById(R.id.iv_middle_flag);
		if (flag) {
			view.setVisibility(View.VISIBLE);
		} else {
			view.setVisibility(View.GONE);
		}
	}

	public void getActionBarNew(boolean flag, String num) {
		redBtn = (TextView) findViewById(R.id.main_tab_chat_unread_text);
		redBtn.setText(num);
		if (flag) {
			redBtn.setVisibility(View.VISIBLE);
		} else {
			redBtn.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		MyProgress.getInstance().dismissDialog();
		hideKeyboard();
	}

	public void verifyUpdate() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userUpdateVersion);
		map.put(Constant.clientVersion, ProjectUpdate.getAppCode(this));
		map.put(Constant.type, "1");
		Log.i("chenghao", "更新请求发送="+map);
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {
				Log.i("chenghao", "更新请求返回="+msg);
				try {
					UpdateVersion updateVersion = ExecuteJSONUtils
							.ParseVersion(BaseActivity.this, msg);

					if (updateVersion != null
							&& updateVersion.getUrl().length()!=0) {

						SharedPrefConstance.setSharePref(BaseActivity.this,
								SharedPrefConstance.mustUpdateVersion,
								updateVersion.getMustupdate());
						SharedPrefConstance.setSharePref(BaseActivity.this,
								SharedPrefConstance.updateVersion,
								updateVersion.getServerversion());
						//这里修改过的。。。。。。
						if (!"2".equals(updateVersion.getMustupdate())) {
							Intent intent = new Intent(BaseActivity.this,
									NewVersionActivity.class);
							intent.putExtra(Constant.UpdateVersion,
									updateVersion);

							startActivity(intent);
						}
						else {

						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onFailure(Exception error, String msg) {
			}
		});

	}

	/**
	 * @Deprecatred:
	 * @param type
	 * @date:2014-11-17
	 * @author:lee
	 * @Funtion:版本更新验证
	 */
	public void verifyUpdate(final boolean type) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constant.commandText, UrlUtil.userUpdateVersion);
		map.put(Constant.clientVersion, ProjectUpdate.getAppCode(this));
		map.put(Constant.type, "1");
		HttpClientRequest.getHttpPost(this, map, new CallBackListener() {

			@Override
			public void onSuccess(String msg) {

				try {
					UpdateVersion updateVersion = ExecuteJSONUtils
							.ParseVersion(BaseActivity.this, msg);

					if (updateVersion != null
							&& updateVersion.getUrl().length()!=0) {

						SharedPrefConstance.setSharePref(BaseActivity.this,
								SharedPrefConstance.mustUpdateVersion,
								updateVersion.getMustupdate());
						SharedPrefConstance.setSharePref(BaseActivity.this,
								SharedPrefConstance.updateVersion,
								updateVersion.getServerversion());
						if (!"2".equals(updateVersion.getMustupdate())) {
							Intent intent = new Intent(BaseActivity.this,
									NewVersionActivity.class);
							intent.putExtra(Constant.UpdateVersion,
									updateVersion);
							startActivity(intent);
						} else {
							if (type) {
								ToastUtils.showToast(context, "已经是最新版本");
							}
						}
					} else {
						if (type) {
							ToastUtils.showToast(context, "已经是最新版本");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			@Override
			public void onFailure(Exception error, String msg) {
			}
		});
	}
	public static void showlog(String msg){
		if(ifshow){
			Log.i("chenghao", msg);
		}
	}
	public static void showToast(Context context,String msg){
		if(ifshow){
			Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
		}
	}


	/**
	 * 退出程序
	 */
	public boolean ClickApp() {
		// 判断2次点击事件时间
		if ((System.currentTimeMillis() - startTime) > 200) {
			startTime = System.currentTimeMillis();
			return true;
		} else {
			return false;
		}
	}
}
