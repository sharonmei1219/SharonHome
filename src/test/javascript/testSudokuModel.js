describe('sudokuModel', function(){
	var sm = new PuzzleModel([['', '', ''], ['', '', ''], ['', '', '']]);
	it('changes value at (1, 2) to \'9\', when change(9, 1, 2) called', function(){
		sm.change('9', 1, 2);
		expect(sm.get(1, 2)).toBe('9');
	});


	var matrix = createMatrix(9, 9);
	it('retrieve row #2 from matrix will get spots (2,0) to (2,8)', function(){
		var spots = retrieveRow(matrix, 2);
		var row2 = [{i:2, j:0},
					{i:2, j:1},
					{i:2, j:2},
					{i:2, j:3},
					{i:2, j:4},
					{i:2, j:5},
					{i:2, j:6},
					{i:2, j:7},
					{i:2, j:8}];
		for(var i = 0; i < 9; i++){
			expect(spots[i].i).toBe(row2[i].i);
			expect(spots[i].j).toBe(row2[i].j);
		}
	});

	it('retrieve column #3 from matrix will get spots (0,3) to (8,3)', function(){
		var spots = retrieveColumn(matrix, 3);
		var  column3 = [{i:0,j:3},
						{i:1,j:3},
						{i:2,j:3},
						{i:3,j:3},
						{i:4,j:3},
						{i:5,j:3},
						{i:6,j:3},
						{i:7,j:3},
						{i:8,j:3}];
		for(var i = 0; i < 9; i++){
			expect(spots[i].i).toBe(column3[i].i);
			expect(spots[i].j).toBe(column3[i].j);
		}
	});

	it('retrieve block with (4,7) inside will spots get (3-5, 6-8)', function(){
		var spots = retrieveBlock(matrix, 4, 7);
		var block = [{i:3,j:6},
					 {i:3,j:7},
					 {i:3,j:8},
					 {i:4,j:6},
					 {i:4,j:7},
					 {i:4,j:8},
					 {i:5,j:6},
					 {i:5,j:7},
					 {i:5,j:8}];

		for(var i = 0; i < 9; i++){
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
		expect(sm.validate('1', valueset)).toBe(true);
		expect(sm.validate('2', valueset)).toBe(false);
	})
});