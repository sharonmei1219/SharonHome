package com.sharonhome.sudoku.generator;

public class TableGenerator {
	final static private int [] columnTitle = {0, 3, 6, 1, 4, 7, 2, 5, 8};
	public int [][] genTable(int[] cslcr1, int [] cslcr2, int [] cslcr3){
		int [][] result = new int [9][9];
		
		for(int j = 0; j < 3; j++){
			int [] column = calculateColum(j, cslcr1);
			putColumnIn(j, result, column);
		}
		
		for(int j = 3; j < 6; j++){
			int [] column = calculateColum(j, cslcr2);
			putColumnIn(j, result, column);
		}
		
		for(int j = 6; j < 9; j++){
			int [] column = calculateColum(j, cslcr3);
			putColumnIn(j, result, column);
		}

		return result;
	}
	
	private int[] calculateColum(int index, int [] cslcr){
		int [] result = new int[9];
		for(int i = 0; i < 9; i ++)
			result[i] = (columnTitle[index] + cslcr[i])%9;
		return result;
	}
	
	private void putColumnIn(int index, int[][] result, int [] column){
		for(int i = 0; i < 9; i ++){
			result[i][index] = column[i];
		}
	}
}
