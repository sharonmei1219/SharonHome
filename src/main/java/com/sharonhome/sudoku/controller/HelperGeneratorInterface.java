package com.sharonhome.sudoku.controller;

import com.sharonhome.sudoku.ranking.Puzzle;

public interface HelperGeneratorInterface {
	public Puzzle generateOriginalPuzzle(int numberOfHoles);
	public String rank(Puzzle puzzle);
	public Puzzle permutate(Puzzle inputPuzzle);
	public boolean validatePermedPuzzle(Puzzle puzzle);
	public Puzzle generatorPuzzle(int holeCount);
}
