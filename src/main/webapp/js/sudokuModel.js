function PuzzleModel(puzzle){
	this.change = function(value, i, j){
		puzzle[i][j] = value;
	};

	this.get = function(i, j){
		return puzzle[i][j];
	};
}