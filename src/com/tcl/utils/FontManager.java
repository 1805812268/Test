package com.tcl.utils;

import android.graphics.Typeface;

public class FontManager {
	public static Typeface getTypefaceOfFZLT(){
		return getTypeface(FontType.FZLTH);
	}
	/**
	 * get font
	 * @param type
	 * @return
	 */
	public static Typeface getTypeface(FontType type){
		switch(type){
		case FZLTH:
			if(typeface_fzlth == null){
				try{
					typeface_fzlth =Typeface.createFromFile(SYSTEM_FONT_FZLTH);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return typeface_fzlth;
		default:
			if(typeface_fzlth == null){
				try{
					typeface_fzlth =Typeface.createFromFile(SYSTEM_FONT_FZLTH);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			return typeface_fzlth;
		}
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static Typeface getTypeface(String type){
		return getTypeface(FontType.getFontType(type));
	}
	
	private static final String SYSTEM = "/system/fonts/";
	private static final String SYSTEM_FONT_FZLTH=SYSTEM+"FZLTH_GB18030.ttf";
	//    private static final String SYSTEM_FONT_FZXH1JW=SYSTEM+"fzxh1jw.ttf";
	//    private static final String SYSTEM_FONT_ARIAL=SYSTEM+"arial.ttf";
	//    private static final String SYSTEM_FONT_HELVETICATHN=SYSTEM+"helveticathn.ttf";
	//    private static final String SYSTEM_FONT_FZXYJW=SYSTEM+"fzxyjw.ttf";
	
    private static Typeface typeface_fzlth =null;
	
	public enum FontType{
		FZLTH("font_fzlth");
		String value;
		private FontType(String value){
			this.value = value;
		}
		public String getValue(){
			return value;
		}
		public static FontType getFontType(String type){
			if(type.equalsIgnoreCase("font_fzlth")){
				return FZLTH;
			}else{
				return FZLTH;
			}
		}
	}
}
