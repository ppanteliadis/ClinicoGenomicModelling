<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="clinicogenomic.ClinicoGenomic" %>
<%@ page import="kmeans2.KMeans2" %>
<%@ page import="clinicogenomic.Clinical2" %>
<!DOCTYPE html "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Clinico Genomic Model - Manage Clusters</title>
		<meta name="keywords" content="csd, uoc, pavlos, panteliadis, george, potamias">
		<meta name="author" content="Ioannis, Pavlos, Panteliadis, George, Potamias">
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		
		<link rel="stylesheet" type = "text/css" href="css/bootstrap.min.css">
		
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
		
		<script src="https://code.jquery.com/jquery-3.1.1.min.js" defer></script>
  		<script src="https://code.jquery.com/jquery-3.1.1.min.js" defer></script>
  		<script src="dist/js/bootstrap-checkbox.min.js" defer></script>
  		<script src="js/sendToServer.js"></script>
		<script type="text/javascript">
			function selectCluster(cluster_id) {
				postToServer(parseInt(cluster_id));
			}
		</script>
		
		<script src="js/reclusterset.js"></script>
		<script type="text/javascript">
			function reCluster(cluster_id) {
				recluster();
			}
		</script>
		<script type="text/javascript">
			function refresh() {
				location.reload(true);
			}
		</script>
	</head>
	<body>
		<div class="navbar">
			<ul>
				<li><a href = "index.jsp" ><img src="images/logo.png" alt="logo"></a></li>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="about.jsp">About</a></li>
				<li><a href="data.jsp">Data</a></li>
				<li><a href="help.jsp">Help</a></li>
			</ul>
		</div>
	
	<!-- Page Content -->
		<div class="container">
			<!-- Jumbotron Header -->
			<header class="jumbotron hero-spacer">
				<h1>Your files have been processed!</h1>
				<% KMeans2 km = ClinicoGenomic.getInstance().getKmeans();%>
				<% Clinical2 cl = ClinicoGenomic.getInstance().getClinical();%>
				<p>Input clusters: <%=km.k%></p>
				<p>Per-sample threshold: <%=ClinicoGenomic.getInstance().getFilterPerSample()%> %</p>
				<p>Across-samples threshold: <%=ClinicoGenomic.getInstance().getFilterAcrossSamples()%> %</p>
				<p>Samples: <%=km.dimensions %></p>
				<p>Genes per sample: <%=km.allPoints.size()%>
			</header>
		<hr>
		<form class="form-horizontal" method="GET" action="ClusterSelectionServlet">
		<%
			DecimalFormat df4 = new DecimalFormat(".####");
			for (int i = 0; i < km.k; ++i) {
				int cluster_size = ClinicoGenomic.getInstance().getClusters().get(i).getPoints().size();
				int cluster_id = ClinicoGenomic.getInstance().getClusters().get(i).getClusterId();
				
				float cluster_percentage = ClinicoGenomic.getInstance().getClusters().get(i).getClusterPercentage();
				float threshold1 = ClinicoGenomic.getInstance().getFilterPerSample();
				float threshold2 = ClinicoGenomic.getInstance().getFilterAcrossSamples();
				
				boolean cluster_filter = ClinicoGenomic.getInstance().getClusters().get(i).passFilter();
				int cluster_value = ClinicoGenomic.getInstance().getClusters().get(i).getClusterValue();
				

				double sample_highscore = ClinicoGenomic.getInstance().getClusters().get(i).getMaxSampleCoverage();
				

				double sample_lowscore = ClinicoGenomic.getInstance().getClusters().get(i).getMinSampleCoverage();
				if (cluster_filter) {%>
					<div class="col-md-4">
						<div class="thumbnail">
							<div class="caption">
								<p>
									<strong>Cluster<%=cluster_id %></strong>: <strong><%=cluster_size%></strong> points (genes)
									<br>
									Samples coverage: <strong><%=df4.format(cluster_percentage)%> % </strong>
									<%switch (cluster_value){
										case 1:%> (Low) <%break;
										case 2:%> (Moderate) <%break;
										case 3:%> (High) <%break;
									}%>
									<br>
									Filter test: 
									<strong style="color:#005A31;">PASS</strong>
									<hr>
									Max sample coverage: <%
									if (sample_highscore >= threshold2) {
										%><strong><%=df4.format(sample_highscore)%> %</strong><%
									}
									else {
										%><%=df4.format(sample_highscore)%> %<%
									}
									%>
									<br>
									Min sample coverage:
									<%=df4.format(sample_lowscore)%> % <%
									%>
									<div class="checkbox" id="<%=cluster_id%>">
										<label>
											<input type="checkbox" name="selections" value="<%=cluster_id%>" onclick="selectCluster('<%=cluster_id%>')">
										</label>
									</div>
								</p>
							</div>
						</div>
					</div>
				<%} 
			}%>
		</form>
		<!-- Page Features -->
		<div class="row text-center">
			<div class="col-md-10 col-sm-6 hero-feature">
				<div class="thumbnail">
					<div class="caption">
						<h3>You can now generate the .arff file</h3>
						<form class="form-horizontal" action="generateArff" method="GET" enctype="multipart/form-data">
		  					<div class="form-group">        
     							<div class="col-sm-offset-2 col-sm-9">
     								<a href="parameters.jsp" class="btn btn-default btn-lg">
     									<span class="glyphicon glyphicon-menu-left"></span> Back
     								</a>
     								<a href="#" data-toggle="tooltip" title="Recluster data with the same settings">
	     								<button type="button" class="btn btn-default btn-lg" onclick="reCluster();">
	     									<span class="glyphicon glyphicon-refresh"></span> Re-Cluster
	     								</button>
     								</a>
     								
       								<button type="submit" class="btn btn-info btn-lg">
       									<span class="glyphicon glyphicon-download"></span> Generate .arff
       								</button>
						      	</div>
						    </div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; ICS-FORTH</p>
				</div>
			</div>
		</footer>
	</div>
	</body>
</html>