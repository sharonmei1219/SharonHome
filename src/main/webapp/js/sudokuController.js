function withNumberInSide(cell){
	return cell.val() !== "";
}

// cellInput id is in the format of cXY
function dataFill(cell){
	var id = cell.attr('id');
	var x = parseInt(id[1]);
	var y = parseInt(id[2]);
	cell.val(puzzle[x][y]);
}

function cellsWithNumber(cellArray){
	return _.filter(cellArray, withNumberInSide);
}

function fixCell(cell){
	cell.attr('readonly', true);
	cell.addClass('fixedCell');
}

function createMatrix(m, n){
	return _.flatten(
				_.map(_.range(m), function(i){
					return _.map(_.range(n), function(j){
						return _.object(['i','j'],[i,j]);
					});
				}));
}

function tellSpotsContainsNumberVsBlankSpots(puzzle){
	var matrix = createMatrix(puzzle.length, puzzle[0].length);
	return _.partition(matrix, function(p){return puzzle[p.i][p.j] != '';});
}


function PuzzleController(puzzle, puzzleView){
	var spotsSubsets = tellSpotsContainsNumberVsBlankSpots(puzzle);
	var numberedPos = spotsSubsets[0];

	this.loadPuzzle = function(){
		var cellsInTable = _.map($('.cellInput'), $);
		_.each(cellsInTable, dataFill);
		var cellsWithGivenNumber = cellsWithNumber(cellsInTable)
		_.each(cellsWithGivenNumber, fixCell);
	};

	this.loadPuzzleNew = function(){
		_.each(numberedPos, function(p){
			puzzleView.put(puzzle[p.i][p.j], p.i, p.j);
		});
	};

	this.lockPuzzle = function(){
		_.each(numberedPos, function(p){
			puzzleView.lock(p.i, p.j);
		});
	};
}

var puzzleController = {};
function onDocReady(){
	puzzleView = new PuzzleView();
	puzzleController = new PuzzleController(puzzle, puzzleView);
	puzzleController.loadPuzzleNew();
	puzzleController.lockPuzzle();
	puzzle = undefined;
}

$(onDocReady);