package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class PossiblePositionVector {
	
	PossiblePositions [] vector = new PossiblePositions[9];

	public PossiblePositionVector(Puzzle puzzle) {
		for(int i = 0; i < 9; i ++)
			vector[i] = new PossiblePositions();
	}

	public void update(Single vpt) {
		for(int i = 0; i < 9; i++){
			vector[i].notPossibleIn(vpt.geti(), vpt.getj());
		}
		valueNotPossibleInRow(vpt.getValue(), vpt.geti());
		valueNotPossibleInColumn(vpt.getValue(), vpt.getj());
		valueNotPossibleInBlock(vpt.getValue(), vpt.geti(), vpt.getj());
		
	}


	private void valueNotPossibleInBlock(int value, int row, int column) {
		int minI = row - row%3;
		int minJ = column - column%3;
		for(int i = minI; i < minI + 3; i++)
			for(int j = minJ; j < minJ + 3; j++)
				vector[value].notPossibleIn(i, j);
		
	}

	private void valueNotPossibleInColumn(int value, int column) {
		for(int i = 0; i < 9; i++){
			vector[value].notPossibleIn(i, column);
		}
		
	}

	private void valueNotPossibleInRow(int value, int row) {
		for(int j = 0; j < 9; j++){
			vector[value].notPossibleIn(row, j);
		}
		
	}

	public void update(Pair pair) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Single> findNewSingle(ArrayList<Single> singlelist) {
		ArrayList<Single> result = new ArrayList<Single> ();
		for(int i = 0; i < 9; i++){
			ArrayList<Spot> determinedPos = vector[i].onlyPossiblePosInARegion();
			for(Spot pos : determinedPos)
				result.add(new Single(i, pos));
		}
			
		return result;
	}

	public ArrayList<Pair> findNewHiddenPair(ArrayList<Pair> pairlist) {
		ArrayList<Pair> result = new ArrayList<Pair>();
		return result;
	}

	public void update(Locked locked) {
		// TODO Auto-generated method stub
		
	}

	public ArrayList<Locked> findNewLocked(ArrayList<Locked> lockedlist) {
		ArrayList<Locked> result = new ArrayList<Locked>();
		return result;
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
		ArrayList<XWing> result = new ArrayList<XWing> ();
		return result;
	}

}
