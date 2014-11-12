package com.tcl.ehotel.provider.download;

import android.net.Uri;

public class MyDownloadDataHelper {
	public static final String SCHEME ="content://";
	
	public static final String AUTHORITY_DOWMLOAD ="com.tcl.ehotel.provider.download";
	
	static final String DB_NAME ="download.db";
	static final int DB_VERSION =1;
	
	public static final Uri URI_DOWNLOADLOG =Uri.parse(MyDownloadDataHelper.SCHEME+MyDownloadDataHelper.AUTHORITY_DOWMLOAD+"/"+MyDownloadDataHelper.TABLE_DOWNLOADLOG);
	public static final Uri URI_SOURCE =Uri.parse(MyDownloadDataHelper.SCHEME+MyDownloadDataHelper.AUTHORITY_DOWMLOAD+"/"+MyDownloadDataHelper.TABLE_SOURCE);
	
	
	public static final String TABLE_SOURCE="source";
	
	
	public static final String ITEM_NAME="name";
	public static final String ITEM_VALUE="value";
	
	public static final String PROPERTY_GUEST="guest";
	public static final String PROPERTY_MENU="tcl.ehotel.mainmenu";
	public static final String PROPERTY_SOURCE="source";
	public static final String PROPERTY_DEFAULT="defaults";

	protected static final String CREATE_TABLE_SOURCE="create table "+TABLE_SOURCE+"("+ITEM_NAME+" [text],"+ITEM_VALUE+" [text]);";
	
	protected static final String DROP_TABLE_SOURCE="drop table  if exists "+TABLE_SOURCE+";";
	
	//download log
	
	public static final String TABLE_DOWNLOADLOG="downloadlog";
	public static final String ITEM_ID ="id";
	public static final String ITEM_MD5="md5";
	public static final String ITEM_DOWNLOADPATH="downloadpath";
	public static final String ITEM_DOWNLOADLENGTH="downloadlength";
	//public static final String ITEM_DOWNLOADPART="downloadpart";
	public static final String ITEM_THREADID="threadid";
	
	protected static final String CREATE_TABLE_DOWNLOADLOG="create table "+TABLE_DOWNLOADLOG+" ("+ITEM_ID+" integer primary key autoincrement ,"+ITEM_MD5+" [text]  ,"+ITEM_DOWNLOADPATH+" [text],"+ITEM_DOWNLOADLENGTH+" INTEGER,"/*"+ITEM_DOWNLOADPART+" INTEGER ,*/+ITEM_THREADID+" INTEGER);";
	protected static final String DROP_TABLE_DOWNLOADLOG="drop table if exists "+TABLE_DOWNLOADLOG+";";
	
	
	// id of ContentProvider
	protected static final int ID_SOURCE =2;
	protected static final int ID_DOWNLOADLOG= 3;
	
}
