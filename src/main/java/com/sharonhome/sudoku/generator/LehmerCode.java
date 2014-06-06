package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class LehmerCode {
	
	static final int [] FACTORIAL = {362880, 40320, 5040, 720, 120, 24, 6, 2, 1};

	private ArrayList<Integer> codesInlists;

	public LehmerCode(int[] codes) {
		codesInlists = new ArrayList<Integer>();
		for(int i = 0; i < codes.length; i ++)
			codesInlists.add(codes[i]);
	}

	public LehmerCode(int number) {
		codesInlists = new ArrayList<Integer>();
		for(int i = 0; i < FACTORIAL.length; i++){
			codesInlists.add(number/FACTORIAL[i]);
			number %= FACTORIAL[i];
		}
		codesInlists.add(0);
	}
	
	public ArrayList<Integer> matchSeqSizeInList(int seqSize){
		ArrayList<Integer> resultInList = new ArrayList<Integer>();
		if(seqSize <= codesInlists.size()){
			for(int i = 0; i < seqSize; i ++){
				resultInList.add(codesInlists.get(i + codesInlists.size() - seqSize));
			}
		}
		else{
			for (int i = 0; i < seqSize - codesInlists.size(); i++) 
				resultInList.add(0);
			resultInList.addAll(codesInlists);
		}
		return resultInList;
	}
}
