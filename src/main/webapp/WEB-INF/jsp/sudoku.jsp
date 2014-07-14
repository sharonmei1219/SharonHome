<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@ page isELIgnored="false" %>	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Prim Sudoku</title>
    <LINK href="<c:url value="/resources/images/icon.png"/>" type=image/x-icon rel="shortcut icon" />
    <link href="<c:url value="/resources/js/dist/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/starter.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/animate.css" />" rel="stylesheet">

</head>
<body>

    <div class="blog-masthead">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item active" href="#"><span class="glyphicon glyphicon-home"></span></a>
        </nav>
      </div>
    </div>

	<div class="container">
      	<div class="starter-template">
			<p class="lead"> <br> </p>
			<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		      <div class="container">
		        <div class="navbar-header">
		          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <a class="navbar-brand" href="#">Prim Sudoku</a>
		        </div>
		        <div class="collapse navbar-collapse">
		          <ul class="nav navbar-nav">
		            <li><a href="#">Home</a></li>
		            <li class="active"><a href="#">Game</a></li>
		            <li><a href="#">About</a></li>
		          </ul>
		        </div><!--/.nav-collapse -->
		      </div>
		    </div>
		    <br>
		    
		<!--     <p>Make each column, each row, and each block contains all of the digits from 1 to 9.</p> -->
		    <br>
		    <br>
		    <br>
		    <br>
			<div class="row">
				<div class="col-xs-3">
					<br>
					<br>
					<img src="<c:url value="/resources/images/icon.png"/>" height="166" width="166">
					<h1>Sudoku</h1>
					<p>Fill grid with digits so that each column, each row, and each block contains all of the digits from 1 to 9.</p>

				</div>

				<div class="col-xs-6" id="puzzle-zone">

					<%@ include file="table.jsp"%>

					<br>

				</div>

				<div class="col-xs-2">
					<!-- <div class="panel panel-default"> -->
						<br>
						<p id="timing">00:00:00</p>
						<br>
						<select class="form-control button-boxed" id="sudoku-level">
						    <option value="easy">Easy</option>
						    <option value="normal">Normal</option>
						    <option value="hard">Hard</option>
						    <option value="evil">Evil</option>
						</select>
						<button class="btn btn-default btn-lg button-boxed" id="button-clear"><span class="glyphicon glyphicon-repeat"></span> Clear</button>
						<br>
<!-- 						<button class="btn btn-default btn-lg button-boxed" id="button-test-bestTime"> Test </button> -->
						<!-- <br> -->
						<button class="btn btn-default btn-lg button-boxed" id="button-new"><span class="glyphicon glyphicon-download"></span> New </button>
						<br>
					<!-- </div> -->

					<br>
					<br>
					<div class="panel panel-default">
					  <div class="panel-heading">Best Time</div>
					  <div class="panel-body">
					  	<div>
					     <dl class="dl-horizontal">
		                  <dt>Easy</dt>
		                  <dd id="best-time-easy">--:--:--</dd>
		                  <dt>Normal</dt>
		                  <dd id="best-time-normal">--:--:--</dd>
		                  <dt>Hard</dt>
		                  <dd id="best-time-hard">--:--:--</dd>
		                  <dt>Evil</dt>
		                  <dd id="best-time-evil">--:--:--</dd>
		                </dl>
		            	</div>
					  </div>
					</div>

				</div>
			</div>
      	</div>
	</div>

	<script src="<c:url value="/resources/js/underscore-min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuController.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuView.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuModel.js"/>"></script>
</body>
</html>