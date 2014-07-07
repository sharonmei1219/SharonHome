package com.sharonhome.sudoku.table;

import com.sharonhome.sudoku.generator.Permutations;
import com.sharonhome.sudoku.generator.RandomNumberGen;

public class RandomCandidatesInDifferentSequence implements RandomCandidates {
	int [][] candidates;
	public RandomCandidatesInDifferentSequence(RandomNumberGen rand, Permutations permutations) {
		candidates = new int[9][];
		for(int i = 0; i < 9; i++){
			int nth = rand.nextInt(permutations.total());
			candidates[i] = permutations.get(nth);
		}
	}

	public int[] getCandidate(int i, int j) {
		return candidates[i];
	}

}
