package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.sharonhome.sudoku.ranking.Puzzle;

public class TestPuzzle {
	Permutations permutations = new Permutations(new int [] {1, 2, 3, 4, 5, 6, 7, 8, 9});
	private Gson gson = new Gson();

	@Test
	public void testPermutation() {
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{ 0, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  2, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  3, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  4, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  5, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  6, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  7, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  8}
		});
		
		Puzzle expPuzzle = new Puzzle(new int [][]{
				{ 1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  2, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  3, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  4, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  6, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  7, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  8, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  9}
		});
		
		int [] perm = permutations.get(0);
		assertEquals(9, perm.length);
		assertEquals(expPuzzle, inputPuzzle.permutate(perm));
	}
	
	@Test
	public void testInversedPermutation() {
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{ 0, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  2, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  3, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  4, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  5, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  6, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  7, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  8}
		});
		
		Puzzle expPuzzle = new Puzzle(new int [][]{
				{ 9, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  8, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  6, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  4, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  2, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  1}
		});
		
		int [] perm = permutations.get(permutations.total() - 1);
		assertEquals(9, perm.length);
		assertEquals(expPuzzle, inputPuzzle.permutate(perm));
	}
	
	@Test
	public void testToString(){
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{ 9, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  8, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  6, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  4, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  2, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  1}
		});
		
		String [][]exp = {
				{"9", "", "", "", "", "", "", "", ""},
				{ "","8", "", "", "", "", "", "", ""},
				{ "", "","7", "", "", "", "", "", ""},
				{ "", "", "","6", "", "", "", "", ""},
				{ "", "", "", "","5", "", "", "", ""},
				{ "", "", "", "", "","4", "", "", ""},
				{ "", "", "", "", "", "","3", "", ""},
				{ "", "", "", "", "", "", "","2", ""},
				{ "", "", "", "", "", "", "", "","1"},
				};
		
		assertEquals(gson .toJson(exp), inputPuzzle.toString());
	}
	
	@Test
	public void testViolationInRow(){
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{ 9, -1, -1, -1, -1, -1, -1, -1,  9},
				{-1,  8, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  6, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  4, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  2, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  1}
		});
		
		assertFalse(inputPuzzle.validate());
	}
	
	@Test
	public void testViolationInColumn(){
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{ 9, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  8, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  6, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  4, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  2, -1},
				{ 9, -1, -1, -1, -1, -1, -1, -1,  1}
		});
		
		assertFalse(inputPuzzle.validate());
	}
	
	@Test
	public void testViolationInBlock(){
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{ 9, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  8, -1, -1, -1, -1, -1, -1, -1},
				{-1,  9,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  6, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  4, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  2, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  1}
		});
		
		assertFalse(inputPuzzle.validate());
	}
	
	@Test
	public void testViolationPass(){
		Puzzle inputPuzzle = new Puzzle(new int [][]{
				{ 9, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  8, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  6, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  4, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  2, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  1}
		});
		
		assertTrue(inputPuzzle.validate());
	}

}
