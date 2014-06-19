package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sharonhome.sudoku.generator.Spot;

public class TestPossiblePositions {

	@Test
	public void testOnlyOnePossiblePosInRow() {
		PossiblePositions pp = new PossiblePositions();
		pp.notPossibleIn(0, 0);
		pp.notPossibleIn(0, 1);
		pp.notPossibleIn(0, 2);
		pp.notPossibleIn(0, 3);
		pp.notPossibleIn(0, 4);
		pp.notPossibleIn(0, 5);
		pp.notPossibleIn(0, 6);
		pp.notPossibleIn(0, 7);
		ArrayList<Spot> determinedPoss = pp.onlyPossiblePosInARegion();
		assertEquals(1, determinedPoss.size());
		assertEquals(new Spot(0, 8), determinedPoss.get(0));
	}
	
	@Test
	public void testOnlyOnePossiblePosInColumn() {
		PossiblePositions pp = new PossiblePositions();
		pp.notPossibleIn(0, 0);
		pp.notPossibleIn(1, 0);
		pp.notPossibleIn(2, 0);
		pp.notPossibleIn(3, 0);
		pp.notPossibleIn(4, 0);
		pp.notPossibleIn(5, 0);
		pp.notPossibleIn(6, 0);
		pp.notPossibleIn(7, 0);
		ArrayList<Spot> determinedPoss = pp.onlyPossiblePosInARegion();
		assertEquals(1, determinedPoss.size());
		assertEquals(new Spot(8, 0), determinedPoss.get(0));
	}
	
	@Test
	public void testOnlyOnePossiblePosInBlock() {
		PossiblePositions pp = new PossiblePositions();
		pp.notPossibleIn(0, 3);
		pp.notPossibleIn(1, 3);
		pp.notPossibleIn(2, 3);
		pp.notPossibleIn(0, 4);
		pp.notPossibleIn(1, 4);
		pp.notPossibleIn(2, 4);
		pp.notPossibleIn(0, 5);
		pp.notPossibleIn(1, 5);
		ArrayList<Spot> determinedPoss = pp.onlyPossiblePosInARegion();
		assertEquals(1, determinedPoss.size());
		assertEquals(new Spot(2, 5), determinedPoss.get(0));
	}
}

