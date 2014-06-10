package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import org.jmock.Mockery;
import org.junit.Test;

public class TestRandomlyPerPuzzle {
	Mockery context = new Mockery();
	@Test
	public void test() {
		RandomNumberGen rand = context.mock(RandomNumberGen.class);
		int [][] puzzle = {{0, 1},{1, -1}};
		RandomPuzzlePermutation rpp = new RandomPuzzlePermutation(puzzle, rand);
	}
}
