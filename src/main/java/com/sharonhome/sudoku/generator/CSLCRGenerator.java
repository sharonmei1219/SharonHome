package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

class CSLCRGenerator{
	private SetSet ss;
	private CSLCRNumberingSystem nrs;

	public CSLCRGenerator(SetSet ss, CSLCRNumberingSystem nrs){
		this.ss = ss;
		this.nrs = nrs;
	}
	
	public ArrayList<int[]> getAllSelection(int nth){
		ArrayList<int[]> result = new ArrayList<int[]>();
		ArrayList<ArrayList<Integer>> selection = nrs.getNthNumber(nth);
		SetSet rest = ss.clone();
		result.add(rest.getSelectedValue(selection.get(0)));
		rest = rest.subSelection(selection.get(0));
		result.add(rest.getSelectedValue(selection.get(1)));
		rest = rest.subSelection(selection.get(1));
		result.add(rest.getSelectedValue(selection.get(2)));
		return result;
	}
}
