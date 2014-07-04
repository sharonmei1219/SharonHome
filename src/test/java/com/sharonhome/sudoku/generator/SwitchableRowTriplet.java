package com.sharonhome.sudoku.generator;

public class SwitchableRowTriplet {

	private int bc;
	private int i1;
	private int i2;

	public SwitchableRowTriplet(int bc, int i1, int i2) {
		this.bc = bc;
		this.i1 = i1;
		this.i2 = i2;
	}
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof SwitchableRowTriplet)) return false;
		SwitchableRowTriplet that = (SwitchableRowTriplet) obj;
		if(this.bc != that.bc) return false;
		if(this.i1 != that.i1) return false;
		if(this.i2 != that.i2) return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "{bc: " + bc + ", \n i1: " + i1 + ",\n i2: " + i2 + "}\n";
	}
	public int[][] switchTriplet(int[][] table) {
		int [][] result = new int [9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j< 9; j++){
				result[i][j] = table[i][j];
			}
		}
		
		for(int j = bc * 3; j < bc*3 + 3; j++){
			result[i1][j] = table[i2][j];
			result[i2][j] = table[i1][j];
		}
		return result;
	}
}
