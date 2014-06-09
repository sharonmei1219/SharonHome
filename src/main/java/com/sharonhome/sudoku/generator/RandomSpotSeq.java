package com.sharonhome.sudoku.generator;

public class RandomSpotSeq {

	private RandomNumberGen rand;
	private HolesCandidate spotCandidates;

	public RandomSpotSeq(HolesCandidate spotCandidates,
			RandomNumberGen randomNumberGenerator) {
		this.rand = randomNumberGenerator;
		this.spotCandidates = spotCandidates;
	}

	public Spot next() {
		int spotIndex = this.rand.nextInt(spotCandidates.nrOfCandidates());
		Spot result = spotCandidates.getNthCandidate(spotIndex);
		spotCandidates = spotCandidates.remove(spotIndex);
		return result;
	}

	public int nrOfCandidates() {
		return spotCandidates.nrOfCandidates();
	}

}
