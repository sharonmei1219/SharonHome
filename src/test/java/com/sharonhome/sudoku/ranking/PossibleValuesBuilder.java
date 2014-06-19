package com.sharonhome.sudoku.ranking;

public class PossibleValuesBuilder {
	public PossibleValues onlyPossibleIn(int [] possibleValues){
		PossibleValues result = new PossibleValues();
		for(int i = 0; i < 9; i++){
			if(!contains(possibleValues, i))
				result.remove(i);
		}
			
		return result;
	}
	
	private boolean contains(int [] array, int value){
		for(int v  : array)
			if(v == value) return true;
		return false;
	}
}
