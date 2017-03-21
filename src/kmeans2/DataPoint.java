package kmeans2;

import java.util.ArrayList;


public class DataPoint {
	
	private int index = -1; 	//Denotes which cluster it belongs to. Initialize to -1 for debugging
	private String id;
	private ArrayList<Integer> values;
	private ArrayList<String> valueID;
	private boolean center;
	private int position = -1;	//position on Y-axis
	
	/**
	 * 
	 * @param sample_id
	 * @param values
	 * @param valueID
	 */
	public DataPoint(String sample_id, ArrayList<Integer> values, ArrayList<String> valueID) {
		this.id = sample_id;
		this.values = values;
		this.valueID = valueID;
		this.center = false;
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
	 * @return
	 */
	public int getBelongingCluster() {
		return this.index;
	}
	
	/**
	 * 
	 * @param i
	 */
	public void setBelongingCluster(int i) {
		this.index = i;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getValues() {
		return values;
	}

	/**
	 * 
	 * @param values
	 */
	public void setValues(ArrayList<Integer> values) {
		this.values = values;
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

	/**
	 * 
	 * @return
	 */
	public int getDimensions(){
		return this.values.size();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isCenter(){
		return this.center;
	}
	


	public double getEuclideanDistance(DataPoint otherPoint){
		double distance = 0.0;
		
		int dims = this.getDimensions();
		
        for (int i = 0; i < dims; i++) {
            distance += Math.pow(this.getValues().get(i) - otherPoint.getValues().get(i), 2);
        }
        
        return Math.sqrt(distance);
	}
	
	/**
     * 
     * @param point1
     * @param point2
     * @return 
     * @brief Works better for high dimensional vectors.
     */
	public double getManhattanDistance(DataPoint otherPoint) {
		double distance = 0.0;
		
		int dims = this.getDimensions();
		
        for (int i = 0; i < dims; i++) {
            distance += Math.abs(this.getValues().get(i) - otherPoint.getValues().get(i));
        }
        
        return distance;
	}
	
	
	public String valueIDToString() {
		String ret = "";
		for (int id = 0; id < this.valueID.size(); id++){
			ret += "@Attribute\t" + this.valueID.get(id) + "\t{low,moderate,high}\n";
		}
		return ret;
	}
	
	/**
	 * 
	 * @return
	 */
	public String valuesToString() {
		//String ret = StringUtils.join(this.values, ',');
		
		int value = -1;
		String ret = "";
		for (int i = 0; i < this.getDimensions(); ++i) {
			
			value = this.values.get(i);
			switch (value) {
				case 1: ret += "low,"; break;
				case 2: ret += "moderate,"; break;
				case 3: ret += "high,"; break;
			}
		}
		ret = ret.substring(0, ret.length() - 1);
		//System.out.println(ret);
		
		return ret;
	}
	
	/**
	 * 
	 * @param pos
	 */
	public void setPosition(int pos) {
		this.position = pos;
	}
	
	/**
	 * 
	 * @return Y-axis position
	 */
	public int getPosition() {
		return this.position;
	}
	
	
	
	public void resetCenter() {
		this.center = false;
	}
	
	/**
	 * 
	 */
	public String toString() {
		String values =  "";
		String id = this.getId();
		
		int dims = this.getDimensions();
		for (int i = 0; i < dims; ++i){
			values += this.values.get(i);
		}
		
		return id + " " + values;
	}
	
}