package com.sharonhome.sudoku.generator;

public class Spot {
	private int i, j;

	public Spot(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) return false;
		if(obj.getClass() != this.getClass()) return false;
		Spot s = (Spot)obj;
		return i == s.i && j == s.j;
	}

	public int geti() {
		return i;
	}

	public int getj() {
		return j;
	}
	
	@Override
	public String toString(){
		return "i: " + i + ", "+ "j: " + j + "";
	}

}
