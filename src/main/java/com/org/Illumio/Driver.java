package com.org.Illumio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.org.Illumio.Constant.Constants;
import com.org.Illumio.Service.Firewall;
import com.org.Illumio.Service.FirewallService;

public class Driver 
{
    public static void main( String[] args ) throws Exception
    {
        Firewall firewall = new FirewallService("C:\\Users\\sawan\\eclipse-workspace\\Illumio-Coding-Assignment\\src\\firewall_rules.csv");
        
        /*System.out.println(firewall.accept_packet("outbound", "udp", 2000, "52.12.48.92"));
        
        
        if(firewall.accept_packet("outbound", "udp", 2000, "52.12.48.92")) {

        	System.out.println("Packet will pass");
        }
        else {
        	System.out.println("Packet will be blocked");
        }*/
        
        String testFilePath = "C:\\Users\\sawan\\eclipse-workspace\\Illumio-Coding-Assignment\\src\\test_firewall.csv";
        test(firewall, testFilePath);
    }
    
    private static void test(Firewall firewall, String testFilePath) throws Exception {
    	
    	BufferedReader br = null;
        String line = "";
        String cvsSplitBy = Constants.CSV_SPLITTER;
        
        try {

            br = new BufferedReader(new FileReader(testFilePath));
            while ((line = br.readLine()) != null) {
            	
                String[] rules = line.split(cvsSplitBy);
                
                boolean val = firewall.accept_packet(rules[0], rules[1], Integer.parseInt(rules[2]), rules[3]);
                
                boolean expected = "TRUE".equalsIgnoreCase(rules[4]) ? true : false;
                
                if(val==expected) {
                	continue;
                }
                else {
                	System.out.println("Faliling test is :");
                	System.out.println(rules[0]+" "+rules[1]+" "+rules[2]+" "+rules[3]);
                	System.out.println("value is "+val+" Expected is "+ expected);
                	throw new Exception("Code failed!!!!");
                }
            }
            
            System.out.println("All test cases passed successfully!!!!");

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
    	
    }
}
