package com.tcl.ehotel.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyWindowManagerHelper {
	private Context mContext= null; 
	private WindowManager mWindowManager = null;
	private boolean isShow = false ;
	private TextView txt = null ;
	RelativeLayout relativeLayout = null;
	WindowManager.LayoutParams wmParams = null;
	ArrayList<String> tips = new ArrayList<String>();
	private int maxLine = 30;
	public MyWindowManagerHelper(Context ctx){
		this.mContext = ctx;
		init(Gravity.CENTER);
	}
	public MyWindowManagerHelper(Context ctx,int gravity){
		this.mContext = ctx;
		init(gravity);
	}
	private void init(int gravity){
		mWindowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		wmParams = new WindowManager.LayoutParams();
		wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
				| WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY; // type是关键，这里的2002表示系统级窗口，你也可以试试2003。
		wmParams.format = 1;
		wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		//wmParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
		wmParams.height = android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
		wmParams.width = 800;
		wmParams.gravity = gravity;
		txt = new TextView(mContext);
		txt.setTextColor(Color.WHITE);
		txt.setBackgroundColor(Color.argb(100, 66, 66, 66));
		isShow = false ;
	}
	public void addMSG(String log){
		if(tips.size()<maxLine){
			tips.add(log);
		}else{
			tips.remove(0);
			tips.add(log);
		}
		updateMSG();
		txt.postInvalidate();
	}
	public void setMaxLine(int max){
		maxLine = max;
	}
	
	private void updateMSG(){
		int len = tips.size();
		for(int i =0;i<len;i++){
			if(i==0){
				txt.setText(tips.get(i));
			}else{
				txt.append("\r\n");
				txt.append(tips.get(i));
			}
		}
	}
	
	public  void dismiss(){
		if(isShow){
			mWindowManager.removeView(txt);
			isShow = false ;
		}
	}
	public  void show(){
		if(!isShow){
			mWindowManager.addView(txt, wmParams);
			isShow = true;
		}
	}
}
