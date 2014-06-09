package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPermutation {

	@Test
	public void test() {
		Permutations pers = new Permutations(new int [] {1, 2});
		assertEquals(2, pers.total());
		int [] per = pers.get(1);
		assertEquals(2, per.length);
		assertEquals(2, per[0]);
		assertEquals(1, per[1]);
	}
}
