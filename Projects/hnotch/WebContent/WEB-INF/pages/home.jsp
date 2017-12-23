<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Home</title>
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
				<a class="brand" href="home"><img src="assets/img/logo.jpg"
					width="100" height="25" border="0" alt="hnotch"> <i> the
						supersite. a supercommunity.</i></a>
				<div class="nav-collapse collapse">

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
                      </li>  -->

						<!-- <li class="active"><a href="logout"><b>Logout</b></a></li>  -->
						<li>
							<div class="btn-group btn-block pull-right">
								<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">${sessionScope.User.emailaddress }
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="profile" style="text-align: left">Profile</a></li>
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

	<div class="text-center">
		<div style="margin: 50px 100px 10px 100px">
			<form class="form-search">
				<input type="text" class="input-large search-query"
					style="width: 80%">
				<button type="submit" class="btn">
					<i class="icon-search"></i>
				</button>
			</form>
		</div>
	</div>
	<div class="row" style="margin: 30px 50px 20px 50px">
		<div class="span16">
			<div class="card">
				<button class="card-heading btn btn-danger pull-right"
					style="margin-right: 20px"
					onclick="javascript: windowPopup('upload', 'Upload app');">
					<span><i class="icon-cloud-upload"></i></span> Upload app
				</button>
				<h2 class="card-heading simple">Apps</h2>
				<div class="card-body">
					<div class="row text-center">
						<!--  <div class="span3 text-center" style="padding-bottom: 30px">
							<p>
								<img src="assets/img/11.png" width="80" height="80" border="0"
									alt="">
							</p>
							<p>App Store</p>
							<p>
							<div class="btn-group">
								<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Options
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="appcenter" style="text-align: left">Launch</a></li>
								</ul>
							</div>
							</p>
						</div>-->
						<div class="span3 text-center" style="padding-bottom: 30px">
							<p>
								<img src="assets/img/22.png" width="80" height="80" border="0"
									alt="">
							</p>
							<p>Super Blogs</p>
							<p>
							<div class="btn-group btn-block">
								<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Options
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#" style="text-align: left"><i
											class="icon-pencil"></i> Edit</a></li>
									<li><a href="#" style="text-align: left"><i
											class="icon-trash"></i> Delete</a></li>
									<li><a href="#" style="text-align: left"><i
											class="icon-ban-circle"></i> Ban</a></li>
									<li class="divider"></li>
									<li><a href="#" style="text-align: left"><i class="i"></i>
											Make admin</a></li>
								</ul>
							</div>
							</p>
						</div>
						<div class="span3 text-center" style="padding-bottom: 30px">
							<p>
								<img src="assets/img/33.png" width="80" height="80" border="0"
									alt="">
							</p>
							<p>Q &amp; A</p>
							<p>
							<div class="btn-group btn-block">
								<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Options
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#" style="text-align: left"><i
											class="icon-pencil"></i> Edit</a></li>
									<li><a href="#" style="text-align: left"><i
											class="icon-trash"></i> Delete</a></li>
									<li><a href="#" style="text-align: left"><i
											class="icon-ban-circle"></i> Ban</a></li>
									<li class="divider"></li>
									<li><a href="#" style="text-align: left"><i class="i"></i>
											Make admin</a></li>
								</ul>
							</div>
							</p>
						</div>
						<div class="span3 text-center" style="padding-bottom: 30px">
							<p>
								<img src="assets/img/55.png" width="80" height="80" border="0"
									alt="">
							</p>
							<p>News</p>
							<p>
							<div class="btn-group">
								<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Options
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="appcenter" style="text-align: left">Launch</a></li>
								</ul>
							</div>
							</p>
						</div>
						<div class="span3 text-center" style="padding-bottom: 30px">
							<p>
								<img src="assets/img/44.png" width="80" height="80" border="0"
									alt="">
							</p>
							<p>Videos</p>
							<p>
							<div class="btn-group btn-block">
								<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Options
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li><a href="#" style="text-align: left"><i
											class="icon-pencil"></i> Edit</a></li>
									<li><a href="#" style="text-align: left"><i
											class="icon-trash"></i> Delete</a></li>
									<li><a href="#" style="text-align: left"><i
											class="icon-ban-circle"></i> Ban</a></li>
									<li class="divider"></li>
									<li><a href="#" style="text-align: left"><i class="i"></i>
											Make admin</a></li>
								</ul>
							</div>
							</p>
						</div>
					</div>
					<!-- First Row -->

					<div class="row">
						<c:forEach var="application" items="${UserApplications}">
							<div class="span3 text-center" style="padding-bottom: 30px">
								<p class="text-center">
									<a href="${application.attributes['LAUNCH_APP_URL']}"><img
										src="assets/img/${application.attributes['LAUNCH_ICON_NAME']}"
										width="64" height="64" border="0" alt=""></a>
								</p>
								<p class="text-center">${application.applicationName}</p>
								<p class="text-center">
								<div class="btn-group btn-block pull-right">
									<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Options
										<span class="caret"></span>
									</a>
									<ul class="dropdown-menu">
										<li><a href="${application.attributes['APP_MENU_URL_1']}"
											style="text-align: left">${application.attributes['APP_MENU_OPTION_1']}</a></li>
										<li><a href="${application.attributes['APP_MENU_URL_2']}"
											style="text-align: left">${application.attributes['APP_MENU_OPTION_2']}</a></li>
										<li><a href="${application.attributes['APP_MENU_URL_3']}"
											style="text-align: left">${application.attributes['APP_MENU_OPTION_3']}</a></li>
										<li><a href="${application.attributes['APP_MENU_URL_4']}"
											style="text-align: left">${application.attributes['APP_MENU_OPTION_4']}</a></li>
										<li><a href="${application.attributes['APP_MENU_URL_5']}"
											style="text-align: left">${application.attributes['APP_MENU_OPTION_5']}</a></li>
										<li class="divider"></li>
										<li><a href="app/uninstall/${application.applicationId }"
											style="text-align: left">Uninstall</a></li>
									</ul>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- Second Row -->

				</div>
			</div>
		</div>
		<!--/span-->
	</div>
	<!--/row-->
	</div>
	<!--/span-->
	</div>
	<!--/row-->


	<div id="footer">
		<div class="container">
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
