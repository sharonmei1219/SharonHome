<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@ page isELIgnored="false" %>	
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Prim Sudoku</title>
    <link href="<c:url value="/resources/js/dist/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/starter.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/animate.css" />" rel="stylesheet">

	<script type="text/javascript">
		var puzzle = ${puzzle};
	</script>
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
			<h1>Sudoku</h1>	
			<p class="lead"> So far I don't have anything to say<br> </p>

			<div class="row">
				<div class="col-md-3">
				</div>

				<div class="col-md-6">
					<p id="timing">00:00:00</p>
					<%@ include file="table.jsp"%>
					<br>
					<button class="btn btn-default btn-lg" id="button-clear"><span class="glyphicon glyphicon-repeat"></span> Clear</button>
					<button class="btn btn-default btn-lg" id="button-new"><span class="glyphicon glyphicon-download"></span> New </button>



				</div>
			</div>
      	</div>
	</div>

<!-- 	<button id="button-start">Start</button>
	<button id="button-stop">Stop</button>
	<p id="messaging"></p> -->
	<script src="<c:url value="/resources/js/underscore-min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuController.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuView.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuModel.js"/>"></script>
</body>
</html>