function withNumberInSide(cell){
	return cell.val() !== "";
}

function filterCellsWithNumber(cellArray){
	return _.filter(cellArray, withNumberInSide);
}

function fixCell(cell){
	cell.attr('readonly', true);
	cell.addClass('fixedCell');
}

function loadPuzzle(){
	for(var i = 0; i < 9; i++){
		for(var j = 0; j < 9; j++){
			var cell = "#c"+i+j;
			$(cell).val(puzzle[i][j]);
		}
	}
	var allCells = _.map($('.cellInput'), $);
	var cellsWithNumber = filterCellsWithNumber(allCells);
	_.each(cellsWithNumber, fixCell);
}

$(loadPuzzle);