package com.sharonhome.sudoku.ranking;

import com.sharonhome.sudoku.generator.Spot;

public class Single {


	private int value;
	private Spot spot;

	public Single(int value, Spot spot) {
		this.value = value;
		this.spot = spot;
	}

	public int geti() {
		return spot.geti();
	}
	
	public int getj() {
		return spot.getj();
	}
	
	public int getValue(){
		return value;
	}
	
	@Override
	public String toString(){
		return "value: " + value + "\nSpot: " + spot.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Single)) return false;
		Single that = (Single) obj;
		if(this.value != that.value) return false;
		if(!this.spot.equals(that.spot)) return false;
		return true;
	}

}
