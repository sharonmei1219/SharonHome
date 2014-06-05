package com.sharonhome.sudoku.service;

public interface PuzzleRetriever {
	public String[][] retrieveRamdomPuzzle(String Level);
	public String[][] retrievePuzzleByID(String ID);
}
