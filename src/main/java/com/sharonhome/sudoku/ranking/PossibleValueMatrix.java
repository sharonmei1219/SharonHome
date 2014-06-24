package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class PossibleValueMatrix {
	PossibleValues [][] matrix = new PossibleValues[9][9];
	static final int BLOCK_SIZE = 3;
	private ArrayList<Pair> pairList;
	private ArrayList<Triple> tripleList;

	public PossibleValueMatrix(Puzzle puzzle) {
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				matrix[i][j] = new PossibleValues();
		
		ArrayList<Single> determinedValues = puzzle.getDeterminedValues();
		for(Single dv : determinedValues)
			update(dv);
		
		this.pairList = new ArrayList<Pair>();
		this.tripleList = new ArrayList<Triple>();
		
	}

	public ArrayList<Single> findNewSingle() {
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
				if(pair.has(new Spot(i, j))){
					matrix[i][j] = pair.possibilities().deepCopy();
				}else{
					matrix[i][j].remove(pair.possibilities());
				}
			}
		}
	}

	private void removePairInColumn(Pair pair) {
		for(int i = 0; i < 9; i ++){
			int column = pair.column();
			if(pair.has(new Spot(i, column))){
				matrix[i][column] = pair.possibilities().deepCopy();
			}else{
				matrix[i][column].remove(pair.possibilities());
			}
		}
	}

	private void removePairInRow(Pair pair) {
		int row = pair.row();
		for(int j = 0; j < 9; j ++){
			if(pair.has(new Spot(row, j))){
				matrix[row][j] = pair.possibilities().deepCopy();
			}else{
				matrix[row][j].remove(pair.possibilities());
			}
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
		if(locked.inARow()){
			int row = locked.row();
			int num = locked.num();
			for(int j = 0; j < 9; j++){
				if(locked.contains(new Spot(row, j))) continue;
				matrix[row][j].remove(num);
			}
		}
		
		if(locked.inAColumn()){
			int column = locked.column();
			int num = locked.num();
			for(int i = 0; i < 9; i ++){
				if(locked.contains(new Spot(i, column))) continue;
				matrix[i][column].remove(num);
			}
		}
		
	}

	public ArrayList<Triple> findNewNakedTriple() {
		ArrayList<Triple> result = new ArrayList<Triple>();
		searchTrippleInRow(result);
		searchTrippleInColumn(result);
		searchTrippleInBlock(result);
		return result;
	}

	private void searchTrippleInBlock(ArrayList<Triple> result) {
		for(int br = 0; br < 3; br ++){
			for(int bc = 0; bc < 3; bc ++){
				int minI = br * 3;
				int minJ = bc * 3;
				for(int i1 = minI ; i1 < minI + 3; i1++){
					for(int j1 = minJ; j1 < minJ + 3; j1++){
						if(matrix[i1][j1].size() == 0) continue;
						for(int i2 = minI; i2 < minI + 3; i2++){
							for(int j2 = minJ; j2 < minJ + 3; j2 ++){
								if(i2 <= i1 && j2 <= j1) continue;
								if(matrix[i2][j2].size() == 0) continue;
								PossibleValues union = matrix[i1][j1].union(matrix[i2][j2]);
								if(union.size() != 3) continue;
								for(int i3 = minI; i3 < minI + 3; i3++){
									for(int j3 = minJ; j3 < minJ + 3; j3++){
										if(j3 <= j2 && i3 <= i2) continue;
										if(j3 <= j1 && i3 <= i1) continue;
										if(matrix[i3][j3].size() == 0) continue;
										if(union.union(matrix[i3][j3]).size() != 3) continue;
										Triple found = new Triple(union, new Spot[]{
										           new Spot(i1, j1),
										           new Spot(i2, j2),
										           new Spot(i3, j3)
												});
										addToResult(result, found);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	private void searchTrippleInColumn(ArrayList<Triple> result) {
		for(int column = 0; column < 9; column ++){
			
			for(int i1 = 0; i1 < 7; i1++){
				if(matrix[i1][column].size() == 0) continue;
				for(int i2 = i1 + 1; i2 < 8; i2++){
					if(matrix[i2][column].size() == 0) continue;
					PossibleValues union = matrix[i1][column].union(matrix[i2][column]);
					if(union.size() != 3) continue;
					for(int i3 = i2 + 1; i3 < 9; i3 ++){
						if(matrix[i3][column].size() == 0) continue;
						if(union.union(matrix[i3][column]).size() != 3) continue;
						Triple found = new Triple(union, new Spot[]{
								           new Spot(i1, column),
								           new Spot(i2, column),
								           new Spot(i3, column)
						});
						addToResult(result, found);
					}
				}
			}
		}
	}

	private void searchTrippleInRow(ArrayList<Triple> result) {
		for(int row = 0; row < 9; row ++){
			
			for(int j1 = 0; j1 < 7; j1 ++){
				if(matrix[row][j1].size() == 0) continue;
				for(int j2 = j1+1; j2 < 8; j2++){
					if(matrix[row][j2].size() == 0) continue;
					PossibleValues union = matrix[row][j1].union(matrix[row][j2]);
					if((union.size() != 3)) continue;
					for(int j3 = j2+1; j3 < 9; j3++){
						if(matrix[row][j3].size() == 0) continue;
						if(union.union(matrix[row][j3]).size() != 3) continue;
						Triple found = new Triple(union, new Spot[]{
								new Spot(row, j1),
								new Spot(row, j2),
								new Spot(row, j3)
						});
						addToResult(result, found);
					}
				}
			}
		}
	}

	private void addToResult(ArrayList<Triple> result, Triple found) {
		if(tripleList.contains(found))
			return;
		result.add(found);
		tripleList.add(found);
	}

	public void update(Triple tripple) {
		if(tripple.inARow()){
			updateTripleInRow(tripple);
		}
		if(tripple.inAColumn()){
			updateTripleInColumn(tripple);
		}
		if(tripple.inABlock()){
			updateTrippleInBlock(tripple);
		}
		
	}

	private void updateTrippleInBlock(Triple tripple) {
		int br = tripple.br();
		int bc = tripple.bc();
		for (int i = br * 3; i < br*3 + 3; i ++){
			for(int j = bc * 3; j < bc *3 + 3; j++){
				if(tripple.contains(new Spot(i, j))){
					matrix[i][j] = tripple.possibleValues().deepCopy();
				}else{
					matrix[i][j].remove(tripple.possibleValues());
				}
			}
		}
	}

	private void updateTripleInColumn(Triple tripple) {
		int column = tripple.column();
		PossibleValues pv = tripple.possibleValues();
		for(int i = 0; i < 9; i++){
			if(tripple.contains(new Spot(i, column))){
				matrix[i][column] = pv.deepCopy();
			}else{
				matrix[i][column].remove(pv);
			}
		}
	}

	private void updateTripleInRow(Triple tripple) {
		int row = tripple.row();
		PossibleValues pv = tripple.possibleValues();
		for(int j = 0; j < 9; j++){
			if(tripple.contains(new Spot(row, j))){
				matrix[row][j] = pv.deepCopy();
			}else{
				matrix[row][j].remove(pv);
			}
		}
	}

	public ArrayList<XWing> findNewXWing(ArrayList<XWing> xwinglist) {
		ArrayList<XWing> result = new ArrayList<XWing>();
		return result;
	}

	public void update(XWing xwing) {
		if(xwing.columnXWing()){
			int [] rows = xwing.rows();
			int num = xwing.num();
			for(int j = 0; j < 9; j++){
				if(xwing.contains(new Spot(rows[0], j))) continue;
				matrix[rows[0]][j].remove(num);
				matrix[rows[1]][j].remove(num);
			}
		}
		if(xwing.rowXWing()){
			int [] columns = xwing.columns();
			int num = xwing.num();
			for(int i = 0; i < 9; i++){
				if(xwing.contains(new Spot(i, columns[0]))) continue;
				matrix[i][columns[0]].remove(num);
				matrix[i][columns[1]].remove(num);
			}
		}
		
	}

	public ArrayList<Locked> findNewLocked() {
		ArrayList<Locked> result = new ArrayList<Locked>();
		return result;
	}

	public ArrayList<XWing> findNewXWing() {
		ArrayList<XWing> result = new ArrayList<XWing>();
		return result;
	}


}
