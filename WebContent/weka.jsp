<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="clinicogenomic.ClinicoGenomic" %>
<%@ page import="clinicogenomic.Clinical" %>
<%@ page import="kmeans2.KMeans2" %>
<%@ page import="weka.Weka2" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Clinico Genomic Model - Weka</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="csd, uoc, pavlos, panteliadis, george, potamias">
		<meta name="author" content="Ioannis, Pavlos, Panteliadis, George, Potamias, Dimitris, Plexousakis">
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		
		<link rel="stylesheet" type = "text/css" href="css/bootstrap.min.css">
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
		
		
	</head>
	<body>
	<div class="navbar">
		<ul>
			<li><a href="index.jsp"><img src="images/logo.png" alt="logo"></a></li>
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
			<% String file_abs_path = ClinicoGenomic.getInstance().getWeka().getWekaAbsolutePath(); %>
			<% String filename = ClinicoGenomic.getInstance().getWeka().getWekaFileName(); %>
			<p>Your file:  <%=filename %> is loaded!</p>
		</header>
		
		<%ClinicoGenomic.getInstance().getWeka().runWeka(); %>
		
		
		<footer>
			<div class="row">
				<div class="col-lg-12">
					<p>Copyright &copy; ICS-FORTH</p>
				</div>
			</div>
		</footer>
	</div>
</html>