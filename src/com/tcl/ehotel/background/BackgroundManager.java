package com.tcl.ehotel.background;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import com.tcl.ehotel.data.Defaults;
import com.tcl.ehotel.data.WeatherData;
import com.tcl.ehotel.log.MyLog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
/**
 * 
 * @author love
 * manager background wallPaper
 *
 */
public class BackgroundManager {
	public Context mContext = null;
	public BackgroundManager(Context ctx){
		mContext = ctx;
	}
	/**
	 * 
	 * change background IMG
	 * 
	 * @param path : single path
	 * @param isClose :is showBackgourd
	 * @return
	 */
	public boolean changeBackgroundImg(String path,boolean isClose){
		if(isClose == false ){
			if(path== null||path.trim().length()==0){
				return false ;
			}
			File file = new File(path);
			if(!file.exists()||file.isDirectory()){
				return false;
			}
		}
		ArrayList<String>paths = new ArrayList<String>();
		paths.add(path);
		return changeBackgroundImg(paths,AnimationType.UNKONW,false);
	}
	/**
	 * change background IMG
	 * @param paths :several path
	 * @param isClose :is showBackgourd
	 * @return
	 */
	public boolean changeBackgroundImg(ArrayList<String> paths,boolean isClose){
		return changeBackgroundImg(paths,AnimationType.UNKONW,isClose);
	}
	
	
	/**
	 * change background IMG
	 * @param paths :several path
	 * @param type :animation type 
	 * @param isClose :is show
	 * @return
	 */
	public boolean changeBackgroundImg(ArrayList<String> paths,AnimationType type,boolean isClose){
		if(isClose ==false ){
			if(paths==null||paths.size()==0){
				return false ;
			}
		}
		int animationId = type.getValue();
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_CHANGE_PHOTO_PATH);
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("path",paths );
		bundle.putInt("animationid", animationId);
		bundle.putBoolean("close", isClose);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
		MyLog.d("background", "background IMG :start");
		for(String path:paths){
			MyLog.d("background", "background IMG path :"+path);
		}
		MyLog.d("background", "background IMG :end");
		return true;
	}
	
	public boolean changeBackgroundMusic(ArrayList<String> paths){
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_CHANGE_MUSIC_PATH);
		Bundle bundle = new Bundle();
		bundle.putStringArrayList("path",paths );
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
		MyLog.d("background", "background music :start");
		for(String path:paths){
			MyLog.d("background", "background music path :"+path);
		}
		MyLog.d("background", "background music :end");
		return true;
	}
	
	/**
	 * change background tips
	 * @param type :tips type
	 * @param isClose :is show
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean changeBackgroundTip(TipsType type,boolean isClose){
		int tipsId = type.getValue();
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_CHANGE_TIPS);
		Bundle bundle = new Bundle();
		bundle.putInt("tip",tipsId );
		bundle.putBoolean("close", isClose);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
		return false;
	}
	/**
	 * change logo
	 * @param path
	 * @param isClose :is show
	 * @return
	 */
	public boolean changeBackgroundLogo(String path,boolean isClose){
		if(isClose ==false){
			if(path == null|| path.trim().length()==0){
				return false ;
			}
			File file = new File(path);
			if(!file.exists()||!file.isFile()){
				return false;
			}
		}
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_CHANGE_LOGO);
		Bundle bundle = new Bundle();
		bundle.putString("path",path );
		bundle.putBoolean("close", isClose);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
		MyLog.d("background", "background logo :"+path);
		return false;
	}
	/**
	 * change weather info.
	 * @param data
	 * @param isClose :is show
	 * @return
	 */
	public boolean changeBackgroundWeather(WeatherData data,boolean isClose){
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_CHANGE_WEATHER);
		Bundle bundle = new Bundle();
		bundle.putParcelable("weather", data);
		bundle.putBoolean("close", isClose);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
		MyLog.d("background", "background weather :"+data.toString());
		return false;
	}
	/**
	 * turn on music
	 * @return
	 */
	public boolean turnOnMusic(){
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_MUSIC_ON);
		mContext.sendBroadcast(intent);
		MyLog.d("background", "background music  on");
		return false;
	}
	/**
	 * turn off music
	 * @param path
	 * @return
	 */
	public boolean turnOffMusic(){
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_MUSIC_OFF);
		mContext.sendBroadcast(intent);
		MyLog.d("background", "background  music off ");
		return false;
	}
	public boolean changeMusicStatus(boolean isMusicPlay){
		if(isMusicPlay){
			return turnOnMusic();
		}else{
			return turnOffMusic();
		}
	}
	
	public boolean changeLanguage(Locale local){
		Intent intent = new Intent();
		intent.setAction(BackgroundDefaults.ACTION_CHANGE_LANGUAGE);
		Bundle bundle = new Bundle();
		int type =0;
		if(local.equals(Defaults.language1)){
			type =1;
		}
		bundle.putInt("type", type);
		intent.putExtras(bundle);
		mContext.sendBroadcast(intent);
		MyLog.d("background", "background  language change ,"+local.getDisplayName()+"type:"+type);
		return false;
	}
	
	@SuppressWarnings("unused")
	private class BackgroundData{
		public ArrayList<String> paths;
		public int animationId ;
	}
}
