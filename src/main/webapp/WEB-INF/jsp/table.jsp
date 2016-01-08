<!-- 9*9 table each cell contains an input whose id is from c00 to c88 -->
<table class="sudokuTable" align="center">
	<%
	int tableSize = Integer.parseInt(request.getParameter("tableSize"));
	int blockSize = Integer.parseInt(request.getParameter("blockSize"));
	for(int r = 0; r < tableSize; r++){
		String rowId = "c" + r;
		String rowClass = "sudokuRow";
		if(r%blockSize == 0) rowClass = "topRow";%>
	<tr class=<%=rowClass%>>
		<%for(int i = 0; i < tableSize; i++){
				String cellId = "cell-"+ rowId + i;
				String inputId = rowId + i;
				String cellClass = "cell";
				if(i%blockSize == 0) 
					cellClass = "\"leftMostCell cell\"";%>
		<td class=<%=cellClass%> id=<%=cellId%>><input class="cellInput" id=<%= inputId%>
			type="text" pattern="[0-9]*" min="1" max="9" maxLength="1" /></td>
		<%}%>
	</tr>
	<%}%>

</table>