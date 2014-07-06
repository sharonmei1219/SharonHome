package com.sharonhome.sudoku.table;

public interface CanTable {
	boolean full();
	int [] candidatesForNextEmptyCell();
	CanTable fillEmptyCell(int i);
	boolean validate();

}
