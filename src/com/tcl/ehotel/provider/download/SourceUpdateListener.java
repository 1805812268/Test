package com.tcl.ehotel.provider.download;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Handler;

public class SourceUpdateListener {
	private MyContentObserver mContentObserver;
	private Context mContext;
	private ContentResolver mResolver;
	private String packageName;
	private Handler mHandler ;
	private int msg_change;
	/**
	 * 
	 * @param ctx
	 * @param handler
	 * @param msg_change
	 */
	public SourceUpdateListener(Context ctx,Handler handler,int msg){
		mContext =ctx;
		mHandler = handler;
		this.msg_change = msg;
		packageName = mContext.getPackageName();
		mResolver=mContext.getContentResolver() ;
		packageName = mContext.getPackageName();
		mContentObserver = new MyContentObserver(mContext,mHandler,msg_change,packageName);
	}
	/**
	 * 
	 * @param ctx
	 * @param handler
	 * @param msg_change
	 * @param pacName  指定为监听参数，不指定则为默认为应用包名
	 */
	public SourceUpdateListener(Context ctx,Handler handler,int msg,String pacName){
		mContext =ctx;
		mHandler = handler;
		this.msg_change = msg;
		packageName = mContext.getPackageName();
		mResolver=mContext.getContentResolver() ;
		packageName = pacName;
		mContentObserver = new MyContentObserver(mContext,mHandler,msg_change,packageName);
	}
	
	
	public void register(){
		mResolver.registerContentObserver(MyDownloadDataHelper.URI_SOURCE, true,mContentObserver);
		mContentObserver.initStatus();
	}
	
	public void unRegister(){
		mResolver.unregisterContentObserver(mContentObserver);
	}
	
	
}
