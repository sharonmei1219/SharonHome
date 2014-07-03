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
	Mockery context = new Mockery();
	final PuzzleDao puzzleDao = context.mock(PuzzleDao.class);
	final HelperGeneratorInterface helperGen = context.mock(HelperGeneratorInterface.class);
	SudokuGeneratorController ctrl = new SudokuGeneratorController();

	@Test
	public void test() {
		ctrl.setPuzzleDao(puzzleDao);
		ModelMap map = loadPage();
		Progress p = (Progress)map.get("progress");
		assertEquals(1, p.getStoredEasy());
		assertEquals(2, p.getStoredNormal());
		assertEquals(3, p.getStoredHard());
		assertEquals(4, p.getStoredEvil());
	}
	
	@Test
	public void testEasyPuzzleGeneration() {
		ctrl.setPuzzleDao(puzzleDao);
		ctrl.setHelperGenerator(helperGen);
		loadPage();
		generatePuzzle(1, "easy");
//		Progress progress = ctrl.getProgress();
//		assertEquals(1, progress.getEasy());
//		assertEquals(2, progress.getStoredEasy());
	}
	
	@Test
	public void testNormalPuzzleGeneration() {
//		ctrl.setPuzzleDao(puzzleDao);
//		ctrl.setHelperGenerator(helperGen);
//		loadPage();
//		generatePuzzle(1, "normal");
//		Progress progress = ctrl.getProgress();
//		assertEquals(1, progress.getNormal());
//		assertEquals(3, progress.getStoredNormal());
	}
	
	@Test
	public void testHardPuzzleGeneration() {
//		ctrl.setPuzzleDao(puzzleDao);
//		ctrl.setHelperGenerator(helperGen);
//		loadPage();
//		generatePuzzle(1, "hard");
//		Progress progress = ctrl.getProgress();
//		assertEquals(1, progress.getHard());
//		assertEquals(4, progress.getStoredHard());
	}
	
	@Test
	public void testEvilPuzzleGeneration() {
//		ctrl.setPuzzleDao(puzzleDao);
//		ctrl.setHelperGenerator(helperGen);
//		loadPage();
//		generatePuzzle(1, "evil");
//		Progress progress = ctrl.getProgress();
//		assertEquals(1, progress.getEvil());
//		assertEquals(5, progress.getStoredEvil());
	}
	
	@Test
	public void testTotalPuzzleGenerated() {
//		ctrl.setPuzzleDao(puzzleDao);
//		ctrl.setHelperGenerator(helperGen);
//		loadPage();
//		generatePuzzle(2, "evil");
//		Progress progress = ctrl.getProgress();
//		assertEquals(2, progress.getTotal());
//		assertEquals(2, progress.getEvil());
//		assertEquals(6, progress.getStoredEvil());
//		generatePuzzle(3, "evil");
//		progress = ctrl.getProgress();
//		assertEquals(3, progress.getTotal());
//		assertEquals(3, progress.getEvil());
//		assertEquals(9, progress.getStoredEvil());
	}

	private ModelMap loadPage() {
		context.checking(new Expectations() {{
		    oneOf(puzzleDao).numberOfPuzzle("easy");will(returnValue(1));
		    oneOf(puzzleDao).numberOfPuzzle("normal");will(returnValue(2));
		    oneOf(puzzleDao).numberOfPuzzle("hard");will(returnValue(3));
		    oneOf(puzzleDao).numberOfPuzzle("evil");will(returnValue(4));
		}}
		);
		
		ModelMap map = new ModelMap();
		ctrl.loadPage(map);
		return map;
	}

	private void generatePuzzle(int numberOfPuzzle, final String level) {
		StartParameter sp = new StartParameter();
		sp.setNumberOfHolesInPuzzle(1);
		sp.setNumberOfPuzzleToGenerate(numberOfPuzzle);
		for(int i = 0; i < numberOfPuzzle; i++){
		context.checking(new Expectations() {{
			oneOf(puzzleDao).insertPuzzle("easy", "[[]]");
			oneOf(helperGen).generateOriginalPuzzle(1); will(returnValue(new Puzzle(new int[][]{})));
			oneOf(helperGen).rank(with(any(Puzzle.class))); will(returnValue(level));
			oneOf(helperGen).permutate(with(any(Puzzle.class))); will(returnValue(new Puzzle(new int[][] {{}})));
			oneOf(helperGen).validatePermedPuzzle(with(any(Puzzle.class))); will(returnValue(true));
		}}
		);}
		ctrl.startGeneration(sp);
	}
}
