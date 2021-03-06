﻿<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@ page isELIgnored="false" %>	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sudoku</title>
    <LINK href="<c:url value="/resources/images/icon.png"/>" type=image/x-icon rel="shortcut icon" />
    <link href="<c:url value="/resources/js/dist/css/bootstrap.css"/>" rel="stylesheet">
    <!--[if IE]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="<c:url value="/resources/js/respond.min.js"/>"></script>
    <![endif]-->
    <link href="<c:url value="/resources/js/dist/css/starter.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/animate.css" />" rel="stylesheet">

</head>
<body>

	<div class="container">
      	<div class="starter-template">
			<p class="lead"> <br> </p>
			<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		      <div class="container">
		        <div class="navbar-header">
		        	<button type="button" class="navbar-toggle" collapsed data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		        		<span class="sr-only">Toggle Navigation</span>
		        		<span class="icon-bar"></span>
		        		<span class="icon-bar"></span>
		        		<span class="icon-bar"></span>
		        	</button>
		          <a class="navbar-brand" href="#">Prim Sudoku</a>
		        </div>
		        <div class="collapse navbar-collapse" id="navbar">
		          <ul class="nav navbar-nav">
		            <li><a href="#">Home</a></li>
		            <li class="active"><a href="sudoku">Game</a></li>
		            <li><a href="sudoku4Times4">Baby Version 4X4</a></li>
		            <li><a href="sudoku5Times5">Baby Version 5X5</a></li>
		            <li><a href="static/part4.html">Match</a></li>
		            <li><a href="../tt/plusMinus.html">Problem Generation</a></li>
		            <li><a href="aboutSudoku.htm">About</a></li>
		          </ul>
		        </div><!--/.nav-collapse -->
		      </div>
		    </div>
		    <br>
		    <br>

			<div class="row">
				<div class="col-md-2">
				</div>

				<div class="col-md-8">
					<div id="logo-zone" class="row">
						<div class="col-sm-2">
							<img src="<c:url value="/resources/images/icon.png"/>" height="80" width="80">
						</div>
						<div class="col-sm-10 align-bottom">
							<h2 class="align-left bottomMargin0 topMargin0">Sudoku</h2>
							<h3 class="align-left bottomMargin0 topMargin0">www.alruo.com</h3>
						</div>
					</div>
					<br>

					<div class="row">
						<div class="col-lg-8 col-md-10 col-sm-12" id="puzzle-zone">
							<jsp:include page="table.jsp">
								<jsp:param name="tableSize" value='<%= pageContext.findAttribute("tableSize") %>'/>
								<jsp:param name="blockSize" value='<%= pageContext.findAttribute("blockSize") %>'/>
							</jsp:include>
						</div>

						<div class="col-lg-3 col-md-2" id="control-zone">
							<p id="timing" class="leftMargin3">00:00:00</p>
							<!-- <p class="align-left" id="sudoku-level">Evil Level</p> -->
							<select class="form-control align-left" id="sudoku-level">
							    <option value="easy" class="align-left">Easy</option>
							    <option value="normal" class="align-left">Normal</option>
							    <option value="hard" class="align-left">Hard</option>
							    <% if(pageContext.findAttribute("tableSize").equals(9)){%>
							    	<option value="evil" class="align-left">Evil</option>
							    <%}%>
							</select>
							<p class="leftMargin5" id="best-time">Best: 00:15:24</p>
							<div class="leftMargin5" id="hint-zone">
							</div>

						</div>

						<div id="button-zone" class="align-bottom col-md-4">
							<br>
							<button class="btn btn-default align-left btn-lg sudoku-button" id="button-new"><span class="glyphicon glyphicon-th button-icon"></span></button>
							<br>
							<button class="btn btn-default align-left btn-lg sudoku-button" id="button-help"><span class="glyphicon glyphicon-earphone button-icon"></span></button>

							
						</div>
					</div>

				</div>

				<div class="col-md-2">
						<br>
						<br>

						<br>

					<br>
					<br>

				</div>

				<ul id="contextMenu" class="dropdown-menu" role="menu" style="display:none" >
				    <li><a tabindex="-1" href="#">Add Note</a></li>
				    <li><a tabindex="-1" href="#">Clear Note</a></li>
				</ul>
			</div>
      	</div>
	</div>

	<script>
		var puzzleType = <%= pageContext.findAttribute("tableSize") %>
	</script>
	<script src="<c:url value="/resources/js/underscore-min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/contextMenu.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuController.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuView.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuModel.js"/>"></script>
	<script src="<c:url value="/resources/js/userInfo.js"/>"></script>
	<script src="<c:url value="/resources/js/dist/js/bootstrap.min.js"/>"></script>
</body>
</html>