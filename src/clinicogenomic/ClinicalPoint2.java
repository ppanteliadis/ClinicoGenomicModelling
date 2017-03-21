package clinicogenomic;

import java.util.ArrayList;

public class ClinicalPoint2 {
	private String id;
	private ArrayList<String> valueID;
	
	public ClinicalPoint2(String sample_id, ArrayList<String> valueID) {
		this.id = sample_id;
		this.valueID = valueID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @param index
	 * @return
	 */
	public String getValueID(int index) {
		return valueID.get(index);
	}

	/**
	 * 
	 * @param index
	 * @param valueID
	 */
	public void setValueID(int index, String valueID) {
		this.valueID.add(index, valueID);
	}
	
	public int getSize() {
		return this.valueID.size();
	}
	
}
