package kmeans2;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Pavlos
 *
 */
public class Clusters extends ArrayList<Cluster>{
		private static final long serialVersionUID = 1L;
		private final List<DataPoint> allPoints;
		private boolean isChanged;
		/**
		 * 
		 * @param allPoints
		 */
		public Clusters(List<DataPoint> allPoints){
			this.allPoints = allPoints;
		}
		
		/** @param point
		 * @return the index of the Cluster nearest to the point
		 */
		public Integer getNearestCluster(DataPoint point){
			double minSquareOfDistance = Double.MAX_VALUE;
			int itsIndex = -1;
			for (int i = 0 ; i < size(); i++){
				double squareOfDistance = point.getEuclideanDistance(get(i).getCentroid());
				if (squareOfDistance < minSquareOfDistance){
					minSquareOfDistance = squareOfDistance;
					itsIndex = i;
				}
			}
			return itsIndex;
		}

		
		/**
		 * 
		 * @return
		 */
		public boolean updateClusters(){
			for (Cluster cluster : this) {
				cluster.updateCentroid();
				cluster.getPoints().clear();
			}
			this.isChanged = false;
			assignPointsToClusters();
			return this.isChanged;
		}

		
		/**
		 * 
		 */
		public void assignPointsToClusters(){
			for (DataPoint point : allPoints) {
				int previousIndex = point.getBelongingCluster();
				int newIndex = getNearestCluster(point);
				
				if (previousIndex != newIndex){
					isChanged = true;
				}
				Cluster target = get(newIndex);
				point.setBelongingCluster(newIndex);
				target.getPoints().add(point);
			}
		}		
	}
