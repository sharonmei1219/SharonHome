package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sharonhome.sudoku.generator.Spot;

public class TestPossiblePositions {
	PossiblePositions pp = new PossiblePositions();
	private void allNotPossibleInRowExcept(PossiblePositions pp, int row, int [] exception){
		for(int j = 0; j < 9; j++)
			if(!contains(exception, j)) pp.notPossibleIn(row, j);
	}
	
	private void allNotPossibleInColumnExcept(PossiblePositions pp, int column, int [] exception){
		for(int i = 0; i < 9; i++)
			if(!contains(exception, i)) pp.notPossibleIn(i, column);
	}
	
	private void allNotPossibleInBlockExcept(PossiblePositions pp, int br, int bc, Spot[] spots){
		for(int i = br * 3; i < br * 3 + 3; i ++){
			for(int j = bc * 3; j < bc * 3 + 3; j ++){
				if(!contains(spots, new Spot(i, j))) pp.notPossibleIn(i, j);
			}
		}
	}
	
	private boolean contains(Spot[] spots, Spot spot) {
		for(Spot sin : spots)
			if(sin.equals(spot)) return true;
		return false;
	}

	private boolean contains(int [] array, int value){
		for(int vina : array)
			if(vina == value) return true;
		return false;
	}

	@Test
	public void testOnlyOnePossiblePosInRow() {
		allNotPossibleInRowExcept(pp, 0, new int []{8});
		ArrayList<Spot> determinedPoss = pp.onlyPossiblePosInARegion();
		assertEquals(1, determinedPoss.size());
		assertEquals(new Spot(0, 8), determinedPoss.get(0));
	}
	
	@Test
	public void testOnlyOnePossiblePosInColumn() {
		allNotPossibleInColumnExcept(pp, 0, new int []{8});
		ArrayList<Spot> determinedPoss = pp.onlyPossiblePosInARegion();
		assertEquals(1, determinedPoss.size());
		assertEquals(new Spot(8, 0), determinedPoss.get(0));
	}
	
	@Test
	public void testOnlyOnePossiblePosInBlock() {
		allNotPossibleInBlockExcept(pp, 0, 1, new Spot []{new Spot(2, 5)});
		ArrayList<Spot> determinedPoss = pp.onlyPossiblePosInARegion();
		assertEquals(1, determinedPoss.size());
		assertEquals(new Spot(2, 5), determinedPoss.get(0));
	}
	
	@Test
	public void testGetNumberOfPossiblePositionsInRow(){
		allNotPossibleInRowExcept(pp, 2, new int []{1, 2, 3});
		assertEquals(3, pp.getNumberOfPossiblePositionsInRow(2));
		assertEquals(9, pp.getNumberOfPossiblePositionsInRow(0));
	}
	
	@Test
	public void testGetNumberOfPossiblePositionsInColumn(){
		allNotPossibleInColumnExcept(pp, 4, new int []{0, 1});
		assertEquals(2, pp.getNumberOfPossiblePositionsInColumn(4));
		assertEquals(9, pp.getNumberOfPossiblePositionsInColumn(2));
	}
	
	@Test
	public void testGetNumberOfPossiblePositionsInBlock(){
		allNotPossibleInBlockExcept(pp, 0, 1, new Spot []{new Spot(1, 4), new Spot(2, 5)});
		assertEquals(2, pp.getNumberOfPossiblePositionsInBlock(0, 1));
		assertEquals(9, pp.getNumberOfPossiblePositionsInBlock(1, 1));
	}
	
	@Test
	public void testMatchesInRow(){
		PossiblePositions pp2 = new PossiblePositions();
		allNotPossibleInRowExcept(pp, 3, new int []{0, 5});
		allNotPossibleInRowExcept(pp2, 3, new int []{0, 5});
		assertTrue(pp.matchesInRow(pp2, 3));
	}
	
	@Test
	public void testNotMatchesInRow(){
		PossiblePositions pp2 = new PossiblePositions();
		allNotPossibleInRowExcept(pp, 3, new int []{0, 5, 6});
		allNotPossibleInRowExcept(pp2, 3, new int []{0, 5});
		assertFalse(pp.matchesInRow(pp2, 3));
	}
	
	@Test
	public void testMatchesInColumn(){
		PossiblePositions pp2 = new PossiblePositions();
		allNotPossibleInColumnExcept(pp, 6, new int []{0, 7});
		allNotPossibleInColumnExcept(pp2, 6, new int []{0, 7});
		assertTrue(pp.matchesInColumn(pp2, 6));
	}
	
	@Test
	public void testNotMatchesInColumn(){
		PossiblePositions pp2 = new PossiblePositions();
		allNotPossibleInColumnExcept(pp, 6, new int []{0});
		allNotPossibleInColumnExcept(pp2, 6, new int []{0, 7});
		assertFalse(pp.matchesInColumn(pp2, 6));
	}
	
	@Test
	public void testMatchesInBlock(){
		PossiblePositions pp2 = new PossiblePositions();
		allNotPossibleInBlockExcept(pp, 2, 2, new Spot[] {new Spot(6, 6), new Spot(6, 7)});
		allNotPossibleInBlockExcept(pp2, 2, 2, new Spot[] {new Spot(6, 6), new Spot(6, 7)});
		assertTrue(pp.matchesInBlock(pp2, 2, 2));
	}
	
	@Test
	public void testNotMatchesInBlock(){
		PossiblePositions pp2 = new PossiblePositions();
		allNotPossibleInBlockExcept(pp, 2, 2, new Spot[] {new Spot(6, 6), new Spot(6, 7)});
		allNotPossibleInBlockExcept(pp2, 2, 2, new Spot[] {new Spot(6, 6), new Spot(3, 7)});
		assertFalse(pp.matchesInBlock(pp2, 2, 2));
	}
	
	@Test
	public void testPossiblePositionInBlock(){
		allNotPossibleInBlockExcept(pp, 2, 2, new Spot[] {new Spot(6, 6), new Spot(6, 7)});
		Spot[] possibleSpots = pp.inBlock(2, 2);
		assertEquals(2, possibleSpots.length);
		assertTrue(contains(possibleSpots, new Spot(6, 6)));
		assertTrue(contains(possibleSpots, new Spot(6, 7)));
	}
	
	
}

