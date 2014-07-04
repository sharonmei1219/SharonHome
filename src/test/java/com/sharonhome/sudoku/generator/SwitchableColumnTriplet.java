package com.sharonhome.sudoku.generator;

public class SwitchableColumnTriplet {

	private int br;
	private int j1;
	private int j2;

	public SwitchableColumnTriplet(int br, int j1, int j2) {
		this.br = br;
		this.j1 = j1;
		this.j2 = j2;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof SwitchableColumnTriplet)) return false;
		SwitchableColumnTriplet that = (SwitchableColumnTriplet) obj;
		if(this.br != that.br) return false;
		if(this.j1 != that.j1) return false;
		if(this.j2 != that.j2) return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "{br: " + br + ", \n j1: " + j1 + ",\n j2: " + j2 + "}\n";
	}

	public int[][] switchTriplet(int[][] table) {
		int [][] result = new int[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				result[i][j] = table[i][j];
			}
		}
		
		for(int i = 0; i < 3; i++){
			result[br*3 + i][j1] = table[br*3 + i][j2];
			result[br*3 + i][j2] = table[br*3 + i][j1];
		}
		return result;
	}

}
