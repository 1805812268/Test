package com.tcl.ehotel.background;

public enum AnimationType {
	UNKONW(999);
	
	private int value;
	private AnimationType(int id){
		this.value = id;
	}
	public int getValue(){
		return this.value;
	}
	public static AnimationType getType(int type){
		switch(type){
		default:
			return UNKONW;
		}
	}
}