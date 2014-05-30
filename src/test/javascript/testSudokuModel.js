describe('sudokuModel', function(){
	var sm = new PuzzleModel([['', '', ''], ['', '', ''], ['', '', '']]);
	it('changes value at (1, 2) to \'9\', when change(9, 1, 2) called', function(){
		sm.change('9', 1, 2);
		expect(sm.get(1, 2)).toBe('9');
	});
});