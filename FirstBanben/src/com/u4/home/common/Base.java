package com.u4.home.common;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.u4.home.R;
import com.u4.home.command.TranslucentActivity;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.AsyncHttpResponseHandler;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;
import com.u4.home.main.Config;
import com.u4.home.main.HomeIndex;
import com.u4.home.main.Newmessage;
import com.u4.home.phoneservice.PhoneService;
import com.u4.home.safe.WarningList;
import com.u4.home.usb.OnUSBCallBack;
import com.u4.home.usb.SmartServiceUSB;
import com.u4.utils.DownloderFile;

public class Base extends Activity {
	private TextView tv_new_mail;
	private ImageView iv_new_mail, ad_imageView;
	private Button btn_nav_home, btn_nav_warning, btn_nav_mail, btn_nav_manager, btn_nav_setting;
	private Button btn_top_finish;

	private VideoView ad_videoView;

	private RelativeLayout rl_top_timer;
	private TextView tv_top_time, tv_top_month, tv_top_day, tv_top_date, tv_top_week;
	private String[] chineseMonth = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
	private String[] chineseWeek = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	private Handler handlerTopTimer = null;
	private Timer topTimer = null;
	private Handler adtimerhandler = null;// 广告timerhandler
	protected int res_top_finish = 0;
	protected String myName = "";
	private Boolean ifshowlog = true;
	private String targetip;

	private ImageView iv_weather_icon;
	private TextView tv_weather_info, tv_weather_temp;
	private Handler handlerWeather = null;

	private NewMessageBroadcast newMessageBroadcast;
	private String tablename;

	protected Context context;
	protected LayoutInflater inflater;
	private int index = 0;
	private int max;
	private Timer adtimer; // 广告timer
	private Intent serviceintent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		closeBar();
//		showBar();

//		requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏标题
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN); //设置全屏
//		if (getRequestedOrientation() !=
//				ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//			// //设置横屏
//		}
		context = this;
		myName = context.toString().split("@")[0];
		serverSetIP(Appcontext.myurl);
		showLog("name=" + myName);
		tablename = myName.split("\\.")[4];
		showLog("tablename=" + tablename);
		Appcontext.mainInstance.addActivity(this);

		inflater = LayoutInflater.from(context);

		serviceintent = new Intent(Base.this, PhoneService.class);
		startService(serviceintent);

	}

	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(newMessageBroadcast);
		showLog("unregisterReceiver : NewMessageBroadcast");
		if (topTimer != null) {
			topTimer.cancel();
			topTimer = null;
		}
		if (adtimer != null) {
			adtimer.cancel();
			adtimer = null;
		}
	}
	public static String[] parseHex(String hex) {
		Pattern pat = Pattern.compile("(?i)aa55(\\w{2})(\\w{8})(\\w{2})(\\w{4})(\\w{2})");
		Matcher mat = pat.matcher(hex);
		if (mat.find()) {
			String[] res = new String[mat.groupCount() + 1];
			for (int i = 0; i <= mat.groupCount(); i++) {
				res[i] = mat.group(i).toUpperCase();
			}
			return res;
		}
		String[] res = {};
		return res;
	}


	@Override
	protected void onResume() {
		super.onResume();

		newMessageBroadcast = new NewMessageBroadcast();
		registerReceiver(newMessageBroadcast, new IntentFilter("newmessage"));
		showLog("registerReceiver : NewMessageBroadcast");
		/**
		 * 返回按钮
		 */
		btn_top_finish = (Button) findViewById(R.id.btn_top_finish);
		if (btn_top_finish != null) {
			if (res_top_finish > 0) {
				btn_top_finish.setText(res_top_finish);
			}

			btn_top_finish.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}

		iv_weather_icon = (ImageView) findViewById(R.id.iv_weather);
		tv_weather_info = (TextView) findViewById(R.id.tv_weather_info);
		tv_weather_temp = (TextView) findViewById(R.id.tv_weather_temp);
		if (iv_weather_icon != null) {
			handlerWeather = new Handler() {
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					iv_weather_icon.setImageBitmap((Bitmap) msg.obj);
				}
			};
		}
		/**
		 * 时间显示
		 */
		rl_top_timer = (RelativeLayout) findViewById(R.id.rl_top_timer);
		if (rl_top_timer != null) {
			tv_top_time = (TextView) findViewById(R.id.tv_top_time);
			tv_top_date = (TextView) findViewById(R.id.tv_top_date);
			tv_top_week = (TextView) findViewById(R.id.tv_top_week);
			tv_top_month = (TextView) findViewById(R.id.tv_top_month);
			tv_top_day = (TextView) findViewById(R.id.tv_top_day);

			handlerTopTimer = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);

					Time time = new Time();
					time.setToNow();

					tv_top_time.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/AndroidClock.ttf"));
					tv_top_time.setText(String.format("%02d:%02d", time.hour, time.minute));

					if (tv_top_date != null)
						tv_top_date.setText((time.month + 1) + "月" + time.monthDay + "日");
					if (tv_top_week != null)
						tv_top_week.setText(chineseWeek[time.weekDay]);
					if (tv_top_month != null)
						tv_top_month.setText(chineseMonth[time.month]);
					if (tv_top_day != null)
						tv_top_day.setText(time.monthDay + "");

					if (Appcontext.weather == null || time.minute == 0 || time.minute == 30) {
						serverWeather();
					}
					if (time.minute == 0 || time.minute == 30) {
						Appcontext.isdowntrue = false;
						// String
						// str=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"U4ad";
						// try {
						// System.out.println("str=="+str);
						// deleteFile(new File(str));
						// } catch (Exception e) {
						// e.printStackTrace();
						// }
						advertisement();
					}

					newmessage();
				}
			};
			startTopTimer();
		}

		btn_nav_home = (Button) findViewById(R.id.btn_nav_home);
		if (btn_nav_home != null)
			btn_nav_home.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!"HomeIndex".equals(tablename)) {
						Intent intent = new Intent(Base.this, HomeIndex.class);
						startActivity(intent);
					}

				}
			});

		btn_nav_warning = (Button) findViewById(R.id.btn_nav_warning);
		if (btn_nav_warning != null) {
			btn_nav_warning.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!"Baojingjilu".equals(tablename)) {
						Intent intent = new Intent(Base.this, WarningList.class);
						startActivity(intent);
					}

				}
			});
		}

		btn_nav_mail = (Button) findViewById(R.id.btn_nav_mail);
		if (btn_nav_mail != null) {
			tv_new_mail = (TextView) findViewById(R.id.tv_new_mail);
			iv_new_mail = (ImageView) findViewById(R.id.iv_new_mail);
			btn_nav_mail.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!"Newmessage".equals(tablename)) {
						Intent intent = new Intent(Base.this, Newmessage.class);
						startActivity(intent);
					}

				}
			});
		}

		btn_nav_manager = (Button) findViewById(R.id.btn_nav_manager);
		if (btn_nav_manager != null) {
			btn_nav_manager.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					callManager();
				}
			});
		}
		/**
		 * 广告布局以及显示
		 */

		ad_imageView = (ImageView) findViewById(R.id.ad_imageView);
		ad_videoView = (VideoView) findViewById(R.id.ad_videoView);
		adstart();
		if (ad_imageView != null && ad_videoView != null) {
			adtimerhandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					if (Appcontext.adarray != null) {
						if (Appcontext.sec == 0) {
							try {
								String path = Environment.getExternalStorageDirectory() + "/U4ad/" + Appcontext.adarray.getJSONObject(Appcontext.idx).getString("name");
								showLog("path=" + path);
								if (Appcontext.adarray.getJSONObject(Appcontext.idx).getString("type").equals("image")) {
									ad_videoView.setVisibility(View.GONE);
									ad_videoView.stopPlayback();
									ad_imageView.setVisibility(View.VISIBLE);
									ad_imageView.setImageBitmap(BitmapFactory.decodeFile(path));
								} else if (Appcontext.adarray.getJSONObject(Appcontext.idx).getString("type").equals("video")) {
									ad_imageView.setVisibility(View.GONE);
									ad_videoView.setVisibility(View.VISIBLE);
									playvideo(path);
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
						if (Appcontext.sec >= max) {
							Appcontext.idx++;
							if (Appcontext.idx >= Appcontext.lng)
								Appcontext.idx = 0;
							Appcontext.sec = 0;
							try {
								max = Appcontext.adarray.getJSONObject(Appcontext.idx).getInt("time");
							} catch (JSONException e) {
								e.printStackTrace();
							}
							// showLog(tablename + "====if idx=" +
							// Appcontext.idx + ",sec=" + Appcontext.sec);
						} else {
							Appcontext.sec++;
							// showLog(tablename + "=====else =" +
							// Appcontext.sec + "+sec");
						}
					}
				}
			};
		}

		btn_nav_setting = (Button) findViewById(R.id.btn_nav_setting);
		if (btn_nav_setting != null) {
			btn_nav_setting.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (!"Config".equals(tablename)) {
						Intent intent = new Intent(Base.this, Config.class);
						startActivity(intent);
					}

				}
			});
		}
	}

	/**
	 * 显示bar
	 */
	private void showBar() {
		try {
			Process proc = Runtime.getRuntime().exec(new String[] { "am", "startservice", "-n", "com.android.systemui/.SystemUIService" });
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 隐藏bar
	 */
	private void closeBar() {
		try {
			// 需要root 权限
			Build.VERSION_CODES vc = new Build.VERSION_CODES();
			Build.VERSION vr = new Build.VERSION();
			String ProcID = "79";

			if (vr.SDK_INT >= vc.ICE_CREAM_SANDWICH) {
				ProcID = "42"; // ICS AND NEWER
			}

			// 需要root 权限
			Process proc = Runtime.getRuntime().exec(new String[] { "su", "-c", "service call activity " + ProcID + " s16 com.android.systemui" }); // WAS
			// 79
			proc.waitFor();

		} catch (Exception ex) {
			Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 当下载完成时，接受消息，开始播放
	 * 
	 * @author Administrator
	 * 
	 */

	public class adcast extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {

			adstart();
		}
	}

	/**
	 * 开始播放
	 */
	public void adstart() {
		showLog("adstart  ： 进入新页面  idx=" + Appcontext.idx + ",:  sec=" + Appcontext.sec);
		if (Appcontext.adarray != null && ad_imageView != null && ad_videoView != null) {
			if (Appcontext.isdowntrue) {
				showLog("条件满足    执行adstatrt");
				Appcontext.lng = Appcontext.adarray.length();
				try {
					max = Appcontext.adarray.getJSONObject(Appcontext.idx).getInt("time");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				adtimer = new Timer();
				adtimer.schedule(new TimerTask() {
					@Override
					public void run() {
						adtimerhandler.sendMessage(new Message());
					}
				}, 0, 1000);
			}

		} else {
			showLog("条件bu满足    执行adstatrt");
		}
	}

	private void playvideo(String path) {
		ad_videoView.setVideoPath(path);
		ad_videoView.requestFocus();
		ad_videoView.seekTo(Appcontext.sec * 1000);
		ad_videoView.start();

	}

	public static void deleteFile(File oldPath) {
		if (oldPath.isDirectory()) {
			File[] files = oldPath.listFiles();
			for (File file : files) {
				deleteFile(file);
				file.delete();
			}
		} else {
			oldPath.delete();
		}
	}

	public void callManager() {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("c", "getmanageip");
		asyncHttpClient.get(Appcontext.myurl, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				showLog("serverGetManageIp : " + content);
				try {
					JSONObject object = new JSONObject(content);
					if (object.getInt("status") == 1) {
						JSONObject object1 = new JSONObject(object.getString("data"));
						targetip = object1.getString("ip");
						showLog("targetip : " + targetip);
						Intent intent = new Intent(context, TranslucentActivity.class);
						intent.putExtra("type", "1"); // 1主叫,2被叫
						intent.putExtra("myname", Appcontext.roomNumber.replace("栋", "d").replace("门", "m"));
						intent.putExtra("targetip", targetip);
						intent.putExtra("targetname", "管理中心");
						startActivity(intent);
						showLog("主叫方已经发送请求");
					} else {
						showToast(object.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void startTopTimer() {
		if (topTimer == null) {
			topTimer = new Timer();
			topTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					handlerTopTimer.sendMessage(new Message());
				}
			}, 0, 1000 * 60);
		}
	}

	public void showToast(String msg) {
		if (ifshowlog)
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public void showToast(int resId) {
		if (ifshowlog) {
			Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
		}
	}

	public void showLog(String msg) {
		if (ifshowlog)
			Log.i(myName, msg);
	}

	/**
	 * 启动USB服务
	 */
	public void startServiceUSB() {
		Intent intent = new Intent(context, SmartServiceUSB.class);
		startService(intent);
		bindService(intent, new connUSB(), BIND_AUTO_CREATE);
	}

	/**
	 * 连接USB服务
	 */
	public class connUSB implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Appcontext.serviceUSB = ((SmartServiceUSB.mBinder) service).getService();
			Appcontext.serviceUSB.setOnUSBCallBack(new OnUSBCallBack() {
				@Override
				public void onRead(String data) {
					if (Appcontext.handlerUSB != null) {
						Message message = new Message();
						message.obj = data;
						Appcontext.handlerUSB.sendMessage(message);
					}
				}
			});
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
	}

	/**
	 * 获取信息消息数量
	 */
	public void newmessage() {
		if (Appcontext.myurl.equals(""))
			return;
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("c", "newtip");
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog("newmessage : " + response.toString());
				try {
					if (1 == response.getInt("status")) {
						JSONObject data = response.getJSONObject("data");
						Appcontext.newHistory = data.getInt("history");
						Appcontext.newMessage = data.getInt("message");
						Appcontext.newNotic = data.getInt("notic");
						Intent intent = new Intent();
						intent.setAction("newmessage");
						intent.putExtra("count", data.getInt("count"));
						sendBroadcast(intent);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 新消息广播
	 */
	public class NewMessageBroadcast extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			if (tv_new_mail != null && iv_new_mail != null) {
				int count = intent.getExtras().getInt("count");
				if (count > 0) {
					tv_new_mail.setText(count > 9 ? "N" : count + "");
					if (tv_new_mail.getVisibility() == View.GONE)
						tv_new_mail.setVisibility(View.VISIBLE);
					if (iv_new_mail.getVisibility() == View.GONE)
						iv_new_mail.setVisibility(View.VISIBLE);
				} else {
					if (tv_new_mail.getVisibility() == View.VISIBLE)
						tv_new_mail.setVisibility(View.GONE);
					if (iv_new_mail.getVisibility() == View.VISIBLE)
						iv_new_mail.setVisibility(View.GONE);
				}
			}
		}
	}

	Handler adhandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			switch (msg.what) {
			case -1:
				index = index + 1;
				showLog("文件存在" + msg.obj);

				break;
			case 0:
				index = index + 1;
				showLog("文件失败" + msg.obj);
				break;
			case 1:
				index = index + 1;
				showLog("文件成功" + msg.obj);
				break;
			default:
				break;
			}
			System.out.println("index=" + index);
			if (index == Appcontext.adarray.length()) {
				showLog("全部下载完成");
				Appcontext.isdowntrue = true;
				Intent intent = new Intent();
				intent.setAction("OK");
				sendBroadcast(intent);
			}

		}
	};

	public void down(final JSONArray array, final String dirname) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				/**
				 * 开始下载文件 用for循环来异步一个一个的下载
				 */
				for (int c = 0; c < array.length(); c++) {
					try {
						System.out.println("url========" + array.getJSONObject(c).getString("url"));
						System.out.println("name========" + array.getJSONObject(c).getString("name"));
						DownloderFile downloader = new DownloderFile(array.getJSONObject(c).getString("url"), dirname, array.getJSONObject(c).getString("name"));
						int aa = downloader.downloder();
						Message message = new Message();
						message.what = aa;
						message.obj = c;
						adhandler.sendMessage(message);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	public void advertisement() {
		if (Appcontext.myurl.equals(""))
			return;
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("c", "ad");
		params.put("id", Appcontext.mainInstance.getDeviceCode());
		asyncHttpClient.get("http://192.168.1.146/api/api.php", params, new JsonHttpResponseHandler() {

			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog("广告response=" + response);
				try {
					if ("1".equals(response.getString("status"))) {
						Appcontext.adarray = response.getJSONArray("data");
						System.out.println("Appcontext.adarray=" + Appcontext.adarray);
						down(Appcontext.adarray, "U4ad");
					} else {
						showToast(response.getString("message"));
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 获取天气信息
	 */
	public void serverWeather() {
		if (Appcontext.myurl.equals(""))
			return;
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("c", "weather");
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog("serverWeather : " + response.toString());
				try {
					if (1 == response.getInt("status")) {
						Appcontext.weather = response.getJSONObject("data");
						Intent intent = new Intent();
						intent.setAction("weather");
						sendBroadcast(intent);
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 天气信息广播
	 */
	public class WeatherBroadcast extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			setWeather();
		}
	}

	public void setWeather() {
		if (Appcontext.weather != null) {
			try {
				tv_weather_temp.setText(Appcontext.weather.getString("temp"));
				tv_weather_info.setText(Appcontext.weather.getString("wn") + " - " + Appcontext.weather.getString("city"));
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			Runnable runable = new Runnable() {
				@Override
				public void run() {
					try {
						Message message = new Message();
						message.obj = BitmapFactory.decodeStream(new URL(Appcontext.weather.getString("icon")).openStream());
						handlerWeather.sendMessage(message);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			};
			Thread thread = new Thread(runable);
			thread.start();
		}
	}

	/**
	 * 注册到服务器
	 * 
	 * @param myurl
	 */
	public void serverSetIP(String myurl) {
		if (myurl.equals("") || Appcontext.registered)
			return;
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("c", "setip");
		params.put("id", Appcontext.mainInstance.getDeviceCode());
		asyncHttpClient.get(myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog("serverSetIP : " + response.toString());
				try {
					int status = response.getInt("status");
					if (status == 1) {
						Appcontext.registered = true;
						JSONObject data = response.getJSONObject("data");
						Appcontext.roomNumber = data.getString("build") + "栋" + data.getString("unit") + "门" + data.getString("room");
						showToast(Appcontext.roomNumber);
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 从JSONArray中移除指定索引内容
	 * 
	 * @param jsonArray
	 * @param position
	 * @return
	 */
	public JSONArray removeJSONArray(JSONArray jsonArray, int position) {
		JSONArray jsonTemp = new JSONArray();
		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				if (i != position) {
					jsonTemp.put(jsonArray.getJSONObject(i));
				}
			}
		} catch (JSONException e) {
			return jsonArray;
		}
		return jsonTemp;
	}

	public class MenuAdapter extends BaseAdapter {
		private int arrName[] = null;
		private int arrIcon[] = null;
		private Class<?> arrJump[] = null;

		public MenuAdapter(int name[], int icon[], Class<?> jump[]) {
			arrName = name;
			arrIcon = icon;
			arrJump = jump;
		}

		@Override
		public int getCount() {
			return arrName.length;
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
		public View getView(final int position, View convertView, ViewGroup parent) {
			if (arrName.length > 0) {
				convertView = inflater.inflate(R.layout.menu_grid_adapter, null);
				TextView tvMenu = (TextView) convertView.findViewById(R.id.tv_menu);
				tvMenu.setText(arrName[position]);
				setDrawableTop(tvMenu, arrIcon[position]);
				tvMenu.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						startActivity(new Intent(context, arrJump[position]));
					}
				});
			}
			return convertView;
		}
	}

	private void setDrawableTop(TextView tv, int id) {
		Drawable drawable = getResources().getDrawable(id);
		drawable.setBounds(0, 0, 96, 96);
		tv.setCompoundDrawables(null, drawable, null, null);
	}

}
