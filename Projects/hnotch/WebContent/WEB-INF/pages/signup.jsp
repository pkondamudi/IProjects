<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
   <head>
      <meta charset="utf-8">
      <title>Create Account - hnotch</title>
      <!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
      <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta name="description" content="">
      <meta name="author" content="">

    <!-- Le styles -->
      <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700' rel='stylesheet' type='text/css'>
      <link href="assets/css/bootplus.css" rel="stylesheet">
      <link href="assets/css/font-awesome.min.css" rel="stylesheet">
      <style type="text/css">
      body {
        padding-top: 40px;
        padding-bottom: 40px;
        background-color: #30A3DA;
      }

      .form-signin {
         border: 1px solid #D8D8D8;
         border-bottom-width: 2px;
         border-top-width: 0;
         background-color: #FFF;
         max-width: 500px;
        padding: 19px 29px 29px;
        margin: 0px auto 0px;
        background-color: #fff;
        border: 1px solid #F5F5F5;
		margin-top:0px;
        -webkit-border-radius: 3px;
           -moz-border-radius: 3px;
                border-radius: 3px;
      }
      .form-signin .form-signin-heading {
         font-size: 24px;
         font-weight: 300;
      }

      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
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
      <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="assets/ico/favicon.png">
  </head>

  <body>

    <div class="container">
        <form:form class="form-signin" action = "signup" commandName = "User" method="post">
        <h2 class="form-signin-heading">Enter details</h2>
		<c:if test = "${not empty errors}">
			<div class = "alert alert-danger">
				<b>Errors occured:</b>
				<ul>
					<c:forEach var = "error" items = "${errors}">
						<li> ${error} </li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		Firstname
		<form:input type="text" class="input-block-level" placeholder="Firstname" path = "FirstName" value = "${FirstName }"/>
		LastName
		<form:input type="text" class="input-block-level" placeholder="Lastname" path = "LastName"  value = "${LastName }"/>
		Email address
        <form:input type="text" class="input-block-level" placeholder="Email address" path = "Emailaddress"  value = "${Emailaddress }"/>
		Password
        <form:input type="password" class="input-block-level" placeholder="Password" path = "Password" />
		Re-type Password
		<form:input type="password" class="input-block-level" placeholder="Re-type Password" path = "RePassword"/>
		Gender<br>
		<form:select  path = "Gender" class = "input-block-level">
			<form:option label = "Gender" value = "Gender"/>
			<form:option label = "Male" value = "Male"/>
			<form:option label ="Female" value = "Female"/>
		</form:select><br>
		<button class="btn btn-block btn-info"  type = "Submit">
           Create account
           <i class="icon-circle-arrow-right"></i>
        </button><br>
		<a class="btn btn-block btn-info" href="signin">
           Signin
           <i class="icon-circle-arrow-right"></i>
        </a><br>
      </form:form>

    </div> <!-- /container -->

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

  </body>
</html>
