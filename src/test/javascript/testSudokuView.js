describe('PuzzleView', function(){
	var sv = new PuzzleView();
	function MockJQueryObj(){
		this.val = function(){};
		this.attr = function(){};
		this.addClass = function(){};
		this.click = function(){};
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

	it('put --:--:-- when 0 is given', function(){
      easyTime = new MockJQueryObj();
      spyOn(easyTime,'val');
      spyOn(sv, 'bestEasyTime').andReturn(easyTime);
      sv.renderBestTime({easy:0, normal:0, hard:0, evil:0});
      expect(sv.bestEasyTime).toHaveBeenCalled();
      expect(easyTime.val).toHaveBeenCalledWith('--:--:--');
  	})

  	it('put 01:01:01 when 3661 is given', function(){
      easyTime = new MockJQueryObj();
      spyOn(easyTime,'val');
      spyOn(sv, 'bestEasyTime').andReturn(easyTime);
      sv.renderBestTime({easy:'3661000', normal:0, hard:0, evil:0});
      expect(sv.bestEasyTime).toHaveBeenCalled();
      expect(easyTime.val).toHaveBeenCalledWith('01:01:01');
  	})

  	it('put 01:01:01 as normal best time when 3661 is given', function(){
      normalTime = new MockJQueryObj();
      spyOn(normalTime,'val');
      spyOn(sv, 'bestNormalTime').andReturn(normalTime);
      sv.renderBestTime({easy:0, normal:'3661000', hard:0, evil:0});
      expect(sv.bestNormalTime).toHaveBeenCalled();
      expect(normalTime.val).toHaveBeenCalledWith('01:01:01');
  	})

  	it('put 01:01:01 as hard best time when 3661 is given', function(){
      hardTime = new MockJQueryObj();
      spyOn(hardTime,'val');
      spyOn(sv, 'bestHardTime').andReturn(hardTime);
      sv.renderBestTime({easy:0, normal:0, hard:'3661000', evil:0});
      expect(sv.bestHardTime).toHaveBeenCalled();
      expect(hardTime.val).toHaveBeenCalledWith('01:01:01');
  	})

  	 it('put 01:01:01 as evil best time when 3661 is given', function(){
      evilTime = new MockJQueryObj();
      spyOn(evilTime,'val');
      spyOn(sv, 'bestEvilTime').andReturn(evilTime);
      sv.renderBestTime({easy:0, normal:0, hard:0, evil:'3661000'});
      expect(sv.bestEvilTime).toHaveBeenCalled();
      expect(evilTime.val).toHaveBeenCalledWith('01:01:01');
  	})
});