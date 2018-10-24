package com.org.Illumio.Service;

import java.util.Map;
import com.org.Illumio.Model.Rule;

public interface Cache {
	
	public Map<String, Rule> getRulesFromCSV(String path);
}
