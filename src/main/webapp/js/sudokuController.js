function loadPuzzle(){
	for(var i = 0; i < 9; i++){
		for(var j = 0; j < 9; j++){
			var cell = "#c"+i+j;
			$(cell).val(puzzle[i][j]);
		}
	}
}

