package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class CSLCRPerGenerator {

	private CSLCRSetGenerator setGen;
	private NumberingSystem ns;

	public CSLCRPerGenerator(CSLCRSetGenerator setGen) {
		this.setGen = setGen;
		this.ns = new NumberingSystem(new int []{setGen.total(), CSLCRPermutations.total()});
	}
	
	public int total(){
		return ns.total();
	}

	public int[] getNthPerCSLCR(int i) {
		ArrayList<Integer> seq = ns.getNthNumber(i);
		ArrayList<int []> cslcrSet = setGen.getAllSelection(seq.get(0));
		CSLCRPermutations pers = new CSLCRPermutations(cslcrSet);
		int [] permutation = pers.getNthPermutation(seq.get(1));
		return permutation;
	}

}
