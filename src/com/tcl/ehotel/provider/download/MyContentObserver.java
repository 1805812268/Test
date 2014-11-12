package com.tcl.ehotel.provider.download;

import com.tcl.ehotel.log.MyLog;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;

public class MyContentObserver extends ContentObserver{
	private Handler handler ;
	private int msg ;
	private Context mContext;
	private ContentResolver mResolver;
	private boolean initStatus = false ;
	private String packageName ; 
	//public MyContentObserver(Handler handler) {
	//	super(handler);
	//	this.handler =handler; 
	//}
	public MyContentObserver(Context  ctx,Handler handler,int msg_change,String pacName) {
		super(handler);
		mContext = ctx;
		this.handler =handler;
		msg =msg_change;
		mResolver=mContext.getContentResolver();
		packageName = pacName;
		initStatus();
	}
	public void initStatus(){
		initStatus =query();
	}
	@Override
	public void onChange(boolean selfChange) {
		boolean newStatus =query();
		if(initStatus!=newStatus && newStatus){
			handler.sendEmptyMessage(msg);
			MyLog.d("packageName ------------update");
		}
		initStatus = newStatus;
		super.onChange(selfChange);
	}
	
	public boolean query(){
		Cursor cursor =null;
		try{
			cursor=mResolver.query(MyDownloadDataHelper.URI_SOURCE, null, MyDownloadDataHelper.ITEM_NAME+"=?", new String[]{packageName}, null);
			if(cursor == null || cursor.getCount()==0){
				return false ;
			}else{
				cursor.moveToFirst();
				int index = cursor.getColumnIndex(MyDownloadDataHelper.ITEM_VALUE);
				String statusStr = cursor.getString(index);
				boolean  status = Integer.parseInt(statusStr)==1;
				return status ;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false ;
		}finally{
			if(cursor != null){
				cursor.close();
			}
		}
	}
	
}
