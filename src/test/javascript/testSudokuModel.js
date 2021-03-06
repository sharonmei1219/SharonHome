describe('sudokuModel', function(){
	var sm = new PuzzleModel([['', '', ''], ['', '', ''], ['', '', '']]);
	it('changes value at (1, 2) to \'9\', when change(9, 1, 2) called', function(){
		sm.change('9', 1, 2);
		expect(sm.get(1, 2)).toBe('9');
	});
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

	it('gives result of toString()', function(){
		result = model.toString();
		// expect("[['1', '2'],['3','4']]", result)
		expect(result).toBe('[["1","2"],["3","4"]]')
	})

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
		expect(model.validInput(1,1)).toBe(true);
	});

	it('validate returns false, if there is a duplication in row', function(){
		var model = new PuzzleModel([['1','1','',''],
									 ['1','2','2','1'],
									 ['','1','',''],
									 ['','1','','']], 2);
		expect(model.validInput(1,1)).toBe(false);
	});

	it('validate returns false, if there is a duplication in column', function(){
		var model = new PuzzleModel([['1','1','',''],
									 ['1','2','1','1'],
									 ['','2','',''],
									 ['','1','','']], 2);
		expect(model.validInput(1,1)).toBe(false);
	});

	it('validate returns false, if there is a duplication in block', function(){
		var model = new PuzzleModel([['2','1','',''],
									 ['1','2','1','1'],
									 ['','1','',''],
									 ['','1','','']], 2);
		expect(model.validInput(1,1)).toBe(false);
	});

	it('returns false when not all cell is filled', function(){
		var model = new PuzzleModel([['1','2'],['3','/']],2);
		expect(model.finished()).toBe(false);
	});

	it('returns false if there is column contains duplicate number', function(){
		var model = new PuzzleModel([['1','2'],['3','2']], 1);
		expect(model.finished()).toBe(false);
	});

	it('returns false if there is a row contains duplicate number', function(){
		var model = new PuzzleModel([['1','1'],['3','4']], 1);
		expect(model.finished()).toBe(false);
	});

	it('returns false if there is a block contains duplicate number', function(){
		var model = new PuzzleModel([['1','2'],['3','1']], 2);
		expect(model.finished()).toBe(false);
	});

	it('returns true if there is no vialation', function(){
		var model = new PuzzleModel([['1','2'],['3','4']], 2);
		expect(model.finished()).toBe(true);
	});
});

describe('duplicationDetector', function(){
	it('builds value Map in which, value is key, and pos are the values', function(){
		var map = duplicationDetector.buildValueMap(['/', '1', '1', '2'])
		expect(map['/']).toEqual([0]);
		expect(map['1']).toEqual([1, 2]);
		expect(map['2']).toEqual([3]);
	})

	it('extracts those poses have duplicated value from map', function(){
		map={'/': [0, 1],
			 '1': [2, 3],
			 '2': [4]};
		var dup = duplicationDetector.findDuplicatesInMap(map);
		expect(dup).toEqual([2, 3]);
	})
})

describe('Error Detection', function(){
	it('returns row and duplicated cells in the row', function(){
		var model = new PuzzleModel([[ '/', '1', '1', '/'],
									 [ '/', '/', '/', '/'],
									 [ '2', '/', '2', '/'],
									 [ '/', '/', '/', '/']], 2);
		var errors = model.validate();
		expect(errors.length).toBe(2);
		expect(errors[0].zone()).toEqual([{i:0, j:0},
										 {i:0, j:1},
										 {i:0, j:2},
										 {i:0, j:3}
										]);
		expect(errors[0].spots()).toEqual([{i:0, j:1},
										 {i:0, j:2}
										]);

		expect(errors[1].zone()).toEqual([{i:2, j:0},
										 {i:2, j:1},
										 {i:2, j:2},
										 {i:2, j:3}
										]);
		expect(errors[1].spots()).toEqual([{i:2, j:0},
										 {i:2, j:2}
										]);
	})

	it('returns column and duplicated cells in the column', function(){
		var model = new PuzzleModel([[ '/', '1', '/', '/'],
									 [ '/', '/', '/', '/'],
									 [ '/', '1', '/', '/'],
									 [ '/', '/', '/', '/']], 2);
		var errors = model.validate();
		expect(errors.length).toBe(1);
		expect(errors[0].zone()).toEqual([{i:0, j:1},
										  {i:1, j:1},
										  {i:2, j:1},
										  {i:3, j:1}
										]);
		expect(errors[0].spots()).toEqual([{i:0, j:1},
										 {i:2, j:1}
										]);
	})

	it('returns block and duplicated cells in the block', function(){
		var model = new PuzzleModel([['/', '1', '/', '/'],
									 ['1', '/', '/', '/'],
									 ['/', '/', '/', '/'],
									 ['/', '/', '/', '/']], 2);
		var errors = model.validate();
		expect(errors.length).toBe(1);
		expect(errors[0].zone()).toEqual([{i:0, j:0},
										  {i:0, j:1},
										  {i:1, j:0},
										  {i:1, j:1}
										]);
		expect(errors[0].spots()).toEqual([{i:0, j:1},
										 {i:1, j:0}
										]);
	})
});