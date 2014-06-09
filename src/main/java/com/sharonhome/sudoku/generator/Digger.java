package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class Digger {
	public Spot[] dig(int[][] puzzle, SudokuSolver solver, RandomSpotSeq randomSpotSeq, int requiredHoles) {
		int [][] localPuzzle = deepcopy(puzzle);
		int nrOfHoles = 0;
		Spot[] result = new Spot[requiredHoles];
		while(nrOfHoles < requiredHoles && randomSpotSeq.nrOfCandidates() != 0){
			Spot next = randomSpotSeq.next();
			int x = next.geti();
			int y = next.getj();
			int temp = localPuzzle[x][y];
			localPuzzle[x][y] = -1;
			ArrayList<int[][]> solutions = new ArrayList<int[][]>();
			solver.solve(localPuzzle, solutions);
			if(solutions.size() == 1){
				result[nrOfHoles++] = next;
			}else{
				localPuzzle[x][y]= temp;
			}
		}
		if (nrOfHoles == requiredHoles)
			return result;
		return null;
	}
	
	public int [][] eraseHoles(int [][]table, Spot[] holes){
		int [][] result = deepcopy(table);
		for(Spot s : holes)
			result[s.geti()][s.getj()] = -1;
		return result;
	};
	
	private int[][] deepcopy(int[][] puzzle) {
		int result[][] = new int [puzzle.length][puzzle[0].length];
		for(int i = 0; i < puzzle.length; i ++){
			for(int j = 0; j < puzzle[i].length; j++){
				result[i][j] = puzzle[i][j];
			}
		}
		return result;
	}
}
