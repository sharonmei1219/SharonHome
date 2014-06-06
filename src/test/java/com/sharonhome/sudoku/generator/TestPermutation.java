package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestPermutation {
	Permutator permutator = new Permutator();
	
	@Test
	public void testLehmerCodeMatchSizeOfSeq(){
		LehmerCode lc = new LehmerCode(new int[]{0});
		ArrayList<Integer> codes = lc.matchSeqSizeInList(2);
		assertEquals(2, codes.size());
		assertEquals(0, codes.get(0).intValue());
		assertEquals(0, codes.get(1).intValue());
	}
	
	@Test
	public void testLehmerCodeMatchSize4(){
		LehmerCode lc = new LehmerCode(new int[]{2, 1, 0});
		ArrayList<Integer> codes = lc.matchSeqSizeInList(4);
		assertEquals(4, codes.size());
		assertEquals(0, codes.get(0).intValue());
		assertEquals(2, codes.get(1).intValue());
		assertEquals(1, codes.get(2).intValue());
		assertEquals(0, codes.get(3).intValue());
	}
	
	@Test
	public void testLehmerCodeMatchSize2(){
		LehmerCode lc = new LehmerCode(new int[]{3, 2, 1, 0});
		ArrayList<Integer> codes = lc.matchSeqSizeInList(2);
		assertEquals(2, codes.size());
		assertEquals(1, codes.get(0).intValue());
		assertEquals(0, codes.get(1).intValue());
	}

	@Test
	public void testLehmerCodeToPermutationOfSequenceOfSize2() {
		
		LehmerCode lc = new LehmerCode(new int []{0, 0});
		Pair p0 = new Pair(0, 0);
		Pair p1 = new Pair(1, 1);
		
		ArrayList<Pair> seq = new ArrayList<Pair>();
		seq.add(p0);
		seq.add(p1);
		
		ArrayList<Pair> per = permutator.permutate(seq, lc);
		assertEquals(p0, per.get(0));
		assertEquals(p1, per.get(1));
		
		lc = new LehmerCode(new int []{1, 0});
		per = permutator.permutate(seq, lc);
		assertEquals(p1, per.get(0));
		assertEquals(p0, per.get(1));
	}
	
	@Test
	public void testLehmerCodeToPermutationOfSequenceOfSize4() {
		LehmerCode lc = new LehmerCode(new int []{});
		Pair p0 = new Pair(0, 0);
		Pair p1 = new Pair(1, 1);
		Pair p2 = new Pair(2, 2);
		Pair p3 = new Pair(3, 3);
		ArrayList<Pair> seq = new ArrayList<Pair>();
		seq.add(p0);
		seq.add(p1);
		seq.add(p2);
		seq.add(p3);
		
		ArrayList<Pair> per = permutator.permutate(seq, lc);
		assertEquals(p0, per.get(0));
		assertEquals(p1, per.get(1));
		assertEquals(p2, per.get(2));
		assertEquals(p3, per.get(3));
		
		lc = new LehmerCode(new int []{3, 2, 1, 0});
		per = permutator.permutate(seq, lc);
		assertEquals(p3, per.get(0));
		assertEquals(p2, per.get(1));
		assertEquals(p1, per.get(2));
		assertEquals(p0, per.get(3));
		
		lc = new LehmerCode(new int []{1, 2, 1, 0});
		per = permutator.permutate(seq, lc);
		assertEquals(p1, per.get(0));
		assertEquals(p3, per.get(1));
		assertEquals(p2, per.get(2));
		assertEquals(p0, per.get(3));
	}
	
	@Test
	public void LehmerCodeEncoder(){
		LehmerCode lc = new LehmerCode(0);
		ArrayList<Integer> codes = lc.matchSeqSizeInList(1);
		assertEquals(0, codes.get(0).intValue());
		
		lc = new LehmerCode(1);
		codes = lc.matchSeqSizeInList(2);
		assertEquals(1, codes.get(0).intValue());
		assertEquals(0, codes.get(1).intValue());
		
		lc = new LehmerCode(2);
		codes = lc.matchSeqSizeInList(3);
		assertEquals(1, codes.get(0).intValue());
		assertEquals(0, codes.get(1).intValue());
		assertEquals(0, codes.get(2).intValue());
		
		lc = new LehmerCode(3);
		codes = lc.matchSeqSizeInList(3);
		assertEquals(1, codes.get(0).intValue());
		assertEquals(1, codes.get(1).intValue());
		assertEquals(0, codes.get(2).intValue());
		
		lc = new LehmerCode(4);
		codes = lc.matchSeqSizeInList(3);
		assertEquals(2, codes.get(0).intValue());
		assertEquals(0, codes.get(1).intValue());
		assertEquals(0, codes.get(2).intValue());
		
		lc = new LehmerCode(5);
		codes = lc.matchSeqSizeInList(3);
		assertEquals(2, codes.get(0).intValue());
		assertEquals(1, codes.get(1).intValue());
		assertEquals(0, codes.get(2).intValue());
		
		lc = new LehmerCode(6);
		codes = lc.matchSeqSizeInList(4);
		assertEquals(1, codes.get(0).intValue());
		assertEquals(0, codes.get(1).intValue());
		assertEquals(0, codes.get(2).intValue());
		assertEquals(0, codes.get(3).intValue());
	}

}
