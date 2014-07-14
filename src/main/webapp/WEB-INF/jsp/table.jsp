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