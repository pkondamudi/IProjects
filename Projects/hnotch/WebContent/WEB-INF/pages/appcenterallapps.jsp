<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>app center - hnotch</title>
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href='http://fonts.googleapis.com/css?family=Roboto:400,300,700'
	rel='stylesheet' type='text/css'>
<link href="assets/css/bootplus.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.hero-unit {
	padding: 60px;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}

.navbar-custom {
	color: #FFFFFF;
	background-color: #30A3DA;
}

.navbar-text-custom {
	margin-bottom: 0;
	line-height: 44px;
	color: #FFFFFF;
}

.navbar-link-custom {
	color: #FFFFFF;
}
</style>
<link href="assets/css/bootplus-responsive.css" rel="stylesheet">

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

	<div
		class="navbar navbar-default navbar-custom navbar-fixed-top navbar-inverse">
		<div class="navbar-inner navbar-custom">
			<div class="container-fluid">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand" href="home"><img src="assets/img/logo.jpg"
					width="100" height="25" border="0" alt="hnotch"> <i> app
						center</i></a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li class="active"><a href="home">Home</a></li>
					</ul>
					<ul class="nav pull-right">
						<!-- <li class="dropdown dropdown-notification">
                         <a href="#" class="dropdown-toggle btn-notification" data-toggle="dropdown">
                            8
                         </a>
                            <ul class="dropdown-menu dropdown-notifications">
                               <li class="header pull-left" style = "color: #000000">Notifications</li>
					  <br><hr>
                               <li>
                                  <span class="title">Notification title</span>
                                  <div class="media">
                                     <a class="pull-left" href="#">
                                        <img class="media-object" data-src="holder.js/48x48/#fff:#444" alt="48x48" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAACs0lEQVRoQ+2YP0hyYRTGHwl1CHEIXDPUIQgpaQrBHKJAQlwbpaA1isAkzD8VBulmgyAEDemgEDQ4KOhYDuEotNSQCJIIQZFpvAeS6KvP7ATy8b0u13u559zzPL9zzkUVnU6ng3/4o5ACBkxPEhgwAEgCkgDTAdlCTAPZ4ZIA20JmAkmAaSA7XBJgW8hMIAkwDWSH/z8EstksIpEIdnd3MT09DfFLtFgs4ujoCPf397BYLFhbW4NOp/u2q7+R81sEqtUqNjc3cXd3h/39fRJwc3ND1xYWFrC4uAiv14vR0VFsbGxAqVT2FPFbOXsKeHl5QTwex9nZGZ6fn7sCSqUSPB4PnU9MTCAYDKJeryMcDqNSqcDv92NpaQlOpxOhUAiNRoOOIyMj+ElOrVb7qSk9BZTLZezt7WFubg6np6ddAbVajQjMzs4She3tbUxOTmJ1dZXaKxqN4urqClarFefn59ja2sLMzAwV8ZOcQ0ND/Qt4eHgg14xGI8xmMxXx1kKiyFwuh8PDQ7RaLRgMBgQCge4MXF9fk8BmswmbzUbfVSoVODk/U/BXAmLI0uk0tYfo+beWETMg2kQUtbKyQhSEUCFkZ2cHw8PD1G6inQqFAgm32+30fE7OvgQ8Pj5S4RcXF3/ECQq3t7c4OTnBwcEBuZ9MJpFKpbrnl5eXNAdPT09EUAgUwjg5+xLw8eb3QysIiP4Wzi4vL2N+fp4KFO0h1my73aatpFar4XA4aP26XC643W4oFIpu6n5yajSa/mfgfcTHh4kiRTscHx/Te8BkMmF9fR16vR6ZTAaJRAI+nw9TU1OIxWLI5/M0P+Pj418K+Crn2NjYl2u55xbqudAHfIMUMGAA8p+5QQOQBCQBrgNyjXId5MZLAlwHufGSANdBbrwkwHWQGy8JcB3kxksCXAe58a8YGGyPRqBrNwAAAABJRU5ErkJggg==" style="width: 48px; height: 48px;">
                                     </a>
                                     <div class="media-body">
                                       Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore.
                                     </div>
                                  </div>
                               </li>
                            </ul>
                   	</li> -->
						<!-- <li class="active"><a href="logout"><b>Logout</b></a></li>  -->
						<li>
							<div class="btn-group btn-block">
								<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">${sessionScope.User.emailaddress }
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="profile" style="text-align: left">Profile</a>
									</li>
									<li><a href="#" style="text-align: left"
										onclick="javascript: windowPopup('changepassword', 'Change Password');">Change
											Password</a></li>
									<li><a href="logout" style="text-align: left">Logout</a></li>
								</ul>
							</div>
						</li>
						</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>


	<div class="container-fluid">
		<div class="row-fluid" style="margin-top: 25px">
			<div class="span3">
				<div class="sidebar-nav">
					<ul class="nav nav-list">
						<li class="nav-header">App Center<br> <br>
							<form class="form-search" action="searchapps">
								<input type="text" class="input-small search-query"
									style="width: 50%">
								<button type="submit" class="btn">
									<i class="icon-search"></i>
								</button>
							</form>
						</li>
						<li><a href="appcenter">My Apps</a></li>
						<li class="active"><a href="allapps">All Apps</a></li>
					</ul>
				</div>
				<!--/.well -->
			</div>
			<!--/span-->

			<div class="span9">
				<c:choose>
					<c:when test="${not empty param.msg && param.msg == 11}">
						<div class="alert alert-success text-center">Application
							installed sucessfully !</div>
					</c:when>
				</c:choose>
				<c:choose>
					<c:when test="${fn:length(allApplications) > 0}">
						<c:forEach var="applications" items="${allApplications }">
							<div class="row-fluid">
								<div class="span12">
									<div class="card">
										<div class="card-heading image">
											<img
												src="assets/img/${applications.attributes['LAUNCH_ICON_NAME']}"
												width="64" height="64" border="0" alt="">
											<div class="card-heading-header">
												<h3>${applications.applicationName }</h3>
												<span>Last Updated - ${applications.timestamp }</span><br>
												<a class="btn btn-small btn-block btn-success"
													href="app/install/${applications.applicationId}">Install</a>
											</div>
										</div>
										<div class="card-body">
											<p style="padding: 50px 50px 30px 50px">${applications.applicationDescription }</p>
										</div>
										<div class="card-comments">
											<div class="comments-collapse-toggle">
												<a data-toggle="collapse"
													data-target="#${applications.applicationId }-comments"
													href="#${applications.applicationId }-comments">${fn:length(applications.comments) }
													comments <i class="icon-angle-down"></i>
												</a>
											</div>
											<div id="${applications.applicationId }-comments"
												class="comments collapse">
												<div class="media">
													<!-- <a class="pull-left" href="#">
									   <img src="assets/img/3.png" width="46" height="46" border="0" alt="">
									</a> -->
													<div class="media-body">
														<h4 class="media-heading">Write your comment:</h4>
														<p>
															<form:form method="post"
																action="app/comment/${applications.applicationId }"
																commandName="applicationComment">
																<form:textarea class="input-block-level" name=""
																	rows="3" cols="" path="Comment"></form:textarea>
																<input type="submit" class="btn btn-primary">
															</form:form>
														</p>
													</div>
												</div>
												<hr>
												<div class="media">
													<!-- <a class="pull-left" href="#">
									   <img src="assets/img/3.png" width="46" height="46" border="0" alt="">
									</a> -->
													<c:forEach var="comment" items="${applications.comments }">
														<div class="media-body">
															<p>${comment.comment }</p>
															<c:if test="${comment.user.userId == userModel.userId }">
																<p>
																	<a class="btn"
																		href="app/comment/delete/${comment.commentId }">Delete
																		comment</a>
																</p>
															</c:if>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
						</c:forEach>
						<ul class="pager">
							<li class="previous"><a href="allapps?page=previous">&larr;
									Previous</a></li>
							<li class="next"><a href="allapps?page=next">Next &rarr;</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<div class="card">
							<div class="card-body">
								<p style="padding: 0px 0px 10px 0px">No application found.</p>
							</div>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<!--/span-->
		</div>
		<!--/row-->


		<div id="footer">
			<div class="container navbar-fixed-bottom">
				<p class="muted credit">
					Example courtesy <a href="http://martinbean.co.uk">Martin Bean</a>
					and <a href="http://ryanfait.com/sticky-footer/">Ryan Fait</a>.
				</p>
			</div>
		</div>

	</div>
	<!--/.fluid-container-->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript">
	<!--
		$("button").click(function() {
			var $btn = $(this);
			$btn.button('loading');
			// Then whatever you actually want to do i.e. submit form
			// After that has finished, reset the button state using
			setTimeout(function() {
				$btn.button('reset');
			}, 1000);
		});
	//-->
	</script>
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

</body>
</html>
