package com.sharonhome.sudoku.repository;

public interface PuzzleDao {
	public String getPuzzle(String level);
	public void insertPuzzle(String level, String puzzle);
}

