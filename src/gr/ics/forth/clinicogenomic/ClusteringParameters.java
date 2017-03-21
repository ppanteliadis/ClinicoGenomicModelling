package gr.ics.forth.clinicogenomic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmeans2.Cluster;
import kmeans2.KMeans2;
import clinicogenomic.Clinical2;
import clinicogenomic.ClinicoGenomic;

/**
 * Servlet implementation class ClusteringParameters
 */
@WebServlet("/clusteringParameters")
public class ClusteringParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClusteringParameters() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		String centroids = request.getParameter("centroids");
		String filter_per_sample = request.getParameter("filter_per_sample");
		String filter_across_samples = request.getParameter("filter_across_samples");
		String username = request.getParameter("username");
		
		int my_centroids = Integer.parseInt(centroids);
		float my_filter_per_sample = Float.parseFloat(filter_per_sample);
		float my_filter_across_samples = Float.parseFloat(filter_across_samples);
		
		//get response writer
		PrintWriter writer = response.getWriter();
		
		//Add the parameters to the model.
		ClinicoGenomic.getInstance().setFilterPerSample(my_filter_per_sample);
		ClinicoGenomic.getInstance().setFilterAcrossSamples(my_filter_across_samples);
		ClinicoGenomic.getInstance().setTotalCentroids(my_centroids);
		ClinicoGenomic.getInstance().setUsername(username);
		
		//Now it's safe to run the model.
		String parent_dir = System.getProperty("catalina.base");
		String file_dir1 = parent_dir + "/" + ClinicoGenomic.getInstance().getGeneExpressionFileName();
		
		//Load Kmeans
		KMeans2 kmeans = new KMeans2(my_centroids, file_dir1, "\t");
		ClinicoGenomic.getInstance().setKmeans(kmeans);
		
		List<Cluster> clusters = ClinicoGenomic.getInstance().filterClusters(kmeans, my_filter_per_sample, my_filter_across_samples);
		ClinicoGenomic.getInstance().setClusters(clusters);
		
		//Load Clinical
		String file_dir2 = parent_dir + "/" + ClinicoGenomic.getInstance().getClinicalFileName();
		Clinical2 cl = new Clinical2(file_dir2, "\t");
		ClinicoGenomic.getInstance().setClinical(cl);
		
		// build HTML code
		String htmlResponse = "<html><header><meta http-equiv=\"refresh\" content=\"0; URL='clusters.jsp\" /></header></html>";
         
        // return response, basically sending us to the next step which is to generate the clusters.
        writer.println(htmlResponse);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Got POST!");
	}

}
