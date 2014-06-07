package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class SudokuSolver {
	private int blockSize;
	private int[] candidatePairs;

	public SudokuSolver(int blockSize) {
		this.blockSize = blockSize;
	}

	public Spot findFirstUnsolvedSpot(int[][] puzzle) {
		for (int i = 0; i < puzzle.length; i++)
			for(int j = 0; j < puzzle[i].length; j++)
				if (puzzle[i][j] == -1)
					return new Spot(i, j);
		return new Spot(-1, -1);
	}

	public boolean isPairValidInSpot(int[][] puzzle, int inputPair, int i, int j) {
		for(int k = 0; k < puzzle[i].length; k++)
			if(puzzle[i][k] != -1 && inputPair == puzzle[i][k]) return false;
		
		for(int k = 0; k < puzzle.length; k++)
			if(puzzle[k][j] != -1 && inputPair == puzzle[k][j]) return false;
		
		int minX = i - i%blockSize;
		int minY = j - j%blockSize;
		
		for(int kx = minX; kx < minX + blockSize; kx++)
			for(int ky = minY; ky < minY + blockSize; ky ++)
				if(puzzle[kx][ky] != -1 && inputPair == puzzle[kx][ky]) return false;
		
		return true;
	}
	
	private int[][] deepCopy(int[][] puzzle){
		if(puzzle == null) return null;
		
		int [][] result = new int[puzzle.length][];
		for(int i = 0; i < puzzle.length; i ++)
			result[i] = puzzle[i].clone();
		return result;
		
	}
	
	public void setCandidatePairs(int [] candidates){
		this.candidatePairs = candidates;
	}
	
	private void setSpot(int[][] puzzle, int candidate, Spot p){
		puzzle[p.geti()][p.getj()] = candidate;
	}
	
	private void clearSpot(int[][] puzzle, Spot p){
		setSpot(puzzle, -1, p);
	}
	
	public void solve(int[][] puzzle, ArrayList<int[][]> solutions) {
		Spot emptySpot = findFirstUnsolvedSpot(puzzle);
		if(emptySpot.equals(new Spot(-1, -1))){
			solutions.add(deepCopy(puzzle));
			return;
		}
		for(int p : candidatePairs){
			if (isPairValidInSpot(puzzle, p, emptySpot.geti(), emptySpot.getj())){
				setSpot(puzzle, p, emptySpot);
				solve(puzzle, solutions);
				clearSpot(puzzle, emptySpot);
			}
		}
	}
}
