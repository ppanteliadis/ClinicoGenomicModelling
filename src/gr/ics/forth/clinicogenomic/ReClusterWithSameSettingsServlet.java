package gr.ics.forth.clinicogenomic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clinicogenomic.Clinical2;
import clinicogenomic.ClinicoGenomic;
import kmeans2.Cluster;
import kmeans2.KMeans2;

/**
 * Servlet implementation class ReClusterWithSameSettings
 */
@WebServlet("/ReClusterWithSameSettingsServlet")
public class ReClusterWithSameSettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReClusterWithSameSettingsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		System.out.println("RECLUSTERING!");
		//get response writer
		PrintWriter writer = response.getWriter();
		

		int my_centroids = ClinicoGenomic.getInstance().getTotalCentroids();
		float my_filter_per_sample = ClinicoGenomic.getInstance().getFilterPerSample();
		float my_filter_across_samples = ClinicoGenomic.getInstance().getFilterAcrossSamples();
		String username = ClinicoGenomic.getInstance().getUsername();
		
		//Add the parameters to the model.
		ClinicoGenomic.getInstance().setFilterPerSample(my_filter_per_sample);
		ClinicoGenomic.getInstance().setFilterAcrossSamples(my_filter_across_samples);
		ClinicoGenomic.getInstance().setTotalCentroids(my_centroids);
		ClinicoGenomic.getInstance().setUsername(username);
		
		//Now it's safe to run the model.
		for (Cluster cluster : ClinicoGenomic.getInstance().getClusters()) {
			cluster.resetCluster();
		}
		
		//Load Kmeans
		KMeans2 kmeans = ClinicoGenomic.getInstance().getKmeans();
		kmeans.resetPointClusters();
		System.out.println(kmeans.getAllPoints().size());
		
		//KMeans2 kmeans = new KMeans2(my_centroids, file_dir1, "\t");
		//ClinicoGenomic.getInstance().setKmeans(kmeans);
		
		List<Cluster> clusters = ClinicoGenomic.getInstance().filterClusters(kmeans, my_filter_per_sample, my_filter_across_samples);
		ClinicoGenomic.getInstance().setClusters(clusters);
		
		//Load Clinical
		//String file_dir2 = parent_dir + "/" + ClinicoGenomic.getInstance().getClinicalFileName();
		Clinical2 cl = ClinicoGenomic.getInstance().getClinical();
		ClinicoGenomic.getInstance().setClinical(cl);
		
		// build HTML code
		System.out.println("Redirecting...");
		//response.sendRedirect("clusters.jsp");
        // return response, basically sending us to the next step which is to generate the clusters.
        //Send a message to the AJAX request which called this Servlet in order to inform it that you are done. 
		// catch it the success block.
        writer.print("refresh page");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
