
package clinicogenomic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import kmeans2.*;
import weka.Weka2;

/**
 *
 * @author Pavlos
 */
public class ClinicoGenomic {
	
	private enum Delimiter{
		tab, space, comma
	}
	
	private String gene_expression_name = "", clinical_name = "";
	private String username = "";
	private Delimiter delim;
	private int number_of_centroids = 2; 
	private float filter_per_sample = 0, filter_across_samples = 0;
	private File gene_expression_file_path, clinical_file_path;
	
	private KMeans2 kmeans;
	private Clinical2 clinical2;
	private DataPoint points;
	private Weka2 weka;
	
	private List<Cluster> cluster_list;
	
	private static String modelName="ClinicoGenomic";
	
	private static ClinicoGenomic instance = null;
	
	
	
	/* Container Class for the project using the Singleton Design Pattern*/
	private ClinicoGenomic() {
		
	}
	
	/**
	 * Resetting the singleton instance of the model.
	 */
	public static void resetInstance() {
		System.out.println("Instance reset!");
		instance = null;
	}
	
	/**
	 * @brief Singleton design pattern to keep track of the instance of our model.
	 * @return
	 */
	public static ClinicoGenomic getInstance() {
		if (instance == null) {
			System.out.println("Returning new Instance!");
			instance = new ClinicoGenomic();
			DateFormat df = new SimpleDateFormat("ddMMyy");
			Date dateobj = new Date();
			instance.setModelName(modelName + df.format(dateobj));
		}
		return instance;
	}	
	
	/**
	 * 
	 * @return Something to put on the creator field of the weka file.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @param username Set a new username for the weka file.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return Returns the models name. Currently set to Clinico_Genomic_(current_date)
	 */
	public String getModelName() {
		return ClinicoGenomic.modelName ;
	}
	
	/**
	 * 
	 * @param str
	 */
	private void setModelName(String str) {
		ClinicoGenomic.modelName = str;
	}
	
	/**
	 * 
	 * @return
	 */
	public DataPoint getDataPoints() {
		return this.points;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getGeneExpressionFileName() {
		return gene_expression_name;
	}

	
	/**
	 * 
	 * @return
	 */
	public KMeans2 getKmeans() {
		return this.kmeans;
	}
	
	/**
	 * 
	 * @param kmeans
	 */
	public void setKmeans(KMeans2 kmeans) {
		this.kmeans = kmeans;
	}
	
	/**
	 * 
	 * @return
	 */
	public Weka2 getWeka() {
		return this.weka;
	}
	
	/**
	 * 
	 * @param weka
	 */
	public void setWeka(Weka2 weka) {
		this.weka = weka;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Cluster> getClusters() {
		return this.cluster_list;
	}
	
	/**
	 * 
	 * @param clusters
	 */
	public void setClusters(List<Cluster> clusters) {
		this.cluster_list = clusters;
	}
	
	/**
	 * 
	 * @return
	 */
	public Clinical2 getClinical() {
		return clinical2;
	}

	/**
	 * 
	 * @param clinical
	 */
	public void setClinical(Clinical2 clinical) {
		this.clinical2 = clinical;
	}

	
	/**
	 * 
	 * @param gene_expression_name
	 */
	public void setGeneExpressionFileName(String gene_expression_name) {
		this.gene_expression_name = gene_expression_name;
	}


	/**
	 * 
	 * @return
	 */
	public String getClinicalFileName() {
		return clinical_name;
	}



	/**
	 * 
	 * @param clinical_name
	 */
	public void setClinicalFileName(String clinical_name) {
		this.clinical_name = clinical_name;
	}



	/**
	 * 
	 * @return
	 */
	public int getTotalCentroids() {
		return number_of_centroids;
	}



	/**
	 * 
	 * @param number_of_centroids
	 */
	public void setTotalCentroids(int number_of_centroids) {
		this.number_of_centroids = number_of_centroids;
	}

	
	/**
	 * *Under construction. Hasn't been incorporated to the model yet.*
	 * Different files have different delimiters, we can't always rely on a '\t' 
	 * @return
	 */
	public String getDelimiter() {
		if (this.delim == Delimiter.tab) {
			return "\t";
		}
		else if (this.delim == Delimiter.space){
			return " ";
		}
		else if (this.delim == Delimiter.comma){
			return ",";
		}
		else {
			return "-1";
		}
	}

	/**
	 * 
	 * @return
	 */
	public float getFilterPerSample() {
		return filter_per_sample;
	}


	/**
	 * 
	 * @param filter_coefficient
	 */
	public void setFilterPerSample(float filter_coefficient) {
		this.filter_per_sample = filter_coefficient;
	}

	/**
	 * 
	 * @return
	 */
	public float getFilterAcrossSamples() {
		return filter_across_samples;
	}

	
	/**
	 * 
	 * @param filter_across_samples
	 */
	public void setFilterAcrossSamples(float filter_across_samples) {
		this.filter_across_samples = filter_across_samples;
	}

	/**
	 * 
	 * @return
	 */
	public File getGeneExpressionFilePath() {
		return gene_expression_file_path;
	}



	/**
	 * 
	 * @param gene_expression_file_path
	 */
	public void setGeneExpressionFilePath(File gene_expression_file_path) {
		this.gene_expression_file_path = gene_expression_file_path;
	}


	/**
	 * 
	 * @return
	 */
	public File getClinicalFilePath() {
		return clinical_file_path;
	}



	/**
	 * 
	 * @param clinical_file_path
	 */
	public void setClinicalFilePath(File clinical_file_path) {
		this.clinical_file_path = clinical_file_path;
	}
	

	/**
	 * 
	 * @param kmeans 
	 * @param filter_per_sample 
	 * @return A <i>List<Cluster></i> containing the original clusters after the filtering. Mainly a PASS or FAIL
	 */
	public static List<Cluster> filterClusters(KMeans2 kmeans, float filter_per_sample, float filter_across_samples) {
		List<Cluster> clusters = kmeans.getPointsClusters();
		System.out.println("Filtering Clusters...");
		for (int i = 0 ; i < kmeans.k; i++){
			clusters.get(i).filterCluster(filter_per_sample, filter_across_samples);
			System.out.println("Cluster " + i + ": " + clusters.get(i).getClusterSize() + ". " + clusters.get(i).passFilter());
		}
		return clusters;
	}
	
	/**
	 * @param clusters An <i>ArrayList<Cluster></i> containing the selected clusters from the original dataset.
	 * @return An <i>ArrayList<DataPoint></i> containing the points that reside in the selected clusters.
	 */
	public static ArrayList<DataPoint> getSelectPoints(ArrayList<Cluster> clusters) {
		ArrayList<DataPoint> points = new ArrayList<DataPoint>();
		//this was the part in my life, I stopped iterating with i++ like a first-year cs student. 
		//mark this moment.
		for (Cluster cluster : clusters) {
			if (cluster.isSelected()){
				for (DataPoint point : cluster.getPoints()) {
					points.add(point);
				}
			}
		}
		
		return points;
	}
	
	
	/**
	 * 
	 * @param km
	 * @param clusters
	 * @param cl
	 * @param threshold
	 */
	public void generateWeka(KMeans2 km, List<Cluster> clusters, Clinical2 cl, float threshold, float threshold2) {
		System.out.println("Generating .arff for clusters (test set)");
		ArrayList<Cluster> approvedClusters = new ArrayList<Cluster>();
		
		DateFormat df = new SimpleDateFormat("ddMMyy");
		Date dateobj = new Date();
		
		String parent_dir = System.getProperty("catalina.base");
		String WEKA_FILE = parent_dir + "/clinicogenomic_weka" + df.format(dateobj) + ".arff";
		
		Cluster cluster;
		//Select the clusters to plot in the weka file.
		for (int i = 0; i < clusters.size(); ++i) {
			cluster = clusters.get(i);
			if (cluster.isSelected()) {
				approvedClusters.add(cluster);
			}
		}
		
		//But there may be 0 clusters selected. Don't plot anything... no point.
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(WEKA_FILE))) {
			String disclaimer = "% 1. Title: Clinico Genomic Model\n%\n% 2. Creator: " + this.getUsername() + "\n\n";
			String relation = "@Relation\tClinico_Genomic_Model_" + df.format(dateobj) + "\n\n";
			writer.write(disclaimer);
			writer.write(relation);
		
			// if there are no approvedClusters, there's no point generating anything...
			if (approvedClusters.size() != 0) {
				for (Cluster my_cluster: approvedClusters) {
					writer.write("@Attribute\t" + "cluster" + "_" + my_cluster.getClusterId()  + "_" + my_cluster.getClusterSize() + "\t{low,moderate,high}\n");
				}
				
				/*
				 * Clinical Attributes Weka
				 */
				writer.write(cl.clinicalToWekaHeader());
				// Done writing the attributes, time to write the data.
				writer.write("\n@Data\n\n");
				
				/* One of the pre-conditions is that the clinical points start with the class feature.
				 * We want the class feature to be last in the list, due to the fact that we want
				 * weka to build a model based on that! That's why, we reverse it! Because Weka will use
				 * the last attribute as a classifier for the model.*/
				Collections.reverse(cl.getClinicalPoints());
				
				for (int i = 0; i < km.dimensions; ++i) {
					for (Cluster approvedCluster : approvedClusters){
						int feature = approvedCluster.getFeatureArray().get(i);
						switch (feature) {
							case 1: {writer.write("low,"); break;}
							case 2: {writer.write("moderate,"); break;}
							case 3: {writer.write("high,"); break;}
							case -1: {writer.write("?,"); break;}
						}
					}
					
					//for clinicals
					writer.write(cl.clinicalToWekaData(i));
					writer.write("\n");
				}
				writer.close();
			}
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
