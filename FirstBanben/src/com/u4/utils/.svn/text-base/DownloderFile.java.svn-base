package com.u4.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloderFile {
	private String url;
	private String dirname;
	private String filename;
	FileUtils fileUtils=new FileUtils();
	public DownloderFile(String url, String dirname, String filename) {
		super();
		this.url = url;
		this.dirname = dirname;
		this.filename = filename;
	}

	public int downloder(){
		try {
			URL myurl=new URL(url);
			URLConnection connection=myurl.openConnection();
			InputStream inputStream= connection.getInputStream();
			if(fileUtils.WenjianExists(dirname, filename)){
				//文件存在
				return -1;
			}
			else{
				fileUtils.writeSDStream(dirname, filename, inputStream);
				//下载文件
				return 1;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
