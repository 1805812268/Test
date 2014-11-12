package com.tcl.ehotel.data;


import java.util.Locale;

import android.content.Context;
import com.tcl.ehotel.log.MyLog;
import com.tcl.model.system.SystemPropertyHelper;

public class Defaults {
	
	public static final Locale  language0= Locale.SIMPLIFIED_CHINESE;
	public static final Locale  language1= Locale.US;
	//ACTION
	public static final String MORNINGCALL_CANCEL ="tcl.ehotel.morningcall.time.cancelMorningcall";
	public static final String CATERING_CANCEL ="tcl.ehotel.catering.cancelcatering";
	public static final String 	ACTION_SERVICE_CONNECT ="tcl.ehotel.service.connect.ok";
	public static final String 	ACTION_BOOT_OK ="tcl.ehotel.boot.ok";
	public static final String 	ACTION_SERVICE ="tcl.cifs.service";
	public static final String SERVERNAME ="ehotel_resource";
	private static  DOWNLOADTYPE downloadType = DOWNLOADTYPE.HTTP;
	public enum DOWNLOADTYPE{
		SAMBA,
		HTTP,
		//FTP
		;
	}
	
	
	
	
	//DOWNLOAD TYP :HTTP 
	//private static  String PATH_ROOT_HTTP =null;
	private static  String PATH_SOURCELIST_HTTP =null;
	private static  String PATH_SOURCELIST_MD5_HTTP =null;
	
	private static  String PATH_GUESTINFO_HTTP=null;
	private static  String PATH_MARQUEE_HTTP=null;
	private static  String PATH_LEAVEMSG_HTTP=null;
	
	//DOWNLOAD TYP :SAMBA 
	public static  String PATH_ROOT_SMABA ="/mnt/samba";
	private static  String PATH_SOURCELIST_SMABA ="/source_list.xml";
	private static  String PATH_SOURCELIST_MD5_SMABA ="/source_list.xml.md5";
	
	private static  String PATH_GUESTINFO_SAMBA=null;
	private static  String PATH_MARQUEE_SAMBA=null;
	private static  String PATH_LEAVEMSG_SAMBA=null;
	
	
	static{
		reLoadPath();
	}
	
	//local path and  tmpPath
	
//	private  static String PATH_LOCAL =SystemPropertyHelper.getLocalPath();
//	private  static String PATH_TEMP =PATH_LOCAL+"_temp";
//	private  static final String PATH_TEMP ="/data/hotel_source_temp";
//	private  static String PATH_LOCAL ="/data/hotel_source";
	
	
	
	public static final String PATH_SOURCELIST="/source_list.xml";
	public static final String PATH_SOURCELISTMD5 ="/source_list.xml.md5";
	
	public static final String PATH_GUESTINFO="/room/info.xml";
	public static final String PATH_MARQUEE ="/room/marquee.xml";
	public static final String PATH_LEAVEMSG="/room/msg.xml";
	
	public static final String PATH_WEATHER = "/xml/localweather.xml";
	
	public static final String PATH_WELCOME="/xml/welcome.xml";
	public static final String PATH_MAINMENU_ZH="/xml/zh/mainmenu.xml";
	public static final String PATH_MAINMENU_EN="/xml/en/mainmenu.xml";
	
	public static final String PATH_STANDBY = "/xml/standby.xml";
	
	//public static final String PATH_TVBIN_9LIAN="/data/data/umdb.dat";
	
	
	public static final String PATH_DTVCHANNEL = "/update/Database/dtvchannellist.xml";
	
	public static final String PATH_SHORTCUT_ZH ="/xml/zh/quickmenu.xml";
	public static final String PATH_SHORTCUT_EN ="/xml/en/quickmenu.xml";
	
	
	public static final String NAME_TV_9LIAN ="umdb.dat";
	public static final String PATH_TV_9LIAN ="/data/data/umdb.dat";
	
	public static final String NAME_HDMI__9LIAN ="hdmi.xml";
	
	
	//MSG
	/**
	 * 资源更新
	 */
	public static final int MSG_UPDATE = 10;
	/**
	 * 更新用户信息
	 */
	public static final int MSG_UPDATE_GUESTINFO = 11;
	
	
	
	public static final int MSG_UPDATE_LEAVEMESSAGE = 12;
	public static final int MSG_UPDATE_MARQUEE = 13;
	
	public static final int MSG_UPDATE_SOURCELIST =15;
	
	//DELAY TIME
	public static final int TIME_DOWNLOAD_DELAY_SHORT = 2;
	public static final int TIME_DOWNLOAD_DELAY_MEDIUM = 10;
	public static final int TIME_DOWNLOAD_DELAY_LONG = 30;	
	
	public static final int PRIORITY_DEFAULT=7;
	public static final int PRIORITY_MEDIUM=5;
	
	//=========================================
	/**
	 * 重新生成数据，以便适应ip、房间号等信息的修改
	 */
	public static void reLoadPath(){
		//PATH_ROOT_HTTP = SystemPropertyHelper.getSourceHead();//"http://"+SystemPropertyHelper.getServerIP()+":"+SystemPropertyHelper.getHttpPort();
		PATH_SOURCELIST_HTTP =SystemPropertyHelper.getSourcePath();//"/"+SERVERNAME+"/source_list.xml";
		PATH_SOURCELIST_MD5_HTTP =SystemPropertyHelper.getSourceMD5();
		
		PATH_GUESTINFO_HTTP="/"+SERVERNAME+"/xml/room/"+SystemPropertyHelper.getRoomNumber()+"/info.xml";
		PATH_MARQUEE_HTTP="/"+SERVERNAME+"/xml/room/"+SystemPropertyHelper.getRoomNumber()+"/marquee.xml";
		PATH_LEAVEMSG_HTTP="/"+SERVERNAME+"/xml/room/"+SystemPropertyHelper.getRoomNumber()+"/msg.xml";
		
		PATH_GUESTINFO_SAMBA="/xml/room/"+SystemPropertyHelper.getRoomNumber()+"/info.xml";
		PATH_MARQUEE_SAMBA="/xml/room/"+SystemPropertyHelper.getRoomNumber()+"/marquee.xml";
		PATH_LEAVEMSG_SAMBA="/xml/room/"+SystemPropertyHelper.getRoomNumber()+"/msg.xml";
		
//		PATH_LOCAL =SystemPropertyHelper.getLocalPath();
//		PATH_TEMP =PATH_LOCAL+"_temp";
		
	}
	
	/**
	 * 获取samba 路径，只在下载方式兼容samba的时候有效
	 * @return
	 */
	public static final String getPath_SMB(){
		return PATH_ROOT_SMABA;
	}
	public static final void setPath_SMB(String path){
		PATH_ROOT_SMABA = path;
	}
	
	
	public static final String getPath_tmp(){
		String path = getPath_Local();
		return path+"_temp";
	}
	public static final String getPath_Local(){
		return SystemPropertyHelper.getLocalPath();
	}
//	public static final String getPath_Server(){
//		String path =null;
//		DOWNLOADTYPE type =getDownloadType();
//		switch(type){
//		case HTTP:
//			path = PATH_ROOT_HTTP;
//			break;
//		case SAMBA:
//			path = PATH_ROOT_SMABA;
//			break;
//		default:
//			break;
//		}
//		return path;
//	}
	/**
	 * 获取下载方式
	 * @return
	 */
	public static DOWNLOADTYPE getDownloadType(){
		return downloadType;
	}
	/**
	 * 设置下载方式
	 * @param tp 
	 */
	public static void initDownloadType(Context ctx){
		initDownloadTypeBySharedPreferences(ctx);
	}
	public static void initDownloadTypeBySystemProperty(){
		String typeStr=SystemPropertyHelper.getDownloadType();
		int type = 0 ;
		try{
			type= Integer.parseInt(typeStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		switch(type){
			case 0 :
				downloadType = DOWNLOADTYPE.HTTP;
				break;
			case 1 :
				downloadType = DOWNLOADTYPE.SAMBA;
				break;
			default :
				downloadType = DOWNLOADTYPE.HTTP;
				break;
		}
	}
	public static void initDownloadTypeBySharedPreferences(Context ctx){
		String typeStr = "0";
		try{
		Context otherAppsContext = ctx.createPackageContext("com.tcl.tclhotelsettings", Context.CONTEXT_IGNORE_SECURITY);
		typeStr= otherAppsContext.getSharedPreferences("curwifi", Context.MODE_WORLD_READABLE).getString("downtype","0");
		}catch(Exception e){
			e.printStackTrace();
		}
		MyLog.i("typeStr----------------->"+typeStr);
		int type = 0 ;
		try{
			type= Integer.parseInt(typeStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		switch(type){
			case 0 :
				downloadType = DOWNLOADTYPE.HTTP;
				break;
			case 1 :
				downloadType = DOWNLOADTYPE.SAMBA;
				break;
			default :
				downloadType = DOWNLOADTYPE.HTTP;
				break;
		}
	}
	
	
	
	
	
	public static final String getPath_Server_SourceList(){
		String path=null;
		DOWNLOADTYPE type =getDownloadType();
		switch(type){
		case HTTP:
			path= PATH_SOURCELIST_HTTP;
			break;
		case SAMBA:
			path = PATH_SOURCELIST_SMABA;
			break;
		}
		return path;
	}
	public static final String getPath_Server_SourceListMD5(){
		String path=null;
		DOWNLOADTYPE type =getDownloadType();
		switch(type){
		case HTTP:
			path= PATH_SOURCELIST_MD5_HTTP;
			break;
		case SAMBA:
			path = PATH_SOURCELIST_MD5_SMABA;
			break;
		}
		return path;
	}
	
	public static final String getPath_Server_GuestInfo(){
		String path=null;
		DOWNLOADTYPE type =getDownloadType();
		switch(type){
		case HTTP:
			path= PATH_GUESTINFO_HTTP;
			break;
		case SAMBA:
			path = PATH_GUESTINFO_SAMBA;
			break;
		}
		return path;
	}
	public static final String getPath_Server_Marquee(){
		String path = null;
		DOWNLOADTYPE type =getDownloadType();
		switch(type){
		case HTTP:
			path =PATH_MARQUEE_HTTP;
			break;
		case SAMBA:
			path =PATH_MARQUEE_SAMBA;
			break;
		default:
			break;
		}
		return path;
	}
	
	public static final String getPath_Server_LeaveMsg(){
		String path =null;
		DOWNLOADTYPE type =getDownloadType();
		switch(type){
		case HTTP:
			path =PATH_LEAVEMSG_HTTP;
			break;
		case SAMBA:
			path=PATH_LEAVEMSG_SAMBA;
			break;
		default:
			break;
		}
		return path ;
	}
	
	public static final String TAG_MUSIC ="music";
	public static final String TAG_IMAGE ="image";
	public static final boolean DEFALT_NUSIC =false;
	
}
