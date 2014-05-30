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
	var blankPos = spotsSubsets[1];

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

	this.clearSolution = function(){
		_.each(blankPos, function(p){
			puzzleView.clear(p.i, p.j);
		});
	};
}

var puzzleController;
function onDocReady(){
	puzzleView = new PuzzleView();
	puzzleController = new PuzzleController(puzzle, puzzleView);
	puzzleController.loadPuzzleNew();
	puzzleController.lockPuzzle();
	puzzleView.whenClearButtonClickedDo(
		puzzleController.clearSolution
		);
	puzzle = undefined;
}

$(onDocReady);