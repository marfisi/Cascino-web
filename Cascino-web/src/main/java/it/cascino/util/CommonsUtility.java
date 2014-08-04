package it.cascino.util;

import java.util.HashMap;
import java.util.Map;

public class CommonsUtility{
	
	public static final Map<Integer, String> fileTypeFromNumberMap = new HashMap<Integer, String>();
	static{
		fileTypeFromNumberMap.put(1, ".orig");
		fileTypeFromNumberMap.put(2, ".hd");
		fileTypeFromNumberMap.put(3, ".hdwm");
		fileTypeFromNumberMap.put(4, ".ld");
		fileTypeFromNumberMap.put(5, ".ldwm");
	}
	
	public static final Map<String, String> fileTypeFromStringMap = new HashMap<String, String>();
	static{
		fileTypeFromStringMap.put(".orig", ".orig");
		fileTypeFromStringMap.put(".hd", ".hd");
		fileTypeFromStringMap.put(".hdwm", ".hdwm");
		fileTypeFromStringMap.put(".ld", ".ld");
		fileTypeFromStringMap.put(".ldwm", ".ldwm");
		// deleteTypeMap.put(6, ".err");
	}
}
