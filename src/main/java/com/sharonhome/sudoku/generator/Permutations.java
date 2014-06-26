package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class Permutations {

	private int [] originSeq;
	private NumberingSystem numberingSystem;

	public Permutations(int[] seq) {
		this.originSeq = seq;
		int [] nChoices = new int[seq.length];
		for(int i = 0; i < seq.length; i++)
			nChoices[i] = seq.length - i;
		this.numberingSystem = new NumberingSystem(nChoices);
	}

	public int total() {
		return numberingSystem.total();
	}

	public int[] get(final int nth) {
		int [] result = new int [originSeq.length];
		ArrayList<Integer> pos = numberingSystem.getNthNumber(nth);
		int [] rest = originSeq.clone();
		for(int i = 0; i < originSeq.length; i++){
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
