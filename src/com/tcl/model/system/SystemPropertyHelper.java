package com.tcl.model.system;

import android.os.SystemProperties;

public class SystemPropertyHelper {
	public static final String ROOM="persist.sys.hwHomeNum";
	public static final String SERVERIP ="persist.sys.hwServiceIp";
	public static final String SERVERPORT="persist.sys.hwServicePort";
	public static final String DOWNLOADTYPE ="persist.sys.hwDownloadType";
	
	
	public static final String HTTPIP ="persist.sys.hwHttpIp";
	public static final String HTTPPORT ="persist.sys.hwHttpPort";
	
	public static final String Project ="persist.sys.hwServerProject";
	
	public static final String PROJECTNAME = "persist.sys.projectName";
	
	public static final String MAXVOLUME ="persist.sys.maxVolume";
	
	public static final String  LIMIT ="persist.sys.limit";
	public static final String  STARTTIME ="persist.sys.start";
    public static final String  ENDTIME ="persist.sys.end";
    
    public static final String INPUT_CONTROL = "persist.sys.hwinputcontrol";
    
    public static final String SYS_CLOCK="persist.sys.alarmvolume";
	
    public static final String SYSTEMVERSION ="ro.build.version.incremental";
    
    public static final String STANDBY ="persist.sys.vendor.fakestandby";
    public static final String STANDBY_9LIAN ="sys.standby.state";
    public static final String CATER_OREDER ="persist.sys.caterorder";
    
    
    
    public static final String CONFIG="persist.sys.config";
    public static final String SERVER_PROJECT="persist.sys.serverproject";
    public static final String MESSAGE_IP ="persist.sys.hwmessageip";
    public static final String MESSAGE_PORT ="persist.sys.hwmessageport";
    public static final String UPLOAD_PATH ="persist.sys.hwupload";
    public static final String REQUEST_PATH ="persist.sys.hwinterfacerequest";
    
    public static final String BALANCEIP="persist.sys.hwbalanceip";
    public static final String BALANCEPORT="persist.sys.hwbalanceport";
    
    public static final String SOURCE_PATH="persist.sys.hwsourcepath";
    public static final String SOURCE_MD5="persist.sys.hwsourcemd5";
    
    public static final String LOCALPATH="persist.sys.hwlocalpath";
    
    
	public static final String DEFAULTIP ="124.205.183.62";//124.205.183.60
	public static final String DEFAULTPORT ="1204";
	public static final String DEFAULTYPE ="1";
	public static final int DEFAULTMAXVOLUME =100;
	
	public static final String DEFAULTHTTPPORT ="80";
	public static final String DEFAULTPROJECT ="ehotel";
	public static final String DEFAULTSTANDBY ="0";
	public static final String DEFAULTSTANDBY_9LIAN ="0";
	
	public static final String 	AUTO="persist.sys.AUTO_ENTER_TV";
	public static final String 	AUTO_TIME="persist.sys.TIMEOUT_ENTER_TV";
	
	public static final int DEFAULT_AUTO_TIME =15;
	public static final String DEFAULT_CONFIG="http://%s:%s/%s/config.xml";
	public static final String DEFAULT_SERVER_PROJECT="ehotel";
	 
	 
	 public static final String DEFAULT_LOCALPATH=/*"/data/hotel_source";*/"/mnt/sdcard/hotel_source";
	 
	public static String getRoomNumber(){
		return getPropertyString(ROOM,"");
	}
	public static String getRoomNumber(String def){
		return getPropertyString(ROOM,def);
	}
	public static String getServerIP(){
		return getPropertyString(SERVERIP, DEFAULTIP);
	}
	public static String getServerIP(String def){
		return getPropertyString(SERVERIP, def);
	}
	
	public static void setServerIP(String def){
		setPropertyString(SERVERIP, def);
	}
	
	public static String getServerPort(){
		return getPropertyString(SERVERPORT, DEFAULTPORT);
	}
	public static String getServerPort(String def){
		return getPropertyString(SERVERPORT, def);
	}
	
	private static String getPropertyString(String key,String def){
		String value =SystemProperties.get(key, def);
		return value;
	}
	private static void setPropertyString(String key ,String def){
		SystemProperties.set(key, def);
	}
	private static int getPropertyInt(String key,int def){
		int value =SystemProperties.getInt(key, def);
		return value;
	}
	
	private static long getPropertyLong(String key,long def){
		long value =SystemProperties.getLong(key, def);
		return value;
	}
	private static boolean getPropertyBool(String key,boolean def){
		boolean value =SystemProperties.getBoolean(key, def);
		return value;
	}
	
	private static void setPropertyInt(String key,String value){
		SystemProperties.set(key, value);
	}
	
	public static String getDownloadType(){
		return getPropertyString(DOWNLOADTYPE,DEFAULTYPE);
	}
	public static int getAutoTVTime(int def){
		return getPropertyInt(AUTO_TIME,def);
	}
	public static int getAutoTVTime(){
		return getPropertyInt(AUTO_TIME,DEFAULT_AUTO_TIME);
	}
	public static int getAutoTVStatus(){
		return getPropertyInt(AUTO,0);
	}
	public static int getAutoTVStatus(boolean bool){
		return getPropertyInt(AUTO,bool?1:0);
	}
	
	public static int getMaxVolume(int def){
		return getPropertyInt(MAXVOLUME, def);
	}
	
	public static int getMaxVolume(){
		return getPropertyInt(MAXVOLUME, DEFAULTMAXVOLUME);
	}
	
	public static void setMaxVolume(int value){
		setPropertyInt(MAXVOLUME, String.valueOf(value));
	}
	
	
	
	public static int getLIMIT(int def){
		return getPropertyInt(LIMIT, def);
	}
	public static void setLIMIT(int value){
		setPropertyInt(LIMIT, String.valueOf(value));
	}
	public static void setLIMIT(String value){
		setPropertyInt(LIMIT, value);
	}
	
	
	public static String getStartTime(String def){
		return getPropertyString(STARTTIME, def);
	}
	public static void setStartTime(String value){
		setPropertyString(STARTTIME, value);
	}
	
	public static String getEndTime(String def){
		return getPropertyString(ENDTIME, def);
	}
	public static void setEndTime(String value){
		setPropertyString(ENDTIME, value);
	}
	
	public static void setSYSClock(String value){
		setPropertyString(SYS_CLOCK, value);
	}
	public static String getHttpPort(String defaults){
		return getPropertyString(HTTPPORT, defaults);
	}
	public static String getHttpPort(){
		return getPropertyString(HTTPPORT, DEFAULTHTTPPORT);
	}
	public static void setHttpPort(String value){
		setPropertyString(HTTPPORT, value);
	}
	
	public static String getProjectName(){
		return getPropertyString(PROJECTNAME, DEFAULTPROJECT);
	}
	public static String getProjectName(String def){
		return getPropertyString(PROJECTNAME, def);
	}
	public static void setProjectName(String value){
		setPropertyString(PROJECTNAME, value);
	}
	public static String getSystemVersion(){
		return getPropertyString(SYSTEMVERSION, "");
	}
	public static String getSystemVersion(String def){
		return getPropertyString(SYSTEMVERSION, def);
	}
	public static String getStandby(){
		return getPropertyString(STANDBY, DEFAULTSTANDBY);
	}
	public static String getStandby_9LIAN(){
		return getPropertyString(STANDBY_9LIAN, DEFAULTSTANDBY_9LIAN);
	}
	public static String getStandby_9LIAN(int state){
		return getPropertyString(STANDBY_9LIAN, String.valueOf(state));
	}
	
	/**
	 * 
	 * @return  true :假待机
	 */
	public static boolean isStandby(){
		if(getStandby().trim().contains("1")){
			return true;
		}
		return false ;
	}
	
	public static String getConfigURL(){
		return getPropertyString(CONFIG, DEFAULT_CONFIG);
	}
	public static String getServerProject(){
		return getPropertyString(SERVER_PROJECT, DEFAULT_SERVER_PROJECT);
	}
	
	public static String getMessageIp(){
		return getPropertyString(MESSAGE_IP,"");
	}
	public static void setMessageIp(String ip){
		setPropertyString(MESSAGE_IP,ip);
	}
	
	public static String getMessagePort(){
		return getPropertyString(MESSAGE_PORT, "");
	}
	public static void   setMessageProt(String port){
		setPropertyInt(MESSAGE_PORT, port);
	}
	
	public static String getUploadPath(){
		return getPropertyString(UPLOAD_PATH, "");
	}
	public static void setUploadPath(String path){
		setPropertyString(UPLOAD_PATH, path);
	}
	
	public static String getRequestPath(){
		return getPropertyString(REQUEST_PATH, "");
	}
	public static void setRequestPath(String path){
		setPropertyString(REQUEST_PATH, path);
	}
	
	public static String getBalanceIp(){
		return getPropertyString(BALANCEIP, "");
	}
	public static void setBalanceIp(String path){
		setPropertyString(BALANCEIP, path);
	}
	
	public static String getBalancePort(){
		return getPropertyString(BALANCEPORT, "");
	}
	public static void setBalancePort(String path){
		setPropertyString(BALANCEPORT, path);
	}
	
	public static String getSourcePath(){
		return getPropertyString(SOURCE_PATH, "");
	}
	public static void setSourcePath(String path){
		setPropertyString(SOURCE_PATH, path);
	}
	public static String getSourceMD5(){
		return getPropertyString(SOURCE_MD5, "");
	}
	public static void setSourceMD5(String path){
		setPropertyString(SOURCE_MD5, path);
	}
	
	public static String getVodServerType(){
		return "vod";
	}
	public static String getSourceServerType(){
		return "resource";
	}
	public static String getLocalPath(){
		return getPropertyString(LOCALPATH, DEFAULT_LOCALPATH);
	}
	public static String getCaterOrder(){
		return getPropertyString(CATER_OREDER, "");
	}
	public static void setCaterOrder(String url){
		setPropertyString(CATER_OREDER, url);
	}
}
