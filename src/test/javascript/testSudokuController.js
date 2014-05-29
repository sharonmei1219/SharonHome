describe("Loading puzzle", function() {
  it("returns 1 given c10 as input of function getX", function() {
    expect(true).toBe(true);
  });

  function MockJQueryObj(){
  	this.val = function(){return this.value;};
  	this.attr = function(){};
  	this.addClass = function(){};
  }

  var cell_0 = new MockJQueryObj();
  cell_0.value = "1";

  var cell_1 = new MockJQueryObj();
  cell_1.value = "2";

  var cell_2 = new MockJQueryObj();
  cell_2.value = "";

  it("returns true when testing if cell_0 is fixed cell", function() {
  	expect(withNumberInSide(cell_0)).toBe(true);
  });

  it("returns cell_0 and cell_1 as the result of filtering fixed cell", function(){
  	var fixedCell = filterCellsWithNumber([cell_0, cell_1, cell_2]);
  	expect(fixedCell[0]).toBe(cell_0);
  	expect(fixedCell[1]).toBe(cell_1);
  });

  it("disable the input to fix a cell", function(){
  	spyOn(cell_0, "attr");
  	fixCell(cell_0);
  	expect(cell_0.attr).toHaveBeenCalledWith('readonly', true);
  });

  it("adds class qualifier fixedCell to fix a cell", function(){
  	spyOn(cell_0, "addClass");
  	fixCell(cell_0);
  	expect(cell_0.addClass).toHaveBeenCalledWith('fixedCell');
  })

});