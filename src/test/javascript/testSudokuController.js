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
  	var fixedCell = cellsWithNumber([cell_0, cell_1, cell_2]);
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

describe('createMatrix', function(){
  it('gives an array of 6 elements to create a mextrix of 2*3', function(){
    var matrix = createMatrix(2, 3);
    expect(matrix.length).toBe(6);
  });

  it('gives an array of 4 elements to create 2*2 matrix, each stands for a spot in matrix', function(){
    var matrix = createMatrix(2, 2);
    var spots = [{i:0, j:0}, {i:0, j:1}, {i:1, j:0}, {i:1, j:1}];
    for (var i = 0; i < 4; i++){
      expect(matrix[i].i).toBe(spots[i].i);
      expect(matrix[i].j).toBe(spots[i].j);
    }
  })
});

describe('tellSpotsContainsNumberVsBlankSpots', function(){
  var spotsSubset = tellSpotsContainsNumberVsBlankSpots([['1', '', ''],['','1','1']]);
  var nonBlankSpots = [{i:0,j:0},{i:1,j:1},{i:1,j:2}];
  var blankSpots = [{i:0,j:1},{i:0,j:2},{i:1,j:0}];
  it('gives 2 subset of spots', function(){
    expect(spotsSubset.length).toBe(2);
  });

  it('first subset is non blank subset, contains 3 spots (0,0) (1,1) (1,2)', function(){
    expect(spotsSubset[0].length).toBe(3);
    for(var i = 0; i < 3; i++){
      expect(spotsSubset[0][i].i).toBe(nonBlankSpots[i].i);
      expect(spotsSubset[0][i].j).toBe(nonBlankSpots[i].j);
    }
  });

  it('second subset is blank subset, contains 3 spots (0,1) (0,2) (1,0)', function(){
    expect(spotsSubset[1].length).toBe(3);
    for(var i = 0; i < 3; i++){
      expect(spotsSubset[1][i].i).toBe(blankSpots[i].i);
      expect(spotsSubset[1][i].j).toBe(blankSpots[i].j);
    }
  });
});

describe('SudokuController load model to view', function(){
  function MockView(){
    this.put = function(value, i, j){};
  }
  var sv = new MockView();
  var sc = new PuzzleController([['1',''],['','2']], sv);
  it('put 1, 2 to pos (0,0) and (1,1) in view', function(){
    spyOn(sv, 'put');
    sc.loadPuzzleNew();
    expect(sv.put).toHaveBeenCalledWith('1', 0, 0);
    expect(sv.put).toHaveBeenCalledWith('2', 1, 1);
  });
});