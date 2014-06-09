package com.sharonhome.sudoku.generator;

public class HolesCandidate {

	private Spot[] candidates;

	public HolesCandidate(int rows, int columns) {
		this.candidates = new Spot[rows * columns];
		int sIndex = 0;
		for(int i =  0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				this.candidates[sIndex++] = new Spot(i, j);
			}
		}
	}

	private HolesCandidate() {
		// TODO Auto-generated constructor stub
	}

	public Spot getNthCandidate(int i) {
		return this.candidates[i];
	}

	public HolesCandidate remove(int indexToRemove) {
		HolesCandidate result = new HolesCandidate();
		result.candidates = new Spot[this.candidates.length - 1];
		int rIndex = 0;
		for(int i = 0; i < this.candidates.length; i++){
			if (i == indexToRemove) continue;
			result.candidates[rIndex ++] = this.candidates[i]; 
		}
		return result;
	}

	public int nrOfCandidates() {
		return candidates.length;
	}

}
