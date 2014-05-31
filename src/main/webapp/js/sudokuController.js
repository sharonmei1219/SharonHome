function createMatrix(m, n){
	return _.flatten(
				_.map(_.range(m), function(i){
					return _.map(_.range(n), function(j){
						return _.object(['i','j'],[i,j]);
					});
				}));
}

function PuzzleController(puzzleView, puzzleModel){

	var tellSpotsContainsNumberVsBlankSpots = function(){
		var matrix = createMatrix(puzzleModel.size.i, puzzleModel.size.j);
		return _.partition(matrix, function(p){return puzzleModel.get(p.i,p.j) != '';});
	};	

	var spotsSubsets = tellSpotsContainsNumberVsBlankSpots();
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

function onDocReady(){
	puzzleView = new PuzzleView();
	puzzleModel = new PuzzleModel(puzzle, 3);
	puzzleController = new PuzzleController(puzzleView, puzzleModel);
	puzzleController.loadPuzzleNew();
	puzzleController.lockPuzzle();
	puzzleView.whenClearButtonClickedDo(puzzleController.clearSolution);
	puzzleView.setKeyUpDelegation(puzzleController.numberInput);
	timer = new StopWatch();
	timer.start();
	puzzle = undefined;
}

$(onDocReady);