package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestPuzzleRanking {

	@Test
	public void testElminateRow() {
		PossiblePositions pp = new PossiblePositions(new int [][] {{-1, -1},{-1, -1}});
		pp.removeRow(0);
		ArrayList<Spot> remaining = pp.remaining();
		assertEquals(2, remaining.size());
		assertEquals(new Spot(1,0), remaining.get(0));
		assertEquals(new Spot(1,1), remaining.get(1));
	}
	
	@Test
	public void testElminateColumn(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		pp.removeColumn(0);
		ArrayList<Spot> remaining = pp.remaining();
		assertEquals(2, remaining.size());
		assertEquals(new Spot(0,1), remaining.get(0));
		assertEquals(new Spot(1,1), remaining.get(1));
	}
	
	@Test
	public void testElminateBlock(){
		PossiblePositions pp = new PossiblePositions(4, 4);
		pp.removeBlock(1, 1, 2);
		ArrayList<Spot> remaining = pp.remaining();
		assertEquals(12, remaining.size());
		assertEquals(new Spot(0,2), remaining.get(0));
		assertEquals(new Spot(0,3), remaining.get(1));
		assertEquals(new Spot(1,2), remaining.get(2));
		assertEquals(new Spot(1,3), remaining.get(3));
		assertEquals(new Spot(2,0), remaining.get(4));
		assertEquals(new Spot(3,3), remaining.get(11));
	}
	
	@Test
	public void notOnlyOneInARow(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		assertFalse(pp.onlyOneInARow(new Spot(0, 0)));
	}
	
	@Test
	public void onlyOneInARow(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		pp.removeColumn(1);
		assertTrue(pp.onlyOneInARow(new Spot(0, 0)));
	}
	
	@Test
	public void notOnlyOneInAColumn(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		assertFalse(pp.onlyOneInAColumn(new Spot(0, 0)));
	}
	
	@Test
	public void onlyOneInAColumn(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		pp.removeRow(1);
		assertTrue(pp.onlyOneInAColumn(new Spot(0, 0)));
	}
	
	@Test
	public void notOnlyOneInABlock(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		assertFalse(pp.onlyOneInABlock(new Spot(0, 0), 2));
	}
	
	@Test
	public void notOnlyOneInABlockEvenRemoveARow(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		pp.removeColumn(1);
		assertFalse(pp.onlyOneInABlock(new Spot(0, 0), 2));
	}
	
	@Test
	public void onlyOneInAblock(){
		PossiblePositions pp = new PossiblePositions(2, 2);
		pp.removeColumn(1);
		pp.removeRow(1);
		assertTrue(pp.onlyOneInABlock(new Spot(0, 0), 2));
	}
	
	@Test
	public void puzzleAlreaySolved(){
		int [][] puzzle = {{0, 1},{1, 0}};
		SolverEliminateRowColumnBlock solver = new SolverEliminateRowColumnBlock();
		solver.setSolutionCandidate(new int[] {0, 1});
		solver.setBlockSize(1);
		int [][] table = solver.solve(puzzle);
		PossiblePositions pp = new PossiblePositions(table);
		ArrayList<Spot> remaining = pp.remaining();
		assertEquals(0, remaining.size());
	}
	
	@Test
	public void elminateAll0(){
		int [][] puzzle = {{0, 1},{1, -1}};
		SolverEliminateRowColumnBlock solver = new SolverEliminateRowColumnBlock();
		solver.setSolutionCandidate(new int[] {0});
		solver.setBlockSize(1);
		int [][] table = solver.solve(puzzle);
		PossiblePositions pp = new PossiblePositions(table);
		ArrayList<Spot> remaining = pp.remaining();
		assertEquals(0, remaining.size());
	}
	
	@Test
	public void elminateAll1(){
		int [][] puzzle = {{0, 1},{1, -1}};
		SolverEliminateRowColumnBlock solver = new SolverEliminateRowColumnBlock();
		solver.setSolutionCandidate(new int[] {1});
		solver.setBlockSize(1);
		int [][] table = solver.solve(puzzle);
		PossiblePositions pp = new PossiblePositions(table);
		ArrayList<Spot> remaining = pp.remaining();
		assertEquals(1, remaining.size());
	}
	


}
