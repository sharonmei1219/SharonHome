package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class SudokuSolver {
	private int blockSize;
	private Pair[] candidatePairs;

	public SudokuSolver(int blockSize) {
		this.blockSize = blockSize;
	}

	public Spot findFirstUnsolvedSpot(Pair[][] puzzle) {
		for (int i = 0; i < puzzle.length; i++)
			for(int j = 0; j < puzzle[i].length; j++)
				if (puzzle[i][j] == null)
					return new Spot(i, j);
		return new Spot(-1, -1);
	}

	public boolean isPairValidInSpot(Pair[][] puzzle, Pair inputPair, int i, int j) {
		for(int k = 0; k < puzzle[i].length; k++)
			if(puzzle[i][k] !=null && inputPair.equals(puzzle[i][k])) return false;
		
		for(int k = 0; k < puzzle.length; k++)
			if(puzzle[k][j] !=null && inputPair.equals(puzzle[k][j])) return false;
		
		int minX = i - i%blockSize;
		int minY = j - j%blockSize;
		
		for(int kx = minX; kx < minX + blockSize; kx++)
			for(int ky = minY; ky < minY + blockSize; ky ++)
				if(puzzle[kx][ky]!=null && inputPair.equals(puzzle[kx][ky])) return false;
		
		return true;
	}
	
	private Pair[][] deepCopy(Pair[][] puzzle){
		if(puzzle == null) return null;
		
		Pair [][] result = new Pair[puzzle.length][];
		for(int i = 0; i < puzzle.length; i ++)
			result[i] = puzzle[i].clone();
		return result;
		
	}
	
	public void setCandidatePairs(Pair [] candidates){
		this.candidatePairs = candidates;
	}
	
	private void setSpot(Pair[][] puzzle, Pair candidate, Spot p){
		puzzle[p.geti()][p.getj()] = candidate;
	}
	
	private void clearSpot(Pair[][] puzzle, Spot p){
		setSpot(puzzle, null, p);
	}
	
	public void solve(Pair[][] puzzle, ArrayList<Pair[][]> solutions) {
		Spot emptySpot = findFirstUnsolvedSpot(puzzle);
		if(emptySpot.equals(new Spot(-1, -1))){
			solutions.add(deepCopy(puzzle));
			return;
		}
		for(Pair p : candidatePairs){
			if (isPairValidInSpot(puzzle, p, emptySpot.geti(), emptySpot.getj())){
				setSpot(puzzle, p, emptySpot);
				solve(puzzle, solutions);
				clearSpot(puzzle, emptySpot);
			}
		}
	}
}
