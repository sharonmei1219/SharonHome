function PuzzleModel(puzzle, blocksize){
	var matrix = createMatrix(9, 9);
	var newMatrix = new Matrix(9, 9);

	var row = this.row = function(i){
		return puzzle[i];
	};

	var column = this.column = function(j){
		return _.pluck(puzzle, j);
	};

	var block = this.block = function(i, j){
		function range(x) {
			return _.range(x - x%blocksize, x - x%blocksize + blocksize);
		};

		return _.flatten(_.map(range(i), function(x){
			return _.map(range(j), function(y){
				return puzzle[x][y];
			});
		}));
	};

	var uniqIn = this.uniqIn = function(v, vs){
		return _.countBy(vs, function(x){
			return v == x?'countV':'others';
		}).countV == 1;
	}

	this.newValidateInput = function(i, j){
		var v = puzzle[i][j];
		function vIsUniq(vs){return uniqIn(v, vs);};
		return _.every([row(i), column(j), block(i,j)], vIsUniq);
	}


	this.valuesInArea = function(puzzle, area){
		var values = _.map(area, function(p){
			return puzzle[p.i][p.j];
		});

		return _.without(values, '');
	}

	this.isValueUniq = function(value, valueSet){
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
		var row = newMatrix.retrieveRow(i);
		var rowValues = this.valuesInArea(puzzle, row);
		result = result && this.isValueUniq(this.get(i,j), rowValues);

		var column = newMatrix.retrieveColumn(j);
		var columnValues = this.valuesInArea(puzzle, column);
		result = result && this.isValueUniq(this.get(i,j), columnValues);

		var block = newMatrix.retrieveBlock(i, j, 3);
		var blockValues = this.valuesInArea(puzzle, block);
		result = result && this.isValueUniq(this.get(i,j), blockValues);
		return result;
	};
}

function Matrix(m, n){
	var matrix = _.flatten(
					_.map(_.range(m), function(i){
						return _.map(_.range(n), function(j){
							return _.object(['i','j'],[i,j]);
						});
					}));

	this.retrieveRow = function(i){
		return _.filter(matrix, function(p){return p.i == i;});
	};

	this.retrieveColumn = function(j){
		return _.filter(matrix, function(p){return p.j == j;});
	};

	this.retrieveBlock = function(i, j, size){
		var b = _.map([i, j], function(x){return x - x%size});
		return _.filter(matrix, function(p){
			return (p.i >= b[0]) && (p.i < b[0] + size) 
		    	&& (p.j >= b[1]) && (p.j < b[1] + size);
		});		
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