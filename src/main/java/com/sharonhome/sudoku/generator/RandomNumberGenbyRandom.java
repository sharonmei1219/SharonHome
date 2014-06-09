package com.sharonhome.sudoku.generator;

import java.util.Random;

public class RandomNumberGenbyRandom implements RandomNumberGen {

	private Random rand;
	public RandomNumberGenbyRandom(){
		this.rand = new Random();
	}
	public int nextInt(int nrOfCandidates) {
		return rand.nextInt(nrOfCandidates);
	}

}
