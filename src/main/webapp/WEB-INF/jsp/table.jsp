<style>
.sudokuTable {
	border-collapse: collapse;
	border-right: 3px solid black;
	border-bottom: 3px solid black;

}

.cell {
	border: 1px solid gray;
	width: 45px;
	height: 45px;
	font-size: 20px;
}

input.cellInput {
	color: blue;
}

input.cellInput.fixedCell {
	color: black;
}

.topRow {
	border-top: 3px solid black;
}

.sudokuRow {
	border-top: 1px solid black;
}

.leftMostCell {
	border-left: 3px solid black;
}

.cellInput {
	border: none;
	width: 45px;
	height: 45px;
	text-align: center;
}

.timer-n-level-cell{
	width: 145px;
}

.button-boxed{
	width: 100%;
	margin-bottom: 10px;

}

.button-boxed{
	line-height: 14px;
	font-size: 18px;
}
.panel-heading{
	font-size: 18px;
}
.panel .dl-horizontal dt {
    white-space: normal;
    width: 50px;
}
.panel .dl-horizontal dd {
    margin-left: 0px;
}

#timing{
	font-size: 25px;
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