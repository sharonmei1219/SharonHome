package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class Permutator {

	public ArrayList<Pair> permutate(ArrayList<Pair> seq, LehmerCode lc) {
		ArrayList<Pair> result = new ArrayList<Pair>();
		ArrayList<Pair> localSeq = (ArrayList<Pair>) seq.clone();
		ArrayList<Integer> codesInList =  lc.matchSeqSizeInList(seq.size());
		for (int pos : codesInList) {
			result.add(localSeq.get(pos));
			localSeq.remove(pos);
		}
		return result;
	}

}
