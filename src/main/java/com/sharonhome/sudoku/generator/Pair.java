package com.sharonhome.sudoku.generator;

public class Pair {
	private int i, j;

	public Pair(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) return false;
		if(obj.getClass() != this.getClass()) return false;
		Pair p = (Pair)obj;
		return i == p.i && j == p.j;
	}
	
	public String toString(){
		return "" + j;
	}

}
