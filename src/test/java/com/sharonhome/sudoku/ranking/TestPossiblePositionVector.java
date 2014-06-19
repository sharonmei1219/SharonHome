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
	public void testConstruction() {
		singleList = PP.findNewSingle(singleList);
		assertEquals(0, singleList.size());
	}
	
	@Test
	public void testUpdateSingleWithRowImpacted() {
		PP.update(new Single(0, new Spot(0, 0)));
		PP.update(new Single(1, new Spot(0, 1)));
		PP.update(new Single(2, new Spot(0, 2)));
		PP.update(new Single(3, new Spot(0, 3)));
		PP.update(new Single(4, new Spot(0, 4)));
		PP.update(new Single(5, new Spot(0, 5)));
		PP.update(new Single(6, new Spot(0, 6)));
		PP.update(new Single(7, new Spot(0, 7)));
		singleList = PP.findNewSingle(singleList);
		assertEquals(1, singleList.size());
		assertEquals(8, singleList.get(0).getValue());
		assertEquals(0, singleList.get(0).geti());
		assertEquals(8, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleWithColumnImpacted() {
		PP.update(new Single(0, new Spot(0, 2)));
		PP.update(new Single(1, new Spot(1, 2)));
		PP.update(new Single(2, new Spot(2, 2)));
		PP.update(new Single(3, new Spot(3, 2)));
		PP.update(new Single(4, new Spot(4, 2)));
		PP.update(new Single(5, new Spot(6, 2)));
		PP.update(new Single(7, new Spot(7, 2)));
		PP.update(new Single(8, new Spot(8, 2)));
		
		singleList = PP.findNewSingle(singleList);
		assertEquals(1, singleList.size());
		assertEquals(6, singleList.get(0).getValue());
		assertEquals(5, singleList.get(0).geti());
		assertEquals(2, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleWithBlockImpacted() {
		PP.update(new Single(0, new Spot(3, 6)));
		PP.update(new Single(1, new Spot(4, 6)));
		PP.update(new Single(2, new Spot(5, 6)));
		PP.update(new Single(3, new Spot(3, 7)));
		PP.update(new Single(4, new Spot(4, 7)));
		PP.update(new Single(6, new Spot(5, 7)));
		PP.update(new Single(7, new Spot(3, 8)));
		PP.update(new Single(8, new Spot(5, 8)));
		
		singleList = PP.findNewSingle(singleList);
		assertEquals(1, singleList.size());
		assertEquals(5, singleList.get(0).getValue());
		assertEquals(4, singleList.get(0).geti());
		assertEquals(8, singleList.get(0).getj());
	}
	
	@Test
	public void testUpdateSingleWithMixedImpact() {
		PP.update(new Single(3, new Spot(4, 0)));
		PP.update(new Single(3, new Spot(7, 2)));
		PP.update(new Single(3, new Spot(0, 3)));
		PP.update(new Single(3, new Spot(1, 8)));

		
		singleList = PP.findNewSingle(singleList);

		assertEquals(1, singleList.size());
		assertEquals(3, singleList.get(0).getValue());
		assertEquals(2, singleList.get(0).geti());
		assertEquals(1, singleList.get(0).getj());
	}
}
