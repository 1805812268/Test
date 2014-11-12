package com.tcl.ehotel.net;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.tcl.ehotel.log.MyLog;
import com.tcl.model.system.SystemPropertyHelper;


public class InfomationGetter {
//	public String ServiceProject ="ehotel";
//	public String ConfigUrl = "http://%s:%s/%s/config.xml";
//	public String httpHeader = "http://%s:%s/bhotel/index.php?r=interface&xmlString=";
//	public String httpParams ="<request type=\"getbill\"><device roomnumber=\"77774\" lang=\"zh\"/></request>";
	
	
	/**
	 * get url of  config info.
	 * @param urlStr
	 * @param ip
	 * @param httpPort
	 * @return
	 */
	public URL getConfigURL(String urlStr ,String ip,String httpPort,String projectName){
		if(ip==null||httpPort==null){
			return null;
		}
		String nUrlStr=String.format(urlStr, ip,httpPort,projectName);
		URL url = null;
		try {
			url = new URL(nUrlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			MyLog.e("wrong url:"+nUrlStr);
		}
		return url;
	}
	public URL getVodBalanceURL(){
		String urlStr= "http://"+SystemPropertyHelper.getBalanceIp()+":"+SystemPropertyHelper.getBalancePort()
				+"/"+SystemPropertyHelper.getVodServerType()+"/";
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			MyLog.e("wrong url:"+urlStr);
		}
		return url;
	}
	public URL getSourceBalanceURL(){
		String urlStr= "http://"+SystemPropertyHelper.getBalanceIp()+":"+SystemPropertyHelper.getBalancePort()
				+"/"+SystemPropertyHelper.getSourceServerType()+"/";
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			MyLog.e("wrong url:"+urlStr);
		}
		return url;
	}
	
	/**
	 * get url
	 * @param httpHeaderFmt
	 * @param requestParams
	 * @param ip
	 * @param httpPort
	 * @return
	 */
	@Deprecated
	public URL getRequestURL(String httpHeaderFmt ,String requestParams,String ip,String httpPort){
		StringBuilder builder = new StringBuilder();
		builder.append(String.format(httpHeaderFmt, ip,httpPort));
		try{
			String params=URLEncoder.encode(requestParams, "UTF-8");
			builder.append(params);
			MyLog.d(builder.toString());
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		URL url = null;
		try {
			url = new URL(builder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			MyLog.e("wrong url:"+builder.toString());
		}
		return url;
	}
	/**
	 * get url
	 * @param requestParams
	 * @return
	 */
	public URL getRequestURL(String requestParams){
		String urlStr =SystemPropertyHelper.getRequestPath();
		if(urlStr==null||urlStr.trim().length()==0){
			return null;
		}
		try{
			String params=URLEncoder.encode(requestParams, "UTF-8");
			urlStr+=params;
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}
		URL url = null;
		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			MyLog.e("warn","wrong url:"+urlStr);
		}
		return url;
	}
	
	public  String getResponse(URL url){
		if(url==null) {
			return null;
		}
		try {
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setConnectTimeout(5*1000);
            conn.setDoInput(true);
            conn.connect();
            if(conn.getResponseCode()==HttpURLConnection.HTTP_OK){
            	InputStream is=conn.getInputStream();
            	String response = readData(is,"UTF-8");
            	return response;
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String readData(InputStream inSream, String charsetName) throws IOException{
	    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int len = -1;
	    while( (len = inSream.read(buffer)) != -1 ){
	        outStream.write(buffer, 0, len);
	    }
	    byte[] data = outStream.toByteArray();
	    outStream.close();
	    inSream.close();
	    return new String(data, charsetName);
	}
	
//	public static void main(String args[]){
//		InfomationGetter getter = new InfomationGetter();
//		String value1="<request type=\"getsanya\"><device language=\"zh\"/></request>";
//		String value =getter.getResponse(getter.getConfigURL(getter.ConfigUrl,"192.168.11.142","80",getter.ServiceProject));
//		System.out.println("-----start-----");
//		System.out.println(value);
//		System.out.println("-----end-----");
//	}
}
