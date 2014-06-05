package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestSudokuSolver {
	SudokuSolver solver = new SudokuSolver(1);
	Pair p0 = new Pair(0, 0);
	Pair p1 = new Pair(0, 1);

	@Test
	public void testFindFirstUnsolvedSpot() {
		Pair [][] puzzle = {{p0, null}, {p0, p0}};
		Spot spot = solver.findFirstUnsolvedSpot(puzzle);
		assertEquals(spot, new Spot(0, 1));
	}
	
	@Test
	public void testAllSpotAreSolved(){
		Pair [][] puzzle = {{p0, p0}, {p0, p0}};
		Spot spot = solver.findFirstUnsolvedSpot(puzzle);
		assertEquals(spot, new Spot(-1, -1));
	}
	
	@Test
	public void testValidationInRow(){
		Pair [][] puzzle = {{p0, null},{null, null}};
		assertTrue(solver.isPairValidInSpot(puzzle, p1 ,0, 1));
		assertFalse(solver.isPairValidInSpot(puzzle, p0 ,0, 1));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 1, 1));
	}
	
	@Test
	public void testValidationInColumn(){
		Pair [][] puzzle = {{p0, null},{null, null}};
		assertTrue(solver.isPairValidInSpot(puzzle, p1 ,1, 0));
		assertFalse(solver.isPairValidInSpot(puzzle, p0 ,1, 0));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 1, 1));
	}
	
	@Test
	public void testValidationInBlock(){
		Pair [][] puzzle = {{p0, null, null, null},
				            {null, null, null, null},
				            {null, p1, null, null},
				            {null, null, null, null}};
		SudokuSolver solver = new SudokuSolver(2);
		assertFalse(solver.isPairValidInSpot(puzzle, p0, 1, 1));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 1, 2));
		assertTrue(solver.isPairValidInSpot(puzzle, p0, 3, 1));
		assertFalse(solver.isPairValidInSpot(puzzle, p1, 3, 1));
	}
	
	@Test
	public void testSolvePuzzleAlreadyFinshed(){
		Pair [][] puzzle = {{p0, p0},
							{p0, p0}};
		ArrayList<Pair[][]>solutions = new ArrayList<Pair[][]>();
		solver.solve(puzzle, solutions);
		assertEquals(1, solutions.size());
		puzzle[1][1] = p1;
		assertEquals(p1, puzzle[1][1]);
		assertEquals(p0, solutions.get(0)[1][1]);
	}
	
	@Test
	public void testSolvePuzzleWithoutSolution(){
		Pair [][] puzzle = {{p0, p0},
							{null, p0}};
		ArrayList<Pair[][]>solutions = new ArrayList<Pair[][]>();
		solver.setCandidatePairs(new Pair[]{p0});
		solver.solve(puzzle, solutions);
		assertEquals(0, solutions.size());
	}
	
	@Test
	public void testSolvePuzzleOneSolution(){
		Pair [][] puzzle = {{p0, p0},
							{null, p0}};
		ArrayList<Pair[][]>solutions = new ArrayList<Pair[][]>();
		solver.setCandidatePairs(new Pair[]{new Pair(1,1)});
		solver.solve(puzzle, solutions);
		assertEquals(1, solutions.size());
	}
	
	@Test
	public void testSolvePuzzleOneRealSolution(){
		Pair [][] puzzle = {{p1, null},
							{null, null}};
		ArrayList<Pair[][]>solutions = new ArrayList<Pair[][]>();
		solver.setCandidatePairs(new Pair[]{p0, p1});
		solver.solve(puzzle, solutions);
		assertEquals(1, solutions.size());
		Pair [][] solution = solutions.get(0);
		assertEquals(p0, solution[0][1]);
		assertEquals(p0, solution[1][0]);
		assertEquals(p1, solution[1][1]);
	}
	
	@Test
	public void testTryARealOne(){
		SudokuSolver solver = new SudokuSolver(3);
		Pair p1  = new Pair(0, 1);
		Pair p2  = new Pair(0, 2);
		Pair p3  = new Pair(0, 3);
		Pair p4  = new Pair(0, 4);
		Pair p5  = new Pair(0, 5);
		Pair p6  = new Pair(0, 6);
		Pair p7  = new Pair(0, 7);
		Pair p8  = new Pair(0, 8);
		Pair p9  = new Pair(0, 9);
		Pair [] candidates = {p1, p2, p3, p4, p5, p6, p7, p8, p9};
		Pair [][] puzzle = {{null, null, p4, p7, null, p3, null, null, null},
							{null, p5, null, null, null, p6, p8, null, null},
							{p6, p7, null, p8, p4, null, null, p2, p3},
							{null, null, null, p1, p2, p8, null, null, null},
							{null, null, p7, null, null, null, p2, null, null},
							{null, null, null, p6, p7, p5, null, null, null},
							{p3, p8, null, null, p2, p7, null, p4, p9},
							{null, null, p5, p4, null, null, null, p8, null},
							{null, null, null, p9, null, p8, p3, null, null}};
		
       ArrayList<Pair[][]>solutions = new ArrayList<Pair[][]>();
       solver.setCandidatePairs(candidates);
       solver.solve(puzzle, solutions);
       assertEquals(1, solutions.size());
       Pair[][] s = solutions.get(0);
       for(int i = 0; i < 9; i++){
    	   for(int j = 0; j < 9; j++){
    		   System.out.print(s[i][j].toString() + " ");
    	   }
    	   System.out.println();
       }
    	   

	}
}
