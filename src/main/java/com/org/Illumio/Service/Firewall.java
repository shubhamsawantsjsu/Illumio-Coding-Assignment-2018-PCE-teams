package com.org.Illumio.Service;

public abstract class Firewall {
	
	protected Firewall(String Path) {}
	
	public abstract boolean accept_packet(String direction, String protocol, Integer port, String ip_address);
	
}
