package com.u4.home.usb;

import android.content.Context;
import android.content.SharedPreferences;

public class UsbControler {
	byte[] writeBuffer = new byte[64];
	char[] readBufferToChar = new char[4096];
	int baudRate = 9600; /* baud rate */
	byte stopBit = 1; /* 1:1stop bits, 2:2 stop bits */
	byte dataBit = 8; /* 8:8bit, 7: 7bit */
	byte parity = 0; /* 0: none, 1: odd, 2: even, 3: mark, 4: space */
	byte flowControl = 0; /* 0:none, 1: flow control(CTS,RTS) */
	public boolean bConfiged = false;
	public SharedPreferences sharePrefSettings;
	public FT311UARTInterface uartInterface;

	public UsbControler(Context context) {
		sharePrefSettings = context.getSharedPreferences("UARTLBPref", 0);
		uartInterface = new FT311UARTInterface(context, sharePrefSettings);
	}

	public void setConfig() {
		if (2 == uartInterface.ResumeAccessory()) {
			baudRate = sharePrefSettings.getInt("baudRate", 9600);
			stopBit = (byte) sharePrefSettings.getInt("stopBit", 1);
			dataBit = (byte) sharePrefSettings.getInt("dataBit", 8);
			parity = (byte) sharePrefSettings.getInt("parity", 0);
			flowControl = (byte) sharePrefSettings.getInt("flowControl", 0);
		}
		bConfiged = true;
		uartInterface.SetConfig(baudRate, dataBit, stopBit, parity, flowControl);
		savePreference();
	}

	protected void savePreference() {
		if (true == bConfiged) {
			sharePrefSettings.edit().putString("configed", "TRUE").commit();
			sharePrefSettings.edit().putInt("baudRate", baudRate).commit();
			sharePrefSettings.edit().putInt("stopBit", stopBit).commit();
			sharePrefSettings.edit().putInt("dataBit", dataBit).commit();
			sharePrefSettings.edit().putInt("parity", parity).commit();
			sharePrefSettings.edit().putInt("flowControl", flowControl).commit();
		} else {
			sharePrefSettings.edit().putString("configed", "FALSE").commit();
		}
	}

	public void destroy() {
		uartInterface.DestroyAccessory(bConfiged);
	}

	public boolean readData(byte[] buffer, int[] actualNumBytes) {
		byte status = uartInterface.ReadData(4096, buffer, actualNumBytes);
		if (status == 0x00 && actualNumBytes[0] > 0) {
			return true;
		}
		return false;
	}

	public void sendData(String srcStr) {
		String destStr = "";
		try {
			destStr = hexToAscii(srcStr.replaceAll(" ", ""));
		} catch (IllegalArgumentException e) {
			return;
		}
		int numBytes = destStr.length();
		for (int i = 0; i < numBytes; i++) {
			writeBuffer[i] = (byte) destStr.charAt(i);
		}
		System.out.println("main   numBytes=====" + numBytes);
		System.out.println("main   writeBuffer=====" + writeBuffer);
		uartInterface.SendData(numBytes, writeBuffer);
	}

	public String getData(byte[] buffer, int[] lens) {
		int len = lens[0];
		for (int i = 0; i < len; i++) {
			readBufferToChar[i] = (char) buffer[i];
		}

		StringBuffer readSB = new StringBuffer();
		if (len >= 1)
			readSB.append(String.copyValueOf(readBufferToChar, 0, len));
		char[] ch = readSB.toString().toCharArray();
		String temp;

		StringBuilder tmpSB = new StringBuilder();
		for (int i = 0; i < ch.length; i++) {
			temp = String.format("%02x", (int) ch[i]);
			if (temp.length() == 4) {
				tmpSB.append(temp.substring(2, 4));
			} else {
				tmpSB.append(temp);
			}

			if (i + 1 < ch.length) {
				tmpSB.append(" ");
			}
		}
		return tmpSB.toString();
	}

	String hexToAscii(String s) throws IllegalArgumentException {
		int n = s.length();
		StringBuilder sb = new StringBuilder(n / 2);
		for (int i = 0; i < n; i += 2) {
			char a = s.charAt(i);
			char b = s.charAt(i + 1);
			sb.append((char) ((hexToInt(a) << 4) | hexToInt(b)));
		}
		return sb.toString();
	}

	static int hexToInt(char ch) {
		if ('a' <= ch && ch <= 'f') {
			return ch - 'a' + 10;
		}
		if ('A' <= ch && ch <= 'F') {
			return ch - 'A' + 10;
		}
		if ('0' <= ch && ch <= '9') {
			return ch - '0';
		}
		throw new IllegalArgumentException(String.valueOf(ch));
	}

	String decToAscii(String s) throws IllegalArgumentException {
		int n = s.length();
		boolean pause = false;
		StringBuilder sb = new StringBuilder(n / 2);
		for (int i = 0; i < n; i += 3) {
			char a = s.charAt(i);
			char b = s.charAt(i + 1);
			char c = s.charAt(i + 2);
			int val = decToInt(a) * 100 + decToInt(b) * 10 + decToInt(c);
			if (0 <= val && val <= 255) {
				sb.append((char) val);
			} else {
				pause = true;
				break;
			}
		}
		if (false == pause)
			return sb.toString();
		throw new IllegalArgumentException("ex_b");
	}

	static int decToInt(char ch) {
		if ('0' <= ch && ch <= '9') {
			return ch - '0';
		}
		throw new IllegalArgumentException("ex_a");
	}
}