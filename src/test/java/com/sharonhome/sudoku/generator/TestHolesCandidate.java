package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestHolesCandidate {

	@Test
	public void testHolesCandidateConstruct() {
		HolesCandidate hc = new HolesCandidate(2, 2);
		assertEquals(new Spot(0, 0), hc.getNthCandidate(0));
		assertEquals(new Spot(0, 1), hc.getNthCandidate(1));
		assertEquals(new Spot(1, 0), hc.getNthCandidate(2));
		assertEquals(new Spot(1, 1), hc.getNthCandidate(3));
	}
	
	@Test
	public void testHolesCandidateRemoveCandidate(){
		HolesCandidate hc = new HolesCandidate(2, 2);
		assertEquals(new Spot(0, 0), hc.getNthCandidate(0));
		hc = hc.remove(0);
		assertEquals(3, hc.nrOfCandidates());
		assertEquals(new Spot(0, 1), hc.getNthCandidate(0));
		assertEquals(new Spot(1, 0), hc.getNthCandidate(1));
		assertEquals(new Spot(1, 1), hc.getNthCandidate(2));
	}

}
