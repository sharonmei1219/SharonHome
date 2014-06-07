package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestSudokuSolver {
	SudokuSolver solver = new SudokuSolver(1);
	int p0 = 0;
	int p1 = 1;

	@Test
	public void testFindFirstUnsolvedSpot() {
		int [][] puzzle = {{p0, -1}, {p0, p0}};
		Spot spot = solver.findFirstUnsolvedSpot(puzzle);
		assertEquals(spot, new Spot(0, 1));
	}
	
	@Test
	public void testAllSpotAreSolved(){
		int [][] puzzle = {{p0, p0}, {p0, p0}};
		Spot spot = solver.findFirstUnsolvedSpot(puzzle);
		assertEquals(spot, new Spot(-1, -1));
	}
	
	@Test
	public void testValidationInRow(){
		int [][] puzzle = {{p0, -1},{-1, -1}};
		assertTrue(solver.isPairValidInSpot(puzzle, p1 ,0, 1));
		assertFalse(solver.isPairValidInSpot(puzzle, p0 ,0, 1));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 1, 1));
	}
	
	@Test
	public void testValidationInColumn(){
		int [][] puzzle = {{p0, -1},{-1, -1}};
		assertTrue(solver.isPairValidInSpot(puzzle, p1 ,1, 0));
		assertFalse(solver.isPairValidInSpot(puzzle, p0 ,1, 0));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 1, 1));
	}
	
	@Test
	public void testValidationInBlock(){
		int [][] puzzle = {{p0, -1, -1, -1},
				            {-1, -1, -1, -1},
				            {-1, p1, -1, -1},
				            {-1, -1, -1, -1}};
		SudokuSolver solver = new SudokuSolver(2);
		assertFalse(solver.isPairValidInSpot(puzzle, p0, 1, 1));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 1, 2));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 3, 1));
		assertFalse(solver.isPairValidInSpot(puzzle, p1, 3, 1));
	}
	
	@Test
	public void testSolvePuzzleAlreadyFinshed(){
		int [][] puzzle = {{p0, p0},
							{p0, p0}};
		ArrayList<int[][]>solutions = new ArrayList<int[][]>();
		solver.solve(puzzle, solutions);
		assertEquals(1, solutions.size());
		puzzle[1][1] = p1;
		assertEquals(p1, puzzle[1][1]);
		assertEquals(p0, solutions.get(0)[1][1]);
	}
	
	@Test
	public void testSolvePuzzleWithoutSolution(){
		int [][] puzzle = {{p0, p0},
							{-1, p0}};
		ArrayList<int[][]>solutions = new ArrayList<int[][]>();
		solver.setCandidatePairs(new int[]{p0});
		solver.solve(puzzle, solutions);
		assertEquals(0, solutions.size());
	}
	
	@Test
	public void testSolvePuzzleOneSolution(){
		int [][] puzzle = {{p0, p0},
							{-1, p0}};
		ArrayList<int[][]>solutions = new ArrayList<int[][]>();
		solver.setCandidatePairs(new int[]{p1});
		solver.solve(puzzle, solutions);
		assertEquals(1, solutions.size());
	}
	
	@Test
	public void testSolvePuzzleOneRealSolution(){
		int [][] puzzle = {{p1, -1},
							{-1, -1}};
		ArrayList<int[][]>solutions = new ArrayList<int[][]>();
		solver.setCandidatePairs(new int[]{p0, p1});
		solver.solve(puzzle, solutions);
		assertEquals(1, solutions.size());
		int [][] solution = solutions.get(0);
		assertEquals(p0, solution[0][1]);
		assertEquals(p0, solution[1][0]);
		assertEquals(p1, solution[1][1]);
	}
	
	@Test
	public void testTryARealOne(){
		SudokuSolver solver = new SudokuSolver(3);
		int p1  = 1;
		int p2  = 2;
		int p3  = 3;
		int p4  = 4;
		int p5  = 5;
		int p6  = 6;
		int p7  = 7;
		int p8  = 8;
		int p9  = 9;
		int [] candidates = {p1, p2, p3, p4, p5, p6, p7, p8, p9};
		int [][] puzzle = {{-1, -1, p4, p7, -1, p3, -1, -1, -1},
							{-1, p5, -1, -1, -1, p6, p8, -1, -1},
							{p6, p7, -1, p8, p4, -1, -1, p2, p3},
							{-1, -1, -1, p1, p2, p8, -1, -1, -1},
							{-1, -1, p7, -1, -1, -1, p2, -1, -1},
							{-1, -1, -1, p6, p7, p5, -1, -1, -1},
							{p3, p8, -1, -1, p2, p7, -1, p4, p9},
							{-1, -1, p5, p4, -1, -1, -1, p8, -1},
							{-1, -1, -1, p9, -1, p8, p3, -1, -1}};
		
       ArrayList<int[][]>solutions = new ArrayList<int[][]>();
       solver.setCandidatePairs(candidates);
       solver.solve(puzzle, solutions);
       assertEquals(1, solutions.size());
       int[][] s = solutions.get(0);
       for(int i = 0; i < 9; i++){
    	   for(int j = 0; j < 9; j++){
    		   System.out.print(s[i][j] + " ");
    	   }
    	   System.out.println();
       }
    	   

	}
}
