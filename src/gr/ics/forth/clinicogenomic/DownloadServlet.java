package gr.ics.forth.clinicogenomic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clinicogenomic.ClinicoGenomic;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String parent_dir = System.getProperty("catalina.base");
		String filename = ClinicoGenomic.getInstance().getModelName() + ".arff";
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
		
		PrintWriter out = response.getWriter();		

		FileInputStream fileInputStream = new FileInputStream(parent_dir + "/" + filename);  
        
		int i;   
		while ((i=fileInputStream.read()) != -1) {  
			out.write(i);   
		}   
		fileInputStream.close();   
		out.close();   
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
