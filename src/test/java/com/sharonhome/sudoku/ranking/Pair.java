package com.sharonhome.sudoku.ranking;

import com.sharonhome.sudoku.generator.Spot;

public class Pair {

	private PossibleValues pv;
	private Spot spot1;
	private Spot spot2;

	public Pair(PossibleValues cellPV, Spot spot, Spot spot2) {
		this.pv = cellPV.deepCopy();
		this.spot1 = spot;
		this.spot2 = spot2;
	}
	
	@Override
	public boolean equals(Object anotherPair){
		if(!(anotherPair instanceof Pair)) return false;
		
		Pair that = ((Pair)anotherPair);
		if(!this.pv.isTwinOf(that.pv)) return false;
		
		boolean equality = (this.spot1.equals(that.spot1) && this.spot2.equals(that.spot2))
				         ||(this.spot1.equals(that.spot2) && this.spot2.equals(that.spot1));
		
		return equality;
	}

	public boolean inARow() {
		return spot1.geti() == spot2.geti();
	}

	public int row() {
		return spot1.geti();
	}

	public boolean inAColumn() {
		return spot1.getj() == spot2.getj();
	}

	public int column() {
		return spot1.getj();
	}

	public boolean inABlock() {
		boolean inSameBR = spot1.geti()/3 == spot2.geti()/3;
		boolean inSameBC = spot1.getj()/3 == spot2.getj()/3;
		return inSameBR && inSameBC;
	}

	public Spot block() {
		return new Spot(spot1.geti()/3, spot1.getj()/3);
	}
	
	@Override
	public String toString(){
		return "pv: " + pv.toString() + "\nspot1: " + spot1.toString() + "\nspot2: " + spot2.toString();  
	}

	public boolean has(Spot spot) {
		return spot1.equals(spot) || spot2.equals(spot);
	}

	public PossibleValues possibilities() {
		return pv;
	}

}
