describe('sudokuModel', function(){
	var sm = new PuzzleModel([['', '', ''], ['', '', ''], ['', '', '']]);
	it('changes value at (1, 2) to \'9\', when change(9, 1, 2) called', function(){
		sm.change('9', 1, 2);
		expect(sm.get(1, 2)).toBe('9');
	});

	var newMatrix = new Matrix(4, 4);

	it('retrieve row 2 will get spots (2,0) to (2,3)', function(){
		var spots = newMatrix.retrieveRow(2);
		var row2 = [{i:2, j:0},
					{i:2, j:1},
					{i:2, j:2},
					{i:2, j:3}];
		for(var i = 0; i < 4; i++){
			expect(spots[i].i).toBe(row2[i].i);
			expect(spots[i].j).toBe(row2[i].j);
		}
	});

	it('retrieve column 2 will get spots (0,2) to (3,2)', function(){
		var spots = newMatrix.retrieveColumn(2);
		var column = [{i:0, j:2},
					  {i:1, j:2},
					  {i:2, j:2},
					  {i:3, j:2}];
		for(var i = 0; i < 4; i++){
			expect(spots[i].i).toBe(column[i].i);
			expect(spots[i].j).toBe(column[i].j);
		}
	});

	it('retrieve a block of size 2 at spot (1,2) will get (0-1,2-3)', function(){
		var spots = newMatrix.retrieveBlock(1, 2, 2);
		var block = [{i:0,j:2},
					 {i:0,j:3},
					 {i:1,j:2},
					 {i:1,j:3}];
		for(var i = 0; i < 4; i++){
			expect(spots[i].i).toBe(block[i].i);
			expect(spots[i].j).toBe(block[i].j);
		}
	});


	it('returns non \'\' value of puzzle in a certain area', function(){
		var area = [{i:0, j:0}, {i:0, j:1}, {i:1, j:0}, {i:1, j:1}];
		var puzzle = [['1', '', ''], ['', '2', '3'], ['8', '8', '8']]
		var values = sm.valuesInArea(puzzle, area);
		expect(values.length).toBe(2);
		expect(values[0]).toBe('1');
		expect(values[1]).toBe('2');
	});

	it('validate a value in set if its uniq', function(){
		var valueset = ['1', '2', '2'];
		expect(sm.isValueUniq('1', valueset)).toBe(true);
		expect(sm.isValueUniq('2', valueset)).toBe(false);
	})
});

describe('Model [[\'1\', \'2\'],[\'3\',\'4\']] with block size of 1', function(){
	var model = new PuzzleModel([['1', '2'],['3','4']], 1);
	it('gives [\'1\',\'2\'] as row(0)', function(){
		var result = model.row(0);
		expect(result[0]).toBe('1');
		expect(result[1]).toBe('2');
	});

	it('gives [\'3\', \'4\'] as row(1)', function(){
		var result = model.row(1);
		expect(result[0]).toBe('3');
		expect(result[1]).toBe('4');
	});

	it('gives [\'1\', \'3\'] as column(0)', function(){
		expect(model.column(0)).toEqual(['1','3']);
	});

	it('gives [\'2\', \'4\'] as column(1)', function(){
		var result = model.column(1);
		expect(result[0]).toBe('2');
		expect(result[1]).toBe('4');
	});

	it('gives [\'1\'] as block(0,0)', function(){
		var result = model.block(0, 0);
		expect(result.length).toBe(1);
		expect(result[0]).toBe('1');
	});

	it('gives [\'4\'] as block(1,1)', function(){
		var result = model.block(1, 1);
		expect(result.length).toBe(1);
		expect(result[0]).toBe('4');
	});

});

describe('Model size of 2', function(){
	var model = new PuzzleModel([['1','2','',''],
		                         ['3','4','',''],
		                         ['','','5','6'],
		                         ['','','7','8']], 2);
	it('gives [\'1\',\'2\',\'3\',\'4\'] as block(1, 1) of model (1,2,,,3,4,,,...)', function(){
		expect(model.block(2,3)).toEqual(['5','6','7','8']);
		expect(model.block(1,1)).toEqual(['1','2','3','4']);
	});

	it('returns true, checking \'a\' uniq in [\'a\',\'b\']', function(){
		expect(model.uniqIn('a', ['a','b','c'])).toBe(true);
	})

	it('validate returns true, if no duplication numbers in row, column and block', function(){
		var model = new PuzzleModel([['1','1','',''],
									 ['1','2','1','1'],
									 ['','1','',''],
									 ['','1','','']], 2);
		expect(model.newValidateInput(1,1)).toBe(true);
	});

	it('validate returns false, if there is a duplication in row', function(){
		var model = new PuzzleModel([['1','1','',''],
									 ['1','2','2','1'],
									 ['','1','',''],
									 ['','1','','']], 2);
		expect(model.newValidateInput(1,1)).toBe(false);
	});

	it('validate returns false, if there is a duplication in column', function(){
		var model = new PuzzleModel([['1','1','',''],
									 ['1','2','1','1'],
									 ['','2','',''],
									 ['','1','','']], 2);
		expect(model.newValidateInput(1,1)).toBe(false);
	});

	it('validate returns false, if there is a duplication in block', function(){
		var model = new PuzzleModel([['2','1','',''],
									 ['1','2','1','1'],
									 ['','1','',''],
									 ['','1','','']], 2);
		expect(model.newValidateInput(1,1)).toBe(false);
	});
});