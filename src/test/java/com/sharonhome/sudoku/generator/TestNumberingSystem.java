package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestNumberingSystem {
	NumberingSystem binaries = new NumberingSystem(new int[]{2, 2, 2, 2});
	ArrayList<Integer> result;
	
	@Test
	public void testBinaryNumberingSystemInput0WillGet0000() {
		result = binaries.getNthNumber(0);
		assertEquals(4, result.size());
		assertEquals(0, result.get(2).intValue());
		assertEquals(0, result.get(3).intValue());
	}
	
	@Test
	public void testBinaryNumberingSystemInput7WillGet0111() {
		result = binaries.getNthNumber(7);
		assertEquals(0, result.get(0).intValue());
		assertEquals(1, result.get(1).intValue());
		assertEquals(1, result.get(2).intValue());
		assertEquals(1, result.get(3).intValue());
	}
	
	@Test
	public void testBinaryNumberingSystemTotal4bitBinaryCanRepresent15Numbers(){
		assertEquals(16, binaries.total());
	}
	
	NumberingSystem lehmerCode = new NumberingSystem(new int[]{4, 3, 2, 1, 1});
	@Test
	public void testLehmerCodeGetInput2WillGet01000(){
		result = lehmerCode.getNthNumber(2);
		assertEquals(0, result.get(0).intValue());
		assertEquals(1, result.get(1).intValue());
		assertEquals(0, result.get(2).intValue());
		assertEquals(0, result.get(3).intValue());
		assertEquals(0, result.get(4).intValue());
	}
}
