package com.tcl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.util.Log;

public class MDFiveHelper {
   
	public static final boolean DEBUG = false;
	public static final String TAG = "MDFiveHelper";
	public static final String TEMP_FILE_NAME = "/data/hotel_source/MDFiveTemp.xml";
	
	static {	//MDFiveHelper.ge
		System.loadLibrary("MD5");
	}
	private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String toHexString(byte[] b) { // String to byte
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
			sb.append(HEX_DIGITS[b[i] & 0x0f]);
		}
		return sb.toString();
	}

	/*
	public static int getMD5(File file) throws FileNotFoundException {
		if (DEBUG) {
			Log.i(TAG,
					"<meifei>-----getMD5Num (File) path is " + file.getPath());
		}
		if(file.exists()){
			
			int byteCount = 0;
			byte[] digest;
			byte[] bytes = new byte[1024];
			FileInputStream input = new FileInputStream(file);
			
			MessageDigest digester = java.security.MessageDigest.getInstance("MD5");

			if (file.length() < 1024 * 1024) {
				while ((byteCount = input.read(bytes)) > 0) {
					digester.update(bytes, 0, byteCount);
				}
			} else {
				bytes = new byte[2048];
				for (int i = 0; i < 3; i++) {
					checkFile(filepathname, TEMP_FILE_NAME, i + 1);
					inputStream = new FileInputStream(TEMP_FILE_NAME);
					while ((byteCount = inputStream.read(bytes)) > 0) {
						digester.update(bytes, 0, byteCount);
					}
				}
			}
			return (toHexString(digester.digest()));
		}
		return 1;
	}

	*/
    //比较文件的MD5码
	public static boolean compareMD5(String filepathname, String md5)
			throws NoSuchAlgorithmException, IOException {

		

		boolean checkResult = false;
		FileInputStream inputStream = new FileInputStream(filepathname);
		byte[] digest;
		byte[] bytes = new byte[1024];
		int byteCount;
		File s1 = new File(filepathname);
		// readMD5Sum = md5;

		MessageDigest digester = java.security.MessageDigest.getInstance("MD5");
		if (s1.length() < 1024 * 1024) {
			while ((byteCount = inputStream.read(bytes)) > 0) {
				digester.update(bytes, 0, byteCount);
			}
		} else {
			bytes = new byte[2048];
			for (int i = 0; i < 3; i++) {
				checkFile(filepathname, TEMP_FILE_NAME, i + 1); // ��ȡ�ļ����ܶΣ�������tempfile
				inputStream = new FileInputStream(TEMP_FILE_NAME);
				while ((byteCount = inputStream.read(bytes)) > 0) {
					digester.update(bytes, 0, byteCount);
				}
			}
		}
		digest = digester.digest();
		String checkSum0 = toHexString(digest);
		// Log.v("*************** Calculate MD5 Check Sum is: ",checkSum0);
		if (DEBUG) {
			
			  Log.i(TAG, "<meifei>----compareMD5 path is " + filepathname
					+ ";md5 is " + md5 + "; new md5 is " + checkSum0);
			  
		}
		if (checkSum0.equals(md5.toUpperCase()))
			checkResult = true;
		else
			checkResult = false;
		return checkResult;
	}
    
	//获得文件的MD5码
	public static String getMD5(String filepathname)
			throws NoSuchAlgorithmException, IOException {

		if (DEBUG) {
			Log.i(TAG, "<meifei>----getMD5 path is " + filepathname + ";");
		}

		FileInputStream inputStream = new FileInputStream(filepathname);
		byte[] bytes = new byte[1024];
		int byteCount;
		File s1 = new File(filepathname);
		MessageDigest digester = java.security.MessageDigest.getInstance("MD5");

		if (s1.length() < 1024 * 1024) {
			while ((byteCount = inputStream.read(bytes)) > 0) {
				digester.update(bytes, 0, byteCount);
			}
		} else {
			bytes = new byte[2048];
			for (int i = 0; i < 3; i++) {
				checkFile(filepathname, TEMP_FILE_NAME, i + 1);
				inputStream = new FileInputStream(TEMP_FILE_NAME);
				while ((byteCount = inputStream.read(bytes)) > 0) {
					digester.update(bytes, 0, byteCount);
				}
			}
		}
		return (toHexString(digester.digest()));
	}

	private native static String checkFile(String inFile, String outFile, int n);
	public native static boolean copyFile(String fromFile,String toFile);
}
