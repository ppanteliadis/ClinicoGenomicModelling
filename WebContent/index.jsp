<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="clinicogenomic.ClinicoGenomic" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Clinico Genomic Model</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="csd, uoc, pavlos, panteliadis, george, potamias">
		<meta name="author" content="Ioannis, Pavlos, Panteliadis, George, Potamias">
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
		
		<link rel="stylesheet" type = "text/css" href="css/bootstrap.min.css">
		
		<script src="js/jquery.js"></script>
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
		
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
				<h1>A Warm Welcome!</h1>
				<p>Welcome to the website of Clinico Genomic Modelling. </p>
				<p>This website and the software that created it, is the thesis implementation of <strong>Ioannis Pavlos Panteliadis</strong></p>
				<p>Hosted by the <a href="http://www.forth.gr/" target="_blank" title="Open in new window">Foundation 
	            for Research and Technology - Hellas (FORTH)</a> and the <a href="http://www.csd.uoc.gr/" target="_blank">Computer Science Department</a> of University of Crete</p>
				<p><a class="btn btn-primary btn-large" href="uploadform.jsp">
				<% ClinicoGenomic.resetInstance(); %>Let's Start!</a>
				</p>
			</header>

			<hr>

			<!-- Title -->
			<div class="row">
				<div class="col-lg-12">
					<h3>Project Members</h3>
				</div>
			</div>
			<!-- /.row -->

			<!-- Page Features -->
			<div class="row text-center">
				<div class="col-md-3 col-sm-6 hero-feature">
					<div class="thumbnail">
						<div class="caption">
							<h3>Thesis Advisor</h3>
							<p>Prof. Dimitris Plexousakis</p>
							<p>
								<a href="http://www.ics.forth.gr/isl/index_main.php?l=e&c=223" target="_blank" class="btn btn-primary">Personal Page</a> <a href="http://users.ics.forth.gr/~potamias/GeorgePotamias.htm" class="btn btn-default">More Info</a>
							</p>
						</div>
					</div>
				</div>
				
				<div class="col-md-3 col-sm-6 hero-feature">
					<div class="thumbnail">
						<div class="caption">
							<h3>Co-Advisor</h3>
							<p>Prof. George Potamias</p>
							<p>
								<a href="http://users.ics.forth.gr/~potamias/GeorgePotamias.htm" target="_blank" class="btn btn-primary">Personal Page</a> <a href="http://users.ics.forth.gr/~potamias/GeorgePotamias.htm" class="btn btn-default">More Info</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-md-3 col-sm-6 hero-feature">
					<div class="thumbnail">
						<div class="caption">
							<h3>Developer</h3>
							<p>Ioannis Pavlos Panteliadis</p>
							<p>
								<a href="http://csd.uoc.gr/~panteliad/" target="_blank" class="btn btn-primary">Personal Page</a> <a href="http://csd.uoc.gr/~panteliad/" class="btn btn-default">More Info</a>
							</p>
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
