package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class PossiblePositionVector {

	PossiblePositions[] vector = new PossiblePositions[9];
	private ArrayList<Pair> pairList = new ArrayList<Pair>();
	private ArrayList<Locked> lockedList = new ArrayList<Locked>();

	public PossiblePositionVector(Puzzle puzzle) {
		for (int i = 0; i < 9; i++)
			vector[i] = new PossiblePositions();

		ArrayList<Single> vpts = puzzle.getDeterminedValues();
		for (Single vpt : vpts)
			update(vpt);
	}

	public void update(Single vpt) {
		for (int i = 0; i < 9; i++) {
			vector[i].notPossibleIn(vpt.geti(), vpt.getj());
		}
		valueNotPossibleInRow(vpt.getValue(), vpt.geti());
		valueNotPossibleInColumn(vpt.getValue(), vpt.getj());
		valueNotPossibleInBlock(vpt.getValue(), vpt.geti(), vpt.getj());

	}

	private void valueNotPossibleInBlock(int value, int row, int column) {
		int minI = row - row % 3;
		int minJ = column - column % 3;
		for (int i = minI; i < minI + 3; i++)
			for (int j = minJ; j < minJ + 3; j++)
				vector[value].notPossibleIn(i, j);

	}

	private void valueNotPossibleInColumn(int value, int column) {
		for (int i = 0; i < 9; i++) {
			vector[value].notPossibleIn(i, column);
		}

	}

	private void valueNotPossibleInRow(int value, int row) {
		for (int j = 0; j < 9; j++) {
			vector[value].notPossibleIn(row, j);
		}
	}

	public void update(Pair pair) {
		if (pair.inARow())
			updatePairInRow(pair);
		if (pair.inAColumn())
			updatePairInColumn(pair);
		if (pair.inABlock())
			updatePairInBlock(pair);
	}

	private void updatePairInBlock(Pair pair) {
		int[] pv = pair.possibilities().possibleValue();
		int br = pair.block().geti();
		int bc = pair.block().getj();
		for (int i = br * 3; i < br * 3 + 3; i++) {
			for (int j = bc * 3; j < bc * 3 + 3; j++) {
				if (pair.has(new Spot(i, j)))
					continue;
				vector[pv[0]].notPossibleIn(i, j);
				vector[pv[1]].notPossibleIn(i, j);
			}
		}

	}

	private void updatePairInColumn(Pair pair) {
		int[] pv = pair.possibilities().possibleValue();
		int column = pair.column();
		for (int i = 0; i < 9; i++) {
			if (pair.has(new Spot(i, column)))
				continue;
			vector[pv[0]].notPossibleIn(i, column);
			vector[pv[1]].notPossibleIn(i, column);
		}
	}

	private void updatePairInRow(Pair pair) {
		int[] pv = pair.possibilities().possibleValue();
		int row = pair.row();
		for (int j = 0; j < 9; j++) {
			if (pair.has(new Spot(row, j)))
				continue;
			vector[pv[0]].notPossibleIn(row, j);
			vector[pv[1]].notPossibleIn(row, j);
		}
	}

	public ArrayList<Single> findNewSingle(ArrayList<Single> singlelist) {
		ArrayList<Single> result = new ArrayList<Single>();
		for (int i = 0; i < 9; i++) {
			ArrayList<Spot> determinedPos = vector[i]
					.onlyPossiblePosInARegion();
			for (Spot pos : determinedPos)
				result.add(new Single(i, pos));
		}

		return result;
	}

	public ArrayList<Pair> findNewHiddenPair(ArrayList<Pair> pairlist) {
		ArrayList<Pair> result = new ArrayList<Pair>();
		for (int row = 0; row < 9; row++)
			findPairsInRow(result, row);
		for (int column = 0; column < 9; column++)
			findPairsInColumn(result, column);
		for (int br = 0; br < 3; br++) {
			for (int bc = 0; bc < 3; bc++) {
				findPairsInBlock(result, br, bc);
			}
		}

		return result;
	}

	private void findPairsInBlock(ArrayList<Pair> result, int br, int bc) {
		for (int n = 0; n < 8; n++) {
			if (2 != vector[n].getNumberOfPossiblePositionsInBlock(br, bc))
				continue;
			for (int cn = n + 1; cn < 9; cn++) {
				PossiblePositions ppNumber = vector[n];
				PossiblePositions ppCN = vector[cn];
				Spot[] spots = ppNumber.possiblePosInBlock(br, bc);
				if (ppNumber.matchesInBlock(ppCN, br, bc)) {
					Pair found = new Pair(new PossibleValues(new int[] { n, cn }), 
							              spots[0],
							              spots[1]);
					addToResult(result, found);
				}
			}
		}
	}

	private void findPairsInColumn(ArrayList<Pair> result, int column) {
		for (int number = 0; number < 8; number++) {
			if (2 != vector[number]
					.getNumberOfPossiblePositionsInColumn(column))
				continue;
			for (int cn = number + 1; cn < 9; cn++) {
				PossiblePositions ppNumber = vector[number];
				PossiblePositions ppCN = vector[cn];
				if (ppNumber.matchesInColumn(ppCN, column)) {
					int[] ros = ppNumber.possiblePosInColumn(column);
					Pair found = new Pair(new PossibleValues(new int[] { number, cn }), 
							              new Spot(ros[0], column), 
							              new Spot(ros[1],column));
					addToResult(result, found);
				}
			}
		}
	}

	private void findPairsInRow(ArrayList<Pair> result, int row) {
		for (int number = 0; number < 8; number++) {
			if (2 != vector[number].getNumberOfPossiblePositionsInRow(row))
				continue;
			for (int cnumber = number + 1; cnumber < 9; cnumber++) {
				PossiblePositions ppNumber = vector[number];
				PossiblePositions ppCNumber = vector[cnumber];
				if (ppNumber.matchesInRow(ppCNumber, row)) {
					int[] cos = ppNumber.possiblePosInRow(row);
					Pair found = new Pair(new PossibleValues(new int[] {number, cnumber }), 
							              new Spot(row, cos[0]),
							              new Spot(row, cos[1]));
					addToResult(result, found);
				}
			}
		}
	}

	private void addToResult(ArrayList<Pair> result, Pair found) {
		if (!pairList.contains(found)) {
			result.add(found);
			pairList.add(found);
		}
	}

	public void update(Locked locked) {
		if(locked.inARow())	updateLockedInRow(locked);
		if(locked.inAColumn()) updateLockedInColumn(locked);

	}

	private void updateLockedInColumn(Locked locked) {
		int column = locked.column();
		int num = locked.num();
		for(int i = 0; i < 9; i++){
			if(!locked.contains(new Spot(i, column)))
				vector[num].notPossibleIn(i, column);
		}
		
	}

	private void updateLockedInRow(Locked locked) {
		int row = locked.row();
		int num = locked.num();
		for(int j = 0; j < 9; j++){
			if(!locked.contains(new Spot(row, j)))
				vector[num].notPossibleIn(row, j);
		}
	}

	public ArrayList<Locked> findNewLocked() {
		ArrayList<Locked> result = new ArrayList<Locked>();
		for(int num = 0; num < 9; num ++){
			PossiblePositions possiblePositions = vector[num];
			searchLockedInBlocks(result, num, possiblePositions);
			searchLockedInRows(result, num, possiblePositions);
			for(int column = 0; column < 9; column ++){
				Spot[] ppInAColumn = possiblePositions.inColumn(column);
				if(ppInAColumn.length > 3 || ppInAColumn.length == 0) continue;
				if(! inABlock(ppInAColumn)) continue;
				Locked found = new Locked(num, ppInAColumn);
				addToResult(result, found);
			}
		}
		return result;
	}

	private void searchLockedInRows(ArrayList<Locked> result, int num,
			PossiblePositions possiblePositions) {
		for(int row = 0; row < 9; row ++){
			Spot[] ppInArow = possiblePositions.inRow(row);
			if(ppInArow.length > 3 || ppInArow.length == 0) continue;
			if(! inABlock(ppInArow)) continue;
			Locked found = new Locked(num, ppInArow);
			addToResult(result, found);
		}
	}

	private boolean inABlock(Spot[] ppInArow) {
		int bc = ppInArow[0].getj()/3;
		for(Spot p : ppInArow)
			if(p.getj()/3 != bc) return false;
		return true;
	}

	private void searchLockedInBlocks(ArrayList<Locked> result, int num,
			PossiblePositions possiblePositions) {
		for(int br = 0; br < 3; br ++){
			for(int bc = 0; bc < 3; bc ++){
				Spot[] ppInBlock = possiblePositions.inBlock(br, bc);
				if(ppInBlock.length > 3 || ppInBlock.length == 0) continue;
				if(inARow(ppInBlock) || inAColumn(ppInBlock)) {
					Locked found = new Locked(num, ppInBlock);
					addToResult(result, found);
				}
			}
		}
	}

	private boolean inAColumn(Spot[] ppInBlock) {
		int column = ppInBlock[0].getj();
		for(Spot eachP : ppInBlock)
			if(eachP.getj() != column) return false;
		return true;
	}

	private void addToResult(ArrayList<Locked> result, Locked found) {
		if(!lockedList.contains(found)){
			result.add(found);
			lockedList.add(found);
		}
	}	

	private boolean inARow(Spot[] ppInBlock) {
		int row = ppInBlock[0].geti();
		for(Spot eachSpot : ppInBlock)
			if(eachSpot.geti() != row) return false;
		return true;
	}

	public void update(Tripple tripple) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Tripple> findNewTripple(ArrayList<Tripple> tripplelist) {
		ArrayList<Tripple> result = new ArrayList<Tripple>();
		return result;
	}

	public void update(XWing xwing) {
		// TODO Auto-generated method stub

	}

	public ArrayList<XWing> findNewXWing(ArrayList<XWing> xwinglist) {
		ArrayList<XWing> result = new ArrayList<XWing>();
		return result;
	}

}
