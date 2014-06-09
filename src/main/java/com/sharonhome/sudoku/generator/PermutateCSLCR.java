package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class PermutateCSLCR {

	private NumberingSystem ns;
	ArrayList<Permutations> perms;

	public PermutateCSLCR(ArrayList <int[]> cslcr) {
		this.ns = new NumberingSystem(new int []{6, 6, 6});
		this.perms = new ArrayList<Permutations>();
		perms.add(new Permutations(cslcr.get(0)));
		perms.add(new Permutations(cslcr.get(1)));
		perms.add(new Permutations(cslcr.get(2)));
	}

	public int[] getNthPermutation(int nth) {
		ArrayList<Integer> shufflePos = ns.getNthNumber(nth);
		int [] result = new int [9];
		int k = 0;
		for (int item : perms.get(0).get(shufflePos.get(0)))
			result[k++] = item;
		for (int item : perms.get(1).get(shufflePos.get(1)))
			result[k++] = item;
		for (int item : perms.get(2).get(shufflePos.get(2)))
			result[k++] = item;
		return result;
	}

}
