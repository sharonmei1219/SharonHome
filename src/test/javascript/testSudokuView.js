describe('PuzzleView', function(){
	var sv = new PuzzleView();
	function MockJQueryObj(){
		this.val = function(){};
		this.attr = function(){};
		this.addClass = function(){};
		this.click = function(){};
    this.text = function(){};
	}
	var mockCell = new MockJQueryObj();
	var mockClearButton = new MockJQueryObj();

	it('find cell at spot, and set its value, when put a value to a spot', function(){
		spyOn(sv, 'cellAt').andReturn(mockCell);
		spyOn(mockCell, 'val');
		sv.put('2', 0, 1);
		expect(sv.cellAt).toHaveBeenCalledWith(0, 1);
		expect(mockCell.val).toHaveBeenCalledWith('2');
	});

	it('find cell at spot, and set its readonly property to true and append fixedCell class', function(){
		spyOn(sv, 'cellAt').andReturn(mockCell);
		spyOn(mockCell,'attr');
		spyOn(mockCell,'addClass');
		sv.lock(0, 1);
		expect(sv.cellAt).toHaveBeenCalledWith(0, 1);
		expect(mockCell.attr).toHaveBeenCalledWith('readonly', true);
		expect(mockCell.addClass).toHaveBeenCalledWith('fixedCell');
	});


	it('find cell and set its value to \'\', when clear the cell', function(){
		spyOn(sv, 'cellAt').andReturn(mockCell);
		spyOn(mockCell, 'val');
		sv.clear(1, 2);
		expect(sv.cellAt).toHaveBeenCalledWith(1, 2);
		expect(mockCell.val).toHaveBeenCalledWith('');
	});
});