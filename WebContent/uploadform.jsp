<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ClinicoGenomic - Upload</title>
		<meta charset="UTF-8">
		<meta name="keywords" content="csd, uoc, pavlos, panteliadis, george, potamias">
		<meta name="author" content="Ioannis, Pavlos, Panteliadis, George, Potamias">
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		
		<link rel="stylesheet" type = "text/css" href="css/bootstrap.min.css">
		<!-- Bootstrap Core JavaScript -->
		<script src="js/bootstrap.min.js"></script>
		
		<script src="js/delim.js"></script>
		<script type="text/javascript">
			function delimiter_select() {
				location.reload(true);
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
	
	<div class="container">
		<!-- Jumbotron Header -->
		<header class="jumbotron hero-spacer">
			<h1>Upload your data</h1>
			<p>Please upload your data set as a .txt file.</p>
		</header>
		
		<hr>
		
		<!-- Page Features -->
		<div class="row text-center">
			<div class="col-md-10 col-sm-6 hero-feature">
				<div class="thumbnail">
					<div class="caption">
						<h3>Upload Center</h3>
						<form class="form-horizontal" action="uploadServlet" method="POST" enctype="multipart/form-data">
		  					<div class="form-group">
		    					<label class="control-label col-sm-2" for="gene_expression">Gene Expression File</label>
		    					<div class="col-sm-10">
		      						<input class="form-control-static" type="file" name="gene_expression"/>
		    					</div>
		  					</div>
		  					
		  					<div class="form-group">
		    					<label class="control-label col-sm-2" for="clinical">Clinical File</label>
		    					<div class="col-sm-10">
		      						<input class="form-control-static" type="file" name="clinical"/>
		    					</div>
		  					</div>
		  					
		  					<!--  <div class="form-group">
		    					<label class="col-sm-2 control-label" for="delimiter">Delimiter</label>
						        <div class="col-sm-2 selectContainer">
						            <select name="delimiter" class="form-control">
						                <option value="tab">Tab</option>
						                <option value="space">Space</option>
						                <option value="comma">Comma</option>
						            </select>
        						</div>
		  					</div>
		  					-->
		  					<div class="form-group">        
   								<div class="col-sm-offset-2 col-sm-10">
   									<a href="index.jsp" class="btn btn-default btn-lg">
     									<span class="glyphicon glyphicon-menu-left"></span> Back
     								</a>
       								<button type="submit" class="btn btn-info btn-lg" onclick="parameters.jsp">
       									<span class="glyphicon glyphicon-upload"></span> Upload
       								</button>
						      	</div>
						    </div>
						</form>
						
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>