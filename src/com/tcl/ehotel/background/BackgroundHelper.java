package com.tcl.ehotel.background;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;

import com.tcl.ehotel.data.Defaults;
import com.tcl.ehotel.log.MyLog;

public class BackgroundHelper {
	private Context mContext;
	BackgroundManager mBackgroundManager ;
	public BackgroundHelper(Context ctx){
		mContext = ctx;
		mBackgroundManager=new BackgroundManager(mContext);
	}
	private ArrayList<String> bgImages = new ArrayList<String>();
	private boolean bgMusic = false ;
	public boolean getBackgroundInfo(Bundle bundle){
		if(bundle==null||!bundle.containsKey(Defaults.TAG_IMAGE)){
			MyLog.e("htest", "exchange ----------> no image");
			return false ;
		};
		ArrayList<String> temp = bundle.getStringArrayList(Defaults.TAG_IMAGE);
		if(temp != null){
			bgImages.clear();
			bgImages.addAll(temp);
		}
		bgMusic = bundle.getBoolean(Defaults.TAG_MUSIC);
		saveBackgroundInfo(bgMusic,bgImages);
		return true;
	}
	public boolean getBackgroundInfo(){
		SharedPreferences sp = mContext.getSharedPreferences("background.ini", Context.MODE_PRIVATE);
		bgMusic =sp.getBoolean(Defaults.TAG_MUSIC,false);
		Set<String> set = sp.getStringSet(Defaults.TAG_IMAGE, null);
		if(set!=null){
			bgImages.clear();
			bgImages.addAll(set);
		}
		Log.e("htest", "music---->"+bgMusic);
		Log.e("htest", "image---->"+bgImages.size()+","+bgImages.toString());
		return false ;
	}
	
	public boolean saveBackgroundInfo(boolean music ,final ArrayList<String> imgs){
		SharedPreferences sp = mContext.getSharedPreferences("background.ini", Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		Set<String> set =new  HashSet<String>();
		set.addAll(imgs);
		editor.putStringSet(Defaults.TAG_IMAGE, set);
		editor.putBoolean(Defaults.TAG_MUSIC, music);
		editor.commit();
		return true;
	}
	public boolean changeBackgroundMusicStatus(){
		mBackgroundManager.changeMusicStatus(bgMusic);
		return true;
	}
	public boolean changeBackgroundImages(){
		mBackgroundManager.changeBackgroundImg(bgImages, false);
		return true;
	}
}
