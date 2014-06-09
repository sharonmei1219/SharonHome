package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class TestSudokuSolutionTableGeneration {

	@Test
	public void test() {
		SetSet ss = new SetSet(new int [][]{{0, 3, 6},{1, 4, 7},{2, 5, 8}});
		CSLCRNumberingSystem cslcrNs = new CSLCRNumberingSystem();
		CSLCRGenerator gen = new CSLCRGenerator(ss, cslcrNs);
		ArrayList<int []> cslcr = gen.getAllSelection(0);
		PermutateCSLCR per = new PermutateCSLCR(cslcr);
		int [] column = per.getNthPermutation(0);
		SudokuTableTemplateGenerator tableGen = new SudokuTableTemplateGenerator();
		int [][] sudokuSolutionTable = tableGen.genTable(column);
		Permutations perOf9 = new Permutations(new int[] {9,8, 7, 6, 5, 4, 3, 2, 1});
		int [] per9 = perOf9.get(0);
		sudokuSolutionTable = perSudokuTable(sudokuSolutionTable, per9);
		
		Assert.assertArrayEquals(new int [] {9,8,7,6,5,4,3,2,1}, sudokuSolutionTable[0]); 
		Assert.assertArrayEquals(new int [] {6,5,4,3,2,1,9,8,7}, sudokuSolutionTable[1]); 
		Assert.assertArrayEquals(new int [] {3,2,1,9,8,7,6,5,4}, sudokuSolutionTable[2]); 
		Assert.assertArrayEquals(new int [] {8,7,6,5,4,3,2,1,9}, sudokuSolutionTable[3]); 
		Assert.assertArrayEquals(new int [] {5,4,3,2,1,9,8,7,6}, sudokuSolutionTable[4]); 
		Assert.assertArrayEquals(new int [] {2,1,9,8,7,6,5,4,3}, sudokuSolutionTable[5]); 
		Assert.assertArrayEquals(new int [] {7,6,5,4,3,2,1,9,8}, sudokuSolutionTable[6]); 
		Assert.assertArrayEquals(new int [] {4,3,2,1,9,8,7,6,5}, sudokuSolutionTable[7]); 
		Assert.assertArrayEquals(new int [] {1,9,8,7,6,5,4,3,2}, sudokuSolutionTable[8]); 
	}

	private int[][] perSudokuTable(int[][] originTable,
			int [] permutation) {
		int [][] result = new int[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				int originNumber = originTable[i][j];
				result[i][j] = permutation[originNumber];
			}
		}
		return result;
	}
}
