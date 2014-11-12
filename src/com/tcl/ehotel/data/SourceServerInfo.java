package com.tcl.ehotel.data;

public class SourceServerInfo {
	public String ip =null;
	public String port =null;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(SourceServerInfo.class.getSimpleName())
		.append(":ip->")
		.append(ip)
		.append(", port->")
		.append(port);
		return builder.toString();
	}
}
