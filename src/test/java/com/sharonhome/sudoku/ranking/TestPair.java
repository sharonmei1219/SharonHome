package com.sharonhome.sudoku.ranking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.sharonhome.sudoku.generator.Spot;

public class TestPair {
	PossibleValuesBuilder pvBuilder = new PossibleValuesBuilder();
	PossibleValues pv1 = pvBuilder.onlyPossibleIn(new int []{0, 1});
	PossibleValues pv2 = pvBuilder.onlyPossibleIn(new int []{0, 1});
	
	Spot s0 = new Spot(0, 0);
	Spot s1 = new Spot(0, 1);
	Spot s2 = new Spot(7, 7);
	Spot s3 = new Spot(8, 8);
	
	@Test
	public void testEquals() {
		Pair p1 = new Pair(pv1, s0, s1);
		Pair p2 = new Pair(pv1, s0, s1);
		assertTrue(p1.equals(p2));
	}
	
	@Test
	public void testNotEqualsIfNotInSamePlace() {
		Pair p1 = new Pair(pv1, s0, s3);
		Pair p2 = new Pair(pv1, s0, s1);
		assertFalse(p1.equals(p2));
	}
	
	@Test
	public void testEqualsSamePlace() {
		Pair p1 = new Pair(pv1, s1, s0);
		Pair p2 = new Pair(pv1, s0, s1);
		assertTrue(p1.equals(p2));
	}
	
	@Test
	public void testNoImpactWhenPVChange(){
		Pair p1 = new Pair(pv1, s1, s0);
		Pair p2 = new Pair(pv2, s0, s1);
		pv1.remove(0);
		assertTrue(p1.equals(p2));
	}
	
	@Test
	public void testPairListContains(){
		ArrayList<Pair> list = new ArrayList<Pair>();
		list.add(new Pair(pv1, s0, s1));
		list.add(new Pair(pv2, s2, s3));
		assertTrue(list.contains(new Pair(pv1, new Spot(0, 0), new Spot(0, 1))));
	}

	@Test
	public void testPairsInRow(){
		Pair pair = new Pair(pv1, new Spot(0, 0), new Spot(0, 1));
		assertTrue(pair.inARow());
		assertEquals(0, pair.row());
		Pair pair2 = new Pair(pv1, new Spot(0, 0), new Spot(1, 0));
		assertFalse(pair2.inARow());
	}
	
	@Test
	public void testPairInColumn(){
		Pair pair = new Pair(pv1, new Spot(0, 0), new Spot(0, 1));
		assertFalse(pair.inAColumn());
		
		Pair pair2 = new Pair(pv1, new Spot(0, 0), new Spot(1, 0));
		assertTrue(pair2.inAColumn());
		assertEquals(0, pair.column());
	}
	
	@Test
	public void testPairInBlock(){
		Pair pair = new Pair(pv1, new Spot(0, 0), new Spot(2, 2));
		assertTrue(pair.inABlock());
		assertEquals(new Spot(0, 0), pair.block());
		
		Pair pair2 = new Pair(pv1, new Spot(0, 0), new Spot(3, 3));
		assertFalse(pair2.inABlock());
	}
	
	@Test
	public void testPairHas(){
		Pair pair = new Pair(pv1, new Spot(0, 0), new Spot(2, 2));
		assertTrue(pair.has(new Spot(2, 2)));
	}
}
