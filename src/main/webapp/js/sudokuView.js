function PuzzleView(){
	this.put = function(value, i, j){
		this.cellAt(i, j).val(value);
	};

	this.cellAt = function(i, j){
		var cid = '#c' + i + j;
		return $(cid);
	};
}