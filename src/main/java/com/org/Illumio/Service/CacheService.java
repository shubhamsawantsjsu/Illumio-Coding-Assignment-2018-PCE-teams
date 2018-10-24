package com.org.Illumio.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.org.Illumio.Constant.Constants;
import com.org.Illumio.Model.Rule;

public class CacheService implements Cache {
	
	public Map<String, Rule> getRulesFromCSV(String path) {
		
		Map<String, Rule> map = getMapOfRules();
		
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = Constants.CSV_SPLITTER;
        
        try {

            br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
            	
                String[] rules = line.split(cvsSplitBy);
                
                String key = rules[0]+ Constants.DASH +rules[1];
                
                Rule targetRule = map.get(key);
                
                fillTheRule(targetRule, rules[2], rules[3]);
                
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return map;

    }
	
	private void fillTheRule(Rule rule, String port, String ip) {
		
		Map<Integer, Set<String>> portIpMap = rule.getMap();
		
		if(port.contains(Constants.DASH)) {
			
			String [] portRange = port.split(Constants.DASH);
			
			int startingPort = Integer.parseInt(portRange[0]);
			int lastPort = Integer.parseInt(portRange[1]);
			
			for (int i=startingPort;i<=lastPort;i++) {
				
				if(portIpMap.containsKey(i)) {
					portIpMap.get(i).add(ip);
				}
				else {
					Set<String> set = new HashSet<String>();
					set.add(ip);
					portIpMap.put(i, set);
				}
			}
		}
		else {
			int portIntegerValue = Integer.parseInt(port);
			
			if(portIpMap.containsKey(portIntegerValue)) {
				portIpMap.get(portIntegerValue).add(ip);
			}
			else {
				Set<String> set = new HashSet<String>();
				set.add(ip);
				portIpMap.put(portIntegerValue, set);
			}
		}
	}
	
	
	
	private Map<String, Rule> getMapOfRules() {
		
		Rule inboundTCP = new Rule();
		inboundTCP.setDirectionAndProtocol(Constants.INBOUND_TCP);
		inboundTCP.setMap(new HashMap<Integer, Set<String>>());
		
		Rule inboundUDP = new Rule();
		inboundUDP.setDirectionAndProtocol(Constants.INBOUND_UDP);
		inboundUDP.setMap(new HashMap<Integer, Set<String>>());
		
		Rule outboundTCP = new Rule();
		outboundTCP.setDirectionAndProtocol(Constants.OUTBOUND_TCP);
		outboundTCP.setMap(new HashMap<Integer, Set<String>>());
		
		Rule outboundUDP = new Rule();
		outboundUDP.setDirectionAndProtocol(Constants.OUTBOUND_UDP);
		outboundUDP.setMap(new HashMap<Integer, Set<String>>());
		
		Map<String, Rule> map = new HashMap<String, Rule> ();
		map.put(Constants.INBOUND_TCP, inboundTCP);
		map.put(Constants.INBOUND_UDP, inboundUDP);
		map.put(Constants.OUTBOUND_TCP, outboundTCP);
		map.put(Constants.OUTBOUND_UDP, outboundUDP);
		
		return map;
		
	}
}
