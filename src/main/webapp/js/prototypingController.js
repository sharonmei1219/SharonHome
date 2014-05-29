
// pos of m * n matrix
function matrix(m, n) {
	var result = _.flatten(
		_.map(_.range(m), function(i){
			return _.map(_.range(n), function(j){
				return _.object(['i','j'],[i,j]);
			});
		}));
	return result;
}

function tellFilledVsVacantPos(puzzle){
	var positions = matrix(puzzle.length, puzzle[0].length);
	return _.partition(positions, function(p){return puzzle[p.i][p.j] != '';});
}
// anything concern with dom operation $ jQuery, move to table.jsp as view part
function lockCell(cell){
	cell.attr('readonly', true);
	cell.addClass('fixedCell');
}

function emptyCell(cell){
	cell.val('');
}

// cellAt
// parse cell Id
// clean cell
// cell output
// empty cell


function PuzzleController(pz){
	var filledAndVacentPos = tellFilledVsVacantPos(pz);
	var originalFilledPos = filledAndVacentPos[0];
	var vacantPos = filledAndVacentPos[1];

	this.cellAt = function(p){
		return $('#c' + p.i + p.j);
	};

	this.outputPuzzle = function(){
		_.each(originalFilledPos, function(p){
			this.cellAt(p).val(pz[p.i][p.j]);
		})
	};

	this.lockPuzzle = function(){
		_.each(originalFilledPos, function(p){
			lockCell(this.cellAt(p));
		});
	};

	this.clearSolution = function(){
		_.each(vacantPos, function(p){
			empty(this.cellAt(p));
		});
	};

	this.clearPuzzle = function(){
		_.each(originalFilledPos, function(p){
			clean(this.cellAt(p));
		});
	};

	this.inputFromCell = function(cell){
		var i = parseInt(cell.attr('id')[1]);
		var j = parseInt(cell.attr('id')[2]);
		pz[i][j] = cell.val;
	};

	this.validateInput = function(){
		validate(pz);
	}
}