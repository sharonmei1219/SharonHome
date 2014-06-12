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
	
	@Test
	public void testCSLCRSetGenerator(){
		SetSet ss = new SetSet(new int [][]{{11, 12, 13}, {21, 22, 23}, {31, 32, 33}});
		CSLCRSetNumberingSystem setNs = new CSLCRSetNumberingSystem();
		CSLCRSetGenerator setGen = new CSLCRSetGenerator(ss, setNs);
		Assert.assertArrayEquals(new int[] {11, 21, 31},   setGen.getAllSelection(0).get(0));
		Assert.assertArrayEquals(new int[] {12, 22, 32},   setGen.getAllSelection(0).get(1));
		Assert.assertArrayEquals(new int[] {13, 23, 33},   setGen.getAllSelection(0).get(2));
		
		Assert.assertArrayEquals(new int[] {11, 21, 32},   setGen.getAllSelection(8).get(0));
		Assert.assertArrayEquals(new int[] {12, 22, 31},   setGen.getAllSelection(8).get(1));
		Assert.assertArrayEquals(new int[] {13, 23, 33},   setGen.getAllSelection(8).get(2));
		
		Assert.assertArrayEquals(new int[] {11, 21, 33},   setGen.getAllSelection(16).get(0));
		Assert.assertArrayEquals(new int[] {12, 22, 31},   setGen.getAllSelection(16).get(1));
		Assert.assertArrayEquals(new int[] {13, 23, 32},   setGen.getAllSelection(16).get(2));
		
		
		Assert.assertArrayEquals(new int[] {13, 23, 33},   setGen.getAllSelection(215).get(0));
		Assert.assertArrayEquals(new int[] {12, 22, 32},   setGen.getAllSelection(215).get(1));
		Assert.assertArrayEquals(new int[] {11, 21, 31},   setGen.getAllSelection(215).get(2));
	}
	
	@Test
	public void testCSLCRSetGeneratorTotalDifferentSets(){
		SetSet ss = new SetSet(new int [][]{{11, 12, 13}, {21, 22, 23}, {31, 32, 33}});
		CSLCRSetNumberingSystem setNs = new CSLCRSetNumberingSystem();
		CSLCRSetGenerator setGen = new CSLCRSetGenerator(ss, setNs);
		assertEquals(216, setGen.total());

	}
	
	@Test
	public void testCSLCRPerNumberingSystem(){
		SetSet ss = new SetSet(new int [][]{{11, 12, 13}, {21, 22, 23}, {31, 32, 33}});
		CSLCRSetNumberingSystem setNs = new CSLCRSetNumberingSystem();
		CSLCRSetGenerator setGen = new CSLCRSetGenerator(ss, setNs);
		CSLCRPerGenerator perGen = new CSLCRPerGenerator(setGen);

		assertEquals(216 * 216, perGen.total());
		
		Assert.assertArrayEquals(new int []{11, 21, 31, 12, 22, 32, 13, 23, 33}, perGen.getNthPerCSLCR(0));
		Assert.assertArrayEquals(new int []{33, 23, 13, 32, 22, 12, 31, 21, 11}, perGen.getNthPerCSLCR(perGen.total() -1));
	}

}
