package com.tcl.ehotel.view;

import android.graphics.Paint;

public class TextViewHelper {
	public static float getTextSize(String value ,Paint paint,float maxWidth){
		float currentWidth = paint.measureText(value);
		if(currentWidth<=maxWidth){
			return paint.getTextSize();
		}
		float newSize = maxWidth/currentWidth*paint.getTextSize();
		return  newSize ;
	}
}
