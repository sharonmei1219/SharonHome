<style>
.sudokuTable {
	border-collapse: collapse;
	border-right: 3px solid #6A6890;
	border-bottom: 3px solid #6A6890;

}

.cell {
	border: 1px solid gray;
	width: 52px;
	height: 52px;
	font-size: 20px;
}

input.cellInput {
	color: blue;
}

input.cellInput.fixedCell {
	color: black;
}

.topRow {
	border-top: 3px solid #6A6890;
}

.sudokuRow {
	border-top: 1px solid #6A6890;
}

.leftMostCell {
	border-left: 3px solid #6A6890;
}

.cellInput {
	border: none;
	width: 30px;
	height: 30px;
	text-align: center;
}
</style>

<!-- 9*9 table each cell contains an input whose id is from c00 to c88 -->
<table class="sudokuTable" align="center">
	<%for(int r = 0; r < 9; r++){
		String rowId = "c" + r;
		String rowClass = "sudokuRow";
		if(r%3 == 0) rowClass = "topRow";%>
	<tr class=<%=rowClass%>>
		<%for(int i = 0; i < 9; i++){
				String cellId = rowId + i;
				String cellClass = "cell";
				if(i%3 == 0) 
					cellClass = "\"leftMostCell cell\"";%>
		<td class=<%=cellClass%>><input class="cellInput" id=<%= cellId%>
			type="text" maxLength="1" /></td>
		<%}%>
	</tr>
	<%}%>
</table>