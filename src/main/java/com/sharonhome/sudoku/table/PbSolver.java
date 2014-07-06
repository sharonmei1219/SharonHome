package com.sharonhome.sudoku.table;

public class PbSolver {

	public void solve(CanTable table, ResultCollector result) {
		if(table.full()){
			result.collect(table);
			return;
		}
		
		int [] candidates = table.candidatesForNextEmptyCell();
		for(int eachCandidate : candidates){
			CanTable newTable = table.fillEmptyCell(eachCandidate);
			if(!newTable.validate()) continue;
			solve(newTable, result);
			if(result.done()) return;
		}
	}
}
