package com.u4.home.usb;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class SmartServiceUSB extends Service {

	private byte[] readBuffer;
	private int[] actualNumBytes;
	private UsbControler usb;
	private Timer timer;
	private OnUSBCallBack onUSBCallBack;
	public String data = "";

	/**
	 * 注册回调接口的方法，供外部调用
	 * 
	 * @param onUSBCallBack
	 */
	public void setOnUSBCallBack(OnUSBCallBack onUSBCallBack) {
		this.onUSBCallBack = onUSBCallBack;
	}

	/**
	 * 返回一个Binder对象
	 */
	@Override
	public IBinder onBind(Intent intent) {
		return new mBinder();
	}

	public class mBinder extends Binder {
		/**
		 * 获取当前Service的实例
		 * 
		 * @return
		 */
		public SmartServiceUSB getService() {
			return SmartServiceUSB.this;
		}
	}

	public void sendData(String data) {
		usb.sendData(data);
	}

	public String createData(int type, String code, String unit, int cmd, String data) {
		String body = "", head = "AA55", sum = "00";
		if (type == 1) { // 灯光
			body = "00{code}{cmd}{unit}0000";
			cmd = cmd == 1 ? 1 : 2; // on=1,off=2
		} else if (type == 2) { // 开关
			body = "00{code}4{cmd}0000";
			cmd = cmd == 1 ? 1 : 2; // on=1,off=2
		} else if (type == 3) { // 窗帘
			body = "00{code}{cmd}{unit}0000";
			cmd = cmd == 1 ? 5 : 7; // on=5,off=7
		} else if (type == 4) {// 红外
			body = "F0{code}A{cmd}00{data}"; // data:40-ab;cmd:normal=1,start=2,study=3,exit=4
			data = (cmd == 2 || cmd == 4) ? "00" : data;
		}
		body = body.replace("{code}", code).replace("{unit}", unit).replace("{cmd}", cmd + "").replace("{data}", data + "");
		sum = makesum(body);
		return head + body + sum;
	}

	public static String makesum(String body) {
		int sum = 0;
		for (int i = 0; i < body.length(); i += 2) {
			sum += Integer.valueOf(body.substring(i, i + 2), 16);
		}
		String hex = Integer.toHexString(sum).toUpperCase();
		if (hex.length() < 2) {
			hex = "0" + hex;
		}
		if (hex.length() > 2) {
			hex = hex.substring(hex.length() - 2);
		}
		return hex;
	}

	public String createData(int type, String code, String unit, int cmd) {
		return createData(type, code, unit, cmd, "");
	}

	@Override
	public void onCreate() {
		super.onCreate();

		readBuffer = new byte[4096];
		actualNumBytes = new int[1];

		usb = new UsbControler(this);
		usb.setConfig();

		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (usb.readData(readBuffer, actualNumBytes)) {
					data += usb.getData(readBuffer, actualNumBytes).replace(" ", "");
				}
				if (onUSBCallBack != null && data.length() >= 22) {
					String a = data.substring(0, 4);
					if ("aa55".equals(a)) {
						onUSBCallBack.onRead(data.substring(0, 22));
						data = data.length() > 22 ? data.substring(22) : "";
						System.out.println(data);
					}
				}

			}
		}, 0, 500);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		usb.destroy();
	}

}
