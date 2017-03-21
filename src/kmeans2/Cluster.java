package kmeans2;

import java.util.ArrayList;

import clinicogenomic.Result;

/**
 * 
 * @author Pavlos
 *
 */
public class Cluster {
	//cluster unique cluster id. it's going to be 0, 1, 2, 3, ...
	private int clusterId = -1;
	
	//cluster values of importance.
	private DataPoint centroid;
	private ArrayList<DataPoint> points;
	
	private ArrayList<Integer> featureArray;
	private ArrayList<Float> featureArrayPercentages;
	
	private boolean selected = false;	//Very important! If selected, the cluster will be plotted.
	private boolean pass = false;
	
	private double min_sample_coverage = 101.0;
	private double max_sample_coverage = 0.0;
	
	
	//Row-wise variables
	private float clusterPercentage = -1;
	private int clusterValue = -1;
	
	
	/**
	 * 
	 */
	public Cluster(){
		this.centroid = null;
		this.points = new ArrayList<DataPoint>();
	}
	

	/**
	 * Constructor for a cluster. Needs a DataPoint to denote the center and a *unique* id provided by the system.
	 * @param point
	 * @param clusterId
	 */
	public Cluster(DataPoint point, int clusterId) {
		this.points = new ArrayList<DataPoint>();
		
		this.featureArray = new ArrayList<Integer>();
		this.featureArrayPercentages = new ArrayList<Float>();
		
		this.centroid = point;
		this.clusterId = clusterId;
	}


	
	/**
	 * Adds the specified *DataPoint* to the current cluster.
	 * @param p
	 */
	public void addPoint(DataPoint p) {
		this.points.add(p);
	}
	
	
	/**
	 * Updates the centroid of the cluster.
	 */
	public void updateCentroid(){
		ArrayList<Float> sums = new ArrayList<Float>();
		
		//Calculate some variables here
		int dims = centroid.getDimensions();
		int sum;
		
		for (DataPoint point : points) {
			sum = 0;
			for (int j = 0; j < dims; ++j){
				sum += point.getValues().get(j);
			}
			sums.add((float)(sum/dims));
		}
	}
	
	
	/**
	 * Returns the cluster's size.
	 * @return
	 */
	public int getClusterSize() {
		return this.points.size();
	}
	
	
	/**
	 * Returns the cluster's centroid as *DataPoint*
	 * @return
	 */
	public DataPoint getCentroid() {
		return this.centroid;
	}
	
	
	/**
	 * Returns an *ArrayList* of points that currently are assigned to the cluster.
	 * @return
	 */
	public ArrayList<DataPoint> getPoints() {
		return this.points;
	}

	
	/**
	 * @brief Return the filter assigned to the cluster.
	 * @return
	 */
	public boolean passFilter() {
		return this.pass;
	}
	
	
	/**
	 * Returns the feature array for the current cluster.
	 * @return
	 */
	public ArrayList<Integer> getFeatureArray() {
		return this.featureArray;
	}


	/**
	 * Returns if cluster will be used in the weka file.
	 * @return
	 */
	public boolean isSelected() {
		return this.selected;
	}
	
	/**
	 * Mark current cluster for representation on the weka file.
	 * If the cluster is already marked, then it's unmarked.
	 */
	public void select() {
		if (this.isSelected()){
			System.out.println("Un-Selected cluster: " + this.clusterId);
			this.selected = false;
		}
		else {
			System.out.println("Selected cluster: " + this.clusterId);
			this.selected = true;
		}
	}
	
	
	
	/**
	 * Returns the cluster's percentage. To be used with the across-samples threshold.
	 * @return
	 */
	public float getClusterPercentage() {
		return this.clusterPercentage;
	}

	
	
	/**
	 * Returns the most highly expressed value of the cluster. (1,2,3)
	 * @return
	 */
	public int getClusterValue() {
		return this.clusterValue;
	}


	/**
	 * Returns the current cluster's id.
	 * @return
	 */
	public int getClusterId(){
		return this.clusterId;
	}
	
	
	/**
	 * Returns the min sample coverage, as it was identified by filterCluster.
	 * @return
	 */
	public double getMinSampleCoverage() {
		return min_sample_coverage;
	}

	
	/**
	 * Returns the max sample coverage, as it was identified by filterCluster.
	 * @return
	 */
	public double getMaxSampleCoverage() {
		return max_sample_coverage;
	}

	
	/**
	 * Checks whether the cluster meets the threshold requirements per sample and across the samples.
	 * Then assigns the *boolean* pass private value accordingly.
	 * @param threshold1	The per sample threshold.
	 * @param threshold2	The across samples threshold. 
	 */
	public void filterCluster(float th1, float th2) {
		//fill the feature array by iterating column wise the dataset.
		int dims = this.centroid.getDimensions();
		int lows_sum = 0, moderates_sum = 0, highs_sum = 0;
		for (int i = 0; i < dims; i++) {
			int lows = 0, moderates = 0 , highs = 0;
			
			int cluster_size = this.getClusterSize();
			for (int j = 0; j < cluster_size; j++) {
				switch (points.get(j).getValues().get(i)) {
					case 1: lows++; break;
					case 2: moderates++; break;
					case 3: highs++; break;
				}
			}
			lows_sum += lows;
			moderates_sum += moderates;
			highs_sum += highs;
			// we have generated results for the column.
			// see how the column is expressed.
			int max = Math.max(Math.max(lows, moderates), highs);
			
			/* calculate the percentage of the max. and compare it with the per sample threshold.
			 * if it's not satisfied, it's assigned the -1 value. */
			float perc = (max / (float)cluster_size) * 100;
			if (perc >= th1) {
				if (max == lows) {
					this.featureArray.add(1);
					this.featureArrayPercentages.add((lows/(float)cluster_size)*100);
				}
				else if (max == moderates) {
					this.featureArray.add(2); 
					this.featureArrayPercentages.add((moderates/(float)cluster_size)*100);
				}
				else if (max == highs) {
					this.featureArray.add(3); 
					this.featureArrayPercentages.add((highs/(float)cluster_size)*100);
				}
			}
			else {
				this.featureArray.add(-1);
				this.featureArrayPercentages.add(-1.0f);
			}
		}
		
		int cluster_max = Math.max(Math.max(lows_sum, moderates_sum), highs_sum);
		if (cluster_max == lows_sum) {this.clusterValue = 1;}
		else if (cluster_max == moderates_sum) {this.clusterValue = 2;}
		else if (cluster_max == highs_sum) {this.clusterValue = 3;}
		else {this.clusterValue = -1;}		//ERROR! SHOULD NEVER REACH THIS!
		
		
		//feature array is now generated for our cluster, need to check if it passes the threshold
		//use a wrapper class to return multiple values!
		Result res = this.getPassingFeaturesPercentage(this.featureArray, this.featureArrayPercentages, th1);
		this.clusterPercentage = res.getPercentage();
		this.min_sample_coverage = res.getMinPercentage();
		this.max_sample_coverage = res.getMaxPercentage();
		
		//System.out.println("Cluster perc = " + this.clusterPercentage);
		if (this.clusterPercentage >= th2) {
			this.pass = true;
		}
		
	}
	
	
	/**
	 * Scan the generated features percentages in order to determine, whether passes the per-sample threshold.
	 * @param Percentages
	 * @return
	 */
	private Result getPassingFeaturesPercentage(ArrayList<Integer> Features, ArrayList<Float> Percentages, float th) {
		int count = 0;
		float max = 0, min = 101;
		
		for (Float percentage : Percentages) {
			//Whatever the percentage of the feature generated(1,2,3). if the percentage is greater than the threshold, we r solid! 
			if (percentage >= th) {
				count++;
			}
			
			if (percentage >= max) {
				max = percentage;
			}
			
			if (percentage <= min && percentage >= th) {
				min = percentage;
			}
		}
		
		float return_percentage = (count/(float)Percentages.size()) * 100;
		
		return new Result(return_percentage, min, max);
	}
	
	
	
	/**
	 * Resets all variables of this cluster to factory settings. This item is necessary, prior to reclustering.
	 */
	public void resetCluster() {
		//this.clusterId = -1;
		
		this.centroid.resetCenter();
		
		/* Reset the variable that denotes to which cluster each belonging point belongs to.*/
		for (DataPoint point : this.points){
			point.setBelongingCluster(-1);
			point.resetCenter();
		}
		
		//this.points = new ArrayList<DataPoint>();
		
		this.featureArray = new ArrayList<Integer>();
		this.featureArrayPercentages = new ArrayList<Float>();
		
		this.selected = false;					//Very important! If selected, the cluster will be plotted.
		this.pass = false;
		
		//Column-wise variables
		
		this.min_sample_coverage = 101.0;
		this.max_sample_coverage = 0.0;
		
		
		//Row-wise variables
		this.clusterPercentage = -1;
		this.clusterValue = -1;
	}
	

	/**
	 * Basic toString, mainly for debugging...
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder("This cluster contains the following points:\n");
		for (DataPoint point : points)
			builder.append(point.toString() + ",\n");
		return builder.deleteCharAt(builder.length() - 2).toString(); 
	}
}