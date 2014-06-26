<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- saved from url=(0048)http://v3.bootcss.com/examples/starter-template/ -->
<html lang="zh-cn"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <%@ page isELIgnored="false"%> 
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <link rel="shortcut icon" href="http://v3.bootcss.com/docs-assets/ico/favicon.png"> -->
    <title>Sudoku Generator</title>
    <link href="<c:url value="/resources/js/dist/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/starter.css" />" rel="stylesheet">
</head>
  <body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Contact</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </div>

    <div class="container">

      <div class="starter-template">
        <h1>Sudoku Generator</h1>
        <p class="lead"> <br> </p>
        <div class="panel panel-default">
          <div class="panel-body">
            <div class = "row">
              <div class = "col-md-5">

                <div class="input-group">
                  <span class="input-group-addon">@</span>
                  <input type="text" class="form-control" id="input-numberOfPuzzles" placeholder="Number Of Sudoku">
                </div>
                <br>
                <div class="input-group">
                  <span class="input-group-addon">$</span>
                  <input type="text" class="form-control" id = "input-numberOfHoles" placeholder="Number Of Empty Cells in Sudoku">
                </div>
                <br>
                <button type="button" class="btn btn-default btn-lg" id="start-button">
                  <span class="glyphicon glyphicon-star"></span> GENERATE
                </button>
              </div>
              <div class="col-md-6">
                <h2>Warnings</h2>
                <p id="info-warnings"><p>
              </div>

            </div>
        </div>
      </div>
        <br>
        <div class="panel panel-default">
          <div class="panel-body">
            <h2>Porgress</h2>
            <br>
            <p>Overall</p>

            <div class="progress">
              <div class="progress-bar" id="total-progress" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style=<c:out value="width:${progress.percentageTotal}%"/>>
                ${progress.total}
              </div>
            </div>

            <br>
            <p>Easy</p>
            <div class="progress">
              <div class="progress-bar" id="easy-progress" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:${progress.percentageEasy}%">
                ${progress.easy}
              </div>
            </div>

            <br>
            <p>Normal</p>
            <div class="progress">
              <div class="progress-bar" id="normal-progress" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:${progress.percentageNormal}%">
                ${progress.normal}
              </div>
            </div>

            <br>
            <p>Hard</p>
            <div class="progress">
              <div class="progress-bar" id="hard-progress" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:${progress.percentageHard}%">
                ${progress.hard}
              </div>
            </div>

            <br>
            <p>Evil</p>
            <div class="progress">
              <div class="progress-bar" id="evil-progress" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:${progress.percentageEvil}%">
                ${progress.evil}
              </div>
            </div>
          </div>
        </div>
      </div>


    </div><!-- /.container -->

    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/sudokuGenerator.js" />"></script>
    <script src="<c:url value="/resources/js/dist/js/bootstrap.min.js"/>"></script>
  

</body></html>