<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>${param.q } - hnotch search</title>
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="assets/css/bootplus.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/font-awesome.css">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
.centered-pills {
	text-align: center
}

.centered-pills ul.nav-pills {
	display: inline-block
}

.centered-pills li {
	display: inline
}

.centered-pills a {
	float: left
}

* html .centered-pills ul.nav-pills, *+html .centered-pills ul.nav-pills
	{
	display: inline
}

.navbar-custom {
	color: #FFFFFF;
	background-color: #30A3DA;
	margin-bottom: 0px;
}

.navbar-zero-bottom {
	margin-bottom: 0px;
}

body {
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

ul.nav-pills {
	width: 100%;
	text-align: center
}

.nav-pills li {
	float: none;
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

	<div class="navbar navbar-zero-bottom">
		<div class="navbar-inner navbar-custom">
			<div class="container-fluid">
				<div class="text-center">
					<form class="form-search" action="search">
						<a href="/hnotch" class="pull-left"><img src="assets/img/logo.jpg"
							width="100px" height="25px" border="0" alt="hnotch"> </a> <input
							type="text" name="q" class="input-large search-query"
							style="width: 80%" value = "${param.q }" autofocus>
						<button type="submit" class="btn">
							<i class="icon-search"></i>
						</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="navbar">
		<div class="navbar-inner navbar-custom">
			<ul class="nav nav-pills form-search">

				<c:choose>
					<c:when test="${empty  param.filter }">
						<li class="active"><a href="search?q=${param.q }">All</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="search?q=${param.q }">All</a></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${param.filter.equalsIgnoreCase('blogs') }">
						<li class="active"><a
							href="search?q=${param.q }&filter=blogs">Blogs</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="search?q=${param.q }&filter=blogs">Blogs</a></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${param.filter.equalsIgnoreCase('news') }">
						<li class="active"><a href="search?q=${param.q }&filter=news">News</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="search?q=${param.q }&filter=news">News</a></li>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${param.filter.equalsIgnoreCase('qa') }">
						<li class="active"><a href="search?q=${param.q }&filter=qa">Q
								&amp; A</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="search?q=${param.q }&filter=qa">Q &amp; A</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="#" onclick="javascript:alert('Coming soon...');">Videos</a></li>
				<li><a href="#" onclick="javascript:aletrt('Coming soon...');">Sites</a></li>
				<li><a href="#" onclick="javascript:alert('Coming soon...');">Apps</a></li>
			</ul>
		</div>
	</div>

	<div class="container-fluid">
		<c:choose>
			<c:when test="${fn:length(search_results) > 0}">
				<c:forEach var="searchResults" items="${search_results }">
					<div class="row-fluid">
						<div class="span14">
							<div class="card">
								<h2 class="card-heading simple">${searchResults.title }</h2>
								<div class="card-body">
									<p>${searchResults.text }</p>
									<p>
										<a class="btn" href="${searchResults.url }">View details
											&raquo;</a>
									</p>
								</div>
							</div>
						</div>
						<!--/span-->
					</div>
					<!--/row-->
				</c:forEach>
				<ul class="pager">
					<li class="previous"><a href="search?q=${param.q}&page=previous&filter=${param.filter}">&larr;
							Previous</a></li>
					<li class="next"><a href="search?q=${param.q}&page=next&filter=${param.filter}">Next &rarr;</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<div class="card">
					<div class="card-body">
						<c:choose>
							<c:when test="${empty  param.filter }">
								<p style="padding: 0px 0px 10px 0px">
									No results found for search term - <b>${param.q }</b>
								</p>
							</c:when>
							<c:otherwise>
								<p style="padding: 0px 0px 10px 0px">
									No results found for search term - <b>${param.q }</b> in <b>${param.filter }</b>
								</p>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<!--/span-->

	<div id="footer">
		<div class="container navbar-fixed-bottom">
			<p class="muted credit">
				Example courtesy <a href="http://martinbean.co.uk">Martin Bean</a>
				and <a href="http://ryanfait.com/sticky-footer/">Ryan Fait</a>.
			</p>
		</div>
	</div>

	<!--/.fluid-container-->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!--    <script src="js/bootstrap-transition.js"></script>
    <script src="js/bootstrap-alert.js"></script>
    <script src="js/bootstrap-modal.js"></script>
    <script src="js/bootstrap-dropdown.js"></script>
    <script src="js/bootstrap-scrollspy.js"></script>
    <script src="js/bootstrap-tab.js"></script>
    <script src="js/bootstrap-tooltip.js"></script>
    <script src="js/bootstrap-popover.js"></script>
    <script src="js/bootstrap-button.js"></script>
    <script src="js/bootstrap-collapse.js"></script>
    <script src="js/bootstrap-carousel.js"></script>
    <script src="js/bootstrap-typeahead.js"></script>-->

</body>
</html>
