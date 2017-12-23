<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
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

<title>Marrker - add Topic.</title>

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
			<p>
				<button type="button" class="btn btn-danger btn-md"
					data-toggle="modal" data-target="#myModal">Add a topic</button>
			</p>
		</div>

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
					<form name="uform" action="PutTopic" method="post">
						<table width="100%" colspacing="2">
							<tr>
								<td>Search Terms:</td>
								<td><input type="text" name="sterms" /></td>
							</tr>
							<tr>
								<td>Context Board Name:</td>
								<td><input type="text" name="ctxname" /></td>
							</tr>
							<tr>
								<td>Context Board Description:</td>
								<td><input type="text" name="ctxdessc" /></td>
							</tr>
							<tr>
								<td>UserId:</td>
								<td><select name="uid">
										<option value="">MarrkerBot - International</option>
										<option value="">MarrkerBot - Unite States</option>
										<option value="86f3db61-0fcc-11e6-8690-f0def15edb3c" selected>MarrkerBot - India</option>
									</select></td>
									
								<%-- <td><select name="uid">
										<option value="">MarrkerBot - Intl</option>
										<option value="">MarrkerBot - US</option>
										<option value="86f3db61-0fcc-11e6-8690-f0def15edb3c">MarrkerBot - IN</option>
									</select></td>--%>
							</tr>
						</table>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-success"
						value="Create topic" />
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
