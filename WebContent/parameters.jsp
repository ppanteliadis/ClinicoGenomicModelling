<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="clinicogenomic.ClinicoGenomic" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Clinico Genomic Model - Clutering Parameters</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="csd, uoc, pavlos, panteliadis, george, potamias">
		<meta name="author" content="Ioannis, Pavlos, Panteliadis, George, Potamias">
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
		
		<link rel="stylesheet" type = "text/css" href="css/bootstrap.min.css">
		
		<script src="js/jquery.js"></script>
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
		<script>
			function validateForm() {
				var centroids = document.forms["myForm"]["centroids"].value;
				if (centroids < 2 || centroids == "") {
					alert("Centroids must be greater than 2");
					return false;
				}
				
				var filter_per_sample = document.form["myForm"]["filter_per_sample"].value;
				if (filter_per_sample < 0 || filter_per_sample > 100 || filter_per_sample == null || filter_per_sample == "") {
					alert("Filter per sample has to be (0, 100)");
					return false;
				}
				
				var filter_across_samples = document.form["myForm"]["filter_across_samples"].value;
				if (filter_across_samples < 0 || filter_across_samples > 100 || filter_across_samples == null ||filter_across_samples == "") {
					alert("Filter across samples has to be (0, 100)");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div class="navbar">
			<ul>
				<li><a href="index.jsp" ><img src="images/logo.png" alt="logo"></a></li>
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
				<h1>Clustering Parameters</h1>
				<% String gene_expr = ClinicoGenomic.getInstance().getGeneExpressionFileName(); %>
				<% String clinical = ClinicoGenomic.getInstance().getClinicalFileName(); %>
				<p>Your file:  <%=gene_expr %> is loaded!</p>
				<p>Your file: <%=clinical %> is loaded!</p>
			</header>
			<hr>
			<div class="row text-center">
				<div class="col-md-10 col-sm-6 hero-feature">
					<div class="thumbnail">
						<div class="caption">
							<form class="form-horizontal" method="GET" action="clusteringParameters" name="myForm" onsubmit="return validateForm()">
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="pwd">Centroids:</label>
							      <div class="col-sm-10">          
							        <input type="text" class="form-control" name="centroids" placeholder="Enter preferred number of centroids k>=2">
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="pwd">Filter:</label>
							      <div class="col-sm-10">          
							        <input type="text" class="form-control" name="filter_per_sample" placeholder="Filter per sample">
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="pwd">Filter:</label>
							      <div class="col-sm-10">          
							        <input type="text" class="form-control" name="filter_across_samples" placeholder="Filter across samples">
							      </div>
							    </div>
							    <div class="form-group">
							      <label class="control-label col-sm-2" for="pwd">Username:</label>
							      <div class="col-sm-10">          
							        <input type="text" class="form-control" name="username" placeholder="Enter username">
							      </div>
							    </div>
							    <div class="form-group">        
							      <div class="col-sm-offset-2 col-sm-10">
							      		<a href="upload.jsp" class="btn btn-default btn-lg">
     										<span class="glyphicon glyphicon-menu-left"></span> Back
     									</a>
        								<button type="submit" class="btn btn-info btn-lg">
        									<span class="glyphicon glyphicon-cluster"></span> Cluster!
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
		