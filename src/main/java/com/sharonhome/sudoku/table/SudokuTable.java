package com.sharonhome.sudoku.table;

import java.util.ArrayList;

public interface SudokuTable {

	ArrayList<int[]> getAllRows();
	ArrayList<int[]> getAllColumns();
	ArrayList<int[]> getAllBlocks();

}
