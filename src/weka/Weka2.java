package weka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;


public class Weka2 {
	private final String WEKA_FILE_NAME;
	private final String WEKA_ABSOLUTE_PATH;
	
	
	public Weka2(){
		DateFormat df = new SimpleDateFormat("ddMMyy");
		Date dateobj = new Date();
		
		String parent_dir = System.getProperty("catalina.base");
		WEKA_ABSOLUTE_PATH = parent_dir + "/clinicogenomic_weka" + df.format(dateobj) + ".arff";
		WEKA_FILE_NAME = "clinicogenomic_weka" + df.format(dateobj) + ".arff";
	}
	
	
	public String getWekaAbsolutePath() {
		return WEKA_ABSOLUTE_PATH;
	}
	
	public String getWekaFileName() {
		return WEKA_FILE_NAME;
	}

	public void runWeka(){
		System.out.println("Generating J48 Decision Tree...");
		try {
			BufferedReader train_reader = new BufferedReader(new FileReader(this.WEKA_FILE_NAME));
			//BufferedReader test_reader = new BufferedReader(new FileReader(this.test_file));
			
			Instances train_data = new Instances(train_reader);
			train_data.setClassIndex(train_data.numAttributes() - 1);

			//Instances test_data = new Instances(test_reader);
			//test_data.setClassIndex(test_data.numAttributes() - 1);
			
			//test_reader.close();
			train_reader.close();
			
			
			J48 decision_tree = new J48();
			decision_tree.buildClassifier(train_data);
			
			Evaluation eval = new Evaluation(train_data);
			//10 times 10-fold cross-validation
			eval.crossValidateModel(decision_tree, train_data, 10, new Random(1));
			//eval.evaluateModel(decision_tree, test_data);
			
			System.out.println(eval.toSummaryString("\nWeka Results\n======\n", false));
			System.out.println(decision_tree);
			//System.out.println(eval.fMeasure(1) + " " +eval.precision(1) + " " + eval.recall(1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}