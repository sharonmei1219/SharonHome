package com.sharonhome.sudoku.table;

import com.sharonhome.sudoku.generator.Permutations;
import com.sharonhome.sudoku.generator.RandomNumberGen;
import com.sharonhome.sudoku.generator.RandomNumberGenbyRandom;

public class RandomTableGenerator {
	private Permutations pers = new Permutations(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8});
	private RandomNumberGen rand = new RandomNumberGenbyRandom();
	private TableValidator v = new SudokuValidator();
	private PbSolver solver = new PbSolver();
	
	public int[][] genTable(){
		RandomCandidates candidates = new RandomCandidatesInDifferentSequence(rand, pers);
		CanTable ct = new RandomTable(candidates, v);
		RandomTableCollector collector = new RandomTableCollector();
		solver.solve(ct, collector);
		return collector.result();
	}
	
	public void printTable(int [][] table){
		for(int i = 0; i < 9; i++){
			System.out.println();
			for(int j = 0; j < 9; j++){
				System.out.print("" + table[i][j] + ", ");
			}
		}
	}
	

}
