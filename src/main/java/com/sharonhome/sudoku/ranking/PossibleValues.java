package com.sharonhome.sudoku.ranking;

import java.util.ArrayList;

public class PossibleValues {
	
	ArrayList<Integer> possibilities = new ArrayList<Integer>();
	
	public PossibleValues(){
		for(int i = 0; i < 9; i++)
			possibilities.add(i);
	}

	public boolean hasSingleValue() {
		return possibilities.size() == 1;
	}

	public boolean hasDeterminedValue() {
		return possibilities.size() == 0;
	}

	public void clearAllPossibility() {
		possibilities = new ArrayList<Integer>();
		
	}

	public void remove(int i) {
		possibilities.remove(new Integer(i));
	}

	public int getSingleValue() {
		return possibilities.get(0).intValue();
	}

	public boolean isTwinOf(PossibleValues comparedPvs) {
		if (this.possibilities.size() != 2) return false;
		if (comparedPvs.possibilities.size() != 2) return false;
		return contains(this.possibilities, comparedPvs.possibilities);

	}
	
	private boolean contains(ArrayList<Integer> pvs1, ArrayList<Integer> pvs2){
		for(Integer p : pvs2)
			if(!pvs1.contains(p)) return false;
		return true;
	}

	public PossibleValues deepCopy() {
		PossibleValues result = new PossibleValues();
		result.possibilities = new ArrayList<Integer>();
		for(Integer i : this.possibilities)
			result.possibilities.add(i);
		return result;
	}
	
	@Override
	public String toString(){
		String result = "possiblities: [";
		for(int p : possibilities)
			result += p + ", ";
		result += "]";
		return result;
	}

	public void remove(PossibleValues pvsToRemove) {
		for(Integer p : pvsToRemove.possibilities)
			this.possibilities.remove(p);
		
	}
}
