package com.sharonhome.sudoku.generator;

public class TripletSet {

	private int[] num;

	public TripletSet(int[] num) {
		this.num = num;
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof TripletSet)) return false;
		TripletSet that = (TripletSet)obj;
		for (int eachNum : this.num){
			if(!that.contains(eachNum)) return false;
		}
		return true;
	}

	private boolean contains(int number) {
		for(int anyNum : this.num)
			if(anyNum == number) return true;
		return false;
	}

}
