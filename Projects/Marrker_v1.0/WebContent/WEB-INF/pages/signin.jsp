<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Welcome to {Marrker} &middot; Sign in.</title>
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta name="google-site-verification"
	content="rt35bDmHRtVk-o6etFm1ZF1-CCuTDvrx6r8_iYpDk98" />

<!-- Le styles -->
<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700'
	rel='stylesheet' type='text/css'>
<link href="assets/css/bootplus.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #e5e5e5;
}

.form-signin {
	border: 1px solid #D8D8D8;
	border-bottom-width: 2px;
	border-top-width: 0;
	background-color: #FFF;
	max-width: 450px;
	padding: 19px 29px 30px;
	margin: 70px auto 20px;
	background-color: #fff;
	border: 1px solid #F5F5F5;
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

<body background="assets/img/cover.jpg">

	<div class="container">

		<form:form class="form-signin" action="signin" commandName="user"
			method="post">
			<c:if test="${not empty msg}">
				<div class="alert alert-success text-center">${msg}</div>
			</c:if>
			<c:choose>
				<c:when test="${not empty param.errors and param.errors == 1}">
					<div class="alert alert-danger">
						<li>Invalid username or password !</li>
					</div>
				</c:when>
				<c:when test="${not empty param.errors and param.errors == 7}">
					<div class="alert alert-danger">
						<li>Invalid username or password !</li>
						<li>Invalid Email address !</li>
					</div>
				</c:when>
			</c:choose>
			<h3 align="center">
				Welcome to {Marrker}<sup><font size="2"> <b>BETA</b></font></sup>.
			</h3>
			<h5 align="right" class="muted">
				<i><b>(~Can't stop sharing..! -@{Marrker}.com.)</b></i>
			</h5>
			<fieldset>
				<legend>Please Signin</legend>
				<label>Email</label> <input type="text" class="input-block-level"
					placeholder="Email address" autofocus name="email"> <label>Password</label>
				<input type="password" class="input-block-level"
					placeholder="Password" name="password">
			</fieldset>
			<button class="btn btn-danger pull-left btn-block" type="submit">
				<i class="icon-circle-arrow-right"></i> Signin.
			</button>
			<a class="btn btn-primary pull-right btn-block" href="main"> <i
				class="icon-circle-arrow-left"></i> Back
			</a>
			<%-- <a class="btn btn-info btn-block"
				onclick="javascript: alert('Signup requires invitation from a signed up user !');">
				<i class="icon-circle-arrow-right"></i> Signup.
			</a>--%>
			<a class="btn btn-link btn-block"
				onclick="javascript: windowPopup('forgotpassword', 'Forgot Password');">
				Forgot Password. </a>
			 <br><br>
			<br>
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
	<script src="assets/js/windowpopup.js"></script>
	<%-- <script src="assets/js/ants.js"></script> --%>

</body>
</html>
