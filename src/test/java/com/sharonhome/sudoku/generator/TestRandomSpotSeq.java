package com.sharonhome.sudoku.generator;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRandomSpotSeq {

	@Test
	public void testRandomSpotSeqConstruction() {
		HolesCandidate spotCandidates = new HolesCandidate(2, 2);
		TestingRandomNumberGen randomNumberGenerator = new TestingRandomNumberGen();
		randomNumberGenerator.setRand(0);
		RandomSpotSeq rss = new RandomSpotSeq(spotCandidates, randomNumberGenerator);
		assertEquals(new Spot(0, 0), rss.next());
		assertEquals(new Spot(0, 1), rss.next());
		assertEquals(2, rss.nrOfCandidates());
	}
	
	class TestingRandomNumberGen implements RandomNumberGen{

		private int rd;

		public void setRand(int i) {
			this.rd = i;
			
		}

		public int nextInt(int nrOfCandidates) {
			return rd;
		}
		
	}

}
