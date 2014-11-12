package com.tcl.model.system;

import java.util.Locale;

import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.app.backup.BackupManager;
import android.content.res.Configuration;
import android.os.RemoteException;

public class MyActivityManager {
	public static Configuration getConfiguration(){
		Configuration config =null;
		IActivityManager am = ActivityManagerNative.getDefault();
		try {
			config = am.getConfiguration();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return config;
	}
	public static boolean updateConfiguration(Configuration config){
		IActivityManager am = ActivityManagerNative.getDefault();
		try {
			 // indicate this isn't some passing default - the user wants this remembered  
	         config.userSetLocale = true;
			am.updateConfiguration(config);
			return true ;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false ;
	}
	public static boolean updateLocale(Locale  locale){
		try {    
	        IActivityManager am = ActivityManagerNative.getDefault();    
	        Configuration config = am.getConfiguration();    
	        config.locale = locale;    
	        // indicate this isn't some passing default - the user wants this remembered    
	        config.userSetLocale = true;    
	        am.updateConfiguration(config);    
	        // Trigger the dirty bit for the Settings Provider.    
	        BackupManager.dataChanged("com.android.providers.settings");   
	        return true;
	    } catch (RemoteException e) {    
	        // Intentionally left blank
	    	return false;
	    }   
	}
}
