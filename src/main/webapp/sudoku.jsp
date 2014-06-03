<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<script src="<c:url value="/resources/js/underscore-min.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-1.11.1.min.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuController.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuView.js"/>"></script>
	<script src="<c:url value="/resources/js/sudokuModel.js"/>"></script>
	<script type="text/javascript">
		var puzzle = [["","","4","7","","3","","",""],
					  ["","5","","","","6","8","",""],
					  ["6","7","","8","4","","","2","3"],
					  ["","","","1","8","2","","",""],
					  ["","","7","","","","2","",""],
					  ["","","","6","7","5","","",""],
					  ["3","8","","","2","7","","4","9"],
					  ["","","5","4","","","","8",""],
					  ["","","","9","","8","3","",""]];

	</script>
</head>
<body>
	<h1> Sudoku </h1>
	<%@ include file="WEB-INF/jsp/table.jsp"%>
	<button id="button-clear">Reset</button>
	<p id="timing">00:00:00</p>
	<p id="messaging"></p>
</body>
</html>