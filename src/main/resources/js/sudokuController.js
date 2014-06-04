function createMatrix(m, n){
	return _.flatten(
				_.map(_.range(m), function(i){
					return _.map(_.range(n), function(j){
						return _.object(['i','j'],[i,j]);
					});
				}));
}

function PuzzleController(puzzleView, puzzleModel){

	var matrix = createMatrix(puzzleModel.size.i, puzzleModel.size.j);
	var tellSpotsContainsNumberVsBlankSpots = function(){
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
		puzzleView.putMessage('not a valid input ' + i + ' ' + j);
	}

	function puzzleFinished(){
		timer.stop();
		puzzleView.putMessage('Congratulations!');
	}

	this.numberInput = function(value, i, j){
		puzzleModel.change(value, i, j);
		if(puzzleModel.finished()) puzzleFinished();
	};

	var timer;
	this.setTimer = function(t){
		timer = t;
		timer.setShowInView(puzzleView.showTime);
	}
}

function StopWatch(){
	var showInView = function(){}
	this.setShowInView = function(show){
		showInView = show;
	}
	var interval = 500;
	var startTime = 0;

	function addLeading0(num){
		if (num < 10) return '0' + num;
		return '' + num;
	}

	function formatedTime(timePassed){
		var c = Math.floor(timePassed/1000);
		return _.map([3600, 60, 1], function(unit){
					var result = addLeading0(Math.floor(c/unit))
					c = c%unit;
					return result;
					}).join(':');
	}

	function update(){
		var timePassed = Date.now() - startTime;
		showInView(formatedTime(timePassed));
	}

	var tic;

	this.start = function(){
		startTime = Date.now();
		tic = setInterval(update, interval);
	};

	this.stop = function(){
		clearInterval(tic);
	}
}

function getNewPuzzle(){
	$.ajax({
		type : "POST",
		url : "sudoku/new",
		data : JSON.stringify({level:"hard"}),
		contentType: 'application/json',
		success : function(response){
			alert(response);
		}
	});
}


function onDocReady(){
	puzzleView = new PuzzleView();
	puzzleModel = new PuzzleModel(puzzle, 3);
	puzzleController = new PuzzleController(puzzleView, puzzleModel);
	timer = new StopWatch();
	puzzleController.setTimer(timer);
	puzzleController.loadPuzzleNew();
	puzzleController.lockPuzzle();
	timer.start();
	puzzleView.whenClearButtonClickedDo(puzzleController.clearSolution);
	puzzleView.setKeyUpDelegation(puzzleController.numberInput);
	puzzle = undefined;
	$('#button-new').click(getNewPuzzle);
}

$(onDocReady);