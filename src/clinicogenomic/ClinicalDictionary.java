package clinicogenomic;

import java.util.ArrayList;
import java.util.HashMap;

public class ClinicalDictionary {
	private HashMap<String, ArrayList<String>> values;
	
	public ClinicalDictionary() {
		/*Search the keys, list to see if the item already exists!*/
		this.values = new HashMap<String, ArrayList<String>>();
	}
	
	public void addValue(String key, String value) {
		boolean found_value = false;
		if (this.values.get(key) == null) {
			this.values.put(key, new ArrayList<String>());
		}
		/*Scan values to see if the inserted value pre-exists*/
		for (String val : this.values.get(key)) {
			if (val.equals(value)) {
				/*It's already in! Don't do anything!*/
				found_value = true;
				break;
			}
		}
		
		/*We didn't find that value! add it!*/
		if (!found_value) {
			this.values.get(key).add(value);
		}
	}
	
	
	/**
	 * 
	 * @return
	 */
	private String printValues(String key) {
		String ret = "";
		for (String value : this.values.get(key)) {
			ret += value + ",";
		}
		
		/*Don't return the last comma*/
		return ret.substring(0, ret.length() - 1);
	}
	
	/**
	 * 
	 * @return
	 */
	public String toWekaHeader(ArrayList<String> keys) {
		String ret = "";
		for (String key : keys) {
			ret += "@Attribute\t" + key + "\t{" + printValues(key) + "}\n"; 
		}
		return ret;
	}
	
	
	
}
