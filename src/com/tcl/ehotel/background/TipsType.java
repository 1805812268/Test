package com.tcl.ehotel.background;

public enum TipsType {
	UNKONW(999);
	
	private int value;
	private TipsType(int id){
		this.value = id;
	}
	public int getValue(){
		return this.value;
	}
	public static TipsType getType(int type){
		switch(type){
		default:
			return UNKONW;
		}
	}
}