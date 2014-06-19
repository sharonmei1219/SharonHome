package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class SolverEliminateRowColumnBlock {

	private int[] candidates;
	private int blockSize;
	
	public SolverEliminateRowColumnBlock(){
		candidates = new int []{0, 1, 2, 3, 4, 5, 6, 7, 8};
		blockSize = 3;
	}
	
	public void setBlockSize(int bs){
		this.blockSize = bs;
	}

	public int[][] solve(int[][] puzzle) {
		int [][] result = deepCopy(puzzle);

		for(int candidate : candidates){
			ArrayList<Spot> numberPos = candidatePos(puzzle, candidate);
			PossiblePositions pp = new PossiblePositions(puzzle);
			for(Spot pos : numberPos){
				pp.removeRow(pos.geti());
				pp.removeColumn(pos.getj());
				pp.removeBlock(pos.geti(), pos.getj(), blockSize);
			}
			ArrayList<Spot> remainingPos = pp.remaining();
			for(Spot pos : remainingPos){
				if(pp.onlyOneInARow(pos)) {
					result[pos.geti()][pos.getj()] = candidate;
					return solve(result);
				}
				if(pp.onlyOneInABlock(pos, blockSize)){
					result[pos.geti()][pos.getj()] = candidate;
					return solve(result);
				}
				
				if(pp.onlyOneInAColumn(pos)){
					result[pos.geti()][pos.getj()] = candidate;
					return solve(result);
				}
			}
		}
		return result;
	}

	private ArrayList<Spot> candidatePos(int[][] puzzle, int candidate) {
		ArrayList<Spot> result = new ArrayList<Spot>();
		for(int i = 0; i < puzzle.length; i++){
			for(int j = 0; j < puzzle[0].length; j++){
				if(puzzle[i][j] == candidate)
				result.add(new Spot(i, j));
			}
		}
		return result;
	}

	private int[][] deepCopy(int[][] puzzle) {
		int [][] result = new int [puzzle.length][puzzle[0].length];
		for(int i = 0; i< puzzle.length; i++){
			for(int j = 0; j< puzzle[0].length; j++){
				result[i][j] = puzzle[i][j];
			}
		}
		return result;
	}

	public void setSolutionCandidate(int[] candidates) {
		this.candidates = candidates;
	}
}
