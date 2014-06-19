package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sharonhome.sudoku.generator.Spot;

public class TestPuzzle {
	Puzzle puzzle = new Puzzle(new int [][]{
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1},
			{-1, -1, -1, -1, -1, -1, -1, -1, -1}
	});

	@Test
	public void testConstructionWithEmptyPuzzle() {
		ArrayList<Single> determinedValues = puzzle.getDeterminedValues();
		assertEquals(0, determinedValues.size());
	}
	
	@Test
	public void testUpdatePuzzle(){
		Single single = new Single(5, new Spot(6, 7));
		puzzle.update(single);
		ArrayList<Single> determinedValues = puzzle.getDeterminedValues();
		assertEquals(1, determinedValues.size());
		assertEquals(5, determinedValues.get(0).getValue());
		assertEquals(6, determinedValues.get(0).geti());
		assertEquals(7, determinedValues.get(0).getj());
		
	}
	
	@Test
	public void testPuzzleNotSolved(){
		assertFalse(puzzle.solved());
	}
	
	@Test
	public void testPuzzleSolved(){
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0, 3, 6, 1, 4, 7, 2, 5, 8},
				{ 1, 4, 7, 2, 5, 8, 3, 6, 0},
				{ 2, 5, 8, 3, 6, 0, 4, 7, 1},
				{ 3, 6, 0, 4, 7, 1, 5, 8, 2},
				{ 4, 7, 1, 5, 8, 2, 6, 0, 3},
				{ 5, 8, 2, 6, 0, 3, 7, 1, 4},
				{ 6, 0, 3, 7, 1, 4, 8, 2, 5},
				{ 7, 1, 4, 8, 2, 5, 0, 3, 6},
				{ 8, 2, 5, 0, 3, 6, 1, 4, 7}
		});
		assertTrue(puzzle.solved());
	}
	
	@Test
	public void testPuzzleNotSolvedForMistakes(){
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0, 3, 6, 0, 4, 7, 2, 5, 8},
				{ 1, 4, 7, 2, 5, 8, 3, 6, 0},
				{ 2, 5, 8, 3, 6, 0, 4, 7, 1},
				{ 3, 6, 0, 4, 7, 1, 5, 8, 2},
				{ 4, 7, 1, 5, 8, 2, 6, 0, 3},
				{ 5, 8, 2, 6, 0, 3, 7, 1, 4},
				{ 6, 0, 3, 7, 1, 4, 8, 2, 5},
				{ 7, 1, 4, 8, 2, 5, 0, 3, 6},
				{ 8, 2, 5, 0, 3, 6, 1, 4, 7}
		});
		assertTrue(puzzle.solved());
	}
}
