<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Forgot Password</title>
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700'
	rel='stylesheet' type='text/css'>
<link href="assets/css/bootplus.css" rel="stylesheet">
<link href="assets/dropzone/dropzone.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 20px;
	padding-bottom: 20px;
	background-color: #30A3DA;
}

.form-signin {
	border: 1px solid #D8D8D8;
	border-bottom-width: 2px;
	border-top-width: 0;
	background-color: #FFF;
	padding: 40px 29px 75px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #F5F5F5;
	margin-top: 7%;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}

.form-signin .form-signin-heading {
	font-size: 24px;
	font-weight: 300;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"], .form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
<link href="assets/css/bootplus-responsive.css" rel="stylesheet">
<link href="assets/css/font-awesome-ie7.min.css" rel="stylesheet">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="assets/js/html5shiv.js"></script>
    <![endif]-->

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="assets/js/html5shiv.js"></script>
      <![endif]-->

<!-- Fav and touch icons -->
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
<link rel="shortcut icon" href="assets/ico/favicon.png">
</head>

<body>

	<div class="container">

		<form:form class="form-signin" action="forgotpassword" method="post"
			commandName="User">
			<h2 class="form-signin-heading text-center">Password reset.</h2>
			<c:choose>
				<c:when test="${not empty param.errors and param.errors == 1}">
					<div class="alert alert-danger text-center">
						Invalid Emailaddress !
					</div>
				</c:when>
				<c:when test="${not empty param.errors and param.errors == 99}">
					<div class="alert alert-danger text-center">
						You are not a hnotch user !
					</div>
				</c:when>
				
				<c:when test="${not empty param.errors and param.errors == 87}">
					<div class="alert alert-success text-center">
						Email successfully sent !
					</div>
				</c:when>
			</c:choose>
		Email
        <form:input type="text" class="input-block-level"
				placeholder="Email address" path="Emailaddress"
				autofocus="autofocus" />
			<button class="btn btn-info" type="submit">
				Send Password <i class="icon-circle-arrow-right"></i>
			</button>
		</form:form>

	</div>
	<!-- /container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap-transition.js"></script>
	<script src="assets/js/bootstrap-alert.js"></script>
	<script src="assets/js/bootstrap-modal.js"></script>
	<script src="assets/js/bootstrap-dropdown.js"></script>
	<script src="assets/js/bootstrap-scrollspy.js"></script>
	<script src="assets/js/bootstrap-tab.js"></script>
	<script src="assets/js/bootstrap-tooltip.js"></script>
	<script src="assets/js/bootstrap-popover.js"></script>
	<script src="assets/js/bootstrap-button.js"></script>
	<script src="assets/js/bootstrap-collapse.js"></script>
	<script src="assets/js/bootstrap-carousel.js"></script>
	<script src="assets/js/bootstrap-typeahead.js"></script>
	<script src="assets/dropzone/dropzone.js"></script>
	<script>
		$("div#appupload").dropzone();
		Dropzone.options.appupload = {
			url : "upload",
			paramName : "file", // The name that will be used to transfer the file
			maxFilesize : 2, // MB
			clickable : false,
			success : function(file, response) {
				document.getElementById("message").innerHTML = response.message;
			}
		};
	</script>
</body>
</html>
