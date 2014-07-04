package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class TestSudokuSolutionTableGeneration {

	@Test
	public void testGenerationWithOneCslcr() {
		SetSet ss = new SetSet(new int [][]{{0, 3, 6},{1, 4, 7},{2, 5, 8}});
		CSLCRSetNumberingSystem cslcrNs = new CSLCRSetNumberingSystem();
		CSLCRSetGenerator gen = new CSLCRSetGenerator(ss, cslcrNs);
		ArrayList<int []> cslcr = gen.getAllSelection(0);
		CSLCRPermutations per = new CSLCRPermutations(cslcr);
		int [] column = per.getNthPermutation(0);
		TableGenerator tableGen = new TableGenerator();
		int [][] sudokuSolutionTable = tableGen.genTable(column, column, column);
		Permutations perOf9 = new Permutations(new int[] {9,8, 7, 6, 5, 4, 3, 2, 1});
		int [] per9 = perOf9.get(0);
		sudokuSolutionTable = perSudokuTable(sudokuSolutionTable, per9);
		
		Assert.assertArrayEquals(new int [] {9,6,3,8,5,2,7,4,1}, sudokuSolutionTable[0]); 
		Assert.assertArrayEquals(new int [] {8,5,2,7,4,1,6,3,9}, sudokuSolutionTable[1]); 
		Assert.assertArrayEquals(new int [] {7,4,1,6,3,9,5,2,8}, sudokuSolutionTable[2]); 
		Assert.assertArrayEquals(new int [] {6,3,9,5,2,8,4,1,7}, sudokuSolutionTable[3]); 
		Assert.assertArrayEquals(new int [] {5,2,8,4,1,7,3,9,6}, sudokuSolutionTable[4]); 
		Assert.assertArrayEquals(new int [] {4,1,7,3,9,6,2,8,5}, sudokuSolutionTable[5]); 
		Assert.assertArrayEquals(new int [] {3,9,6,2,8,5,1,7,4}, sudokuSolutionTable[6]); 
		Assert.assertArrayEquals(new int [] {2,8,5,1,7,4,9,6,3}, sudokuSolutionTable[7]); 
		Assert.assertArrayEquals(new int [] {1,7,4,9,6,3,8,5,2}, sudokuSolutionTable[8]); 
	}
	
	@Test
	public void testGenerationWithDifferentCslcr() {
		int [] cslcr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		int [] cslcr2 = {3, 4, 2, 6, 7, 8, 0, 1, 5};
		int [] cslcr3 = {6, 4, 5, 3, 7, 2, 0, 1, 8};
		TableGenerator tableGen = new TableGenerator();
		int [][] sudokuSolutionTable = tableGen.genTable(cslcr1, cslcr2, cslcr3);
		
		Assert.assertArrayEquals(new int [] {0,3,6,4,7,1,8,2,5}, sudokuSolutionTable[0]); 
		Assert.assertArrayEquals(new int [] {1,4,7,5,8,2,6,0,3}, sudokuSolutionTable[1]); 
		Assert.assertArrayEquals(new int [] {2,5,8,3,6,0,7,1,4}, sudokuSolutionTable[2]); 
		Assert.assertArrayEquals(new int [] {3,6,0,7,1,4,5,8,2}, sudokuSolutionTable[3]); 
		Assert.assertArrayEquals(new int [] {4,7,1,8,2,5,0,3,6}, sudokuSolutionTable[4]); 
		Assert.assertArrayEquals(new int [] {5,8,2,0,3,6,4,7,1}, sudokuSolutionTable[5]); 
		Assert.assertArrayEquals(new int [] {6,0,3,1,4,7,2,5,8}, sudokuSolutionTable[6]); 
		Assert.assertArrayEquals(new int [] {7,1,4,2,5,8,3,6,0}, sudokuSolutionTable[7]); 
		Assert.assertArrayEquals(new int [] {8,2,5,6,0,3,1,4,7}, sudokuSolutionTable[8]); 
	}
	
	@Test
	public void breakColumnTriplet(){
		
		int [] cslcr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		int [] cslcr2 = {3, 4, 2, 6, 7, 8, 0, 1, 5};
		int [] cslcr3 = {6, 4, 5, 3, 7, 2, 0, 1, 8};
		TableGenerator tableGen = new TableGenerator();
		int [][] table = tableGen.genTable(cslcr1, cslcr2, cslcr3);
		
		Assert.assertArrayEquals(new int [] {0,3,6,4,7,1,8,2,5}, table[0]); 
		Assert.assertArrayEquals(new int [] {1,4,7,5,8,2,6,0,3}, table[1]); 
		Assert.assertArrayEquals(new int [] {2,5,8,3,6,0,7,1,4}, table[2]); 
		Assert.assertArrayEquals(new int [] {3,6,0,7,1,4,5,8,2}, table[3]); 
		Assert.assertArrayEquals(new int [] {4,7,1,8,2,5,0,3,6}, table[4]); 
		Assert.assertArrayEquals(new int [] {5,8,2,0,3,6,4,7,1}, table[5]); 
		Assert.assertArrayEquals(new int [] {6,0,3,1,4,7,2,5,8}, table[6]); 
		Assert.assertArrayEquals(new int [] {7,1,4,2,5,8,3,6,0}, table[7]); 
		Assert.assertArrayEquals(new int [] {8,2,5,6,0,3,1,4,7}, table[8]); 
		
		SwitchableColumnTriplet stc = findFirstSwitchableColumnTriplet(table);
		assertEquals(new SwitchableColumnTriplet(0, 0, 5), stc);
		table = stc.switchTriplet(table);
		Assert.assertArrayEquals(new int [] {1,3,6,4,7,0,8,2,5}, table[0]); 
		Assert.assertArrayEquals(new int [] {2,4,7,5,8,1,6,0,3}, table[1]); 
		Assert.assertArrayEquals(new int [] {0,5,8,3,6,2,7,1,4}, table[2]);
		
		SwitchableRowTriplet str = findFirstSwitchableRowTriplet(table);
		assertEquals(new SwitchableRowTriplet(0, 3, 6), str);
		table = str.switchTriplet(table);
		
		Assert.assertArrayEquals(new int [] {6,0,3,7,1,4,5,8,2}, table[3]); 
		Assert.assertArrayEquals(new int [] {4,7,1,8,2,5,0,3,6}, table[4]); 
		Assert.assertArrayEquals(new int [] {5,8,2,0,3,6,4,7,1}, table[5]); 
		Assert.assertArrayEquals(new int [] {3,6,0,1,4,7,2,5,8}, table[6]); 
		Assert.assertArrayEquals(new int [] {7,1,4,2,5,8,3,6,0}, table[7]); 
		Assert.assertArrayEquals(new int [] {8,2,5,6,0,3,1,4,7}, table[8]); 
	}
	

	private SwitchableRowTriplet findFirstSwitchableRowTriplet(int[][] table) {
		for(int bc = 0; bc < 3; bc++){
			for(int i1 = 0; i1 < 8; i1++){
				for(int i2 = i1+1; i2< 9; i2++){
					TripletSet ts1 = retrieveRowTriplet(i1, bc, table);
					TripletSet ts2 = retrieveRowTriplet(i2, bc, table);
					if(ts1.equals(ts2)) return new SwitchableRowTriplet(bc, i1, i2);
				}
			}
		}
		return null;
	}

	private TripletSet retrieveRowTriplet(int i, int bc, int[][] table) {
		int [] num = new int[3];
		for(int j = 0; j < 3; j++){
			num[j] = table[i][bc*3 + j];
		}
		return new TripletSet(num);
	}

	private SwitchableColumnTriplet findFirstSwitchableColumnTriplet(
			int[][] table) {
		for(int br = 0; br < 3; br ++){
			for(int j1 = 0; j1 < 8; j1++){
				for(int j2 = j1+1; j2 < 9; j2++){
					TripletSet ts1 = retrieveColumnTriplet(br, j1, table);
					TripletSet ts2 = retrieveColumnTriplet(br, j2, table);
					if(ts1.equals(ts2))
						return new SwitchableColumnTriplet(br, j1, j2);
				}
			}
		}
		return null;
	}

	private TripletSet retrieveColumnTriplet(int br, int j,
			int[][] table) {
		int [] num = new int[3];
		for(int i = 0; i < 3; i++){
			num[i] = table[br*3 + i][j];
		}
		return new TripletSet(num);
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
