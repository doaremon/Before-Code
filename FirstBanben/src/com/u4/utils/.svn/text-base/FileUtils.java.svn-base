package com.u4.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.os.Environment;

public class FileUtils {
	private String sdpath = null;// 存储SD卡的路径

	// 构造方法也是文件路径
	public FileUtils() {
		super();
		sdpath = Environment.getExternalStorageDirectory() + "/";
	}
	/**判断目录是否存在
	 * 
	 * @param dirname 目录名
	 * @return
	 */
	public boolean MuluExists(String dirname) {
		File filedir = new File(sdpath + dirname);
		return filedir.exists();
	}
	/**
	 *  判断文件是否存在
	 * @param dirname
	 * @param filename 文件名
	 * @return
	 */
	public boolean WenjianExists(String dirname, String filename) {
		File wenjianfile = new File(sdpath + dirname + "/" + filename);
		return wenjianfile.exists();
	}

	/**
	 * 创建目录 
	 * @param dirname 目录名
	 * @return
	 */
	public boolean createSDDir(String dirname) {
		if (!MuluExists(dirname)) {
			File dir = new File(sdpath + dirname);

			return dir.mkdir();
		}
		return true;
	}
	/**创建文件
	 * filename文件名
	 * @param dirname
	 * @param Filename
	 * @return
	 */
	public File creatFile(String dirname, String Filename) {
		File file = new File(sdpath + dirname + "/" + Filename);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;

	}
	/**
	 * 存储下载文件
	 * 
	 * @param dirname
	 *            目录名
	 * @param filename
	 *            文件名
	 * @param inputStream
	 *            文件输出流
	 * @return SD卡中下载完成后存储的文件
	 * @throws FileNotFoundException
	 *             抛出流异常
	 */
	public File writeSDStream(String dirname, String filename,
			InputStream inputStream) throws FileNotFoundException {
		// 创建文件
		File file = null;
		// 创建目录下载文件
		if (createSDDir(dirname)) {
			file = creatFile(dirname, filename);
			OutputStream outputStream = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int count = 0;
			try {
				while ((count = inputStream.read(buffer)) != -1) {

					outputStream.write(buffer, 0, count);

				}
				outputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return file;
	}
}
