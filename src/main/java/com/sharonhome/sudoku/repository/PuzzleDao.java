package com.sharonhome.sudoku.repository;

public interface PuzzleDao {
	public String getPuzzle(String level);
	public void insertPuzzle(String level, String puzzle);
	public void insertTemplate(String puzzle, String table, int holeCount);
	public int numberOfPuzzle(String level);
	public String getBabyVersionPuzzle(String level);
	public String getBabyVersion5X5Puzzle(String level);
}

