package com.org.Illumio.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;

import com.org.Illumio.Constant.Constants;
import com.org.Illumio.Model.Rule;

public class FirewallService extends Firewall {
	
	Map<String, Rule> map;
	
	public FirewallService(String Path) {
		super(Path);
		Cache cache = new CacheService();
		map = cache.getRulesFromCSV(Path);
	}
	
	public boolean accept_packet(String direction, String protocol, Integer port, String ip_address) {
		
		String key = direction+ Constants.DASH +protocol;
		Rule rule = map.get(key);
		
		Map<Integer, Set<String>> portIpMap = rule.getMap();
		
		if(!portIpMap.containsKey(port)) {
			return false;
		}
		
		Set<String> ipAddressSet = portIpMap.get(port);
		
		if(checkIfPresent(ipAddressSet, ip_address)) {
			return true;
		}
		
		return false;
	}
	
	private boolean checkIfPresent(Set<String> ipAddressSet, String ip_address) {
		
		if(ipAddressSet.contains(ip_address)) {
			return true;
		}
		
		for (String ip : ipAddressSet) {
			
			if(!ip.contains(Constants.DASH)) {
				continue;
			}
			
			String [] ipRange = ip.split(Constants.DASH);
			
			if(isValidRange(ipRange[0], ipRange[1], ip_address)) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isValidRange(String ipStart, String ipEnd, String ipToCheck) {
		try {
			long ipLo = ipToLong(InetAddress.getByName(ipStart));
			
			long ipHi = ipToLong(InetAddress.getByName(ipEnd));
			
			long ipToTest = ipToLong(InetAddress.getByName(ipToCheck));
			
			return (ipToTest >= ipLo && ipToTest <= ipHi);
			
		} 
		catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public long ipToLong(InetAddress ip) {
		byte[] octets = ip.getAddress();
		long result = 0;
		
		for (byte octet : octets) {
			result <<= 8;
			result |= octet & 0xff;
		}
		
		return result;
	}

}
