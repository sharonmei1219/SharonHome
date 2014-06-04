<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<%@ page isELIgnored="false" %>	
	<script src="<c:url value="/resources/js/underscore-min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuController.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuView.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuModel.js"/>"></script>
	<script type="text/javascript">
		var puzzle = ${puzzle};
	</script>
</head>
<body>
	<h1>Sudoku</h1>	
	<%@ include file="table.jsp"%>
	<button id="button-clear">Reset</button>
	<p id="timing">00:00:00</p>
	<p id="messaging"></p>
</body>
</html>