describe('outputToCell', function(){
	var sv = new PuzzleView();
	it('find cell at spot i,j, and set its value', function(){
		function MockJQueryObj(){
			this.val = function(){};
		}
		var mockCell = new MockJQueryObj();
		spyOn(sv, 'cellAt').andReturn(mockCell);

		sv.put('2', 0, 1);
		expect(sv.cellAt).toHaveBeenCalledWith(0, 1);

	});
});