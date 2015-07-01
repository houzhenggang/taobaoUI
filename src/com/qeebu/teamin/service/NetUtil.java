package com.qeebu.teamin.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {

	/**
	 * 获取服务器频道信息返回回来的输入流
	 * @param address 服务器地址 
	 * @return inputstream
	 */
	public static InputStream getChannelStream (String address) throws Exception{
		URL url = new URL(address);
		HttpURLConnection conn =  (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		int code = conn.getResponseCode() ;
		if(code == 200){
		   return conn.getInputStream();
		}
		
		return null;
	}
	
}
