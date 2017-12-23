<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Marrker - Refer & Win.</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap.min.css" rel="stylesheet">

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="jumbotron-narrow.css" rel="stylesheet">


<link rel="stylesheet"
	href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="../../assets/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="container">
		<!--  <div class="header clearfix">
			<nav>
				<ul class="nav nav-pills pull-left">
					<li role="presentation" class="active"><a
						href="http://www.marrker.com">Marrker.com</a></li>
				</ul>
			</nav>
		</div>-->

		<div class="jumbotron">
			<h2>
				<i class="fa fa-gift" style="font-size: 32px"></i> Refer & Win
			</h2>
			<p class="lead">Get a referrer link, share with your friends and
				win free mobile recharge upto Rs 50/- everyday.</p>
				
			<p>
				<button type="button" class="btn btn-danger btn-md"
					data-toggle="modal" data-target="#myModal">Get Referrer
					link.</button>
			</p>
			<p><font size = "2">~ User with most visitors above 5 will be the winner ~</font></p>
			<c:if test="${not empty randid }"><b>Your Referrer URL: <a href="ref/${randid }">http://www.marrker.com/ref/${randid }</a></b></c:if>
		</div>

		<% 
		
		ArrayList<String> values = (ArrayList<String>)request.getAttribute("fRunners");
			if(values.size() > 0){
		
		%>
		<div class="row marketing">
			<table width="100%">
				<th colspan="2">
					<center>Today's Top Front Runners.</center>
				</th>
				<tr>
					<td><center><b>Name</b></center></td>
					<td><center><b>Visitors</b></center></td>
				</tr>
				<% for(int i= 0; i < values.size() ; i++) {%>
					<tr>
						<td><center><%= values.get(i).substring(0, values.get(i).lastIndexOf("^"))%></center></td>
						<td><center><%= values.get(i).substring(values.get(i).lastIndexOf("^") + 1, values.get(i).length())%></center></td>
					</tr>
				<%}%>
			</table>
		</div>
		<%} %>
	</div>
	<!-- /container -->

	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					* This offer is valid only for phone numbers in India.<br>
					<form name="uform" aciton="getRefCode" method="post">
						<table width="100%">
							<tr>
								<td>Name:</td>
								<td><input type="text" name="uname" /></td>
								<br>
								<td>Phone Number:</td>
								<td><input type="text" name="phno" /></td>
							</tr>
						</table>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-success" value="Get referrer link."/>
				</div>
				</form>
			</div>
		</div>
	</div>

	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="ie10-viewport-bug-workaround.js"></script>
	<script src="jquery.min.js"></script>
	<script src="bootstrap.min.js"></script>
</body>
</html>
