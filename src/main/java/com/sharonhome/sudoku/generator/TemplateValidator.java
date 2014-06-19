package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

import com.google.gson.Gson;

public class TemplateValidator {
	SudokuSolver solver = new SudokuSolver(3);
	Gson gson = new Gson();
	public TemplateValidator(){
		solver.setSolutionCandidates(new int []{0, 1, 2, 3, 4, 5, 6, 7, 8});
	}
	
	public boolean validate(int[][] puzzle, String table){
		ArrayList <int [][]> solutions = new ArrayList<int[][]>();
		solver.solve(puzzle, solutions);
		if(solutions.size() != 1) return false;
		if(!table.equals(gson.toJson(solutions.get(0)))) return false;
		return true;
	}
}
