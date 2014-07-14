function UserInfo(){
	this.getBestTime = function(){
		var bestTime = {easy:0, normal:0, hard:0, evil:0}
		if(typeof(localStorage) !== "undefined"){
			if(typeof(localStorage.bestEasyTime) !== 'undefined'){
				bestTime.easy = localStorage.bestEasyTime;
			}
			if(typeof(localStorage.bestNormalTime) !== 'undefined'){
				bestTime.normal = localStorage.bestNormalTime;
			}
			if(typeof(localStorage.bestHardTime) !== 'undefined'){
				bestTime.hard = localStorage.bestHardTime;
			}
			if(typeof(localStorage.bestEvilTime) !== 'undefined'){
				bestTime.evil = localStorage.bestEvilTime;
			}
		}
		return bestTime;
	}

	this.setBestTime = function(bestTime){
		if(typeof(localStorage) !== "undefined"){
			localStorage.bestEasyTime = bestTime.easy;
			localStorage.bestNormalTime = bestTime.normal;
			localStorage.bestHardTime = bestTime.hard;
			localStorage.bestEvilTime = bestTime.evil;
		}
	}

	this.saveLevel = function(inputLevel){
		if(typeof(localStorage) !== "undefined") {
    		localStorage.sudokuLevel = inputLevel;
		}
	}

	this.getLevel = function(){
		if(typeof(localStorage) !== "undefined") {
			if(typeof(localStorage.sudokuLevel) !== 'undefined'){
				return localStorage.sudokuLevel;
			}
		}
		return undefined;
	}
}


if (!Date.now) {
  Date.now = function now() {
    return new Date().getTime();
  };
}

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

	this.unlockPuzzle = function(){
		_.each(numberedPos, function(p){
			puzzleView.unlock(p.i, p.j);
		});	
	};

	this.clearPuzzle = function(){
		_.each(numberedPos, function(p){
			puzzleView.clear(p.i, p.j);
		});
	};

	this.clearSolution = function(){
		_.each(blankPos, function(p){
			puzzleView.clear(p.i, p.j);
		});
		puzzleView.seekAttentionToTimer();
	};

	this.numberInput = function(value, i, j){
		puzzleModel.change(value, i, j);
		if(puzzleModel.finished()) {
			puzzleFinished()
		};
	};

}

function puzzleFinished(){
	var time = timer.stop();
	var isNewBest = bestTimeController.saveWhenTimeIsNewBest(time, levelCtrl.currentLevel());
	finishedTime(isNewBest, formatedTime(time));
}


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

function StopWatch(){
	var showInView = function(){}
	this.setShowInView = function(show){
		showInView = show;
	}
	var interval = 500;
	var startTime = 0;

	function update(){
		var timePassed = Date.now() - startTime;
		showInView(formatedTime(timePassed));
	}

	var tic;
	var cnt = 0;

	this.start = function(){
		if(tic!== "undefined"){
			clearInterval(tic);
		}
		startTime = Date.now();
		tic = setInterval(update, interval);
		cnt = cnt + 1;
	};

	this.stop = function(){
		clearInterval(tic);
		return Date.now() - startTime;
	}
}

function getNewPuzzle(){
	var clevel = levelCtrl.currentLevel();
	$.ajax({
		type : "POST",
		url : "sudoku/new",
		data : JSON.stringify({level:levelCtrl.currentLevel()}),
		contentType: 'application/json',
		success : function(response){
			if(typeof puzzleController != 'undefined'){
				puzzleController.unlockPuzzle();
				puzzleController.clearPuzzle();
				puzzleController.clearSolution();
			}
			puzzle = JSON.parse(response);
			puzzleModel = new PuzzleModel(puzzle, 3);
			puzzleController = new PuzzleController(puzzleView, puzzleModel);
			puzzleController.loadPuzzleNew();
			puzzleController.lockPuzzle();
			puzzleView.setResetbuttonDelegation(puzzleController.clearSolution);
			puzzleView.setKeyUpDelegation(puzzleController.numberInput);
			puzzle = undefined;
			timer.start();
		}
	});
}

function BestTimeController(){
	this.saveWhenTimeIsNewBest = function(time, sudokulevel){
		var bestTime = userInfo.getBestTime();
		if(time < Number(bestTime[sudokulevel]) || bestTime[sudokulevel] == 0){
			bestTime[sudokulevel] = time.toString();
			userInfo.setBestTime(bestTime);
			bestTimeView.renderBestTime(bestTime);
			return true;
		}
		return false;
	}
	this.loadBestTime = function(){
		var bestTime = userInfo.getBestTime();
		bestTimeView.renderBestTime(bestTime);
	}
}

function LevelSelectionController(){
	var sudokulevel = 'hard';

	this.levelChanged = function(inputLevel){
		userInfo.saveLevel(inputLevel);
		sudokulevel = inputLevel;
		getNewPuzzle();
	}

	this.currentLevel = function(){
		if(userInfo.getLevel() !== undefined) return userInfo.getLevel();
		return sudokulevel;
	}
}


var bestTimeController = new BestTimeController();
var userInfo = new UserInfo();
var levelCtrl = new LevelSelectionController();

function finishedTime(isNewBest, time){
	var ann = new StayAnnimation(500);
	if(isNewBest) ann = new NewBestAnnimation(ann);
	ann = new FinishTimeAnnimation(ann);
	ann.start(time);
}

function NewBestAnnimation(ann){
	var decoratedAnn = ann;
	var afterEnd = function(){};
	this.start = function(time){
		$('#puzzle-zone').append('<p id="new-best-sign" class="animated bounceIn">New Best</p>');
		$('#new-best-sign').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
			decoratedAnn.start();
		});
	}

	this.end = function(){
		$('#new-best-sign').removeClass("animated bounceIn");
		$('#new-best-sign').addClass("animated bounceOut");
		$('#new-best-sign').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
			$('#new-best-sign').remove();
			afterEnd();
		});
	}

	this.endFinished = function(action){
		afterEnd = action;
	}

	ann.endFinished(this.end);
}

function FinishTimeAnnimation(ann){
	var decoratedAnn = ann;

	this.start = function(time){
		$('#puzzle-zone').append('<p id="time-puzzle-finished" class="animated bounceIn">' + time +'</p>');
		$('#time-puzzle-finished').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
			decoratedAnn.start();
		});
	}
	
	this.end = function(){
		$('#time-puzzle-finished').removeClass("animated bounceIn");
		$('#time-puzzle-finished').addClass("animated bounceOut");
		$('#time-puzzle-finished').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
			$('#time-puzzle-finished').remove();
		});
	}
	ann.endFinished(this.end);
}

function StayAnnimation(duration){
	var actionAfterTO;
	var tic;
	this.start = function(){
		tic = setInterval(this.end, duration);
	}
	this.end = function(){
		actionAfterTO();
		clearInterval(tic);
	}
	this.endFinished = function(action){
		actionAfterTO = action;
	}
}

function onDocReady(){
	timer = new StopWatch();
	timer.setShowInView(puzzleView.showTime);
	$('#sudoku-level').val(levelCtrl.currentLevel());
	$('#button-new').click(getNewPuzzle);
	$('#button-test-bestTime').click(function(){
		puzzleFinished();
		// var stay = new StayAnnimation(500);
		// var bestTime = new NewBestAnnimation(stay);
		// var finishedTime = new FinishTimeAnnimation(bestTime);
		// finishedTime.start("00:15:20");
		// finishedTime();
	})
	puzzleView.setLevelSelectionDelegation(levelCtrl.levelChanged);
	bestTimeController.loadBestTime();
	getNewPuzzle();
}

$(onDocReady);