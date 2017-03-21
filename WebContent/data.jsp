
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
				<li><a href="index.jsp" ><img src="images/logo.png" alt="logo"></a></li>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="about.jsp">About</a></li>
				<li><a href="data.jsp">Data</a></li>
				<li><a href="help.jsp">Help</a></li>
			</ul>
		</div>
		<!-- Page Content -->
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h3>Data</h3>
				</div>
			</div>
			<!-- Jumbotron Header -->
			<header class="jumbotron hero-spacer">
				<h1>Okay... </h1>
				<h2>But what kind of data can this software analyze? </h2>
				<p>Here's a functional example to run. Create a <strong>tab-delimited</strong> file that looks like the following</p>
				<p>This will be your <strong>GeneExpression</strong> file</p>
			</header>
			<table class="table table-bordered">
			    <thead>
			        <tr>
			            <th>sample_ID</th>
			            <th>Sample_1</th>
			            <th>Sample_2</th>
			            <th>Sample_3</th>
			            <th>Sample_4</th>
			            <th>Sample_5</th>
			            <th>...</th>
			            <th>Sample_M</th>
			        </tr>
			    </thead>
			    <tbody>
			        <tr>
			            <td>Probe_id_1</td>
			            <td>1</td>
			            <td>1</td>
			            <td>2</td>
			            <td>1</td>
			            <td>3</td>
			            <td>...</td>
			            <td>2</td>
			        </tr>
			        <tr>
			            <td>Probe_id_2</td>
			            <td>1</td>
			            <td>3</td>
			            <td>3</td>
			            <td>2</td>
			            <td>1</td>
			            <td>...</td>
			            <td>1</td>
			        </tr>
			        <tr>
			            <td>Probe_id_3</td>
			            <td>2</td>
			            <td>1</td>
			            <td>2</td>
			            <td>3</td>
			            <td>2</td>
			            <td>...</td>
			            <td>3</td>
			        </tr>
			        <tr>
			            <td>Probe_id_4</td>
			            <td>3</td>
			            <td>1</td>
			            <td>2</td>
			            <td>2</td>
			            <td>1</td>
			            <td>...</td>
			            <td>2</td>
			        </tr>
			        <tr>
			        	<td>...</td>
			        	<td>...</td>
			        	<td>...</td>
			        	<td>...</td>
			        	<td>...</td>
			        	<td>...</td>
			        	<td>...</td>
			        	<td>...</td>
			        </tr>
			        <tr>
			        	<td>Probe_id_N</td>
			        	<td>3</td>
			        	<td>1</td>
			        	<td>3</td>
			        	<td>2</td>
			        	<td>1</td>
			        	<td>...</td>
			        	<td>2</td>
			        </tr>
			    </tbody>
			</table>
			<hr>
			<header class="jumbotron hero-spacer">
				<p>Now, create a <strong>tab-delimited</strong> file that looks like the following</p>
				<p>This will be your <strong>Clinical</strong> file</p>
			</header>
			
			<table class="table table-bordered">
			    <thead>
			        <tr>
			            <th>sample_ID</th>
			            <th>Sample_1</th>
			            <th>Sample_2</th>
			            <th>Sample_3</th>
			            <th>Sample_4</th>
			            <th>Sample_5</th>
			            <th>...</th>
			            <th>Sample_M</th>
			        </tr>
			    </thead>
			    <tbody>
			        <tr>
			            <td>class</td>
			            <td>good</td>
			            <td>bad</td>
			            <td>good</td>
			            <td>good</td>
			            <td>bad</td>
			            <td>...</td>
			            <td>good</td>
			        </tr>
			        <tr>
			            <td>age</td>
			            <td>age_69_84</td>
			            <td>age_54_69</td>
			            <td>age_69_84</td>
			            <td>age_great_84</td>
			            <td>age_0_39</td>
			            <td>...</td>
			            <td>age_great_84</td>
			        </tr>
			        <tr>
			            <td>size</td>
			            <td>size_le_20</td>
			            <td>size_20_50</td>
			            <td>size_great_50</td>
			            <td>size_20_50</td>
			            <td>size_20_50</td>
			            <td>...</td>
			            <td>size_great_50</td>
			        </tr>
			        <tr>
			            <td>lymph</td>
			            <td>lymph_0</td>
			            <td>lymph_2_3</td>
			            <td>lymph_1</td>
			            <td>lymph_6_9</td>
			            <td>lymph_great_10</td>
			            <td>...</td>
			            <td>lymph_6_9</td>
			        </tr>
			        <tr>
			            <td>grade</td>
			            <td>g_1</td>
			            <td>g_1</td>
			            <td>g_1</td>
			            <td>g_3</td>
			            <td>g_2</td>
			            <td>...</td>
			            <td>g_3</td>
			        </tr>
			        <tr>
			            <td>histo</td>
			            <td>idc</td>
			            <td>ilc</td>
			            <td>idc_and_ilc</td>
			            <td>other_invasive</td>
			            <td>idc_med</td>
			            <td>...</td>
			            <td>idc_and_ilc</td>
			        </tr>
			        <tr>
			            <td>erIHC</td>
			            <td>neg</td>
			            <td>pos</td>
			            <td>pos</td>
			            <td>neg</td>
			            <td>pos</td>
			            <td>...</td>
			            <td>pos</td>
			        </tr>
			        <tr>
			            <td>erEXP</td>
			            <td>neg</td>
			            <td>neg</td>
			            <td>pos</td>
			            <td>neg</td>
			            <td>pos</td>
			            <td>...</td>
			            <td>neg</td>
			        </tr>
			        <tr>
			            <td>her2SNP6</td>
			            <td>neut</td>
			            <td>gain</td>
			            <td>loss</td>
			            <td>gain</td>
			            <td>neut</td>
			            <td>...</td>
			            <td>gain</td>
			        </tr>
			        <tr>
			            <td>her2EXP</td>
			            <td>neg</td>
			            <td>pos</td>
			            <td>pos</td>
			            <td>neg</td>
			            <td>pos</td>
			            <td>...</td>
			            <td>pos</td>
			        </tr>
			        <tr>
			            <td>chemo</td>
			            <td>no</td>
			            <td>no</td>
			            <td>yes</td>
			            <td>yes</td>
			            <td>yes</td>
			            <td>...</td>
			            <td>yes</td>
			        </tr>
			        <tr>
			            <td>horm</td>
			            <td>yes</td>
			            <td>no</td>
			            <td>yes</td>
			            <td>yes</td>
			            <td>no</td>
			            <td>...</td>
			            <td>yes</td>
			        </tr>
			        <tr>
			            <td>npi</td>
			            <td>excellent</td>
			            <td>moderate</td>
			            <td>good</td>
			            <td>excellent</td>
			            <td>poor</td>
			            <td>...</td>
			            <td>excellent</td>
			        </tr>
			        <tr>
			            <td>cellularity</td>
			            <td>low</td>
			            <td>moderate</td>
			            <td>moderate</td>
			            <td>high</td>
			            <td>low</td>
			            <td>...</td>
			        	<td>high</td>
			        </tr>
			        <tr>
			            <td>pam50</td>
			            <td>basal</td>
			            <td>her2</td>
			            <td>nc</td>
			            <td>lumA</td>
			            <td>lumB</td>
			            <td>...</td>
			            <td>normal</td>
			        </tr>
			    </tbody>
			</table>
			
			
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
