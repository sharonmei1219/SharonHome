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

describe('SudokuController', function(){
  var sv = new PuzzleView();
  var sc = new PuzzleController([['1',''],['','2']], sv);
  it('put 1, 2 to pos (0,0) and (1,1) in view when loadPuzzle', function(){
    spyOn(sv, 'put');
    sc.loadPuzzleNew();
    expect(sv.put).toHaveBeenCalledWith('1', 0, 0);
    expect(sv.put).toHaveBeenCalledWith('2', 1, 1);
  });

  it('lock cell at pos (0,0) and (1,1) in view when lockPuzzle', function(){
    spyOn(sv, 'lock');
    sc.lockPuzzle();
    expect(sv.lock).toHaveBeenCalledWith(0, 0);
    expect(sv.lock).toHaveBeenCalledWith(1, 1);
  });
});

