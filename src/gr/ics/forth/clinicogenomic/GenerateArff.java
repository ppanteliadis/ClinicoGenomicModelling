package gr.ics.forth.clinicogenomic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kmeans2.Cluster;
import kmeans2.KMeans2;
import weka.Weka2;
import clinicogenomic.Clinical2;
import clinicogenomic.ClinicoGenomic;

/**
 * Servlet implementation class GenerateArff
 */
@WebServlet("/generateArff")
public class GenerateArff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateArff() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletConfig()
	 */
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String checkboxValues = request.getParameter("vehicle");
		System.out.println("checkboxValues = " + checkboxValues);
		// build HTML code
		System.out.println("Generating .arff");
		List<Cluster> my_clusters = ClinicoGenomic.getInstance().getClusters();
		
		KMeans2 km = ClinicoGenomic.getInstance().getKmeans();
		Clinical2 cl = ClinicoGenomic.getInstance().getClinical();
		float filter1 = ClinicoGenomic.getInstance().getFilterPerSample();
		float filter2 = ClinicoGenomic.getInstance().getFilterAcrossSamples();
		
		ClinicoGenomic.getInstance().generateWeka(km, my_clusters, cl, filter1, filter2);
		ClinicoGenomic.getInstance().setWeka(new Weka2());
		
		//Download the file 
		System.out.println("Beginning download.");
		
		String file_abs_path = ClinicoGenomic.getInstance().getWeka().getWekaAbsolutePath();
		String filename = ClinicoGenomic.getInstance().getWeka().getWekaFileName();
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
		
		PrintWriter download = response.getWriter();
		
		FileInputStream fi = new FileInputStream(file_abs_path);
		int i;
		while ((i = fi.read())!=-1)
			download.write(i);
		
		download.close();
		fi.close();
        System.out.println("File downloaded at client successfully");
        // build HTML code
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
