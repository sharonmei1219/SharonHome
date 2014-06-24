package com.sharonhome.sudoku.ranking;

import com.sharonhome.sudoku.generator.Spot;

public class Triple {

	private PossibleValues possibleValues;
	private Spot[] spots;

	public Triple(PossibleValues union, Spot[] spots) {
		this.possibleValues = union;
		this.spots = spots;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Triple)) return false;
		Triple that = (Triple) obj;
		if(!this.possibleValues.equals(that.possibleValues)) return false;
		for(Spot p : that.spots)
			if(!this.contains(p)) return false;
		return true;
	}
	
	@Override
	public String toString(){
		String result = possibleValues.toString();
		for(Spot s : spots)
			result += "\n" + s.toString();
		
		return result;
	}

	public boolean inARow() {
		int row = spots[0].geti();
		for(Spot s : spots)
			if(s.geti() != row) return false;
		return true;
	}

	public int row() {
		return spots[0].geti();
	}

	public PossibleValues possibleValues() {
		return possibleValues;
	}

	public boolean contains(Spot spot) {
		for(Spot s: spots)
			if(s.equals(spot)) return true;
		return false;
	}

	public boolean inAColumn() {
		int column = spots[0].getj();
		for(Spot s : spots)
			if(s.getj() != column) return false;
		return true;
	}

	public int column() {
		return spots[0].getj();
	}

	public boolean inABlock() {
		int br = spots[0].geti()/3;
		int bc = spots[0].getj()/3;
		for(Spot s : spots)
			if(!(s.geti()/3 == br && s.getj()/3 == bc)) return false;
		return true;
	}

	public int br() {
		return spots[0].geti()/3;
	}

	public int bc() {
		return spots[0].getj()/3;
	}
}
