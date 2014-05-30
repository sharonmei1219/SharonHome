function PuzzleModel(puzzle){
	var matrix = createMatrix(9, 9);

	this.valuesInArea = function(puzzle, area){
		var values = _.map(area, function(p){
			return puzzle[p.i][p.j];
		});

		return _.without(values, '');
	}

	this.validate = function(value, valueSet){
		var count = _.countBy(valueSet, function(num){
			return num == value ? 'value': 'other';
		})
		return count.value == 1;
	};

	this.change = function(value, i, j){
		puzzle[i][j] = value;
	};

	this.get = function(i, j){
		return puzzle[i][j];
	};

	this.validInput = function(i, j){
		var result = true;
		var row = retrieveRow(matrix, i);
		var rowValues = this.valuesInArea(puzzle, row);
		result = result && this.validate(this.get(i,j), rowValues);

		var column = retrieveColumn(matrix, j);
		var columnValues = this.valuesInArea(puzzle, column);
		result = result && this.validate(this.get(i,j), columnValues);

		var block = retrieveBlock(matrix, i, j);
		var blockValues = this.valuesInArea(puzzle, block);
		result = result && this.validate(this.get(i,j), blockValues);
		return result;
	};
}