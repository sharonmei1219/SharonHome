package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

import com.sharonhome.sudoku.generator.Spot;

public class Puzzle {

	private int[][] puzzle;
	public Puzzle(int[][] puzzle) {
		this.puzzle = puzzle;
	}
	
	public Puzzle(){
		
	}
	
	public void update(Single vpt) {
		puzzle[vpt.geti()][vpt.getj()] = vpt.getValue();
		
	}

	public boolean solved() {
		if(!finished()) return false;
		return true;
	}

	private boolean finished() {
		for(int i = 0; i < 9; i ++)
			for(int j = 0; j < 9; j++)
				if(empty(i, j)) return false;
		return true;
	}

	public Puzzle deepCopy() {
		Puzzle result = new Puzzle();
		result.puzzle = new int[9][9];
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				result.puzzle[i][j] = this.puzzle[i][j];
		return result;
	}
	public ArrayList<Single> getDeterminedValues() {
		ArrayList<Single> result = new ArrayList<Single>();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(!empty(i, j)) result.add(new Single(puzzle[i][j], new Spot(i, j)));
			}
		}
		return result;
	}
	
	private boolean empty(int i, int j) {
		return puzzle[i][j] == -1;
	}
	
	private boolean validate(){
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				int value = puzzle[i][j];
				if(!onlyOneInRow(value, i)) return false;
				if(!onlyOneInColumn(value, j)) return false;
				if(!onlyOneInBlock(value,i, j)) return false;
			}
		}
		return true;
	}

	private boolean onlyOneInBlock(int value, int row, int column) {
		int count = 0;
		int minI = row - row%3;
		int minJ = column - column%3;
		for(int i = minI; i < minI + 3; i++){
			for(int j = minJ; j < minJ + 3; j++){
				if(puzzle[i][j] == value) count ++;
			}
		}
		return count == 1;
	}

	private boolean onlyOneInColumn(int value, int column) {
		int count = 9;
		for(int i = 0; i < 9; i++)
			if(puzzle[i][column] == value) count ++;
		return count == 1;
	}

	private boolean onlyOneInRow(int value, int row) {
		int count = 0;
		for(int j = 0; j < 9; j++)
			if(puzzle[row][j] == value) count ++;
		return count == 1;
	}


}
