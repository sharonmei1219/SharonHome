function withNumberInSide(cell){
	return cell.val() !== "";
}

function dataFill(cell){
	var id = cell.attr('id');
	var i = parseInt(id[1]);
	var j = parseInt(id[2]);
	cell.val(puzzle[i][j]);
}

function cellsWithNumber(cellArray){
	return _.filter(cellArray, withNumberInSide);
}

function fixCell(cell){
	cell.attr('readonly', true);
	cell.addClass('fixedCell');
}

function loadPuzzle(){
	var allCells = _.map($('.cellInput'), $);
	_.each(allCells, dataFill);
	_.each(cellsWithNumber(allCells), fixCell);
}

$(loadPuzzle);