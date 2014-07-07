package com.sharonhome.sudoku.controller;

import java.util.ArrayList;
import java.util.Random;

import com.sharonhome.sudoku.generator.Permutations;
import com.sharonhome.sudoku.generator.PuzzleGenerator;
import com.sharonhome.sudoku.generator.SudokuSolver;
import com.sharonhome.sudoku.ranking.Puzzle;
import com.sharonhome.sudoku.ranking.PuzzleRanking;
import com.sharonhome.sudoku.table.RandomTableGenerator;

public class HelperGenerator implements HelperGeneratorInterface {
	private static final int CSLCR_MAX = 46656;

	public Puzzle generateOriginalPuzzle(int numberOfHoles) {
		Random rand = new Random();
		PuzzleGenerator gen = new PuzzleGenerator();
		int tableIndex = rand.nextInt(CSLCR_MAX);
		int [][] puzzle = gen.generatePuzzle(tableIndex, numberOfHoles);
		if(puzzle == null) return null;
		return  new Puzzle(puzzle);
	}
	
	public Puzzle generatorPuzzle(int holeCount){
		PuzzleGenerator gen = new PuzzleGenerator();
		int [][] puzzle = gen.generatePuzzleNew(holeCount);
		if(puzzle == null) return null;
		return new Puzzle(puzzle);
	}


	public String rank(Puzzle puzzle) {
		PuzzleRanking ranking = new PuzzleRanking();
		int rank =  ranking.ranking(puzzle);
		if(rank <= 7){
			return "easy";
		}else if(rank <= 13){
			return "normal";
		}else if(rank <= 20){
			return "hard";
		}else if(rank <= 500){
			return "evil";
		}
		return "bruteForce";
	}

	public Puzzle permutate(Puzzle inputPuzzle) {
		Random rand = new Random();
		Permutations permutations = new Permutations(new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9});
		int perIndex = rand.nextInt(permutations.total());
		int [] permutation = permutations.get(perIndex);
		return inputPuzzle.permutate(permutation);
	}

	public boolean validatePermedPuzzle(Puzzle puzzle) {
		if(! puzzle.validate()) return false;
		SudokuSolver solver = new SudokuSolver(3);
		solver.setSolutionCandidates(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		ArrayList<int[][]>solutions = new ArrayList<int[][]>();
		solver.solve(puzzle.puzzle, solutions);
		if(solutions.size() != 1) return false;
		return true;
	}
}
