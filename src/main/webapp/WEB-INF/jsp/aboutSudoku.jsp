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
    <link href="<c:url value="/resources/js/dist/css/bootstrap-theme.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/about.css"/>" rel="stylesheet">
    <!--[if IE]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="<c:url value="/resources/js/respond.min.js"/>"></script>
    <![endif]-->
    <link href="<c:url value="/resources/js/dist/css/starter.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/js/dist/css/animate.css" />" rel="stylesheet">

</head>
<body>

    <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Prim Sudoku</a>
            </div>

            <div class="collapse navbar-collapse">
              <ul class="nav navbar-nav">
                <li><a href="#">Home</a></li>
                <li><a href="sudoku.htm">Game</a></li>
                <li class="active"><a href="aboutSudoku.htm">About</a></li>
              </ul>
            </div>              
        </div>
    </div>
    <div class="container theme-showcase" role="main">
        <div class="jumbotron">
            <div class="row">
                <div class="col-md-2">
                    <img src="<c:url value="/resources/images/icon.png"/>" height="135" width="135" style="  margin-top:20px">
                </div>
                <div class="col-md-10">
                    <h1>数独 <small> Sudoku.</small></h1>
                    <p>挑战，探索，排除，发现，确定，这就是数独带来的快乐，从不一成不变，与计算无关</p>
                    <p>Having fun in Sudoku</p>

                </div>
            </div>
        </div>


        <div class="navbar navbar-default">
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#Csudoku">数独</a>
                    </li>
                    <li>
                        <a href="#Csolve">求解</a>
                    </li>
                    <li>
                        <a href="#Cstatistic">统计</a>
                    </li>
                </ul>

            </div>
        </div>

        <a id="Csudoku"></a>
        <div class="page-header">
            <h1>数独 <small>Sudoku</small></h1>
        </div>
        <p>数独就是在一个9*9的表格中填数字</p>
        <ul>
            <li>
                <p>在每个单元格中填从1到9任意一个数字</p>
                <img src="<c:url value="/resources/images/sudoku.jpg"/>" height="200">
            </li>
            <br>
            <li>
                <p>使得每一行, 每一列, 每一个九宫格中都有从1到9, 九个不同的数字</p>
                <br>
                <div class="row">
                    <div class="col-md-4">
                        <img src="<c:url value="/resources/images/row.jpg"/>" height="200">
                    </div>
                    <div class="col-md-4">
                        <img src="<c:url value="/resources/images/column.jpg"/>" height="200">
                    </div>
                    <div class="col-md-4">
                        <img src="<c:url value="/resources/images/block.jpg"/>" height="200">                
                    </div>
                </div>
            </li>
        <br>
            <li>
                <p>每个数独的解都是唯一的！</p>
            </li>
        <br>
        </ul>

        <a href="sudoku.htm" class="btn btn-default btn-lg" role="button">挑战 »</a>
        <br>
        <br>
        <div class="page-header">
            <h1><a id="Csolve"></a>数独求解 <small>How to solve sudoku</small></h1>
        </div>

        <ul class="nav nav-tabs" role="tablist">
            <li class="active">
                <a href="#">Naked Single</a>
            </li>
            <li>
                <a href="#">Hidden Single</a>
            </li>
            <li>
                <a href="#">Locked Candidates</a>
            </li>
        </ul>

        <div class="page-header">
            <h1><a id="Cstatistic"></a>统计 <small>Statistic</small></h1>
        </div>

        
    </div>

</body>
</html>