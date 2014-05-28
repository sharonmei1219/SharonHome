<!DOCTYPE html>
<html>
<head>
	<script src="js/jquery-1.11.1.min.js"></script>
</head>
<body>
	<h1> Sudoku </h1>
	<%@ include file="WEB-INF/jsp/table.jsp"%>
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

		(function(){

			for(var i = 0; i < 9; i++){
				for(var j = 0; j < 9; j++){
					var cell = "#c"+i+j;
					$(cell).val(puzzle[i][j]);
				}
			}
		})();

	</script>
</body>

</html>