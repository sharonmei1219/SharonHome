package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TestSetSet {
	SetSet cts = new SetSet(new int [][]{{11, 12}, {21, 22}});
	ArrayList<Integer> pos = new ArrayList<Integer>();
	ArrayList<Integer> pos1 = new ArrayList<Integer>();
	
	@Before
	public void setUp(){
		pos.add(0);
		pos.add(0);
		pos1.add(0);
		pos1.add(1);
	}

	@Test
	public void testCosetExtract() {
		int[] cslcr = cts.getSelectedValue(pos);
		assertEquals(2, cslcr.length);
		assertEquals(11, cslcr[0]);
		assertEquals(21, cslcr[1]);
		
		cslcr = cts.getSelectedValue(pos1);
		assertEquals(11, cslcr[0]);
		assertEquals(22, cslcr[1]);
	}
	
	@Test
	public void testRemovePosInCoset(){
		SetSet rest = cts.subSelection(pos);
		int [] cslcr = rest.getSelectedValue(pos);
		assertEquals(12, cslcr[0]);
		assertEquals(22, cslcr[1]);
		rest = cts.subSelection(pos1);
		cslcr = rest.getSelectedValue(pos);
		assertEquals(12, cslcr[0]);
		assertEquals(21, cslcr[1]);
	}
	
	@Test
	public void testArrayRemove(){
		int [] result = cts.remove(new int [] {1, 2, 3}, 1);
		assertEquals(2, result.length);
		assertEquals(1, result[0]);
		assertEquals(3, result[1]);
	}
	
	@Test
	public void testGetCosetsSize(){
		SetSet cts = new SetSet(new int [][]{{11, 12, 13}, {21, 22, 13}});
		assertEquals(2, cts.getNumberOfSet());
		assertEquals(3, cts.getSetSize());
	}
}
