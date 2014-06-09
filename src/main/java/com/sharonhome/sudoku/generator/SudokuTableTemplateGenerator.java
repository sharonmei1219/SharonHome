package com.sharonhome.sudoku.generator;

public class SudokuTableTemplateGenerator {
	final static private int [] row = {0, 3, 6, 1, 4, 7, 2, 5, 8};
	public int [][] genTable(int[] column){
		int [][] result = new int [9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				result[i][j] = (row[i] + column[j])%9;
			}
		}
		return result;
	}
}
