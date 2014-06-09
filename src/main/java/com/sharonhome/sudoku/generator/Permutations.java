package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class Permutations {

	private int [] seq;
	private NumberingSystem numberingSystem;

	public Permutations(int[] seq) {
		this.seq = seq;
		int [] nChoices = new int[seq.length];
		for(int i = 0; i < seq.length; i++)
			nChoices[i] = seq.length - i;
		this.numberingSystem = new NumberingSystem(nChoices);
	}

	public int total() {
		return numberingSystem.total();
	}

	public int[] get(int nth) {
		int [] result = new int [seq.length];
		ArrayList<Integer> pos = numberingSystem.getNthNumber(nth);
		int [] rest = seq.clone();
		for(int i = 0; i < seq.length; i++){
			result[i] = rest[pos.get(i)];
			rest = remove(rest, pos.get(i));
		}
		return result;
	}

	private int[] remove(int[] rest, int indexToRemove) {
		int [] result = new int [rest.length - 1];
		int k = 0;
		for(int i = 0; i < rest.length; i++){
			if(i == indexToRemove) continue;
			result[k++] = rest[i];
		}
		return result;
	}
}
