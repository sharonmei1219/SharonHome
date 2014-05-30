function createMatrix(m, n){
	return _.flatten(
				_.map(_.range(m), function(i){
					return _.map(_.range(n), function(j){
						return _.object(['i','j'],[i,j]);
					});
				}));
}

function retrieveRow(matrix, lineNum){
	return _.filter(matrix, function(p){return p.i == lineNum;});
}

function retrieveColumn(matrix, columnNum){
	return _.filter(matrix, function(p){return p.j == columnNum;});
}

function retrieveBlock(matrix, i, j){
	var b = _.map([i, j], function(x){return x - x%3});
	return _.filter(matrix, function(p){
		return (p.i >= b[0]) && (p.i < b[0] + 3) && (p.j >= b[1]) && (p.j < b[1] + 3);
	});
}

function tellSpotsContainsNumberVsBlankSpots(puzzle){
	var matrix = createMatrix(puzzle.length, puzzle[0].length);
	return _.partition(matrix, function(p){return puzzle[p.i][p.j] != '';});
}

function PuzzleController(puzzle, puzzleView, puzzleModel){
	var spotsSubsets = tellSpotsContainsNumberVsBlankSpots(puzzle);
	var numberedPos = spotsSubsets[0];
	var blankPos = spotsSubsets[1];

	this.loadPuzzleNew = function(){
		_.each(numberedPos, function(p){
			puzzleView.put(puzzleModel.get(p.i, p.j), p.i, p.j);
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
	function notValidInput(i,j){
		alert('not a valid input ' + i + ' ' + j);
	}


	this.numberInput = function(value, i, j){
		puzzleModel.change(value, i, j);
		var valid  = puzzleModel.validInput(i, j);
		if(!valid) notValidInput(i, j);

	};
}

var puzzleController;
function onDocReady(){
	puzzleView = new PuzzleView();
	puzzleModel = new PuzzleModel(puzzle);
	puzzleController = new PuzzleController(puzzle, puzzleView, puzzleModel);
	puzzleController.loadPuzzleNew();
	puzzleController.lockPuzzle();
	puzzleView.whenClearButtonClickedDo(puzzleController.clearSolution);
	puzzleView.whenGetInputDo(puzzleController.numberInput);
	puzzle = undefined;
}

$(onDocReady);