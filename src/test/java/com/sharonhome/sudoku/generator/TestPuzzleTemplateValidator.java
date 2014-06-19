package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.Gson;

public class TestPuzzleTemplateValidator {
	TemplateValidator validator = new TemplateValidator();
	@Test
	public void testNotAValidTemplateAsMoreThanOneSolution() {
		int [][] puzzle = {{0, 1, 2}, {3, 4, 5}, {-1, -1, -1}};
		String table = "[[]]";
		assertFalse(validator.validate(puzzle, table));
	}
	
	@Test
	public void testNotAValidTemplateAsSolutionIsNotIdenticalWithTable() {
		int [][] puzzle = {{0, 1, 2}, {3, 4, 5}, {6, 7, -1}};
		String table = "[[0,1,2],[3,4,5],[8,7,6]]";
		assertFalse(validator.validate(puzzle, table));
	}
	
	@Test
	public void testAValidTemplateAsSolutionIsIdenticalWithTable() {
		int [][] puzzle = {{0, 1, 2}, {3, 4, 5}, {6, 7, -1}};
		String table = "[[0,1,2],[3,4,5],[6,7,8]]";
		assertTrue(validator.validate(puzzle, table));
	}

}
