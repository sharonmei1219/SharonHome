package com.sharonhome.sudoku.table;

import java.util.ArrayList;

public class SudokuValidator implements TableValidator {

	public boolean validate(SudokuTable table) {
		ArrayList<int[]> groups = table.getAllRows();
		if(!validate(groups)) return false;
		groups = table.getAllColumns();
		if(!validate(groups)) return false;
		groups = table.getAllBlocks();
		if(!validate(groups)) return false;
		return true;
	}

	private boolean validate(ArrayList<int[]> groups) {
		for(int[] eachGroup : groups){
			if(!allNumberAreUniqInGroup(eachGroup)) return false;
		}
		return true;
	}

	private boolean allNumberAreUniqInGroup(int[] group) {
		boolean [] bitmap = {false, false, false, false, false, false, false, false, false};
		for(int num : group){
			int index = num - 1;
			if(bitmap[index]) return false;
			bitmap[index] = true;
		}
		return true;
	}

}
