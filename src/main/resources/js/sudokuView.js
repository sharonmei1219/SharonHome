function PuzzleView(){
	this.put = function(value, i, j){
		this.cellAt(i, j).val(value);
	};

	this.cellAt = function(i, j){
		var cid = '#c' + i + j;
		return $(cid);
	};

	this.cellCellAt = function(i, j){
		var cid = '#cell-c' + i + j;
		return $(cid);
	}

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

	this.putHint = function(i, j, value){
		var cell = this.cellCellAt(i, j)
		cell.append('<div class="speechRape"><p class="speech">' + value + '</p></div>')
		$('.cellInput', cell)[0].focus()
	}

	this.clearHint = function(i, j){
		var cell = this.cellCellAt(i, j)
		$('.speechRape', cell).remove()
		$('#hint-zone').empty()
	}

	this.putHintName = function(name, mouseEnterFun, mouseLeaveFun){
		hint = $('<p>' + name + '</p>')
		hint.appendTo('#hint-zone')
		hint.mouseenter(mouseEnterFun)
		hint.mouseleave(mouseLeaveFun)
	}

	this.removeHighLight = function(poses){
		for(i in poses){
			pos = poses[i]
			cell = this.cellCellAt(pos[0], pos[1])
			$('.hint', cell).remove()
		}
	}

	this.highLight = function(poses, contents){
		for(i in poses){
			pos = poses[i]
			cell = this.cellCellAt(pos[0], pos[1])
			cell.append('<p class="hint">' + contents + '</p>')
		}
	}

	this.inputNote = function(i, j){
		var cellArea = this.cellCellAt(i, j);
		$('.notetext', cellArea).remove();
		cellArea.append('<input type="text" class="note-input">');
		var inputBox = $('.note-input', cellArea);

		inputBox.focus();

		inputBox.keyup(function(e) {
  			if (e.keyCode == 27) { 
  				puzzleView.cellAt(i, j).focus();
  				this.remove();
  			}   // esc
		});

		inputBox.blur(function() {
  				this.remove();
		});

		inputBox.change(function(){
			var inputText = $(this).val();
			cellArea.append('<div class="row notetext"><p>'+inputText+' <a class="glyphicon glyphicon-remove close-note"></a></p></div>');
			$('.close-note', cellArea).click(function(){$('.notetext', cellArea).remove();});
			puzzleView.cellAt(i, j).focus();
			$(this).remove();
		})

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

	function parseCellid(cellid){
		return {i: parseInt(cellid[1]), j:parseInt(cellid[2])};
	}

	function keyUp(e){
		var key = e.keyCode ? e.keyCode : e.which;
		var value = $(this).val();
		var pos = parseCellid($(this).attr('id'));
		if((key >= 37) && (key <= 40) || (key == 9)) return true;
		keyUpDelegation.call(value, pos.i, pos.j);
	}

	function keydown(e){
		var pos = parseCellid($(this).attr('id'));
		var key = e.keyCode ? e.keyCode : e.which;
		if((key == 46) || (key == 8)) return true; //backspace, delete
		if((key > 96) && (key < 106) || (key > 48) && (key < 58)) return true; //number key
		if((key >= 37) && (key <= 40) || (key == 9)) {
			moveAround(pos.i, pos.j, key);
			return true;
		}
		return false;
	};

	var moveAroundCtrl = {};
	this.setMoveAroundCtrl = function(ctrl){
		moveAroundCtrl = ctrl;
	}

	function moveAround(i, j, key){
		pos = '{i: ' + i + ', j: ' + j + '}';
		if(key == 37){
			moveAroundCtrl.stepLeft(i, j);
		}
		if(key == 38){
			moveAroundCtrl.stepUp(i, j);
		}
		if(key == 39){
			moveAroundCtrl.stepRight(i, j);
		}
		if(key == 40){
			moveAroundCtrl.stepDown(i, j);
		}
	}

	this.focus = function(i, j){
		this.cellAt(i,j).focus();
	}

	this.allCell().keyup(keyUp);
	this.allCell().keydown(keydown);
	this.allCell().contextMenu({
    	menuSelector: "#contextMenu",
    	menuSelected: function (invokedOn, selectedMenu) {
        	if(selectedMenu.text() == 'Add Note'){
				var pos = parseCellid(invokedOn.attr('id'));
        		puzzleView.inputNote(pos.i, pos.j);
        	}
   	 	}
	});

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