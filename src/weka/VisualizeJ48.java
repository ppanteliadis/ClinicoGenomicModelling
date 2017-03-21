package weka;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;


public class VisualizeJ48 {
	
	private final String WEKA_FILE;
	
	public VisualizeJ48(){
		DateFormat df = new SimpleDateFormat("ddMMyy");
		Date dateobj = new Date();
		
		String parent_dir = System.getProperty("catalina.base");
		this.WEKA_FILE = parent_dir + "/clinicogenomic_weka" + df.format(dateobj) + ".arff";
	}
	
	public VisualizeJ48(String filename) {
		this.WEKA_FILE = filename;
	}
	
	
	/**
	 * Under Construction. Visualization of the J48 Decision Tree
	 */
	public void visualize() {
		// train classifier
		try{
			J48 cls = new J48();
		     Instances data = new Instances(new BufferedReader(new FileReader(WEKA_FILE)));
		     data.setClassIndex(data.numAttributes() - 1);
		     cls.buildClassifier(data);
		 
		     // display classifier
		     final javax.swing.JFrame jf = new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
		     
		     jf.setSize(1200,1200);
		     jf.getContentPane().setLayout(new BorderLayout());
		     
		     TreeVisualizer tv = new TreeVisualizer(null, cls.graph(), new PlaceNode2());
		     
		     jf.getContentPane().add(tv, BorderLayout.CENTER);
		     
		     jf.addWindowListener(new java.awt.event.WindowAdapter() {
		    	 public void windowClosing(java.awt.event.WindowEvent e) {
		    		 jf.dispose();
		       }
		     });
		 
		     jf.setVisible(true);
		     tv.fitToScreen();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	public static void main(String[] args){
		VisualizeJ48 viz = new VisualizeJ48("clinicogenomic_weka280217.arff");
		viz.visualize();
	}
}

