package com.sharonhome.sudoku.ranking;

import com.sharonhome.sudoku.generator.Spot;

public class XWing {

	private int mode;
	private int num;
	private Spot[] spots;

	public XWing(int mode, int num, Spot[] spots) {
		this.mode = mode;
		this.num = num;
		this.spots = spots;
	}

	public static final int COLUMN = 0;
	public static final int ROW = 1;
	
	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof XWing)) return false;
		XWing that = (XWing) obj;
		if(this.mode != that.mode) return false;
		if(this.num != that.num) return false;
		for(Spot s : that.spots){
			if(!contains(this.spots, s)) return false;
		}
		return true;
	}

	private boolean contains(Spot[] spots, Spot spot) {
		for(Spot s: spots)
			if(s.equals(spot)) return true;
		return false;
	}
	
	@Override
	public String toString(){
		String result = "";
		if(mode == XWing.COLUMN)
			result += "C";
		result += "\n" + num;
		for(Spot s : spots)
			result += "\n" + s.toString();
		return result;
	}

	public boolean columnXWing() {
		return mode == COLUMN;
	}

	public int[] rows() {
		int [] result = new int [2];
		result[0] = spots[0].geti();
		for(Spot s : spots)
			if(s.geti() != result[0]){
				result[1] = s.geti();
				break;
			}
		return result;
	}

	public boolean contains(Spot spot) {
		for(Spot s : spots)
			if(s.equals(spot)) return true;
		return false;
	}

	public int num() {
		return num;
	}

	public boolean rowXWing() {
		return mode == ROW;
	}

	public int[] columns() {
		int [] result = new int[2];
		result[0] = spots[0].getj();
		for(Spot s : spots){
			if(s.getj()!=result[0]){
				result[1] = s.getj();
				break;
			}
		}
		return result;
	}

}
