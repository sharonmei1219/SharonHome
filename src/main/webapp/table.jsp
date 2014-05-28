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
		<%for(int r = 0; r < 1; r++){%>
			<tr class="topRow">
				<%for(int i = 0; i < 9; i++){
					String cellId = "c0" + i;
					String cellClass = "cell";
					if(i%3 == 0) 
						cellClass = "\""+cellClass + " leftMostCell\"";%>
					<td class=<%=cellClass%>><input class="cellInput" id=<%= cellId%>  type="text" maxLength="1"/></td>
				<%}%>
			</tr>
		<%}%>


		<tr class="row">
			<td class="leftMostCell cell"><input class="cellInput" id="c10" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c11" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c12" type="text" maxLength="1"/></td>

			<td class="leftMostCell cell"><input class="cellInput" id="c13" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c14" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c15" type="text" maxLength="1"/></td>

			<td class="leftMostCell cell"><input class="cellInput" id="c16" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c17" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c18" type="text" maxLength="1"/></td>
		</tr>
		<tr class="row">
			<td class="leftMostCell cell"><input class="cellInput" id="c20" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c21" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c22" type="text" maxLength="1"/></td>

			<td class="leftMostCell cell"><input class="cellInput" id="c23" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c24" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c25" type="text" maxLength="1"/></td>

			<td class="leftMostCell cell"><input class="cellInput" id="c26" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c27" type="text" maxLength="1"/></td>
			<td class="cell"><input class="cellInput" id="c28" type="text" maxLength="1"/></td>
		</tr>		
	
	</table>
</body>

</html>