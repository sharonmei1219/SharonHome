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

describe('SudokuController', function(){
  var sv = new PuzzleView();
  var sm = new PuzzleModel([['1',''],['','2']]);
  var sc = new PuzzleController(sv, sm);
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

  it('unlock cell at pos (0,0) and (1,1) in view when unlockPuzzle', function(){
    spyOn(sv, 'unlock');
    sc.unlockPuzzle();
    expect(sv.unlock).toHaveBeenCalledWith(0, 0);
    expect(sv.unlock).toHaveBeenCalledWith(1, 1);
  });

  it('clear cell at pos (0,1) and (1,0) in view when receive clear command', function(){
    spyOn(sv, 'clear');
    sc.clearSolution();
    expect(sv.clear).toHaveBeenCalledWith(0, 1);
    expect(sv.clear).toHaveBeenCalledWith(1, 0);
  });

  it('clear cell at pos (0,1) and (1,0) in view when clear puzzle', function(){
    spyOn(sv, 'clear');
    sc.clearPuzzle();
    expect(sv.clear).toHaveBeenCalledWith(0, 0);
    expect(sv.clear).toHaveBeenCalledWith(1, 1);
  });

  it('put input to model, and ask model to validate it', function(){
    spyOn(sm, 'change');
    spyOn(sm, 'finished').andReturn(false);
    sc.numberInput('9', 1, 2);
    expect(sm.change).toHaveBeenCalledWith('9', 1, 2);
    expect(sm.finished).toHaveBeenCalled();
  });
});

describe('Best Time Record', function(){
  it('read from localStorage and put to view', function(){
      var bestTime = {
        easy:0,
        normal:0,
        hard:0,
        evil:0
      };
      spyOn(userInfo, 'getBestTime').andReturn(bestTime);
      spyOn(bestTimeView, 'renderBestTime');

      onDocReady();
      expect(userInfo.getBestTime).toHaveBeenCalled();
      expect(bestTimeView.renderBestTime).toHaveBeenCalledWith(bestTime);
  });

  it('Best Time Refresed Solved the First Puzzle', function(){
      sudokulevel = 'easy';
      spyOn(timer, 'stop').andReturn(1000);
      spyOn(userInfo, 'getBestTime').andReturn({easy:0, normal:0, hard:0, evil:0});
      spyOn(userInfo, 'setBestTime');
      spyOn(bestTimeView,'renderBestTime');

      puzzleFinished();

      expect(timer.stop).toHaveBeenCalled();
      expect(userInfo.getBestTime).toHaveBeenCalled();
      expect(userInfo.setBestTime).toHaveBeenCalledWith({easy:'1000', normal:0, hard:0, evil:0});
      expect(bestTimeView.renderBestTime).toHaveBeenCalledWith({easy:'1000', normal:0, hard:0, evil:0});
  })


  it('Best Time Refreshed When solving time is shorter than best time', function(){
      // sudokulevel = 'easy';
      levelCtrl.levelChanged('easy');
      spyOn(timer, 'stop').andReturn(1000);
      spyOn(userInfo, 'getBestTime').andReturn({easy:'2000', normal:0, hard:0, evil:0});
      spyOn(userInfo, 'setBestTime');
      spyOn(bestTimeView,'renderBestTime');

      puzzleFinished();

      expect(timer.stop).toHaveBeenCalled();
      expect(userInfo.getBestTime).toHaveBeenCalled();
      expect(userInfo.setBestTime).toHaveBeenCalledWith({easy:'1000', normal:0, hard:0, evil:0});
      expect(bestTimeView.renderBestTime).toHaveBeenCalledWith({easy:'1000', normal:0, hard:0, evil:0});
  })

  it('Best Time Not Refreshed When solving time is greater than best time', function(){
      sudokulevel = 'easy';
      spyOn(timer, 'stop').andReturn(1000);
      spyOn(userInfo, 'getBestTime').andReturn({easy:'900', normal:0, hard:0, evil:0});
      spyOn(userInfo, 'setBestTime');
      spyOn(bestTimeView,'renderBestTime');

      puzzleFinished();

      expect(timer.stop).toHaveBeenCalled();
      expect(userInfo.getBestTime).toHaveBeenCalled();
   })

})

describe('JSON', function(){
  it('turns var to JSOn string', function(){
    var string = JSON.stringify({x:5});
    expect(string).toEqual('{\"x\":5}');
  })
})