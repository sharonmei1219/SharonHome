package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class PossiblePositions {
	boolean [][] posiblePos = new boolean[9][9];
	
	public PossiblePositions(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				posiblePos[i][j] = true;
			}
		}
	}

	public void notPossibleIn(int i, int j) {
		posiblePos[i][j] = false;
	}

	public ArrayList<Spot> onlyPossiblePosInARegion() {
		ArrayList<Spot> result = new ArrayList<Spot>();
		
		for(int row = 0; row < 9; row ++){
			if(hasOnlyOnePossiblePosInRow(row))
				result.add(getOnlyPosInRow(row));
		}
		
		for(int column = 0; column < 9; column ++){
			if(hasOnlyOnePossiblePosInColumn(column)){
				Spot s = getOnlyPosInColumn(column);
				if(notInList(s, result))
					result.add(s);
			}
		}
		
		for(int bRow = 0; bRow < 3; bRow ++){
			for(int bColumn = 0; bColumn < 3; bColumn ++){
				if(hasOnlyOnePossiblePosInBlock(bRow, bColumn)){
					Spot s = getOnlyPossiblePosInBlock(bRow, bColumn);
					if(notInList(s, result))
						result.add(s);
				}
			}
		}
			
		return result;
	}

	private boolean notInList(Spot target, ArrayList<Spot> spotList) {
		for(Spot spot : spotList)
			if(spot.equals(target)) return false;
		return true;
	}

	private Spot getOnlyPossiblePosInBlock(int bRow, int bColumn) {
		int minI = bRow * 3;
		int minJ = bColumn * 3;
		for(int i = minI; i < minI + 3; i++)
			for(int j = minJ; j < minJ + 3; j++) 
				if(posiblePos[i][j]) return new Spot(i, j);
		
		return null;
	}

	private boolean hasOnlyOnePossiblePosInBlock(int bRow, int bColumn) {
		int count = 0;
		int minI = bRow * 3;
		int minJ = bColumn * 3;
		for(int i = minI; i < minI + 3; i++)
			for(int j = minJ; j < minJ + 3; j++)
				if(posiblePos[i][j]) count ++;
		
		return count == 1;
	}

	private Spot getOnlyPosInColumn(int column) {
		for(int i = 0; i < 9; i++)
			if(posiblePos[i][column]) return new Spot(i, column);
		
		return null;
	}

	private boolean hasOnlyOnePossiblePosInColumn(int column) {
		int count = 0;
		for(int i = 0; i < 9; i++){
			if(posiblePos[i][column]) count++;
		}
		return count == 1;
	}

	private Spot getOnlyPosInRow(int row) {
		for(int j = 0; j < 9; j++){
			if(posiblePos[row][j]) return new Spot(row, j);
		}
		return null;
	}

	private boolean hasOnlyOnePossiblePosInRow(int row) {
		int cnt = 0;
		for(int j = 0; j < 9; j++)
			if(posiblePos[row][j]) cnt ++; 
		return cnt == 1;
	}

}
