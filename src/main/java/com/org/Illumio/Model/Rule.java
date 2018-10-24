package com.org.Illumio.Model;

import java.util.Map;
import java.util.Set;

public class Rule {

	String directionAndProtocol;
	Map<Integer, Set<String>> map;
	
	public Map<Integer, Set<String>> getMap() {
		return map;
	}

	public void setMap(Map<Integer, Set<String>> map) {
		this.map = map;
	}
	
	public String getDirectionAndProtocol() {
		return directionAndProtocol;
	}

	public void setDirectionAndProtocol(String directionAndProtocol) {
		this.directionAndProtocol = directionAndProtocol;
	}

}
