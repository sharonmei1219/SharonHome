package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

public class SetSet {

	private ArrayList<int []> cosets = new ArrayList<int []>();

	public SetSet(int[][] cosets) {
		for(int i = 0; i < cosets.length; i++)
			this.cosets.add(cosets[i]);
	}

	private SetSet() {}

	public int[] getSelectedValue(ArrayList<Integer> selectionInd) {
		int [] result = new int [cosets.size()];
		for(int  i = 0; i < cosets.size(); i ++)
			result[i] = cosets.get(i)[selectionInd.get(i)];
		return result;
	}

	public SetSet subSelection(ArrayList<Integer> selectionInd) {
		SetSet result = new SetSet();
		for (int i = 0; i < this.cosets.size(); i++){
			result.cosets.add(remove(this.cosets.get(i), selectionInd.get(i)));
		}
		return result;
	}

	public int[] remove(int[] array, int posToRemove) {
		int [] result = new int[array.length - 1];
		int ri = 0;
		for(int i = 0; i < array.length; i ++){
			if(i == posToRemove) continue;
			result[ri ++] = array[i];
		}
		return result;
	}

	public int getNumberOfSet() {
		return cosets.size();
	}

	public int getSetSize() {
		return cosets.get(0).length;
	}
	
	public SetSet clone(){
		SetSet result = new SetSet();
		for (int i = 0; i < this.cosets.size(); i++){
			result.cosets.add(this.cosets.get(i));
		}
		return result;
	}

}
