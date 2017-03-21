package kmeans2;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Pavlos
 *
 */
public class KMeans2 {
	private static final Random random = new Random();
	public List<DataPoint> allPoints;
	public final int k;
	public int dimensions = 0;
	private Clusters pointClusters; //the k Clusters

	/**
	 * @param pointsFile : the csv file for input points
	 * @param k : number of clusters
	 * @brief Constructs a KMeans instance with default parameters
	 */
	public KMeans2(int k, String pointsFile, String delimiter) {
		System.out.println("Opening: " + pointsFile + "...");
		if (k < 2)
			new Exception("The value of k should be 2 or more.").printStackTrace();
		this.k = k;
		List<DataPoint> points = new ArrayList<DataPoint>();
		String words[], id;
		int pos = 0;
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(pointsFile), "UTF-8"))){
			
			String header[] = reader.readLine().split(delimiter);		//Skip header
			ArrayList<String> valuesIDs = new ArrayList<String>(Arrays.asList(header));
			valuesIDs.remove(0);	//sample id
			
			String line;
			ArrayList<String> strValues;
			ArrayList<Integer> intValues = null;
			
			while ((line = reader.readLine()) != null){
				words = line.split(delimiter);
				strValues = new ArrayList<String>(Arrays.asList(words));
				id = strValues.get(0);
				strValues.remove(0);
				
				intValues = getIntegerArray(strValues);
				
				DataPoint p = new DataPoint(id, intValues, valuesIDs);
				p.setPosition(pos);
				points.add(p);
				pos++;
			}
			dimensions = intValues.size();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		this.allPoints = Collections.unmodifiableList(points);
		System.out.println("A total of: " + this.allPoints.size() + " points to be clustered");
	}

	
	
	
	/**
	 * 
	 * @return
	 */
	public List<DataPoint> getAllPoints() {
		return allPoints;
	}

	
	/**
	 * 
	 * @param allPoints
	 */
	public void setAllPoints(List<DataPoint> allPoints) {
		this.allPoints = allPoints;
	}


	


	public void resetPointClusters() {
		this.pointClusters = null;
	}




	/**
	 * 
	 * @param stringArray
	 * @return Returns the contents of <i>stringArray</i> as an Integer ArrayList.
	 */
	private ArrayList<Integer> getIntegerArray(ArrayList<String> stringArray) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int value = -1;
        for (String stringValue : stringArray) {
            try {
                //Convert String to Integer, and store it into integer array list.
            	value = Integer.parseInt(stringValue);
                result.add(value);
            } catch (NumberFormatException nfe) {
               System.out.println("Could not parse " + nfe);
            } 
        }
        return result;
    }
	

	/**
	 * @brief this is the 1st step of Kmeans. It gets random seeds as initial centroids of the k clusters.
	 */
	private void getInitialKRandomSeeds(){
		pointClusters = new Clusters(allPoints);
		List<DataPoint> kRandomPoints = getKRandomPoints();
		for (int i = 0; i < k; i++) {
			//Assign the point to a cluster
			kRandomPoints.get(i).setBelongingCluster(i);
			//But this is arbitrary. Because the cluster has to be created first. 
			Cluster cluster = new Cluster(kRandomPoints.get(i), i);
			//Assign the cluster to the cluster list.
			pointClusters.add(cluster);
		} 
	}

	/**
	 * 
	 * @return A list of the initial k clusters.
	 * @brief Initializes the first k random points as centroids.
	 */
	private List<DataPoint> getKRandomPoints() {
		List<DataPoint> kRandomPoints = new ArrayList<DataPoint>();
		boolean[] alreadyChosen = new boolean[allPoints.size()];
		int size = allPoints.size();
		for (int i = 0; i < k; i++) {
			int index = -1, r = random.nextInt(size--) + 1;
			for (int j = 0; j < r; j++) {
				index++;
				while (alreadyChosen[index])
					index++;
			}
			kRandomPoints.add(allPoints.get(index));
			alreadyChosen[index] = true;
		}
		return kRandomPoints;
	}

	
	/**
	 * @brief This is the 2nd step of the kmeans algorithm. It assigns all the points to the initial k clusters.
	 */
	private void getInitialClusters(){
		pointClusters.assignPointsToClusters();
	}


	/**
	 * @brief This is the 3rd step of the kmeans algorithm. It updates the k Clusters until no changes in their members occur.
	 */
	private void updateClustersUntilNoChange(){
		boolean isChanged = pointClusters.updateClusters();
		while (isChanged)
			isChanged = pointClusters.updateClusters();
	}


	/**
	 * @return Returns a <i>List</i> of clusters filled with points.
	 * @brief Performs the kmeans clustering.
	 */
	public List<Cluster> getPointsClusters() {
		System.out.println("Generating " + this.k + " Clusters...");
        long startTime = System.currentTimeMillis();
		
		if (pointClusters == null) {
			//Step1
			getInitialKRandomSeeds();
			
			//Step2
			getInitialClusters();
			
			//Step3
			updateClustersUntilNoChange();
		}
		
		long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        String t = String.format("%02d min, %02d sec", 
            TimeUnit.MILLISECONDS.toMinutes(totalTime),
            TimeUnit.MILLISECONDS.toSeconds(totalTime) - 
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalTime))
        );
        
        System.out.println("Kmeans running time: " + t);
		
        if (pointClusters == null) {
        	System.out.println("null");
        }
        
		return pointClusters;
	}

}