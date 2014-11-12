package com.tcl.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
//import android.util.Log;



public class BitmapCache {
	private static BitmapCache cache;
	private Hashtable<String ,BitmapReference> bitmapReferences;
	private ReferenceQueue<Bitmap> queue;
	
	
	private BitmapCache(){
		bitmapReferences = new Hashtable<String, BitmapReference>();
		queue = new ReferenceQueue<Bitmap>();
	}
	
	private class BitmapReference extends SoftReference<Bitmap>{
		private String path;
		public BitmapReference(Bitmap r, ReferenceQueue<Bitmap> q,String path) {
			super(r, q);
			this.path = path;
		}
		public String getPath(){
			return path;
		}
	}
	
	public static synchronized BitmapCache getInstance(){
		if(cache == null){
			cache = new BitmapCache();
		}
		return cache;
	}
	// 以软引用的方式对一个 Bitmap对象的实例进行引用并保存该引用
	private void cacheBitmap(String path,Bitmap bitmap){
		cleanCache();
		BitmapReference  bitmapReference = new BitmapReference(bitmap,queue,path);
		bitmapReferences.put(path, bitmapReference);
	}
	public Bitmap getBitmap(String path){
		Bitmap bitmap = null;
		if(path == null){
			return bitmap;
		}
		if(bitmapReferences.containsKey(path)){
			BitmapReference bitmapReference = bitmapReferences.get(path);
			bitmap = bitmapReference.get();
		}
		if(bitmap == null){
			bitmap  = CreateBitmap(path);
			this.cacheBitmap(path, bitmap);
			//Log.v("cache", "cache bitmap------------>"+path);
		}else{
			//Log.v("cache", "cache bitmap----success-------->"+path);
		}
		return bitmap;
	}
	
	public Bitmap getBitmap(String path,int width ,int height){
		Bitmap bitmap = null;
		if(bitmapReferences.containsKey(path)){
			BitmapReference bitmapReference = bitmapReferences.get(path);
			bitmap = bitmapReference.get();
		}
		if(bitmap == null||bitmap.getWidth()!=width ||bitmap.getHeight()!= height){
			bitmap  = CreateBitmap(path,width,height);
			if(bitmap != null){
				this.cacheBitmap(path, bitmap);
			}
			//Log.v("cache", "cache bitmap----fail-------->"+path);
		}else{
			//Log.v("cache", "cache bitmap----success-------->"+path);
		}
		return bitmap;
	}
	//缓存清理,从Hashtable中删除已经被回收的软引用bitmap
	public void cleanCache(){
		BitmapReference ref = null;
		while((ref= (BitmapReference) queue.poll())!=null){
			bitmapReferences.remove(ref.getPath());
		}
	}
	//创建原比列的bitmap对象
	private Bitmap  CreateBitmap(String path){
		return CreateBitmap(path,false,0,0);
	}
	//创建指定大小的bitmap对象
	private Bitmap  CreateBitmap(String path,int width,int height){
		return CreateBitmap(path,true,width,height);
	}
	//创建bitmap对象
	private Bitmap  CreateBitmap(String path,boolean isScale,int width,int height){
		Bitmap bitmap = null ;
		File f = new File(path);
		if(!f.exists()){
			return null;
		}
		try{
			InputStream is = new FileInputStream(f);
			bitmap = BitmapFactory.decodeStream(is);
			if(isScale){
				int w= bitmap.getWidth();
				int h = bitmap.getHeight();
				float scaleWidth = ((float)width)/w;
				float scaleHeight = ((float)height)/h;
				Matrix  matrix = new Matrix();
				matrix.postScale(scaleWidth, scaleHeight);
				Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, w,h,matrix,true);
				return newBitmap;
			}
		}catch(OutOfMemoryError e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
			//Log.e("cache", "load bitmap failed :"+path);
			bitmap = null;
		}
		return bitmap;
	}
	
	
	
	
	/**设置图片的圆角
	 * 
	 * @param bitmap
	 * @param pixels 圆角大小
	 * @return
	 */
	public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
		if (bitmap == null) {
			return null;
		}
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);
		final float roundPx = pixels;
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}
	/**
	 * 图片变形缩放，缩放到指定尺寸
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap ScaleBitmap(Bitmap bitmap ,int width,int height){
		int ow = bitmap.getWidth();
		int oh =bitmap.getHeight();
		float sx = width*1f/ow;
		float sy = height*1f/oh;
		Matrix matrix = new Matrix();
		matrix.postScale(sx, sy);
		Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, ow, oh, matrix, true);
		return b;
	}
	/**图片变形缩放，缩放到指定尺寸
	 * 
	 * @param bitmap
	 * @param sx x方向缩放比例
	 * @param sy y方向缩放比例
	 * @return
	 */
	public static Bitmap ScaleBitmap(Bitmap bitmap ,float sx,float sy){
		int ow = bitmap.getWidth();
		int oh =bitmap.getHeight();
		Matrix matrix = new Matrix();
		matrix.postScale(sx, sy);
		Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, ow, oh, matrix, true);
		return b;
	}
	
	public static Bitmap ScaleBitmap(Resources res,int id,int width,int height){
		Bitmap bitmap = BitmapFactory.decodeResource(res, id);
		bitmap = ScaleBitmap(bitmap,width,height);
		return bitmap;
	}
	
	public static Bitmap getBitmapByHttp(String url) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bitmap;
	}
}
