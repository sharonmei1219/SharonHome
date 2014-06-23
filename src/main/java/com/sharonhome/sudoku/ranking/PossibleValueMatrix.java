package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class PossibleValueMatrix {
	PossibleValues [][] matrix = new PossibleValues[9][9];
	static final int BLOCK_SIZE = 3;
	private ArrayList<Pair> pairList;

	public PossibleValueMatrix(Puzzle puzzle) {
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				matrix[i][j] = new PossibleValues();
		
		ArrayList<Single> determinedValues = puzzle.getDeterminedValues();
		for(Single dv : determinedValues)
			update(dv);
		
		this.pairList = new ArrayList<Pair>();
		
	}

	public ArrayList<Single> findNewSingle(ArrayList<Single> singlelist) {
		ArrayList<Single> result = new ArrayList<Single>();
		for(int row = 0; row < 9; row ++){
			for(int cell = 0; cell < 9; cell ++){
				if(matrix[row][cell].hasSingleValue()){
					result.add(new Single(matrix[row][cell].getSingleValue(), new Spot(row, cell)));
				}
			}
		}
		return result;
	}

	public void update(Single vpt) {
		int i = vpt.geti();
		int j = vpt.getj();
		matrix[i][j].clearAllPossibility();
		removePossibleValuesInRow(vpt.getValue(), i);
		removePossibleValuesInColumn(vpt.getValue(), j);
		removePossibleValuesInBlock(vpt.getValue(), i, j);
	}

	private void removePossibleValuesInBlock(int value, int row, int column) {
		int minRow = row - row%BLOCK_SIZE;
		int minColumn = column - column%BLOCK_SIZE;
		for(int i = minRow; i < minRow + BLOCK_SIZE; i++){
			for(int j = minColumn; j < minColumn + BLOCK_SIZE; j++){
				matrix[i][j].remove(value);
			}
		}
		
	}

	private void removePossibleValuesInColumn(int value, int column) {
		for(int i = 0; i < 9; i ++){
			matrix[i][column].remove(value);
		}
	}

	private void removePossibleValuesInRow(int value, int row) {
		for(int i = 0; i < 9; i++){
			matrix[row][i].remove(value);
		}
	}
	
	public ArrayList<Pair> findNewNakedPair() {
		ArrayList<Pair> result = new ArrayList<Pair>();
		for(int row = 0; row < 9; row++) findTwinsInRow(result, row);
		for(int column = 0; column < 9; column ++) findTwinsInColumn(result, column);
		for(int br = 0; br < 3; br ++){
			for(int bc = 0; bc < 3; bc ++){
				findTwinsInBlock(result, br, bc);
			}
		}
		
		return result;
	}

	private void findTwinsInBlock(ArrayList<Pair> result, int br, int bc) {
		int minI = br * 3;
		int minJ = bc * 3;
		for(int ci = minI; ci < minI + 3; ci ++){
			for(int cj = minJ; cj < minJ + 3; cj ++){
				for(int cci = minI; cci < minI + 3; cci++){
					for(int ccj = minJ; ccj  < minJ + 3; ccj ++){
						if(cci <= ci && ccj <= cj) continue;
						PossibleValues cellPV = matrix[ci][cj];
						PossibleValues comparedCellPV = matrix[cci][ccj];
						if(cellPV.isTwinOf(comparedCellPV))
							addToResult(result, new Pair(cellPV, new Spot(ci, cj), new Spot(cci, ccj)));
					}
				}
			}
		}
	}

	private void addToResult(ArrayList<Pair> result, Pair foundPair) {
		if(!this.pairList.contains(foundPair)){
			result.add(foundPair);
			this.pairList.add(foundPair);
		}
	}

	private void findTwinsInColumn(ArrayList<Pair> result, int column) {
		for(int cell = 0; cell <  8; cell ++){
			for(int comparedCell = cell + 1; comparedCell < 9; comparedCell ++){
				PossibleValues cellPV = matrix[cell][column];
				PossibleValues comparedCellPV = matrix[comparedCell][column];
				if(cellPV.isTwinOf(comparedCellPV))
					addToResult(result, new Pair(cellPV, new Spot(cell, column), new Spot(comparedCell, column)));
			}
		}
	}

	private void findTwinsInRow(ArrayList<Pair> result, int row) {
		for(int cell = 0; cell < 8; cell ++){
			for(int comparedCell = cell + 1; comparedCell < 9; comparedCell ++ ){
				PossibleValues cellPV = matrix[row][cell];
				PossibleValues compareCellPV = matrix[row][comparedCell];
				if(cellPV.isTwinOf(compareCellPV))
					addToResult(result, new Pair(cellPV, new Spot(row, cell), new Spot(row, comparedCell)));
			}
		}
	}

	public void update(Pair pair) {
		if(pair.inARow()) removePairInRow(pair);
		if(pair.inAColumn()) removePairInColumn(pair);
		if(pair.inABlock())	removePairInBlock(pair);
	}

	private void removePairInBlock(Pair pair) {
		int br = pair.block().geti();
		int bc = pair.block().getj();
		for(int i = br * 3; i < br * 3 + 3; i ++){
			for(int j = bc * 3; j < bc * 3 + 3; j ++){
				if(pair.has(new Spot(i, j))) continue;
				matrix[i][j].remove(pair.possibilities());
			}
		}
	}

	private void removePairInColumn(Pair pair) {
		for(int i = 0; i < 9; i ++){
			int column = pair.column();
			if(pair.has(new Spot(i, column))) continue;
			matrix[i][column].remove(pair.possibilities());
		}
	}

	private void removePairInRow(Pair pair) {
		int row = pair.row();
		for(int j = 0; j < 9; j ++){
			if(pair.has(new Spot(row, j))) continue;
			matrix[row][j].remove(pair.possibilities());
		}
	}

	public ArrayList<Locked> findNewLocked(ArrayList<Locked> lockedlist) {
		ArrayList<Locked> result = new ArrayList<Locked>();
		return result;
	}

	public void update(Locked locked) {
		if(locked.inABlock()){
			int br = locked.br();
			int bc = locked.bc();
			int num = locked.num();
			for(int i = br * 3; i < br * 3 + 3; i ++){
				for(int j = bc * 3; j < bc * 3 + 3; j ++){
					if(locked.contains(new Spot(i, j))) continue;
					matrix[i][j].remove(num);
				}
			}
		}
		
	}

	public ArrayList<Tripple> findNewTripple(ArrayList<Tripple> tripplelist) {
		ArrayList<Tripple> result = new ArrayList<Tripple>();
		return result;
	}

	public void update(Tripple tripple) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<XWing> findNewXWing(ArrayList<XWing> xwinglist) {
		ArrayList<XWing> result = new ArrayList<XWing>();
		return result;
	}

	public void update(XWing xwing) {
		// TODO Auto-generated method stub
		
	}


}
