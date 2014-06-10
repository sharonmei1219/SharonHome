function PuzzleModel(puzzle, blocksize){

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

	var validInput = this.validInput = function(i, j){
		var v = puzzle[i][j];
		function vIsUniq(vs){return uniqIn(v, vs);};
		return _.every([row(i), column(j), block(i,j)], vIsUniq);
	}

	this.change = function(value, i, j){
		puzzle[i][j] = value;
	};

	this.get = function(i, j){
		return puzzle[i][j];
	};

	var size = this.size = {i:puzzle.length, j:puzzle[0].length};

	this.finished = function(){
		if (!_.every(puzzle, function(row){
			return !_.contains(row, '');
		}))	return false;

		return _.every(_.range(size.i), function(i){
			return _.every(_.range(size.j), function(j){
				return validInput(i, j);
			});
		});
	}
}
