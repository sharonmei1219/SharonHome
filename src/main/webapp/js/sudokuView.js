function PuzzleView(){
	this.put = function(value, i, j){
		this.cellAt(i, j).val(value);
	};

	this.cellAt = function(i, j){
		var cid = '#c' + i + j;
		return $(cid);
	};

	this.allInputCell = function(){
		return $('.cellInput');
	};

	this.clearButton = function(){
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
		this.clearButton().click(clearAction);
	};


	this.whenGetInputDo = function(inputAction){
		this.allInputCell().keyup(function(e){
			var cellid = $(this).attr('id');
			var i = parseInt(cellid[1]);
			var j = parseInt(cellid[2]);
			var key = e.keyCode ? e.keyCode : e.which;
			var value = String.fromCharCode(key);
			inputAction(value, i, j);
		});
	};

	this.varifyKeyInIsNumber = function(e){
		var key = e.keyCode ? e.keyCode : e.which;
		if((key == 46) || (key == 8)) return true; //backspace, delete
		if((key > 96) && (key < 106)) return true;
		if((key > 48) && (key < 58)) return true;
		return false;
	};

	this.allInputCell().keydown(this.varifyKeyInIsNumber);
}

function StopWatch(){
	var interval = 500;
	var clock = 0;
	function format(num){
		if (num < 10) return '0' + num;
		return '' + num;
	}

	function update(){
		clock += interval;
		var c = Math.floor(clock/1000);
		var hr = Math.floor(c/3600);
		c = c%3600;
		var min = Math.floor(c/60);
		c = c%60;
		var sec = c;

		hr = format(hr);
		min = format(min);
		sec = format(sec);

		$('#timing').text(hr +':'+min+':'+sec);

	}
	this.start = function(){
		setInterval(update, interval);
	};
}