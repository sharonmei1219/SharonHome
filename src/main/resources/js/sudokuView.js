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

	this.lock = function(i, j){
		var cell = this.cellAt(i, j);
		cell.attr('readonly', true);
		cell.addClass('fixedCell');
	};

	this.clear = function(i, j){
		this.cellAt(i, j).val('');
	};

	this.whenClearButtonClickedDo = function(clearAction){
		this.resetButton().click(clearAction);
	};

	this.varifyKeyInIsNumber = function(e){
		var key = e.keyCode ? e.keyCode : e.which;
		if((key == 46) || (key == 8)) return true; //backspace, delete
		if((key > 96) && (key < 106)) return true;
		if((key > 48) && (key < 58)) return true;
		return false;
	};

	function Delegation(){
		this.call = function(){};
	}

	var keyUpDelegation = new Delegation;

	this.setKeyUpDelegation = function(action){
		keyUpDelegation.call = action;
	}

	function keyUp(e){
		var cellid = $(this).attr('id');
		var i = parseInt(cellid[1]);
		var j = parseInt(cellid[2]);
		var value = $(this).val();
		keyUpDelegation.call(value, i, j);
	}

	this.allCell().keyup(keyUp);
	this.allCell().keydown(this.varifyKeyInIsNumber);

	this.showTime = function(time){
		$('#timing').text(time);
	}
}