<html>
<head>
	<style>
		.sudokuTable{
			border-collapse:collapse;
			border-right:2px solid #6A6890;
			border-bottom:2px solid #6A6890;
		}

		.cell{
			border:1px solid #6A6890;
			width:30px;
			height:30px;
		}

		.topRow{
			border-top:2px solid #6A6890;
		}

		.leftMostCell{
			border-left:2px solid #6A6890;
		}

		.cellInput{
			border:none;
			width:30px;
			height:30px;
			text-align:center;
		}

	</style>
</head>
<body>
	<h1> hello world</h1>
	<table class="sudokuTable">
		<%for(int r = 0; r < 9; r++){
			String rowId = "c" + r;
			String rowClass = "row";
			if(r%3 == 0) rowClass = "topRow";%>
			<tr class=<%=rowClass%>>
				<%for(int i = 0; i < 9; i++){
					String cellId = rowId + i;
					String cellClass = "cell";
					if(i%3 == 0) 
						cellClass = "\"leftMostCell cell\"";%>
					<td class=<%=cellClass%>><input class="cellInput" id=<%= cellId%>  type="text" maxLength="1"/></td>
				<%}%>
			</tr>
		<%}%>
	
	</table>
</body>

</html>