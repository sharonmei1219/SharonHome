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

	this.clear = function(i, j){
		puzzle[i][j] = '/';
	};

	this.get = function(i, j){
		return puzzle[i][j];
	};

	var size = this.size = {i:puzzle.length, j:puzzle[0].length};

	this.finished = function(){
		if (!_.every(puzzle, function(row){
			return !_.contains(row, '/');
		}))	return false;

		return _.every(_.range(size.i), function(i){
			return _.every(_.range(size.j), function(j){
				return validInput(i, j);
			});
		});
	}

	function Row(i){
		return function(){return _.map(_.range(0, puzzle.length), function(j){return {i:i, j:j}});}
	}

	function PosesInRow(i, pos){
		return function(){return _.map(pos, function(j){return{i:i, j:j}});};
	}

	function Column(j){
		return function(){return _.map(_.range(0, puzzle.length), function(i){return {i:i, j:j}});}
	}

	function PosesInColumn(j, pos){
		return function(){return _.map(pos, function(i){return{i:i, j:j}});};
	}

	function Block(i){
		return function(){
		var bc = i%blocksize;
		var br = (i-bc)/blocksize;
		function range(x){
			return _.range(x * blocksize, x * blocksize + blocksize)
		}
		return _.flatten(_.map(range(br), function(i){
			return _.map(range(bc), function(j){
				return {i:i, j:j};
			})
		}))}
	}

	function PosesInBlock(i, pos){
		return function(){
		var zone = Block(i)();
		return _.map(pos, function(j){
			return zone[j];
		})}
	}

	var detectDuplicate = function(zone){
		return duplicationDetector.detect(zone, puzzle);
	}

	this.validate = function(){
		var result = [];

		_.each(_.range(0, puzzle.length), function(i){
			var values = row(i);
			var duplicates = detectDuplicate(values);
			if(duplicates.length == 0) return;
			error = {zone: Row(i), 
					 spots: PosesInRow(i, duplicates)
					};
			result.push(error);
		})

		_.each(_.range(0, puzzle.length), function(i){
			var values = column(i);
			var duplicates = detectDuplicate(values);
			if(duplicates.length == 0) return;
			error = {zone: Column(i), 
					 spots: PosesInColumn(i, duplicates)
					};
			result.push(error);
		})

		var nrOfBlock = (puzzle.length * puzzle.length) / (blocksize * blocksize);
		_.each(_.range(0, nrOfBlock), function(i){
			var bc = i%blocksize;
			var br = (i-bc)/blocksize;
			
			var values = block(br * blocksize, bc * blocksize);

			var duplicates = detectDuplicate(values);
			if(duplicates.length == 0) return;
			error = {zone: Block(i), 
					 spots: PosesInBlock(i, duplicates)
					};
			result.push(error);
		})

		return result;
	}
}

duplicationDetector = {
	buildValueMap: function(values){
		return _.reduce(_.range(0, values.length), 
				 		function(valueMap, j){var value = values[j];
								if(typeof(valueMap[value]) == 'undefined') valueMap[value] = [j];
								else valueMap[value].push(j);
								return valueMap;
							},
							{});
	},

	findDuplicatesInMap: function(valueMap){
		var result = [];
		for(var value in valueMap){
			if(valueMap[value].length > 1 && value != '/'){
				_.each(valueMap[value], function(j){result.push(j)})
			}
		}
		return result;
	},

	detect: function(values){
		var valueMap = this.buildValueMap(values);
		return this.findDuplicatesInMap(valueMap);
	}
}