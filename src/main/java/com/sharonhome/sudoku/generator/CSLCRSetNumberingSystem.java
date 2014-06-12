package com.sharonhome.sudoku.generator;

import java.util.ArrayList;

class CSLCRSetNumberingSystem{
	ArrayList<NumberingSystem> nrSystem = new ArrayList<NumberingSystem>();
	NumberingSystem highOrderNrSystem;
	public CSLCRSetNumberingSystem(){
		nrSystem.add(new NumberingSystem(new int [] {3, 3, 3}));
		nrSystem.add(new NumberingSystem(new int [] {2, 2, 2}));
		nrSystem.add(new NumberingSystem(new int [] {1, 1, 1}));
		highOrderNrSystem = new NumberingSystem(new int[]{
				nrSystem.get(0).total(),
				nrSystem.get(1).total(),
				nrSystem.get(2).total()
		});
	}
	
	public ArrayList<ArrayList<Integer>> getNthNumber(int nth){
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> highOrder = highOrderNrSystem.getNthNumber(nth);
		result.add(nrSystem.get(0).getNthNumber(highOrder.get(0)));
		result.add(nrSystem.get(1).getNthNumber(highOrder.get(1)));
		result.add(nrSystem.get(2).getNthNumber(highOrder.get(2)));
		return result;
	}
	
	public int total(){
		return highOrderNrSystem.total();
	}
}