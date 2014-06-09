package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestTableDigger {

	@Test
	public void testShufflePos() {
		Permutations pers = new Permutations(new int[]{0, 1, 2, 3});
		TestRandom rand = new TestRandom();
		rand.setNextRandom(23);
		int perNr = rand.nextInt(24);
		assertEquals(24, pers.total());
		int[] per = pers.get(perNr);
		Assert.assertArrayEquals(new int []{3,  2, 1, 0}, per);
		Spot[] pos = mapNumberToSpot(per, 2);
		assertEquals(new Spot(1, 1), pos[0]);
		assertEquals(new Spot(1, 0), pos[1]);
		assertEquals(new Spot(0, 1), pos[2]);
		assertEquals(new Spot(0, 0), pos[3]);
	}
	
	@Test
	public void testDig1Hole(){
		int [][] puzzle = {{0, 0}, {0, 0}};
		SudokuSolver solver = new SudokuSolver(1);
		solver.setSolutionCandidates(new int []{1});
		Spot[] pos = new Spot[]{new Spot(0,0), new Spot(0, 1), new Spot(1, 0), new Spot(1, 1)};
		Spot[] holes = dig(puzzle, solver, pos, 1);
		assertEquals(1, holes.length);
		assertEquals(new Spot(0, 0), holes[0]);
		
		Spot[] holes2 = dig(puzzle, solver, pos, 2);
		assertEquals(2, holes2.length);
		assertEquals(new Spot(0, 0), holes2[0]);
		assertEquals(new Spot(1, 1), holes2[1]);
	}
	
	@Test
	public void testDig12Holes(){
		int [][] puzzle = {{0, 0}, {0, 0}};
		SudokuSolver solver = new SudokuSolver(1);
		solver.setSolutionCandidates(new int []{1});
		Spot[] pos = new Spot[]{new Spot(0,0), new Spot(0, 1), new Spot(1, 0), new Spot(1, 1)};
		
		Spot[] holes2 = dig(puzzle, solver, pos, 2);
		assertEquals(2, holes2.length);
		assertEquals(new Spot(0, 0), holes2[0]);
		assertEquals(new Spot(1, 1), holes2[1]);
	}
	
	@Test
	public void testDigReturnsNull(){
		int [][] puzzle = {{0, 1}, {0, 1}};
		SudokuSolver solver = new SudokuSolver(1);
		solver.setSolutionCandidates(new int []{1});
		Spot[] pos = new Spot[]{new Spot(0,0), new Spot(0, 1), new Spot(1, 0), new Spot(1, 1)};
		
		Spot[] holes2 = dig(puzzle, solver, pos, 2);
		assertNull(holes2);
	}
	
	@Test
	public void testDigARealOne(){
		int [][] puzzle = {{8,2,4,7,5,3,9,1,6},
						   {9,5,3,2,1,6,8,7,4},
						   {6,7,1,8,4,9,5,2,3},
						   {4,6,9,1,2,8,7,3,5},
						   {5,1,7,3,9,4,2,6,8},
						   {2,3,8,6,7,5,4,9,1},
						   {3,8,6,5,2,7,1,4,9},
						   {7,9,5,4,3,1,6,8,2},
						   {1,4,2,9,6,8,3,5,7}};
		int r = (new Random()).nextInt(100);
		int [] pind = new int[81];
		for(int i = 0; i < 81; i++)
			pind[i] = i;
		Permutations pers = new Permutations(pind);
		
	}
	
	private Spot[] dig(int[][] puzzle, SudokuSolver solver, Spot[] pos, int requiredHoles) {
		int [][] localPuzzle = deepcopy(puzzle);
		int nrOfHoles = 0;
		Spot[] result = new Spot[requiredHoles];
		int i = 0;
		while(nrOfHoles < requiredHoles && i < pos.length){
			int x = pos[i].geti();
			int y = pos[i].getj();
			int temp = localPuzzle[x][y];
			localPuzzle[x][y] = -1;
			ArrayList<int[][]> solutions = new ArrayList<int[][]>();
			solver.solve(localPuzzle, solutions);
			if(solutions.size() == 1){
				result[nrOfHoles++] = pos[i];
			}else{
				localPuzzle[x][y]= temp;
			}
			i ++;
		}
		if (nrOfHoles == requiredHoles)
			return result;
		return null;
	}

	private int[][] deepcopy(int[][] puzzle) {
		int result[][] = new int [puzzle.length][puzzle[0].length];
		for(int i = 0; i < puzzle.length; i ++){
			for(int j = 0; j < puzzle[i].length; j++){
				result[i][j] = puzzle[i][j];
			}
		}
		return result;
	}

	private Spot[] mapNumberToSpot(int[] per, int nrColumn) {
		Spot [] result = new Spot[per.length];
		for(int i = 0; i < per.length; i++){
			int x = per[i]/nrColumn;
			int y = per[i]%nrColumn;
			result[i] = new Spot(x, y);
		}
		return result;
	}

	class TestRandom implements SudokuRandom{
		private int nextRandom;
		public void setNextRandom(int nextRandom){
			this.nextRandom = nextRandom;
		};
		public int nextInt(int range) {
			return nextRandom;
		}
		
	}
}