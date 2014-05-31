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
	timer = new StopWatch();
	timer.start();
	puzzle = undefined;
}

$(onDocReady);