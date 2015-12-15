package com.u4.home.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.videolan.vlc.VLCApplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.u4.home.command.CommandListenerService;
import com.u4.home.db.Equipment_Shitilei;
import com.u4.home.usb.SmartServiceUSB;

public class Appcontext extends VLCApplication {

	private String localIp;
	private String deviceCode;
	private List<Object> activityList = new LinkedList<Object>();
	public static int width, height;
	public static Boolean undefended = true;
	public static Boolean ifservice = true;
	public static Appcontext mainInstance;
	public static String roomNumber;
	public static String dbName = "smart";
	public static int dbVersion = 1;
	public static Equipment_Shitilei shitilei = new Equipment_Shitilei();

	public static int newHistory = 0, newMessage = 0, newNotic = 0;

	public static String preferencesName = "config";
	public static SharedPreferences preferences;

	public static String conf_server_ip = "", conf_server_port = "", conf_undefence_password = "";
	public static int conf_undefence_mode = 0;

	public static SmartServiceUSB serviceUSB = null;
	public static Handler handlerUSB = null;
	public static String busy = "";
	public static String myurl = "";
	public static String basepath = "";
	public static Boolean debug = false;
	public static Boolean registered = false;

	public static JSONObject weather = null;
	public static JSONArray adarray=null;
	public static Boolean isdowntrue=false;

	public static int idx=0; 
	public static int sec=0; 
	public static int lng=0; 

	@Override
	public void onCreate() {
		super.onCreate();
		showLog("onCreate Start");

		preferences = getSharedPreferences(preferencesName, MODE_PRIVATE);

		mainInstance = this;
		localIp = getLocalIpAddress();
		getDeviceId();

		DisplayMetrics dm = new DisplayMetrics();
		WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		manager.getDefaultDisplay().getMetrics(dm);
		width = dm.widthPixels;
		height = dm.heightPixels;

		if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			basepath = Environment.getExternalStorageDirectory().getPath();
		}

		setServer(getConfigString("conf_server_ip"), getConfigString("conf_server_port"));

		conf_undefence_mode = getConfigInt("conf_undefence_mode");
		conf_undefence_password = getConfigString("conf_undefence_password");

		Intent intent = new Intent(this, CommandListenerService.class);
		startService(intent);

		showLog("onCreate End");
	}

	private void showLog(String log) {
		Log.i("Appcontext", log);
	}

	/**
	 * 获取设备唯一标识
	 */
	private void getDeviceId() {
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		deviceCode = telephonyManager.getDeviceId();
		if (deviceCode == null) {
			WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wifi.getConnectionInfo();
			deviceCode = info.getMacAddress().replace(":", "");
		}
		if (deviceCode == null) {
			deviceCode = preferences.getString("deviceCode", System.currentTimeMillis() + "");
			preferences.edit().putString("deviceCode", deviceCode).commit();
		}
	}

	/**
	 * 得到本机IP地址
	 * 
	 * @return
	 */
	public String getLocalIpAddress() {
		try {
			// 获得当前可用的wifi网络
			Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
			while (en.hasMoreElements()) {
				NetworkInterface nif = en.nextElement();
				Enumeration<InetAddress> enumIpAddr = nif.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress mInetAddress = enumIpAddr.nextElement();
					if (!mInetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(mInetAddress.getHostAddress())) {
						return mInetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			Toast.makeText(this, "获取本机IP地址失败", Toast.LENGTH_SHORT).show();
		}
		return null;
	}

	/**
	 * 返回版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVerCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo("com.u4.home", 0).versionCode;
		} catch (NameNotFoundException e) {
			Log.i("Appcontext", e.getMessage());
		}
		return verCode;
	}

	/**
	 * 返回版本名
	 * 
	 * @param context
	 * @return
	 */
	public static String getVerName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo("com.u4.home", 0).versionName;
		} catch (NameNotFoundException e) {
			Log.i("Appcontext", e.getMessage());
		}
		return verName;
	}

	/**
	 * 检测SDCard
	 * 
	 * @return
	 */
	public static boolean hasSDcard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	public String getLocalIp() {
		if (localIp == null)
			localIp = getLocalIpAddress();
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public void setServer(String ip, String port) {
		if (!ip.equals("")) {
			conf_server_ip = ip;
			conf_server_port = port;
			myurl = "http://" + ip + ":" + port + "/api/api.php";
		}
	}


	public String getConfigString(String key) {
		if (key.equals(""))
			return "";
		return preferences.getString(key, "");
	}

	public int getConfigInt(String key) {
		if (key.equals(""))
			return 0;
		return preferences.getInt(key, 0);
	}

	public void setConfig(String key, String value) {
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public void setConfig(String key, int value) {
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	// 单例模式
	public static Appcontext getInstance() {
		if (null == mainInstance) {
			mainInstance = new Appcontext();
		}
		return mainInstance;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exit() {
		for (Object activity : activityList) {
			((Activity) activity).finish();
		}
		System.exit(0);
	}

}
