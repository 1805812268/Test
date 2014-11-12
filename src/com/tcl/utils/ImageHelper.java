package com.tcl.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;

public class ImageHelper {
	public Bitmap getBitmapByString(String text,int w, int h,int textSize,int textColor,Typeface tf) {
		Bitmap strBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(strBitmap);
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setTextSize(textSize);
		paint.setTypeface(tf);
		paint.setColor(textColor);
		paint.setTextAlign(Align.LEFT);
		FontMetrics fm = paint.getFontMetrics();
		c.drawText(text, 0, h + fm.top - fm.ascent, paint);
		c.save();
		return strBitmap;
	}
	public Bitmap getBitmapByString(Paint paint,String text,int w, int h) {
		Bitmap strBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(strBitmap);
		if(paint == null){
			 paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		}
		paint.setTextAlign(Align.LEFT);
		FontMetrics fm = paint.getFontMetrics();
		c.drawText(text, 0, h + fm.top - fm.ascent, paint);
		c.save();
		return strBitmap;
	}
}
