function PuzzleView(){
	this.put = function(value, i, j){
		this.cellAt(i, j).val(value);
	};

	this.cellAt = function(i, j){
		var cid = '#c' + i + j;
		return $(cid);
	};

	this.allCell = function(){
		return $('.cellInput');
	}; 

	this.resetButton = function(){
		return $('#button-clear');
	};

	this.levelSelect = function(){
		return $('#sudoku-level');
	}

	this.lock = function(i, j){
		var cell = this.cellAt(i, j);
		cell.attr('readonly', true);
		cell.addClass('fixedCell');
	};

	this.unlock = function(i, j){
		var cell = this.cellAt(i, j);
		cell.removeAttr('readonly');
		cell.removeClass('fixedCell');
	}

	this.clear = function(i, j){
		this.cellAt(i, j).val('');
	};

	varifyKeyInIsNumber = function(e){
		var key = e.keyCode ? e.keyCode : e.which;
		if((key == 46) || (key == 8)) return true; //backspace, delete
		if($(this).val() != '') return false;
		if((key > 96) && (key < 106)) return true;
		if((key > 48) && (key < 58)) return true;
		return false;
	};

	function Delegation(){
		this.call = function(){};
	}

	var keyUpDelegation = new Delegation;
	var resetButtonDelegation = new Delegation;
	var levelSelectionDelegation = new Delegation;


	this.setResetbuttonDelegation = function(action){
		resetButtonDelegation.call = action;
	};

	this.setKeyUpDelegation = function(action){
		keyUpDelegation.call = action;
	};

	function resetTable(){
		resetButtonDelegation.call();
	}

	function levelChanged(){
		level = $(this).val();
		levelSelectionChangedDelegation(level);
	};

	this.setLevelSelectionDelegation = function(action){
		levelSelectionChangedDelegation = action;
	}

	function keyUp(e){
		var cellid = $(this).attr('id');
		var i = parseInt(cellid[1]);
		var j = parseInt(cellid[2]);

		var value = $(this).val();
		keyUpDelegation.call(value, i, j);
	}

	this.allCell().keyup(keyUp);
	this.allCell().keydown(varifyKeyInIsNumber);
	this.resetButton().click(resetTable);
	this.levelSelect().change(levelChanged);

	this.showTime = function(time){
		$('#timing').text(time);
	}
	this.seekAttentionToTimer = function(){
		$('#timing').addClass('animated pulse');
		$('#timing').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
			$('#timing').removeClass('animated pulse');
		});
	}

	this.lightBg = function(i, j){
		this.cellAt(i, j).addClass('warning-bg-light');
	}
	this.mediumBg = function(i, j){
		this.cellAt(i, j).addClass('warning-bg-medium');
	}
	this.darkBg = function(i, j){
		this.cellAt(i, j).addClass('warning-bg-dark');
	}
	this.clearBg = function(i, j){
		this.cellAt(i, j).removeClass('warning-bg-light warning-bg-medium warning-bg-dark');
	}
}


function BestTimeView() {
	this.bestEasyTime = function(){
		return $('#best-time-easy');
	}

	this.bestNormalTime = function(){
		return $('#best-time-normal');
	}

	this.bestHardTime = function(){
		return $('#best-time-hard');
	}

	this.bestEvilTime = function(){
		return $('#best-time-evil');
	}


	this.renderBestTime = function(bestTime){
		if(bestTime.easy == 0){
			this.bestEasyTime().text("--:--:--");
		}else{
			this.bestEasyTime().text(formatedTime(Number(bestTime.easy)));
		}

		if(bestTime.normal == 0){
			this.bestNormalTime().text("--:--:--");
		}else{
			this.bestNormalTime().text(formatedTime(Number(bestTime.normal)));
		}

		if(bestTime.hard == 0){
			this.bestHardTime().text("--:--:--");
		}else{
			this.bestHardTime().text(formatedTime(Number(bestTime.hard)));
		}

		if(bestTime.evil == 0){
			this.bestEvilTime().text("--:--:--");
		}else{
			this.bestEvilTime().text(formatedTime(Number(bestTime.evil)));
		}
	};
}

BouncedInAndOutAnnimation = function(wrapedAnn, annObjId, popUpText){
	var viewSelector = '#' + annObjId;
	this.start = function(){
		$('#puzzle-zone').append('<p id="'+ annObjId +'" class="animated bounceIn"></p>');
		$(viewSelector).text(popUpText);
		$(viewSelector).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
			$(viewSelector).removeClass("animated bounceIn");
			wrapedAnn.afterItsEnd(endAnimation);
			wrapedAnn.start();
		});
	}
	var afterEnd = function(){};

	var endAnimation = function(){
		$(viewSelector).addClass("animated bounceOut");
		$(viewSelector).one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',function(){
			$(viewSelector).remove();
			afterEnd();
		});
	}

	this.afterItsEnd = function(action){
		afterEnd = action;
	}
}

var puzzleView = new PuzzleView();
var bestTimeView = new BestTimeView();