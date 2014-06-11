package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class TestCSLCRGenerator {
	SetSet cst = new SetSet(new int [][]{{11, 12},{21, 22}});
	@Test
	public void test() {
		ArrayList<NumberingSystem> nrSystem = new ArrayList<NumberingSystem>();
		nrSystem.add(new NumberingSystem(new int [] {3, 3, 3}));
		nrSystem.add(new NumberingSystem(new int [] {2, 2, 2}));
		nrSystem.add(new NumberingSystem(new int [] {1, 1, 1}));
		NumberingSystem highOrderNrSystem = new NumberingSystem(new int[]{
				nrSystem.get(0).total(),
				nrSystem.get(1).total(),
				nrSystem.get(2).total()
		});
		assertEquals(27, nrSystem.get(0).total());
		assertEquals(8, nrSystem.get(1).total());
		assertEquals(1, nrSystem.get(2).total());
		assertEquals(216, highOrderNrSystem.total());
	}

	
	@Test
	public void testShuffledCSLCR(){
		ArrayList<int []> cslcr = new ArrayList<int []>();
		cslcr.add(new int[] {11, 12, 13});
		cslcr.add(new int[] {21, 22, 23});
		cslcr.add(new int[] {31, 32, 33});
		CSLCRPermutations sCSLCRs = new CSLCRPermutations(cslcr);
		Assert.assertArrayEquals(new int []{11, 12, 13, 21, 22, 23, 31, 32, 33}, sCSLCRs.getNthPermutation(0));
		Assert.assertArrayEquals(new int []{11, 12, 13, 21, 22, 23, 33, 32, 31}, sCSLCRs.getNthPermutation(6 - 1));
		Assert.assertArrayEquals(new int []{11, 12, 13, 23, 22, 21, 33, 32, 31}, sCSLCRs.getNthPermutation(6*6 - 1));
		Assert.assertArrayEquals(new int []{13, 12, 11, 23, 22, 21, 33, 32, 31}, sCSLCRs.getNthPermutation(6*6*6 - 1));
	}

}
