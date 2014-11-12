package com.tcl.ehotel.data;

public class VodServerInfo {
	public String ip =null;
	public String name=null;
	public String password=null;
	public String path =null;
	@Override
	public String toString() {
		StringBuilder builer = new StringBuilder();
		builer.append(VodServerInfo.class.getSimpleName())
		.append(":")
		.append("ip->")
		.append(ip)
		.append(", name->")
		.append(name)
		.append(", password->")
		.append(password)
		.append(", path->")
		.append(path);
		return builer.toString();
	}
}
