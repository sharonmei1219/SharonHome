package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sharonhome.sudoku.generator.Spot;

public class TestPossiblePositionVector {
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
	PossiblePositionVector PP = new PossiblePositionVector(puzzle);
	ArrayList<Single> singleList = new ArrayList<Single>(); 

	@Test
	public void testConstruction(){
		singleList = PP.findNewSingle();
		assertEquals(0, singleList.size());
	}
	
	@Test
	public void testUpdateSingleWithRowImpacted() {
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0,  1,  2,  3,  4,  5,  6,  7, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);

		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(8, singleList.get(0).getValue());
		assertEquals(0, singleList.get(0).geti());
		assertEquals(8, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleWithColumnImpacted() {
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1,  0, -1, -1, -1, -1, -1, -1},
				{-1, -1,  1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  2, -1, -1, -1, -1, -1, -1},
				{-1, -1,  3, -1, -1, -1, -1, -1, -1},
				{-1, -1,  4, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  5, -1, -1, -1, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1,  8, -1, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(6, singleList.get(0).getValue());
		assertEquals(5, singleList.get(0).geti());
		assertEquals(2, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleWithBlockImpacted() {
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  0,  3,  7},
				{-1, -1, -1, -1, -1, -1,  1,  4, -1},
				{-1, -1, -1, -1, -1, -1,  2,  6,  8},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(5, singleList.get(0).getValue());
		assertEquals(4, singleList.get(0).geti());
		assertEquals(8, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleWithMixedImpact() {
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1,  3, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  3},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 3, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  3, -1, -1, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		singleList = PP.findNewSingle();

		assertEquals(1, singleList.size());
		assertEquals(3, singleList.get(0).getValue());
		assertEquals(1, singleList.get(0).geti());
		assertEquals(2, singleList.get(0).getj());
	}
	
	@Test
	public void testFindHiddenPairInRow(){
		//(0, 1)(0, 4) are only possible position for both 7 and 8
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  8, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  7},
				{-1, -1, -1,  7, -1, -1, -1, -1, -1},
				{ 7, -1, -1,  8, -1, -1, -1, -1, -1},
				{ 8, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  8, -1, -1,  7, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  8, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Pair> pairlist = new ArrayList<Pair>();
		ArrayList<Pair> newPairList = PP.findNewHiddenPair();
		
		assertEquals(1, newPairList.size());
		assertEquals(new Pair(new PossibleValues(new int[]{7, 8}),
				              new Spot(0, 1),
				              new Spot(0, 4)), 
				     newPairList.get(0));
		
		newPairList = PP.findNewHiddenPair();
		assertEquals(0, newPairList.size());
	}
	
	@Test
	public void testFindHiddenPairInColumn(){
		//(2, 3)(6, 3) are only possible position for both 2 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 2,  6, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  2,  6, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  2, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  6, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  2,  6, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  2,  6}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Pair> pairlist = new ArrayList<Pair>();
		ArrayList<Pair> newPairList = PP.findNewHiddenPair();
		
		assertEquals(1, newPairList.size());
		assertEquals(new Pair(new PossibleValues(new int[]{2, 6}),
				              new Spot(2, 3),
				              new Spot(6, 3)), 
				     newPairList.get(0));
		
		newPairList = PP.findNewHiddenPair();
		assertEquals(0, newPairList.size());
	}
	
	@Test
	public void testFindHiddenPairInBlock(){
		//(8, 3)(7, 5) are only possible position for both 3 and 5
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1,  3, -1, -1, -1, -1},
				{-1, -1, -1, -1,  5, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 3,  5, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  7, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  8, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Pair> pairlist = new ArrayList<Pair>();
		ArrayList<Pair> newPairList = PP.findNewHiddenPair();
		
		assertEquals(1, newPairList.size());
		assertEquals(new Pair(new PossibleValues(new int[]{3, 5}),
				              new Spot(8, 3),
				              new Spot(7, 5)), 
				     newPairList.get(0));
		
		newPairList = PP.findNewHiddenPair();
		assertEquals(0, newPairList.size());
	}
	
	@Test
	public void testUpdatePairInRow(){
		//(0, 6)(0, 8) are only possible position for both 5 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  0, -1,  2},
				{-1, -1, -1,  0, -1, -1,  1, -1,  3},
				{-1, -1, -1, -1,  6, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  6, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  5, -1},
				{-1, -1, -1, -1, -1, -1, -1,  6, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Pair> pairlist = new ArrayList<Pair>();
		ArrayList<Pair> newPairList = PP.findNewHiddenPair();
		
		assertEquals(1, newPairList.size());
		assertEquals(new Pair(new PossibleValues(new int[]{5, 6}),
				              new Spot(0, 6),
				              new Spot(0, 8)), 
				     newPairList.get(0));
		
		PP.update(newPairList.get(0));
		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(new Single(6, new Spot(1, 3)), singleList.get(0));
	}
	
	@Test
	public void testUpdatePairInColumn(){
		//(3, 4)(5, 4) are only possible position for both 7 and 8
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1,  0, -1, -1, -1},
				{ 7, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  7},
				{-1, -1, -1,  0, -1,  2, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  7,  8},
				{-1, -1, -1,  1, -1,  3, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Pair> pairlist = new ArrayList<Pair>();
		ArrayList<Pair> newPairList = PP.findNewHiddenPair();
		
		assertEquals(1, newPairList.size());
		assertEquals(new Pair(new PossibleValues(new int[]{7, 8}),
				              new Spot(3, 4),
				              new Spot(5, 4)), 
				     newPairList.get(0));
		
		PP.update(newPairList.get(0));
		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(new Single(7, new Spot(0, 3)), singleList.get(0));
	}
	
	@Test
	public void testUpdatePairInBlock(){
		//(0, 6)(0, 8) are only possible position for both 5 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 0,  1,  2,  3,  4,  5, -1,  7, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  0},
				{-1, -1, -1, -1, -1, -1, -1, -1,  1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  2},
				{-1, -1, -1, -1, -1, -1, -1, -1,  3},
				{-1, -1, -1,  6, -1, -1, -1, -1, -1},
				{-1,  6, -1, -1, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Pair> pairlist = new ArrayList<Pair>();
		ArrayList<Pair> newPairList = PP.findNewHiddenPair();
		
		assertEquals(1, newPairList.size());
		assertEquals(new Pair(new PossibleValues(new int[]{6, 8}),
				              new Spot(0, 6),
				              new Spot(0, 8)), 
				     newPairList.get(0));
		
		PP.update(newPairList.get(0));
		singleList = PP.findNewSingle();

		assertEquals(1, singleList.size());
		assertEquals(new Single(6, new Spot(0, 8)), singleList.get(0));
	}
	
	@Test
	public void testFindLockedCell(){
		//(0, 6)(0, 8) are only possible position for both 5 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  6, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  6, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 1,  4, -1, -1, -1, -1,  7, -1,  5},
				{ 2, -1, -1, -1, -1, -1,  0, -1,  3}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Locked> lockedList = new ArrayList<Locked>();
		lockedList = PP.findNewLocked();
		assertTrue(lockedList.contains(new Locked(6, new Spot[] {new Spot(6, 6), new Spot(6, 8)})));
		
		PP.update(lockedList.get(0));

		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
	}
	
	@Test
	public void testFindLockedCellNoDuplication(){
		//(0, 6)(0, 8) are only possible position for both 5 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  6, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  6, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 1,  4, -1, -1, -1, -1,  7, -1,  5},
				{ 2, -1, -1, -1, -1, -1,  0, -1,  3}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Locked> lockedList = new ArrayList<Locked>();
		lockedList = PP.findNewLocked();
		lockedList = PP.findNewLocked();
			
		assertEquals(0, lockedList.size());
	}
	
	@Test
	public void testFindLockedCellInAColumn(){
		//(0, 6)(0, 8) are only possible position for both 5 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1, -1, -1, -1,  5,  3},
				{-1, -1, -1, -1, -1, -1, -1,  7,  0},
				{-1, -1, -1, -1, -1,  6, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  6, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  4, -1},
				{-1, -1, -1, -1, -1, -1, -1,  1,  2}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<Locked> lockedList = new ArrayList<Locked>();
		lockedList = PP.findNewLocked();
		assertTrue(lockedList.contains(new Locked(6, new Spot[]{new Spot(0, 6), new Spot(1, 6)})));
		
		PP.update(lockedList.get(0));
		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(new Single(6, new Spot(7, 8)), singleList.get(0));
	}
	
	@Test
	public void testFindLockedCellRow(){
		//(0, 6)(0, 8) are only possible position for both 5 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1,  0, -1, -1,  1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{ 1,  2, -1,  4,  5, -1,  7,  8, -1},
				{ 6, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1,  6, -1, -1, -1, -1,  3},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  6, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  6, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		
		singleList = PV.findNewSingle();
		assertEquals(0, singleList.size());
		
		ArrayList<Locked> lockedList = new ArrayList<Locked>();
		lockedList = PP.findNewLocked();
		assertTrue(lockedList.contains(new Locked(6, new Spot[]{new Spot(0, 6), new Spot(0, 7), new Spot(0, 8)})));
		
		PV.update(new Locked(6, new Spot[]{new Spot(0, 6), new Spot(0, 7), new Spot(0, 8)}));
		singleList = PV.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(new Single(0, new Spot(2, 8)), singleList.get(0));
	}
	
	@Test
	public void testFindLockedCellColumn(){
		//(0, 6)(0, 8) are only possible position for both 5 and 6
		Puzzle puzzle = new Puzzle(new int [][]{
				{-1, -1, -1, -1,  3, -1, -1, -1, -1},
				{-1, -1,  8, -1, -1, -1, -1, -1, -1},
				{-1, -1,  7, -1, -1, -1, -1, -1, -1},
				{ 1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  5, -1, -1, -1, -1, -1,  6},
				{-1, -1,  4, -1,  6, -1, -1, -1, -1},
				{ 0, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  2, -1, -1, -1,  6, -1, -1},
				{-1, -1,  1,  6, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		PossibleValueMatrix PV = new PossibleValueMatrix(puzzle);
		
		singleList = PV.findNewSingle();
		assertEquals(0, singleList.size());
		
		ArrayList<Locked> lockedList = new ArrayList<Locked>();
		lockedList = PP.findNewLocked();
		assertTrue(lockedList.contains(new Locked(6, new Spot[]{new Spot(0, 0), new Spot(1, 0), new Spot(2, 0)})));
		
		PV.update(new Locked(6, new Spot[]{new Spot(0, 0), new Spot(1, 0), new Spot(2, 0)}));
		singleList = PV.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(new Single(0, new Spot(0, 2)), singleList.get(0));
	}
	
	@Test
	public void testFindXWingColumn(){
		XWing expXWing = new XWing(XWing.COLUMN, 3, new Spot[]{
			new Spot(3, 2),
			new Spot(6, 2),
			new Spot(3, 3),
			new Spot(6, 3)
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 3, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  3, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1,  3, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  0,  1, -1, -1, -1, -1, -1},
				{-1, -1,  1,  2, -1, -1, -1,  0, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1,  3},
				{-1, -1,  2,  0, -1, -1, -1, -1, -1}
		});
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<XWing> xList = PP.findNewXWing();
		assertTrue(xList.contains(expXWing));
		
		PP.update(expXWing);
		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(new Single(3, new Spot(4, 7)), singleList.get(0));
	}
	
	@Test
	public void testFindXWingRow(){
		XWing expXWing = new XWing(XWing.ROW, 3, new Spot[]{
			new Spot(2, 3),
			new Spot(2, 6),
			new Spot(3, 3),
			new Spot(3, 6)
		});
		
		Puzzle puzzle = new Puzzle(new int [][]{
				{ 3, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1,  0,  1, -1, -1,  2},
				{-1, -1, -1, -1,  1,  2, -1, -1,  0},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1,  3, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1,  3, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1,  0, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1,  3, -1}
		});
		
		PossiblePositionVector PP = new PossiblePositionVector(puzzle);
		
		ArrayList<XWing> xList = PP.findNewXWing();
		assertTrue(xList.contains(expXWing));
		
		PP.update(expXWing);
		singleList = PP.findNewSingle();
		assertEquals(1, singleList.size());
		assertEquals(new Single(3, new Spot(7, 4)), singleList.get(0));
	}
}
