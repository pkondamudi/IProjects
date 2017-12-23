<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Recently Updated - {Marrker}</title>
<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame -->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300,700"
	rel="stylesheet" type="text/css">
<link href="assets/css/bootplus.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 46px;
	padding-bottom: 40px;
}

.hero-unit {
	background: #00001C url("assets/img/cover4.jpg) no-repeat top left;
}

.hero-unit h1 {
	color: #FFF
}

.hero-unit p {
	color: #F5F5F5
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

	<div class="navbar navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="brand"
					href="${pageContext.servletContext.contextPath}/userHome">{Marrker}
					<sup>BETA</sup>
				</a>
				<div class="nav-collapse collapse">
					<form:form class="navbar-form" action="search" name="ctxSearch"
						commandName="contextBoard" method="get">
						<input class="search-query span2" type="text"
							placeholder="Start searching the best fun here... E.g. Fun2016"
							style="width: 60%; margin-left: 50px" name="boardName"
							value="${query }" autofocus>
						<button type="submit" class="btn btn-primary">Search</button>
						<div class="btn-group pull-right">
							<a class="btn btn-danger" href="#createContextModal"
								data-toggle="modal" role="button"><b>Create Funspot</b></a>
							<button type="button"
								class="btn btn-default btn-xs dropdown-toggle btn-danger"
								data-toggle="dropdown">
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="#doPost" data-toggle="modal" role="button">Quick
										Post</a></li>
								<%-- <li><a href="#" role="button" data-toggle="modal"
									onclick="javascript: windowPopup('invite', 'Invite a Friend.');">Invite
									a Friend</a></li>--%>
								<li class="divider"></li>
								<li><a href="ctx/${defultContextBoard.boardId}">My
										Board</a></li>
								<li><a href="trending">Trending *</a></li>
								<li><a href="postsHome">Recent posts</a></li>
								<li><a href="userHome">Recent updates</a></li>
								<li><a href="userCreations">My Creations</a></li>
								<li><a href="userFollowing">What am i following ?</a></li>
								<li class="divider"></li>
								<li><a href="#" role="button" data-toggle="modal"
									onclick="javascript: windowPopup('changepassword', 'Change Password');">Change
										password</a></li>
								<li><a href="logout">Logout</a></li>
							</ul>
						</div>
					</form:form>
					<!-- <form class="navbar-form pull-right">
                 <input class="span2" type="text" placeholder="Email">
                 <input class="span2" type="password" placeholder="Password">
                 <button type="submit" class="btn btn-primary">Sign in</button>
               </form> -->
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>
	</div>

	<!-- Modal -->
	<div id="createContextModal" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
		</div>
		<div class="modal-body">
			<form:form method="post" action="ctx/createContext"
				commandName="contextBoard" name="createctx">
				<fieldset>
					<legend>Create Funspot</legend>
					<label>Code:</label> <input type="text"
						placeholder="Enter your context code..." style="width: 100%"
						name="BoardName"> <span class="help-block">Example:
						<b>Fun2016</b>.(do not put any spaces in between)
					</span> <label>Type</label> <select style="width: 100%" name="boardType">
						<option value="Protected" selected>Protected
							(Recommended) - Users who follow can also contribute</option>
						<option value="Private">Private - Only you can contribute
							the information.</option>
						<option value="Public">Public - Any Marrker user can
							contribute.</option>
					</select> <label>Description:</label>
					<textarea type="text"
						placeholder="Enter your context description..."
						style="width: 100%" rows="4" cols="25" name="BoardDescription"></textarea>
				</fieldset>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" type="submit">Start</button>
		</div>
		</form:form>
	</div>

	<!-- Modal -->
	<div id="doPost" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
			<h3 id="myModalLabel">Say what you think...</h3>
		</div>
		<div class="modal-body">

			<div class="tabbable tabs-left">
				<!-- Only required for left/right tabs -->
				<ul class="nav nav-tabs">
					<li class="active"><a href="#tab1" data-toggle="tab">Text</a></li>
					<li><a href="#tab2" data-toggle="tab">Upload Video</a></li>
					<li><a href="#tab3" data-toggle="tab">Upload Image</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="tab1">
						<p>
							<form:form method="post" action="pst/multiPost" name="multiPost"
								commandName="multiPost" enctype="multipart/form-data">
								<input type="hidden" name="contextBoardId" value="${ctxid }">
								<textarea style="width: 100%" name="post" rows="8" cols="25"
									placeholder="Say what you think about this..."></textarea>
						</p>
					</div>
					<div class="tab-pane" id="tab2">
						<p>
							<input type="file" name="files[0]"> <span
								class="help-block">* Please upload MP4 format videos
								only.</span>
						</p>
					</div>
					<div class="tab-pane" id="tab3">
						<p>
							<input type="file" name="files[1]"> <span
								class="help-block">* Please upload JPEG format images
								only.</span>
						</p>
					</div>
				</div>
			</div>
			<label>Type</label> <select style="width: 100%" name="postType">
				<option value="Open" selected>Open - The post will appear
					on your personal board.</option>
				<option value="Closed">Closed - The post will not appear on
					your personal board.</option>
			</select>
			<%-- <span class="help-block pull-right"><a href="#">Know more
					about types.</a></span> --%>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<a class="btn btn-primary" href="#selectBoards" data-toggle="modal"
				role="button">Post</a>
		</div>
	</div>



	<!-- Modal -->
	<div id="selectBoards" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">x</button>
			<h3 id="myModalLabel">Choose boards to publish your post...</h3>
		</div>
		<div class="modal-body">
			<table width=100%>
				<c:forEach var="userCreatedBoard" items="${userCreatedBoards}">
					<tr>
						<td><input type="checkbox" name="publishBoardIds"
							value="${userCreatedBoard.boardId }" /></td>
						<td>${userCreatedBoard.boardName }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" type="submit">Post</button>
			</form:form>
		</div>
	</div>


	<div class="container">
		<div class="page-header">
			<h2>Recently updated...</h2>
		</div>
		<!-- Example row of columns -->
		<c:choose>
			<c:when test="${fn:length(userBoards) > 0}">
				<c:forEach var="userBoard" items="${userBoards}">
					<div class="row">
						<div class="card">
							<h3 class="card-heading simple">
								<c:choose>
									<c:when test="${userBoard.isDefault eq 1 }">
										<i class="icon-user"></i>
									</c:when>
									<c:otherwise>
										<i class="icon-desktop"></i>
									</c:otherwise>
								</c:choose>
								${userBoard.boardName }
							</h3>
							<div class="card-body">
								<p>${userBoard.boardDescription }</p>
								<p></p>
							</div>
							<div class="card-actions">
								<a class="btn" href="ctx/${userBoard.boardId}">View</a>
							</div>
						</div>
					</div>
				</c:forEach>
				<ul class="pager">
					<li><a href="userHome?nav=pre">Previous</a></li>
					<li><a href="userHome?nav=nxt">Next</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<div class="card">
					<div class="card-body">
						<p style="padding: 0px 0px 10px 0px">No recent updates found.</p>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- /container -->

	<div id="push"></div>
	<div id="footer">
		<div class="container">
			<p class="muted credit">Copyright © Marrker Inc.</p>
		</div>
	</div>


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