package com.sharonhome.sudoku.controller;

import static org.junit.Assert.*;

import java.util.Random;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.sharonhome.sudoku.generator.PuzzleGenerator;
import com.sharonhome.sudoku.model.Progress;
import com.sharonhome.sudoku.model.StartParameter;
import com.sharonhome.sudoku.ranking.Puzzle;
import com.sharonhome.sudoku.ranking.PuzzleRanking;
import com.sharonhome.sudoku.repository.PuzzleDao;

public class TestProgressUpdater {

	@Test
	public void test() {
		Mockery context = new Mockery();
		final PuzzleDao puzzleDao = context.mock(PuzzleDao.class);
		SudokuGeneratorController ctrl = new SudokuGeneratorController();
		ctrl.setPuzzleDao(puzzleDao);
		context.checking(new Expectations() {{
		    oneOf(puzzleDao).numberOfPuzzle("easy");will(returnValue(1));
		    oneOf(puzzleDao).numberOfPuzzle("normal");will(returnValue(2));
		    oneOf(puzzleDao).numberOfPuzzle("hard");will(returnValue(3));
		    oneOf(puzzleDao).numberOfPuzzle("evil");will(returnValue(4));
		}}
		);
		ModelMap map = new ModelMap();
		ctrl.loadPage(map);
		Progress p = (Progress)map.get("progress");
		assertEquals(1, p.getStoredEasy());
		assertEquals(2, p.getStoredNormal());
		assertEquals(3, p.getStoredHard());
		assertEquals(4, p.getStoredEvil());
	}
	
}
